package net.p3pp3rf1y.sophisticatedcore.inventory;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.List;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.Nullable;

public class SlottedStackStorageContainerItemContext implements ContainerItemContext {
   private final SlottedStackStorage wrappedStorage;
   private final int slot;

   public static SlottedStackStorageContainerItemContext of(SlottedStackStorage storage, int slot) {
      return new SlottedStackStorageContainerItemContext(storage, slot);
   }

   public SlottedStackStorageContainerItemContext(SlottedStackStorage storage, int slot) {
      this.wrappedStorage = storage;
      this.slot = slot;
   }

   @Nullable
   public <A> A find(ItemApiLookup<A, ContainerItemContext> lookup) {
      return (A)(this.getItemVariant().isBlank() ? null : lookup.find(this.wrappedStorage.getStackInSlot(this.slot), this));
   }

   public SingleSlotStorage<ItemVariant> getMainSlot() {
      return this.wrappedStorage.getSlot(this.slot);
   }

   public long insertOverflow(ItemVariant itemVariant, long maxAmount, TransactionContext transactionContext) {
      return this.wrappedStorage.insert(itemVariant, maxAmount, transactionContext);
   }

   public List<SingleSlotStorage<ItemVariant>> getAdditionalSlots() {
      return this.wrappedStorage.getSlots();
   }
}
