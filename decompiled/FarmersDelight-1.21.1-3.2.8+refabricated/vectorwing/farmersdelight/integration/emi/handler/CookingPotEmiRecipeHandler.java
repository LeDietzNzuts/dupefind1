package vectorwing.farmersdelight.integration.emi.handler;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1735;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.integration.emi.FDRecipeCategories;

public class CookingPotEmiRecipeHandler implements StandardRecipeHandler<CookingPotMenu> {
   public List<class_1735> getInputSources(CookingPotMenu handler) {
      List<class_1735> slots = new ArrayList<>();

      for (int i = 0; i < 7; i++) {
         slots.add(handler.method_7611(i));
      }

      for (int i = 9; i < 45; i++) {
         slots.add(handler.method_7611(i));
      }

      return slots;
   }

   public List<class_1735> getCraftingSlots(CookingPotMenu handler) {
      List<class_1735> slots = new ArrayList<>();

      for (int i = 0; i < 7; i++) {
         slots.add(handler.method_7611(i));
      }

      return slots;
   }

   @Nullable
   public class_1735 getOutputSlot(CookingPotMenu handler) {
      return (class_1735)handler.field_7761.get(8);
   }

   public boolean supportsRecipe(EmiRecipe recipe) {
      return recipe.getCategory() == FDRecipeCategories.COOKING && recipe.supportsRecipeTree();
   }
}
