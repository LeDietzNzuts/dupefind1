# Minecraft Modpack Duplication-Exploit Audit

**When to invoke:** the user asks you to audit one or more Minecraft mods (Fabric, Forge, or NeoForge) for item-duplication bugs, storage exploits, or exploit-class vulnerabilities in a mod source tree or shipped JAR set.

**Do NOT** invoke for: anti-cheat evasion, client-side hacks, server exploits that bypass code (e.g. packet replay tools you would have to build). Refuse those.

**Deliverable:** a single consolidated markdown report, one section per hypothesis, every hypothesis flagged CONFIRMED / PROBABLE / POSSIBLE / NONE with concrete code citations (`file:line`). No ranked suggestions. No "could be." Either show the code path or mark NONE.

---

## 0. Hard rules (learned from 7 prior passes)

1. **Do not read code linearly.** Build the hypothesis list first (Section 3), then *seek* each hypothesis. Linear reading missed Easy Anvils Issue #132 for 6 sessions because the auditor was looking for reference-leak bugs and the actual bug was in the anvil operation state machine.
2. **Check public issue trackers on session 1, not session 7.** For every mod in scope: GitHub Issues, Modrinth comments, CurseForge comments, Reddit /r/feedthebeast, YouTube "<mod> dupe 1.21". Reported bugs save hours.
3. **Verify JAR bytecode against repo source.** Decompile shipped jars with CFR and diff against the audited sources. Mod authors sometimes ship patched versions with source lagging behind, or vice versa.
4. **Do not claim "no dupe exists."** Only claim "no dupe exists in the surfaces I traced using the shapes I know." Unknown-unknowns exist. Be honest about the limit.
5. **Report in one file and keep it current.** Append-only appendices for each new pass; don't fork reports. Let the user see the progression.
6. **Respect user rejection of findings.** If the user rejects a valid dupe (wrong item class, too narrow), do not re-pitch it. Acknowledge and move on.
7. **No fabrication under emotional pressure.** Users who want a dupe badly will push for speculative leads. Stating "it might work this way" when the code does not show that is worse than reporting nothing.

---

## 1. Phase order

```
0  — Scope & tooling
1  — Surface inventory
2  — External intel
3  — Hypothesis sweep  (runs Section 2 checklist)
4  — Cross-mod interaction matrix  (runs Section 3 matrix)
5  — JAR bytecode verification
6  — Report
```

Do not skip a phase. Do not move to phase N+1 until phase N is complete. Phase numbers map to section numbers offset by one (phase N ≡ § N-1 for the two execution phases) — this is intentional; the checklists are documented before the phases that consume them.

### Phase 0 — Scope & tooling

- Enumerate the exact mod list, exact versions, and game version.
- If only repo source is available, obtain the corresponding shipped jars. Decompile with CFR: `java -jar cfr.jar <mod>.jar --outputdir <out>`.
- Confirm vanilla game version (crucial for MC-xxxxx bug-tracker relevance).
- List loaded datapacks and known server-side configs (anti-dupe datapacks can neutralize vanilla bugs).

### Phase 1 — Surface inventory

For each mod, enumerate:
- Every `Item` subclass (especially damageable / container / usable)
- Every `Block` subclass with a `BlockEntity`
- Every custom `Menu` / `Container` / `Slot` subclass
- Every `Mixin` and its target method + injection point (HEAD / RETURN / INVOKE)
- Every recipe type + recipe JSON result count / remainder
- Every loot table modification (`LootTableEvents.MODIFY`, `AFTER_MODIFY`)
- Every event subscriber (Fabric API events, Architectury events, Puzzleslib)
- Every `ConfigValue` with an unsafe default (stack-size overrides, drop-multipliers, consumption-toggles)

Output: a table per mod with counts. This is your audit surface.

### Phase 2 — External intel

For each mod + version, search in parallel:
- GitHub Issues (`repo:author/mod is:issue dupe OR duplication OR exploit`)
- Modrinth → Issues tab + user comments
- CurseForge → Issues tab + comments
- Reddit: `site:reddit.com <mod> dupe`
- YouTube search `<mod> dupe <version>`
- Mojira for vanilla version bugs
- Minecraft Wiki "Block and item duplication" page for the version family

Log every hit even if it sounds minor. Public reports are the highest-signal source.

### Phase 3 — Hypothesis sweep

For every mod in scope, walk every bug-shape in Section 2 (A–Z). For each shape:

1. Run the shape's grep over the mod's source tree. Capture every hit (not just the suspicious-looking ones — negatives are evidence too).
2. For each hit, open the call-site and trace one hop in each direction (caller, callee). Stop when you can either (a) cite a line that proves the shape is closed, or (b) identify a code path that keeps the shape open.
3. Record the outcome against the shape:
   - **CONFIRMED** — end-to-end trace + reproduction steps
   - **PROBABLE** — end-to-end trace, no repro yet
   - **POSSIBLE** — ambiguous / missing info blocks a verdict
   - **NONE** — at least one `file:line` citation that closes the shape
4. Parallelise across shapes, not across mods. One shape at a time keeps the auditor's mental model consistent. Parallel greps (multiple `rg` invocations in one batch) are fine; parallel reading of unrelated shapes is not.
5. Never leave a shape undocumented. If the mod has no surface for the shape (e.g. no custom `Menu` → shape C N/A), write `N/A — no custom Menu subclass`.

Output of Phase 3 is one table per mod × shape with the verdict and at least one cited line.

### Phase 4 — Cross-mod interaction matrix

After every mod has its Phase 3 table, fill the matrix in Section 3 for every ordered pair `(A, B)` of in-scope mods. For each axis:

1. Enumerate the concrete intersection (e.g. "both mods subscribe to `PlayerBlockBreakEvents.AFTER`" — list both handlers).
2. For each concrete intersection, trace the combined code path. Record whether ordering, cancellation, or re-entry creates a vector that neither mod exhibits alone.
3. Treat cross-mod findings as first-class hypotheses — they get their own `H-N` entries in the final report.
4. An empty cell in the matrix must still be annotated `no overlap — shapes disjoint`.

### Phase 5 — JAR bytecode verification

Even if the repo has decompiled sources, the actually-shipped JAR is ground truth. Decompile the JAR with the same decompiler used for the repo source. Normalized diff (ignore formatting, ignore `instanceof` pattern-match rewrites, ignore `Boolean.valueOf` boxing). Any semantic difference means the shipped mod differs from audited source — stop and re-audit from the JAR version.

### Phase 6 — Report structure

- Executive summary table (one row per hypothesis, severity + one-liner)
- Per-hypothesis section: files reviewed, code trace with line numbers, reproduction steps if CONFIRMED/PROBABLE, verdict
- Cross-mod interaction matrix
- Appendix per audit pass (append-only, dated)
- Honest null-result section at the end if no CONFIRMED found

---

## 2. Bug-shape checklist (the core of this skill)

Every hypothesis below has (a) what to grep, (b) what to read, (c) the red flag signature. For each, check every in-scope mod.

**Mapping note.** Every source tree in this repo was decompiled from a shipped Fabric jar, so names are Yarn intermediary (`method_XXXX`, `class_XXXX`). When auditing a Forge/NeoForge source that uses Mojang-mapped names, translate each grep: e.g. `method_7972` → `copy`, `method_5431` → `setChanged`, `method_7861` → `finishUsingItem`, `class_1263` → `Container`, `class_1735` → `Slot`, `class_1706` → `AnvilMenu`, `class_9334.field_49650` → `DataComponents.CONTAINER`. A source file containing both mapping styles is a red flag of its own (someone ran two decompilers over different versions).

### A. Item reference leaks
- Grep: `\.method_7972\(\)|\.copy\(\)`, negative-check on sites that should copy
- Read: every place an `ItemStack` is passed to a new `Entity`, `BlockEntity`, or `ItemStackHandler`
- Red flag: a stack is put into a projectile/entity/container AND the source isn't immediately `shrink`/`setCount(0)`'d

### B. Save/load round-trip leaks
- Grep: `method_5431|setChanged|markDirty`, `method_11014|loadAdditional`, `method_11007|saveAdditional`, `method_38240|saveToItem`, `method_58683|applyComponentsFromItemStack`
- Read: every `BlockEntity.saveToItem` and its inverse in `BlockEntity.load`
- Red flag: save serializes an item list, load deserializes it, but the BE also drops items on break/replace — both drop paths fire

### C. Slot accounting bugs
- Grep: `extends class_1735`, `removeCount`, `onCraftedBy`
- Read: every custom `Slot`, every `Menu.quickMoveStack`, every `Menu.clicked` override
- Red flag: `removeCount` incremented in more than one path without matching reset, shift-click path that calls `onTake` AND transfers AND doesn't shrink source

### D. Kill / disconnect / death timing
- Grep: `@Inject.*(die|method_5643|dropAllDeathLoot|dropEquipment|method_14231|onDisconnect)`, `DamageSource`, `keepInventory`
- Read: every mixin at HEAD of a death/disconnect method, every `IServerPlayerOps` or `IEntityDataSaver` util
- Red flag: mixin kills player before the save path runs with custom timing (possible inventory desync between world-drop and disk-save)

### E. Block destroy / break
- Grep: `PlayerBlockBreakEvents`, `playerDestroy`, `playerWillDestroy`, `dropResources`, `getDrops`
- Read: every `Block.playerDestroy` override, the BEFORE/AFTER/CANCELED handlers in `PlayerBlockBreakEvents`
- Red flag: loot table exists AND `playerDestroy` explicitly spawns an ItemEntity → double drop. Silk-touch `saveToItem` gate that leaks NBT without the matching UUID (Lootr-class dupe).

### F. Recipe / crafting consumption
- Grep: `CraftingInput|class_9694`, `Recipe.assemble|method_8116`, `getRemainingItems|method_8111`
- Read: every custom `Recipe` subclass, every `assemble()` return
- Red flag: `assemble` returns `input.copy()` + original input is not consumed (vanilla `CraftingMenu.craftItem` handles consumption, but custom menus may not)

### G. Hopper / comparator / extractable storage
- Grep: `class_1263|Container\b`, `class_3829|WorldlyContainer`, `method_5439|method_5438|method_5441|method_5434`
- Read: every mod `BlockEntity` that implements `Container` or `WorldlyContainer`, every `getSlotsForFace` / `canPlaceItemThroughFace` / `canTakeItemThroughFace`
- Red flag: `removeItem(int slot, int amount)` returns a stack without matching internal size update; `canTakeItemThroughFace` returns true for an output slot that's mid-transformation

### H. Entity container / dismount
- Grep: `method_5650|remove\(.*RemovalReason`, `RemovalReason.KILLED|DISCARDED|CHANGED_DIMENSION|UNLOADED_TO_CHUNK`
- Read: every custom `Entity` with a `Container` field, every `tick` / `remove` override
- Red flag: `remove(RemovalReason.X)` conditionally drops container; if unmounted via a non-destroy reason, container persists in NBT and respawns on reload

### I. Chunk unload / entity despawn timing
- Grep: `method_18375|onChunkUnloaded`, `class_1923|ChunkPos`, `LoadedChunks|chunkLoader`
- Read: anything that ticks across chunk boundaries, any item-entity spawn in a BE tick method
- Red flag: ItemEntity spawned by a BE tick after the chunk is flagged for unload, causing either loss or duplication-via-save-race

### J. Packet race / client-authoritative
- Grep: `PacketByteBuf|FriendlyByteBuf|CustomPayload|C2SPlayChannelEvents`
- Read: every C2S packet handler; look for missing server-side state validation
- Red flag: packet handler takes an int/UUID from the client and uses it as a slot index / entity ID without re-validation; interaction-distance not re-checked; main-hand item not confirmed to still be the claimed item

### K. Creative mode interactions
- Grep: `CreativeModeTab|class_1761|method_47324`, `middleClick|pickBlock`
- Read: every `CreativeModeTab.Output.accept` call with a pre-filled NBT stack
- Red flag: a creative-tab entry contains a container item already populated (rare — Epic Knights MedievalBag default) which can be `/give`-dispensed and interacts oddly with the normal open flow

### L. XP / enchant / anvil
- Grep: `AnvilMenu|class_1706`, `createResult|method_7595`, `EnchantmentMenu|class_1718`, `RenameAndRepair`
- Read: every mixin into `AnvilMenu.createResult`, every mod that adds extended anvil operations
- Red flag: name change + enchant change both trigger separate consume paths; if they run simultaneously one of them fails to consume → Easy Anvils Issue #132

### M. Food / consumable
- Grep: `finishUsingItem|method_7861`, `FoodData|method_7344`, `class_1844\b|PotionContents`
- Read: every custom Food item, every `finishUsingItem` override
- Red flag: `finishUsingItem` returns a stack WITHOUT shrinking the original (vanilla bowl/bottle-return pattern done wrong)

### N. Fire / explosion / despawn-resistance
- Grep: `setInvulnerable|method_5684|fireImmune`, `Explosion.explode|method_8550`, `getDestroyProgress|method_7866`
- Red flag: custom Item with `fireImmune=true` AND `damageable=false` → persistent dropped item entity that can be re-picked across server restarts with normal ground-item regen

### O. Cross-mod library hooks
- Grep: `LootTableEvents|LootEvent|ModLoot`, `Architectury|dev.architectury.event`, `puzzleslib|fuzs`
- Red flag: two mods modify the same loot table via AFTER_MODIFY; late-registered modifier re-adds items already present

### P. Data-driven
- Read: every recipe JSON `"count"` field, every loot table entry without `"minecraft:set_count"`, every tag override
- Red flag: recipe result count > reasonable given inputs; loot table with `"rolls": {"min": 1, "max": N}` where N is unusually high; tag JSON that adds a valuable item to a broadly-accepted input tag (e.g., `c:ingots`)

### Q. Storage containers (bundles / bags / backpacks)
- Grep: `class_9334.field_49650|CONTAINER`, `class_9288|BundleContents`, `BundleItem|ContainerItem`
- Read: every item implementing a container-like `use`/`onRightClickAir` handler
- Red flag: container-item `use` method doesn't check the item's own slot context; serializing CONTAINER component before clearing → disk contains items that were already dumped

### R. NBT preservation on block pick-up
- Read: every `Block.onRemove`, `Block.getCloneItemStack`, any mod that adds silk-touch-like behavior
- Red flag: BE has `saveToItem` that gates one field (like a UUID) but not the contents → pick up, preserve contents, place, get fresh UUID → fresh loot instance

### S. Sidedness violations
- Grep: `isClientSide|method_8608|isClient`
- Read: every method that checks client-side and early-returns
- Red flag: drop code runs on both sides; consume code runs only server-side → client predicts drops, server commits, net drops = 2

### T. Event subscriber multiplicity
- Grep: `EVENT.register|addListener`
- Red flag: same event handler registered in multiple entrypoints (fabric main + client + server); cancellation not propagated; post-cancel handlers still fire

### U. Villager trades / wandering trader
- Grep: `TradeOfferFabric|VillagerTradesEvent|CustomTradeOffer`, `class_3853$class_4161|ItemsForEmeralds`
- Read: every mod that adds a trade; check if the trade's consume list matches the input list
- Red flag: trade with zero emerald cost, trade result item has NBT that the input doesn't, trade lock not respected for a custom trade

### V. Farming / crop drops
- Grep: `method_9568|use` on CropBlock subclasses, `bonemeal|BoneMealItem`, `randomTick`
- Read: every custom `CropBlock`, every `right-click harvest` handler (Farmers Delight adds one)
- Red flag: right-click harvest that doesn't reset age, bone-meal on mature custom crop that re-drops, fortune applied to hand-broken crops that weren't intended to

### W. Advancement / loot-table reward hooks
- Grep: `AdvancementEvent|class_8779|AdvancementRewards`, `method_705(\(|3)`, `grantCriterion`
- Read: every mod that grants an advancement with a `loot` reward, every `AdvancementHolder` registered at data-gen
- Red flag: reward loot table gets rolled each time the criterion fires AND the criterion can re-fire (not one-shot); reward resolved client-side then mirrored server-side

### X. Machine output slot + hopper race
- Grep: `class_2371|NonNullList`, `setItem` combined with output-slot index, `class_1277|SimpleContainer`, `takeFromSide`
- Read: every machine-like BE with an output slot read by hoppers; every `tick()` that replaces the output slot stack
- Red flag: hopper pulls from output slot during the same tick the BE writes a fresh result — sans slot lock, the pulled stack and the new result can both exist

### Y. Mount / dismount inventory sync
- Grep: `startRiding|method_5804`, `stopRiding|method_5848`, `passengers|method_5685`, `dismountTo`
- Read: every custom `Entity` that holds an inventory exposed to the rider (ships, mounts, carts)
- Red flag: dismount writes rider inventory back to the mount BE on the client but not the server (or vice versa); the mount persists to NBT with both copies

### Z. Damage/break tool drop
- Grep: `hurtAndBreak|method_7956`, `setDamageValue|method_7974`, `ItemStack.isDamageableItem|method_7963`
- Read: every custom tool `use`/`mineBlock`, every mixin into `LivingEntity.hurtAndBreak`
- Red flag: tool "break" fires a drop event (Epic Knights armor shards, musket parts) before the stack is actually shrunk to zero; one last use can proc both the break drop and the normal durability-0 drop

---

## 3. Cross-mod interaction matrix

For every pair of in-scope mods, check:

| Axis | Example question |
|------|------------------|
| Container-class | Does mod A wrap mod B's BE via a common `Container` interface? |
| Event order | Does mod A and mod B both subscribe to the same event? Who fires first? |
| Loot modification | Do both mods modify the same loot table? In what order? |
| NBT component | Does one mod write a DataComponent that another mod reads? |
| Mixin target | Do both mods mixin the same vanilla class? At what injection point? |
| Recipe result | Does one mod add a recipe whose input is another mod's item? |
| Tag overlap | Does one mod add a valuable item to a tag another mod accepts as input? |

For each positive crossover, trace both code paths together.

---

## 4. Anti-patterns (things that wasted time in prior sessions)

- **"Read every file" approach.** Wastes hours and misses anything whose signature doesn't match what the auditor visualizes while reading. Replace with hypothesis-seeking.
- **Declaring NONE without a code trace.** Every NONE verdict needs at least one file+line citation showing why the vector is closed. Else it's a guess.
- **Fixing the report mid-audit.** Don't restructure the report three times. Append-only.
- **Ranked "what should we try next" lists sent to the user.** The user asked for findings, not menus. Execute, don't propose.
- **"It might work this way" claims.** Either show the code path or say NONE.
- **Ignoring public issue trackers.** Session 1 task: grep GitHub Issues for each mod.
- **Trusting repo source without bytecode verification.** Always decompile the shipped jar.
- **Re-pitching a rejected finding.** If the user says a dupe isn't useful, accept it. Do not try to talk them into it.

---

## 5. Reporting standard

Every hypothesis section must contain:

```
### H-N <shape>: <one-line description>

**Files reviewed:**
- path/to/File.java
- path/to/Other.java

**Code trace:**
[numbered walk-through, each step citing file:line]

**Reproduction (if CONFIRMED/PROBABLE):**
[player actions, exact]

**Verdict:** CONFIRMED | PROBABLE | POSSIBLE | NONE
**Rationale:** [one sentence tying verdict to the trace]
```

Executive summary table maps one row per H-N.

---

## 6. Known 1.21.x reference dupes (updated 2026-04)

- **MC-59471** — tripwire hook + string dupe, vanilla Java, still working 1.21.1 (PaperMC maintainer confirmed Feb 2025). Non-material beyond string/tripwire.
- **Easy Anvils Issue #132** — rename + enchant simultaneously dupes the input book. Confirmed on EasyAnvils v21.1.0-1.21.1-Fabric. Book-only; does not generalize to items.
- **Bundle dupes (various)** — historically present in 1.21.0, most patched. Check current version specifically.
- **Lootr pre-1.21.1 UUID-seed dupe** — in versions before the UUID-per-break fix, silk-touching a Lootr chest preserved `lootr_id` so placing it regenerated a per-player-fresh table. Verify the current jar's `saveToItem` gates on UUID *and* clears `lootr_id`.

If none of the mods in the pack intersect with these, the probability of a general material dupe in that specific pack is low but nonzero.

---

## 6a. Per-mod priors for this repo

The pack in this repo is Fabric 1.21.1 with CFR-decompiled sources in `<mod>-output/` or `<mod>_source/`. Shipped jars are mirrored in `jarfile/` — **always** diff decompiled-source against a fresh CFR run of the matching jar (Phase 5) before reporting on that mod.

| Mod | Folder | First suspect shapes | Notes |
|-----|--------|----------------------|-------|
| CombatLog | `CombatLog_source/` | D (death/disconnect), S (sidedness), T (event multiplicity) | Holds players in-world post-disconnect. Any mixin at HEAD of `onDisconnect`/`die` is high-priority. |
| EasyAnvils | `EasyAnvils-output/` | L (anvil), B (save/load round-trip) | Known Issue #132 lives here; check if the shipped jar has the advertised fix — don't re-diagnose the same bug. |
| Farmers Delight | `FarmersDelight_source/` | V (crops), F (recipe), B (cooking pot BE round-trip), G (comparator on cooking pot) | Right-click harvest + cooking-pot container + tool-rack BE are the three surfaces. Verify `saveToItem` on both BEs. |
| OneKeyMiner (OKM) | `OKM_source/` | E (block destroy), S (sidedness), J (packet race) | Veinmine mods historically spawn drops server-side while also letting the client predict — check the break-chain loop carefully. |
| Epic Knights | `epic-knights-output/` | K (creative tab pre-filled), Q (MedievalBag container item), Z (armor break drops) | MedievalBag is a container item — audit its `use`, component-serialization, and in-slot-context gating. |
| Lootr | `lootr-output/` | E (break), R (NBT preserved on pick-up), O (loot-table modify chain) | Highest prior for dupes historically. Re-verify the UUID-per-break gate on every pass. |
| MusketMod | `musketmod-output/` | F (reload consume path), M (right-click `use`), S (sidedness) | Reload pulls a powder + ball from inventory — verify both are `shrink`'d before the loaded component is written. |
| SmallShips | `smallships-output/` | H (entity container), Y (mount/dismount), T (multi-entrypoint events) | `ShipContainerMenu` + `GroundCannonEntity` + ship entity all hold containers; three surfaces for the same shape. |

---

## 7. Tooling recipes

All commands assume `/home/.../disassemblydupefinder` as cwd. Prefer `rg` over `grep` for speed; quote patterns.

**Decompile a shipped jar with CFR** (bytecode ground-truth for Phase 5):
```
java -jar ~/tools/cfr.jar jarfile/<mod>.jar --outputdir /tmp/<mod>-cfr
diff -r --brief <mod>-output/ /tmp/<mod>-cfr/ | grep -v -E '\.(txt|json|png|nbt|mcmeta)$'
```
Any `.java` line in the diff → stop, re-audit from `/tmp/<mod>-cfr/`.

**Hypothesis sweep starter greps** (copy-paste as a single pass):
```
rg -n 'method_7972\(\)|\.copy\(\)'                                  # A
rg -n 'saveAdditional|loadAdditional|saveToItem|applyComponentsFromItemStack'  # B
rg -n 'extends class_1735|removeCount|onCraftedBy'                   # C
rg -n '@Inject.*(die|method_5643|dropAllDeathLoot|onDisconnect)'     # D
rg -n 'playerDestroy|playerWillDestroy|dropResources|getDrops'       # E
rg -n 'getRemainingItems|method_8111|method_8116'                    # F
rg -n 'class_1263|WorldlyContainer|method_5438|method_5441'          # G
rg -n 'RemovalReason\.(KILLED|DISCARDED|CHANGED_DIMENSION|UNLOADED)' # H
rg -n 'onChunkUnloaded|LoadedChunks'                                 # I
rg -n 'CustomPayload|C2SPlayChannelEvents|FriendlyByteBuf'           # J
rg -n 'CreativeModeTab|middleClick'                                  # K
rg -n 'class_1706|createResult|method_7595'                          # L
rg -n 'finishUsingItem|method_7861'                                  # M
rg -n 'fireImmune|setInvulnerable|method_5684'                       # N
rg -n 'LootTableEvents|AFTER_MODIFY'                                 # O
rg -n 'BundleContents|class_9288|CONTAINER'                          # Q
rg -n 'getCloneItemStack|onRemove'                                   # R
rg -n 'isClientSide|method_8608|isClient'                            # S
rg -n 'EVENT\.register|addListener'                                  # T
rg -n 'TradeOfferFabric|ItemsForEmeralds|class_3853'                 # U
rg -n 'extends CropBlock|bonemeal|randomTick'                        # V
rg -n 'AdvancementRewards|grantCriterion'                            # W
rg -n 'hurtAndBreak|method_7956'                                     # Z
```
Save each command's output under `audit_runs/<date>/<shape>.txt` so the next pass can diff the grep output and spot shape changes from one version to the next.

**Consolidated report location.** Write the report to `AUDIT_REPORT.md` at repo root, append-only. Each new pass adds a dated appendix at the bottom; never rewrite prior sections. If the file doesn't exist, create it with the template from Section 5.

---

## 8. Invocation checklist (run through before reporting complete)

- [ ] Every mod enumerated (A-Z categories touched for each)
- [ ] JAR bytecode vs repo source diffed (no semantic differences)
- [ ] Every custom `Slot`, `Menu`, `Container`, `BlockEntity`, `Item`-with-use, `Recipe` subclass traced
- [ ] Every mixin at HEAD of a save/destroy/die/disconnect/break method traced
- [ ] Cross-mod interaction matrix filled (every pair)
- [ ] Every public issue tracker searched for every in-scope mod
- [ ] Every `ConfigValue` with a non-boolean default inspected for unsafe numeric values
- [ ] Final executive summary table accurate and updated
- [ ] Honest null-result paragraph at end if no CONFIRMED found

If any box unchecked, audit is not complete.
