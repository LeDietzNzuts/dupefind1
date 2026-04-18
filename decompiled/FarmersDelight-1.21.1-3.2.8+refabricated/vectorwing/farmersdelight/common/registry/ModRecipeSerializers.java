package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.minecraft.class_1865;
import net.minecraft.class_1866;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.DoughRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModRecipeSerializers {
   public static final Supplier<class_1865<CookingPotRecipe>> COOKING = RegUtils.regRecipeSerializer("cooking", CookingPotRecipe.Serializer::new);
   public static final Supplier<class_1865<CuttingBoardRecipe>> CUTTING = RegUtils.regRecipeSerializer("cutting", CuttingBoardRecipe.Serializer::new);
   public static final Supplier<class_1865<FoodServingRecipe>> FOOD_SERVING = RegUtils.regRecipeSerializer(
      "food_serving", () -> new class_1866(FoodServingRecipe::new)
   );
   public static final Supplier<class_1865<DoughRecipe>> DOUGH = RegUtils.regRecipeSerializer("dough", () -> new class_1866(DoughRecipe::new));

   public static void touch() {
   }
}
