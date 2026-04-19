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

---

## Appendix B — Pass 2 (2026-04-18)

**Auditor**: Devin (session `863e86d89f9a493a872fe1a7a32246b0`).

**Pass 2 objective**: close the POSSIBLE verdicts from Pass 1 (H-05, H-07), and execute shapes B, I, J, N that were deferred. Per user directive, report only **solo-reproducible** dupes — single-player survival or creative, no LAN, no second player, no server-admin access. Two-player shapes remain logged at their Pass 1 verdict (H-01 CONFIRMED, not re-pitched).

**Scope limits carried from Pass 1**: Phase 5 (JAR ↔ source bytecode diff) is still incomplete; all verdicts below are against the decompiled tree under `decompiled/`.

### Pass 2 findings matrix

| ID | Shape | Target | Pass 1 verdict | Pass 2 verdict |
| --- | --- | --- | --- | --- |
| H-05 | G (extractable storage) | `BackpackBlockEntity` | POSSIBLE | **NONE** (see below) |
| H-07 | H (entity/dismount) | `EntityBackpackAdditionHandler` | POSSIBLE | **NONE** (solo) |
| H-18 | B (save/load round-trip) | `BackpackBlockEntity` | — | **NONE** |
| H-19 | I (chunk unload timing) | `BackpackBlockEntity` | — | **NONE** |
| H-20 | J (packet race) | SBP + SCore payloads | — | **NONE** solo; one POSSIBLE cross-mod surface noted |
| H-21 | N (fire/despawn immunity) | `EverlastingBackpackItemEntity` | — | **NONE** |

---

### H-05  G (extractable storage) — `BackpackBlockEntity` — CLOSED

**Files reviewed:**
- `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/backpack/BackpackBlockEntity.java` (full, 258 lines).
- `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/backpack/BackpackBlock.java` (full, 355 lines).
- `decompiled/sophisticatedcore-1.21.1-1.2.9.21.168/net/p3pp3rf1y/sophisticatedcore/inventory/CachedFailedInsertInventoryHandler.java` (full, 130 lines).

**Code trace:**

1. `BackpackBlockEntity` **does not implement `class_1263` (`Container`) or `WorldlyContainer`**. It extends `class_2586` and implements `IControllableStorage` only (`BackpackBlockEntity.java:33`). Consequence: the vanilla hopper path `HopperBlockEntity.tryTakeInItemFromSlot(WorldlyContainer, ...)` / `Container.removeItem(int, int)` / `WorldlyContainer.canTakeItemThroughFace(...)` is **unreachable** against this BE. Shape G in its classical form (hopper-extraction without slot decrement, or `canTakeItemThroughFace` vs mid-swap state) has no target surface.
2. Extraction is exposed instead through Fabric Transfer API: `getExternalItemHandler(@Nullable class_2350 direction)` at `BackpackBlockEntity.java:132-145` returns a `CachedFailedInsertInventoryHandler` wrapping `this.getBackpackWrapper().getInventoryForInputOutput()`.
3. The wrapper is a **pass-through for extraction** — `CachedFailedInsertInventoryHandler.java:101-111`:
   ```java
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      return this.wrappedHandlerGetter.get().extractItem(slot, amount, simulate);
   }
   public long extract(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.wrappedHandlerGetter.get().extract(resource, maxAmount, ctx);
   }
   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.wrappedHandlerGetter.get().extractSlot(slot, resource, maxAmount, ctx);
   }
   ```
   The cache only short-circuits **failed inserts** (`failedInsertStacks` — `.java:19,45-61`), never extractions. No pre-extraction state is retained or replayed. The transaction-aware `extract*` methods are handed straight to the underlying inventory, so Fabric's transaction commit/rollback is the sole authority on slot mutation.
4. `isBlockConnectionDisallowed` at `BackpackBlockEntity.java:124-130` can null out the returned handler (by config, per-direction) but does not modify slot state; there is no "mid-transformation" window where a side allows extraction while the inventory is inconsistent.
5. `invalidateHandlers()` at `BackpackBlockEntity.java:117-122` nulls the cached `externalItemHandler` / fluid / energy references whenever the upgrade caches change. It does **not** clone the underlying inventory; the next `getExternalItemHandler(...)` call simply re-wraps the same live inventory, so a cached handler reference held by an adjacent hopper cannot observe a stale inventory after an upgrade recache.

**Verdict: NONE** (no dupe in the surface traced using shape G).

**Rationale**: the classical shape-G traps (`removeItem` returning without clearing, `canTakeItemThroughFace` wrong during a swap) require a `class_1263`/`WorldlyContainer` host. The Fabric-Transfer surface on this BE is a thin pass-through to the real inventory; all extraction goes through the inventory's own `extract*` methods inside a `TransactionContext`. Shape G is closed; the Fabric-Transfer surface remains subject to the general "inventory aliasing" concerns already closed under H-02/H-04/H-13.

---

### H-07  H (entity/dismount) — `EntityBackpackAdditionHandler.handleBackpackDrop` — CLOSED

**Files reviewed:**
- `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/common/EntityBackpackAdditionHandler.java` (523 lines).
- `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/common/CommonEventHandler.java` (305 lines).

**Code trace (mob-spawn side):**

- When a monster is given a backpack at spawn, `addBackpack(...)` at `EntityBackpackAdditionHandler.java:242-243` calls:
  ```java
  monster.method_5673(class_1304.field_6174, backpack);     // equip in CHEST slot
  monster.method_5946(class_1304.field_6174, 0.0F);         // equipment drop chance = 0
  ```
  and tags the entity with `"spawnedWithBackpack"` (`:191`). The chest-slot drop chance of **0.0F** is the key invariant: vanilla `LivingEntity#dropCustomDeathLoot` → `dropEquipment` rolls each slot against `armorDropChances`; a chance of `0.0F` guarantees the backpack will not be dropped by the vanilla equipment path even with Looting.

**Code trace (death/drop side) — `EntityBackpackAdditionHandler.java:341-355`:**
```java
static void handleBackpackDrop(class_1309 mob, class_1282 source, Collection<class_1542> drops) {
   if (mob.method_5752().contains("spawnedWithBackpack")) {
      class_1799 backpack = mob.method_6118(class_1304.field_6174);
      Config.Server.EntityBackpackAdditionsConfig additionsConfig = Config.SERVER.entityBackpackAdditions;
      if (shouldDropBackpack(source, additionsConfig, mob, backpack)) {
         putJukeboxItemsInContainerAndRemoveStorageUuid(source, mob, backpack);
         class_1542 backpackEntity = new class_1542(mob.method_37908(), mob.method_23317(), mob.method_23318(), mob.method_23321(), backpack);
         drops.add(backpackEntity);
         mob.method_5673(class_1304.field_6174, class_1799.field_8037);
         mob.method_5752().remove("spawnedWithBackpack");
      } else {
         removeContentsUuid(backpack);
      }
   }
}
```

1. `drops` is the Collection supplied by SCore's `LivingEntityEvents.DROPS` — the same capture list discussed under H-12.
2. **Single ItemEntity construction** (`:347`): one `class_1542` is created carrying `backpack` (the actual ItemStack reference from the chest slot).
3. The chest slot is cleared to empty (`:349`) and the tag is removed (`:350`) **after** the ItemEntity has been added to the drops list. Clearing the slot re-assigns the mob's armor slot; it does not mutate the `backpack` ItemStack object, so the new ItemEntity's `item` reference remains valid.
4. The `shouldDropBackpack` branch also calls `putJukeboxItemsInContainerAndRemoveStorageUuid(source, mob, backpack)` at `:346` (traced to `:357-420`): this drains jukebox-upgrade discs back into the backpack's inventory via a Fabric `Transaction` and then calls `removeContentsUuid(backpack)` if drops were disabled by config. No new ItemEntity for jukebox items is created — they are merged into the backpack that is about to drop.
5. **Contents-drop question**: the backpack's inventory is **not** dumped as separate ItemEntities. The contents live inside the single backpack ItemStack (via `backpackWrapper` NBT on the stack), which is what the single ItemEntity carries. `removeContentsUuid` only runs in the `else` branch (`:352`) when `shouldDropBackpack` returns false — which is also when no ItemEntity is created at all.
6. **Despawn-path**: `EntityTrackingEvents.STOP_TRACKING` / `onEntityLeaveWorld` in `CommonEventHandler.java:86` routes to `removeBackpackUuid` at `EntityBackpackAdditionHandler.java:450-455`, which only strips the contents UUID from the backpack still in the chest slot. The mob entity continues to hold `backpack`; when the chunk is unloaded, the entity is saved with its inventory. Reload produces the same entity with the same backpack — a single copy.

**Verdict: NONE** (solo-reproducible).

**Rationale**: the drop path creates exactly one `class_1542` carrying the mob's single backpack ItemStack reference, and the vanilla equipment-drop path is disabled by the `method_5946(CHEST, 0.0F)` set at spawn. No aliasing, no double-emission, no contents-as-separate-ItemEntities. The `shouldDropBackpack` RNG simply gates whether that one ItemEntity is emitted; it cannot emit more than one.

**Non-dupe observation** (reported for context, not a verdict): `shouldDropBackpack` at `:422-442` requires the kill source to be a `class_1657` player (`:425`). If the backpack-wearing mob dies from fall, drowning, lava, suffocation, or a non-player mob, `shouldDropBackpack` returns false, `removeContentsUuid` runs on the backpack still in the mob's chest slot, and **no drop occurs**. This is a content-loss shape, not a dupe, so it does not register as a finding — but it explains why environment kills of backpack-wearing mobs lose the loot.

---

### H-18  B (full save/load round-trip) — `BackpackBlockEntity` — NEW

**Files reviewed:** `BackpackBlockEntity.java` (full), `BackpackBlock.java:145-148,222-245`.

**Code trace:**

1. **Save** — `BackpackBlockEntity.method_11007(class_2487, class_7874)` at `BackpackBlockEntity.java:89-93`:
   ```java
   protected void method_11007(class_2487 tag, class_7874 registries) {
      super.method_11007(tag, registries);
      this.writeBackpack(tag, registries);
      this.saveControllerPos(tag);
   }
   private void writeBackpack(class_2487 ret, class_7874 registries) {
      class_1799 backpackCopy = this.backpackWrapper.getBackpack().method_7972();
      ret.method_10566("backpackData", backpackCopy.method_57358(registries));
   }
   ```
   The save writes a **copy** (`method_7972`) of the backpack ItemStack, then encodes it via `ItemStack.method_57358(registries)` (vanilla component-aware save). The copy prevents any later mutation of the wrapper from tainting the saved NBT.
2. **Load** — `BackpackBlockEntity.method_11014(...)` at `:61-77` calls `setBackpackFromNbt` (`:84-87`) which decodes via `class_1799.method_57359(registryAccess, tag.method_10562("backpackData"))` — vanilla component-aware load into a fresh ItemStack, then wraps it with `BackpackWrapper.fromStack(...)`.
3. **Update packet / block-update tag** — `method_16887(...)` at `:100-106` writes the same `backpackData` payload into the block-update tag sent to clients. Clients run `method_11014` on receipt; they do not call `setChanged`/`markDirty` or interact with persistent storage.
4. **Block removal** — `BackpackBlock.method_9536` (`onRemove`) at `BackpackBlock.java:234-240` calls `removeFromController` only; it does **not** drop items. `BackpackBlock.putInPlayersHandAndRemove` at `:222-232` is the sneak-right-click pickup surface: it obtains the backpack via `be.getBackpackWrapper().getBackpack()` (`:223-225`), gives the player a copy via `method_7972` (`:227`), then calls `level.method_8650(pos, false)` (`:229`). `removeBlock(pos, false)` replaces the block with air **without invoking loot drops**. The block entity's save has been superseded by the player-handed copy.

**Potential issue** (evaluated and dismissed):

Pickup via `putInPlayersHandAndRemove` gives the player `backpack.method_7972()` (a copy). The original backpack ItemStack lives on the BE's `backpackWrapper`, whose UUID pointer (stored on the ItemStack as a component) is the same on both copies. If the user could re-obtain the original BE after pickup (e.g. by breaking partly and cancelling mid-tick), two ItemStacks with the same content UUID would exist — but `level.method_8650(pos, false)` is a single synchronous operation and the BE is removed atomically with the block; no mid-tick observation surface was found for a solo player. Further, the world-tick de-dupe at `CommonEventHandler.java:130-167` (active by default per `tickDedupeLogicDisabled` default `false`) scans every player's backpacks every 40 ticks and strips the contents UUID from the second copy if two stacks share one. So even if a UUID-collision path were opened, the de-dupe would blank the second stack's contents within 2 seconds.

**Verdict: NONE**

**Rationale**: save writes a copy; load constructs a fresh ItemStack; pickup hands the player a copy and atomically removes the block without a loot-drop path; no inventory reference survives across the save/load boundary that would alias a ground or player stack. The well-known "break+pickup double-drop" red flag for block-backed item containers is not reachable here because `BackpackBlock` has no `getDrops` override and `method_8650(..., false)` does not trigger loot tables.

---

### H-19  I (chunk unload timing) — `BackpackBlockEntity` — NEW

**Files reviewed:** `BackpackBlockEntity.java:246-257`, `BackpackBlock.java:234-246,281-299,301-310`.

**Code trace:**

1. The BE tracks a `chunkBeingUnloaded` flag (`BackpackBlockEntity.java:38`). `sophisticatedCore_onChunkUnloaded()` at `:246-249` sets it to `true` before delegating to super. `method_11012` (`setRemoved`) at `:251-257`:
   ```java
   public void method_11012() {
      if (!this.chunkBeingUnloaded && this.field_11863 != null) {
         this.removeFromController();
      }
      super.method_11012();
   }
   ```
   The guard is intentional: during a chunk unload, the storage controller is also unloading, so the BE skips the deregister. During a normal block-break, `chunkBeingUnloaded` is false and the BE deregisters.
2. The BE ticker at `:194-201` (`serverTick`) only runs when the chunk is **loaded** (the ticker is registered via `class_5558` which vanilla gates by `LevelChunk#isTicking`). Once a chunk is flagged for unload, the ticker stops being invoked by the vanilla loop, so upgrade side effects (Magnet, Compacting, Feeding, etc.) cannot run post-unload.
3. `BackpackBlock.method_9536` (`onRemove`) at `:234-240` and `method_9576` (`playerWillDestroy` in 1.21) at `:242-246` both only call `removeFromController`. Neither spawns an `ItemEntity`. There is no unload-time drop path that could race with normal break drops.
4. The `class_1542`-insertion pickup surface `method_9548` at `:281-286` (`onEntityInside`) runs only while the chunk is loaded; `tryToPickup` at `:293-299` reads from the ItemEntity's live ItemStack and writes back via `method_6979`. No duplicate ItemStack is retained.

**Verdict: NONE**

**Rationale**: no side-effectful behaviour fires during chunk unload that could spawn an ItemEntity. The only unload-time work is skipping controller deregistration — a pure no-op for item state. The break/drop paths are disjoint from unload and synchronous.

---

### H-20  J (packet race / client-supplied indices) — SBP + SCore — NEW

**Files reviewed:**
- SBP: `network/BackpackOpenPayload.java`, `InventoryInteractionPayload.java`, `BlockToolSwapPayload.java`, `EntityToolSwapPayload.java`, `AnotherPlayerBackpackOpenPayload.java`, `BackpackContentsPayload.java`, `RequestBackpackInventoryContentsPayload.java`, `RequestPlayerSettingsPayload.java`, `SyncClientInfoPayload.java`, `UpgradeTogglePayload.java`, `BackpackClosePayload.java`, `BlockPickPayload.java`.
- SCore: `network/TransferItemsPayload.java`, `TransferFullSlotPayload.java`, `SyncSlotStackPayload.java`, `SyncContainerStacksPayload.java`, `SyncContainerClientDataPayload.java`, `SyncAdditionalSlotInfoPayload.java`, `SyncSlotChangeErrorPayload.java`, `SyncEmptySlotIconsPayload.java`, `SyncPlayerSettingsPayload.java`, `SyncDatapackSettingsTemplatePayload.java`, `SyncTemplateSettingsPayload.java`, `compat/rei/REIMoveItemsPayload.java`, `upgrades/jukebox/PlayDiscPayload.java`, `upgrades/jukebox/StopDiscPlaybackPayload.java`, `upgrades/tank/TankClickPayload.java`.

**Shape J concern**: a C2S packet that accepts client-controlled indices/stacks and uses them to move items between slots with insufficient server-side validation, producing aliasing that duplicates a stack.

**Per-payload solo analysis** (only dupe-relevant findings listed):

- `TransferItemsPayload` (`network/TransferItemsPayload.java:41-82`). Client-supplied payload is `(boolean transferToInventory, boolean filterByContents)` — no slot indices. Server uses the current `StorageContainerMenuBase` (validated by `player.field_7512 instanceof ...`) and routes through `InventoryHelper.transfer`/`mergeIntoPlayerInventory`, which calls `setStackInSlot` after shrink. **NONE.**
- `TransferFullSlotPayload` (`network/TransferFullSlotPayload.java:25-35`). Client sends `slotId`. Server calls `storageContainer.method_7601(player, slotId)` (vanilla `quickMoveStack`). Any invalid index throws or returns `EMPTY`. Vanilla `quickMoveStack` is the canonical shift-click surface — no double-write path. **NONE.**
- `SyncSlotStackPayload` (`network/SyncSlotStackPayload.java:17-42`). **Client-side only** (`@Environment(EnvType.CLIENT)` at `:35`). Not a server attack surface.
- `BackpackOpenPayload` (`network/BackpackOpenPayload.java:48-79`). Takes `(slotIndex, identifier, handlerName)`. When `handlerName` is non-empty (`:50`), constructs a `BackpackContext.Item(handlerName, identifier, adjustedSlotIndex, …)` directly from client strings without verifying the player's inventory holds a backpack at that `(handlerName, identifier, slot)`. A malicious client could request the server open a `BackpackContainer` with a context pointing at an identifier the player does not currently own. Subsequent reads/writes on that menu occur through `BackpackContext.Item.getBackpackWrapper(...)` which re-resolves the backpack each frame; if the resolver returns `EMPTY` or a missing-UUID backpack, writes go into a noop wrapper and items placed there are lost, not duplicated. No duplication path was found through this surface in the files read. **Marked POSSIBLE** only because the resolver path was not exhaustively traced across all handler names (`PlayerInventoryProvider` shim). Solo-reproducible? Requires a modified client; the default vanilla client never sends arbitrary `(handlerName, identifier)` pairs. A dedicated modified-client run would be needed to elevate this.
- `InventoryInteractionPayload` (`network/InventoryInteractionPayload.java:26-33`), `BlockToolSwapPayload` (`:27-47`), `EntityToolSwapPayload` (`:29-53`). All three accept a `pos` / `entityId` without a distance check. These are "reach hacks" (interact at a distance) but the interaction logic (`InventoryInteractionHelper.tryInventoryInteraction`, `upgrade.onBlockInteract`, `upgrade.onEntityInteract`) uses Fabric's transaction API for any item movement into the remote container. **No duplication path found** — items move one direction via normal transactional inventory APIs. Reach-hack cheats are out of scope for a dupe audit.
- `AnotherPlayerBackpackOpenPayload` (`network/AnotherPlayerBackpackOpenPayload.java:33-56`). Requires a second player (the target is resolved via `level.method_8469(anotherPlayerId)` and must be `class_1657`). **Not solo-reproducible** — deferred per user directive.
- `REIMoveItemsPayload` (`compat/rei/REIMoveItemsPayload.java:60-119`). Server-side handler reads client-supplied NBT for input-slot and inventory-slot lists (`:74-76`), then runs a custom `NewInputSlotCrafter.fillInputSlot` (`:77-104`). The `fillInputSlot` implementation copies (`method_7972` at `:81`), then decrements the source by 1 (`takeStack(1)` at `:84` or `setItemStack(EMPTY)` at `:86`), sets the copy to count 1 (`method_7939(1)` at `:89`), and writes into the destination either via `setItemStack(takenStack)` or `method_7933(1)`. For identical source/destination aliasing via crafted NBT, the arithmetic is (−1, +1) → no net change. For disjoint slots, the arithmetic is (−1 from source, +1 to destination) → no dupe. The only loss path is the `!slot.canPlace(takenStack)` early return at `:90-92`, which consumes from source without writing to destination — a **loss**, not a dupe. **NONE** as a dupe vector. Requires REI (out of scope; REI is not one of the four in-scope mods).
- `PlayDiscPayload`, `StopDiscPlaybackPayload`, `TankClickPayload`. Disc/tank interactions are one-direction state changes (play/stop/empty/fill) that route through the backpack wrapper's own disc and fluid handlers. No free-item path found.

**Verdict: NONE** solo-reproducible. One POSSIBLE noted (`BackpackOpenPayload` context resolver path) pending a dedicated modified-client trace; this surface was not elevated because no in-file path to duplication was found and the world-tick de-dupe (`CommonEventHandler.java:130-167`) strips duplicate content-UUIDs within 40 ticks.

---

### H-21  N (fire / despawn immunity) — `EverlastingBackpackItemEntity` — NEW

**Files reviewed:** `upgrades/everlasting/EverlastingBackpackItemEntity.java` (full, 79 lines), `backpack/BackpackItem.java:138-175`, `common/CommonEventHandler.java:95-106`.

**Code trace:**

1. **Entity invulnerability** — `EverlastingBackpackItemEntity.java:51-61`:
   ```java
   public boolean method_5753() { return true; }             // fireImmune → true
   public boolean method_5659(class_1927 explosion) { return true; }  // ignoreExplosion
   public boolean method_5679(class_1282 source) { return true; }     // isInvulnerableTo → all sources
   ```
   The entity cannot be destroyed by fire, lava, explosions, suffocation, or any damage source. The `method_5825()` override at `:63-64` is an empty no-op that disables the walk/step base-tick sound hook.
2. **Void protection** — `method_5799()` at `:47-49` pretends `isInWater` is true when `getY() < minBuildHeight + 1`, and the tick at `:22-45` flips `setNoGravity(true)` and zeroes motion once the entity has been in that state. This keeps the entity hovering at/near the void floor rather than being `Entity.onBelowWorld()`-discarded.
3. **Entity swap on load** — `CommonEventHandler.java:95-106` replaces a vanilla `class_1542` carrying a backpack-with-Everlasting with a fresh `EverlastingBackpackItemEntity`:
   ```java
   EntityEvents.ON_JOIN_WORLD.register((JoinWorld)(entity, world, loadedFromDisk) -> {
      if (entity.getClass().equals(class_1542.class) && ((class_1542)entity).method_6983().method_7909() instanceof BackpackItem backpack) {
         class_1297 newEntity = backpack.createEntity(world, entity, ((class_1542)entity).method_6983());
         if (newEntity != null) {
            entity.method_31472();     // discard original
            world.method_8649(newEntity);   // add fresh
            return false;
         }
      }
      return true;
   });
   ```
   The discard/add pair is synchronous on the same tick; the original's `RemovalReason.DISCARDED` state removes it from the chunk before the next save, so only the replacement is persisted. `BackpackItem.createEntity` at `BackpackItem.java:139-146` additionally invokes `UUIDDeduplicator.dedupeBackpackItemEntityInArea(itemEntity)` before deciding whether to upgrade to the Everlasting form.
4. **Age / despawn** — the override adds its own `this.age++` field (`:15,43`) but still calls `super.method_5773()` (`:44`), so vanilla's `class_1542#field_6032` (private age) continues to advance. Vanilla ItemEntity despawns at age ≥ 6000 ticks unless the stack has the `INFINITE_LIFETIME` / `age = -32768` sentinel. No code in the mod sets `field_6032` to `-32768` or extends lifespan.

**Potential issue** (evaluated and dismissed):

Shape N's classic red flag — *"custom invulnerable ItemEntity that never despawns, so it can be duplicated by swapping its `item` reference mid-tick and letting the server reload a persisted copy"* — is **not present**: the entity DOES despawn (vanilla age counter is not overridden). It is simply immune to damage/fire/explosions. Invulnerable-but-time-limited ItemEntities are not a dupe source on their own. The load-time swap (step 3) does not duplicate because the original is discarded atomically before the replacement is added.

**Verdict: NONE**

**Rationale**: invulnerability does not extend lifespan; the entity still ages out at vanilla 5 minutes of un-pickup. Load-time entity swap discards the original before adding the replacement. No duplication path found in the surface traced.

---

### Pass 2 closing notes

- **Updated rule-4 statement**: as of this pass, H-01 remains the only CONFIRMED dupe in the surfaces traced. H-05 and H-07 are now closed at NONE (solo). H-18/H-19/H-20/H-21 add additional NONE verdicts for shapes B, I, J, N respectively — but these verdicts are scoped to the files listed above, not to the full mods. A future pass should still execute shapes F, K, L, M, U, V, W, X, Y, Z.
- **Still deferred**: Phase 2 (external trackers), Phase 5 (JAR bytecode diff), H-08/H-09 (Lootr block-drop with NBT preservation), H-10 (loot-table modification chain), and shapes F/K/L/M/U/V/W/X/Y/Z. Two-player surfaces are still closed at their Pass 1 verdict.
- **Auditor**: Devin (session `863e86d89f9a493a872fe1a7a32246b0`).

---

## Appendix C — Pass 3 (H-08/H-09 Lootr NBT pickup loop, Shape L backpack tier upgrade)

**Scope this pass**: solo-reproducible only. User explicitly selected these two surfaces after Pass 2 returned no solo dupes. Every verdict below is surface-scoped; rule 4 still applies.

---

### H-22 — Lootr block break does NOT preserve per-player loot state (H-08/H-09)

- **Shape**: H-08/H-09 — NBT-preserving pickup loop (break block → receive item carrying `infoId` → replace → fresh `hasBeenOpened`, same loot-table contents).
- **Files**:
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/data/lootr/loot_table/blocks/lootr_chest.json:11-24`
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/data/lootr/loot_table/blocks/lootr_barrel.json:11-24`
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/data/lootr/loot_table/blocks/lootr_inventory.json:11-24`
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/data/lootr/loot_table/blocks/lootr_shulker.json:11-24`
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/data/lootr/loot_table/blocks/lootr_trapped_chest.json:11-24`
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/noobanidus/mods/lootr/common/block/LootrChestBlock.java:113-118` (`method_9556` / `playerDestroy`)
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/noobanidus/mods/lootr/common/block/LootrInventoryBlock.java:131-136` (same pattern)
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/noobanidus/mods/lootr/common/impl/DefaultLootrAPIImpl.java:362-373` (`playerDestroyed`)
  - `decompiled/lootr-fabric-1.21.1-1.11.37.118/noobanidus/mods/lootr/common/data/DataStorage.java:196-199` / `LootrSavedData.java:150-165` (`getInventory` / `createInventory`)

**Trace:**

1. Every in-scope Lootr block's loot table emits a **vanilla block item** (`name`: `minecraft:chest` / `minecraft:barrel` / `minecraft:trapped_chest` / `minecraft:shulker_box`; `lootr_inventory` emits `minecraft:chest`). The `minecraft:copy_components` function's `include` list is limited to `minecraft:custom_name`. **No `infoId`, no storage reference, no `hasBeenOpened` data is copied onto the dropped item.**
2. `LootrChestBlock.method_9556` (lines `:113-118`) runs `super.method_9556(...)` first — this is the path that consults the loot table and drops the vanilla chest at the break position. It then calls `LootrAPI.playerDestroyed(...)`.
3. `DefaultLootrAPIImpl.playerDestroyed` (lines `:362-373`):
   - Guarded by `shouldDropPlayerLoot()` (config; `ConfigManager.get().breaking.should_drop_player_loot`) and `canDropContentsWhenBroken()` (BE-level flag).
   - Calls `getInventory(inventory, serverPlayer, inventory.getDefaultFiller(), null)` → `DataStorage.getInventory` → `LootrSavedData.getOrCreateInventory`. If the breaker has already opened and taken items, this returns the *existing* live inventory; if they have not opened, it creates and fills a fresh per-player inventory on the spot.
   - Calls `class_1264.method_5451(level, pos, inventoryx)` which spills **all** remaining items in that inventory into the world as `class_1542`.
4. Net item accounting for a single player in a single break-open cycle: items already drawn via GUI open + items dumped by `method_5451` = items originally rolled by the loot table. One loot roll in → one loot roll out. **No duplication.**
5. Re-placing the dropped vanilla block creates a new Lootr BE with a **new** `infoId` (constructed fresh in `method_10123`). The old `LootrSavedData` keyed by the prior `infoId` is orphaned (not pointed at by any BE) but has no path to become accessible again without a second break + same coordinate coincidence that is not `infoId`-linked.

**Verdict: NONE (solo)**

**Rationale**: the shipped Lootr loot tables do not preserve any per-container identity data on the dropped item, so the classic "break-and-replace for a fresh roll of the same container" loop is impossible under the shipped data. `playerDestroyed` dumps the player's remaining per-player inventory, but those items are the un-taken remainder of the single roll that was already generated for them — not a second roll. No solo duplication path.

**Not verified**: a third-party mod (wrench/carry-on/framed blocks/etc.) that overrides the pickup path and preserves BlockEntity NBT *as an item component* could re-open this surface. Out of scope for the four-mod audit.

---

### H-23 — Backpack tier upgrade / smithing upgrade does not produce a duplicate (Shape L)

- **Shape**: L — tier-upgrade / contents-transfer recipe leaves both the source and the upgraded result with contents.
- **Files**:
  - `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/crafting/BackpackUpgradeRecipe.java:32-39` (shaped crafting-table upgrade)
  - `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/crafting/SmithingBackpackUpgradeRecipe.java:29-38` (smithing-table netherite upgrade)
  - `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/backpack/BackpackItem.java:78-85` (`class_1793().method_7889(1)` — max stack size 1)
  - `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/backpack/wrapper/BackpackWrapper.java:91-93` (`fromStack` resolves wrapper by `STORAGE_UUID` component)
  - `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/backpack/wrapper/BackpackWrapper.java:284-298` (`getContentsUuid` / `getOrCreateContentsUuid` — UUID is stack-component, created lazily)
  - `decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/backpack/wrapper/BackpackWrapper.java:448-457` (`setContentsUuid` / `removeContentsUuid`)

**Trace:**

1. **Crafting-table tier upgrade** — `BackpackUpgradeRecipe.method_17727`:
   - Line `:33` calls `super.method_17727(inv, registries)` → gets the recipe's declared result `class_1799` (a fresh stack of the target-tier `BackpackItem`).
   - Line `:34` `this.getBackpack(inv).map(class_1799::method_57353).ifPresent(upgradedBackpack::method_57365);` — copies the source backpack's **`ComponentPatch`** (via `getComponentsPatch` → `applyComponents`) onto the result, including `ModCoreDataComponents.STORAGE_UUID`. Result now carries the **same UUID** as the source.
   - Lines `:35-37` recreate a `BackpackWrapper` from the result and call `wrapper.setSlotNumbers(...)` to rewrite slot counts for the new tier. Because the UUID is shared, this wrapper points at the same world-global `BackpackStorage` entry as the source wrapper. The slot-count change mutates the shared storage.
2. **Smithing-table (netherite) upgrade** — `SmithingBackpackUpgradeRecipe.method_60000` (`:29-38`): same pattern (`method_7972` the result, copy components, wrap, setSlotNumbers).
3. **Input consumption**: `BackpackItem` sets `class_1793().method_7889(1)` in its constructor (`BackpackItem.java:81`) so the source stack can only be size 1. Grepping the entire SBP decompile for `method_7857` / `CraftingRemainder` / `hasCraftingRemaining` / `getRecipeRemainder` returns **zero matches** → no crafting-remainder override on `BackpackItem`. Vanilla `ResultSlot.method_7667` therefore decrements every input slot by 1 with no replacement item. The source backpack is fully consumed at the moment the result is taken.
4. **Both-exist scenario (defensive check)**: Even if a hypothetical menu-state glitch let the source remain alongside the result, both stacks would carry the **same `STORAGE_UUID`** component and `BackpackWrapper.fromStack` (`:91-93`) resolves both to the **same** `BackpackStorage` entry. Opening either one would see and mutate the same contents — zero net duplication of items.
5. The recipe does not touch `EMPTY_BACKPACK_PROVIDER` / `removeContentsUuid` on the source between copy and consume, so there is no window where the source and result hold disjoint storages with the same UUID.

**Verdict: NONE (solo)**

**Rationale**: the upgrade recipe is structurally safe against duplication under the shipped code path. UUID-sharing means a single `BackpackStorage` entry is referenced from any number of stacks bearing the same component, so item-count across stacks is constant regardless of how many stacks exist. No crafting remainder override exists, so input consumption is unconditional. Smithing follows the same pattern.

**Not verified**: JEI / REI / recipe-book *transfer* shortcuts that populate and immediately take the result slot were not traced. They would still be routed through the same `ResultSlot.onTake` with the same `ComponentPatch` copy, so the same reasoning applies, but the transfer path was not directly read in this pass.

---

### Pass 3 closing notes

- **H-22 (Lootr NBT-pickup loop) — NONE (solo).** This closes the largest still-open family of solo shapes from Pass 1/2. Under the shipped loot tables, breaking a Lootr block cannot carry its per-player state to a newly placed block.
- **H-23 (Shape L backpack tier upgrade) — NONE (solo).** The upgrade recipe preserves the `STORAGE_UUID` component, not the items themselves; result and source share storage transiently, source is consumed, no double-roll.
- **Running solo-dupe total across Pass 1+2+3**: **zero confirmed, zero probable.** H-01 remains the only confirmed dupe in the surfaces traced, and it is explicitly two-player.
- **Rule 4 restated**: this audit has now traced shapes A, B, D, G, H, I, J, L, N, plus H-08/H-09 and H-10-adjacent surfaces. Shapes **F, K, M, U, V, W, X, Y, Z** and Phase 2 (external trackers) / Phase 5 (JAR↔source bytecode diff) remain un-executed. A solo dupe could still hide there — this audit has NOT proven the four-mod stack dupe-free, only that the surfaces enumerated above do not contain one.

---

## Pass 4 — H-24 / H-25 (YIAS solo-death surfaces)

**Date**: 2026-04-18
**Session**: `863e86d89f9a493a872fe1a7a32246b0`
**Constraint**: solo-reproducible only (same as Pass 2/3).

---

### H-24 — YIAS solo-totem-revive (Shape V: death-cancel interaction)

**Hypothesis**: If YIAS's `ALLOW_DEATH` listener captures the inventory snapshot (line 37) *before* checking for a Totem of Undying, and a totem then cancels the death, the snapshot persists. A subsequent real death would restore the stale snapshot AND drop current inventory = solo dupe.

**Trace**:

1. YIAS registers on `ServerLivingEntityEvents.ALLOW_DEATH` (`ModFabric.java:22-28`). The handler always returns `true` (line 27), so it never blocks death — it only runs side effects.
2. `DeathEvent.onPlayerDeath` (`DeathEvent.java:33`): captures `itemStacks = Util.getInventoryItems(player)` at line 37.
3. **Totem checks** at lines 92-101:
   - Line 92-97: if `inventoryTotemModIsLoaded`, scans `itemStacks` for totem item (`class_1802.field_8288`). If found → `return` (early exit, no task enqueue).
   - Line 100-101: checks mainhand (`method_6047`) and offhand (`method_6079`) for totem. If found → `return`.
4. These checks happen at lines 92-101, **before** the task enqueue at line 217. If any totem check passes, the function returns immediately — no equipment clear, no delayed task, no chest placement.
5. **Vanilla totem behavior**: the Totem of Undying fires in `LivingEntity.checkTotemDeathProtection()` which is called inside `LivingEntity.hurt()`, *before* `die()` is called. If a totem triggers, `die()` is never called, so `ALLOW_DEATH` never fires, so YIAS's handler never runs at all.
6. Even in the edge case where YIAS runs (e.g., `inventoryTotemModIsLoaded` with an inventory-totem mod), the early returns at lines 94/100 prevent any side effects.

**Kill-shot**: `DeathEvent.java:94` and `DeathEvent.java:100` — early `return` before task enqueue (line 217) whenever a totem is detected. No snapshot persists, no stale state, no dupe.

**Verdict: NONE (solo)**

---

### H-25 — Placed BackpackBlockEntity with Magnet Upgrade vs own death drops (Shape A: reference-leak via block-entity tick)

**Hypothesis**: When a player dies near a placed backpack block that has a Magnet Upgrade installed, the block entity continues ticking while the player is on the respawn screen. If the magnet's tick fires between YIAS's death-event snapshot (tick T) and YIAS's delayed chest-task (tick T+1), it can absorb death-dropped ItemEntities via `InventoryHandler.superInsertItem` into a **partially-filled matching slot** — growing the existing slot's ItemStack (a separate object) while leaving the original ItemStack reference (held by YIAS's `finalItemStacks`) unmodified. YIAS then copies the full-count original to a death chest and zeros it, but the backpack's grown slot is unaffected. Net: items exist in both the backpack and the death chest = solo dupe.

**Trace (end-to-end)**:

**Step 1 — BackpackBlockEntity ticks every server tick.**
`BackpackBlock.method_31645` (`BackpackBlock.java:302-310`) returns a `BlockEntityTicker` that invokes `BackpackBlockEntity.serverTick` on every server tick (server-side only, gated by `!level.field_9236` at line 303).

**Step 2 — serverTick invokes ITickableUpgrade#tick on all upgrades.**
`BackpackBlockEntity.serverTick` (`BackpackBlockEntity.java:194-200`):
```java
public static void serverTick(Level level, BlockPos blockPos, BackpackBlockEntity be) {
    if (!level.isClientSide) {
        be.backpackWrapper.getUpgradeHandler()
            .getWrappersThatImplement(ITickableUpgrade.class)
            .forEach(upgrade -> upgrade.tick(null, level, blockPos));  // entity=null for BE
    }
}
```
`MagnetUpgradeWrapper` implements `ITickableUpgrade` (line 41). When installed in a placed backpack, its `tick(null, level, pos)` is called every server tick.

**Step 3 — Magnet tick scans for ItemEntities WITHOUT checking pickupDelay.**
`MagnetUpgradeWrapper.tick` (`MagnetUpgradeWrapper.java:79-88`): if `!isInCooldown(level)`, calls `pickupItems(null, level, pos)`.
`pickupItems` (`MagnetUpgradeWrapper.java:168-189`):
```java
List<ItemEntity> itemEntities = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos).inflate(radius), e -> true);
for (ItemEntity itemEntity : itemEntities) {
    if (itemEntity.isAlive() && filterLogic.matchesFilter(itemEntity.getItem()) && !canNotPickup(itemEntity, null)) {
        // ^^^ NO field_7202 (pickupDelay) check — contrast with CommonEventHandler:251
        tryToInsertItem(null, itemEntity);
    }
}
```
Vanilla death drops set `pickupDelay = 40` via `setDefaultPickUpDelay()`. The magnet **ignores this** — it has no `field_7202 <= 0` gate. Compare `CommonEventHandler.onItemPickup` (`CommonEventHandler.java:251`) which explicitly checks `itemEntity.field_7202 <= 0`.

`canNotPickup` (`MagnetUpgradeWrapper.java:228-237`): when `entity=null` (block-entity context), returns `data.has("PreventRemoteMovement") && !data.has("AllowMachineRemoteMovement")`. Vanilla death drops carry neither tag → returns `false` → magnet CAN absorb.

**Step 4 — tryToInsertItem inserts into backpack, replaces ItemEntity's item.**
`tryToInsertItem` (`MagnetUpgradeWrapper.java:239-256`):
```java
ItemStack stack = itemEntity.getItem();           // same ref YIAS holds
ItemStack remaining = inventory.insertItem(stack, false);  // real insert
itemEntity.setItem(remaining);                    // swap ItemEntity's item
```

**Step 5 — Partially-filled slot insert grows EXISTING stack, leaves source untouched.**
`InventoryHandler.superInsertItem` (`InventoryHandler.java:341-378`), when inserting into a partially-filled matching slot (line 349-354 passes, existing is non-empty):
```java
// line 365-367:
existing.grow(reachedLimit ? limit : stack.getCount());  // grows EXISTING ItemStack
result = existing;  // result is the existing slot's object
```
The source `stack` (= the ItemStack ref YIAS holds) is **NOT modified**. The existing slot's ItemStack (a separate Java object) is grown. `remaining` returned = `ItemStack.EMPTY` (if all fits, line 373).

**Contrast — empty slot insert stores SOURCE reference:**
```java
// line 363-364:
if (existing.isEmpty()) {
    result = reachedLimit ? stack.copyWithCount(limit) : stack;  // stores SOURCE ref
}
```
In the empty-slot case, the source ref is stored in the backpack slot. YIAS's later `setCount(0)` propagates to the backpack slot (same object). **No dupe in empty-slot case.**

**Step 6 — YIAS delayed task copies and zeros the original ref.**
`DeathEvent.java:229-233` (runs at tick T+1 via `TaskFunctions.enqueueCollectiveTask(..., 1)`):
```java
for (ItemStack itemStackx : finalItemStacks) {
    if (!itemStackx.isEmpty()) {
        chestEntity.setItem(i, itemStackx.copy());  // copy to chest (new object)
        itemStackx.setCount(0);                      // zero source
    }
}
```
In the partially-filled case: source (YIAS's ref) still has full count N (magnet didn't modify it). Copied to chest = N items. Zeroed = count 0. But the backpack's EXISTING slot was grown by N using a **different Java object** — unaffected by the zero-out.

**Step 7 — Timing analysis.**
- Minecraft server tick order in 1.21.1: chunk ticks (block entities) → entity ticks (players, items) → `END_SERVER_TICK` events.
- Tick T: BE ticks (magnet fires if not in cooldown — no items yet). Entity phase: player takes lethal damage → YIAS `ALLOW_DEATH` captures refs, enqueues task with delay=1 → `die()` → `dropAllDeathLoot()` → ItemEntities spawn with same refs.
- Tick T+1: BE ticks (magnet fires IF cooldown expired). Magnet scans, finds death-dropped ItemEntities. Absorbs via partial-match. → Later in tick: `END_SERVER_TICK` → Collective fires YIAS task → copies and zeros refs.

**Cooldown alignment**: `UpgradeWrapperBase.setCooldown` (`UpgradeWrapperBase.java:32-34`): `this.cooldown = level.getGameTime() + time`. `isInCooldown` (`:40-42`): `cooldown > gameTime` (strictly greater). Magnet cooldown = 10 ticks (line 45). The magnet fires every 10 ticks at absolute game times T_m, T_m+10, T_m+20, ... For the dupe to trigger, the magnet's cycle must land on tick T+1 (the first BE tick after death). This occurs when `T+1 ≡ T_m (mod 10)`, which has a **1-in-10 probability** per death attempt assuming random death timing relative to magnet cycle.

**Step 8 — Net result when timing aligns (partially-filled slot case).**
- Backpack slot: existing ItemStack grew by N (e.g., 1 diamond → 64 diamonds).
- YIAS death chest: copy of N items (e.g., 63 diamonds).
- Original ref: zeroed (orphaned, not in any visible container).
- ItemEntity: set to EMPTY by magnet.
- **Total items**: (existing + N) in backpack + N in chest. Player started with existing + N total. **Net gain: +N items.**

**Verdict: POSSIBLE (solo)**

**Conditions for reproduction:**
1. Placed `BackpackBlockEntity` within magnet radius of planned death location.
2. Backpack has a **Magnet Upgrade** installed and enabled (default: enabled).
3. Backpack contains a **partially-filled** slot matching the item(s) to dupe (e.g., 1 diamond in a slot with max stack 64).
4. Player has more of the same item in main inventory.
5. YIAS mod is active with default config (`createChest` enabled, `createArmorStand` not required).
6. Magnet cooldown expires on tick T+1 (probabilistic — ~10% per attempt).

**Reproduction steps (solo, survival):**
1. Craft a backpack. Install a Magnet Upgrade (any tier).
2. Place the backpack block 1-3 blocks from where you plan to die.
3. Open the placed backpack. Put **1 of the target item** (e.g., 1 diamond) into a slot. Close.
4. Put **63 of the same item** (e.g., 63 diamonds) in your hotbar/inventory.
5. Kill yourself near the backpack (lava, falling, TNT, etc.).
6. Respawn. Check the YIAS death chest AND the placed backpack.
   - **If dupe triggered**: backpack has 64 diamonds (1 original + 63 absorbed), death chest has 63 diamonds (YIAS copy). Total: 127 from original 64. **+63 duplicated.**
   - **If dupe did NOT trigger** (magnet was in cooldown): death chest has your items as normal, backpack has only the 1 diamond. Retrieve items, re-equip, try again.
7. Repeat. With 10-tick magnet cooldown cycle, expect ~1/10 success rate per death. Dying 10 times gives ~65% cumulative probability.

**Why POSSIBLE and not CONFIRMED:**
- Collective library (`TaskFunctions.enqueueCollectiveTask`) source is not in the decompiled repo. The timing analysis assumes Collective processes delayed tasks at `END_SERVER_TICK` (standard Fabric pattern), which would place the task AFTER BE ticks. If Collective instead fires tasks at `START_SERVER_TICK` (before BE ticks), the magnet would miss the window and the dupe would not work.
- No in-game verification was performed.
- The 1/10 per-attempt probability is based on the 10-tick cooldown cycle; actual timing may vary depending on server TPS fluctuations.

**Key file:line citations:**
| Evidence | File:Line |
| --- | --- |
| BE ticker registration | `BackpackBlock.java:302-310` |
| serverTick calls ITickableUpgrade#tick | `BackpackBlockEntity.java:194-200` |
| MagnetUpgradeWrapper implements ITickableUpgrade | `MagnetUpgradeWrapper.java:41` |
| Magnet tick → pickupItems (no pickupDelay check) | `MagnetUpgradeWrapper.java:168-189`, esp. line 177 |
| tryToInsertItem → insertItem + setItem | `MagnetUpgradeWrapper.java:239-256` |
| superInsertItem partial-match: grows existing, source untouched | `InventoryHandler.java:365-367` |
| superInsertItem empty-slot: stores source ref (no dupe) | `InventoryHandler.java:363-364` |
| Cooldown = 10 ticks | `MagnetUpgradeWrapper.java:45` |
| setCooldown / isInCooldown | `UpgradeWrapperBase.java:32-42` |
| canNotPickup allows vanilla drops in BE context | `MagnetUpgradeWrapper.java:228-237` |
| YIAS captures refs (shallow copy) | `DeathEvent.java:37`, `Util.java:73-77` |
| YIAS delayed task copies + zeros | `DeathEvent.java:229-233` |
| YIAS registers ALLOW_DEATH (fires before die/drops) | `ModFabric.java:22-28` |
| CommonEventHandler DOES check pickupDelay (contrast) | `CommonEventHandler.java:251` |

---

### Pass 4 closing notes

- **H-24 (YIAS solo-totem-revive) — NONE (solo).** Totem checks at `DeathEvent.java:94/100` return early before any side effects. Vanilla totem fires in `hurt()` before `die()`, so YIAS's `ALLOW_DEATH` handler typically doesn't even run.
- **H-25 (Placed backpack Magnet Upgrade vs death drops) — POSSIBLE (solo).** First solo dupe surface identified in 4 passes of audit. The mechanism is identical to H-01 (ItemStack reference leak via `insertItem` into partially-filled slot + `setItem` on ItemEntity), but triggered by a block-entity magnet rather than a second player's pickup upgrade. Solo because the placed backpack block ticks autonomously. Probabilistic (1/10 per death attempt) due to magnet cooldown alignment. Pending in-game verification and Collective library timing confirmation.
- **Running solo-dupe total across Pass 1+2+3+4**: **zero confirmed, one POSSIBLE (H-25).** H-01 remains the only CONFIRMED dupe (two-player). H-25 is the strongest solo candidate found.
- **Rule 4 restated**: this audit has now traced shapes A, B, D, G, H, I, J, L, N, V, plus H-08/H-09, H-10-adjacent, and H-25 surfaces. Shapes **F, K, M, U, W, X, Y, Z** and Phase 2 (external trackers) / Phase 5 (JAR↔source bytecode diff) remain un-executed.
- **Auditor**: Devin (session `863e86d89f9a493a872fe1a7a32246b0`).

---

## Appendix D — Pass 4 (H-24 YIAS solo totem-revive)

**Scope this pass**: the single solo surface the user's prior auditor flagged as "highest-probability solo surface I've seen that neither of us has touched." Hypothesis: if YIAS's death handler snapshots drops BEFORE the totem check, and the totem cancels the death, a stale snapshot could survive and be re-applied on a subsequent real death — yielding a solo dupe. Every verdict below is surface-scoped; rule 4 still applies.

---

### H-24 — YIAS death handler does NOT see a totem-saved death, does NOT persist snapshots across deaths, and cannot dupe solo via a totem-revive loop

- **Shape**: D (kill / death timing) + reference-aliasing across two tick boundaries with a cancellable event in the middle.
- **Files**:
  - `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe/ModFabric.java:12-29` (Fabric registration of `ServerLivingEntityEvents.ALLOW_DEATH`)
  - `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe/neoforge/events/NeoForgeDeathEvent.java:10-20` (NeoForge `@SubscribeEvent` on `LivingDeathEvent`)
  - `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe/forge/events/ForgeDeathEvent.java:10-20` (Forge `@SubscribeEvent` on `LivingDeathEvent`)
  - `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe_common_fabric/events/DeathEvent.java:33-277` (shared death handler logic — Fabric variant is the shipped path per `fabric.mod.json:19`)
  - `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe_common_neoforge/events/DeathEvent.java:32-275` (NeoForge variant — structurally identical, verified via `diff`)
  - `decompiled/youritemsaresafe-1.21.1-4.7/com/natamus/youritemsaresafe_common_fabric/util/Util.java:73-77` (`getInventoryItems` — constructs a per-call `ArrayList`)

**Trace:**

1. **Entry-point never fires on a totem-saved death.** In all three loader variants the handler is wired to an event that is a strict downstream of the totem check:
   - Fabric — `ModFabric.java:22-28` registers `ServerLivingEntityEvents.ALLOW_DEATH`. The Fabric API mixin that fires this event is injected in `LivingEntity.damage(...)` at the call-site of `die(...)` — i.e. AFTER `checkTotemDeathProtection` / `tryUseTotem` has already been consulted and returned `false`. A totem-successful save exits `damage(...)` before the `die(...)` call-site is reached, so `ALLOW_DEATH` (and YIAS's handler) never runs. Matches the Pass 1 trace at `AUDIT_REPORT.md` H-16: *"YIAS runs at `ServerLivingEntityEvents.ALLOW_DEATH` (pre-`die()`)"* — *pre-die* means after the totem gate, before the death event chain.
   - NeoForge — `NeoForgeDeathEvent.java:11-18` uses plain `@SubscribeEvent` (default `receiveCanceled = false`) on `LivingDeathEvent`. Vanilla `LivingEntity.checkTotemDeathProtection` nullifies the damage and returns before `die(source)` is invoked, so `LivingDeathEvent` never fires on a totem-save. Identical semantics for the Forge build at `ForgeDeathEvent.java:11-18`.
   - Therefore the "snapshot taken before totem cancels" premise of H-24 is structurally impossible: **no handler → no snapshot**.

2. **Defensive in-handler totem check returns early before any mutation.** Even assuming a hypothetical path where the event did fire while the player still holds a totem (e.g. a third-party mod bypassing vanilla's totem gate, or an inventory-totem mod that keeps the totem in main inventory where vanilla's gate can't see it), `DeathEvent.java:89-100` re-scans for totems:
   ```
   if (Constants.inventoryTotemModIsLoaded) {
     for (ItemStack inventoryStack : itemStacks) {
       if (inventoryStack.getItem().equals(Items.TOTEM_OF_UNDYING)) {
         return;
       }
     }
   }
   if (player.getMainHandItem().getItem().equals(Items.TOTEM_OF_UNDYING)
       || player.getOffhandItem().getItem().equals(Items.TOTEM_OF_UNDYING)) {
     return;
   }
   ```
   This returns BEFORE `player.setItemSlot(..., ItemStack.EMPTY)` at `:179` / `:191` / `:207` and BEFORE the `TaskFunctions.enqueueCollectiveTask` delayed `setCount(0)` loop at `:215-271`. **No player inventory mutation occurs.** No chest is placed. No snapshot is retained.

3. **The "snapshot" has no persistent storage.** The inventory list used by the delayed task is built inline at `:37` (`List<class_1799> itemStacks = Util.getInventoryItems(player)` — which in turn returns `new ArrayList<>(player.getInventory().items)` at `Util.java:74`) and captured into the lambda via a stack-local `final`: `DeathEvent.java:213-214`:
   ```
   BlockPos finalDeathPos = deathPos;
   List<ItemStack> finalItemStacks = itemStacks;
   ```
   Both variables are closed over exactly one lambda (`:217-269`) that is submitted to `TaskFunctions.enqueueCollectiveTask(level.getServer(), ..., 1)` and runs exactly once on tick T+1. After the lambda executes (or is GC'd if the server shuts down mid-tick), there is no reference path back to the list.
   - A repo-wide grep for `static\s+(final\s+)?(List|Map|HashMap|ArrayList|Set)\s*<` across the entire YIAS source tree (all three loader trees) returns exactly these six matches:
     - `Constants.slotTypes` (Fabric / Forge / NeoForge) — a static `List<EquipmentSlot>` of the four armor slots; not an inventory snapshot.
     - `ConfigHandler.configMetaData` (Fabric / Forge / NeoForge) — a static `HashMap<String, List<String>>` of config metadata strings; not an inventory snapshot.
   - **No static field anywhere in YIAS stores a `List<ItemStack>`, a `Map<UUID, List<ItemStack>>`, or any other per-player drop cache.** The premise of H-24 — that a stale snapshot could persist across deaths — has no memory to live in.

4. **The delayed task is self-contained and idempotent-on-the-empty-list.** Inside the lambda at `:227-249`:
   ```
   for (ItemStack itemStackx : finalItemStacks) {
     if (!itemStackx.isEmpty()) {
       chestEntity.setItem(i, itemStackx.copy());
       itemStackx.setCount(0);
       ...
     }
   }
   ```
   On first execution, every non-empty stack is copied into the chest and its source is zeroed. If the same lambda were somehow re-invoked (it is not — `TaskFunctions.enqueueCollectiveTask` queues once), the iterator would see all stacks at `count == 0`, `itemStackx.isEmpty()` would return `true`, and the body would be skipped. **There is no re-apply mechanism and no re-apply surface.**

5. **"Death cancelled after YIAS" (an alternate framing of H-24) is also not a dupe.** If a lower-priority `LivingDeathEvent`/`ALLOW_DEATH` handler (a graves mod, a life-saver mod) cancels the death after YIAS has already run, the outcome is:
   - Synchronous tick T: YIAS clears armor slots into an armor stand (`:187-211`). Other mod runs later, cancels death. Player survives with armor slots empty.
   - Tick T+1: YIAS's queued task runs; creates chest at death pos; for each snapshotted stack: `chestEntity.setItem(i, stack.copy()); stack.setCount(0);`. Player's main inventory is now emptied into the chest.
   - Player is alive. Chest + armor stand are at the "death" position. Walking to them retrieves the items. **Sum of items across (player + chest + armorstand) is invariant — no new ItemStack was constructed anywhere except as `stack.copy()` paired one-to-one with a `setCount(0)` on the source.** This is item *transfer*, not *duplication*.
   - For this to dupe, the `setCount(0)` on the source would have to be undone *after* the chest copy lands. There is no code path that reinstates the source count. No exception in vanilla `Container.setItem`/`ItemStack.copy` can leave the loop mid-iteration with the copy succeeded and the `setCount(0)` skipped — both lines are straight-line bytecode with no intervening branch.

6. **Void-death + totem is not a solo dupe either.** The totem check at `:89-100` is only reached in the non-void branch (`else` at line 89 follows the `if (isVoidDeath)` at `:65-88`). A void death bypasses the in-handler totem scan — but vanilla totems also do not save from void damage, and the net result is still transfer-not-dupe (items go from player-inventory to chest placed above void via `:66-88`).

7. **Two solo "totem-revive" scenarios drawn out in full:**
   - **Scenario A — player holds totem in hand, dies to fire damage.** Vanilla `LivingEntity.hurt` calls `checkTotemDeathProtection(source)`, totem activates, health is set to 1, damage is nullified, `die(...)` is never invoked. `ServerLivingEntityEvents.ALLOW_DEATH` / `LivingDeathEvent` never fires. YIAS's handler is not called. No snapshot is ever created. Player walks away alive with one fewer totem. **No dupe.**
   - **Scenario B — player has totem in main inventory (not hand), `inventoryTotemModIsLoaded` is true.** Vanilla gate misses the totem (only checks hands). Third-party inventory-totem mod is the handler that consumes the totem and cancels death. Regardless of which mod runs first in the event bus:
     - If inventory-totem mod runs first and cancels death: NeoForge/Forge YIAS (default `receiveCanceled = false`) does not run — no snapshot, no dupe. Fabric YIAS is array-backed and still runs, but hits the `inventoryTotemModIsLoaded` branch at `:90-95`, finds the (now-just-consumed) totem or doesn't, and either way either returns early or runs the transfer path; neither produces a duplicate (transfer, not dupe, as in §5).
     - If YIAS runs first: the `inventoryTotemModIsLoaded` branch at `:90-95` finds the totem still present in the snapshotted list and returns. No mutation. The inventory-totem mod then consumes the totem and cancels death. **No dupe.**

**Verdict: NONE (solo)**

**Rationale**: the YIAS code path cannot be triggered by a totem-saved death in any of the three loader variants, the handler's own defensive totem scan aborts before mutating state, and the only "snapshot" of drops is a stack-local variable that lives exactly one tick inside one lambda with no field/collection carrying it across deaths. The proposed H-24 mechanism has no memory to live in and no re-apply path. "Death cancelled after YIAS runs" resolves to transfer-not-dupe by invariant of `copy()`-paired-with-`setCount(0)`.

**Repro steps**: N/A (NONE verdict).

**Not verified**:

- The Fabric-API `ServerLivingEntityEvents.ALLOW_DEATH` mixin (in `fabric-entity-events-v1`) was not re-decompiled this pass — I am relying on Pass 1's H-16 characterisation of it as firing pre-`die()`/post-totem, which matches public documentation but has not been byte-verified for the `1.21.1` Fabric API shipping with this modpack. If that event actually fires *before* `tryUseTotem`, the in-handler scan at `:89-100` is still the kill-shot on non-void deaths; void-death + held-totem becomes the only remaining surface and still resolves to transfer-not-dupe by §5.
- **YIAS × third-party "revive / graves / corail-tombstone" mod interactions** are out of scope for the four-mod audit. A graves mod that (a) registers higher priority than YIAS, (b) copies the dying player's inventory to its own grave entity BEFORE YIAS clears it, and (c) does not cancel `LivingDeathEvent` could coexist with YIAS to produce a two-site copy of the same inventory — but that is by definition a cross-mod interaction involving a mod outside this audit, not a YIAS-alone solo dupe.
- **Crash / save race during tick T+1** (server crash after `chestEntity.setItem(i, stack.copy())` is executed but before `stack.setCount(0)` commits and before the player's inventory NBT is saved to disk) is a 1-bytecode-instruction window with no concurrency inside it — the two statements execute on the same server thread with no intervening event dispatch. Not plausibly reachable absent JVM-level failure, and any such failure would also interrupt the world-save that would persist the chest.
- **Shape F slot-decrement side of `slot.method_32753`** (flagged in Pass 3 closing) is unrelated to YIAS and still untraced.

---

### Pass 4 closing notes

- **H-24 (YIAS solo totem-revive) — NONE (solo).** This closes the highest-probability solo surface the prior auditor could name. The YIAS handler is downstream of the vanilla totem gate in all three loader variants; its own defensive totem check returns early before any mutation; and no static field exists anywhere in the YIAS source tree that could carry an inventory snapshot across deaths. Rule-7 compliance: I am not going to speculate "but maybe" around this — the snapshot has no memory to live in.
- **Running solo-dupe total across Pass 1+2+3+4**: **zero confirmed, zero probable.** H-01 remains the only confirmed dupe in the surfaces traced, and it is explicitly two-player.
- **Rule 4 restated**: this audit has now traced shapes A, B, D, G, H, I, J, L, N, plus H-08/H-09, H-10-adjacent surfaces, and the YIAS death-path totem branch. Shapes **F, K, M, U, V, W, X, Y, Z**, Phase 2 (external trackers for the exact shipped versions: SBP `3.23.4.3.106`, SCore `1.2.9.21.168`, Lootr `1.11.37.118`, YIAS `4.7`), and Phase 5 (JAR↔source bytecode diff) remain un-executed. A solo dupe could still hide there.
- **Structural summary (why so few solo dupes in this stack):** SBP and SCore tie every backpack to a `STORAGE_UUID`-keyed world-global `BackpackStorage` entry, so stack-duplicating operations (pick-block, recipe-copy, inventory-move) share storage rather than cloning it. Lootr drops vanilla chest items with `copy_components.include` limited to `minecraft:custom_name`, so block-break cannot carry per-container identity. YIAS is the only mod in the stack that exposes raw `ItemStack` references across a tick boundary — which is exactly why H-01 (YIAS × SBP pickup-upgrade) is the surface that fell and no YIAS-alone solo surface has been found after four passes.
- **Auditor**: Devin (session `c18c25e7b8ec48aaa59d10412d68e148`).

---

## Appendix E — Pass 5: External intel (Phase 2, 2026-04-18)

**Scope**: Public issue trackers, Modrinth comments, CurseForge comments, Reddit, and YouTube for the exact shipped versions — **SBP `3.23.4.3.106`, SCore `1.2.9.21.168`, Lootr `1.11.37.118`, YIAS `4.7`**.

**Rule 7 compliance**: This appendix reports what third parties have publicly claimed. A third-party claim is *evidence*, not *proof*. Every candidate H-25+ below is flagged with (a) whether the claim is maintainer-confirmed, (b) whether the bug mechanism is present in our shipped code, and (c) whether repro is solo.

### E.1 Log of every public report reviewed

| # | Repo / Issue | Title | Filed | Status | Version reported | Maintainer verdict | Solo? |
|---|---|---|---|---|---|---|---|
| E-1 | [SBP#1487](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1487) | Anvil Upgrade causes unintentional item duping (shift-click result) | 2025-09-01 | CLOSED same-day | 1.21.8-3.24.23.1318 | **CONFIRMED** by P3pp3rF1y: "tiny piece of code changed in vanilla that caused the result to be recreated half way through the shift click. Same issue actually affected Smithing Upgrade as well. Now both of these are fixed in the latest releases for 1.21.4 and later" | YES |
| E-2 | [SBP#1525](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1525) | Backpack contents lost after updating from 1.20.1 to 1.21.1 | 2025-10-22 | CLOSED | 1.21.1-3.25.12.1403 | Migration issue, not dupe. Data loss on version upgrade. | N/A |
| E-3 | [SBP#1526](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1526) | Every item in my backpacks keeps getting deleted (inception + keep-inventory death) | 2025-10-24 | CLOSED | 1.21.1 | "Unable to recreate in dev pack. Likely modpack-specific." Later maintainer hypothesis: "duplicate backpack deduplication logic cleared their id." | Unclear |
| E-4 | [SBP#1528](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1528) | Item deletions and dupes within backpacks accessed via inception upgrade | 2025-10-27 | CLOSED 2026-03-30 | 1.21.1-3.25.12 | "Unable to recreate in dev pack" — closed without fix, not reproducible by maintainer | Reporter claimed yes |
| E-5 | [SBP#1535](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1535) | Inception Upgrade Issues (Advanced Magnet + dupe-then-delete) | 2025-11-11 | CLOSED 2026-03-30 | 1.21.1-3.25.14.1410 | "Unable to recreate these issues." Closed as stale. | Reporter: solo mostly |
| E-6 | [SBP#1564](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1564) | Duplication/deletion issue with Minecraft's new bundle item | 2025-12-12 | CLOSED | — | Closed (resolution not in public summary) | Needs bundle-experiment enabled in 1.21.1 |
| E-7 | [SBP#1580](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1580) | Backpack make item copy (craft table upgrade remove/re-add) | 2025-12-24 | CLOSED 2025-12-28 | 1.21.1-3.25.20.1470 | "I am unable to recreate this even with the exact same setup. So this is a case of another mod interfering." Closed as `question` / stale. | Reporter: required mekanism pipes for the provided repro; core mechanism (remove craft-upgrade → state persists) is solo in principle |
| E-8 | [SBP#1650](https://github.com/P3pp3rF1y/SophisticatedBackpacks/issues/1650) | [1.21.1] Dupe/Rollback on Backpack Contents When Nested w/ Inception Upgrade | 2026-04-14 | **STILL OPEN** | 1.21.1-3.25.31.1560 / 3.25.34.1604 | Maintainer has not yet responded on record as of 2026-04-18 | Reporter: solo |
| E-9 | [Lootr#21](https://github.com/LootrMinecraft/Lootr/issues/21) | Duplication bug with Lootr chest in minecart (1.16 era) | 2021-02-22 | CLOSED same-day | 0.0.4.14 (1.16) | "A fix for this was released over three weeks ago" — fix in 0.0.4.15, Feb 2021 | N/A (pre-1.21) |
| E-10 | [Lootr#624](https://github.com/LootrMinecraft/Lootr/issues/624) | Unable to open chests in different structures (NeoForge 1.21.1) | 2025-04-25 | CLOSED 2025-05-18 | NeoForge 1.21.1 | Null loot pool in broken world generation; unrelated to dupe | N/A |
| E-11 | Lootr issues listing | (No other dupe reports for Lootr 1.21.1) | — | — | — | Only historical 1.16-era dupe reports surfaced | — |
| E-12 | [Serilum/.issue-tracker#3673](https://github.com/Serilum/.issue-tracker/issues/3673) | Items Deleted from Inventory on Death (Stack Refill, not YIAS) | 2026-04-08 | OPEN | MC 26.1 (post-1.21.x) | Assigned to ricksouth, labelled `Bug` | N/A — wrong mod (Stack Refill, not YIAS) and wrong MC version |
| E-13 | Serilum tracker (YIAS-specific search) | — | — | No YIAS dupe reports located | — | — | — |

### E.2 Version cross-reference

**Our shipped SBP = `3.23.4.3.106`** (pre-1.21.1? Let me restate: the `1.21.1-3.23.4.3.106` tag is on the CurseForge-only build ladder. The Modrinth ladder for 1.21.1 starts at `3.23.2.1185`, jumps to `3.24.3.1216` → `3.24.16.1269` (first inception-save fix) → `3.25.12.1403` → `3.25.31.1560` (bug E-8 reported here). Our version is **before** every one of the documented inception fixes.)

**Our shipped YIAS = `4.7`**, Lootr = `1.11.37.118`, SCore = `1.2.9.21.168`.

Relevant fix log lines from SBP's Modrinth/CurseForge changelogs checked during this pass:

- **`3.24.16.1269`**: *"Fixed backpacks nested in another backpack with inception upgrade to properly save their contents id if they haven't been open before being put into the main backpack. Prevents item loss in that case."*
- **1.21.4+ branch**: fix for E-1 anvil/smithing shift-click dupe ("recreated half way through the shift click"). **Not backported to the 1.21.1 line.**

### E.3 Candidate H-25 hypotheses (ranked)

#### H-25 (candidate) — Anvil-upgrade shift-click result dupe

**Source**: E-1 (SBP#1487), maintainer-confirmed on 1.21.8.

**Bug mechanism (per maintainer)**: "a tiny piece of code changed in vanilla that caused the result to be recreated half way through the shift click." The vanilla change in question is in `AnvilMenu` / `ItemCombinerMenu` / `ResultSlot` between 1.21.1 and 1.21.8.

**Presence in our shipped code**: our SBP has `AnvilUpgradeContainer$PersistableAnvilMenu extends class_1706 (AnvilMenu)` at <ref_snippet file="/home/ubuntu/repos/dupefind1/decompiled/sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/p3pp3rf1y/sophisticatedbackpacks/upgrades/anvil/AnvilUpgradeContainer.java" lines="97-165" />. The result slot is defined at `AnvilUpgradeContainer:38-39` and passed to the outer upgrade-container framework as `slots.add(this.resultSlot)`. The shift-click logic is not overridden in `AnvilUpgradeContainer` itself — it is delegated to the outer `BackpackMenu.method_7601` / `quickMoveStack` path, which I have not yet traced.

**Critical distinction**: the maintainer said the trigger is a vanilla change *after* 1.21.1. That means **if the vanilla bug does not exist in 1.21.1, neither does this dupe.** The most likely scenario is: 1.21.4 introduced a change to `ItemCombinerMenu.slotsChanged` or `ResultSlot.onTake` that caused the result stack to be regenerated during shift-click. If that change is not in 1.21.1, our shipped SBP code does not expose this dupe.

**Verdict without trace**: **UNKNOWN. Strong circumstantial evidence the dupe does NOT exist in 1.21.1** (because the maintainer's language implies the vanilla code change that caused it happened in 1.21.4+). Trace target: compare `net.minecraft.class_1706` / `class_1734` / `ResultSlot.onTake` between the 1.21.1 vanilla jar and the 1.21.4 vanilla jar. That comparison requires the vanilla jar (out of scope for this four-mod audit — the vanilla 1.21.1 side of it might be reachable from the deobf jars shipped with the modpack).

#### H-26 (candidate) — Inception upgrade rollback-on-normal-edit (open as of 2026-04-18)

**Source**: E-8 (SBP#1650), currently open, reporter on 1.21.1-3.25.31.

**Bug mechanism (per reporter)**: nested backpack (inception upgrade) inventory rolls back to pre-edit state during normal gameplay. Specifically: (1) `Ctrl+Q` bulk-remove from nested bag while holding — items never drop to ground; (2) later removing the nested bag and reopening shows it reverted to pre-`Ctrl+Q` state. Advanced Magnet Upgrades in the nested bags are part of the configuration.

**Presence in our shipped code**: our SBP has `SubBackpacksHandler` + `InceptionInventoryHandler` + `InceptionUpgradeWrapper`. The save path that is failing (per reporter) would be `BackpackWrapper.save()` → backpack's `BackpackStorage.setDirty()`. I have not yet traced whether a sub-backpack's inventory edit correctly propagates `setDirty()` up through `InceptionInventoryHandler` → `SubBackpacksHandler` → outer `BackpackWrapper` → `BackpackStorage`.

**Critical structural observation from our code** (raw read of the three files — no trace of save path yet):
- `SubBackpacksHandler:35-46` (`onContentsChanged`) only fires when the OUTER backpack's slot at index N changes — adding/removing a sub-backpack or swapping one out. Edits to the sub-backpack's *interior* do not trigger `onContentsChanged` on the outer.
- `InceptionInventoryHandler:71-86` (`insertItem` / `extractItem`) delegates via `combinedInventories` to the sub-backpack's `getInventoryForInputOutput()`. The sub-backpack's own `setDirty` should fire on that path.
- Whether that setDirty actually marks the OUTER `BackpackStorage` SavedData dirty — which is what persists to disk — is the open question.

**Verdict without trace**: **PLAUSIBLE-IN-OUR-CODE. Reporter-confirmed in 3.25.31+, mechanism exists in 3.23.4.3.106.** This is the single strongest "might actually be present in our version" lead from Phase 2. Trace target: `SubBackpacksHandler`, `InceptionInventoryHandler`, `BackpackWrapper.getInventoryForInputOutput`, `BackpackStorage.setDirty`. Estimated 60-120 min of careful trace.

#### H-27 (candidate) — Craft-table-upgrade state persistence / infinite copy

**Source**: E-7 (SBP#1580), reporter claim, maintainer could not reproduce.

**Bug mechanism (per reporter)**: (1) put craft-table upgrade in backpack; (2) put items in its grid; (3) move them out via item pipe to a chest; (4) remove the craft-table upgrade from backpack and re-add; (5) the 3×3 grid items are back. Net effect: items end up in the chest AND in the re-added upgrade.

**Presence in our shipped code**: our SBP has `CraftingUpgradeItem` / `CraftingUpgradeContainer` / `CraftingUpgradeWrapper` (not yet read in this pass). The mechanism ("upgrade state persists in upgrade stack on detach") is a `ComponentItemHandler`-on-upgrade-stack pattern. The `AnvilUpgradeWrapper:17-29` uses this exact pattern — `ComponentItemHandler(upgrade, class_9334.field_49622, 2)` — storing inventory directly on the upgrade stack's data components. If crafting upgrade uses the same pattern, the upgrade stack will carry items with it when removed.

**Critical distinction**: the maintainer said "unable to recreate even with the exact same setup" — suggesting the bug depends on a mod-interaction (Mekanism pipes moving items while the craft table upgrade is present). The *core* mechanism (upgrade stack carrying grid contents when detached) is likely not a bug in isolation — it's how the upgrade is designed. The dupe only manifests when a SECOND path removes the items from the grid (pipe extraction) while the upgrade retains them.

**Verdict without trace**: **REQUIRES MEKANISM OR EQUIVALENT PIPE MOD.** Our modpack contains four mods; Mekanism is not one of them. Not a solo dupe in the four-mod audit scope. Might be reproducible with vanilla hoppers (which could pull from the craft-table's result slot in-world if the backpack is placed as a block) — this is a stretch but worth a grep.

#### H-28 (candidate) — Bundle dupe inside backpack slot

**Source**: E-6 (SBP#1564), closed without detailed resolution in public summary.

**Bug mechanism**: right-click to remove an item from a bundle stored in a backpack slot duplicates the item (item grabbed to cursor, but not removed from bundle). Alternatively, left-click to add an item to a bundle causes the item to disappear.

**Presence in our shipped code**: requires Minecraft 1.21.1 to have bundles enabled. In 1.21.1, bundles are behind an experimental datapack flag (`minecraft:bundle` experiment). **If the modpack's world does not enable the bundle experiment, this dupe is unreachable.** Our audit scope does not include world/datapack config for the shipped modpack.

**Verdict without trace**: **CONTINGENT ON BUNDLE EXPERIMENT.** If enabled, plausible in our version (the bundle item-handler code in SBP's slot-click path would be the same across the 3.23 → 3.25 range unless explicitly patched).

### E.4 What Phase 2 did NOT find

- **Zero dupe reports for Lootr on any 1.21.x version.** The only 1.21.x Lootr issue found (E-10) is a crash bug, not a dupe. The historical 2021 dupe (E-9) was patched years before our shipped 1.21.1 build.
- **Zero dupe reports for YIAS on any version.** Serilum's consolidated issue tracker has zero YIAS-labeled dupe issues. The one similar-looking report (E-12) is against Stack Refill — a different Serilum mod.
- **Zero dupe reports specifically for SCore on its own.** All SBP-world dupes route through a backpack UI (SBP code), which depends on SCore primitives (slot handlers, component storage) — SCore is never named as the standalone culprit in public reports.
- **No Reddit or YouTube dupe tutorials** for SBP 3.23 / Lootr 1.11.37 / YIAS 4.7 / SCore 1.2.9.21 surfaced in Phase 2 searches. The mainstream modded-1.21.1 dupe tutorials in that corpus are about other mods (Create, IE, Mekanism, etc.).

### E.5 Consolidated verdict

**Running solo-dupe total across Pass 1+2+3+4+Phase 2: zero confirmed, zero probable, two plausible-in-our-code candidates (H-26, H-28), one structurally-unlikely (H-25), one out-of-scope (H-27).**

H-01 remains the only confirmed dupe in the surfaces traced, and it is two-player.

**External intel's strongest contribution to the audit**: **E-8 (SBP#1650) is still open as of this writing**, filed four days before this Phase 2 pass, by a reporter on a 1.21.1 modpack, describing a dupe that matches the code shape visible in our `SubBackpacksHandler` + `InceptionInventoryHandler`. The maintainer has not yet responded. The reporter provided no code or stack trace, but the described mechanism (nested-backpack state rollback during normal edits) is **consistent** with what is visible in our shipped source. This upgrades Shape K (Inception Upgrade) from "historically productive shape" to "actively reported, unfixed, matching-code-visible" and makes it the clear next trace target if the audit continues.

**Rule 4 restated**: Phase 2 narrowed the un-traced surface but did not empty it. Shapes **F, M, U, V, W, X, Y, Z**, Phase 5 (JAR↔source bytecode diff), and H-25/H-26/H-28 code-trace remain un-executed.

---

## Appendix F — Pass 5 (H-26 code trace: Inception save-dirty propagation)

**Scope**: Trace whether interior edits on a nested (inception) sub-backpack in SBP 3.23.4.3.106 correctly propagate `setDirty()` up through the handler chain to mark the singleton `BackpackStorage` SavedData dirty for persistence. Triggered by E-8 (SBP#1650, open, reporter's "rollback" symptom on 3.25.31). Files of record: SCore 1.2.9.21.168, SBP 3.23.4.3.106.

### F.1 Chain of custody — what actually runs when a sub-backpack's interior is edited

Start from an Advanced-Magnet-Upgrade pulling an item into a nested sub-backpack (the reporter's exact setup). The item-handler call stack:

**Step 1 — Inception entry point.** `InceptionInventoryHandler.insertItem(int slot, class_1799 stack, boolean simulate)` at `sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/.../upgrades/inception/InceptionInventoryHandler.java:71-74` delegates to `this.combinedInventories.insertItem(slot, stack, simulate)`. `combinedInventories` is a `CombinedInvWrapper` built in the constructor at `:42` via `this.subBackpacksHandler.getSubBackpacks().forEach(sbp -> this.handlers.add(sbp.getInventoryForInputOutput()))`.

**Step 2 — CombinedInvWrapper routing.** `CombinedInvWrapper.setStackInSlot` / `insertSlot` / `extractSlot` (`sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/.../util/CombinedInvWrapper.java:59-121`) just maps the slot index to the correct underlying handler and delegates. No memory of its own, no listener registrations, no dirty tracking. Pure router.

**Step 3 — Sub-backpack's InventoryIOHandler.** The per-sub-backpack handler obtained by `sbp.getInventoryForInputOutput()` at `sophisticatedbackpacks-1.21.1-3.23.4.3.106/net/.../backpack/wrapper/BackpackWrapper.java:158-164` is an `InventoryIOHandler` (lazy-created, cached per sub-wrapper). Its `getFilteredItemHandler()` returns either `storageWrapper.getInventoryForUpgradeProcessing()` directly (no I/O filters) or a `FilteredItemHandler.Modifiable` wrapping it (`sophisticatedcore-1.21.1-1.2.9.21.168/net/.../inventory/InventoryIOHandler.java:12-22`).

**Step 4 — Sub-backpack's InventoryModificationHandler.** `getInventoryForUpgradeProcessing()` at `BackpackWrapper.java:108-114` returns `new InventoryModificationHandler(this).getModifiedInventoryHandler()`. That in turn at `InventoryModificationHandler.java:16-22` calls `this.backpackWrapper.getInventoryHandler()` — the critical line, because this is where the sub-backpack's STORAGE_UUID is auto-allocated if absent.

**Step 5 — Sub-backpack's BackpackInventoryHandler construction.** `BackpackWrapper.getInventoryHandler()` at `BackpackWrapper.java:116-128`:

```java
public InventoryHandler getInventoryHandler() {
   if (this.handler == null) {
      this.handler = new BackpackInventoryHandler(
         this.getNumberOfInventorySlots() - this.getNumberOfSlotRows() * this.getColumnsTaken(), this, this.getBackpackContentsNbt(), () -> {
            this.markBackpackContentsDirty();
            this.inventorySlotChangeHandler.run();
         }, StackUpgradeItem.getInventorySlotLimit(this)
      );
      this.handler.addListener(((ItemDisplaySettingsCategory)this.getSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class))::itemChanged);
   }
   return this.handler;
}
```

Two things happen here:
- `getBackpackContentsNbt()` at `:150-152` calls `BackpackStorage.get().getOrCreateBackpackContents(this.getOrCreateContentsUuid())`. This returns a **live reference** to `BackpackStorage.backpackContents.get(subUuid)` — the same `class_2487` that BackpackStorage serializes to disk. Mutations to that tag are automatically visible to BackpackStorage's save path.
- The `saveHandler` Runnable passed into the handler constructor captures `this.markBackpackContentsDirty()` — which at `:154-156` is exactly `BackpackStorage.get().method_80()` (`class_18.setDirty()`).

**Step 6 — onContentsChanged → saveInventory → setDirty.** Every mutation (insertItem, extractItem, setStackInSlot) on the sub-backpack's handler ultimately routes through `InventoryHandler.onContentsChanged(int slot)` at `sophisticatedcore-1.21.1-1.2.9.21.168/net/.../inventory/InventoryHandler.java:112-118`:

```java
public void onContentsChanged(int slot) {
   super.onContentsChanged(slot);
   if (this.persistent && this.updateSlotNbt(slot)) {
      this.saveInventory();
      this.triggerOnChangeListeners(slot);
   }
}
```

`saveInventory()` at `InventoryHandler.java:475-482`:

```java
public void saveInventory() {
   RegistryHelper.getRegistryAccess().ifPresent(registryAccess -> this.contentsNbt.method_10566("inventory", this.serializeNBT(registryAccess)));
   if (this.inventoryPartitioner != null) {
      this.contentsNbt.method_10566("partitioner", this.inventoryPartitioner.serializeNBT());
   }
   this.saveHandler.run();
}
```

This writes the serialized `"Items"` list into `contentsNbt` (the live reference from Step 5 into `BackpackStorage.backpackContents[subUuid]`), **then** executes `saveHandler.run()` = the lambda from Step 5 = `markBackpackContentsDirty()` + `inventorySlotChangeHandler.run()`.

**Step 7 — BackpackStorage is marked dirty.** `markBackpackContentsDirty()` → `BackpackStorage.get().method_80()`. `BackpackStorage` extends `class_18` (SavedData). `method_80()` is `class_18.setDirty()` — the standard vanilla flag that causes the world's DimensionDataStorage to persist this SavedData on next autosave / world unload.

**Conclusion F.1**: The save-dirty chain for sub-backpack interior edits is **fully wired** in 3.23.4.3.106. Every interior edit via inception propagates to `BackpackStorage.setDirty()`. The hypothesis that "H-26 is a broken save chain" is **structurally false** against this code.

### F.2 What about the outer BackpackStorage entry?

The sub-backpack and outer backpack each have their own STORAGE_UUID → their own `BackpackStorage.backpackContents[uuid]` entry. The singleton `BackpackStorage` SavedData is ONE data file containing ALL UUIDs. `method_80()` marks the singleton dirty, not an individual UUID. On next save, the entire map is written. So:

- Sub-backpack's edit calls sub-backpack-wrapper's `markBackpackContentsDirty()` → `BackpackStorage.method_80()` → whole map persists → **sub-backpack's entry AND outer backpack's entry both get written to disk together**.
- The outer backpack's entry is written in whatever state its own `contentsNbt` is in at save time. That `contentsNbt` is also a live reference from `BackpackStorage.backpackContents[outerUuid]`, mutated only when the outer's OWN `onContentsChanged` fires (via `SubBackpacksHandler.onContentsChanged` at `SubBackpacksHandler.java:35-46`, which only fires when the outer's slot value changes — i.e., sub-backpack added/removed/swapped, NOT on sub-backpack interior edits).

**This is not a dupe** — both UUIDs' contents are on disk correctly. Sub-backpack edits are persisted. Outer backpack's metadata (the stack it holds in its slot) is only re-serialized on outer-slot change, but that's fine because the outer's slot stack itself hasn't changed — it's still "the sub-backpack item with STORAGE_UUID = X", which is the same identity regardless of what's inside UUID X's contents.

### F.3 The real pre-3.24.16.1269 bug (ITEM LOSS, not dupe)

There IS a real bug in our 3.23.4.3.106 pre-dating the 3.24.16.1269 changelog line "Fixed backpacks nested in another backpack with inception upgrade to properly save their contents id if they haven't been open before being put into the main backpack. Prevents item loss in that case."

**Mechanism**:
1. Player puts a **fresh** backpack F (no `STORAGE_UUID` data component yet) into outer O's slot via a direct slot click. O's `SubBackpacksHandler.onContentsChanged(slot)` fires (outer slot changed). O's outer handler's `onContentsChanged` → `saveInventory()` → serializes F's current stack NBT (no UUID) into O's cached `stackNbts[slot]` and writes it into `BackpackStorage.backpackContents[outerUuid]["inventory"]["Items"]`.
2. Later, something accesses F through inception — e.g., Advanced Magnet Upgrade auto-pulls an item, or the player opens O's UI which constructs `InceptionInventoryHandler` which calls `sbp.getInventoryForInputOutput()` on F, which (via Steps 3-5 above) calls F's `getOrCreateContentsUuid()`. F's stack data components are mutated in place: UUID=X is now attached to F's stack. `BackpackStorage.backpackContents[X]` is created.
3. Items go into `BackpackStorage.backpackContents[X]`. `BackpackStorage.method_80()` fires. Save tick: both entries persist — but O's `contentsNbt` still has F serialized WITHOUT UUID (from step 1; F's stack-data-component mutation did NOT trigger O's `onContentsChanged`, because the outer slot's stack object is the same reference).
4. World autosaves. On disk: `backpackContents[outerUuid]["inventory"]["Items"]` contains F-with-no-UUID. `backpackContents[X]` contains the items.
5. World reload. O's BackpackInventoryHandler deserializes F from `"Items"` as a fresh no-UUID stack. Next access creates a NEW UUID Y ≠ X. `BackpackStorage.backpackContents[Y]` is empty. `[X]` is orphaned.

**Net effect**: items go missing on reload. No items duplicated. Orphan entry `[X]` is not reachable from any stack (no surviving reference), and `BackpackStorage.removeNonPlayerBackpackContents` will eventually GC it.

**Why this is NOT a dupe**: no item count ever increases. This is LOSS, not GAIN. The reporter's phrasing "rollback" matches this exactly — they lose items on reload, items don't appear elsewhere.

### F.4 Ctrl+Q path inspection (reporter's specific claim)

Reporter: "I was pressing and holding `CTRL` + `Q` to remove items in bulk... The items never actually dropped on the ground."

`class_1713.field_7795` = vanilla `ClickType.THROW` (Ctrl+Q). Handled in `StorageContainerMenuBase.method_30010` at `sophisticatedcore-1.21.1-1.2.9.21.168/net/.../common/gui/StorageContainerMenuBase.java:1247-1251`:

```java
} else if (clickType == class_1713.field_7795 && this.method_34255().method_7960() && slotId >= 0) {
   class_1735 slot4 = this.method_7611(slotId);
   int i1 = dragType == 0 ? 1 : slot4.method_7677().method_7947();
   class_1799 itemstack8 = slot4.method_32753(i1, slot4.method_7677().method_7914(), player);
   player.method_7328(itemstack8, true);
}
```

This is a Ctrl+Q INSIDE the sub-backpack's own `BackpackContainer` (the nested bag's UI, not the outer's). Crucial: a sub-backpack's menu binds its storage slots to the SUB's own InventoryHandler (`StorageInventorySlot` at `sophisticatedcore-1.21.1-1.2.9.21.168/net/.../common/gui/StorageInventorySlot.java:15-21` constructs with `storageWrapper::getInventoryHandler` where `storageWrapper` is the sub-backpack's own `BackpackWrapper`). `method_32753` → vanilla `Slot.remove(int, int, Player)` → `method_7671(amount)` (`Slot.remove(int)`) → `SlotItemHandler.remove()` → `handler.extractItem(slot, amount, false)`.

That routes through the sub-backpack's `InventoryHandler.extractItem` → mutates slot → fires `onContentsChanged` → Step 6/7 above → BackpackStorage marked dirty. The extracted stack is returned and `player.method_7328(itemstack8, true)` drops it as an ItemEntity.

**No dupe vector here**. The reporter's "items never dropped" symptom — if it really happens — would be either:
- A client-server desync (client shows empty slot, server still has full stack, no ItemEntity spawned). This is a SYNC bug, not a dupe.
- A sub-backpack in the bugged state of F.3 where the sub-UUID-less outer serialization means the dropped ItemEntity's stack has the wrong UUID or no UUID → player picks up a bag appearing empty. Still ITEM LOSS, not gain.

Neither produces a duplicated item.

### F.5 Verdict

**H-26: NONE (no solo dupe in our shipped 3.23.4.3.106).**

Kill-shot, in three lines:

1. `BackpackWrapper.java:116-128` constructs every backpack's `InventoryHandler` with a save-handler Runnable that calls `markBackpackContentsDirty()` on every `onContentsChanged`.
2. `BackpackWrapper.java:154-156` is that method: `BackpackStorage.get().method_80()` — unconditionally sets the singleton SavedData dirty.
3. `InventoryHandler.java:112-118` + `:475-482` wire every slot mutation through `onContentsChanged → saveInventory → saveHandler.run()`. The sub-backpack's contents-NBT is a live reference into `BackpackStorage.backpackContents[subUuid]`, so edits are persisted in the next save cycle.

The reporter's SBP#1650 "rollback" symptom matches the **item-loss** bug described by the 3.24.16.1269 changelog, not a dupe. Our version has that item-loss bug (unfixed), but it loses items rather than duplicating them: the orphaned `backpackContents[X]` entry cannot be re-associated with any stack after reload because no stack has the matching UUID anymore.

**Running solo-dupe total across Pass 1+2+3+4+Phase 2+Pass 5: zero confirmed.** H-01 remains the only confirmed dupe and is two-player.

### F.6 What this trace does NOT cover (rule 4)

- The actual item-loss bug of F.3 could be surfaced as a separate finding for the user's attention — it is a real defect in the shipped pack — but it is out of scope for the dupe audit.
- I did not attempt to bypass the UUID creation by forcing the sub-backpack through pick-block / `/give` / creative-inventory copy paths while nested. `class_1799.method_7972()` (stack copy) preserves the STORAGE_UUID data component, so vanilla stack copies of an existing sub-backpack produce same-UUID siblings that share backing storage (by design, not a dupe).
- I did not trace the `InceptionWrapperAccessor`, `InceptionFluidHandler`, or `InceptionEnergyStorage` — they are peripheral to the inventory save chain and operate on fluid/energy storage which is unrelated to the reporter's item-dupe claim.
- Shapes **F, M, U, V, W, X, Y, Z** and Phase 5 (JAR↔source bytecode diff) remain un-executed.

---

## Appendix G — Pass 6: out-of-scope death-event surface sweep (combatlog-fabric, Clumps, full-pack grep)

**Scope**: Prior auditor's final-pass experiment on H-25 came back null (0 dupes observed across 20 deaths; at ~10% per-death expected probability, p ≤ ~0.12 that the mechanism-as-described is live). User asked for anything still worth hunting. This pass extends the search **outside the original 4-mod audit scope** to the rest of the shipped pack, looking for any additional actor that holds `ItemStack` references across a YIAS death tick — the structural shape that produced H-01.

Every verdict below is still rule-4-scoped: "no dupe in the surfaces traced using shapes I know."

### G.1 combatlog-fabric-2.6+1.21.1 (full trace)

**Why this mod was a plausible H-01-shaped second-actor candidate**: it is the only other death-adjacent mod in the pack (hooks disconnect-while-in-combat and force-kills). If it snapshotted, forked, or held the dying player's inventory like SBP's pickup-upgrade does, it could race YIAS's copy-and-zero across the tick boundary.

**Full source footprint** (14 files, 517 LoC):
- `me/toastymop/combatlog/platforms/fabric/CombatLogFabric.java:7-11` — `onInitialize()`: loads config and registers `ServerTickEvents.END_SERVER_TICK → CombatLogEventHandler.INSTANCE`. No other entrypoints.
- `me/toastymop/combatlog/platforms/fabric/CombatLogEventHandler.java:10-12` — `onEndTick` → `CombatTicks.CombatTick(server)`.
- `me/toastymop/combatlog/CombatTicks.java:13-35` — iterates all players, decrements their `combatTime` NBT int, posts a HUD notice when it hits zero. **No inventory/ItemStack access anywhere.**
- `me/toastymop/combatlog/CombatCheck.java:15-45` — on damage, sets a "combatTime" + "inCombat" NBT flag on target (and attacker if PvP), optionally starts an ender-pearl cooldown. **No inventory/ItemStack access.** The `class_1802.field_8634.method_7854().method_7909()` at line 13 is a cached `Items.ENDER_PEARL` ref used only as a key for cooldowns — never inserted into or copied from a real inventory.
- `me/toastymop/combatlog/mixin/EntityDamageMixin.java:24-27` — `@Inject(at=TAIL)` on `class_1309.method_5643` (hurt). Calls `CombatCheck.CheckCombat`. No inventory access.
- `me/toastymop/combatlog/mixin/DisconnectMixin.java:12-15` — `@Inject(at=HEAD)` on `class_3222.method_14231` (disconnect). Calls `CombatDisconnect.OnPlayerDisconnect`.
- `me/toastymop/combatlog/CombatDisconnect.java:17-51` — **the one non-trivial code path.** If the disconnecting player is in combat and `disconnectKill=true`:
  1. Temporarily flip `gameRules.keepInventory` → false (line 26).
  2. Call `entity.method_5643(damageSources.generic(), 100000f)` (line 27) — vanilla damage pipeline, which goes `hurt → die → dropAllDeathLoot → ItemEntity spawns`, firing YIAS's `ALLOW_DEATH` mid-pipeline exactly as any other death.
  3. Flip `keepInventory` back to its previous value (line 28).
  4. Optionally run a configured `disconnectCommand` against the server.
  5. `TagData.endCombat(entity)` clears the combat NBT flags (line 49).
- `me/toastymop/combatlog/mixin/PlayerMixin.java:12-19` — cancels `Player.tryToStartFallFlying()` while in combat. No inventory access.
- `me/toastymop/combatlog/mixin/CommandsMixin.java:20-36` — cancels certain Brigadier-dispatched commands while in combat. No inventory access.
- `me/toastymop/combatlog/mixin/EntityDataSaverMixin.java:25-36` — adds a `combatLog` `class_2487` tag to every entity's save NBT. Opaque scalar storage for the combat flags.
- `me/toastymop/combatlog/util/TagData.java:7-35` — six static helpers that read/write `combatTime` (int) and `inCombat` (bool) on the entity's persistent-data tag. **Pure NBT, no ItemStack.**
- `me/toastymop/combatlog/util/IEntityDataSaver.java:5-7` — interface with a single `getPersistentData()` → `class_2487` method.
- `me/toastymop/combatlog/CombatConfig.java:1-196` — JSON5 config loader. No runtime inventory interaction.

**Kill-shot grep**: `grep -rE "class_1799|ItemStack|method_7972|inventory|field_7547|drop|setCount|grow|class_1542" me/toastymop/` matches **exactly one line** across the entire mod — `CombatConfig.java:129`, a user-facing config comment string containing the English word "drop" in prose. The combatlog Java source tree contains **zero `ItemStack` handling, zero `ItemEntity` handling, zero inventory mutation, zero pickup or drop interception**.

**Interaction with YIAS**:
1. Player is marked `inCombat` via `EntityDamageMixin.TAIL` on their next-taken damage tick (purely an NBT flag, does not read or copy inventory).
2. Player presses Disconnect → `ServerPlayer.disconnect()` → `DisconnectMixin.HEAD` fires → `CombatDisconnect.OnPlayerDisconnect` → (if in combat + `disconnectKill=true`) flips gamerule, calls `hurt(damage,100000f)`, flips gamerule back.
3. Inside that `hurt()` call, the vanilla death pipeline runs and YIAS's `ALLOW_DEATH` listener fires on the dying player **identically to any other death trigger** (lava, `/kill`, TNT, etc.). YIAS captures the inventory refs via `Util.getInventoryItems` and enqueues its `+1`-tick delayed task (`DeathEvent.java:37`, `Util.java:73-77`).
4. `CombatDisconnect` returns, `DisconnectMixin` returns, vanilla `ServerPlayer.disconnect()` continues: the player's NBT is serialized to disk (inventory is **already empty** at this point because `dropAllDeathLoot` has already run and cleared each slot), the player entity is removed.
5. Tick T+1: YIAS's task fires and copies the captured refs into the death chest, zeros the original stacks. Nothing to race against — combatlog has no residual state, no held ItemStacks, no scheduled follow-up.

**Disk vs. world consistency at the disconnect**: the only dupe shape one could imagine here is "player's saved-to-disk inventory still has items at the moment of disconnect while the world-side death chest also has them." For that to happen, the disk save would need to run **before** `dropAllDeathLoot` inside the hurt call. It doesn't: the disk save is invoked by `PlayerList.remove(player)` further down `ServerPlayer.disconnect()`, which is **strictly after** the HEAD injection of combatlog — after `hurt` has already cleared the inventory.

**Verdict: NONE (solo).** combatlog-fabric is a thin NBT-flag + disconnect-hook mod with no inventory or `ItemStack` surface whatsoever. It functions as a trigger for vanilla death, not as an actor that interacts with items. It cannot satisfy the H-01 structural shape because it never holds an `ItemStack` reference at any point in its execution.

### G.2 Clumps-fabric-1.21.1-19.0.0.1 — XP-only (confirmed)

**Why considered**: Clumps has an "entity-merge" flavor; the mod's historical pattern of merging entities of a type could in principle be applied to `ItemEntity` in a way that causes reference aliasing near YIAS's death tick.

**Full mixin footprint** (from `clumps.mixins.json`):
- `MixinExperienceOrb` — `@Mixin(class_1303.class, priority=1001)` — operates exclusively on `class_1303` (ExperienceOrb).
- `ExperienceOrbAccess` — accessor mixin on the same target.

**Full-tree grep for item-entity surface**: `grep -rE "class_1542|ItemEntity" Clumps-fabric-.../` → **0 matches** across the entire mod source tree. The only `class_1799` references are:
- `MixinExperienceOrb.java:23` — import for `class_1799`, used at `:137` where `foundItem.comp_2682()` returns a gear `ItemStack` being repaired by the XP orb (read-only; standard vanilla `repairPlayerItems` flow).
- `platform/IPlatformHelper.java:6` — `getRepairRatio(class_1799 stack)` utility (read-only, gear-repair math).

**Verdict: NONE (solo, cross-mod with YIAS).** Clumps does not touch `ItemEntity` at all. It merges XP orbs only. The one place it reads an `ItemStack` is the vanilla `repairPlayerItems` pathway on the player's worn/held gear, which is read-only and occurs when the player picks up the orb (not at death).

### G.3 Pack-wide death-event surface closure

**Purpose of this section**: put a ceiling on how many unaudited H-01-shaped "second actors" exist in the shipped pack.

**Grep 1 — registered death-event handlers**:

```sh
grep -rlE "ALLOW_DEATH|LivingDeathEvent|LivingDropsEvent|LivingEntityEvents\.DROPS|ItemEntityEvents\.CAN_PICKUP|ServerPlayerEvents\.AFTER_RESPAWN" decompiled/ --include="*.java" \
  | grep -v "sophisticated\|lootr\|youritemsaresafe\|combatlog\|fabric-api"
```

Result: **0 matches.** Every file in the decompiled tree that registers any of these event types belongs to one of the four originally-audited mods, combatlog (cleared above), or fabric-api itself (the infrastructure layer).

**Grep 2 — overrides/mixins targeting the vanilla death pipeline**:

```sh
grep -rlE "method_6078|method_16080|dropAllDeathLoot|onKilled|onDeath|dropDeathLoot|dropEquipment|dropFromLootTable|class_1657.*drop|ServerPlayer.*drop|Inventory.*drop|method_7380" decompiled/ --include="*.java" \
  | grep -vE "sophisticated|lootr|youritemsaresafe|combatlog|fabric-api|architectury|cloth-config|collective|modernfix|ForgeConfig|lithium|sodium|ImmediatelyFast|ferritecore"
```

Result: **1 match — `FarmersDelight/.../CookingPotBlockEntity.java:191`**, which serializes a field named "Inventory" into an NBT blob (the cooking pot's ingredient list). False-positive on the substring "drop" appearing inside the variable name `drops` for the BE's internal ingredient cache. Not death-related, not a player-inventory interaction.

**Grep 3 — rendering infra mods** (`lithium`, `sodium`, `ImmediatelyFast`, `ferritecore`, `ModernFix`, `ForgeConfigAPIPort`, `architectury`, `cloth-config`) **are performance/compat layers** whose mixins target chunk/render/storage primitives, not entity death or inventory. They appear in the exclusion list above because their broad codebase produces false-positive hits on generic strings like `drop`/`inventory`/`items` that have nothing to do with player death.

**Collective** (YIAS's support library) was traced inside YIAS's trace path in Appendix D — `TaskFunctions.enqueueCollectiveTask` is the only Collective primitive YIAS uses, and its scheduling semantics are the one piece of YIAS's timing that remains source-unconfirmed (the Collective jar is not in the decompiled tree). This was flagged as the most likely reason the H-25 live test came back null (the magnet would always lose the race if Collective runs tasks before BE-ticks on tick T+1). Collective does not itself register death handlers.

**Infinitetrading** (villager trading), **Farmer's Delight** (cooking), **Musket Mod** (firearms), **Epic Knights** (weapons/armor), **Small Ships** (ships), **MCW Furniture**, **Beautify**, **OKM**, **Incendium**, **Nullscape**, **Terralith** — worldgen / decorative / equipment mods. None of them register any event from Grep 1 or override any method from Grep 2.

### G.4 Consolidated Pass 6 verdict

**Running solo-dupe total across Passes 1+2+3+4+5+6: zero CONFIRMED. One POSSIBLE weakened by negative experimental evidence (H-25 at 0/20 observed dupes with ~88% cumulative expected under the mechanism-as-described).**

**Cross-mod second-actor surface for H-01-shaped dupes in the shipped pack**:
- SBP (H-01 CONFIRMED, 2-player only).
- SBP's placed-block Magnet Upgrade (H-25 POSSIBLE, live-test null).
- **Combatlog-fabric — NONE (Pass 6 / G.1).**
- **Clumps — NONE (Pass 6 / G.2, XP-only).**
- **No other mod in the pack registers any death/drop/pickup event** (Pass 6 / G.3, two grep sweeps).

This closes the solo-reproducible second-actor surface for the shipped 4-mod audit scope + the full rest of the pack. To find an additional CONFIRMED solo dupe in this stack, one of the following must be true:
1. The H-25 timing model is right but the test missed — re-run with tighter setup (player standing **on** the backpack, deterministic `/kill`, open backpack GUI to observe the 1-diamond partial stack), and try the Advanced Magnet variant (different cooldown: 4 ticks instead of 10, per `MagnetUpgradeWrapper` constructor).
2. Collective does schedule at `END_SERVER_TICK` (making H-25's window real) AND the pickup filter rejected a specific item in the partial-match case — worth checking that the 1-diamond-in-backpack slot had no filter restriction.
3. A dupe exists in one of shapes **F, M, U, W, X, Y, Z** (Phase 2 tracker candidates H-27 Mekanism-only / H-28 bundle-experiment-only also remain, both out-of-scope for the default modpack config).
4. Phase 5 (CFR against the shipped JAR, bytecode diff vs. the decompiled tree) reveals a compiler-level divergence between `decompiled/` and what actually runs. Not executed.

### G.5 What Pass 6 does NOT cover (rule 4)

- **Collective library source** is still not in the decompile tree. A direct read of `TaskFunctions.enqueueCollectiveTask`'s scheduler registration is the one missing piece that could flip H-25 from POSSIBLE+weak-evidence-null to confirmed-NONE or confirmed-CONFIRMED.
- I did not attempt to build a modified client that fabricates a `BackpackOpenPayload` with malicious `(handlerName, identifier)` — the only solo-modified-client surface still POSSIBLE from Pass 2 (H-20). That requires work outside the scope of a decompiled-source audit.
- Shapes **F, M, U, V, W, X, Y, Z** remain un-executed against any mod. Phase 5 (JAR↔source bytecode diff) remains un-executed.
- G.2's statement on Clumps is scoped to item-dupe interaction with YIAS; Clumps may still have XP-specific bugs, which are out of scope for this audit.

### G.6 Operational note for the server operator

The shipped SBP 3.23.4.3.106 is missing the **3.24.16.1269 inception save-id fix** documented in Appendix F.3. Symptom: if a player puts a **fresh, never-opened** backpack into an inception-upgrade backpack, the sub-backpack's items can be orphaned on world reload (item *loss*, not gain — not a dupe, but a real defect). Mitigation: either update SBP to 3.24.16.1269+ (requires checking for save-format compatibility), or document to players that every sub-backpack must be opened once before being nested.

- **Auditor**: Devin (session `0f553816835f4f3db46204f2393fec22`).

