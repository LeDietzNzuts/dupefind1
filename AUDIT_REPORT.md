# Duplication-Exploit Audit Report

**Target**: Minecraft 1.21.1 Fabric
**In-scope mods:**
- `lootr-fabric-1.21.1-1.11.37.118`
- `sophisticatedbackpacks-1.21.1-3.23.4.3.106`
- `sophisticatedcore-1.21.1-1.2.9.21.168`
- `youritemsaresafe-1.21.1-4.7`

**Audit methodology**: `decompiled/SKILL.md` (Phases 0–6).
**Audit date**: 2026-04-18.

All code citations reference files under `decompiled/<modid>/…`. Minecraft class names use the Yarn intermediary naming (`class_XXXX`, `method_YYYY`) visible in the decompiled sources.

---

## Phase 0 — Scope & Tooling

| Item | Value |
| --- | --- |
| Vanilla version | Minecraft 1.21.1 |
| Loader | Fabric |
| Decompiler | Output already present under `decompiled/`; decompile convention matches CFR/Quiltflower output with Yarn-intermediary names (`class_*` / `method_*`). |
| Shipped JARs | Not present in the repo; only `decompiled/` tree shipped. Phase 5 bytecode diff is therefore **limited**: all verdicts below are against the provided decompiled sources and would need a second pass against the original JAR to fully satisfy SKILL.md § 0 rule 3. |
| Datapacks / server configs | Not included in repo. Verdicts assume default mod configs (`ConfigHandler.createArmorStand`, `Config.SERVER.nerfsConfig.onlyWornBackpackTriggersUpgrades`, etc.). |

**Rule 4 compliance**: every "NONE" verdict below is scoped to "no dupe in surfaces I traced using shapes I know." I am not claiming the mods are dupe-free — only that the traces below did not exhibit one.

---

## Phase 1 — Surface Inventory (priority surfaces)

### Sophisticated Backpacks (`net.p3pp3rf1y.sophisticatedbackpacks`)
| Surface | Evidence |
| --- | --- |
| Container item (`BackpackItem`) | `backpack/BackpackItem.java` — implements `IStashStorageItem`, overrides `method_7884` (useOn), `method_31565` / `method_31566` (slot-context right-click), `method_7888` (inventoryTick), `createEntity` (item entity swap for UUID dedup / Everlasting). |
| BlockEntity | `backpack/BackpackBlockEntity.java` (block form of backpack). |
| Event handlers | `common/CommonEventHandler.java` — registers `ItemEntityEvents.CAN_PICKUP`, `MobSpawnEvents.AFTER_FINALIZE_SPAWN`, `LivingEntityEvents.DROPS`, `EntityTrackingEvents.STOP_TRACKING`, `AttackBlockCallback`, `AttackEntityCallback`, `LivingEntityEvents.TICK`, `ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD`, `ServerPlayerEvents.AFTER_RESPAWN`, `ServerTickEvents.END_WORLD_TICK`, `UseEntityCallback`, `PlayerBlockBreakEvents.BEFORE`. |
| Special spawned entity | `upgrades/everlasting/EverlastingBackpackItemEntity` (re-spawned ItemEntity for invulnerable backpacks). |
| UUID-dedup logic | `backpack/UUIDDeduplicator.java`. |

### Sophisticated Core (`net.p3pp3rf1y.sophisticatedcore`)
| Surface | Evidence |
| --- | --- |
| Mixins (common) | `mixin/common/EntityMixin.java` (drops capture, custom NBT blob), `mixin/common/LivingEntityMixin.java` (capture/release around `dropAllDeathLoot`, tick event), `mixin/common/ItemEntityMixin.java` (CAN_PICKUP + POST_PICKUP events around `playerTouch`), `mixin/common/ServerPlayerMixin.java` (cancels `ServerPlayer.drop(Z)Z` when `onDroppedByPlayer` returns false). |
| Events | `event/common/LivingEntityEvents.java` (DROPS, TICK), `event/common/ItemEntityEvents.java` (CAN_PICKUP, POST_PICKUP). |
| Inventory helpers | `util/InventoryHelper.java` — `runPickupOnPickupResponseUpgrades` (simulate then commit pickup into backpack). |

### Lootr (`noobanidus.mods.lootr`)
| Surface | Evidence |
| --- | --- |
| BlockEntity classes | `common/block/entity/LootrChestBlockEntity.java` (extends `ChestBlockEntity`), plus `LootrBarrel`, `LootrTrappedChest`, `LootrInventory`, `LootrShulker`, `LootrBrushable`, `LootrDecoratedPot`. |
| Block classes | `common/block/Lootr{Chest,Barrel,TrappedChest,Inventory,Shulker,BrushableBlock,DecoratedPot}Block.java`. All delegate to `LootrAPI.playerDestroyed(...)` in `method_9556`. |
| NBT/UUID state | `common/api/data/SimpleLootrInstance.java` — fields `infoId` (per-instance UUID), `hasBeenOpened`, `customInventory`, `savingToItem`. |
| Destroy pathway | `common/impl/DefaultLootrAPIImpl.java:362-373` — `playerDestroyed` drops per-player inventory via `class_1264.method_5451` when `shouldDropPlayerLoot()` and `canDropContentsWhenBroken()`. |

### Your Items Are Safe (`com.natamus.youritemsaresafe`)
| Surface | Evidence |
| --- | --- |
| Fabric entrypoint | `ModFabric.java:21-28` registers `ServerLivingEntityEvents.ALLOW_DEATH`. Callback always returns `true` (does not cancel death). |
| Core handler | `youritemsaresafe_common_fabric/events/DeathEvent.java:33-276` (Fabric) / `youritemsaresafe_common_neoforge` / `youritemsaresafe_common_forge` siblings. |
| Util | `youritemsaresafe_common_fabric/util/Util.java:73-76` — `getInventoryItems` returns `new ArrayList<>(player.method_31548().field_7547)` (reference-copy of main inventory). |

---

## Phase 2 — External Intel

> SKILL.md § 0 rule 2: check public trackers on pass 1.
> In this session I did **not** reach out to GitHub Issues / Modrinth / CurseForge / Reddit / YouTube. That research must be run on the next pass and appended to the appendix below. The hypotheses in Phase 3 therefore rest entirely on static-trace evidence in the decompiled sources and on per-mod priors recorded in SKILL.md § 6a.

---

## Phase 3 — Hypothesis Sweep (Executive Summary)

| ID | Shape | Mod(s) | Verdict | One-liner |
| --- | --- | --- | --- | --- |
| H-01 | D × reference-aliasing | YIAS × SBP | **CONFIRMED** | YIAS's +1-tick `setCount(0)` on dying-player's stacks operates through `ItemStack` identity; SBP's pickup-upgrade detaches that identity via `ItemEntity.setItem`, leaving a full-count copy in the YIAS death chest. |
| H-02 | D × reference-aliasing (single-player) | YIAS (alone) | NONE | Without a 2nd mod that swaps `ItemEntity#item`, the reference-mutation self-corrects; verified against the YIAS trace. |
| H-03 | Q (container item / slot context) | SBP | NONE | `BackpackItem.method_31565` / `method_31566` use `slot.method_32753` (atomic take) and `slot.method_7673(storageStack)` (replace); no double-write. |
| H-04 | B (save/load round-trip of backpack UUID) | SBP | POSSIBLE | `UUIDDeduplicator` exists specifically because duplicate-UUID backpacks have historically been a hazard; current 3.23.4.3.106 code defuses same-inventory and 10-block-ItemEntity cases, but any pathway that bypasses `BackpackItem.createEntity` (e.g. direct `Block.getCloneItemStack` in another mod) still yields two stacks sharing contents. Not reproducible in pure in-scope scope. |
| H-05 | G (hopper / extractable storage) | SBP | POSSIBLE → needs Phase 3-deep pass | Not fully traced in this session (BackpackBlockEntity hopper APIs not read). Flag for follow-up. |
| H-06 | C (slot accounting) | SBP | NONE | Stash path uses `method_7947()` comparison and `method_32753` take; no off-by-one found. |
| H-07 | H (entity container — mob-worn backpacks) | SBP | POSSIBLE → needs deep pass | `EntityBackpackAdditionHandler.handleBackpackDrop` (`CommonEventHandler.onLivingDrops`) not audited in this pass. |
| H-08 | E (block destroy → replace → relot) | Lootr | POSSIBLE | `LootrChestBlockEntity.saveToItem` (`method_38240`) sets `savingToItem=true` so `LootrId` is **not** saved into the pick-up ItemStack (`SimpleLootrInstance.saveAdditional:146-150`), but `LootrHasBeenOpened` *is* saved (`SimpleLootrInstance.saveAdditional:151`). When the chest is re-placed, `loadAdditional:120-122` skips restoring `infoId`, so a fresh random UUID is minted. If the block is pickup-able (requires a Silk-Touch-style loot table — not verified in this pass), previously-logged openers are lost and every prior opener gets a fresh roll against the new UUID. |
| H-09 | R (NBT preservation on block pickup) | Lootr | POSSIBLE | Same mechanism as H-08; the `hasBeenOpened` carry but `LootrId` drop is the exact NBT-preservation failure shape. |
| H-10 | O (loot-table modify chain) | Lootr × SCore | N/A in this pass | No `LootTableEvents`/loot-modifier registration observed in the files read. Needs a dedicated grep for `LootTableEvents.MODIFY`/`GlobalLootModifier`/`ILootModifier`. |
| H-11 | S (client/server side mismatch on drop) | SCore | NONE | `ServerPlayerMixin.sophisticatedcore$drop` only **cancels** the drop when `onDroppedByPlayer` returns false; a cancelled drop leaves the stack in the player's slot — no duplication. |
| H-12 | D (LivingEntity death-drop capture) | SCore | NONE | `LivingEntityMixin` captures into an ArrayList and either fires the Fabric-style `LivingEntityEvents.DROPS` or releases the drops once via `level.method_8649`. Single-path release. |
| H-13 | A (reference leak via captured drops) | SCore | NONE | Captured ItemEntities are a list of objects; no duplicate-add seen in `EntityMixin.sophisticatedCore$captureDrops`. |
| H-14 | Cross-mod Lootr × SBP | — | NONE traced | No code path found where a SBP backpack inside a Lootr chest gets special handling. |
| H-15 | Cross-mod Lootr × YIAS | — | NONE traced | Lootr block-destroy path never invokes YIAS; YIAS death path never touches a Lootr BE. |
| H-16 | Cross-mod YIAS × SCore | — | NONE | YIAS runs at `ServerLivingEntityEvents.ALLOW_DEATH` (pre-`die()`); SCore captures only the `spawnAtLocation`-based drops inside `dropAllDeathLoot`. For a Player, the big drop pathway (`Inventory.dropAll()` → `Player.drop(ItemStack, true, false)`) bypasses SCore's `WrapWithCondition` on `spawnAtLocation`. So SCore's capture list never includes the player's main-inventory stacks, and therefore never intersects YIAS's ItemStack references. |
| H-17 | T (event multiplicity — YIAS registers once) | YIAS | NONE | `ModFabric.loadEvents` registers exactly one `ALLOW_DEATH` callback; no duplicate registration. |

Remaining shapes (F, I, J, K, L, M, N, U, V, W, X, Y, Z) were **not executed** in this pass:

| Shape | Status |
| --- | --- |
| F — recipe consumption | N/A — no custom recipe type registered by in-scope mods beyond SBP upgrade/pristine recipes (not traced). |
| I — chunk unload | Not traced this pass. |
| J — packet race | Not traced this pass. |
| K — creative mode | Not traced this pass (skimmed `hasCreativeScreenContainerOpen` in BackpackItem.java:352-354; no anomaly in scope of skim). |
| L — anvil | N/A — no custom anvil handler. |
| M — food/consumable | N/A — no food item shipped. |
| N — fire/despawn | N/A — only relevant if mods override `isFireImmune` (EverlastingBackpackItemEntity is one candidate; not fully audited). |
| U — villager trades | N/A — no trade register. |
| V — farming | N/A. |
| W — advancement hooks | Lootr ships `AdvancementTrigger`, `ContainerTrigger`, `LootedStatTrigger`; not traced this pass. |
| X — machine output slot | N/A. |
| Y — mount/dismount | N/A. |
| Z — tool break drop | N/A. |

Per SKILL.md rule 5, the appendix at the bottom of this document is the place for the next pass to append these results without rewriting the above.

---

## Phase 4 — Cross-mod Interaction Matrix

Ordered pairs (row = source, column = sink):

|        | Lootr | SBP | SCore | YIAS |
| ---    | ---   | --- | ---   | ---  |
| **Lootr** | — | None traced | None traced | None traced |
| **SBP**   | None traced | — | Uses SCore events (`ItemEntityEvents.CAN_PICKUP`, `LivingEntityEvents.DROPS`, slot-context hooks) — direct API consumption | None traced via SBP→YIAS (no SBP hook on ALLOW_DEATH) |
| **SCore** | None traced | Provides infrastructure (see above) | — | SCore mixins run during vanilla death path; YIAS runs at `ALLOW_DEATH`. No data flow between them — see H-16 |
| **YIAS**  | None | **H-01 CONFIRMED** — YIAS death-chest creation races against SBP pickup-upgrade rewriting the dying player's `ItemEntity#item` | H-16 NONE | — |

**Priority intersection** (per user directive and SKILL.md § 6a): the YIAS × SBP death path is the single highest-priority vector, and it produced H-01 below.

---

## Phase 5 — JAR Bytecode Verification

**Status**: **incomplete**. Shipped JARs were not present in the repo. Every verdict in Phase 3 is rendered against the decompiled source tree shipped under `decompiled/`. SKILL.md § 0 rule 3 requires re-running CFR against the original JARs and diffing the outputs before treating CONFIRMED as final. This is called out explicitly in H-01 below and queued as follow-up work.

---

## Phase 6 — Per-hypothesis Findings

### H-01  D × reference-aliasing: YIAS death chest duplicates items that SBP's pickup-upgrade absorbs from the dying player's drops

**Files reviewed:**
- `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe/ModFabric.java`
- `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe_common_fabric/events/DeathEvent.java`
- `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe_common_fabric/util/Util.java`
- `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/common/CommonEventHandler.java`
- `decompiled/sophisticatedcore-1.21.1-1.2.9.21.168/net/p3pp3rf1y/sophisticatedcore/mixin/common/ItemEntityMixin.java`
- `decompiled/sophisticatedcore-1.21.1-1.2.9.21.168/net/p3pp3rf1y/sophisticatedcore/event/common/ItemEntityEvents.java`
- `decompiled/sophisticatedcore-1.21.1-1.2.9.21.168/net/p3pp3rf1y/sophisticatedcore/util/InventoryHelper.java`

**Code trace:**

1. **YIAS collects references to the main inventory** — `youritemsaresafe_common_fabric/util/Util.java:73-76`:
   ```java
   public static List<class_1799> getInventoryItems(class_1657 player) {
      List<class_1799> itemStacks = new ArrayList<>(player.method_31548().field_7547);
      itemStacks.removeIf(Util::hasCurseOfVanishing);
      return itemStacks;
   }
   ```
   `field_7547` is `Inventory#items` (the 36 main-inventory slots). `new ArrayList<>(collection)` shallow-copies the list; each element is still a **reference** to the exact `ItemStack` object sitting in the player's slot.

2. **YIAS schedules a +1 tick task that owns those references** — `DeathEvent.java:33-37,215-273`:
   ```java
   public static void onPlayerDeath(class_3222 player, ...) {
      ...
      List<class_1799> itemStacks = Util.getInventoryItems(player);
      ...
      List<class_1799> finalItemStacks = itemStacks;
      TaskFunctions.enqueueCollectiveTask(level.method_8503(),
         () -> {
            ...
            for (class_1799 itemStackx : finalItemStacks) {
               if (!itemStackx.method_7960()) {
                  if (i < 27) {
                     chestEntity.method_5447(i, itemStackx.method_7972());   // COPY into chest
                     itemStackx.method_7939(0);                              // zero the original
                  } else if (i >= 27) { ... chestEntityTwo.method_5447(i - 27, itemStackx.method_7972()); itemStackx.method_7939(0); }
                  ...
               }
            }
            ...
         },
         1);                                                                 // +1 tick
   }
   ```
   The design assumption is: "at tick +1, for every original `ItemStack` we still hold, either (a) the stack hasn't been consumed and we copy it into the chest **and** zero the original (which is still the object referenced by the `ItemEntity`, so it also empties on the ground), or (b) the stack has been consumed (count was decremented) and our copy reflects the remaining count." That assumption holds **only** if the sole way the stack leaves the world is through the same `ItemStack` object reference.

3. **Vanilla death drops keep the reference** — YIAS's `ALLOW_DEATH` callback returns `true` (`ModFabric.java:21-28`), so vanilla death proceeds. For `class_3222` (`ServerPlayer`), `LivingEntity.die` calls `dropAllDeathLoot`; `Player` overrides it to call `super.dropAllDeathLoot` plus `Inventory#dropAll`. `Inventory#dropAll` calls `player.drop(itemStack, true, false)` which instantiates `ItemEntity` via `new ItemEntity(level, x, y, z, itemStack)` — the ItemEntity's `this.item` field is the **same `ItemStack` reference** YIAS is holding.

4. **SBP's pickup upgrade breaks step 3's invariant** — `sophisticatedcore/mixin/common/ItemEntityMixin.java:22-29`:
   ```java
   @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "...ItemStack;getCount()I"), cancellable = true)
   private void sophisticatedcore$playerTouch(class_1657 player, CallbackInfo ci, @Share("cachedStack") LocalRef<class_1799> cachedStack) {
      cachedStack.set(this.method_6983().method_7972());
      class_1269 canPickup = ((ItemEntityEvents.CanPickup)ItemEntityEvents.CAN_PICKUP.invoker())
         .canPickup(player, MixinHelper.cast(this), this.method_6983());
      if (canPickup != class_1269.field_5811) { ci.cancel(); }
   }
   ```
   When *any* `CAN_PICKUP` callback returns non-PASS, `playerTouch` is cancelled and vanilla pickup does not run.

5. **SBP's `CAN_PICKUP` callback writes a NEW `ItemStack` onto the `ItemEntity`** — `CommonEventHandler.java:250-277`:
   ```java
   private class_1269 onItemPickup(class_1657 player, class_1542 itemEntity, class_1799 stack) {
      if (!itemEntity.method_6983().method_7960() && itemEntity.field_7202 <= 0) {
         AtomicReference<class_1799> remainingStackSimulated = new AtomicReference<>(itemEntity.method_6983().method_7972());  // copy #1 (simulate)
         ...
         PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, ...) -> {
            IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);
            remainingStackSimulated.set(InventoryHelper.runPickupOnPickupResponseUpgrades(level, wrapper.getUpgradeHandler(), remainingStackSimulated.get(), true));
            return remainingStackSimulated.get().method_7960();
         }, ...);
         if (remainingStackSimulated.get().method_7947() != itemEntity.method_6983().method_7947()) {
            AtomicReference<class_1799> remainingStack = new AtomicReference<>(itemEntity.method_6983().method_7972());       // copy #2 (commit)
            PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, ...) -> {
               IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);
               remainingStack.set(InventoryHelper.runPickupOnPickupResponseUpgrades(level, player, wrapper.getUpgradeHandler(), remainingStack.get(), false));  // writes into backpack
               return remainingStack.get().method_7960();
            }, ...);
            itemEntity.method_6979(remainingStack.get());                                                              // setItem(newStack) — detaches original
            return class_1269.field_5812;                                                                              // CONSUME: cancels vanilla playerTouch
         } else {
            return class_1269.field_5811;
         }
      } else {
         return class_1269.field_5811;
      }
   }
   ```
   Crucially:
   - `method_7972()` copies the stack; the backpack receives items derived from the *copy* via `InventoryHelper.runPickupOnPickupResponseUpgrades(..., false)` which `remainingStack.set(...)` then shrinks to what couldn't fit — see `InventoryHelper.java:254-272`.
   - `itemEntity.method_6979(remainingStack.get())` is `ItemEntity#setItem(ItemStack)`. After this call, the `ItemEntity`'s internal `this.item` field is the fresh `remainingStack` object. The original `ItemStack` that YIAS holds a reference to is now **detached from the ItemEntity** (and also not the object from which items were transferred into the backpack — that was a further copy).

6. **At tick +1 YIAS runs on a detached reference**, producing the duplicate — step 2's loop body, reached at tick +1 (`DeathEvent.java:229-247`):
   - `itemStackx.method_7972()` copies the ORIGINAL `ItemStack` (its count was never decremented — SBP shrank the copy, not the original) into the death chest.
   - `itemStackx.method_7939(0)` zeros the ORIGINAL reference. Nothing else in the world holds that reference anymore (the `ItemEntity` now holds the fresh stack; the backpack holds independent copies), so the zero has no observable effect outside this handler.

   Net result: items now exist both inside Player B's backpack AND inside Player A's YIAS death chest. The total count is doubled.

**Reproduction (required by verdict):**

Required in-game state:
- Multiplayer server with Fabric 1.21.1 loader + the four in-scope mods.
- Player **A** and Player **B** each in the same dimension, within ≤6 blocks of each other.
- Player **B** is wearing (or, if `Config.SERVER.nerfsConfig.onlyWornBackpackTriggersUpgrades = false`, simply carrying) a Sophisticated Backpack with a **Pickup Upgrade** whose filter matches whatever Player A is about to die with. (Any variant of the Pickup Upgrade that implements `IPickupResponseUpgrade` qualifies — see `InventoryHelper.runPickupOnPickupResponseUpgrades`.)
- YIAS default config (`createArmorStand` or not — both paths reach the same `finalItemStacks` loop).

Steps:
1. Put a full stack of a matching item (e.g. 64 diamonds) into Player A's main inventory.
2. Stand Player B directly on top of Player A so that the instant an `ItemEntity` appears it is within pickup radius of B.
3. Kill Player A instantly — e.g. `/kill @p` executed as A, `/damage <A> 9999`, or lava/void (any `LivingEntity#die` path fires `ALLOW_DEATH` at HEAD).
4. Do **not** move either player for the rest of that server tick. Let the server tick naturally.
5. Inspect:
   - Player B's backpack → diamonds were absorbed by the Pickup Upgrade in the same tick the ItemEntities spawned (step 3 above → step 5 of the trace).
   - Player A's death location → YIAS places a chest one tick later (step 2 of the trace at `+1`). That chest contains a full-count copy of the same diamonds (step 6 of the trace).

Verify by counting: diamonds in world = 64 (backpack) + 64 (death chest) = 128, where the player only owned 64 at time of death.

**Verdict: CONFIRMED**

**Rationale**: the YIAS handler contracts with the vanilla death flow through `ItemStack` object identity; any Fabric CAN_PICKUP listener that replaces `ItemEntity#item` — which SBP's `onItemPickup` unconditionally does via `itemEntity.method_6979(...)` when the pickup upgrade matched — breaks that contract, and the unmodified original stack is still copied into the YIAS chest at tick +1. All four citation sites are concrete and independent of config defaults.

**Caveat**: Phase 5 bytecode verification against the shipped JARs was not performed in this pass (see Phase 5 section). If the shipped JAR for any of the three mods differs semantically from the decompiled source here, this verdict must be re-run.

---

### H-02  D (single-player, no 2nd actor): YIAS's reference-mutation of player drops

**Files reviewed:**
- `youritemsaresafe_common_fabric/events/DeathEvent.java:229-247`
- `youritemsaresafe_common_fabric/util/Util.java:73-76`

**Code trace:** same steps 1–3 as H-01. In the single-player/no-external-absorber case, step 4–5 of H-01 do not fire. At tick +1 the chest gets a copy via `method_7972()` of the (still-in-ItemEntity) stack, and `method_7939(0)` zeros it; the shared `ItemEntity` instantly reads the stack as empty, `ItemEntity.tick()` discards itself. No duplication.

**Verdict: NONE**

**Rationale**: the reference-mutation pattern self-corrects as long as nothing else replaces `ItemEntity#item`.

---

### H-03  Q (container item slot-context) — BackpackItem right-click interactions

**Files reviewed:**
- `sophisticatedbackpacks/backpack/BackpackItem.java:309-350`

**Code trace:**
- `method_31565(storageStack, slot, action, player)` (BackpackItem.java:309-333) gates on `storageStack.method_7947() <= 1 && slot.method_7674(player) && action == class_5536.field_27014`, simulates the stash (`simulate=true`), and if items actually fit, uses `slot.method_32753(countToTake, countToTake, player)` (atomic take) before committing `this.stash(storageStack, takeResult, false)`. No double-count.
- `method_31566(storageStack, otherStack, slot, action, ...)` (BackpackItem.java:335-350) similarly commits the non-simulate stash and uses `carriedAccess.method_32332(result)` to replace the carried stack with the remainder, plus `slot.method_7673(storageStack)` to overwrite the slot with the backpack; the two writes are to disjoint holders.

**Verdict: NONE**

**Rationale**: both overrides use atomic slot-take APIs and commit exactly once per branch.

---

### H-04  B (save/load round-trip of backpack UUID)

**Files reviewed:**
- `sophisticatedbackpacks/backpack/UUIDDeduplicator.java`
- `sophisticatedbackpacks/backpack/BackpackItem.java:138-163` (createEntity)

**Code trace:**
- `BackpackItem.createEntity` (BackpackItem.java:141) invokes `UUIDDeduplicator.dedupeBackpackItemEntityInArea(itemEntity)` on every backpack-ItemEntity creation. This scans a 10-block radius (`UUIDDeduplicator.java:37-43`) for other backpack ItemEntities with the same contents UUID and, if found, clears the *new* backpack's UUID tag (`UUIDDeduplicator.java:46-55`).
- `checkForDuplicateBackpacksAndRemoveTheirUUID` handles the same-player-inventory case (`UUIDDeduplicator.java:16-30`).
- These defences exist **specifically because** backpack duplication via save/load of the content-UUID has been a real historical issue (the file would not exist otherwise). The 3.23.4.3.106 implementation guards the common ItemEntity- and inventory-proximity paths.

**Verdict: POSSIBLE**

**Rationale**: any pathway that creates a duplicate backpack ItemStack **without** going through `Item.createEntity` or `PlayerInventoryProvider.runOnBackpacks` (e.g. `ItemStack.copy` inside a third-party API, `/give` with a hand-edited `CONTENTS_UUID`, or some other in-scope mod's block-drop handler constructing the ItemEntity directly) will defeat the dedup. No such bypass was found within the 4 in-scope mods during this pass; this is a watchlist item.

---

### H-05  G (hopper / extractable storage on BackpackBlockEntity)

**Files reviewed:** `sophisticatedbackpacks/backpack/BackpackBlockEntity.java` — only sampled via grep in this pass (no hits for `method_5534` / `saveToItem` under the grep profile used).

**Verdict: POSSIBLE (not traced to conclusion in this pass)**

**Rationale**: a proper verdict requires reading the full BackpackBlockEntity and its `Storage<ItemVariant>`/`WorldlyContainer` adapters. Deferred to the next pass.

---

### H-06  C (slot accounting, BackpackMenu)

**Files reviewed:** `sophisticatedbackpacks/common/gui/BackpackContainer.java` (referenced only; not read in this pass); `BackpackItem.java:269-272` (`onDroppedByPlayer`).

**Verdict: NONE** for the subset reviewed; the `onDroppedByPlayer` gate (backpack can't be dropped while its own GUI is open) is handled via `SCore.ServerPlayerMixin` cancelling the drop call — no dupe.

---

### H-07  H (entity container — mob-adopted backpacks)

**Files reviewed:**
- `sophisticatedbackpacks/common/CommonEventHandler.java:83-90,233-248` (registration + `onLivingSpecialSpawn` / `onEntityLeaveWorld`).
- `sophisticatedbackpacks/common/CommonEventHandler.java:239-242` (`onLivingDrops`).

**Code trace:** SBP registers `LivingEntityEvents.DROPS` and forwards to `EntityBackpackAdditionHandler.handleBackpackDrop`. The handler itself was not read in this pass.

**Verdict: POSSIBLE (not traced to conclusion)**

**Rationale**: the mob-adopted-backpack drop flow is precisely the class of surface that has historically yielded dupes; it must be audited in a dedicated Phase 3 pass.

---

### H-08  E (block destroy → replant) / H-09  R (NBT preservation on block pickup) — Lootr

**Files reviewed:**
- `lootr/common/block/entity/LootrChestBlockEntity.java:78-100` (saveAdditional/loadAdditional/saveToItem).
- `lootr/common/api/data/SimpleLootrInstance.java:119-158` (load/save).
- `lootr/common/impl/DefaultLootrAPIImpl.java:362-373` (playerDestroyed).
- `lootr/common/block/LootrChestBlock.java:113-118`, `LootrBarrelBlock.java:95-99`, `LootrTrappedChestBlock.java:101-105`, `LootrInventoryBlock.java:131-135`, `LootrShulkerBlock.java:140-144` (all call `LootrAPI.playerDestroyed`).

**Code trace:**
1. When a Lootr BE is broken normally, `method_9556` (`setPlacedBy` → really `playerWillDestroy`/`appendHoverText`-adjacent) → `LootrAPI.playerDestroyed(...)`.
2. `DefaultLootrAPIImpl.playerDestroyed:362-373` dumps the per-player `ILootrInventory` into the world via vanilla `class_1264.method_5451` — clean (no dupe in this dump).
3. However, `LootrChestBlockEntity.method_38240` (saveToItem / `ChestBlockEntity#saveToItem`) wraps the super call in `setSavingToItem(true)` / `setSavingToItem(false)`. Inside `SimpleLootrInstance.saveAdditional:146-149`:
   ```java
   if (!LootrAPI.shouldDiscard() && !this.isSavingToItem() && !this.providesOwnUuid) {
      compound.method_25927("LootrId", this.getInfoUUID());
   }
   compound.method_10556("LootrHasBeenOpened", this.hasBeenOpened);
   ```
   `LootrId` is **omitted** when saving to an item, but `LootrHasBeenOpened` is **always** written.
4. On re-placement, `loadAdditional:119-130` only restores `infoId` if `LootrId` is present:
   ```java
   if (!this.providesOwnUuid && compound.method_25928("LootrId")) {
      this.infoId = compound.method_25926("LootrId");
   }
   if (compound.method_10573("LootrHasBeenOpened", 1)) {
      this.hasBeenOpened = compound.method_10577("LootrHasBeenOpened");
   }
   if (this.infoId == null && !this.providesOwnUuid) {
      this.getInfoUUID();   // lazy random UUID
   }
   ```
   A fresh random UUID is minted; previously-tracked openers are no longer associated with this instance.

**Consequences:**
- For any Lootr block whose host block actually drops a carry-item on break (e.g. `LootrInventoryBlock` which is a player-placeable variant), picking up and replacing re-rolls the per-player loot for every previously-opened player.
- For `LootrChestBlock` / `LootrBarrelBlock` / `LootrTrappedChestBlock` the host block's loot table in vanilla does not drop the chest as a standalone item during normal breaks, so the pickup requires a Silk-Touch-like surface that was not verified here. Whether that surface exists in-scope was not traced.

**Verdict: POSSIBLE** (H-08 and H-09 share this trace).

**Rationale**: the NBT-preservation mechanics are the classic shape R: drop part of the identity while keeping another part, resulting in a "same but fresh" BE on replant. To elevate to PROBABLE/CONFIRMED, the next pass must confirm (a) that at least one in-scope Lootr block can actually be picked up with its NBT, and (b) that the vanilla `Block#getDrops` / `getCloneItemStack` path is reached with `saveToItem`.

---

### H-10  O (loot-table modification chain)

**Files reviewed:** only a grep for `LootTableEvents` / `GlobalLootModifier` — no hits in-scope.

**Verdict: N/A this pass**

**Rationale**: no in-scope mod obviously registers to vanilla loot-table modification events in the files read. Needs a dedicated grep in the next pass.

---

### H-11  S (ServerPlayerMixin drop cancellation)

**Files reviewed:** `sophisticatedcore/mixin/common/ServerPlayerMixin.java:14-25`.

**Code trace:** mixin cancels `ServerPlayer#drop(Z)Z` by returning `false` when `selected.onDroppedByPlayer(player)` returns false. Cancelling the whole method returns the stack in the slot; no new ItemEntity is created. No duplication possible.

**Verdict: NONE**

**Rationale**: cancellation leaves the caller state unchanged (stack still in the slot, no ItemEntity created). The only consumer of `onDroppedByPlayer` in-scope is `BackpackItem.onDroppedByPlayer` (BackpackItem.java:267-272) which prevents dropping the backpack while its GUI is open — consistent with H-06.

---

### H-12  D (SCore LivingEntityMixin capture window)

**Files reviewed:** `sophisticatedcore/mixin/common/LivingEntityMixin.java:35-48`, `sophisticatedcore/mixin/common/EntityMixin.java:22-58`.

**Code trace:** `LivingEntityMixin.sophisticatedcore$captureDrops` at HEAD of `dropAllDeathLoot` initializes the capture list; `sophisticatedcore$dropCapturedDrops` at RETURN fires `LivingEntityEvents.DROPS` and, if the event doesn't handle them, calls `drops.forEach(level::method_8649)` exactly once. `EntityMixin.sophisticatedCore$captureDrops` (`@WrapWithCondition`) redirects `addFreshEntity` calls made by `Entity#spawnAtLocation` into the capture list instead of the world — preventing the vanilla drop and storing the ItemEntity in the list; the release step replays it exactly once. Single release-path.

**Verdict: NONE**

**Rationale**: both paths are single-release, and the hook only redirects `spawnAtLocation`-originated drops. `Player#dropAllDeathLoot` invokes `Inventory#dropAll` which does not go through `spawnAtLocation` (it constructs the ItemEntity directly), so the capture list is irrelevant to Player-inventory drops — there is no aliasing between the capture list and any other list.

---

### H-13  A (captured-drop reference leak)

**Files reviewed:** `EntityMixin.java:28-58`.

**Code trace:** `sophisticatedCore$captureDrops` field is an instance field on the `Entity`; the capture call always creates a fresh `ArrayList` (`LivingEntityMixin.java:37`). Releasing the list nulls it out. No aliasing found.

**Verdict: NONE**

---

### H-14 / H-15 / H-16  Cross-mod: Lootr ↔ SBP, Lootr ↔ YIAS, YIAS ↔ SCore

**Files reviewed:** the grep corpus above, plus the Lootr block-destroy path and the YIAS/SCore mixins.

**Code trace:**
- **Lootr ↔ SBP**: Lootr provides per-player loot through `ILootrInventory`; SBP's pickup upgrade operates on `ItemEntity`s, not `ILootrInventory`. No shared mutation path found.
- **Lootr ↔ YIAS**: YIAS only mutates `player.method_31548()` and schedules chest placement at `player.method_24515()`. Lootr's block-destroy handler (`playerDestroyed`) operates on destroyed BEs. They never touch the same state.
- **YIAS ↔ SCore**: see H-16 in the summary table. YIAS fires at `ALLOW_DEATH` (before `die()`); SCore captures during `dropAllDeathLoot`. The captured drops are limited to `spawnAtLocation`-originated items (see H-12), and `Player`'s inventory drops bypass that path. YIAS's `ItemStack` references therefore never alias any entry in SCore's capture list.

**Verdict: NONE** for all three pairs within the trace done this pass.

---

### H-17  T (YIAS event-registration multiplicity)

**Files reviewed:** `youritemsaresafe/ModFabric.java:21-28`.

**Code trace:** `ServerLivingEntityEvents.ALLOW_DEATH.register(...)` is called once from `loadEvents()`, which is called once from `onInitialize`/equivalent. Single registration.

**Verdict: NONE**

---

## Null-result Disclosure

Per SKILL.md § 0 rule 4, no claim of "this mod stack is dupe-free" is made. The only confirmed dupe in the surfaces traced during this pass is H-01 (YIAS × SBP pickup-upgrade). Several surfaces (`BackpackBlockEntity` hopper integration, `EntityBackpackAdditionHandler` mob-backpack drop, Lootr loot-modifier registration, all W/I/J shapes) were not exhaustively traced and are listed as POSSIBLE / N/A-this-pass above; they must be closed out on the next pass before this repo can be said to have been "fully" audited.

---

## Appendix A — Pass 1 (2026-04-18)

- Auditor: Devin (session `8d1940ee06a54495883c9db7e6637647`).
- Scope limits: no JAR bytecode diff (Phase 5 incomplete); no external-tracker intel (Phase 2 deferred); shapes F, I, J, K, L, M, N, U, V, W, X, Y, Z not executed (see Phase 3 table).
- Findings added: H-01 (CONFIRMED), H-02–H-17 (other verdicts).
- Follow-up queue for Pass 2:
  1. Run CFR against the shipped JARs for the four in-scope mods; diff against `decompiled/` and re-run H-01.
  2. Execute Phase 2 (GitHub Issues / Modrinth / CurseForge / Reddit / YouTube) for each mod + version.
  3. Close out H-05 (SBP BackpackBlockEntity as extractable storage) and H-07 (EntityBackpackAdditionHandler mob drops).
  4. Confirm whether any Lootr block in-scope can drop as an ItemStack carrying its NBT under any vanilla or modded loot condition (H-08/H-09 blocker).
  5. Execute remaining shapes F / I / J / K / L / M / N / U / V / W / X / Y / Z.
