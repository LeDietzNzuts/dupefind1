package net.p3pp3rf1y.sophisticatedcore.util;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;

public class EmptyItemHandler implements IItemHandlerSimpleInserter {
   public static final EmptyItemHandler INSTANCE = new EmptyItemHandler();

   public class_1799 getStackInSlot(int slot) {
      return class_1799.field_8037;
   }

   public void setStackInSlot(int slot, class_1799 stack) {
   }

   public int getSlotLimit(int slot) {
      return 0;
   }

   public int getSlotCount() {
      return 0;
   }

   public SingleSlotStorage<ItemVariant> getSlot(int slot) {
      return new SingleStackStorage() {
         protected boolean canInsert(ItemVariant itemVariant) {
            return false;
         }

         protected boolean canExtract(ItemVariant itemVariant) {
            return false;
         }

         protected class_1799 getStack() {
            return class_1799.field_8037;
         }

         protected void setStack(class_1799 stack) {
         }
      };
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return 0L;
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return 0L;
   }

   @Override
   public boolean isItemValid(int slot, ItemVariant resource, int count) {
      return false;
   }
}
