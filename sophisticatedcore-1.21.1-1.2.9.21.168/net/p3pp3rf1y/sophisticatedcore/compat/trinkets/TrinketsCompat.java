package net.p3pp3rf1y.sophisticatedcore.compat.trinkets;

import dev.emi.trinkets.api.TrinketsApi;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedSlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_9331;
import net.p3pp3rf1y.sophisticatedcore.compat.ICompat;
import net.p3pp3rf1y.sophisticatedcore.util.EmptyItemHandler;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public class TrinketsCompat implements ICompat {
   @Override
   public void setup() {
      this.addInventoryItemHandler();
   }

   private void addInventoryItemHandler() {
      InventoryHelper.registerPlayerInventoryProvider(
         player -> TrinketsApi.getTrinketComponent(player)
            .map(comp -> comp.getInventory().values().stream().flatMap(group -> group.values().stream()).map(TrinketsCompat.Wrapper::new).toList())
            .map(list -> new TrinketsCompat.CombinedWrapper<>((List<TrinketsCompat.Wrapper>)list))
            .orElse(EmptyItemHandler.INSTANCE)
      );
   }

   private static class CombinedWrapper<S extends SlottedStackStorage> extends CombinedSlottedStorage<ItemVariant, S> implements SlottedStackStorage {
      protected final int[] baseIndex;
      protected final int slotCount;

      public CombinedWrapper(List<S> inventories) {
         super(inventories);
         this.baseIndex = new int[inventories.size()];
         int index = 0;

         for (int i = 0; i < inventories.size(); i++) {
            index += inventories.get(i).getSlotCount();
            this.baseIndex[i] = index;
         }

         this.slotCount = index;
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

   private static class Wrapper extends CombinedSlottedStorage<ItemVariant, SingleSlotStorage<ItemVariant>> implements SlottedStackStorage {
      private final class_1263 inventory;
      private final List<SingleStackStorage> backingList;
      final TrinketsCompat.Wrapper.MarkDirtyParticipant markDirtyParticipant = new TrinketsCompat.Wrapper.MarkDirtyParticipant();

      public Wrapper(class_1263 inventory) {
         super(Collections.emptyList());
         this.inventory = inventory;
         this.backingList = new ArrayList<>();
         this.resizeSlotList();
      }

      private void resizeSlotList() {
         int inventorySize = this.inventory.method_5439();
         if (inventorySize != this.parts.size()) {
            while (this.backingList.size() < inventorySize) {
               this.backingList.add(new TrinketsCompat.Wrapper.InventorySlotWrapper(this.backingList.size()));
            }

            this.parts = Collections.unmodifiableList(this.backingList.subList(0, inventorySize));
         }
      }

      public class_1799 getStackInSlot(int slot) {
         return this.inventory.method_5438(slot);
      }

      public void setStackInSlot(int slot, class_1799 stack) {
         this.inventory.method_5447(slot, stack);
      }

      public int getSlotLimit(int slot) {
         return (int)this.getSlot(slot).getCapacity();
      }

      class InventorySlotWrapper extends SingleStackStorage {
         final int slot;
         private class_1799 lastReleasedSnapshot = null;

         InventorySlotWrapper(int slot) {
            this.slot = slot;
         }

         protected class_1799 getStack() {
            return Wrapper.this.inventory.method_5438(this.slot);
         }

         protected void setStack(class_1799 stack) {
            Wrapper.this.inventory.method_5447(this.slot, stack);
         }

         public int getCapacity(ItemVariant variant) {
            return Math.min(Wrapper.this.inventory.method_5444(), variant.getItem().method_7882());
         }

         public void updateSnapshots(TransactionContext transaction) {
            Wrapper.this.markDirtyParticipant.updateSnapshots(transaction);
            super.updateSnapshots(transaction);
         }

         protected void releaseSnapshot(class_1799 snapshot) {
            this.lastReleasedSnapshot = snapshot;
         }

         protected void onFinalCommit() {
            class_1799 original = this.lastReleasedSnapshot;
            class_1799 currentStack = this.getStack();
            if (!original.method_7960() && original.method_7909() == currentStack.method_7909()) {
               if (!Objects.equals(original.method_57380(), currentStack.method_57380())) {
                  for (class_9331<?> type : original.method_57353().method_57831()) {
                     original.method_57379(type, null);
                  }

                  original.method_57365(currentStack.method_57353());
               }

               original.method_7939(currentStack.method_7947());
               this.setStack(original);
            } else {
               original.method_7939(0);
            }
         }
      }

      class MarkDirtyParticipant extends SnapshotParticipant<Boolean> {
         protected Boolean createSnapshot() {
            return Boolean.TRUE;
         }

         protected void readSnapshot(Boolean snapshot) {
         }

         protected void onFinalCommit() {
            Wrapper.this.inventory.method_5431();
         }
      }
   }
}
