package net.p3pp3rf1y.sophisticatedcore.upgrades.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3545;
import net.minecraft.class_437;
import net.minecraft.class_8000;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.Button;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinition;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ToggleButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import org.joml.Vector2i;
import org.joml.Vector2ic;

public class CraftingUpgradeTab extends UpgradeSettingsTab<CraftingUpgradeContainer> {
   public static final int RESULT_SELECTION_BORDER_WIDTH = 3;
   private static final TextureBlitData ARROW = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(97, 216), new Dimension(15, 8));
   private static final Dimension DIMENSION_8_12 = new Dimension(8, 12);
   private static final Dimension DIMENSION_16_12 = new Dimension(16, 12);
   private static final TextureBlitData SMALL_BUTTON_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(53, 18), DIMENSION_8_12);
   private static final TextureBlitData SMALL_BUTTON_HOVERED_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(61, 18), DIMENSION_8_12);
   private static final TextureBlitData PREVIOS_RESULT_FOREGROUND = new TextureBlitData(
      GuiHelper.ICONS, new Position(0, 0), Dimension.SQUARE_256, new UV(48, 144), DIMENSION_8_12
   );
   private static final ButtonDefinition PREVIOUS_RESULT = new ButtonDefinition(
      DIMENSION_8_12,
      SMALL_BUTTON_BACKGROUND,
      SMALL_BUTTON_HOVERED_BACKGROUND,
      PREVIOS_RESULT_FOREGROUND,
      class_2561.method_43471(TranslationHelper.INSTANCE.translUpgradeButton("previous_result"))
   );
   private static final TextureBlitData NEXT_RESULT_FOREGROUND = new TextureBlitData(
      GuiHelper.ICONS, new Position(0, 0), Dimension.SQUARE_256, new UV(56, 144), DIMENSION_8_12
   );
   private static final ButtonDefinition NEXT_RESULT = new ButtonDefinition(
      DIMENSION_8_12,
      SMALL_BUTTON_BACKGROUND,
      SMALL_BUTTON_HOVERED_BACKGROUND,
      NEXT_RESULT_FOREGROUND,
      class_2561.method_43471(TranslationHelper.INSTANCE.translUpgradeButton("next_result"))
   );
   private static final TextureBlitData BIG_BUTTON_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(69, 18), DIMENSION_16_12);
   private static final TextureBlitData BIG_BUTTON_HOVERED_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(63, 30), DIMENSION_16_12);
   private static final ButtonDefinition SELECT_RESULT = new ButtonDefinition(
      DIMENSION_16_12,
      BIG_BUTTON_BACKGROUND,
      BIG_BUTTON_HOVERED_BACKGROUND,
      null,
      class_2561.method_43471(TranslationHelper.INSTANCE.translUpgradeButton("select_result"))
   );
   private static final class_8000 LEFT_SIDE_TOOLTIP_POSITIONER = new class_8000() {
      public Vector2ic method_47944(int guiWidth, int guiHeight, int mouseX, int mouseY, int tooltipWidth, int tooltipHeight) {
         Vector2i tooltipTopLeft = new Vector2i(mouseX, mouseY).add(12, -12);
         this.positionTooltip(guiHeight, tooltipTopLeft, tooltipWidth, tooltipHeight);
         return tooltipTopLeft;
      }

      private void positionTooltip(int guiHeight, Vector2i tooltipTopLeft, int tooltipWidth, int tooltipHeight) {
         tooltipTopLeft.x = Math.max(tooltipTopLeft.x - 24 - tooltipWidth, 4);
         int i = tooltipHeight + 3;
         if (tooltipTopLeft.y + i > guiHeight) {
            tooltipTopLeft.y = guiHeight - i;
         }
      }
   };
   private final ICraftingUIPart craftingUIAddition;
   private final Button previousResultButton;
   private final Button nextResultButton;
   private final Button selectResultButton;
   private boolean resultSelectionShown = false;
   private class_3545<Position, Dimension> resultListPositionDimensions;
   private final List<Position> resultChoicePositions = new ArrayList<>();

   public CraftingUpgradeTab(
      CraftingUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen, ButtonDefinition.Toggle<Boolean> shiftClickTargetButton
   ) {
      super(
         upgradeContainer, position, screen, TranslationHelper.INSTANCE.translUpgrade("crafting"), TranslationHelper.INSTANCE.translUpgradeTooltip("crafting")
      );
      this.addHideableChild(
         new ToggleButton<>(
            new Position(this.x + 3, this.y + 24),
            shiftClickTargetButton,
            button -> this.getContainer().setShiftClickIntoStorage(!this.getContainer().shouldShiftClickIntoStorage()),
            this.getContainer()::shouldShiftClickIntoStorage
         )
      );
      this.craftingUIAddition = screen.getCraftingUIAddition();
      this.openTabDimension = new Dimension(63 + this.craftingUIAddition.getWidth(), 142);
      this.previousResultButton = new Button(new Position(this.x + 3 + 6 + this.craftingUIAddition.getWidth(), this.y + 118), PREVIOUS_RESULT, button -> {
         if (button == 0) {
            this.getContainer().selectPreviousCraftingResult();
         }
      }) {
         @Override
         public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
            if (this.visible && this.method_25405(mouseX, mouseY)) {
               guiGraphics.method_51436(
                  this.minecraft.field_1772,
                  this.getTooltip().stream().map(class_2561::method_30937).toList(),
                  CraftingUpgradeTab.LEFT_SIDE_TOOLTIP_POSITIONER,
                  mouseX,
                  mouseY
               );
            }
         }
      };
      this.addHideableChild(this.previousResultButton);
      this.nextResultButton = new Button(new Position(this.x + 3 + 6 + this.craftingUIAddition.getWidth() + 8 + 26, this.y + 118), NEXT_RESULT, button -> {
         if (button == 0) {
            this.getContainer().selectNextCraftingResult();
         }
      });
      this.addHideableChild(this.nextResultButton);
      this.selectResultButton = new Button(new Position(this.x + 3 + 6 + this.craftingUIAddition.getWidth() + 13, this.y + 99), SELECT_RESULT, button -> {
         if (button == 0) {
            this.resultSelectionShown = !this.resultSelectionShown;
         }
      }) {
         @Override
         protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
            super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
            guiGraphics.method_51433(
               this.font, String.valueOf(CraftingUpgradeTab.this.getContainer().getMatchedCraftingResults().size()), this.x + 5, this.y + 2, 16777215, true
            );
         }
      };
      this.addHideableChild(this.selectResultButton);
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      super.renderBg(guiGraphics, minecraft, mouseX, mouseY);
      if (this.getContainer().isOpen()) {
         GuiHelper.renderSlotsBackground(guiGraphics, this.x + 3 + this.craftingUIAddition.getWidth(), this.y + 44, 3, 3);
         GuiHelper.blit(guiGraphics, this.x + 3 + this.craftingUIAddition.getWidth() + 19, this.y + 101, ARROW);
         GuiHelper.blit(guiGraphics, this.x + 3 + this.craftingUIAddition.getWidth() + 14, this.y + 111, GuiHelper.CRAFTING_RESULT_SLOT);
      }
   }

   @Override
   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      List<class_1799> matchedCraftingResults = this.getContainer().getMatchedCraftingResults();
      this.previousResultButton.setVisible(this.shouldShowResultSelection());
      this.nextResultButton.setVisible(this.shouldShowResultSelection());
      this.selectResultButton.setVisible(this.shouldShowResultSelection());
      if (!this.shouldShowResultSelection()) {
         this.resultSelectionShown = false;
         this.resultListPositionDimensions = null;
         this.resultChoicePositions.clear();
      } else if (this.resultSelectionShown) {
         if (this.resultListPositionDimensions == null || this.resultChoicePositions.size() != matchedCraftingResults.size()) {
            this.initResultSelectionPositionDimension(matchedCraftingResults);
            this.resultChoicePositions.clear();

            for (int i = 0; i < matchedCraftingResults.size(); i++) {
               int xOffset = i % 3 * 18;
               int yOffset = i / 3 * 18;
               this.resultChoicePositions
                  .add(
                     new Position(
                        ((Position)this.resultListPositionDimensions.method_15442()).x() + 3 + xOffset,
                        ((Position)this.resultListPositionDimensions.method_15442()).y() + 3 + yOffset
                     )
                  );
            }
         }

         guiGraphics.method_51448().method_22903();
         guiGraphics.method_51448().method_46416(0.0F, 0.0F, 410.0F);
         renderResultSelectionBackground(
            guiGraphics,
            matchedCraftingResults,
            ((Dimension)this.resultListPositionDimensions.method_15441()).width(),
            ((Dimension)this.resultListPositionDimensions.method_15441()).height(),
            ((Position)this.resultListPositionDimensions.method_15442()).x(),
            ((Position)this.resultListPositionDimensions.method_15442()).y()
         );
         this.renderResultChoices(
            guiGraphics,
            matchedCraftingResults,
            ((Position)this.resultListPositionDimensions.method_15442()).x(),
            ((Position)this.resultListPositionDimensions.method_15442()).y()
         );
         this.renderSelectionSlotHover(guiGraphics, mouseX, mouseY);
         guiGraphics.method_51448().method_22909();
      }
   }

   private void renderSelectionSlotHover(class_332 guiGraphics, int mouseX, int mouseY) {
      this.getResultChoiceHovered(mouseX, mouseY).ifPresent(i -> {
         Position position = this.resultChoicePositions.get(i);
         GuiHelper.renderSlotHighlight(guiGraphics, position.x() + 1, position.y() + 1, 0, -2130706433);
      });
   }

   private Optional<Integer> getResultChoiceHovered(int mouseX, int mouseY) {
      if (this.resultSelectionShown && this.resultListPositionDimensions != null) {
         Position pos = (Position)this.resultListPositionDimensions.method_15442();
         Dimension dim = (Dimension)this.resultListPositionDimensions.method_15441();
         int slotsLeftX = pos.x() + 3;
         int slotsTopY = pos.y() + 3;
         if (mouseX >= slotsLeftX && mouseX < pos.x() + dim.width() - 3 && mouseY >= slotsTopY && mouseY < pos.y() + dim.height() - 3) {
            for (int i = 0; i < this.resultChoicePositions.size(); i++) {
               Position position = this.resultChoicePositions.get(i);
               if (mouseX >= position.x() && mouseX < position.x() + 18 && mouseY >= position.y() && mouseY < position.y() + 18) {
                  return Optional.of(i);
               }
            }
         }

         return Optional.empty();
      } else {
         return Optional.empty();
      }
   }

   @Override
   public boolean method_25402(double mouseX, double mouseY, int button) {
      boolean ret = super.method_25402(mouseX, mouseY, button);
      return ret ? true : this.getResultChoiceHovered((int)mouseX, (int)mouseY).map(i -> {
         this.getContainer().selectCraftingResult(i);
         this.resultSelectionShown = false;
         return true;
      }).orElse(false);
   }

   private void initResultSelectionPositionDimension(List<class_1799> matchedCraftingResults) {
      int height = 3 + ((matchedCraftingResults.size() - 1) / 3 + 1) * 18 + 3;
      int width = 3 + Math.min(matchedCraftingResults.size(), 3) * 18 + 3;
      int resultListLeftX = this.selectResultButton.getX() + 8 - 3 - (int)(Math.min(matchedCraftingResults.size(), 3) / 2.0F * 18.0F);
      int resultListTopY = this.selectResultButton.getY() - height;
      this.resultListPositionDimensions = new class_3545(new Position(resultListLeftX, resultListTopY), new Dimension(width, height));
   }

   private static void renderResultSelectionBackground(
      class_332 guiGraphics, List<class_1799> matchedCraftingResults, int width, int height, int resultListLeftX, int resultListTopY
   ) {
      int halfWidth = width / 2;
      int halfHeight = height / 2;
      guiGraphics.method_25290(GuiHelper.GUI_CONTROLS, resultListLeftX, resultListTopY, 85.0F, 24.0F, halfWidth, halfHeight, 256, 256);
      guiGraphics.method_25290(GuiHelper.GUI_CONTROLS, resultListLeftX + halfWidth, resultListTopY, 117.0F - halfWidth, 24.0F, halfWidth, halfHeight, 256, 256);
      guiGraphics.method_25290(GuiHelper.GUI_CONTROLS, resultListLeftX, resultListTopY + halfHeight, 85.0F, 56.0F - halfHeight, halfWidth, halfHeight, 256, 256);
      guiGraphics.method_25290(
         GuiHelper.GUI_CONTROLS,
         resultListLeftX + halfWidth,
         resultListTopY + halfHeight,
         117.0F - halfWidth,
         56.0F - halfHeight,
         halfWidth,
         halfHeight,
         256,
         256
      );
      GuiHelper.renderSlotsBackground(
         guiGraphics, resultListLeftX + 3, resultListTopY + 3, 3, matchedCraftingResults.size() / 3, matchedCraftingResults.size() % 3
      );
   }

   private void renderResultChoices(class_332 guiGraphics, List<class_1799> matchedCraftingResults, int resultListLeftX, int resultListTopY) {
      for (int i = 0; i < matchedCraftingResults.size(); i++) {
         class_1799 resultStack = matchedCraftingResults.get(i);
         int xOffset = i % 3 * 18;
         int yOffset = i / 3 * 18;
         int x = resultListLeftX + 3 + 1 + xOffset;
         int y = resultListTopY + 3 + 1 + yOffset;
         guiGraphics.method_51427(resultStack, x, y);
         guiGraphics.method_51432(this.font, resultStack, x, y, null);
      }
   }

   @Override
   protected void onTabClose() {
      super.onTabClose();
      this.craftingUIAddition.onCraftingSlotsHidden();
   }

   @Override
   protected void moveSlotsToTab() {
      int slotNumber = 0;

      for (class_1735 slot : this.getContainer().getSlots()) {
         slot.field_7873 = this.x + 3 + this.craftingUIAddition.getWidth() - this.screen.sophisticatedCore_getGuiLeft() + 1 + slotNumber % 3 * 18;
         slot.field_7872 = this.y + 44 - this.screen.sophisticatedCore_getGuiTop() + 1 + slotNumber / 3 * 18;
         if (++slotNumber >= 9) {
            break;
         }
      }

      class_1735 craftingSlot = this.getContainer().getSlots().get(9);
      craftingSlot.field_7873 = this.x + 3 + this.craftingUIAddition.getWidth() - this.screen.sophisticatedCore_getGuiLeft() + 19;
      craftingSlot.field_7872 = this.y + 44 - this.screen.sophisticatedCore_getGuiTop() + 72;
      this.craftingUIAddition.onCraftingSlotsDisplayed(this.getContainer().getSlots());
   }

   private boolean shouldShowResultSelection() {
      return this.getContainer().getMatchedCraftingResults().size() > 1;
   }

   @Override
   public boolean slotIsNotCoveredAt(class_1735 slot, double mouseX, double mouseY) {
      if (this.isOpen && this.shouldShowResultSelection() && this.resultSelectionShown) {
         Position pos = (Position)this.resultListPositionDimensions.method_15442();
         Dimension dim = (Dimension)this.resultListPositionDimensions.method_15441();
         return mouseX < pos.x() || mouseX > pos.x() + dim.width() || mouseY < pos.y() || mouseY > pos.y() + dim.height();
      } else {
         return true;
      }
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      guiGraphics.method_51448().method_22903();
      guiGraphics.method_51448().method_46416(0.0F, 0.0F, 410.0F);
      this.getResultChoiceHovered(mouseX, mouseY).ifPresent(i -> {
         List<class_1799> matchedCraftingResults = this.getContainer().getMatchedCraftingResults();
         if (i < matchedCraftingResults.size()) {
            class_1799 stack = matchedCraftingResults.get(i);
            guiGraphics.method_51446(this.minecraft.field_1772, stack, mouseX, mouseY);
         }
      });
      guiGraphics.method_51448().method_22909();
   }
}
