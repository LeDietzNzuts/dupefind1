package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.anvil;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_5481;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.TextBox;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;

public class AnvilUpgradeTab extends UpgradeSettingsTab<AnvilUpgradeContainer> {
   public static final TextureBlitData EDIT_ITEM_NAME_BACKGROUND_DISABLED = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(28, 115), new Dimension(100, 16)
   );
   public static final TextureBlitData EDIT_ITEM_NAME_BACKGROUND = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(28, 99), new Dimension(100, 16)
   );
   public static final TextureBlitData PLUS_SIGN = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(113, 203), new Dimension(13, 13));
   public static final TextureBlitData ARROW = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(56, 221), new Dimension(14, 15));
   public static final TextureBlitData RED_CROSS = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(113, 216), new Dimension(15, 15));
   private static final class_2561 TOO_EXPENSIVE_TEXT = class_2561.method_43471("container.repair.expensive");
   private final TextBox itemNameTextBox;

   public AnvilUpgradeTab(AnvilUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen) {
      super(
         upgradeContainer,
         position,
         screen,
         SBPTranslationHelper.INSTANCE.translUpgrade("anvil", new Object[0]),
         SBPTranslationHelper.INSTANCE.translUpgradeTooltip("anvil")
      );
      this.openTabDimension = new Dimension(103, 92);
      this.itemNameTextBox = new TextBox(new Position(this.x + 5, this.y + 25), new Dimension(90, 14)) {
         public boolean method_25402(double mouseX, double mouseY, int button) {
            if (this.isEditable()) {
               this.method_25365(true);
               screen.method_25395(AnvilUpgradeTab.this.itemNameTextBox);
            }

            return super.method_25402(mouseX, mouseY, button);
         }

         protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
            super.renderBg(guiGraphics, minecraft, mouseX, mouseY);
            TextureBlitData textureBlitData = ((class_1735)((AnvilUpgradeContainer)AnvilUpgradeTab.this.getContainer()).getSlots().get(0)).method_7681()
               ? AnvilUpgradeTab.EDIT_ITEM_NAME_BACKGROUND
               : AnvilUpgradeTab.EDIT_ITEM_NAME_BACKGROUND_DISABLED;
            GuiHelper.blit(guiGraphics, this.getX() - 2, this.getY() - 2, textureBlitData, this.getWidth() + 4, this.getHeight() + 2);
         }
      };
      this.itemNameTextBox.setTextColor(-1);
      this.itemNameTextBox.setTextColorUneditable(-1);
      this.itemNameTextBox.setBordered(false);
      this.itemNameTextBox.setMaxLength(50);
      this.itemNameTextBox.setResponder(this::onNameChanged);
      this.itemNameTextBox.setValue(((AnvilUpgradeContainer)this.getContainer()).getItemName());
      this.addHideableChild(this.itemNameTextBox);
      this.itemNameTextBox.setEditable(!((class_1735)upgradeContainer.getSlots().get(0)).method_7677().method_7960());
      ((AnvilUpgradeContainer)this.getContainer()).setNameChangeListener(() -> {
         this.itemNameTextBox.setValue(((AnvilUpgradeContainer)this.getContainer()).getItemName());
         this.itemNameTextBox.setEditable(!((class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(0)).method_7677().method_7960());
      });
   }

   private void onNameChanged(String name) {
      if (!((AnvilUpgradeContainer)this.getContainer()).isProcessingOnTakeLogic()) {
         ((AnvilUpgradeContainer)this.getContainer()).setItemName(name);
      }
   }

   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      super.renderBg(guiGraphics, minecraft, mouseX, mouseY);
      if (((AnvilUpgradeContainer)this.getContainer()).isOpen()) {
         this.renderSlotBg(guiGraphics, (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(0));
         this.renderSlotBg(guiGraphics, (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(1));
         this.renderSlotBg(guiGraphics, (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(2));
      }
   }

   private void renderSlotBg(class_332 guiGraphics, class_1735 slot) {
      GuiHelper.renderSlotsBackground(
         guiGraphics,
         slot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() - 1,
         slot.field_7872 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() - 1,
         1,
         1
      );
   }

   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
      if (this.isOpen) {
         this.renderCost(guiGraphics, this.x + 3, this.y + 62);
         class_1735 firstSlot = (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(0);
         int inputSlotsY = firstSlot.field_7872 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop();
         int firstInputSlotX = firstSlot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft();
         int secondInputSlotX = ((class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(1)).field_7873
            + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft();
         class_1735 resultSlot = (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(2);
         int resultSlotX = resultSlot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft();
         GuiHelper.blit(
            guiGraphics, firstInputSlotX + 18 + (secondInputSlotX - (firstInputSlotX + 18)) / 2 - PLUS_SIGN.getWidth() / 2 - 1, inputSlotsY + 2, PLUS_SIGN
         );
         int arrowX = secondInputSlotX + 18 + (resultSlotX - (secondInputSlotX + 18)) / 2 - ARROW.getWidth() / 2 - 1;
         int arrowY = inputSlotsY + 1;
         GuiHelper.blit(guiGraphics, arrowX, arrowY, ARROW);
         if (firstSlot.method_7681() && !resultSlot.method_7681()) {
            GuiHelper.blit(guiGraphics, arrowX, arrowY, RED_CROSS);
         }
      }
   }

   protected void moveSlotsToTab() {
      class_1735 firstInputSlot = (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(0);
      firstInputSlot.field_7873 = this.x - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() + 4;
      firstInputSlot.field_7872 = this.y + 42 - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 1;
      class_1735 secondInputSlot = (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(1);
      secondInputSlot.field_7873 = this.x - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() + this.getWidth() / 2 - 9;
      secondInputSlot.field_7872 = this.y + 42 - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 1;
      class_1735 resultSlot = (class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(2);
      resultSlot.field_7873 = this.x - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() + this.getWidth() - 2 - 3 - 18;
      resultSlot.field_7872 = this.y + 42 - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 1;
   }

   protected void renderCost(class_332 guiGraphics, int x, int y) {
      RenderSystem.disableBlend();
      int i = ((AnvilUpgradeContainer)this.getContainer()).getCost();
      if (i > 0) {
         int color = 8453920;
         class_2561 component;
         if (i >= 40 && !this.minecraft.field_1724.method_31549().field_7477) {
            component = TOO_EXPENSIVE_TEXT;
            color = 16736352;
         } else if (!((class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(2)).method_7681()) {
            component = null;
         } else {
            component = class_2561.method_43469("container.repair.cost", new Object[]{i});
            if (!((class_1735)((AnvilUpgradeContainer)this.getContainer()).getSlots().get(2)).method_7674(this.minecraft.field_1724)) {
               color = 16736352;
            }
         }

         if (component != null) {
            int maxWidth = this.getWidth() - 9;
            List<class_5481> lines = this.font.method_1728(component, maxWidth);
            guiGraphics.method_25294(x, y, x + maxWidth, y + lines.size() * 12, 1325400064);
            int yOffset = 0;

            for (class_5481 line : lines) {
               int width = this.font.method_30880(line);
               guiGraphics.method_51430(this.font, line, x + 2 + (maxWidth - width) / 2, y + 2 + yOffset, color, true);
               yOffset += 12;
            }
         }
      }
   }
}
