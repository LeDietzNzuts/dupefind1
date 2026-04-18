package net.p3pp3rf1y.sophisticatedcore.inventory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;

public class CachedFailedInsertInventoryHandler implements IItemHandlerSimpleInserter {
   private final Supplier<IItemHandlerSimpleInserter> wrappedHandlerGetter;
   private final LongSupplier timeSupplier;
   private long currentCacheTime = 0L;
   private final Set<class_1799> failedInsertStacks = new HashSet<>();

   public CachedFailedInsertInventoryHandler(Supplier<IItemHandlerSimpleInserter> wrappedHandlerGetter, LongSupplier timeSupplier) {
      this.wrappedHandlerGetter = wrappedHandlerGetter;
      this.timeSupplier = timeSupplier;
   }

   public void setStackInSlot(int slot, @NotNull class_1799 stack) {
      this.wrappedHandlerGetter.get().setStackInSlot(slot, stack);
   }

   public int getSlotCount() {
      return this.wrappedHandlerGetter.get().getSlotCount();
   }

   public SingleSlotStorage<ItemVariant> getSlot(int slot) {
      return this.wrappedHandlerGetter.get().getSlot(slot);
   }

   @NotNull
   public class_1799 getStackInSlot(int slot) {
      return this.wrappedHandlerGetter.get().getStackInSlot(slot);
   }

   @NotNull
   @Override
   public class_1799 insertItem(int slot, @NotNull class_1799 stack, boolean simulate) {
      if (this.currentCacheTime != this.timeSupplier.getAsLong()) {
         this.failedInsertStacks.clear();
         this.currentCacheTime = this.timeSupplier.getAsLong();
      }

      if (this.failedInsertStacks.contains(stack)) {
         return stack;
      } else {
         class_1799 result = this.wrappedHandlerGetter.get().insertItem(slot, stack, simulate);
         if (result == stack) {
            this.failedInsertStacks.add(stack);
         }

         return result;
      }
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      if (this.currentCacheTime != this.timeSupplier.getAsLong()) {
         this.failedInsertStacks.clear();
         this.currentCacheTime = this.timeSupplier.getAsLong();
      }

      if (this.failedInsertStacks.contains(resource.toStack())) {
         return 0L;
      } else {
         long inserted = this.wrappedHandlerGetter.get().insert(resource, maxAmount, ctx);
         if (inserted == 0L) {
            this.failedInsertStacks.add(resource.toStack());
         }

         return inserted;
      }
   }

   public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      if (this.currentCacheTime != this.timeSupplier.getAsLong()) {
         this.failedInsertStacks.clear();
         this.currentCacheTime = this.timeSupplier.getAsLong();
      }

      if (this.failedInsertStacks.contains(resource.toStack())) {
         return 0L;
      } else {
         long inserted = this.wrappedHandlerGetter.get().insertSlot(slot, resource, maxAmount, ctx);
         if (inserted == 0L) {
            this.failedInsertStacks.add(resource.toStack());
         }

         return inserted;
      }
   }

   @NotNull
   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      return this.wrappedHandlerGetter.get().extractItem(slot, amount, simulate);
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.wrappedHandlerGetter.get().extract(resource, maxAmount, ctx);
   }

   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.wrappedHandlerGetter.get().extractSlot(slot, resource, maxAmount, ctx);
   }

   public int getSlotLimit(int slot) {
      return this.wrappedHandlerGetter.get().getSlotLimit(slot);
   }

   @Override
   public boolean isItemValid(int slot, @NotNull class_1799 stack) {
      return this.wrappedHandlerGetter.get().isItemValid(slot, stack);
   }

   @Override
   public boolean isItemValid(int slot, @NotNull ItemVariant resource, int count) {
      return this.wrappedHandlerGetter.get().isItemValid(slot, resource, count);
   }

   public Iterator<StorageView<ItemVariant>> iterator() {
      return this.wrappedHandlerGetter.get().iterator();
   }
}
