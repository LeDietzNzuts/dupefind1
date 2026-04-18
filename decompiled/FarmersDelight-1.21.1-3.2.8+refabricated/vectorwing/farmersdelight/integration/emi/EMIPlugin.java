package vectorwing.farmersdelight.integration.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import java.util.List;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_310;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.integration.emi.handler.CookingPotEmiRecipeHandler;
import vectorwing.farmersdelight.integration.emi.recipe.CookingPotEmiRecipe;
import vectorwing.farmersdelight.integration.emi.recipe.CuttingEmiRecipe;
import vectorwing.farmersdelight.integration.emi.recipe.DecompositionEmiRecipe;

@EmiEntrypoint
public class EMIPlugin implements EmiPlugin {
   public void register(EmiRegistry registry) {
      registry.addCategory(FDRecipeCategories.COOKING);
      registry.addCategory(FDRecipeCategories.CUTTING);
      registry.addCategory(FDRecipeCategories.DECOMPOSITION);
      registry.addWorkstation(FDRecipeCategories.COOKING, FDRecipeWorkstations.COOKING_POT);
      registry.addWorkstation(FDRecipeCategories.CUTTING, FDRecipeWorkstations.CUTTING_BOARD);
      registry.addRecipeHandler(ModMenuTypes.COOKING_POT.get(), new CookingPotEmiRecipeHandler());
      registry.addRecipe(
         new EmiCraftingRecipe(
            List.of(EmiStack.of(class_1802.field_8861), EmiStack.of(class_1802.field_8705)),
            EmiStack.of((class_1935)ModItems.WHEAT_DOUGH.get()),
            FarmersDelight.res("wheat_dough_from_water"),
            true
         )
      );

      for (class_8786<CookingPotRecipe> recipe : registry.getRecipeManager().method_30027(ModRecipeTypes.COOKING.get())) {
         registry.addRecipe(
            new CookingPotEmiRecipe(
               recipe.comp_1932(),
               ((CookingPotRecipe)recipe.comp_1933()).method_8117().stream().<EmiIngredient>map(EmiIngredient::of).toList(),
               EmiStack.of(((CookingPotRecipe)recipe.comp_1933()).method_8110(class_310.method_1551().field_1687.method_30349())),
               EmiStack.of(((CookingPotRecipe)recipe.comp_1933()).getOutputContainer()),
               ((CookingPotRecipe)recipe.comp_1933()).getCookTime(),
               ((CookingPotRecipe)recipe.comp_1933()).getExperience()
            )
         );
      }

      for (class_8786<CuttingBoardRecipe> recipe : registry.getRecipeManager().method_30027(ModRecipeTypes.CUTTING.get())) {
         registry.addRecipe(
            new CuttingEmiRecipe(
               recipe.comp_1932(),
               EmiIngredient.of(((CuttingBoardRecipe)recipe.comp_1933()).getTool()),
               EmiIngredient.of((class_1856)((CuttingBoardRecipe)recipe.comp_1933()).method_8117().get(0)),
               ((CuttingBoardRecipe)recipe.comp_1933())
                  .getRollableResults()
                  .stream()
                  .map(chanceResult -> EmiStack.of(chanceResult.stack()).setChance(chanceResult.chance()))
                  .toList()
            )
         );
      }

      registry.addRecipe(new DecompositionEmiRecipe());
   }
}
