package vectorwing.farmersdelight.integration.jei;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1863;
import net.minecraft.class_1867;
import net.minecraft.class_1935;
import net.minecraft.class_2371;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3955;
import net.minecraft.class_638;
import net.minecraft.class_7710;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

public class FDRecipes {
   private final class_1863 recipeManager;

   public FDRecipes() {
      class_310 minecraft = class_310.method_1551();
      class_638 level = minecraft.field_1687;
      if (level != null) {
         this.recipeManager = level.method_8433();
      } else {
         throw new NullPointerException("minecraft world must not be null.");
      }
   }

   public List<class_8786<CookingPotRecipe>> getCookingPotRecipes() {
      return this.recipeManager.method_30027(ModRecipeTypes.COOKING.get());
   }

   public List<class_8786<CuttingBoardRecipe>> getCuttingBoardRecipes() {
      return this.recipeManager.method_30027(ModRecipeTypes.CUTTING.get());
   }

   public List<class_8786<class_3955>> getSpecialWheatDoughRecipe() {
      Optional<class_8786<?>> specialRecipe = this.recipeManager.method_8130(class_2960.method_60655("farmersdelight", "wheat_dough_from_water"));
      List<class_8786<class_3955>> recipes = Lists.newArrayList();
      specialRecipe.ifPresent(
         recipe -> {
            class_2371<class_1856> inputs = class_2371.method_10212(
               class_1856.field_9017,
               new class_1856[]{
                  class_1856.method_8091(new class_1935[]{class_1802.field_8861}), class_1856.method_8091(new class_1935[]{class_1802.field_8705})
               }
            );
            class_1799 output = new class_1799((class_1935)ModItems.WHEAT_DOUGH.get());
            class_2960 id = recipe.comp_1932();
            class_3955 newRecipe = new class_1867("fd_dough", class_7710.field_40251, output, inputs);
            recipes.add(new class_8786(id, newRecipe));
         }
      );
      return recipes;
   }
}
