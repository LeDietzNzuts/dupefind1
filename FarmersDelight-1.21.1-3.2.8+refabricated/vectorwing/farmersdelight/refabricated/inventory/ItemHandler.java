package vectorwing.farmersdelight.refabricated.inventory;

import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;

public interface ItemHandler extends SlottedStorage<ItemVariant> {
   int getSlotCount();

   int getSlotLimit(int var1);

   class_1799 getStackInSlot(int var1);

   class_1799 insertItem(int var1, class_1799 var2, boolean var3);

   class_1799 extractItem(int var1, int var2, boolean var3);

   void setStackInSlot(int var1, class_1799 var2);

   boolean isItemValid(int var1, class_1799 var2);

   default IntList getInputSlotIndexes() {
      return IntList.of();
   }

   @Deprecated(forRemoval = true, since = "3.1.0")
   default void commitModifiedStacks() {
   }

   @Deprecated(forRemoval = true, since = "3.1.0")
   default class_1799 removeItem(int slot, int amount) {
      return this.extractItem(slot, amount, false);
   }

   @Deprecated(forRemoval = true, since = "3.1.0")
   default long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return this.getSlot(slot).insert(resource, maxAmount, transaction);
   }

   @Deprecated(forRemoval = true, since = "3.1.0")
   default long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return this.getSlot(slot).extract(resource, maxAmount, transaction);
   }
}
