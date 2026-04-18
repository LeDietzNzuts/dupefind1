package net.p3pp3rf1y.sophisticatedcore.inventory;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.Iterator;
import java.util.List;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryStorageWrapper implements SlottedStackStorage, IInventoryHandlerHelper {
   private final InventoryStorage wrapped;
   private final class_1263 wrappedInventory;

   public static InventoryStorageWrapper of(class_1657 player) {
      return new InventoryStorageWrapper(player.method_31548());
   }

   public static InventoryStorageWrapper of(class_1263 container) {
      return new InventoryStorageWrapper(container);
   }

   private InventoryStorageWrapper(class_1263 inventory) {
      this.wrapped = InventoryStorage.of(inventory, null);
      this.wrappedInventory = inventory;
   }

   public @UnmodifiableView List<SingleSlotStorage<ItemVariant>> getSlots() {
      return this.wrapped.getSlots();
   }

   public int getSlotCount() {
      return this.wrapped.getSlotCount();
   }

   public SingleSlotStorage<ItemVariant> getSlot(int slot) {
      return this.wrapped.getSlot(slot);
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return this.wrapped.insert(resource, maxAmount, transaction);
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return this.wrapped.extract(resource, maxAmount, transaction);
   }

   public class_1799 getStackInSlot(int slot) {
      return this.wrappedInventory.method_5438(slot);
   }

   public void setStackInSlot(int slot, class_1799 stack) {
      this.wrappedInventory.method_5447(slot, stack);
   }

   public int getSlotLimit(int slot) {
      return (int)this.wrapped.getSlot(slot).getCapacity();
   }

   public Iterator<StorageView<ItemVariant>> iterator() {
      return this.wrapped.iterator();
   }
}
