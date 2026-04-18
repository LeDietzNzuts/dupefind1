package vectorwing.farmersdelight.integration.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5684;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.integration.emi.FDRecipeCategories;

public class CookingPotEmiRecipe implements EmiRecipe {
   private static final class_2960 BACKGROUND = class_2960.method_60655("farmersdelight", "textures/gui/cooking_pot.png");
   private final class_2960 id;
   private final List<EmiIngredient> inputs;
   private final EmiStack output;
   private final EmiStack container;
   private final int cookTime;
   private final float experience;
   private final List<class_5684> tooltipComponents;

   public CookingPotEmiRecipe(class_2960 id, List<EmiIngredient> inputs, EmiStack output, EmiStack container, int cookTime, float experience) {
      this.id = id;
      this.inputs = inputs;
      this.output = output;
      this.container = container;
      this.cookTime = cookTime;
      this.experience = experience;
      this.tooltipComponents = this.createTooltipComponents();
   }

   private List<class_5684> createTooltipComponents() {
      List<class_5684> tooltipStrings = new ArrayList<>();
      if (this.cookTime > 0) {
         int cookTimeSeconds = this.cookTime / 20;
         tooltipStrings.add(class_5684.method_32662(class_2561.method_43469("emi.cooking.time", new Object[]{cookTimeSeconds}).method_30937()));
      }

      if (this.experience > 0.0F) {
         tooltipStrings.add(class_5684.method_32662(class_2561.method_43469("emi.cooking.experience", new Object[]{this.experience}).method_30937()));
      }

      return tooltipStrings;
   }

   public EmiRecipeCategory getCategory() {
      return FDRecipeCategories.COOKING;
   }

   @Nullable
   public class_2960 getId() {
      return this.id;
   }

   public List<EmiIngredient> getInputs() {
      return this.inputs;
   }

   public List<EmiStack> getOutputs() {
      return List.of(this.output);
   }

   public List<EmiIngredient> getCatalysts() {
      return List.of(this.container);
   }

   public int getDisplayWidth() {
      return 117;
   }

   public int getDisplayHeight() {
      return 56;
   }

   public void addWidgets(WidgetHolder widgets) {
      widgets.addTexture(BACKGROUND, 0, 0, 116, 56, 29, 16);
      int borderSlotSize = 18;

      for (int row = 0; row < 2; row++) {
         for (int column = 0; column < 3; column++) {
            int inputIndex = row * 3 + column;
            if (inputIndex < this.inputs.size()) {
               this.addSlot(widgets, this.inputs.get(inputIndex), column * borderSlotSize, row * borderSlotSize);
            }
         }
      }

      this.addSlot(widgets, this.output, 94, 9);
      this.addSlot(widgets, this.container, 62, 38);
      this.addSlot(widgets, this.output, 94, 38).recipeContext(this);
      widgets.addAnimatedTexture(BACKGROUND, 60, 9, 24, 17, 176, 15, 10000, true, false, false);
      widgets.addTexture(BACKGROUND, 18, 39, 17, 15, 176, 0);
      widgets.addTexture(BACKGROUND, 64, 2, 8, 11, 176, 32);
      if (this.experience > 0.0F) {
         widgets.addTexture(BACKGROUND, 63, 21, 9, 9, 176, 43);
      }

      widgets.addTooltip(
         (mouseX, mouseY) -> ClientRenderUtils.isCursorInsideBounds(60, 2, 22, 28, mouseX.intValue(), mouseY.intValue()) ? this.tooltipComponents : List.of(),
         0,
         0,
         widgets.getWidth(),
         widgets.getHeight()
      );
   }

   private SlotWidget addSlot(WidgetHolder widgets, EmiIngredient ingredient, int x, int y) {
      return widgets.addSlot(ingredient, x, y).drawBack(false);
   }
}
