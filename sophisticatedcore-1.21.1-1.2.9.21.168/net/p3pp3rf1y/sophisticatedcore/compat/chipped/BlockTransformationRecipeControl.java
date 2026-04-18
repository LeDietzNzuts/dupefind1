package net.p3pp3rf1y.sophisticatedcore.compat.chipped;

import java.util.List;
import net.minecraft.class_1109;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.WidgetBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;

public class BlockTransformationRecipeControl extends WidgetBase {
   private static final TextureBlitData SLIDER = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 131), Dimension.RECTANGLE_12_15);
   private static final TextureBlitData DISABLED_SLIDER = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(41, 131), Dimension.RECTANGLE_12_15
   );
   private static final TextureBlitData RECIPE_BACKGROUND = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(110, 148), Dimension.RECTANGLE_16_18
   );
   private static final TextureBlitData SELECTED_RECIPE_BACKGROUND = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(110, 166), Dimension.RECTANGLE_16_18
   );
   private static final TextureBlitData RECIPE_BACKGROUND_HOVERED = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(110, 184), Dimension.RECTANGLE_16_18
   );
   private static final TextureBlitData LIST_BACKGROUND = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 146), new Dimension(81, 56)
   );
   private static final int LIST_Y_OFFSET = 22;
   private static final int INPUT_SLOT_HEIGHT = 18;
   private static final int SPACING = 4;
   private boolean clickedOnScroll;
   private final StorageScreenBase<?> screen;
   private final BlockTransformationRecipeContainer container;
   private boolean hasItemsInInputSlot;
   private int recipeIndexOffset;
   private float sliderProgress;

   protected BlockTransformationRecipeControl(StorageScreenBase<?> screen, BlockTransformationRecipeContainer container, Position position) {
      super(position, new Dimension(81, 108));
      this.screen = screen;
      this.container = container;
      container.setInventoryUpdateListener(this::onInventoryUpdate);
      this.onInventoryUpdate();
   }

   public void moveSlotsToView() {
      class_1735 inputSlot = this.container.getInputSlot();
      inputSlot.field_7873 = this.x + this.getCenteredX(16) - this.screen.sophisticatedCore_getGuiLeft();
      inputSlot.field_7872 = this.y - this.screen.sophisticatedCore_getGuiTop() + 1;
      class_1735 outputSlot = this.container.getOutputSlot();
      outputSlot.field_7873 = this.x + this.getCenteredX(16) - this.screen.sophisticatedCore_getGuiLeft();
      outputSlot.field_7872 = inputSlot.field_7872 + 18 + 4 + LIST_BACKGROUND.getHeight() + 4 + 4;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      GuiHelper.renderSlotsBackground(guiGraphics, this.x + this.getCenteredX(18), this.y, 1, 1);
      GuiHelper.blit(guiGraphics, this.x, this.y + 22, LIST_BACKGROUND);
      GuiHelper.blit(guiGraphics, this.x + this.getCenteredX(26), this.y + 18 + 4 + LIST_BACKGROUND.getHeight() + 4, GuiHelper.CRAFTING_RESULT_SLOT);
      int sliderYOffset = (int)(39.0F * this.sliderProgress) + 1;
      GuiHelper.blit(guiGraphics, this.x + 68, this.y + 22 + sliderYOffset, this.canScroll() ? SLIDER : DISABLED_SLIDER);
      int listInnerLeftX = this.x + 1;
      int listTopY = this.getListTopY();
      int recipeIndexOffsetMax = this.recipeIndexOffset + 12;
      this.renderRecipeBackgrounds(guiGraphics, mouseX, mouseY, listInnerLeftX, listTopY, recipeIndexOffsetMax);
      this.drawRecipesItems(guiGraphics, listInnerLeftX, listTopY, recipeIndexOffsetMax);
   }

   private void drawRecipesItems(class_332 guiGraphics, int listInnerLeftX, int top, int recipeIndexOffsetMax) {
      List<class_1799> results = this.container.getResults();

      for (int i = this.recipeIndexOffset; i < recipeIndexOffsetMax && i < results.size(); i++) {
         int j = i - this.recipeIndexOffset;
         int k = listInnerLeftX + j % 4 * 16;
         int l = j / 4;
         int i1 = top + l * 18 + 2;
         GuiHelper.renderItemInGUI(guiGraphics, this.minecraft, results.get(i), k, i1);
      }
   }

   private int getListTopY() {
      return this.y + 22;
   }

   private void renderRecipeBackgrounds(class_332 guiGraphics, int mouseX, int mouseY, int listInnerLeftX, int listTopY, int recipeIndexOffsetMax) {
      for (int recipeIndex = this.recipeIndexOffset; recipeIndex < recipeIndexOffsetMax && recipeIndex < this.container.getResults().size(); recipeIndex++) {
         int j = recipeIndex - this.recipeIndexOffset;
         int recipeX = listInnerLeftX + j % 4 * 16;
         int row = j / 4;
         int recipeY = listTopY + row * 18 + 2;
         TextureBlitData background = RECIPE_BACKGROUND;
         if (recipeIndex == this.container.getSelectedRecipe()) {
            background = SELECTED_RECIPE_BACKGROUND;
         } else if (mouseX >= recipeX && mouseY >= recipeY && mouseX < recipeX + 16 && mouseY < recipeY + 18) {
            background = RECIPE_BACKGROUND_HOVERED;
         }

         GuiHelper.blit(guiGraphics, recipeX, recipeY - 1, background);
      }
   }

   private boolean canScroll() {
      return this.hasItemsInInputSlot && this.container.getResults().size() > 12;
   }

   @Override
   public void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      this.renderHoveredTooltip(guiGraphics, mouseX, mouseY);
   }

   private void renderHoveredTooltip(class_332 guiGraphics, int mouseX, int mouseY) {
      if (this.hasItemsInInputSlot) {
         int listTopY = this.getListTopY();
         int k = this.recipeIndexOffset + 12;
         List<class_1799> results = this.container.getResults();

         for (int recipeIndex = this.recipeIndexOffset; recipeIndex < k && recipeIndex < results.size(); recipeIndex++) {
            int inviewRecipeIndex = recipeIndex - this.recipeIndexOffset;
            int recipeLeftX = this.x + inviewRecipeIndex % 4 * 16;
            int k1 = listTopY + inviewRecipeIndex / 4 * 18 + 2;
            if (mouseX >= recipeLeftX && mouseX < recipeLeftX + 16 && mouseY >= k1 && mouseY < k1 + 18) {
               this.renderTooltip(guiGraphics, results.get(recipeIndex), mouseX, mouseY);
            }
         }
      }
   }

   private void renderTooltip(class_332 guiGraphics, class_1799 itemStack, int mouseX, int mouseY) {
      guiGraphics.method_51434(this.font, class_437.method_25408(this.minecraft, itemStack), mouseX, mouseY);
   }

   private void onInventoryUpdate() {
      this.hasItemsInInputSlot = this.container.hasItemsInInputSlot();
      if (!this.hasItemsInInputSlot) {
         this.sliderProgress = 0.0F;
         this.recipeIndexOffset = 0;
      } else if (this.container.getSelectedRecipe() - this.recipeIndexOffset >= 12) {
         int rowsToScroll = (this.container.getSelectedRecipe() - this.recipeIndexOffset - 12) / 4 + 1;
         this.scrollRecipesByDelta(-rowsToScroll);
      }
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      this.clickedOnScroll = false;
      if (this.hasItemsInInputSlot) {
         int listInnerLeftX = this.x + 1;
         int listInnerTopY = this.y + 22 + 1;
         int maxRecipeIndexOffset = this.recipeIndexOffset + 12;

         for (int recipeIndex = this.recipeIndexOffset; recipeIndex < maxRecipeIndexOffset; recipeIndex++) {
            int visibleRecipeIndex = recipeIndex - this.recipeIndexOffset;
            double relativeX = mouseX - (listInnerLeftX + visibleRecipeIndex % 4 * 16);
            double relativeY = mouseY - (listInnerTopY + Math.floorDiv(visibleRecipeIndex, 4) * 18);
            if (relativeX >= 0.0 && relativeY >= 0.0 && relativeX < 16.0 && relativeY < 18.0 && this.container.selectRecipeIndex(recipeIndex)) {
               class_310.method_1551().method_1483().method_4873(class_1109.method_4758(class_3417.field_17711, 1.0F));
               return true;
            }
         }

         int sliderLeftX = listInnerLeftX + 67;
         if (mouseX >= sliderLeftX && mouseX < sliderLeftX + 12 && mouseY >= listInnerTopY && mouseY < listInnerTopY + 54) {
            this.clickedOnScroll = true;
            return true;
         }
      }

      return super.method_25402(mouseX, mouseY, button);
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double dragX, double dragY) {
      if (this.clickedOnScroll && this.canScroll()) {
         int listTopY = this.y + 22;
         int listBottomY = listTopY + 54;
         this.sliderProgress = ((float)mouseY - listTopY - 7.5F) / (listBottomY - listTopY - 15.0F);
         this.sliderProgress = class_3532.method_15363(this.sliderProgress, 0.0F, 1.0F);
         this.recipeIndexOffset = (int)(this.sliderProgress * this.getHiddenRows() + 0.5) * 4;
         return true;
      } else {
         return super.method_25403(mouseX, mouseY, button, dragX, dragY);
      }
   }

   public boolean method_25401(double mouseX, double mouseY, double scrollX, double scrollY) {
      if (this.canScroll()) {
         this.scrollRecipesByDelta(scrollY);
      }

      return true;
   }

   private void scrollRecipesByDelta(double delta) {
      int i = this.getHiddenRows();
      this.sliderProgress = (float)(this.sliderProgress - delta / i);
      this.sliderProgress = class_3532.method_15363(this.sliderProgress, 0.0F, 1.0F);
      this.recipeIndexOffset = (int)(this.sliderProgress * i + 0.5) * 4;
   }

   protected int getHiddenRows() {
      return (this.container.getResults().size() + 4 - 1) / 4 - 3;
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }
}
