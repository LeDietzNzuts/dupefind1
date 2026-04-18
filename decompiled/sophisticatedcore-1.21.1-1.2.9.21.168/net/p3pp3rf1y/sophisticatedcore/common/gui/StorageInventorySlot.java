package net.p3pp3rf1y.sophisticatedcore.common.gui;

import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.api.ISlotChangeResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;

public class StorageInventorySlot extends SlotSuppliedHandler {
   private final boolean isClientSide;
   private final IStorageWrapper storageWrapper;
   private final int slotIndex;
   private final class_1657 player;

   public StorageInventorySlot(boolean isClientSide, IStorageWrapper storageWrapper, int slotIndex, class_1657 player) {
      super(storageWrapper::getInventoryHandler, slotIndex, 0, 0);
      this.isClientSide = isClientSide;
      this.storageWrapper = storageWrapper;
      this.slotIndex = slotIndex;
      this.player = player;
   }

   @Override
   public boolean method_7680(class_1799 stack) {
      return this.storageWrapper.getInventoryHandler().isItemValid(this.slotIndex, stack, this.player);
   }

   public void method_7668() {
      super.method_7668();
      this.storageWrapper.getInventoryHandler().onContentsChanged(this.slotIndex);
      this.processSlotChangeResponse(this.slotIndex, this.storageWrapper.getInventoryHandler(), this.storageWrapper);
   }

   private void processSlotChangeResponse(int slot, IItemHandlerSimpleInserter handler, IStorageWrapper storageWrapper) {
      if (!this.isClientSide) {
         storageWrapper.getUpgradeHandler()
            .getWrappersThatImplementFromMainStorage(ISlotChangeResponseUpgrade.class)
            .forEach(u -> u.onSlotChange(handler, slot));
      }
   }

   @Override
   public int method_7676(class_1799 stack) {
      return this.storageWrapper.getInventoryHandler().getStackLimit(this.slotIndex, stack);
   }

   public class_1799 method_32755(class_1799 stack, int maxCount) {
      if (!stack.method_7960() && this.method_7680(stack)) {
         class_1799 itemstack = this.method_7677();
         int i = Math.min(Math.min(maxCount, stack.method_7947()), this.method_7676(stack) - itemstack.method_7947());
         if (itemstack.method_7960()) {
            this.method_7673(stack.method_7971(i));
         } else if (class_1799.method_31577(itemstack, stack)) {
            stack.method_7934(i);
            class_1799 copy = itemstack.method_7972();
            copy.method_7933(i);
            this.method_7673(copy);
         }

         return stack;
      } else {
         return stack;
      }
   }

   public boolean isInfinite() {
      return this.storageWrapper.getInventoryHandler().isInfinite(this.slotIndex);
   }
}
