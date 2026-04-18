package net.p3pp3rf1y.sophisticatedbackpacks.util;

import java.util.List;
import java.util.Optional;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedSlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;

public class CombinedInvWrapper<S extends IItemHandlerSimpleInserter> extends CombinedSlottedStorage<ItemVariant, S> implements IItemHandlerSimpleInserter {
   protected final int[] baseIndex;
   protected final int slotCount;

   public CombinedInvWrapper(List<S> inventories) {
      super(inventories);
      this.baseIndex = new int[inventories.size()];
      int index = 0;

      for (int i = 0; i < inventories.size(); i++) {
         index += inventories.get(i).getSlotCount();
         this.baseIndex[i] = index;
      }

      this.slotCount = index;
   }

   @SafeVarargs
   public CombinedInvWrapper(S... inventories) {
      this(List.of(inventories));
   }

   protected int getIndexForSlot(int slot) {
      if (slot < 0) {
         return -1;
      } else {
         for (int i = 0; i < this.baseIndex.length; i++) {
            if (slot - this.baseIndex[i] < 0) {
               return i;
            }
         }

         return -1;
      }
   }

   protected Optional<S> getHandlerFromIndex(int index) {
      return index >= 0 && index < this.parts.size() ? Optional.of((S)this.parts.get(index)) : Optional.empty();
   }

   protected int getSlotFromIndex(int slot, int index) {
      return index > 0 && index < this.baseIndex.length ? slot - this.baseIndex[index - 1] : slot;
   }

   public int getSlotCount() {
      return this.slotCount;
   }

   public void setStackInSlot(int slot, class_1799 stack) {
      int index = this.getIndexForSlot(slot);
      Optional<S> handler = this.getHandlerFromIndex(index);
      if (!handler.isEmpty()) {
         slot = this.getSlotFromIndex(slot, index);
         handler.get().setStackInSlot(slot, stack);
      }
   }

   public class_1799 getStackInSlot(int slot) {
      int index = this.getIndexForSlot(slot);
      Optional<S> handler = this.getHandlerFromIndex(index);
      if (handler.isEmpty()) {
         return class_1799.field_8037;
      } else {
         slot = this.getSlotFromIndex(slot, index);
         return handler.get().getStackInSlot(slot);
      }
   }

   public int getSlotLimit(int slot) {
      int index = this.getIndexForSlot(slot);
      Optional<S> handler = this.getHandlerFromIndex(index);
      if (handler.isEmpty()) {
         return 0;
      } else {
         int localSlot = this.getSlotFromIndex(slot, index);
         return handler.get().getSlotLimit(localSlot);
      }
   }

   public boolean isItemValid(int slot, ItemVariant resource, int count) {
      int index = this.getIndexForSlot(slot);
      Optional<S> handler = this.getHandlerFromIndex(index);
      if (handler.isEmpty()) {
         return false;
      } else {
         int localSlot = this.getSlotFromIndex(slot, index);
         return handler.get().isItemValid(localSlot, resource, count);
      }
   }

   public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      int index = this.getIndexForSlot(slot);
      Optional<S> handler = this.getHandlerFromIndex(index);
      if (handler.isEmpty()) {
         return 0L;
      } else {
         slot = this.getSlotFromIndex(slot, index);
         return handler.get().insertSlot(slot, resource, maxAmount, ctx);
      }
   }

   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      int index = this.getIndexForSlot(slot);
      Optional<S> handler = this.getHandlerFromIndex(index);
      if (handler.isEmpty()) {
         return 0L;
      } else {
         slot = this.getSlotFromIndex(slot, index);
         return handler.get().extractSlot(slot, resource, maxAmount, ctx);
      }
   }
}
