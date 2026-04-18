package vectorwing.farmersdelight.integration.jei.category;

import java.util.ArrayList;
import java.util.List;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_5250;
import net.minecraft.class_6328;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

@class_6328
public class DecompositionRecipeCategory implements IRecipeCategory<DecompositionDummy> {
   public static final class_2960 UID = class_2960.method_60655("farmersdelight", "decomposition");
   private static final int slotSize = 22;
   private final class_2561 title = TextUtils.getTranslation("jei.decomposition");
   private final IDrawable background;
   private final IDrawable slotIcon;
   private final IDrawable icon;
   private final class_1799 organicCompost;
   private final class_1799 richSoil;

   public DecompositionRecipeCategory(IGuiHelper helper) {
      class_2960 backgroundImage = class_2960.method_60655("farmersdelight", "textures/gui/jei/decomposition.png");
      this.background = helper.createDrawable(backgroundImage, 0, 0, 118, 80);
      this.organicCompost = new class_1799((class_1935)ModBlocks.ORGANIC_COMPOST.get());
      this.richSoil = new class_1799((class_1935)ModItems.RICH_SOIL.get());
      this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, this.richSoil);
      this.slotIcon = helper.createDrawable(backgroundImage, 119, 0, 22, 22);
   }

   public RecipeType<DecompositionDummy> getRecipeType() {
      return FDRecipeTypes.DECOMPOSITION;
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

   public void setRecipe(IRecipeLayoutBuilder builder, DecompositionDummy recipe, IFocusGroup focusGroup) {
      List<class_1799> accelerators = new ArrayList<>();
      class_7923.field_41175
         .method_40266(ModTags.COMPOST_ACTIVATORS)
         .ifPresent(s -> s.forEach(f -> accelerators.add(new class_1799((class_1935)f.comp_349()))));
      builder.addSlot(RecipeIngredientRole.INPUT, 9, 26).addItemStack(this.organicCompost);
      builder.addSlot(RecipeIngredientRole.OUTPUT, 93, 26).addItemStack(this.richSoil);
      builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 64, 54).addItemStacks(accelerators);
   }

   public void draw(DecompositionDummy recipe, IRecipeSlotsView recipeSlotsView, class_332 guiGraphics, double mouseX, double mouseY) {
      this.slotIcon.draw(guiGraphics, 63, 53);
   }

   public void getTooltip(ITooltipBuilder tooltip, DecompositionDummy recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
      if (ClientRenderUtils.isCursorInsideBounds(40, 38, 11, 11, mouseX, mouseY)) {
         tooltip.add(translateKey(".light"));
      } else if (ClientRenderUtils.isCursorInsideBounds(53, 38, 11, 11, mouseX, mouseY)) {
         tooltip.add(translateKey(".fluid"));
      } else if (ClientRenderUtils.isCursorInsideBounds(67, 38, 11, 11, mouseX, mouseY)) {
         tooltip.add(translateKey(".accelerators"));
      }
   }

   private static class_5250 translateKey(@NotNull String suffix) {
      return class_2561.method_43471("farmersdelight.jei.decomposition" + suffix);
   }
}
