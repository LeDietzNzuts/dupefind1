package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_289;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_6382;
import net.minecraft.class_6379.class_6380;

public class InventoryScrollPanel extends ScrollPanel {
   private static final int TOP_Y_OFFSET = 1;
   private final InventoryScrollPanel.IInventoryScreen screen;
   private final int firstSlotIndex;
   private final int numberOfSlots;
   private final int slotsInARow;
   private int visibleSlotsCount = 0;

   public InventoryScrollPanel(
      class_310 client, InventoryScrollPanel.IInventoryScreen screen, int firstSlotIndex, int numberOfSlots, int slotsInARow, int height, int top, int left
   ) {
      super(client, slotsInARow * 18 + 6, height, top, left, 0);
      this.screen = screen;
      this.firstSlotIndex = firstSlotIndex;
      this.numberOfSlots = numberOfSlots;
      this.slotsInARow = slotsInARow;
   }

   @Override
   protected int getScrollAmount() {
      return 18;
   }

   @Override
   protected int getContentHeight() {
      int rows = this.numberOfSlots / this.slotsInARow + (this.numberOfSlots % this.slotsInARow > 0 ? 1 : 0);
      return rows * 18;
   }

   @Override
   protected void drawBackground(class_332 guiGraphics, class_289 tess, float partialTick) {
      this.screen.drawSlotBg(guiGraphics, this.visibleSlotsCount);
   }

   @Override
   protected void drawPanel(class_332 guiGraphics, int entryRight, int relativeY, class_289 tess, int mouseX, int mouseY) {
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_22904(this.screen.getLeftX(), this.screen.getTopY(), 0.0);
      this.screen.renderInventorySlots(guiGraphics, mouseX, mouseY, this.method_25405(mouseX, mouseY));
      poseStack.method_22909();
   }

   public Optional<class_1735> findSlot(double mouseX, double mouseY) {
      if (!this.method_25405(mouseX, mouseY)) {
         return Optional.empty();
      } else {
         for (int slotIndex = this.firstSlotIndex; slotIndex < this.firstSlotIndex + this.numberOfSlots; slotIndex++) {
            class_1735 slot = this.screen.getSlot(slotIndex);
            if (this.screen.isMouseOverSlot(slot, mouseX, mouseY) && slot.method_7682()) {
               return Optional.of(slot);
            }
         }

         return Optional.empty();
      }
   }

   public class_6380 method_37018() {
      return class_6380.field_33784;
   }

   public void method_37020(class_6382 narrationElementOutput) {
   }

   @Override
   public boolean method_25401(double mouseX, double mouseY, double scrollX, double scrollY) {
      boolean ret = super.method_25401(mouseX, mouseY, scrollX, scrollY);
      this.updateSlotsPosition();
      return ret;
   }

   @Override
   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      boolean ret = super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
      this.updateSlotsPosition();
      return ret;
   }

   @Override
   public boolean method_25402(double mouseX, double mouseY, int button) {
      return this.method_25405(mouseX, mouseY) ? super.method_25402(mouseX, mouseY, button) : false;
   }

   public void resetScrollDistance() {
      this.scrollDistance = 0.0F;
   }

   public void updateSlotsPosition() {
      this.visibleSlotsCount = 0;
      int filteredSlotsCount = 0;

      for (int i = this.firstSlotIndex; i < this.firstSlotIndex + this.numberOfSlots; i++) {
         int rowOffset = (int)this.scrollDistance / 18;
         int row = filteredSlotsCount / this.slotsInARow - rowOffset;
         boolean matchesFilter = this.screen.getStackFilter().test(this.screen.getSlot(i).method_7677());
         if (matchesFilter) {
            filteredSlotsCount++;
         }

         int column = this.visibleSlotsCount % this.slotsInARow;
         int newY = this.top - this.screen.getTopY() + row * 18 + 1;
         int newX = this.left - this.screen.getLeftX() + column * 18 + 1;
         if (newY >= 1 && newY <= this.height && matchesFilter) {
            this.visibleSlotsCount++;
         } else {
            newY = -100;
         }

         this.screen.getSlot(i).field_7872 = newY;
         this.screen.getSlot(i).field_7873 = newX;
      }
   }

   public interface IInventoryScreen {
      void renderInventorySlots(class_332 var1, int var2, int var3, boolean var4);

      boolean isMouseOverSlot(class_1735 var1, double var2, double var4);

      void drawSlotBg(class_332 var1, int var2);

      int getTopY();

      int getLeftX();

      class_1735 getSlot(int var1);

      Predicate<class_1799> getStackFilter();
   }
}
