package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.minecraft.class_1860;
import net.minecraft.class_3956;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModRecipeTypes {
   public static final Supplier<class_3956<CookingPotRecipe>> COOKING = RegUtils.regRecipe("cooking", () -> registerRecipeType("cooking"));
   public static final Supplier<class_3956<CuttingBoardRecipe>> CUTTING = RegUtils.regRecipe("cutting", () -> registerRecipeType("cutting"));

   public static <T extends class_1860<?>> class_3956<T> registerRecipeType(String identifier) {
      return new class_3956<T>() {
         @Override
         public String toString() {
            return "farmersdelight:" + identifier;
         }
      };
   }

   public static void touch() {
   }
}
