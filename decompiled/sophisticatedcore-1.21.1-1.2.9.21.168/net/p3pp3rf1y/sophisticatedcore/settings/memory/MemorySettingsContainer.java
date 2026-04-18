package net.p3pp3rf1y.sophisticatedcore.settings.memory;

import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsContainerBase;

public class MemorySettingsContainer extends SettingsContainerBase<MemorySettingsCategory> {
   private static final String ACTION_TAG = "action";
   private static final String SELECT_ALL_ACTION = "selectAll";
   private static final String UNSELECT_ALL_ACTION = "unselectAll";
   private static final String UNSELECT_SLOT_TAG = "unselectSlot";
   private static final String SELECT_SLOT_TAG = "selectSlot";
   private static final String IGNORE_NBT_TAG = "ignoreNbt";

   public MemorySettingsContainer(SettingsContainerMenu<?> settingsContainer, String categoryName, MemorySettingsCategory category) {
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
      } else if (data.method_10545("ignoreNbt")) {
         this.setIgnoreNbt(data.method_10577("ignoreNbt"));
      }
   }

   public void unselectSlot(int slotNumber) {
      if (this.isSlotSelected(slotNumber)) {
         if (this.isServer()) {
            this.getCategory().unselectSlot(slotNumber);
            this.getSettingsContainer().onMemorizedStackRemoved(slotNumber);
         } else {
            this.sendIntToServer("unselectSlot", slotNumber);
         }
      }
   }

   public void selectSlot(int slotNumber) {
      if (!this.isSlotSelected(slotNumber)) {
         if (this.isServer()) {
            this.getCategory().selectSlot(slotNumber);
            this.getSettingsContainer().onMemorizedStackAdded(slotNumber);
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

   public boolean isSlotSelected(int slotNumber) {
      return this.getCategory().isSlotSelected(slotNumber);
   }

   public boolean ignoresNbt() {
      return this.getCategory().ignoresNbt();
   }

   public void setIgnoreNbt(boolean ignoreNbt) {
      if (this.isServer()) {
         this.getCategory().setIgnoreNbt(ignoreNbt);
         this.getSettingsContainer().onMemorizedItemsChanged();
      } else {
         this.sendBooleanToServer("ignoreNbt", ignoreNbt);
      }
   }

   public class_1799 getMemorizedStack(int slotNumber) {
      return this.getCategory().getSlotFilterStack(slotNumber, false).orElse(class_1799.field_8037);
   }

   public class_1799 getSelectedTemplatesMemorizedStack(int slotNumber) {
      return this.getSettingsContainer()
         .getSelectedTemplatesCategory(MemorySettingsCategory.class)
         .flatMap(cat -> cat.getSlotFilterStack(slotNumber, false))
         .orElse(class_1799.field_8037);
   }
}
