package net.p3pp3rf1y.sophisticatedcore.upgrades.crafting;

import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.minecraft.class_9288;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.ComponentItemHandler;

public class CraftingUpgradeWrapper extends UpgradeWrapperBase<CraftingUpgradeWrapper, CraftingUpgradeItem> {
   private final ComponentItemHandler inventory;

   public CraftingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.inventory = new ComponentItemHandler(upgrade, class_9334.field_49622, 9) {
         @Override
         protected void onContentsChanged(int slot, class_1799 oldStack, class_1799 newStack) {
            super.onContentsChanged(slot, oldStack, newStack);
            CraftingUpgradeWrapper.this.save();
         }

         @Override
         public boolean isItemValid(int slot, class_1799 stack) {
            return true;
         }
      };
   }

   public ComponentItemHandler getInventory() {
      return this.inventory;
   }

   @Override
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
}
