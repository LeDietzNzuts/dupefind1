package vectorwing.farmersdelight.data.advancement;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.class_174;
import net.minecraft.class_189;
import net.minecraft.class_1935;
import net.minecraft.class_205;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_3483;
import net.minecraft.class_5258;
import net.minecraft.class_5341;
import net.minecraft.class_8103;
import net.minecraft.class_8129;
import net.minecraft.class_8779;
import net.minecraft.class_161.class_162;
import net.minecraft.class_170.class_171;
import net.minecraft.class_2010.class_2012;
import net.minecraft.class_2019.class_2020;
import net.minecraft.class_2022.class_2023;
import net.minecraft.class_2027.class_2029;
import net.minecraft.class_2048.class_2049;
import net.minecraft.class_2066.class_2068;
import net.minecraft.class_2090.class_2091;
import net.minecraft.class_2102.class_8748;
import net.minecraft.class_2115.class_2117;
import net.minecraft.class_4550.class_4710;
import net.minecraft.class_4559.class_4560;
import net.minecraft.class_4711.class_4712;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_8782.class_8797;
import net.minecraft.class_9107.class_9108;
import vectorwing.farmersdelight.common.advancement.CuttingBoardTrigger;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.registry.ModEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class FDAdvancementGenerator extends FabricAdvancementProvider {
   public FDAdvancementGenerator(FabricDataOutput output, CompletableFuture<class_7874> registryLookup) {
      super(output, registryLookup);
   }

   protected static class_162 getAdvancement(
      class_8779 parent, class_1935 display, String name, class_189 frame, boolean showToast, boolean announceToChat, boolean hidden
   ) {
      return class_162.method_707()
         .method_701(parent)
         .method_697(
            display,
            TextUtils.getTranslation("advancement." + name),
            TextUtils.getTranslation("advancement." + name + ".desc"),
            null,
            frame,
            showToast,
            announceToChat,
            hidden
         );
   }

   public void generateAdvancement(class_7874 provider, Consumer<class_8779> consumer) {
      class_8779 farmersDelight = class_162.method_707()
         .method_697(
            (class_1935)ModItems.COOKING_POT.get(),
            TextUtils.getTranslation("advancement.root"),
            TextUtils.getTranslation("advancement.root.desc"),
            class_2960.method_60654("minecraft:textures/block/bricks.png"),
            class_189.field_1254,
            false,
            false,
            false
         )
         .method_705("seeds", class_2068.method_8959(new class_1935[0]))
         .method_694(consumer, this.getNameId("main/root"));
      class_8779 huntAndGather = getAdvancement(farmersDelight, (class_1935)ModItems.FLINT_KNIFE.get(), "craft_knife", class_189.field_1254, true, true, false)
         .method_705("flint_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.FLINT_KNIFE.get()}))
         .method_705("iron_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.IRON_KNIFE.get()}))
         .method_705("diamond_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.DIAMOND_KNIFE.get()}))
         .method_705("golden_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.GOLDEN_KNIFE.get()}))
         .method_705("netherite_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.NETHERITE_KNIFE.get()}))
         .method_704(class_8797.field_1257)
         .method_694(consumer, this.getNameId("main/craft_knife"));
      class_8779 graspingAtStraws = getAdvancement(huntAndGather, (class_1935)ModItems.STRAW.get(), "harvest_straw", class_189.field_1254, true, false, false)
         .method_705("harvest_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_694(consumer, this.getNameId("main/harvest_straw"));
      class_8779 advancedComposting = getAdvancement(
            graspingAtStraws, (class_1935)ModItems.ORGANIC_COMPOST.get(), "place_organic_compost", class_189.field_1254, true, false, false
         )
         .method_705("place_organic_compost", class_4712.method_51710(ModBlocks.ORGANIC_COMPOST.get()))
         .method_694(consumer, this.getNameId("main/place_organic_compost"));
      class_8779 plantFood = getAdvancement(advancedComposting, (class_1935)ModItems.RICH_SOIL.get(), "get_rich_soil", class_189.field_1249, true, true, false)
         .method_705("get_rich_soil", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RICH_SOIL.get()}))
         .method_694(consumer, this.getNameId("main/get_rich_soil"));
      class_8779 wildButcher = getAdvancement(huntAndGather, (class_1935)ModItems.HAM.get(), "get_ham", class_189.field_1254, true, false, false)
         .method_705("ham", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.HAM.get()}))
         .method_705("smoked_ham", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.SMOKED_HAM.get()}))
         .method_704(class_8797.field_1257)
         .method_694(consumer, this.getNameId("main/get_ham"));
      class_8779 watchYourFingers = getAdvancement(
            huntAndGather, (class_1935)ModItems.CUTTING_BOARD.get(), "use_cutting_board", class_189.field_1254, true, false, false
         )
         .method_705("cutting_board", CuttingBoardTrigger.TriggerInstance.simple())
         .method_694(consumer, this.getNameId("main/use_cutting_board"));
      class_8779 cantTakeTheHeat = getAdvancement(
            watchYourFingers, (class_1935)ModItems.NETHERITE_KNIFE.get(), "obtain_netherite_knife", class_189.field_1250, true, true, false
         )
         .method_705("obtain_netherite_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.NETHERITE_KNIFE.get()}))
         .method_703(class_171.method_750(200))
         .method_694(consumer, this.getNameId("main/obtain_netherite_knife"));
      class_8779 cropsOfTheWild = getAdvancement(farmersDelight, (class_1935)ModItems.WILD_ONIONS.get(), "get_fd_seed", class_189.field_1254, true, true, false)
         .method_705("cabbage_seeds", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CABBAGE_SEEDS.get()}))
         .method_705("tomato_seeds", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.TOMATO_SEEDS.get()}))
         .method_705("onion", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.ONION.get()}))
         .method_705("rice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RICE.get()}))
         .method_704(class_8797.field_1257)
         .method_694(consumer, this.getNameId("main/get_fd_seed"));
      class_8779 fungusAmongUs = getAdvancement(
            cropsOfTheWild, (class_1935)ModItems.RED_MUSHROOM_COLONY.get(), "get_mushroom_colony", class_189.field_1254, true, false, false
         )
         .method_705("brown_mushroom_colony", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.BROWN_MUSHROOM_COLONY.get()}))
         .method_705("red_mushroom_colony", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RED_MUSHROOM_COLONY.get()}))
         .method_704(class_8797.field_1257)
         .method_694(consumer, this.getNameId("main/get_mushroom_colony"));
      class_8779 dippingYourRoots = getAdvancement(cropsOfTheWild, (class_1935)ModItems.RICE.get(), "plant_rice", class_189.field_1254, true, false, false)
         .method_705("plant_rice", class_4712.method_51710(ModBlocks.RICE_CROP.get()))
         .method_694(consumer, this.getNameId("main/plant_rice"));
      class_8779 tallmato = getAdvancement(
            cropsOfTheWild, (class_1935)ModItems.TOMATO.get(), "harvest_ropelogged_tomato", class_189.field_1254, true, false, false
         )
         .method_705(
            "harvest_ropelogged_tomato",
            class_174.field_48268
               .method_53699(
                  new class_9108(
                     Optional.empty(),
                     Optional.of(
                        class_5258.method_27973(
                           new class_5341[]{
                              class_205.method_884(
                                    class_2091.method_22484()
                                       .method_27989(
                                          class_4710.method_23880()
                                             .method_27962(new class_2248[]{ModBlocks.TOMATO_CROP.get()})
                                             .method_27963(
                                                class_4560.method_22523()
                                                   .method_22524(TomatoVineBlock.VINE_AGE, 0)
                                                   .method_22527(TomatoVineBlock.ROPELOGGED, true)
                                             )
                                       )
                                 )
                                 .build()
                           }
                        )
                     )
                  )
               )
         )
         .method_694(consumer, this.getNameId("main/harvest_ropelogged_tomato"));
      class_8779 booHiss = getAdvancement(
            tallmato, (class_1935)ModItems.ROTTEN_TOMATO.get(), "hit_raider_with_rotten_tomato", class_189.field_1254, true, true, false
         )
         .method_705(
            "hit_raider_with_rotten_tomato",
            class_2117.method_35294(
               Optional.of(
                  class_2020.method_8844()
                     .method_8842(
                        class_2023.method_8855()
                           .method_48785(class_8129.method_48965(class_8103.field_42247))
                           .method_8854(class_2049.method_8916().method_8921(ModEntityTypes.ROTTEN_TOMATO.get()))
                     )
                     .method_8843()
               ),
               Optional.of(class_2049.method_8916().method_8922(class_3483.field_19168).method_8920())
            )
         )
         .method_694(consumer, this.getNameId("main/hit_raider_with_rotten_tomato"));
      class_8779 cropRotation = getAdvancement(dippingYourRoots, (class_1935)ModItems.CABBAGE.get(), "plant_all_crops", class_189.field_1250, true, true, false)
         .method_705("wheat", class_4712.method_51710(class_2246.field_10293))
         .method_705("beetroot", class_4712.method_51710(class_2246.field_10341))
         .method_705("carrot", class_4712.method_51710(class_2246.field_10609))
         .method_705("potato", class_4712.method_51710(class_2246.field_10247))
         .method_705("cabbage", class_4712.method_51710(ModBlocks.CABBAGE_CROP.get()))
         .method_705("tomato", class_4712.method_51710(ModBlocks.BUDDING_TOMATO_CROP.get()))
         .method_705("onion", class_4712.method_51710(ModBlocks.ONION_CROP.get()))
         .method_705("rice", class_4712.method_51710(ModBlocks.RICE_CROP.get()))
         .method_705("melon", class_4712.method_51710(class_2246.field_46287))
         .method_705("pumpkin", class_4712.method_51710(class_2246.field_46286))
         .method_705("sweet_berries", class_4712.method_51710(class_2246.field_16999))
         .method_705("sugar_cane", class_4712.method_51710(class_2246.field_10424))
         .method_705("kelp", class_4712.method_51710(class_2246.field_9993))
         .method_705("cocoa", class_4712.method_51710(class_2246.field_10302))
         .method_705("nether_wart", class_4712.method_51710(class_2246.field_9974))
         .method_705("chorus_flower", class_4712.method_51710(class_2246.field_10528))
         .method_705("brown_mushroom", class_4712.method_51710(class_2246.field_10251))
         .method_705("red_mushroom", class_4712.method_51710(class_2246.field_10559))
         .method_705("glow_berries", class_4712.method_51710(class_2246.field_28675))
         .method_703(class_171.method_750(100))
         .method_694(consumer, this.getNameId("main/plant_all_crops"));
      class_8779 bonfireLit = getAdvancement(farmersDelight, class_2246.field_17350, "place_campfire", class_189.field_1254, true, true, false)
         .method_705("campfire", class_4712.method_51710(class_2246.field_17350))
         .method_705("soul_campfire", class_4712.method_51710(class_2246.field_23860))
         .method_704(class_8797.field_1257)
         .method_694(consumer, this.getNameId("main/place_campfire"));
      class_8779 portableCooking = getAdvancement(bonfireLit, (class_1935)ModItems.SKILLET.get(), "use_skillet", class_189.field_1254, true, false, false)
         .method_705("skillet", class_2012.method_8828((class_1935)ModItems.SKILLET.get()))
         .method_694(consumer, this.getNameId("main/use_skillet"));
      class_8779 sizzlingHot = getAdvancement(portableCooking, (class_1935)ModItems.SKILLET.get(), "place_skillet", class_189.field_1254, true, false, false)
         .method_705("skillet", class_4712.method_51710(ModBlocks.SKILLET.get()))
         .method_694(consumer, this.getNameId("main/place_skillet"));
      class_8779 dinnerIsServed = getAdvancement(
            bonfireLit, (class_1935)ModItems.COOKING_POT.get(), "place_cooking_pot", class_189.field_1249, true, true, false
         )
         .method_705("cooking_pot", class_4712.method_51710(ModBlocks.COOKING_POT.get()))
         .method_694(consumer, this.getNameId("main/place_cooking_pot"));
      class_8779 comforting = getAdvancement(
            dinnerIsServed, (class_1935)ModItems.BAKED_COD_STEW.get(), "eat_comfort_food", class_189.field_1254, true, false, false
         )
         .method_705("comfort", class_2029.method_8869(class_8748.method_53200().method_53201(ModEffects.COMFORT)))
         .method_694(consumer, this.getNameId("main/eat_comfort_food"));
      class_8779 nourishing = getAdvancement(
            comforting, (class_1935)ModItems.STEAK_AND_POTATOES.get(), "eat_nourishing_food", class_189.field_1254, true, false, false
         )
         .method_705("nourishment", class_2029.method_8869(class_8748.method_53200().method_53201(ModEffects.NOURISHMENT)))
         .method_694(consumer, this.getNameId("main/eat_nourishing_food"));
      class_8779 gloriousFeast = getAdvancement(
            nourishing, (class_1935)ModItems.ROAST_CHICKEN_BLOCK.get(), "place_feast", class_189.field_1254, true, true, false
         )
         .method_705("roast_chicken", class_4712.method_51710(ModBlocks.ROAST_CHICKEN_BLOCK.get()))
         .method_705("stuffed_pumpkin", class_4712.method_51710(ModBlocks.STUFFED_PUMPKIN_BLOCK.get()))
         .method_705("honey_glazed_ham", class_4712.method_51710(ModBlocks.HONEY_GLAZED_HAM_BLOCK.get()))
         .method_705("shepherds_pie", class_4712.method_51710(ModBlocks.SHEPHERDS_PIE_BLOCK.get()))
         .method_705("rice_roll_medley", class_4712.method_51710(ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get()))
         .method_704(class_8797.field_1257)
         .method_694(consumer, this.getNameId("main/place_feast"));
      class_8779 masterChef = getAdvancement(gloriousFeast, (class_1935)ModItems.HONEY_GLAZED_HAM.get(), "master_chef", class_189.field_1250, true, true, false)
         .method_705("mixed_salad", class_2012.method_8828((class_1935)ModItems.MIXED_SALAD.get()))
         .method_705("cooked_rice", class_2012.method_8828((class_1935)ModItems.COOKED_RICE.get()))
         .method_705("bone_broth", class_2012.method_8828((class_1935)ModItems.BONE_BROTH.get()))
         .method_705("beef_stew", class_2012.method_8828((class_1935)ModItems.BEEF_STEW.get()))
         .method_705("vegetable_soup", class_2012.method_8828((class_1935)ModItems.VEGETABLE_SOUP.get()))
         .method_705("fish_stew", class_2012.method_8828((class_1935)ModItems.FISH_STEW.get()))
         .method_705("chicken_soup", class_2012.method_8828((class_1935)ModItems.CHICKEN_SOUP.get()))
         .method_705("fried_rice", class_2012.method_8828((class_1935)ModItems.FRIED_RICE.get()))
         .method_705("pumpkin_soup", class_2012.method_8828((class_1935)ModItems.PUMPKIN_SOUP.get()))
         .method_705("baked_cod_stew", class_2012.method_8828((class_1935)ModItems.BAKED_COD_STEW.get()))
         .method_705("noodle_soup", class_2012.method_8828((class_1935)ModItems.NOODLE_SOUP.get()))
         .method_705("bacon_and_eggs", class_2012.method_8828((class_1935)ModItems.BACON_AND_EGGS.get()))
         .method_705("ratatouille", class_2012.method_8828((class_1935)ModItems.RATATOUILLE.get()))
         .method_705("steak_and_potatoes", class_2012.method_8828((class_1935)ModItems.STEAK_AND_POTATOES.get()))
         .method_705("pasta_with_meatballs", class_2012.method_8828((class_1935)ModItems.PASTA_WITH_MEATBALLS.get()))
         .method_705("pasta_with_mutton_chop", class_2012.method_8828((class_1935)ModItems.PASTA_WITH_MUTTON_CHOP.get()))
         .method_705("mushroom_rice", class_2012.method_8828((class_1935)ModItems.MUSHROOM_RICE.get()))
         .method_705("roasted_mutton_chops", class_2012.method_8828((class_1935)ModItems.ROASTED_MUTTON_CHOPS.get()))
         .method_705("vegetable_noodles", class_2012.method_8828((class_1935)ModItems.VEGETABLE_NOODLES.get()))
         .method_705("squid_ink_pasta", class_2012.method_8828((class_1935)ModItems.SQUID_INK_PASTA.get()))
         .method_705("grilled_salmon", class_2012.method_8828((class_1935)ModItems.GRILLED_SALMON.get()))
         .method_705("roast_chicken", class_2012.method_8828((class_1935)ModItems.ROAST_CHICKEN.get()))
         .method_705("stuffed_pumpkin", class_2012.method_8828((class_1935)ModItems.STUFFED_PUMPKIN.get()))
         .method_705("honey_glazed_ham", class_2012.method_8828((class_1935)ModItems.HONEY_GLAZED_HAM.get()))
         .method_705("shepherds_pie", class_2012.method_8828((class_1935)ModItems.SHEPHERDS_PIE.get()))
         .method_703(class_171.method_750(200))
         .method_694(consumer, this.getNameId("main/master_chef"));
   }

   private String getNameId(String id) {
      return "farmersdelight:" + id;
   }
}
