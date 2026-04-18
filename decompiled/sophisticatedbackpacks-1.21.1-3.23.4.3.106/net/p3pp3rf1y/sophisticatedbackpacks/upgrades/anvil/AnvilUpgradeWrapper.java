package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.anvil;

import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.minecraft.class_9288;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModDataComponents;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.ComponentItemHandler;

public class AnvilUpgradeWrapper extends UpgradeWrapperBase<AnvilUpgradeWrapper, AnvilUpgradeItem> {
   private final ComponentItemHandler inventory;

   protected AnvilUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.inventory = new ComponentItemHandler(upgrade, class_9334.field_49622, 2) {
         protected void onContentsChanged(int slot, class_1799 oldStack, class_1799 newStack) {
            super.onContentsChanged(slot, oldStack, newStack);
            AnvilUpgradeWrapper.this.save();
         }

         public boolean isItemValid(int slot, class_1799 stack) {
            return true;
         }
      };
   }

   public ComponentItemHandler getInventory() {
      return this.inventory;
   }

   public boolean canBeDisabled() {
      return false;
   }

   public boolean shouldShiftClickIntoStorage() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.SHIFT_CLICK_INTO_STORAGE, true);
   }

   public void setShiftClickIntoStorage(boolean shiftClickIntoStorage) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.SHIFT_CLICK_INTO_STORAGE, shiftClickIntoStorage);
      this.save();
   }

   public String getItemName() {
      return (String)this.upgrade.sophisticatedCore_getOrDefault(ModDataComponents.ITEM_NAME, "");
   }

   public void setItemName(String itemName) {
      this.upgrade.sophisticatedCore_set(ModDataComponents.ITEM_NAME, itemName);
      this.save();
   }
}
