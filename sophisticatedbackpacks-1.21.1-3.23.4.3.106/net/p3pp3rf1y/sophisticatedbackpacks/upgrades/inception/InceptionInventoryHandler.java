package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedbackpacks.util.CombinedInvWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;

public class InceptionInventoryHandler implements ITrackedContentsItemHandler {
   private CombinedInvWrapper<ITrackedContentsItemHandler> combinedInventories;
   private final ITrackedContentsItemHandler wrappedInventoryHandler;
   private final InventoryOrder inventoryOrder;
   private final SubBackpacksHandler subBackpacksHandler;
   private List<ITrackedContentsItemHandler> handlers;
   private int[] baseIndex;

   public InceptionInventoryHandler(ITrackedContentsItemHandler wrappedInventoryHandler, InventoryOrder inventoryOrder, SubBackpacksHandler subBackpacksHandler) {
      this.wrappedInventoryHandler = wrappedInventoryHandler;
      this.inventoryOrder = inventoryOrder;
      this.subBackpacksHandler = subBackpacksHandler;
      subBackpacksHandler.addRefreshListener(sbs -> this.refreshHandlerDelegate());
      this.refreshHandlerDelegate();
   }

   private void refreshHandlerDelegate() {
      this.handlers = new ArrayList<>();
      if (this.inventoryOrder == InventoryOrder.MAIN_FIRST) {
         this.handlers.add(this.wrappedInventoryHandler);
      }

      this.subBackpacksHandler.getSubBackpacks().forEach(sbp -> this.handlers.add(sbp.getInventoryForInputOutput()));
      if (this.inventoryOrder == InventoryOrder.INCEPTED_FIRST) {
         this.handlers.add(this.wrappedInventoryHandler);
      }

      this.combinedInventories = new CombinedInvWrapper(this.handlers);
      this.baseIndex = new int[this.handlers.size()];
      int index = 0;

      for (int i = 0; i < this.handlers.size(); i++) {
         index += this.handlers.get(i).getSlotCount();
         this.baseIndex[i] = index;
      }
   }

   public void setStackInSlot(int slot, class_1799 stack) {
      this.combinedInventories.setStackInSlot(slot, stack);
   }

   public int getSlotCount() {
      return this.combinedInventories.getSlotCount();
   }

   @Nonnull
   public class_1799 getStackInSlot(int slot) {
      return this.combinedInventories.getStackInSlot(slot);
   }

   @Nonnull
   public class_1799 insertItem(int slot, class_1799 stack, boolean simulate) {
      return this.combinedInventories.insertItem(slot, stack, simulate);
   }

   public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.combinedInventories.insertSlot(slot, resource, maxAmount, ctx);
   }

   @Nonnull
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      return this.combinedInventories.extractItem(slot, amount, simulate);
   }

   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.combinedInventories.extractSlot(slot, resource, maxAmount, ctx);
   }

   public int getSlotLimit(int slot) {
      return this.combinedInventories.getSlotLimit(slot);
   }

   public boolean isItemValid(int slot, class_1799 stack) {
      return this.combinedInventories.isItemValid(slot, stack);
   }

   public class_1799 insertItem(class_1799 stack, boolean simulate) {
      class_1799 remainingStack = stack;

      for (IItemHandlerSimpleInserter handler : this.handlers) {
         remainingStack = handler.insertItem(remainingStack, simulate);
         if (remainingStack.method_7960()) {
            break;
         }
      }

      return remainingStack;
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      long remaining = maxAmount;

      for (IItemHandlerSimpleInserter handler : this.handlers) {
         remaining -= handler.insert(resource, remaining, ctx);
         if (remaining <= 0L) {
            break;
         }
      }

      return maxAmount - remaining;
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      long remaining = maxAmount;

      for (IItemHandlerSimpleInserter handler : this.handlers) {
         remaining -= handler.extract(resource, remaining, ctx);
         if (remaining <= 0L) {
            break;
         }
      }

      return maxAmount - remaining;
   }

   public Set<ItemStackKey> getTrackedStacks() {
      Set<ItemStackKey> ret = new HashSet<>();
      this.handlers.forEach(h -> ret.addAll(h.getTrackedStacks()));
      return ret;
   }

   public void registerTrackingListeners(
      Consumer<ItemStackKey> onAddStackKey, Consumer<ItemStackKey> onRemoveStackKey, Runnable onAddFirstEmptySlot, Runnable onRemoveLastEmptySlot
   ) {
      this.handlers.forEach(h -> h.registerTrackingListeners(onAddStackKey, onRemoveStackKey, onAddFirstEmptySlot, onRemoveLastEmptySlot));
   }

   public void unregisterStackKeyListeners() {
      this.handlers.forEach(ITrackedContentsItemHandler::unregisterStackKeyListeners);
   }

   public boolean hasEmptySlots() {
      return this.handlers.stream().anyMatch(ITrackedContentsItemHandler::hasEmptySlots);
   }

   public int getInternalSlotLimit(int slot) {
      int index = this.getIndexForSlot(slot);
      ITrackedContentsItemHandler handler = this.getHandlerFromIndex(index);
      int localSlot = this.getSlotFromIndex(slot, index);
      return handler.getInternalSlotLimit(localSlot);
   }

   private int getIndexForSlot(int slot) {
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

   private int getSlotFromIndex(int slot, int index) {
      return index > 0 && index < this.baseIndex.length ? slot - this.baseIndex[index - 1] : slot;
   }

   private ITrackedContentsItemHandler getHandlerFromIndex(int index) {
      return index >= 0 && index < this.handlers.size() ? this.handlers.get(index) : (ITrackedContentsItemHandler)this.handlers.getFirst();
   }

   public SingleSlotStorage<ItemVariant> getSlot(int slot) {
      return this.combinedInventories.getSlot(slot);
   }

   public Iterator<StorageView<ItemVariant>> iterator() {
      return this.combinedInventories.iterator();
   }
}
