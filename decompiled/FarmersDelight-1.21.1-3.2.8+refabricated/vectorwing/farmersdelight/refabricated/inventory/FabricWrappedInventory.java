package vectorwing.farmersdelight.refabricated.inventory;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_128;
import net.minecraft.class_148;
import org.jetbrains.annotations.NotNull;

public abstract class FabricWrappedInventory implements ItemHandler {
   private final List<ItemHandlerStackWrapper> fabricWrappers;

   public FabricWrappedInventory(int size) {
      this.fabricWrappers = Lists.newArrayListWithCapacity(size);

      for (int i = 0; i < size; i++) {
         this.fabricWrappers.add(new ItemHandlerStackWrapper(this, i));
      }
   }

   public SingleSlotStorage<ItemVariant> getSlot(int slot) {
      return (SingleSlotStorage<ItemVariant>)this.fabricWrappers.get(slot);
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      long amount = 0L;

      try {
         for (ItemHandlerStackWrapper wrapper : this.fabricWrappers) {
            amount += wrapper.insert(resource, maxAmount - amount, transaction);
            if (amount == maxAmount) {
               return maxAmount;
            }
         }

         return amount;
      } catch (Exception var9) {
         class_128 report = class_128.method_560(var9, "Inserting resources into slots");
         report.method_562("Slotted insertion details")
            .method_577("Slots", () -> Objects.toString(this.fabricWrappers, null))
            .method_577("Resource", () -> Objects.toString(resource, null))
            .method_578("Max amount", maxAmount)
            .method_578("Transaction", transaction);
         throw new class_148(report);
      }
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      long amount = 0L;

      try {
         for (ItemHandlerStackWrapper wrapper : this.fabricWrappers) {
            amount += wrapper.extract(resource, maxAmount - amount, transaction);
            if (amount == maxAmount) {
               return maxAmount;
            }
         }

         return amount;
      } catch (Exception var9) {
         class_128 report = class_128.method_560(var9, "Inserting resources into slots");
         report.method_562("Slotted insertion details")
            .method_577("Slots", () -> Objects.toString(this.fabricWrappers, null))
            .method_577("Resource", () -> Objects.toString(resource, null))
            .method_578("Max amount", maxAmount)
            .method_578("Transaction", transaction);
         throw new class_148(report);
      }
   }

   @NotNull
   public Iterator<StorageView<ItemVariant>> iterator() {
      return this.getSlots().stream().map(storageViews -> (StorageView<ItemVariant>)storageViews).iterator();
   }
}
