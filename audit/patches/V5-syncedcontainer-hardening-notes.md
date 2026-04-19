# V5: SyncContainerClientDataPayload hardening notes

This is not a single-line bug. It is a design convention that needs to be enforced across every `ISyncedContainer#handlePacket(class_2487)` implementation in SCore and SBP. The same pattern that produced H-27 can produce more dupes if future `handlePacket` implementations trust client-supplied indices, counts, or stacks.

## Invariants to enforce in `handlePacket` implementations

1. **Never trust a client-supplied index into a server-side cache without re-deriving the cache from current authoritative state.**
   - Bad: `this.craftingResultSlot.method_7673(this.matchedCraftingResults.get(data.method_10550("selectResult")))`
   - Good: recompute `matchedCraftingResults` from the current craft matrix *inside* the handler, then consult the result set.

2. **Never trust a client-supplied stack size or slot index to indicate "how much to consume/produce"** — use it only to *select* an action, then let the server derive the quantity from actual inventory/wrapper state.

3. **Bound all integer inputs.** `class_2487#method_10550` silently returns 0 on wrong-type/missing keys, which is better than NPE but can still land on an out-of-bounds index if you don't guard.

4. **Do not perform side effects proportional to input size.** `handlePacket` should be amortized O(1) on the input NBT. Large tag-lists in the payload must not translate to large server work.

5. **Fail closed on unknown keys.** If `data` has no recognised top-level key, do nothing — do not fall through to a default action.

## Priority re-audit list

High priority (takes/consumes/produces items; directly materially valuable to an attacker):

- `CraftingUpgradeContainer` ← H-27 (fixed by this patchset).
- `StonecutterRecipeContainer` (reads `selectedRecipeIndex`; currently calls `updateRecipeResultSlot` which re-derives from `inputInventory.method_5438(0)` — safe today, keep it that way).
- `CompactingUpgradeContainer`, `CookingUpgradeContainer`, `AutoCookingUpgradeContainer`, `PumpUpgradeContainer`, `XpPumpUpgradeContainer`.
- `JukeboxUpgradeContainer` (disc handling; potential double-spawn if client-driven).
- `FeedingUpgradeContainer` (may trigger feeding actions that consume inventory).

Medium priority (state toggles, can enable attacks indirectly):

- All `FilterLogicContainerBase` subclasses (`FilterUpgradeContainer`, `ContentsFilteredUpgradeContainer`, `ContentsFilterLogicContainer`, `VoidUpgradeContainer`, `MagnetUpgradeContainer`, `FluidFilterContainer`).
- All settings containers under `sophisticatedcore-.../settings/`.

Low priority (already empty or trivially safe):

- `BatteryUpgradeContainer`, `TankUpgradeContainer` (`handlePacket` is no-op).
- `MainSettingsContainer` / `MemorySettingsContainer` / `NoSortSettingsContainer` / `ItemDisplaySettingsContainer` (boolean toggles).

## Fuzzing recipe

The pentest-harness ships `V5SyncedContainerPoke`, which lets you fire arbitrary NBT at the currently-open menu. To fuzz:

1. Open the menu whose `handlePacket` you want to probe.
2. In the harness console (in-game `/dupefind pokeSynced <key> <value>`), push the tags the handler reads — for each upgrade container grep its `handlePacket` for `data.method_10545("...")` and `data.method_10550/10558/10577("...")` to enumerate keys.
3. Specifically try: negative integers, `Integer.MAX_VALUE`, index 0 when the cache is known to be empty, the same key twice in a single tick, empty strings, strings longer than expected.
4. Compare server-side inventory snapshots before/after to detect phantom items.
