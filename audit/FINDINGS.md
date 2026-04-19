# Sophisticated Backpacks / Core — Packet Surface Audit

Scope: code-read of decompiled sources under this repo. Target: Fabric 1.21.1 ports shipping in this modpack.

- `sophisticatedbackpacks-1.21.1-3.23.4.3.106/` (SBP)
- `sophisticatedcore-1.21.1-1.2.9.21.168/` (SCore)

Format per finding: verdict → vector → evidence (file:line) → proof-of-exploit sketch → suggested fix. All line numbers are against the paths in this repo.

---

## H-27 — Crafting Upgrade stale-recipe dupe (CONFIRMED real dupe)

Verdict: **CONFIRMED.** Server-side state is not reset on the empty-input path, and `selectCraftingResult` re-materialises the result slot without re-validating the current craft matrix. Taking the result does not consume any inputs.

Vector: `SyncContainerClientDataPayload` → `StorageContainerMenuBase.handlePacket` (`containerId` routing) → `CraftingUpgradeContainer.handlePacket` with `{containerId: <id>, selectResult: 0}`.

Evidence:

- `sophisticatedcore-.../network/SyncContainerClientDataPayload.java:25-30` — routes any attacker-supplied `class_2487` to the player's open `field_7512` if it is an `ISyncedContainer`. No auth beyond "you have a menu open".
- `sophisticatedcore-.../common/gui/StorageContainerMenuBase.java:641-665` — dispatches on `containerId` to an arbitrary `upgradeContainers` entry. Client picks the id.
- `sophisticatedcore-.../upgrades/crafting/CraftingUpgradeContainer.java:191-221` (`updateCraftingResult`, server path) — when `lastRecipe` no longer matches current input, falls into the `else` branch. That branch only repopulates `matchedCraftingRecipes`/`matchedCraftingResults` **when `RecipeHelper.safeGetRecipesFor(...)` returns a non-empty list**. On empty input it returns empty → the `if (!recipes.isEmpty())` at line 199 short-circuits, and the stale lists survive. The slot is cleared to `field_8037` at line 219 (so the GUI looks empty), but the server-side match lists are not.
- `sophisticatedcore-.../upgrades/crafting/CraftingUpgradeContainer.java:239-251` (`selectCraftingResult`) — the only bounds check is `resultIndex < matchedCraftingResults.size()`. With stale lists still populated, `selectResult: 0` unconditionally sets `lastRecipe = matchedCraftingRecipes.get(0)`, copies the stale `matchedCraftingResults.get(0)` into the result slot via `method_7673`, and fires `craftResult.method_7665(...)` (recipe-used tracking).
- `sophisticatedcore-.../upgrades/crafting/CraftingUpgradeContainer.java:78-130` (`craftingResultSlot.method_7667`, take handler) — on take, shrinks each non-empty matrix slot by one (`method_5434(i, 1)`, line 107). With the matrix empty, every slot is empty, so zero consumption happens. `remainingItems` is the empty list (line 90) because `lastRecipe.method_8115(emptyInput, level)` returns false. Net effect: result stack is taken, inputs untouched.

Proof-of-exploit (no code yet, pure protocol):

1. Open a backpack with a Crafting Upgrade installed.
2. Place any ingredients that form a real recipe (e.g. 4 planks → crafting table). Wait one tick so the server populates `matchedCraftingRecipes` / `matchedCraftingResults`.
3. Shift-click or manually pull the ingredients back out of the matrix. Server clears the visible result slot but *keeps* the match lists.
4. Send `SyncContainerClientDataPayload` with NBT `{containerId: <crafting upgrade id>, selectResult: 0}`.
5. Server re-materialises the crafting-table stack into the result slot.
6. Click-pick-up or shift-click the result. Inputs stay zero. Repeat.

Harness module: `H27CraftingDupe`.

Suggested fix: see `audit/patches/H-27-crafting-dupe.patch`. Clear `matchedCraftingRecipes`/`matchedCraftingResults`/`lastRecipe`/`selectedCraftingResultIndex` on the empty-input branch, and re-validate the recipe against the current matrix inside `selectCraftingResult` before touching the result slot.

---

## V1 — RequestBackpackInventoryContentsPayload: any backpack readable by UUID (CONFIRMED)

Verdict: **CONFIRMED.** Info-disclosure, not a dupe.

Vector: client → `RequestBackpackInventoryContentsPayload(UUID)` → server replies with the full inventory + upgrade NBT.

Evidence: `sophisticatedbackpacks-.../network/RequestBackpackInventoryContentsPayload.java:29-46`. The handler:

- Looks up `BackpackStorage.get().getOrCreateBackpackContents(uuid)` (note: `getOrCreate` — this also **creates an empty entry** for any UUID you throw at it, which is a minor storage-pollution concern separate from the info leak).
- Copies the `inventory` and `upgradeInventory` NBT sub-tags into a response.
- Sends it back via `BackpackContentsPayload`.

No ownership check, no "was this backpack ever open by this player", no proximity. UUIDs show up in the NBT of every placed or dropped backpack item, so any attacker who can see a backpack (or sniff a dropped one) can later read its contents from anywhere in the world.

Harness module: `V1BackpackInventoryLeak`.

Suggested fix: `audit/patches/V1-backpack-inventory-leak.patch`. Maintain a server-side "allowed UUIDs for this player" set — populated when the player opens a backpack menu, bounded in size, and cleared on menu close — and reject requests whose UUID is not in that set. Also flip `getOrCreate` → `get` so unknown UUIDs don't cause fresh empty entries to be created.

---

## V2 — InventoryInteractionPayload: remote item-handler access, no reach check (CONFIRMED)

Verdict: **CONFIRMED.** Theft/reach bug.

Vector: client → `InventoryInteractionPayload(BlockPos pos, Direction face)` → `InventoryInteractionHelper.tryInventoryInteraction(pos, level, backpack, face, player)`.

Evidence: `sophisticatedbackpacks-.../network/InventoryInteractionPayload.java:26-33`. Only `Config.SERVER.noInteractionBlocks` is consulted inside `tryInventoryInteraction`. There is:

- No distance check against `player.method_19538()` (`player.getEyePosition` / `player.distanceToSqr`).
- No `level.method_8477(pos)` / `level.method_22347(pos)` equivalent — attacker can drive interactions into **unloaded chunks**, force-loading them through the `Storage<ItemVariant>` lookup path.
- No `level.method_8320(pos)` precheck that there's even a block there.

The upgrade's `onHandlerInteract` will then push/pull from whatever storage is at `pos` — chest, barrel, another player's shulker, etc. — using the attacker's backpack as one side of the transfer.

Harness module: `V2InventoryInteraction`.

Suggested fix: `audit/patches/V2-inventory-interaction-reach.patch`. Reject the payload if the target block is further than the player's block reach (1.21.1 uses `player.method_55754()` / `class_1657.method_55754` for block interaction range, conventionally ≤ 6 blocks) or if the chunk at `pos` is not loaded.

---

## V3 — AnotherPlayerBackpackOpenPayload: no distance cap (CONFIRMED)

Verdict: **CONFIRMED.** Privacy + theft depending on the other player's settings.

Vector: client → `AnotherPlayerBackpackOpenPayload(int anotherPlayerId)` → `level.method_8469(id)` → if target player has `ANOTHER_PLAYER_CAN_OPEN` enabled on any backpack, opens that backpack's GUI for the attacker.

Evidence: `sophisticatedbackpacks-.../network/AnotherPlayerBackpackOpenPayload.java:33-56`. The only constraints:

- `Config.SERVER.allowOpeningOtherPlayerBackpacks` must not be explicitly false.
- The entity id must resolve to a `class_1657` in the same dimension (`method_8469` is dimension-scoped in practice).
- The target's `BackpackMainSettingsCategory.ANOTHER_PLAYER_CAN_OPEN` setting must be true.

There is no distance check. Any player across the map with the setting on can be opened.

Harness module: `V3CrossPlayerOpen`.

Suggested fix: `audit/patches/V3-another-player-distance.patch`. Require both players within a bounded radius (suggest `Config.SERVER.anotherPlayerBackpackOpenMaxDistance`, default 6.0 blocks to match normal interaction range, or the stricter of that and the existing "entity interaction range"). Optionally log the opener for server moderation.

---

## V4 — BlockToolSwapPayload / EntityToolSwapPayload: no reach check (CONFIRMED)

Verdict: **CONFIRMED.** Reach-extend vector. Not a direct dupe, but pairs well with unreach-based griefing (mine blocks / hit entities from off-screen).

Vectors:

- `sophisticatedbackpacks-.../network/BlockToolSwapPayload.java:27-47`. Calls `upgrade.onBlockInteract(level, payload.pos, level.method_8320(payload.pos), player)` with an arbitrary pos. No reach check, no loaded-chunk check.
- `sophisticatedbackpacks-.../network/EntityToolSwapPayload.java:29-52`. `level.method_8469(entityId)` with no distance check; if the entity id resolves cross-dimension in some configurations, that's an additional leak surface (though `method_8469` is usually dimension-scoped).

Harness modules: `V4BlockToolSwap`, `V4EntityToolSwap`.

Suggested fix: `audit/patches/V4-tool-swap-reach.patch`. Gate both handlers by the player's configured block/entity reach.

---

## V5 — SyncContainerClientDataPayload is an NBT-driven fanout (CONFIRMED attack surface, audit sweep recommended)

Verdict: **CONFIRMED attack surface**, not itself a bug. Treat as the "entrypoint for all future dupe shapes in this mod family."

Vector: any client-open menu that implements `ISyncedContainer` receives attacker-chosen NBT. Confirmed reachable implementations (27 in this repo; `grep -l "public void handlePacket(class_2487 data)"` in `sophisticatedcore-.../` and `sophisticatedbackpacks-.../`):

- CraftingUpgradeContainer (← H-27)
- StonecutterRecipeContainer (selectRecipe — re-validates by re-reading inputSlot, currently safe)
- StonecutterUpgradeContainer, MagnetUpgradeContainer, FeedingUpgradeContainer, VoidUpgradeContainer, JukeboxUpgradeContainer, FilterUpgradeContainer, CompactingUpgradeContainer, CookingUpgradeContainer, AutoCookingUpgradeContainer, PumpUpgradeContainer, FluidFilterContainer, XpPumpUpgradeContainer, TankUpgradeContainer, BatteryUpgradeContainer (no-op), ContentsFilteredUpgradeContainer, ContentsFilterLogicContainer, FilterLogicContainerBase
- MainSettingsContainer, MemorySettingsContainer, NoSortSettingsContainer, ItemDisplaySettingsContainer, TemplatePersistanceContainer, SettingsContainerMenu
- 6 SBP-specific upgrade containers under `sophisticatedbackpacks-.../common/gui/`

Every `handlePacket` that reads a count / slot / stack / recipe-index from the NBT without re-validating against current server state is a candidate for the same shape as H-27. This report does not sweep all 27; the modpack owner should.

Harness module: `V5SyncedContainerPoke` — lets you send arbitrary NBT to the currently-open menu by hotkey+console, for interactive fuzzing.

Suggested hardening: document the invariant "any server-side `handlePacket` implementation MUST re-derive authoritative state from slots / inventory / wrapper, and MUST NOT trust indices into cached lists without re-checking that the cache is still coherent." See `audit/patches/V5-syncedcontainer-hardening-notes.md`.

---

## Out of scope but worth flagging

- `BackpackStorage.get().getOrCreateBackpackContents(uuid)` in V1 is used across several handlers — every call site inherits the "any UUID creates a backpack entry" issue.
- `PlayerInventoryProvider.get().runOnBackpacks(player, ...)` is the common fanout; it iterates every backpack the player has across main inventory / Curios / Trinkets slots. Reach checks should be applied at the *payload* layer, not inside each upgrade, to avoid missing any.
