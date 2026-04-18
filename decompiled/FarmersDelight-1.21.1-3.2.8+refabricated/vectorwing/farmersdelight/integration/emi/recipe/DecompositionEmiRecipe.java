package vectorwing.farmersdelight.integration.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import java.util.List;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5684;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.integration.emi.FDRecipeCategories;
import vectorwing.farmersdelight.integration.emi.FDRecipeWorkstations;

public class DecompositionEmiRecipe implements EmiRecipe {
   private static final class_2960 BACKGROUND = class_2960.method_60655("farmersdelight", "textures/gui/jei/decomposition.png");
   private static final EmiStack RICH_SOIL = EmiStack.of((class_1935)ModItems.RICH_SOIL.get());
   private static final EmiIngredient ACCELERATORS = EmiIngredient.of(ModTags.COMPOST_ACTIVATORS);
   private static final class_5684 LIGHT_TOOLTIP = createTooltip(".light");
   private static final class_5684 FLUID_TOOLTIP = createTooltip(".fluid");
   private static final class_5684 ACCELERATORS_TOOLTIP = createTooltip(".accelerators");

   public EmiRecipeCategory getCategory() {
      return FDRecipeCategories.DECOMPOSITION;
   }

   @Nullable
   public class_2960 getId() {
      return FarmersDelight.res("/decomposition/dummy");
   }

   public List<EmiIngredient> getInputs() {
      return List.of(FDRecipeWorkstations.ORGANIC_COMPOST);
   }

   public List<EmiStack> getOutputs() {
      return List.of(RICH_SOIL);
   }

   public int getDisplayWidth() {
      return 102;
   }

   public int getDisplayHeight() {
      return 62;
   }

   public void addWidgets(WidgetHolder widgets) {
      widgets.addTexture(BACKGROUND, 0, 0, 102, 41, 8, 9);
      this.addSlot(widgets, FDRecipeWorkstations.ORGANIC_COMPOST, 0, 16);
      this.addSlot(widgets, RICH_SOIL, 84, 16).recipeContext(this);
      this.addSlot(widgets, ACCELERATORS, 55, 44);
      widgets.addTooltip((mouseX, mouseY) -> {
         if (ClientRenderUtils.isCursorInsideBounds(32, 30, 11, 11, mouseX.intValue(), mouseY.intValue())) {
            return List.of(LIGHT_TOOLTIP);
         } else if (ClientRenderUtils.isCursorInsideBounds(45, 30, 11, 11, mouseX.intValue(), mouseY.intValue())) {
            return List.of(FLUID_TOOLTIP);
         } else {
            return ClientRenderUtils.isCursorInsideBounds(59, 30, 11, 11, mouseX.intValue(), mouseY.intValue()) ? List.of(ACCELERATORS_TOOLTIP) : List.of();
         }
      }, 0, 0, widgets.getWidth(), widgets.getHeight());
   }

   private SlotWidget addSlot(WidgetHolder widgets, EmiIngredient ingredient, int x, int y) {
      return widgets.addSlot(ingredient, x, y).backgroundTexture(BACKGROUND, 119, 0);
   }

   private static class_5684 createTooltip(@NotNull String suffix) {
      return class_5684.method_32662(class_2561.method_43471("farmersdelight.jei.decomposition" + suffix).method_30937());
   }
}
