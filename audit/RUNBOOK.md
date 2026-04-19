# DupeFind pentest runbook

How to validate each finding on a Test Server you operate.

## Test environment prerequisites

1. A fresh, isolated Minecraft 1.21.1 Fabric server running this exact modpack.
   - Do **not** run this on your production Marks' server until findings are patched there. Spin up a copy.
   - `server.properties`: `online-mode=true`, `white-list=true`, whitelist only your two test accounts.
2. Two Minecraft accounts (A = attacker, B = victim). Both clients run the same mod list as the server, plus A installs `pentest-harness` from this repo.
3. Creative-mode or /give access on account A to quickly load test stacks.
4. Disable all other non-test players.
5. A persistent server console window — many findings are detectable as odd log lines.

## Build + install the harness

```bash
cd pentest-harness
./gradlew build
cp build/libs/dupefind-harness-0.1.0.jar "<account A client>/mods/"
```

Start the server, then both clients. Account B logs in, places a few shulker/chest/backpack items as bait, then stands still. Account A logs in last.

## Per-finding playbook

### H-27 — Crafting Upgrade dupe

1. On A: craft a Sophisticated Backpack and apply a Crafting Upgrade to it.
2. Give A any working recipe ingredients, e.g. 64 oak planks.
3. Open the backpack, open the crafting upgrade tab.
4. Fill the 2x2 or 3x3 matrix with planks → crafting table recipe now matched. Wait one tick. Confirm the result slot shows a crafting table.
5. Shift-click the planks *out* of the matrix back into the backpack. Result slot visually clears.
6. Press F7 (`/dupefind h27 0`). A line "[H-27] sent selectResult=0 to containerId=<n>" appears in chat.
7. **On a vulnerable server**: a crafting table appears in the result slot without inputs. Take it. The matrix stays empty. Repeat step 6–7.
8. Track the backpack's contents before and after to confirm inputs are never consumed.
9. **On a patched server** (after applying `patches/H-27-crafting-dupe.patch`): the result slot stays empty, no item is given.

Expected server console signal when patched: none — the patch fails closed silently. You can log at DEBUG inside the new `resultIndex >= fresh.size()` branch if you want audit trails.

### V1 — Read arbitrary backpack inventory

Setup: B places a Sophisticated Backpack at base camp. A reads the backpack item's UUID by picking up a second copy of the same backpack item (UUIDs persist across pickup when placed-then-broken) or by having B drop a backpack as an item and A walks past.

1. Stand A on the opposite end of the world from B's placed backpack (outside render distance).
2. `/dupefind v1 <uuid-of-Bs-backpack>` on A.
3. **Vulnerable**: A receives a `BackpackContentsPayload` containing the backpack's full inventory + upgrades NBT. The vanilla SBP client UI doesn't render it automatically, but you can verify by:
   - Adding a debug log mixin in the harness, or
   - Running `/dupefind v1 <uuid>` twice and watching server-side `BackpackStorage` metrics, or
   - Using the in-game F3 network debug overlay — the byte count of the response is proportional to the inventory size.
4. **Patched**: request is dropped, no response bytes.

### V2 — Inventory interaction reach bypass

1. B places a chest full of diamonds at pos `(X1, Y1, Z1)` in the overworld.
2. A equips a Sophisticated Backpack with an Inventory Input Upgrade (or whichever upgrade implements `IItemHandlerInteractionUpgrade` on your version).
3. A walks thousands of blocks away (outside `(X1, Y1, Z1)` render distance and outside chunk load radius from A).
4. A calls `/dupefind v2` after setting override pos — or edit the source to accept `/dupefind v2 <x> <y> <z> <face>` and target `(X1, Y1, Z1)`.
5. **Vulnerable**: chest contents start moving into A's backpack. If the target chunk was unloaded, it got force-loaded.
6. **Patched**: nothing happens, no chunk loading.

### V3 — Cross-map open another player's backpack

1. B enables `ANOTHER_PLAYER_CAN_OPEN` on at least one of their backpacks (normal SBP settings UI).
2. B flies 10,000 blocks away from A.
3. A presses F10. Harness picks the furthest loaded player and sends `AnotherPlayerBackpackOpenPayload(B.getId())`.
4. **Vulnerable**: A's screen opens B's backpack GUI.
5. **Patched**: nothing happens.

Caveat: the payload resolves B's entity id via `level.method_8469(id)` which only returns entities currently tracked by A's player context. If B is outside A's simulation distance, you'll get no effect — the exploit still works for "same dimension, loaded chunk or loaded entity" cases, which covers e.g. raid scenarios where the attacker is at base and the victim is a few hundred blocks away but still ticking.

### V4 — Tool-swap reach bypass

- F11 / `/dupefind v4 block`: with a Tool Swap Upgrade + a hoe in your backpack, point at dirt / crops / leaves outside reach. Observe the upgrade firing on those blocks without the vanilla right-click-on-block normally triggering.
- F12 / `/dupefind v4 entity`: with an Entity Tool Swap upgrade, stand more than 6 blocks from a hostile mob or another player and press the key.

Patched version just drops the packet when pos/entity is out of reach.

### V5 — Interactive fuzzer

Use `/dupefind synced list` to print the current menu's `containerId -> UpgradeContainer` map, then send arbitrary NBT with `/dupefind synced int <id> <key> <value>` (or `str` / `raw`). For each upgrade container, start with the keys listed in its `handlePacket`, then try boundary values (`-1`, `Integer.MAX_VALUE`, indices into caches you expect to be empty, repeated keys). Compare server-side backpack inventory snapshots before/after to spot phantom items.

## Reporting results

Fill in a short matrix after each run:

| Finding | Vulnerable? | Notes |
| --- | --- | --- |
| H-27 | yes/no | crafting table / other recipe used / measured inputs/outputs |
| V1   | yes/no | response bytes observed, UUID source |
| V2   | yes/no | target pos, distance, chunk load side-effect |
| V3   | yes/no | target player, distance |
| V4 block | yes/no | target pos, distance |
| V4 entity | yes/no | target entity, distance |

File upstream issues to the Sophisticated Backpacks Fabric repo with the patch diffs from `patches/` attached. H-27 specifically is a dupe and should be coordinated disclosure, not public-first.

## Cleanup

After you finish:

1. `./gradlew --stop` and nuke the test world.
2. Remove `dupefind-harness-*.jar` from every client. The mod is a live footgun — do not leave it installed.
3. If you patched your server copies, diff against the upstream jars with `diffoscope` to confirm nothing else changed.
