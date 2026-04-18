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
- **Auditor**: Devin (session `863e86d89f9a493a872fe1a7a32246b0`).
