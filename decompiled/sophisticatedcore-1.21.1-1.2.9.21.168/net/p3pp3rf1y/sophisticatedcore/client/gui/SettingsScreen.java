package net.p3pp3rf1y.sophisticatedcore.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.class_1058;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4587;
import net.minecraft.class_465;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.InventoryScrollPanel;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageBackgroundProperties;
import net.p3pp3rf1y.sophisticatedcore.settings.StorageSettingsTabControlBase;

public abstract class SettingsScreen extends class_465<SettingsContainerMenu<?>> implements InventoryScrollPanel.IInventoryScreen {
   public static final int HEIGHT_WITHOUT_STORAGE_SLOTS = 114;
   public static final Predicate<class_1799> MATCH_ALL_FILTER = stack -> true;
   private StorageSettingsTabControlBase settingsTabControl;
   private InventoryScrollPanel inventoryScrollPanel = null;
   private TemplatePersistanceControl templatePersistanceControl = null;
   private StorageBackgroundProperties storageBackgroundProperties;
   private boolean mouseDragHandledByOther = false;

   protected SettingsScreen(SettingsContainerMenu<?> screenContainer, class_1661 inv, class_2561 titleIn) {
      super(screenContainer, inv, titleIn);
      this.updateDimensionsAndSlotPositions(class_310.method_1551().method_22683().method_4502());
      this.settingsTabControl = this.initializeTabControl();
   }

   public void method_25410(class_310 minecraft, int width, int height) {
      this.updateDimensionsAndSlotPositions(height);
      super.method_25410(minecraft, width, height);
   }

   private void updateDimensionsAndSlotPositions(int height) {
      int displayableNumberOfRows = Math.min((height - 114) / 18, ((SettingsContainerMenu)this.method_17577()).getNumberOfRows());
      int newImageHeight = 114 + this.getStorageInventoryHeight(displayableNumberOfRows);
      this.storageBackgroundProperties = ((SettingsContainerMenu)this.method_17577()).getNumberOfStorageInventorySlots()
               + ((SettingsContainerMenu)this.method_17577()).getColumnsTaken() * ((SettingsContainerMenu)this.method_17577()).getNumberOfRows()
            <= 81
         ? StorageBackgroundProperties.REGULAR_9_SLOT
         : StorageBackgroundProperties.REGULAR_12_SLOT;
      this.field_2792 = this.storageBackgroundProperties.getSlotsOnLine() * 18 + 14;
      this.updateStorageSlotsPositions();
      if (displayableNumberOfRows < ((SettingsContainerMenu)this.method_17577()).getNumberOfRows()) {
         this.storageBackgroundProperties = this.storageBackgroundProperties == StorageBackgroundProperties.REGULAR_9_SLOT
            ? StorageBackgroundProperties.WIDER_9_SLOT
            : StorageBackgroundProperties.WIDER_12_SLOT;
         this.field_2792 += 6;
      }

      this.field_2779 = newImageHeight;
      this.field_25270 = this.field_2779 - 94;
      this.field_25269 = 8 + this.storageBackgroundProperties.getPlayerInventoryXOffset();
   }

   protected int getStorageInventoryHeight(int displayableNumberOfRows) {
      return displayableNumberOfRows * 18;
   }

   private void updateInventoryScrollPanel() {
      if (this.inventoryScrollPanel != null) {
         this.method_37066(this.inventoryScrollPanel);
      }

      int numberOfVisibleRows = this.getNumberOfVisibleRows();
      if (numberOfVisibleRows < ((SettingsContainerMenu)this.method_17577()).getNumberOfRows()) {
         this.inventoryScrollPanel = new InventoryScrollPanel(
            class_310.method_1551(),
            this,
            0,
            ((SettingsContainerMenu)this.method_17577()).getNumberOfStorageInventorySlots(),
            this.getSlotsOnLine(),
            numberOfVisibleRows * 18,
            this.sophisticatedCore_getGuiTop() + 17,
            this.sophisticatedCore_getGuiLeft() + 7
         );
         this.method_37063(this.inventoryScrollPanel);
         this.inventoryScrollPanel.updateSlotsPosition();
      } else {
         this.inventoryScrollPanel = null;
      }
   }

   private int getNumberOfVisibleRows() {
      return Math.min((this.field_2779 - 114) / 18, ((SettingsContainerMenu)this.method_17577()).getNumberOfRows());
   }

   protected void updateStorageSlotsPositions() {
      int yPosition = 18;
      int slotIndex = 0;

      while (slotIndex < ((SettingsContainerMenu)this.method_17577()).getNumberOfStorageInventorySlots()) {
         class_1735 slot = ((SettingsContainerMenu)this.method_17577()).method_7611(slotIndex);
         int lineIndex = slotIndex % this.getSlotsOnLine();
         slot.field_7873 = 8 + lineIndex * 18;
         slot.field_7872 = yPosition;
         if (++slotIndex % this.getSlotsOnLine() == 0) {
            yPosition += 18;
         }
      }
   }

   public int getSlotsOnLine() {
      return this.storageBackgroundProperties.getSlotsOnLine() - ((SettingsContainerMenu)this.method_17577()).getColumnsTaken();
   }

   protected void method_25426() {
      super.method_25426();
      this.updateInventoryScrollPanel();
      this.settingsTabControl = this.initializeTabControl();
      this.templatePersistanceControl = this.initializeTemplatePersistanceControl();
      this.method_25429(this.settingsTabControl);
      this.method_25429(this.templatePersistanceControl);
   }

   private TemplatePersistanceControl initializeTemplatePersistanceControl() {
      return new TemplatePersistanceControl(
         new Position(this.field_2776 + this.field_25269 - 29, this.field_2800 + this.field_25270 + 29),
         ((SettingsContainerMenu)this.method_17577()).getTemplatePersistanceContainer()
      );
   }

   protected abstract StorageSettingsTabControlBase initializeTabControl();

   protected void method_2389(class_332 guiGraphics, float partialTicks, int mouseX, int mouseY) {
      int x = (this.field_22789 - this.field_2792) / 2;
      int y = (this.field_22790 - this.field_2779) / 2;
      StorageGuiHelper.renderStorageBackground(
         new Position(x, y),
         guiGraphics,
         this.storageBackgroundProperties.getTextureName(),
         this.field_2792,
         this.getStorageInventoryHeight(this.getNumberOfVisibleRows())
      );
      if (this.inventoryScrollPanel == null) {
         this.drawSlotBg(guiGraphics, x, y, ((SettingsContainerMenu)this.method_17577()).getStorageInventorySlots().size());
      }
   }

   protected void drawSlotBg(class_332 guiGraphics, int x, int y, int visibleSlotsCount) {
      int slotsOnLine = this.getSlotsOnLine();
      int slotRows = visibleSlotsCount / slotsOnLine;
      int remainingSlots = visibleSlotsCount % slotsOnLine;
      GuiHelper.renderSlotsBackground(guiGraphics, x + 7, y + 17, slotsOnLine, slotRows, remainingSlots);
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      ((SettingsContainerMenu)this.field_2797).detectSettingsChangeAndReload();
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(0.0F, 0.0F, -20.0F);
      this.method_25420(guiGraphics, mouseX, mouseY, partialTicks);
      poseStack.method_22909();
      this.settingsTabControl.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      this.templatePersistanceControl.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      this.settingsTabControl.renderTooltip(this, guiGraphics, mouseX, mouseY);
      this.templatePersistanceControl.renderTooltip(this, guiGraphics, mouseX, mouseY);
      this.method_2380(guiGraphics, mouseX, mouseY);
   }

   public void method_52752(class_332 guiGraphics) {
      class_4587 pose = guiGraphics.method_51448();
      pose.method_22903();
      pose.method_46416(0.0F, 0.0F, -12.0F);
      super.method_52752(guiGraphics);
      pose.method_22909();
   }

   protected void method_2388(class_332 guiGraphics, int mouseX, int mouseY) {
      super.method_2388(guiGraphics, mouseX, mouseY);
      if (this.inventoryScrollPanel == null) {
         this.renderInventorySlots(guiGraphics, mouseX, mouseY, true);
      }
   }

   @Override
   public void renderInventorySlots(class_332 guiGraphics, int mouseX, int mouseY, boolean canShowHover) {
      for (int slotId = 0; slotId < ((SettingsContainerMenu)this.field_2797).ghostSlots.size(); slotId++) {
         class_1735 slot = ((SettingsContainerMenu)this.field_2797).ghostSlots.get(slotId);
         this.method_2385(guiGraphics, slot);
         this.settingsTabControl.renderSlotOverlays(guiGraphics, slot, this::renderSlotOverlay, this.isTemplateLoadHovered());
         if (canShowHover && this.method_2387(slot, mouseX, mouseY) && slot.method_7682()) {
            this.field_2787 = slot;
            GuiHelper.renderSlotHighlight(guiGraphics, slot.field_7873, slot.field_7872, 0, this.sophisticatedCore_getSlotColor(slotId));
         }

         this.settingsTabControl.renderSlotExtra(guiGraphics, slot);
      }
   }

   public void method_2385(class_332 guiGraphics, class_1735 slot) {
      class_1799 itemstack = slot.method_7677() != class_1799.field_8037
         ? slot.method_7677()
         : this.settingsTabControl.getSlotStackDisplayOverride(slot.sophisticatedCore_getSlotIndex(), this.isTemplateLoadHovered());
      RenderSystem.enableDepthTest();
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(0.0F, 0.0F, 100.0F);
      if (!this.settingsTabControl.renderGuiItem(guiGraphics, this.field_22787.method_1480(), itemstack, slot, this.isTemplateLoadHovered())) {
         if (!((SettingsContainerMenu)this.method_17577()).getSlotFilterItem(slot.field_7874).method_7960()) {
            guiGraphics.method_51427(((SettingsContainerMenu)this.method_17577()).getSlotFilterItem(slot.field_7874), slot.field_7873, slot.field_7872);
         } else {
            Pair<class_2960, class_2960> pair = slot.method_7679();
            if (pair != null) {
               class_1058 textureatlassprite = (class_1058)this.field_22787.method_1549((class_2960)pair.getFirst()).apply((class_2960)pair.getSecond());
               guiGraphics.method_25298(slot.field_7873, slot.field_7872, 0, 16, 16, textureatlassprite);
            }
         }
      }

      poseStack.method_22909();
      this.settingsTabControl.drawSlotStackOverlay(guiGraphics, slot, this.isTemplateLoadHovered());
   }

   private boolean isTemplateLoadHovered() {
      return this.templatePersistanceControl.isTemplateLoadHovered();
   }

   protected void method_2383(class_1735 slot, int slotId, int mouseButton, class_1713 type) {
      if (slot != null) {
         this.settingsTabControl.handleSlotClick(slot, mouseButton);
      }
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double dragX, double dragY) {
      if (this.mouseDragHandledByOther) {
         return false;
      } else {
         class_1735 slot = this.method_2386(mouseX, mouseY);
         if (slot != null) {
            this.settingsTabControl.handleSlotClick(slot, button);
         }

         for (class_364 child : this.method_25396()) {
            if (child.method_25405(mouseX, mouseY) && child.method_25403(mouseX, mouseY, button, dragX, dragY)) {
               return true;
            }
         }

         return super.method_25403(mouseX, mouseY, button, dragX, dragY);
      }
   }

   @Nullable
   public class_1735 method_2386(double mouseX, double mouseY) {
      for (int i = 0; i < ((SettingsContainerMenu)this.field_2797).ghostSlots.size(); i++) {
         class_1735 slot = ((SettingsContainerMenu)this.field_2797).ghostSlots.get(i);
         if (this.method_2387(slot, mouseX, mouseY) && slot.method_7682()) {
            return slot;
         }
      }

      return null;
   }

   protected boolean method_2381(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
      return super.method_2381(mouseX, mouseY, guiLeftIn, guiTopIn, mouseButton) && this.hasClickedOutsideOfSettings(mouseX, mouseY);
   }

   private boolean hasClickedOutsideOfSettings(double mouseX, double mouseY) {
      return this.settingsTabControl.getTabRectangles().stream().noneMatch(r -> r.method_3318((int)mouseX, (int)mouseY));
   }

   private void renderSlotOverlay(class_332 guiGraphics, int xPos, int yPos, int height, int slotColor) {
      RenderSystem.disableDepthTest();
      RenderSystem.colorMask(true, true, true, false);
      guiGraphics.method_25296(xPos, yPos, xPos + 16, yPos + height, slotColor, slotColor);
      RenderSystem.colorMask(true, true, true, true);
      RenderSystem.enableDepthTest();
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         this.sendStorageInventoryScreenOpenMessage();
         return true;
      } else {
         return super.method_25404(keyCode, scanCode, modifiers);
      }
   }

   protected abstract void sendStorageInventoryScreenOpenMessage();

   public StorageSettingsTabControlBase getSettingsTabControl() {
      return this.settingsTabControl;
   }

   @Override
   public boolean isMouseOverSlot(class_1735 slot, double mouseX, double mouseY) {
      return this.method_2387(slot, mouseX, mouseY);
   }

   @Override
   public void drawSlotBg(class_332 guiGraphics, int visibleSlotsCount) {
      this.drawSlotBg(guiGraphics, (this.field_22789 - this.field_2792) / 2, (this.field_22790 - this.field_2779) / 2, visibleSlotsCount);
   }

   @Override
   public int getTopY() {
      return this.sophisticatedCore_getGuiTop();
   }

   @Override
   public int getLeftX() {
      return this.sophisticatedCore_getGuiLeft();
   }

   @Override
   public class_1735 getSlot(int slotIndex) {
      return ((SettingsContainerMenu)this.method_17577()).method_7611(slotIndex);
   }

   public void startMouseDragHandledByOther() {
      this.mouseDragHandledByOther = true;
   }

   public void stopMouseDragHandledByOther() {
      this.mouseDragHandledByOther = false;
   }

   @Override
   public Predicate<class_1799> getStackFilter() {
      return MATCH_ALL_FILTER;
   }
}
