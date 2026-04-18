package net.p3pp3rf1y.sophisticatedbackpacks.common;

import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.class_1266;
import net.minecraft.class_1282;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1322;
import net.minecraft.class_1324;
import net.minecraft.class_1542;
import net.minecraft.class_1548;
import net.minecraft.class_1588;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1826;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_1935;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_3763;
import net.minecraft.class_5134;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_9288;
import net.minecraft.class_9334;
import net.minecraft.class_9636;
import net.minecraft.class_1322.class_1323;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.JukeboxUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.JukeboxUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RandHelper;
import net.p3pp3rf1y.sophisticatedcore.util.WeightedElement;

public class EntityBackpackAdditionHandler {
   private static final int MAX_DIFFICULTY = 3;
   private static final float MAX_LOCAL_DIFFICULTY = 6.75F;
   private static final String SPAWNED_WITH_BACKPACK = "spawnedWithBackpack";
   private static final String SPAWNED_WITH_JUKEBOX_UPGRADE = "sophisticatedbackpacks:jukebox";
   private static final List<WeightedElement<class_1792>> HELMET_CHANCES = List.of(
      new WeightedElement(1, class_1802.field_22027),
      new WeightedElement(3, class_1802.field_8805),
      new WeightedElement(9, class_1802.field_8862),
      new WeightedElement(27, class_1802.field_8743),
      new WeightedElement(81, class_1802.field_8267)
   );
   private static final List<WeightedElement<class_1792>> LEGGINGS_CHANCES = List.of(
      new WeightedElement(1, class_1802.field_22029),
      new WeightedElement(3, class_1802.field_8348),
      new WeightedElement(9, class_1802.field_8416),
      new WeightedElement(27, class_1802.field_8396),
      new WeightedElement(81, class_1802.field_8570)
   );
   private static final List<WeightedElement<class_1792>> BOOTS_CHANCES = List.of(
      new WeightedElement(1, class_1802.field_22030),
      new WeightedElement(3, class_1802.field_8285),
      new WeightedElement(9, class_1802.field_8753),
      new WeightedElement(27, class_1802.field_8660),
      new WeightedElement(81, class_1802.field_8370)
   );
   private static final Map<class_1792, Float> dropChanceMultiplier = Map.of(
      (class_1792)ModItems.BACKPACK.get(),
      1.0F,
      (class_1792)ModItems.COPPER_BACKPACK.get(),
      1.25F,
      (class_1792)ModItems.IRON_BACKPACK.get(),
      1.5F,
      (class_1792)ModItems.GOLD_BACKPACK.get(),
      3.0F,
      (class_1792)ModItems.DIAMOND_BACKPACK.get(),
      4.5F,
      (class_1792)ModItems.NETHERITE_BACKPACK.get(),
      6.0F
   );
   private static final List<WeightedElement<EntityBackpackAdditionHandler.BackpackAddition>> BACKPACK_CHANCES = List.of(
      new WeightedElement(
         1,
         new EntityBackpackAdditionHandler.BackpackAddition(
            (class_1792)ModItems.NETHERITE_BACKPACK.get(), 4, HELMET_CHANCES.subList(0, 1), LEGGINGS_CHANCES.subList(0, 1), BOOTS_CHANCES.subList(0, 1)
         )
      ),
      new WeightedElement(
         5,
         new EntityBackpackAdditionHandler.BackpackAddition(
            (class_1792)ModItems.DIAMOND_BACKPACK.get(), 3, HELMET_CHANCES.subList(0, 2), LEGGINGS_CHANCES.subList(0, 2), BOOTS_CHANCES.subList(0, 2)
         )
      ),
      new WeightedElement(
         25,
         new EntityBackpackAdditionHandler.BackpackAddition(
            (class_1792)ModItems.GOLD_BACKPACK.get(), 2, HELMET_CHANCES.subList(1, 3), LEGGINGS_CHANCES.subList(1, 3), BOOTS_CHANCES.subList(1, 3)
         )
      ),
      new WeightedElement(
         125,
         new EntityBackpackAdditionHandler.BackpackAddition(
            (class_1792)ModItems.IRON_BACKPACK.get(), 1, HELMET_CHANCES.subList(2, 4), LEGGINGS_CHANCES.subList(2, 4), BOOTS_CHANCES.subList(2, 4)
         )
      ),
      new WeightedElement(
         250,
         new EntityBackpackAdditionHandler.BackpackAddition(
            (class_1792)ModItems.COPPER_BACKPACK.get(), 1, HELMET_CHANCES.subList(2, 4), LEGGINGS_CHANCES.subList(3, 5), BOOTS_CHANCES.subList(3, 5)
         )
      ),
      new WeightedElement(
         625,
         new EntityBackpackAdditionHandler.BackpackAddition(
            (class_1792)ModItems.BACKPACK.get(), 0, HELMET_CHANCES.subList(3, 5), LEGGINGS_CHANCES.subList(3, 5), BOOTS_CHANCES.subList(3, 5)
         )
      )
   );
   private static final Map<Integer, List<WeightedElement<EntityBackpackAdditionHandler.BackpackAddition>>> DIFFICULTY_BACKPACK_CHANCES = Map.of(
      0, BACKPACK_CHANCES, 1, BACKPACK_CHANCES.subList(1, 5), 2, BACKPACK_CHANCES.subList(2, 5)
   );
   private static List<class_1792> musicDiscs = null;
   private static final List<EntityBackpackAdditionHandler.ApplicableEffect> APPLICABLE_EFFECTS = List.of(
      new EntityBackpackAdditionHandler.ApplicableEffect(List.of(class_1294.field_5907, class_1294.field_5924), 1),
      new EntityBackpackAdditionHandler.ApplicableEffect(class_1294.field_5918),
      new EntityBackpackAdditionHandler.ApplicableEffect(class_1294.field_5898),
      new EntityBackpackAdditionHandler.ApplicableEffect(class_1294.field_5914),
      new EntityBackpackAdditionHandler.ApplicableEffect(class_1294.field_5904),
      new EntityBackpackAdditionHandler.ApplicableEffect(class_1294.field_5910)
   );

   private EntityBackpackAdditionHandler() {
   }

   static void addBackpack(class_1588 monster, class_1936 level, class_1266 difficultyInstance) {
      class_5819 rnd = level.method_8409();
      if (Config.SERVER.entityBackpackAdditions.canWearBackpack(monster.method_5864())
         && rnd.method_43048((int)(1.0 / (Double)Config.SERVER.entityBackpackAdditions.chance.get())) == 0
         && !(monster instanceof class_3763 raider && raider.method_16478() != null)) {
         float localDifficulty = difficultyInstance.method_5457();
         int index = Ints.constrainToRange(
            (int)Math.floor(DIFFICULTY_BACKPACK_CHANCES.size() / 6.75F * localDifficulty - 0.1F), 0, DIFFICULTY_BACKPACK_CHANCES.size()
         );
         RandHelper.getRandomWeightedElement(rnd, DIFFICULTY_BACKPACK_CHANCES.get(index))
            .ifPresent(
               backpackAddition -> {
                  class_1799 backpack = new class_1799(backpackAddition.getBackpackItem());
                  int minDifficulty = backpackAddition.getMinDifficulty();
                  int difficulty = Math.max(minDifficulty, rnd.method_43048(4));
                  equipBackpack(
                     monster,
                     backpack,
                     difficulty,
                     Boolean.TRUE.equals(Config.SERVER.entityBackpackAdditions.playJukebox.get()) && rnd.method_43048(4) == 0,
                     level,
                     rnd
                  );
                  applyPotions(monster, difficulty, minDifficulty, rnd);
                  raiseHealth(monster, minDifficulty);
                  if (Boolean.TRUE.equals(Config.SERVER.entityBackpackAdditions.equipWithArmor.get())) {
                     equipArmorPiece(monster, rnd, minDifficulty, backpackAddition.getHelmetChances(), class_1304.field_6169, level, difficultyInstance);
                     equipArmorPiece(monster, rnd, minDifficulty, backpackAddition.getLeggingsChances(), class_1304.field_6172, level, difficultyInstance);
                     equipArmorPiece(monster, rnd, minDifficulty, backpackAddition.getBootsChances(), class_1304.field_6166, level, difficultyInstance);
                  }

                  monster.method_5780("spawnedWithBackpack");
               }
            );
      }
   }

   private static void equipArmorPiece(
      class_1588 monster,
      class_5819 rnd,
      int minDifficulty,
      List<WeightedElement<class_1792>> armorChances,
      class_1304 slot,
      class_1936 level,
      class_1266 difficultyInstance
   ) {
      RandHelper.getRandomWeightedElement(rnd, armorChances)
         .ifPresent(
            armorPiece -> {
               if (armorPiece != class_1802.field_8162) {
                  class_1799 armorStack = new class_1799(armorPiece);
                  if (rnd.method_43048(6 - minDifficulty) == 0) {
                     float additionalDifficulty = difficultyInstance.method_5458();
                     int enchantmentLevel = (int)(5.0F + additionalDifficulty * 18.0F + minDifficulty * 6);
                     class_1890.method_60133(
                        rnd,
                        armorStack,
                        enchantmentLevel,
                        level.method_30349(),
                        level.method_30349().method_30530(class_7924.field_41265).method_40266(class_9636.field_51548)
                     );
                  }

                  monster.method_5673(slot, armorStack);
               }
            }
         );
   }

   private static void equipBackpack(class_1588 monster, class_1799 backpack, int difficulty, boolean playMusicDisc, class_1936 level, class_5819 rnd) {
      getSpawnEgg(monster.method_5864()).ifPresent(egg -> {
         IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);
         wrapper.setColors(egg.method_8016(0) | 0xFF000000, egg.method_8016(1) | 0xFF000000);
         setLoot(monster, wrapper, difficulty, level);
         if (playMusicDisc) {
            wrapper.getInventoryHandler();
            if (wrapper.getUpgradeHandler().getSlotCount() > 0) {
               monster.method_5780("sophisticatedbackpacks:jukebox");
               addJukeboxUpgradeAndRandomDisc(level.method_8409(), wrapper, rnd);
            }
         }
      });
      monster.method_5673(class_1304.field_6174, backpack);
      monster.method_5946(class_1304.field_6174, 0.0F);
   }

   private static void addJukeboxUpgradeAndRandomDisc(class_5819 random, IStorageWrapper w, class_5819 rnd) {
      boolean advancedJukebox = random.method_43057() < 0.25;
      w.getUpgradeHandler()
         .setStackInSlot(0, new class_1799(advancedJukebox ? (class_1935)ModItems.ADVANCED_JUKEBOX_UPGRADE.get() : (class_1935)ModItems.JUKEBOX_UPGRADE.get()));
      Iterator<JukeboxUpgradeWrapper> it = w.getUpgradeHandler().getTypeWrappers(JukeboxUpgradeItem.TYPE).iterator();
      if (it.hasNext()) {
         JukeboxUpgradeWrapper wrapper = it.next();
         int numberOfDiscs = advancedJukebox ? random.method_43048(wrapper.getDiscInventory().getSlotCount() / 3) + 1 : 1;

         for (int i = 0; i < numberOfDiscs; i++) {
            Transaction ctx = Transaction.openOuter();

            try {
               wrapper.getDiscInventory().insertSlot(i, ItemVariant.of((class_1935)getMusicDiscs().get(rnd.method_43048(getMusicDiscs().size()))), 1L, ctx);
               ctx.commit();
            } catch (Throwable var12) {
               if (ctx != null) {
                  try {
                     ctx.close();
                  } catch (Throwable var11) {
                     var12.addSuppressed(var11);
                  }
               }

               throw var12;
            }

            if (ctx != null) {
               ctx.close();
            }
         }
      }
   }

   private static List<class_1792> getMusicDiscs() {
      if (musicDiscs == null) {
         class_7923.field_41178.method_40266(ConventionalItemTags.MUSIC_DISCS).ifPresentOrElse(records -> {
            Set<String> blockedDiscs = new HashSet<>((Collection<? extends String>)Config.SERVER.entityBackpackAdditions.discBlockList.get());
            musicDiscs = new ArrayList<>();
            records.forEach(musicDisc -> {
               if (!blockedDiscs.contains(musicDisc.method_55840())) {
                  musicDiscs.add((class_1792)musicDisc.comp_349());
               }
            });
         }, () -> musicDiscs = Collections.emptyList());
      }

      return musicDiscs;
   }

   private static void raiseHealth(class_1588 monster, int minDifficulty) {
      if (!Boolean.FALSE.equals(Config.SERVER.entityBackpackAdditions.buffHealth.get())) {
         class_1324 maxHealth = monster.method_5996(class_5134.field_23716);
         if (maxHealth != null) {
            double healthAddition = maxHealth.method_6201() * minDifficulty;
            if (healthAddition > 0.1) {
               maxHealth.method_26837(new class_1322(SophisticatedBackpacks.getRL("backpack_bearer_health_bonus"), healthAddition, class_1323.field_6328));
            }

            monster.method_6033(monster.method_6063());
         }
      }
   }

   private static Optional<class_1826> getSpawnEgg(class_1299<?> entityType) {
      return Optional.ofNullable((class_1826)class_1826.field_8914.get(entityType));
   }

   private static void setLoot(class_1588 monster, IBackpackWrapper backpackWrapper, int difficulty, class_1936 level) {
      MinecraftServer server = level.method_8503();
      if (server != null) {
         if (Boolean.TRUE.equals(Config.SERVER.entityBackpackAdditions.addLoot.get())) {
            addLoot(monster, backpackWrapper, difficulty);
         }
      }
   }

   private static void applyPotions(class_1588 monster, int difficulty, int minDifficulty, class_5819 rnd) {
      if (Boolean.TRUE.equals(Config.SERVER.entityBackpackAdditions.buffWithPotionEffects.get())) {
         RandHelper.getNRandomElements(APPLICABLE_EFFECTS, difficulty + 2).forEach(applicableEffect -> {
            int amplifier = Math.min(Math.max(minDifficulty, rnd.method_43048(difficulty + 1)), applicableEffect.getMaxAmplifier());
            monster.method_6092(new class_1293(applicableEffect.getRandomEffect(rnd), 36000, amplifier));
         });
      }
   }

   private static void addLoot(class_1588 monster, IBackpackWrapper backpackWrapper, int difficulty) {
      if (difficulty != 0) {
         Config.SERVER.entityBackpackAdditions.getLootTableName(monster.method_5864()).ifPresent(lootTableName -> {
            float lootFactor = difficulty / 3.0F;
            backpackWrapper.setLoot(lootTableName, lootFactor);
         });
      }
   }

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

   private static void putJukeboxItemsInContainerAndRemoveStorageUuid(class_1282 source, class_1309 mob, class_1799 backpack) {
      if (mob.method_5752().remove("sophisticatedbackpacks:jukebox")) {
         List<class_1799> inventoryItems = new ArrayList<>();
         IBackpackWrapper backpackwrapper = BackpackWrapper.fromStack(backpack);
         backpackwrapper.getUpgradeHandler()
            .getTypeWrappers(JukeboxUpgradeItem.TYPE)
            .forEach(wrapper -> InventoryHelper.iterate(wrapper.getDiscInventory(), (slot, stack) -> {
               if (!stack.method_7960()) {
                  Transaction ctx = Transaction.openOuter();

                  try {
                     long extracted = wrapper.getDiscInventory().extractSlot(slot, ItemVariant.of(stack), stack.method_7947(), ctx);
                     inventoryItems.add(stack.method_46651((int)extracted));
                     ctx.commit();
                  } catch (Throwable var8) {
                     if (ctx != null) {
                        try {
                           ctx.close();
                        } catch (Throwable var7) {
                           var8.addSuppressed(var7);
                        }
                     }

                     throw var8;
                  }

                  if (ctx != null) {
                     ctx.close();
                  }
               }
            }));
         InventoryHelper.iterate(backpackwrapper.getUpgradeHandler(), (slot, stack) -> {
            if (!stack.method_7960()) {
               Transaction ctx = Transaction.openOuter();

               try {
                  long extracted = backpackwrapper.getUpgradeHandler().extractSlot(slot, ItemVariant.of(stack), stack.method_7947(), ctx);
                  inventoryItems.add(stack.method_46651((int)extracted));
                  ctx.commit();
               } catch (Throwable var8) {
                  if (ctx != null) {
                     try {
                        ctx.close();
                     } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                     }
                  }

                  throw var8;
               }

               if (ctx != null) {
                  ctx.close();
               }
            }
         });
         UUID backpackUuid = (UUID)backpack.sophisticatedCore_remove(ModCoreDataComponents.STORAGE_UUID);
         if (backpackUuid != null) {
            BackpackStorage.get().removeBackpackContents(backpackUuid);
         }

         backpack.method_57379(class_9334.field_49622, class_9288.method_57493(inventoryItems));
      }
   }

   private static boolean shouldDropBackpack(
      class_1282 source, Config.Server.EntityBackpackAdditionsConfig additionsConfig, class_1309 mob, class_1799 backpack
   ) {
      if (source.method_5529() instanceof class_1657 player) {
         if (!Boolean.TRUE.equals(additionsConfig.dropToFakePlayers.get()) && source.method_5529() instanceof FakePlayer) {
            return false;
         } else {
            float lootingChanceMultiplier = dropChanceMultiplier.getOrDefault(backpack.method_7909(), 1.0F);
            int lootingLevel = player.method_37908()
               .method_30349()
               .method_33310(class_7924.field_41265)
               .map(registry -> player.method_6047().getEnchantmentLevel(registry.method_40290(class_1893.field_9110)))
               .orElse(0);
            return mob.method_37908().field_9229.method_43057()
               < ((Double)additionsConfig.backpackDropChance.get() + lootingLevel * (Double)additionsConfig.lootingChanceIncreasePerLevel.get())
                  * lootingChanceMultiplier;
         }
      } else {
         return false;
      }
   }

   public static void removeBeneficialEffects(class_1548 creeper) {
      if (creeper.method_5752().contains("spawnedWithBackpack")) {
         creeper.method_6026().removeIf(e -> ((class_1291)e.method_5579().comp_349()).method_5573());
      }
   }

   public static void removeBackpackUuid(class_1588 entity, class_1937 level) {
      if (!level.method_8608() && entity.method_5752().contains("spawnedWithBackpack")) {
         class_1799 stack = entity.method_6118(class_1304.field_6174);
         removeContentsUuid(stack);
      }
   }

   private static void removeContentsUuid(class_1799 stack) {
      BackpackWrapper.fromStack(stack).getContentsUuid().ifPresent(uuid -> BackpackStorage.get().removeBackpackContents(uuid));
   }

   public static void onLivingUpdate(class_1309 livingEntity) {
      if (livingEntity.method_5752().contains("sophisticatedbackpacks:jukebox")) {
         IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(livingEntity.method_6118(class_1304.field_6174));
         backpackWrapper.getUpgradeHandler().getTypeWrappers(JukeboxUpgradeItem.TYPE).forEach(wrapper -> {
            if (wrapper.isPlaying()) {
               wrapper.tick(livingEntity, livingEntity.method_37908(), livingEntity.method_24515());
            } else {
               wrapper.play(livingEntity);
            }
         });
      }
   }

   private static class ApplicableEffect {
      private final List<class_6880<class_1291>> effects;
      private final int maxAmplifier;

      private ApplicableEffect(class_6880<class_1291> effect) {
         this(List.of(effect), Integer.MAX_VALUE);
      }

      private ApplicableEffect(List<class_6880<class_1291>> effects, int maxAmplifier) {
         this.effects = effects;
         this.maxAmplifier = maxAmplifier;
      }

      public class_6880<class_1291> getRandomEffect(class_5819 random) {
         return this.effects.get(random.method_43048(this.effects.size()));
      }

      public int getMaxAmplifier() {
         return this.maxAmplifier;
      }
   }

   private record BackpackAddition(
      class_1792 backpackItem,
      int minDifficulty,
      List<WeightedElement<class_1792>> helmetChances,
      List<WeightedElement<class_1792>> leggingsChances,
      List<WeightedElement<class_1792>> bootsChances
   ) {
      public List<WeightedElement<class_1792>> getHelmetChances() {
         return this.helmetChances;
      }

      public List<WeightedElement<class_1792>> getLeggingsChances() {
         return this.leggingsChances;
      }

      public List<WeightedElement<class_1792>> getBootsChances() {
         return this.bootsChances;
      }

      public class_1792 getBackpackItem() {
         return this.backpackItem;
      }

      public int getMinDifficulty() {
         return this.minDifficulty;
      }
   }
}
