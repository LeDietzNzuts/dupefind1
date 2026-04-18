package vectorwing.farmersdelight.data.recipe;

import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2454;
import net.minecraft.class_2960;
import net.minecraft.class_7800;
import net.minecraft.class_8790;
import net.minecraft.class_2066.class_2068;
import vectorwing.farmersdelight.common.registry.ModItems;

public class SmeltingRecipes {
   public static void register(class_8790 output) {
      foodSmeltingRecipes("fried_egg", class_1802.field_8803, (class_1935)ModItems.FRIED_EGG.get(), 0.35F, output);
      foodSmeltingRecipes("beef_patty", (class_1935)ModItems.MINCED_BEEF.get(), (class_1935)ModItems.BEEF_PATTY.get(), 0.35F, output);
      foodSmeltingRecipes("cooked_chicken_cuts", (class_1935)ModItems.CHICKEN_CUTS.get(), (class_1935)ModItems.COOKED_CHICKEN_CUTS.get(), 0.35F, output);
      foodSmeltingRecipes("cooked_cod_slice", (class_1935)ModItems.COD_SLICE.get(), (class_1935)ModItems.COOKED_COD_SLICE.get(), 0.35F, output);
      foodSmeltingRecipes("cooked_salmon_slice", (class_1935)ModItems.SALMON_SLICE.get(), (class_1935)ModItems.COOKED_SALMON_SLICE.get(), 0.35F, output);
      foodSmeltingRecipes("cooked_bacon", (class_1935)ModItems.BACON.get(), (class_1935)ModItems.COOKED_BACON.get(), 0.35F, output);
      foodSmeltingRecipes("cooked_mutton_chops", (class_1935)ModItems.MUTTON_CHOPS.get(), (class_1935)ModItems.COOKED_MUTTON_CHOPS.get(), 0.35F, output);
      class_2454.method_17802(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WHEAT_DOUGH.get()}), class_7800.field_40640, class_1802.field_8229, 0.35F, 200
         )
         .method_10469("has_dough", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.WHEAT_DOUGH.get()}))
         .method_36443(output, class_2960.method_60655("farmersdelight", "bread") + "_from_smelting");
      class_2454.method_35918(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.WHEAT_DOUGH.get()}), class_7800.field_40640, class_1802.field_8229, 0.35F, 100
         )
         .method_10469("has_dough", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.WHEAT_DOUGH.get()}))
         .method_36443(output, class_2960.method_60655("farmersdelight", "bread") + "_from_smoking");
      class_2454.method_35918(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.HAM.get()}), class_7800.field_40640, (class_1935)ModItems.SMOKED_HAM.get(), 0.35F, 200
         )
         .method_10469("has_ham", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.HAM.get()}))
         .method_10431(output);
      class_2454.method_17802(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.IRON_KNIFE.get()}), class_7800.field_40642, class_1802.field_8675, 0.1F, 200
         )
         .method_10469("has_iron_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.IRON_KNIFE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "iron_nugget_from_smelting_knife"));
      class_2454.method_17802(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.GOLDEN_KNIFE.get()}), class_7800.field_40642, class_1802.field_8397, 0.1F, 200
         )
         .method_10469("has_golden_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.GOLDEN_KNIFE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "gold_nugget_from_smelting_knife"));
      class_2454.method_10473(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.IRON_KNIFE.get()}), class_7800.field_40642, class_1802.field_8675, 0.1F, 100
         )
         .method_10469("has_iron_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.IRON_KNIFE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "iron_nugget_from_blasting_knife"));
      class_2454.method_10473(
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.GOLDEN_KNIFE.get()}), class_7800.field_40642, class_1802.field_8397, 0.1F, 100
         )
         .method_10469("has_golden_knife", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.GOLDEN_KNIFE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "gold_nugget_from_blasting_knife"));
   }

   private static void foodSmeltingRecipes(String name, class_1935 ingredient, class_1935 result, float experience, class_8790 output) {
      String namePrefix = class_2960.method_60655("farmersdelight", name).toString();
      class_2454.method_17802(class_1856.method_8091(new class_1935[]{ingredient}), class_7800.field_40640, result, experience, 200)
         .method_10469(name, class_2068.method_8959(new class_1935[]{ingredient}))
         .method_10431(output);
      class_2454.method_35916(class_1856.method_8091(new class_1935[]{ingredient}), class_7800.field_40640, result, experience, 600)
         .method_10469(name, class_2068.method_8959(new class_1935[]{ingredient}))
         .method_36443(output, namePrefix + "_from_campfire_cooking");
      class_2454.method_35918(class_1856.method_8091(new class_1935[]{ingredient}), class_7800.field_40640, result, experience, 100)
         .method_10469(name, class_2068.method_8959(new class_1935[]{ingredient}))
         .method_36443(output, namePrefix + "_from_smoking");
   }
}
