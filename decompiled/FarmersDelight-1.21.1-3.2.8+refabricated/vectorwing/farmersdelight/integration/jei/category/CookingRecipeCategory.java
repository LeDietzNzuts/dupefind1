package vectorwing.farmersdelight.integration.jei.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_6328;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.common.utility.RecipeUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

@class_6328
public class CookingRecipeCategory implements IRecipeCategory<class_8786<CookingPotRecipe>> {
   protected final IDrawable heatIndicator;
   protected final IDrawable timeIcon;
   protected final IDrawable expIcon;
   protected final IDrawableAnimated arrow;
   private final class_2561 title = TextUtils.getTranslation("jei.cooking");
   private final IDrawable background;
   private final IDrawable icon;

   public CookingRecipeCategory(IGuiHelper helper) {
      class_2960 widgetBackgroundImage = class_2960.method_60655("farmersdelight", "textures/gui/jei/cooking_pot.png");
      class_2960 interfaceImage = class_2960.method_60655("farmersdelight", "textures/gui/cooking_pot.png");
      this.background = helper.createDrawable(widgetBackgroundImage, 0, 0, 116, 56);
      this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new class_1799((class_1935)ModItems.COOKING_POT.get()));
      this.heatIndicator = helper.createDrawable(interfaceImage, 176, 0, 17, 15);
      this.timeIcon = helper.createDrawable(interfaceImage, 176, 32, 8, 11);
      this.expIcon = helper.createDrawable(interfaceImage, 176, 43, 9, 9);
      this.arrow = helper.drawableBuilder(interfaceImage, 176, 15, 24, 17).buildAnimated(200, StartDirection.LEFT, false);
   }

   public RecipeType<class_8786<CookingPotRecipe>> getRecipeType() {
      return FDRecipeTypes.COOKING;
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

   public void setRecipe(IRecipeLayoutBuilder builder, class_8786<CookingPotRecipe> holder, IFocusGroup focusGroup) {
      CookingPotRecipe recipe = (CookingPotRecipe)holder.comp_1933();
      class_2371<class_1856> recipeIngredients = recipe.method_8117();
      class_1799 resultStack = RecipeUtils.getResultItem(recipe);
      class_1799 containerStack = recipe.getOutputContainer();
      int borderSlotSize = 18;

      for (int row = 0; row < 2; row++) {
         for (int column = 0; column < 3; column++) {
            int inputIndex = row * 3 + column;
            if (inputIndex < recipeIngredients.size()) {
               builder.addSlot(RecipeIngredientRole.INPUT, column * borderSlotSize + 1, row * borderSlotSize + 1)
                  .addItemStacks(Arrays.asList(((class_1856)recipeIngredients.get(inputIndex)).method_8105()));
            }
         }
      }

      builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 10).addItemStack(resultStack);
      if (!containerStack.method_7960()) {
         builder.addSlot(RecipeIngredientRole.CATALYST, 63, 39).addItemStack(containerStack);
      }

      builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 39).addItemStack(resultStack);
   }

   public void draw(class_8786<CookingPotRecipe> holder, IRecipeSlotsView recipeSlotsView, class_332 guiGraphics, double mouseX, double mouseY) {
      this.arrow.draw(guiGraphics, 60, 9);
      this.heatIndicator.draw(guiGraphics, 18, 39);
      this.timeIcon.draw(guiGraphics, 64, 2);
      if (((CookingPotRecipe)holder.comp_1933()).getExperience() > 0.0F) {
         this.expIcon.draw(guiGraphics, 63, 21);
      }
   }

   public List<class_2561> getTooltipStrings(class_8786<CookingPotRecipe> holder, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
      CookingPotRecipe recipe = (CookingPotRecipe)holder.comp_1933();
      if (ClientRenderUtils.isCursorInsideBounds(61, 2, 22, 28, mouseX, mouseY)) {
         List<class_2561> tooltipStrings = new ArrayList<>();
         int cookTime = recipe.getCookTime();
         if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            tooltipStrings.add(class_2561.method_43469("gui.jei.category.smelting.time.seconds", new Object[]{cookTimeSeconds}));
         }

         float experience = recipe.getExperience();
         if (experience > 0.0F) {
            tooltipStrings.add(class_2561.method_43469("gui.jei.category.smelting.experience", new Object[]{experience}));
         }

         return tooltipStrings;
      } else {
         return Collections.emptyList();
      }
   }
}
