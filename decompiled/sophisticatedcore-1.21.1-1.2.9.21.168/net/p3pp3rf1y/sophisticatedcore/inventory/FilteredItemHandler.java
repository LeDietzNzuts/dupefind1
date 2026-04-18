package net.p3pp3rf1y.sophisticatedcore.inventory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.FilteringStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;

public class FilteredItemHandler<T extends Storage<ItemVariant>> extends FilteringStorage<ItemVariant> {
   protected final List<FilterLogic> inputFilters;
   private final List<FilterLogic> outputFilters;

   public FilteredItemHandler(T inventoryHandler, List<FilterLogic> inputFilters, List<FilterLogic> outputFilters) {
      super(inventoryHandler);
      this.inputFilters = inputFilters;
      this.outputFilters = outputFilters;
   }

   protected boolean canInsert(ItemVariant resource) {
      if (this.inputFilters.isEmpty()) {
         return true;
      } else {
         class_1799 stack = resource.toStack();

         for (FilterLogic filter : this.inputFilters) {
            if (filter.matchesFilter(stack)) {
               return true;
            }
         }

         return false;
      }
   }

   protected boolean canExtract(ItemVariant resource) {
      if (this.outputFilters.isEmpty()) {
         return true;
      } else {
         class_1799 stack = resource.toStack();

         for (FilterLogic filter : this.outputFilters) {
            if (filter.matchesFilter(stack)) {
               return true;
            }
         }

         return false;
      }
   }

   public class FilteredSingleSlotStorage implements SingleSlotStorage<ItemVariant> {
      private final SingleSlotStorage<ItemVariant> backingSlot;

      public FilteredSingleSlotStorage(SingleSlotStorage<ItemVariant> backingSlot) {
         this.backingSlot = backingSlot;
      }

      public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         return FilteredItemHandler.this.canInsert(resource) ? this.backingSlot.insert(resource, maxAmount, transaction) : 0L;
      }

      public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         return FilteredItemHandler.this.canExtract(resource) ? this.backingSlot.extract(resource, maxAmount, transaction) : 0L;
      }

      public boolean isResourceBlank() {
         return this.backingSlot.isResourceBlank();
      }

      public ItemVariant getResource() {
         return (ItemVariant)this.backingSlot.getResource();
      }

      public long getAmount() {
         return this.backingSlot.getAmount();
      }

      public long getCapacity() {
         return this.backingSlot.getSlotCount();
      }
   }

   public static class Modifiable extends FilteredItemHandler<ITrackedContentsItemHandler> implements ITrackedContentsItemHandler {
      public Modifiable(ITrackedContentsItemHandler inventoryHandler, List<FilterLogic> inputFilters, List<FilterLogic> outputFilters) {
         super(inventoryHandler, inputFilters, outputFilters);
      }

      public class_1799 getStackInSlot(int slot) {
         return ((ITrackedContentsItemHandler)this.backingStorage.get()).getStackInSlot(slot);
      }

      public void setStackInSlot(int slot, @Nonnull class_1799 stack) {
         ((ITrackedContentsItemHandler)this.backingStorage.get()).setStackInSlot(slot, stack);
      }

      @Override
      public Set<ItemStackKey> getTrackedStacks() {
         Set<ItemStackKey> ret = new HashSet<>();
         ((ITrackedContentsItemHandler)this.backingStorage.get()).getTrackedStacks().forEach(ts -> {
            if (this.inputFiltersMatchStack(ts.stack())) {
               ret.add(ts);
            }
         });
         return ret;
      }

      private boolean inputFiltersMatchStack(class_1799 stack) {
         for (FilterLogic filter : this.inputFilters) {
            if (filter.matchesFilter(stack)) {
               return true;
            }
         }

         return false;
      }

      @Override
      public void registerTrackingListeners(
         Consumer<ItemStackKey> onAddStackKey, Consumer<ItemStackKey> onRemoveStackKey, Runnable onAddFirstEmptySlot, Runnable onRemoveLastEmptySlot
      ) {
         ((ITrackedContentsItemHandler)this.backingStorage.get()).registerTrackingListeners(isk -> {
            if (this.inputFiltersMatchStack(isk.stack())) {
               onAddStackKey.accept(isk);
            }
         }, isk -> {
            if (this.inputFiltersMatchStack(isk.stack())) {
               onRemoveStackKey.accept(isk);
            }
         }, onAddFirstEmptySlot, onRemoveLastEmptySlot);
      }

      @Override
      public void unregisterStackKeyListeners() {
         ((ITrackedContentsItemHandler)this.backingStorage.get()).unregisterStackKeyListeners();
      }

      @Override
      public boolean hasEmptySlots() {
         return ((ITrackedContentsItemHandler)this.backingStorage.get()).hasEmptySlots();
      }

      @Override
      public int getInternalSlotLimit(int slot) {
         return ((ITrackedContentsItemHandler)this.backingStorage.get()).getInternalSlotLimit(slot);
      }

      public int getSlotLimit(int slot) {
         return ((ITrackedContentsItemHandler)this.backingStorage.get()).getSlotLimit(slot);
      }

      public int getSlotCount() {
         return ((ITrackedContentsItemHandler)this.backingStorage.get()).getSlotCount();
      }

      public SingleSlotStorage<ItemVariant> getSlot(int slot) {
         return new FilteredItemHandler.FilteredSingleSlotStorage(((ITrackedContentsItemHandler)this.backingStorage.get()).getSlot(slot));
      }
   }
}
