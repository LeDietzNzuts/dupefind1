package vectorwing.farmersdelight.data.recipe;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_3417;
import net.minecraft.class_8790;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.refabricated.ItemAbility;

public class CuttingRecipes {
   public static void register(class_8790 output) {
      cuttingAnimalItems(output);
      cuttingVegetables(output);
      cuttingFoods(output);
      cuttingFlowers(output);
      salvagingMinerals(output);
      strippingWood(output);
      salvagingWoodenFurniture(output);
      diggingSediments(output);
      salvagingUsingShears(output);
   }

   private static void cuttingAnimalItems(class_8790 output) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8046}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.MINCED_BEEF.get(),
            2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8389}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.BACON.get(),
            2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8726}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.CHICKEN_CUTS.get(),
            2
         )
         .addResult(class_1802.field_8324)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8544}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.COOKED_CHICKEN_CUTS.get(),
            2
         )
         .addResult(class_1802.field_8324)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8429}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.COD_SLICE.get(),
            2
         )
         .addResult(class_1802.field_8324)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8373}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.COOKED_COD_SLICE.get(),
            2
         )
         .addResult(class_1802.field_8324)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8209}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.SALMON_SLICE.get(),
            2
         )
         .addResult(class_1802.field_8324)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8509}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.COOKED_SALMON_SLICE.get(),
            2
         )
         .addResult(class_1802.field_8324)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.HAM.get()}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8389, 2
         )
         .addResult(class_1802.field_8606)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.SMOKED_HAM.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            class_1802.field_8261,
            2
         )
         .addResult(class_1802.field_8606)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8748}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.MUTTON_CHOPS.get(),
            2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8347}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.COOKED_MUTTON_CHOPS.get(),
            2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8794}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8226, 2
         )
         .build(output);
   }

   private static void cuttingVegetables(class_8790 output) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.CABBAGE.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.CABBAGE_LEAF.get(),
            2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.RICE_PANICLE.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.RICE.get(),
            1
         )
         .addResult((class_1935)ModItems.STRAW.get())
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17522}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8497, 9
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17518}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.PUMPKIN_SLICE.get(),
            4
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.BROWN_MUSHROOM_COLONY.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            class_1802.field_17516,
            5
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.RED_MUSHROOM_COLONY.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            class_1802.field_17517,
            5
         )
         .build(output);
   }

   private static void cuttingFoods(class_8790 output) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8106(CommonTags.FOODS_DOUGH), class_1856.method_8106(CommonTags.TOOLS_KNIFE), (class_1935)ModItems.RAW_PASTA.get(), 1
         )
         .build(output, class_2960.method_60655("farmersdelight", "tag_dough"));
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.KELP_ROLL.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.KELP_ROLL_SLICE.get(),
            3
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17534}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.CAKE_SLICE.get(),
            7
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.APPLE_PIE.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.APPLE_PIE_SLICE.get(),
            4
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.SWEET_BERRY_CHEESECAKE.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(),
            4
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.CHOCOLATE_PIE.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.CHOCOLATE_PIE_SLICE.get(),
            4
         )
         .build(output);
   }

   private static void cuttingFlowers(class_8790 output) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17515}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8226, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17513}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8345, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17499}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8273, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17501}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8851, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17512}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8851, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17510}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8851, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17500}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8669, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17509}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8492, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17511}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8330, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17502}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8264, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8880}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8264, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_17514}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8446, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8491}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8192, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_42695}), class_1856.method_8106(CommonTags.TOOLS_KNIFE), class_1802.field_8492, 2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WILD_BEETROOTS.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            class_1802.field_8309,
            1
         )
         .addResult(class_1802.field_8264)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WILD_CABBAGES.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.CABBAGE_SEEDS.get(),
            1
         )
         .addResultWithChance(class_1802.field_8192, 0.5F, 2)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WILD_CARROTS.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            class_1802.field_8179,
            1
         )
         .addResultWithChance(class_1802.field_8851, 0.5F, 2)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WILD_ONIONS.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.ONION.get(),
            1
         )
         .addResult(class_1802.field_8669, 2)
         .addResultWithChance(class_1802.field_8131, 0.1F)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WILD_POTATOES.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            class_1802.field_8567,
            1
         )
         .addResultWithChance(class_1802.field_8296, 0.5F, 2)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WILD_RICE.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.RICE.get(),
            1
         )
         .addResultWithChance((class_1935)ModItems.STRAW.get(), 0.5F)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WILD_TOMATOES.get()}),
            class_1856.method_8106(CommonTags.TOOLS_KNIFE),
            (class_1935)ModItems.TOMATO_SEEDS.get(),
            1
         )
         .addResultWithChance((class_1935)ModItems.TOMATO.get(), 0.2F)
         .addResultWithChance(class_1802.field_8408, 0.1F)
         .build(output);
   }

   private static void salvagingMinerals(class_8790 output) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_20390}),
            new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(),
            class_1802.field_8621,
            4
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_20398}),
            new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(),
            class_1802.field_8729,
            4
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_20391}),
            new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(),
            class_1802.field_20412,
            1
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_28866}),
            new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(),
            class_1802.field_29025,
            1
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_20402}),
            new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(),
            class_1802.field_8155,
            4
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_27064}),
            new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(),
            class_1802.field_27063,
            4
         )
         .build(output);
   }

   private static void strippingWood(class_8790 output) {
      stripLogForBark(output, class_1802.field_8583, class_1802.field_8415);
      stripLogForBark(output, class_1802.field_8888, class_1802.field_8248);
      stripLogForBark(output, class_1802.field_8684, class_1802.field_8624);
      stripLogForBark(output, class_1802.field_8210, class_1802.field_8362);
      stripLogForBark(output, class_1802.field_8170, class_1802.field_8767);
      stripLogForBark(output, class_1802.field_8201, class_1802.field_8472);
      stripLogForBark(output, class_1802.field_8125, class_1802.field_8334);
      stripLogForBark(output, class_1802.field_8439, class_1802.field_8785);
      stripLogForBark(output, class_1802.field_8820, class_1802.field_8072);
      stripLogForBark(output, class_1802.field_8587, class_1802.field_8284);
      stripLogForBark(output, class_1802.field_8652, class_1802.field_8808);
      stripLogForBark(output, class_1802.field_8458, class_1802.field_8219);
      stripLogForBark(output, class_1802.field_37512, class_1802.field_37515);
      stripLogForBark(output, class_1802.field_37510, class_1802.field_37509);
      stripLogForBark(output, class_1802.field_42692, class_1802.field_42693);
      stripLogForBark(output, class_1802.field_42691, class_1802.field_42690);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_41066}),
            new ItemAbilityIngredient(ItemAbility.AXE_STRIP).toVanilla(),
            class_1802.field_41065
         )
         .addResult((class_1935)ModItems.STRAW.get())
         .addSound(class_3417.field_14675)
         .build(output);
      stripLogForBark(output, class_1802.field_21981, class_1802.field_21983);
      stripLogForBark(output, class_1802.field_22489, class_1802.field_22487);
      stripLogForBark(output, class_1802.field_21982, class_1802.field_21984);
      stripLogForBark(output, class_1802.field_22490, class_1802.field_22488);
   }

   private static void salvagingWoodenFurniture(class_8790 output) {
      salvagePlankFromFurniture(output, class_1802.field_8118, class_1802.field_8691, class_1802.field_8376, class_1802.field_8788, class_1802.field_40229);
      salvagePlankFromFurniture(output, class_1802.field_8113, class_1802.field_8165, class_1802.field_8495, class_1802.field_8111, class_1802.field_40230);
      salvagePlankFromFurniture(output, class_1802.field_8191, class_1802.field_8438, class_1802.field_8774, class_1802.field_8422, class_1802.field_40231);
      salvagePlankFromFurniture(output, class_1802.field_8842, class_1802.field_8199, class_1802.field_8321, class_1802.field_8867, class_1802.field_40232);
      salvagePlankFromFurniture(output, class_1802.field_8651, class_1802.field_8758, class_1802.field_8190, class_1802.field_8203, class_1802.field_40233);
      salvagePlankFromFurniture(output, class_1802.field_8404, class_1802.field_8517, class_1802.field_8844, class_1802.field_8496, class_1802.field_40234);
      salvagePlankFromFurniture(output, class_1802.field_37507, class_1802.field_37528, class_1802.field_37529, class_1802.field_37534, class_1802.field_40235);
      salvagePlankFromFurniture(output, class_1802.field_42687, class_1802.field_42705, class_1802.field_42702, class_1802.field_42709, class_1802.field_42708);
      salvagePlankFromFurniture(output, class_1802.field_40213, class_1802.field_40222, class_1802.field_40226, class_1802.field_40228, class_1802.field_40236);
      salvagePlankFromFurniture(output, class_1802.field_22031, class_1802.field_22010, class_1802.field_22002, class_1802.field_22011, class_1802.field_40237);
      salvagePlankFromFurniture(output, class_1802.field_22032, class_1802.field_22009, class_1802.field_22003, class_1802.field_22012, class_1802.field_40238);
   }

   private static void diggingSediments(class_8790 output) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_19060}),
            new ItemAbilityIngredient(ItemAbility.SHOVEL_DIG).toVanilla(),
            class_1802.field_8696,
            4
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8110}),
            new ItemAbilityIngredient(ItemAbility.SHOVEL_DIG).toVanilla(),
            class_1802.field_8110,
            1
         )
         .addResultWithChance(class_1802.field_8145, 0.1F)
         .build(output);
   }

   private static void salvagingUsingShears(class_8790 output) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8175}), class_1856.method_8106(ConventionalItemTags.SHEAR_TOOLS), class_1802.field_8745, 2
         )
         .addResultWithChance(class_1802.field_8675, 0.5F, 2)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_18138}),
            class_1856.method_8106(ConventionalItemTags.SHEAR_TOOLS),
            class_1802.field_8745,
            2
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8267}), class_1856.method_8106(ConventionalItemTags.SHEAR_TOOLS), class_1802.field_8745, 1
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8577}), class_1856.method_8106(ConventionalItemTags.SHEAR_TOOLS), class_1802.field_8745, 1
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8570}), class_1856.method_8106(ConventionalItemTags.SHEAR_TOOLS), class_1802.field_8745, 1
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{class_1802.field_8370}), class_1856.method_8106(ConventionalItemTags.SHEAR_TOOLS), class_1802.field_8745, 1
         )
         .build(output);
   }

   private static void salvagePlankFromFurniture(
      class_8790 output, class_1935 plank, class_1935 door, class_1935 trapdoor, class_1935 sign, class_1935 hangingSign
   ) {
      CuttingBoardRecipeBuilder.cuttingRecipe(class_1856.method_8091(new class_1935[]{door}), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{trapdoor}), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank
         )
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(class_1856.method_8091(new class_1935[]{sign}), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank)
         .build(output);
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{hangingSign}), new ItemAbilityIngredient(ItemAbility.AXE_DIG).toVanilla(), plank
         )
         .build(output);
   }

   private static void stripLogForBark(class_8790 output, class_1935 log, class_1935 strippedLog) {
      CuttingBoardRecipeBuilder.cuttingRecipe(
            class_1856.method_8091(new class_1935[]{log}), new ItemAbilityIngredient(ItemAbility.AXE_STRIP).toVanilla(), strippedLog
         )
         .addResult((class_1935)ModItems.TREE_BARK.get())
         .addSound(class_3417.field_14675)
         .build(output);
   }
}
