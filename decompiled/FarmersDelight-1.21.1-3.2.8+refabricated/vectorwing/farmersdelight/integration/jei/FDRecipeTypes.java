package vectorwing.farmersdelight.integration.jei;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

public final class FDRecipeTypes {
   public static final RecipeType<class_8786<CookingPotRecipe>> COOKING = RecipeType.createFromVanilla(ModRecipeTypes.COOKING.get());
   public static final RecipeType<class_8786<CuttingBoardRecipe>> CUTTING = RecipeType.createFromVanilla(ModRecipeTypes.CUTTING.get());
   public static final RecipeType<DecompositionDummy> DECOMPOSITION = RecipeType.create("farmersdelight", "decomposition", DecompositionDummy.class);
}
