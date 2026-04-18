package net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay;

import java.util.List;
import net.minecraft.class_1767;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.renderdata.DisplaySide;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsContainerBase;

public class ItemDisplaySettingsContainer extends SettingsContainerBase<ItemDisplaySettingsCategory> {
   private static final String COLOR_TAG = "color";
   private static final String DISPLAY_SIDE_TAG = "displaySide";
   private static final String SELECT_SLOT_TAG = "selectSlot";
   private static final String UNSELECT_SLOT_TAG = "unselectSlot";
   private static final String ROTATE_CLOCKWISE_TAG = "rotateClockwise";
   private static final String ROTATE_COUNTER_CLOCKWISE_TAG = "rotateCounterClockwise";

   public ItemDisplaySettingsContainer(SettingsContainerMenu<?> settingsContainer, String categoryName, ItemDisplaySettingsCategory category) {
      super(settingsContainer, categoryName, category);
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("selectSlot")) {
         this.selectSlot(data.method_10550("selectSlot"));
      } else if (data.method_10545("unselectSlot")) {
         this.unselectSlot(data.method_10550("unselectSlot"));
      } else if (data.method_10545("rotateClockwise")) {
         this.rotateClockwise(data.method_10550("rotateClockwise"));
      } else if (data.method_10545("rotateCounterClockwise")) {
         this.rotateCounterClockwise(data.method_10550("rotateCounterClockwise"));
      } else if (data.method_10545("color")) {
         this.setColor(class_1767.method_7791(data.method_10550("color")));
      } else if (data.method_10545("displaySide")) {
         this.getCategory().setDisplaySide(DisplaySide.fromName(data.method_10558("displaySide")));
      }
   }

   public void unselectSlot(int slotIndex) {
      if (this.isSlotSelected(slotIndex)) {
         if (this.isServer()) {
            this.getCategory().unselectSlot(slotIndex);
         } else {
            this.getCategory().unselectSlot(slotIndex);
            this.sendIntToServer("unselectSlot", slotIndex);
         }
      }
   }

   public void selectSlot(int slotIndex) {
      if (!this.isSlotSelected(slotIndex)) {
         if (this.isServer()) {
            this.getCategory().selectSlot(slotIndex);
         } else {
            this.getCategory().selectSlot(slotIndex);
            this.sendIntToServer("selectSlot", slotIndex);
         }
      }
   }

   public void rotateClockwise(int slotIndex) {
      if (this.isServer()) {
         this.getCategory().rotate(slotIndex, true);
      } else {
         this.sendIntToServer("rotateClockwise", slotIndex);
      }
   }

   public void rotateCounterClockwise(int slotIndex) {
      if (this.isServer()) {
         this.getCategory().rotate(slotIndex, false);
      } else {
         this.sendIntToServer("rotateCounterClockwise", slotIndex);
      }
   }

   public void setColor(class_1767 color) {
      if (this.isServer()) {
         this.getCategory().setColor(color);
      } else {
         this.sendIntToServer("color", color.method_7789());
      }
   }

   public void setDisplaySide(DisplaySide displaySide) {
      if (this.isServer()) {
         this.getCategory().setDisplaySide(displaySide);
      } else {
         this.sendStringToServer("displaySide", displaySide.method_15434());
      }
   }

   public boolean isSlotSelected(int slotIndex) {
      return this.getCategory().getSlots().contains(slotIndex);
   }

   public class_1767 getColor() {
      return this.getCategory().getColor();
   }

   public int getRotation(int slotIndex) {
      return this.getCategory().getRotation(slotIndex);
   }

   public int getFirstSelectedSlot() {
      List<Integer> slots = this.getCategory().getSlots();
      return slots.isEmpty() ? -1 : slots.get(0);
   }

   public DisplaySide getDisplaySide() {
      return this.getCategory().getDisplaySide();
   }
}
