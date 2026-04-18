package vectorwing.farmersdelight.integration.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_6328;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

@class_6328
public class CuttingRecipeCategory implements IRecipeCategory<class_8786<CuttingBoardRecipe>> {
   public static final int OUTPUT_GRID_X = 76;
   public static final int OUTPUT_GRID_Y = 10;
   private final IDrawable slot;
   private final IDrawable slotChance;
   private final class_2561 title = TextUtils.getTranslation("jei.cutting");
   private final IDrawable background;
   private final IDrawable icon;

   public CuttingRecipeCategory(IGuiHelper helper) {
      class_2960 backgroundImage = class_2960.method_60655("farmersdelight", "textures/gui/jei/cutting_board.png");
      this.slot = helper.createDrawable(backgroundImage, 0, 58, 18, 18);
      this.slotChance = helper.createDrawable(backgroundImage, 18, 58, 18, 18);
      this.background = helper.createDrawable(backgroundImage, 0, 0, 117, 57);
      this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new class_1799((class_1935)ModItems.CUTTING_BOARD.get()));
   }

   public RecipeType<class_8786<CuttingBoardRecipe>> getRecipeType() {
      return FDRecipeTypes.CUTTING;
   }

   public class_2561 getTitle() {
      return this.title;
   }

   public IDrawable getBackground() {
      return this.background;
   }

   public IDrawable getIcon() {
      return this.icon;
   }

   public void setRecipe(IRecipeLayoutBuilder builder, class_8786<CuttingBoardRecipe> holder, IFocusGroup focusGroup) {
      CuttingBoardRecipe recipe = (CuttingBoardRecipe)holder.comp_1933();
      builder.addSlot(RecipeIngredientRole.INPUT, 16, 8).addIngredients(recipe.getTool());
      builder.addSlot(RecipeIngredientRole.INPUT, 16, 27).addIngredients((class_1856)recipe.method_8117().get(0));
      class_2371<ChanceResult> recipeOutputs = recipe.getRollableResults();
      int size = recipeOutputs.size();
      int centerX = size > 1 ? 1 : 10;
      int centerY = size > 2 ? 1 : 10;

      for (int i = 0; i < size; i++) {
         int xOffset = centerX + (i % 2 == 0 ? 0 : 19);
         int yOffset = centerY + i / 2 * 19;
         int index = i;
         ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.OUTPUT, 76 + xOffset, 10 + yOffset)
               .addItemStack(((ChanceResult)recipeOutputs.get(i)).stack()))
            .addTooltipCallback((slotView, tooltip) -> {
               ChanceResult output = (ChanceResult)recipeOutputs.get(index);
               float chance = output.chance();
               if (chance != 1.0F) {
                  tooltip.add(1, TextUtils.getTranslation("jei.chance", chance < 0.01 ? "<1" : (int)(chance * 100.0F)).method_27692(class_124.field_1065));
               }
            });
      }
   }

   public void draw(class_8786<CuttingBoardRecipe> holder, IRecipeSlotsView recipeSlotsView, class_332 guiGraphics, double mouseX, double mouseY) {
      CuttingBoardRecipe recipe = (CuttingBoardRecipe)holder.comp_1933();
      class_2371<ChanceResult> recipeOutputs = recipe.getRollableResults();
      int size = recipe.getResults().size();
      int centerX = size > 1 ? 0 : 9;
      int centerY = size > 2 ? 0 : 9;

      for (int i = 0; i < size; i++) {
         int xOffset = centerX + (i % 2 == 0 ? 0 : 19);
         int yOffset = centerY + i / 2 * 19;
         if (((ChanceResult)recipeOutputs.get(i)).chance() != 1.0F) {
            this.slotChance.draw(guiGraphics, 76 + xOffset, 10 + yOffset);
         } else {
            this.slot.draw(guiGraphics, 76 + xOffset, 10 + yOffset);
         }
      }
   }
}
