package vectorwing.farmersdelight.refabricated;

import java.util.List;
import java.util.Optional;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.class_1299;
import net.minecraft.class_141;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_1935;
import net.minecraft.class_2035;
import net.minecraft.class_212;
import net.minecraft.class_215;
import net.minecraft.class_219;
import net.minecraft.class_223;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_225;
import net.minecraft.class_2272;
import net.minecraft.class_2302;
import net.minecraft.class_2758;
import net.minecraft.class_2960;
import net.minecraft.class_39;
import net.minecraft.class_44;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_55;
import net.minecraft.class_6880;
import net.minecraft.class_7225;
import net.minecraft.class_77;
import net.minecraft.class_7924;
import net.minecraft.class_83;
import net.minecraft.class_9356;
import net.minecraft.class_9361;
import net.minecraft.class_2040.class_2041;
import net.minecraft.class_2048.class_2049;
import net.minecraft.class_2073.class_2074;
import net.minecraft.class_2096.class_2100;
import net.minecraft.class_3735.class_5278;
import net.minecraft.class_4559.class_4560;
import net.minecraft.class_47.class_50;
import net.minecraft.class_52.class_53;
import net.minecraft.class_6880.class_6883;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_85.class_86;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

public class LootModificationEvents {
   private static final class_5321<class_52> BLOCKS_CAKE = vanillaKey("blocks/cake");
   private static final class_5321<class_52> BLOCKS_PUMPKIN = vanillaKey("blocks/pumpkin");
   private static final class_5321<class_52> BLOCKS_SHORT_GRASS = vanillaKey("blocks/short_grass");
   private static final class_5321<class_52> BLOCKS_TALL_GRASS = vanillaKey("blocks/tall_grass");
   private static final class_5321<class_52> ENTITIES_CAVE_SPIDER = vanillaKey("entities/cave_spider");
   private static final class_5321<class_52> ENTITIES_CHICKEN = vanillaKey("entities/chicken");
   private static final class_5321<class_52> ENTITIES_HOGLIN = vanillaKey("entities/hoglin");
   private static final class_5321<class_52> ENTITIES_PIG = vanillaKey("entities/pig");
   private static final class_5321<class_52> ENTITIES_RABBIT = vanillaKey("entities/rabbit");
   private static final class_5321<class_52> ENTITIES_SHULKER = vanillaKey("entities/shulker");
   private static final class_5321<class_52> ENTITIES_SPIDER = vanillaKey("entities/spider");
   private static final class_5321<class_52> BLOCKS_WHEAT = vanillaKey("blocks/wheat");
   private static final class_5321<class_52> BLOCKS_APPLE_PIE = key("blocks/apple_pie");
   private static final class_5321<class_52> BLOCKS_CHOCOLATE_PIE = key("blocks/chocolate_pie");
   private static final class_5321<class_52> BLOCKS_RICE_PANICLES = key("blocks/rice_panicles");
   private static final class_5321<class_52> BLOCKS_SANDY_SHRUB = key("blocks/sandy_shrub");
   private static final class_5321<class_52> BLOCKS_SWEET_BERRY_CHEESECAKE = key("blocks/sweet_berry_cheesecake");
   public static final class_5321<class_52> FD_ABANDONED_MINESHAFT = key("chests/fd_abandoned_mineshaft");
   public static final class_5321<class_52> FD_BASTION_HOGLIN_STABLE = key("chests/fd_bastion_hoglin_stable");
   public static final class_5321<class_52> FD_BASTION_TREASURE = key("chests/fd_bastion_treasure");
   public static final class_5321<class_52> FD_END_CITY_TREASURE = key("chests/fd_end_city_treasure");
   public static final class_5321<class_52> FD_PILLAGER_OUTPOST = key("chests/fd_pillager_outpost");
   public static final class_5321<class_52> FD_RUINED_PORTAL = key("chests/fd_ruined_portal");
   public static final class_5321<class_52> FD_SHIPWRECK_SUPPLY = key("chests/fd_shipwreck_supply");
   public static final class_5321<class_52> FD_SIMPLE_DUNGEON = key("chests/fd_simple_dungeon");
   public static final class_5321<class_52> FD_VILLAGE_BUTCHER = key("chests/fd_village_butcher");
   public static final class_5321<class_52> FD_VILLAGE_DESERT_HOUSE = key("chests/fd_village_desert_house");
   public static final class_5321<class_52> FD_VILLAGE_PLAINS_HOUSE = key("chests/fd_village_plains_house");
   public static final class_5321<class_52> FD_VILLAGE_SAVANNA_HOUSE = key("chests/fd_village_savanna_house");
   public static final class_5321<class_52> FD_VILLAGE_SNOWY_HOUSE = key("chests/fd_village_snowy_house");
   public static final class_5321<class_52> FD_VILLAGE_TAIGA_HOUSE = key("chests/fd_village_taiga_house");

   public static void init() {
      LootTableEvents.MODIFY.register(LootModificationEvents::modifyTable);
   }

   private static void modifyTable(class_5321<class_52> key, class_53 tableBuilder, LootTableSource source, class_7874 registries) {
      chestLoot(key, tableBuilder, source, registries);
      scavengingLoot(key, tableBuilder, source, registries);
      slicingLoot(key, tableBuilder, source, registries);
      straw(key, tableBuilder, source, registries);
   }

   private static void chestLoot(class_5321<class_52> key, class_53 tableBuilder, LootTableSource source, class_7874 registries) {
      if (Configuration.GENERATE_FD_CHEST_LOOT.get()) {
         if (key == class_39.field_472) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_ABANDONED_MINESHAFT)));
         }

         if (key == class_39.field_24049) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_BASTION_HOGLIN_STABLE)));
         }

         if (key == class_39.field_24046) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_BASTION_TREASURE)));
         }

         if (key == class_39.field_274) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_END_CITY_TREASURE)));
         }

         if (key == class_39.field_16593) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_PILLAGER_OUTPOST)));
         }

         if (key == class_39.field_24050) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_RUINED_PORTAL)));
         }

         if (key == class_39.field_880) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_SHIPWRECK_SUPPLY)));
         }

         if (key == class_39.field_356) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_SIMPLE_DUNGEON)));
         }

         if (key == class_39.field_17012) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_VILLAGE_BUTCHER)));
         }

         if (key == class_39.field_16752) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_VILLAGE_DESERT_HOUSE)));
         }

         if (key == class_39.field_16748) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_VILLAGE_PLAINS_HOUSE)));
         }

         if (key == class_39.field_16753) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_VILLAGE_SAVANNA_HOUSE)));
         }

         if (key == class_39.field_16754) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_VILLAGE_SNOWY_HOUSE)));
         }

         if (key == class_39.field_16749) {
            tableBuilder.method_336(class_55.method_347().method_351(class_83.method_428(FD_VILLAGE_TAIGA_HOUSE)));
         }
      }
   }

   private static void scavengingLoot(class_5321<class_52> key, class_53 tableBuilder, LootTableSource source, class_7874 registries) {
      class_7225<class_1887> enchantments = registries.method_46762(class_7924.field_41265);
      if (key == ENTITIES_CHICKEN) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(
                  class_77.method_411(class_1802.field_8153)
                     .method_421(
                        class_215.method_917(
                           class_50.field_936,
                           class_2049.method_8916().method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                        )
                     )
               )
         );
      }

      if (key == ENTITIES_HOGLIN) {
         tableBuilder.method_336(
               class_55.method_347()
                  .method_351(
                     class_77.method_411((class_1935)ModItems.HAM.get())
                        .method_421(
                           class_215.method_917(
                                 class_50.field_936,
                                 class_2049.method_8916()
                                    .method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                              )
                              .and(class_215.method_917(class_50.field_935, class_2049.method_8916().method_8919(class_2041.method_8897().method_8898(false))))
                        )
                  )
            )
            .method_336(
               class_55.method_347()
                  .method_351(
                     class_77.method_411((class_1935)ModItems.SMOKED_HAM.get())
                        .method_421(
                           class_215.method_917(
                                 class_50.field_936,
                                 class_2049.method_8916()
                                    .method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                              )
                              .and(class_215.method_917(class_50.field_935, class_2049.method_8916().method_8919(class_2041.method_8897().method_8898(true))))
                        )
                  )
            );
      }

      if (key == ENTITIES_PIG) {
         tableBuilder.method_336(
               class_55.method_347()
                  .method_351(
                     class_77.method_411((class_1935)ModItems.HAM.get())
                        .method_421(
                           class_215.method_917(
                                 class_50.field_936,
                                 class_2049.method_8916()
                                    .method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                              )
                              .and(class_215.method_917(class_50.field_935, class_2049.method_8916().method_8919(class_2041.method_8897().method_8898(false))))
                              .and(class_225.method_953(registries, 0.5F, 0.1F))
                        )
                  )
            )
            .method_336(
               class_55.method_347()
                  .method_351(
                     class_77.method_411((class_1935)ModItems.SMOKED_HAM.get())
                        .method_421(
                           class_215.method_917(
                                 class_50.field_936,
                                 class_2049.method_8916()
                                    .method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                              )
                              .and(
                                 class_215.method_917(class_50.field_935, class_2049.method_8916().method_8919(class_2041.method_8897().method_8898(true)))
                                    .and(class_225.method_953(registries, 0.5F, 0.1F))
                              )
                        )
                  )
            );
      }

      if (key == BLOCKS_PUMPKIN) {
         tableBuilder.modifyPools(
               builder -> builder.conditionally(
                  class_223.method_945(class_2074.method_8973().method_8975(ModTags.KNIVES))
                     .and(
                        class_223.method_945(
                              class_2074.method_8973()
                                 .method_58179(
                                    class_9361.field_49807,
                                    class_9356.method_58173(List.of(new class_2035(enchantments.method_46747(class_1893.field_9099), class_2100.field_9708)))
                                 )
                           )
                           .method_16780()
                     )
                     .method_16780()
                     .build()
               )
            )
            .method_336(
               class_55.method_347()
                  .method_351(
                     ((class_86)class_77.method_411((class_1935)ModItems.PUMPKIN_SLICE.get())
                           .method_421(
                              class_223.method_945(class_2074.method_8973().method_8975(ModTags.KNIVES))
                                 .and(
                                    class_223.method_945(
                                          class_2074.method_8973()
                                             .method_58179(
                                                class_9361.field_49807,
                                                class_9356.method_58173(
                                                   List.of(new class_2035(enchantments.method_46747(class_1893.field_9099), class_2100.field_9708))
                                                )
                                             )
                                       )
                                       .method_16780()
                                 )
                           ))
                        .method_438(class_141.method_621(class_44.method_32448(4.0F)))
                  )
            );
      }

      if (key.method_29177().method_12832().startsWith("entities/")) {
         class_7225<class_1299<?>> lookup = registries.method_46762(class_7924.field_41266);
         Optional<class_6883<class_1299<?>>> entityType = lookup.method_46746(
            class_5321.method_29179(class_7924.field_41266, key.method_29177().method_45134(s -> s.substring(9)))
         );
         if (entityType.isPresent() && TagUtils.isDropsLeatherTag((class_6880<class_1299<?>>)entityType.get(), lookup)) {
            tableBuilder.method_336(
               class_55.method_347()
                  .method_351(
                     class_77.method_411(class_1802.field_8745)
                        .method_421(
                           class_215.method_917(
                              class_50.field_936,
                              class_2049.method_8916()
                                 .method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                           )
                        )
                  )
            );
         }
      }

      if (key == ENTITIES_RABBIT) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(
                  class_77.method_411(class_1802.field_8245)
                     .method_421(
                        class_215.method_917(
                           class_50.field_936,
                           class_2049.method_8916().method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                        )
                     )
               )
         );
      }

      if (key == ENTITIES_SHULKER) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(
                  class_77.method_411(class_1802.field_8815)
                     .method_421(
                        class_215.method_917(
                           class_50.field_936,
                           class_2049.method_8916().method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                        )
                     )
               )
         );
      }

      if (key == ENTITIES_SHULKER) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(
                  class_77.method_411(class_1802.field_8815)
                     .method_421(
                        class_215.method_917(
                           class_50.field_936,
                           class_2049.method_8916().method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                        )
                     )
               )
         );
      }

      if (key == ENTITIES_SPIDER || key == ENTITIES_CAVE_SPIDER) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(
                  class_77.method_411(class_1802.field_8276)
                     .method_421(
                        class_215.method_917(
                           class_50.field_936,
                           class_2049.method_8916().method_53141(class_5278.method_27965().method_35195(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                        )
                     )
               )
         );
      }
   }

   private static void slicingLoot(class_5321<class_52> key, class_53 tableBuilder, LootTableSource source, class_7874 registries) {
      if (key == BLOCKS_APPLE_PIE) {
         pastrySlicing(tableBuilder, ModBlocks.APPLE_PIE.get(), (class_1935)ModItems.APPLE_PIE_SLICE.get(), PieBlock.BITES, 4);
      }

      if (key == BLOCKS_CAKE) {
         pastrySlicing(tableBuilder, class_2246.field_10183, (class_1935)ModItems.CAKE_SLICE.get(), class_2272.field_10739, 7);
      }

      if (key.method_29177().method_12832().startsWith("blocks/")) {
         class_7225<class_2248> lookup = registries.method_46762(class_7924.field_41254);
         Optional<class_6883<class_2248>> block = lookup.method_46746(
            class_5321.method_29179(class_7924.field_41254, key.method_29177().method_45134(s -> s.substring(7)))
         );
         if (block.isPresent() && TagUtils.isCandleDropsCakeSliceTag((class_6880<class_2248>)block.get(), lookup)) {
            tableBuilder.method_336(
               class_55.method_347()
                  .method_351(
                     class_77.method_411((class_1935)ModItems.CAKE_SLICE.get())
                        .method_438(class_141.method_621(class_44.method_32448(7.0F)))
                        .method_421(class_223.method_945(class_2074.method_8973().method_8975(ModTags.KNIVES)))
                  )
            );
         }
      }

      if (key == BLOCKS_CHOCOLATE_PIE) {
         pastrySlicing(tableBuilder, ModBlocks.CHOCOLATE_PIE.get(), (class_1935)ModItems.CHOCOLATE_PIE_SLICE.get(), PieBlock.BITES, 4);
      }

      if (key == BLOCKS_SWEET_BERRY_CHEESECAKE) {
         pastrySlicing(tableBuilder, ModBlocks.SWEET_BERRY_CHEESECAKE.get(), (class_1935)ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(), PieBlock.BITES, 4);
      }
   }

   public static void pastrySlicing(class_53 tableBuilder, class_2248 block, class_1935 slice, class_2758 property, int maxValue) {
      tableBuilder.modifyPools(builder -> builder.method_356(class_223.method_945(class_2074.method_8973().method_8975(ModTags.KNIVES)).method_16780()));

      for (int value : property.method_11898()) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(class_77.method_411(slice).method_438(class_141.method_621(class_44.method_32448(maxValue - value))))
               .method_356(
                  class_223.method_945(class_2074.method_8973().method_8975(ModTags.KNIVES))
                     .and(class_212.method_900(block).method_22584(class_4560.method_22523().method_22524(property, value)))
               )
         );
      }
   }

   private static void straw(class_5321<class_52> key, class_53 tableBuilder, LootTableSource source, class_7874 registries) {
      if (key == BLOCKS_SHORT_GRASS || key == BLOCKS_TALL_GRASS) {
         strawChance02(tableBuilder);
      }

      if (key == BLOCKS_SANDY_SHRUB) {
         strawChance03(tableBuilder);
      }

      if (key == BLOCKS_RICE_PANICLES) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(
                  class_77.method_411((class_1935)ModItems.STRAW.get())
                     .method_421(
                        class_223.method_945(class_2074.method_8973().method_8975(ModTags.STRAW_HARVESTERS))
                           .and(
                              class_212.method_900(ModBlocks.RICE_CROP_PANICLES.get())
                                 .method_22584(class_4560.method_22523().method_22524(class_2302.field_10835, 3))
                           )
                     )
               )
         );
      }

      if (key == BLOCKS_WHEAT) {
         tableBuilder.method_336(
            class_55.method_347()
               .method_351(
                  class_77.method_411((class_1935)ModItems.STRAW.get())
                     .method_421(
                        class_223.method_945(class_2074.method_8973().method_8975(ModTags.STRAW_HARVESTERS))
                           .and(class_212.method_900(class_2246.field_10293).method_22584(class_4560.method_22523().method_22524(class_2302.field_10835, 7)))
                     )
               )
         );
      }
   }

   public static void strawChance02(class_53 tableBuilder) {
      tableBuilder.method_336(
         class_55.method_347()
            .method_351(
               class_77.method_411((class_1935)ModItems.STRAW.get())
                  .method_421(class_219.method_932(0.3F).and(class_223.method_945(class_2074.method_8973().method_8975(ModTags.STRAW_HARVESTERS))))
            )
      );
   }

   public static void strawChance03(class_53 tableBuilder) {
      tableBuilder.method_336(
         class_55.method_347()
            .method_351(
               class_77.method_411((class_1935)ModItems.STRAW.get())
                  .method_421(class_219.method_932(0.3F).and(class_223.method_945(class_2074.method_8973().method_8975(ModTags.STRAW_HARVESTERS))))
            )
      );
   }

   private static class_5321<class_52> vanillaKey(String path) {
      return class_5321.method_29179(class_7924.field_50079, class_2960.method_60656(path));
   }

   private static class_5321<class_52> key(String path) {
      return class_5321.method_29179(class_7924.field_50079, FarmersDelight.res(path));
   }
}
