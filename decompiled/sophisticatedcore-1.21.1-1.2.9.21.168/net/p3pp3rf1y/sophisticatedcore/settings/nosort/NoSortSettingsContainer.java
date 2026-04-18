package net.p3pp3rf1y.sophisticatedcore.settings.nosort;

import net.minecraft.class_1767;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsContainerBase;

public class NoSortSettingsContainer extends SettingsContainerBase<NoSortSettingsCategory> {
   private static final String ACTION_TAG = "action";
   private static final String SELECT_ALL_ACTION = "selectAll";
   private static final String UNSELECT_ALL_ACTION = "unselectAll";
   private static final String UNSELECT_SLOT_TAG = "unselectSlot";
   private static final String SELECT_SLOT_TAG = "selectSlot";
   private static final String COLOR_TAG = "color";

   public NoSortSettingsContainer(SettingsContainerMenu<?> settingsContainer, String categoryName, NoSortSettingsCategory category) {
      super(settingsContainer, categoryName, category);
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("action")) {
         String var2 = data.method_10558("action");
         switch (var2) {
            case "selectAll":
               this.selectAllSlots();
               break;
            case "unselectAll":
               this.unselectAllSlots();
         }
      } else if (data.method_10545("selectSlot")) {
         this.selectSlot(data.method_10550("selectSlot"));
      } else if (data.method_10545("unselectSlot")) {
         this.unselectSlot(data.method_10550("unselectSlot"));
      } else if (data.method_10545("color")) {
         this.setColor(class_1767.method_7791(data.method_10550("color")));
      }
   }

   public void unselectSlot(int slotNumber) {
      if (this.isSlotSelected(slotNumber)) {
         if (this.isServer()) {
            this.getCategory().unselectSlot(slotNumber);
         } else {
            this.sendIntToServer("unselectSlot", slotNumber);
         }
      }
   }

   public void selectSlot(int slotNumber) {
      if (!this.isSlotSelected(slotNumber)) {
         if (this.isServer()) {
            this.getCategory().selectSlot(slotNumber);
         } else {
            this.sendIntToServer("selectSlot", slotNumber);
         }
      }
   }

   public void unselectAllSlots() {
      if (this.isServer()) {
         this.getCategory().unselectAllSlots();
      } else {
         this.sendStringToServer("action", "unselectAll");
      }
   }

   public void selectAllSlots() {
      if (this.isServer()) {
         this.getCategory().selectSlots(0, this.getSettingsContainer().getNumberOfSlots());
      } else {
         this.sendStringToServer("action", "selectAll");
      }
   }

   public void setColor(class_1767 color) {
      if (this.isServer()) {
         this.getCategory().setColor(color);
      } else {
         this.sendIntToServer("color", color.method_7789());
      }
   }

   public boolean isSlotSelected(int slotNumber) {
      return this.getCategory().isSlotSelected(slotNumber);
   }

   public class_1767 getColor() {
      return this.getCategory().getColor();
   }
}
