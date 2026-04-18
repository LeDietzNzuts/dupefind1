package net.p3pp3rf1y.sophisticatedcore.network;

import io.netty.buffer.ByteBuf;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.impl.transfer.item.InventoryStorageImpl;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public record TransferItemsPayload(boolean transferToInventory, boolean filterByContents) implements class_8710 {
   public static final class_9154<TransferItemsPayload> TYPE = new class_9154(SophisticatedCore.getRL("transfer_items"));
   public static final class_9139<ByteBuf, TransferItemsPayload> STREAM_CODEC = class_9139.method_56435(
      class_9135.field_48547,
      TransferItemsPayload::transferToInventory,
      class_9135.field_48547,
      TransferItemsPayload::filterByContents,
      TransferItemsPayload::new
   );

   public static void handlePayload(TransferItemsPayload payload, Context context) {
      class_1657 player = context.player();
      if (player.field_7512 instanceof StorageContainerMenuBase<?> storageMenu) {
         IStorageWrapper var5 = storageMenu.getStorageWrapper();
         if (payload.transferToInventory) {
            if (payload.filterByContents) {
               mergeToPlayersInventoryFiltered(player, var5);
            } else {
               mergeToPlayersInventory(var5, player);
            }
         } else {
            InventoryHelper.transfer(
               new TransferItemsPayload.PlayerMainInvWithoutHotbarWrapper(player.method_31548()),
               new TransferItemsPayload.FilteredStorageItemHandler(var5, payload.filterByContents),
               s -> {}
            );
         }
      }
   }

   private static void mergeToPlayersInventory(IStorageWrapper storageWrapper, class_1657 player) {
      InventoryHelper.iterate(storageWrapper.getInventoryHandler(), (slot, stack) -> {
         if (!stack.method_7960()) {
            class_1799 result = InventoryHelper.mergeIntoPlayerInventory(player, stack, 9);
            if (result.method_7947() != stack.method_7947()) {
               storageWrapper.getInventoryHandler().setStackInSlot(slot, result);
            }
         }
      });
   }

   private static void mergeToPlayersInventoryFiltered(class_1657 player, IStorageWrapper storageWrapper) {
      Set<ItemStackKey> uniqueStacks = InventoryHelper.getUniqueStacks(new TransferItemsPayload.PlayerMainInvWrapper(player.method_31548()));
      InventoryHelper.iterate(storageWrapper.getInventoryHandler(), (slot, stack) -> {
         if (!stack.method_7960() && uniqueStacks.contains(ItemStackKey.of(stack))) {
            class_1799 result = InventoryHelper.mergeIntoPlayerInventory(player, stack, 0);
            if (result.method_7947() != stack.method_7947()) {
               storageWrapper.getInventoryHandler().setStackInSlot(slot, result);
            }
         }
      });
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   private static class FilteredItemHandler<T extends IItemHandlerSimpleInserter> implements IItemHandlerSimpleInserter {
      protected final T itemHandler;
      protected final boolean matchContents;
      private final Set<ItemStackKey> uniqueStacks;

      public FilteredItemHandler(T itemHandler, boolean matchContents) {
         this.itemHandler = itemHandler;
         this.matchContents = matchContents;
         this.uniqueStacks = this.getUniqueStacks(itemHandler);
      }

      protected Set<ItemStackKey> getUniqueStacks(T itemHandler) {
         return InventoryHelper.getUniqueStacks(itemHandler);
      }

      public int getSlotCount() {
         return this.itemHandler.getSlotCount();
      }

      public SingleSlotStorage<ItemVariant> getSlot(int slot) {
         return this.itemHandler.getSlot(slot);
      }

      @Nonnull
      public class_1799 getStackInSlot(int slot) {
         return this.itemHandler.getStackInSlot(slot);
      }

      public void setStackInSlot(int slot, class_1799 stack) {
      }

      @Nonnull
      @Override
      public class_1799 insertItem(int slot, class_1799 stack, boolean simulate) {
         return this.matchContents && !this.matchesFilter(stack) ? stack : this.itemHandler.insertItem(slot, stack, simulate);
      }

      protected boolean matchesFilter(class_1799 stack) {
         return this.uniqueStacks.contains(ItemStackKey.of(stack));
      }

      @Nonnull
      @Override
      public class_1799 extractItem(int slot, int amount, boolean simulate) {
         return this.itemHandler.extractItem(slot, amount, simulate);
      }

      public int getSlotLimit(int slot) {
         return this.itemHandler.getSlotLimit(slot);
      }

      @Override
      public boolean isItemValid(int slot, class_1799 stack) {
         return this.itemHandler.isItemValid(slot, stack);
      }

      public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         return 0L;
      }

      public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         return 0L;
      }
   }

   private static class FilteredStorageItemHandler
      extends TransferItemsPayload.FilteredItemHandler<ITrackedContentsItemHandler>
      implements IItemHandlerSimpleInserter {
      private final IStorageWrapper storageWrapper;

      public FilteredStorageItemHandler(IStorageWrapper storageWrapper, boolean smart) {
         super(storageWrapper.getInventoryHandler(), smart);
         this.storageWrapper = storageWrapper;
      }

      protected Set<ItemStackKey> getUniqueStacks(ITrackedContentsItemHandler itemHandler) {
         return itemHandler.getTrackedStacks();
      }

      @Override
      protected boolean matchesFilter(class_1799 stack) {
         return super.matchesFilter(stack) || this.storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class).matchesFilter(stack);
      }

      @Nonnull
      @Override
      public class_1799 insertItem(class_1799 stack, boolean simulate) {
         return this.matchContents && !this.matchesFilter(stack) ? stack : this.itemHandler.insertItem(stack, simulate);
      }

      @Override
      public void setStackInSlot(int slot, class_1799 stack) {
         this.itemHandler.setStackInSlot(slot, stack);
      }
   }

   private static class PlayerMainInvWithoutHotbarWrapper extends TransferItemsPayload.RangedWrapper {
      private final class_1661 inventoryPlayer;

      public PlayerMainInvWithoutHotbarWrapper(class_1661 inv) {
         super(inv, 9, inv.field_7547.size());
         this.inventoryPlayer = inv;
      }

      @Override
      public class_1799 insertItem(int slot, class_1799 stack, boolean simulate) {
         class_1799 rest = super.insertItem(slot, stack, simulate);
         if (rest.method_7947() != stack.method_7947()) {
            class_1799 inSlot = this.getStackInSlot(slot);
            if (!inSlot.method_7960()) {
               if (this.getInventoryPlayer().field_7546.method_37908().field_9236) {
                  inSlot.method_7912(5);
               } else if (this.getInventoryPlayer().field_7546 instanceof class_3222) {
                  this.getInventoryPlayer().field_7546.field_7512.method_7623();
               }
            }
         }

         return rest;
      }

      public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
         long inserted = super.insertSlot(slot, resource, maxAmount, transaction);
         if (inserted != maxAmount) {
            class_1799 inSlot = this.getStackInSlot(slot);
            if (!inSlot.method_7960()) {
               if (this.getInventoryPlayer().field_7546.method_37908().field_9236) {
                  inSlot.method_7912(5);
               } else if (this.getInventoryPlayer().field_7546 instanceof class_3222) {
                  this.getInventoryPlayer().field_7546.field_7512.method_7623();
               }
            }
         }

         return 0L;
      }

      @Override
      public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         long inserted = super.insert(resource, maxAmount, transaction);
         if (inserted != maxAmount
            && !this.getInventoryPlayer().field_7546.method_37908().field_9236
            && this.getInventoryPlayer().field_7546 instanceof class_3222) {
            this.getInventoryPlayer().field_7546.field_7512.method_7623();
         }

         return inserted;
      }

      public class_1661 getInventoryPlayer() {
         return this.inventoryPlayer;
      }
   }

   private static class PlayerMainInvWrapper extends TransferItemsPayload.RangedWrapper {
      private final class_1661 inventoryPlayer;

      public PlayerMainInvWrapper(class_1661 inv) {
         super(inv, 0, inv.field_7547.size());
         this.inventoryPlayer = inv;
      }

      @Override
      public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         long inserted = super.insert(resource, maxAmount, transaction);
         if (inserted != maxAmount
            && !this.getInventoryPlayer().field_7546.method_37908().field_9236
            && this.getInventoryPlayer().field_7546 instanceof class_3222) {
            this.getInventoryPlayer().field_7546.field_7512.method_7623();
         }

         return inserted;
      }

      public class_1661 getInventoryPlayer() {
         return this.inventoryPlayer;
      }
   }

   private static class RangedWrapper implements IItemHandlerSimpleInserter {
      private final InventoryStorageImpl inventoryStorage;

      public RangedWrapper(class_1661 inv, int start, int end) {
         this.inventoryStorage = (InventoryStorageImpl)InventoryStorage.of(inv, null);
         this.inventoryStorage.parts = Collections.unmodifiableList(this.inventoryStorage.parts.subList(start, end));
      }

      public int getSlotCount() {
         return this.inventoryStorage.getSlotCount();
      }

      public SingleSlotStorage<ItemVariant> getSlot(int slot) {
         return this.inventoryStorage.getSlot(slot);
      }

      public List<SingleSlotStorage<ItemVariant>> getSlots() {
         return this.inventoryStorage.parts;
      }

      public class_1799 getStackInSlot(int slot) {
         SingleSlotStorage<ItemVariant> s = this.getSlot(slot);
         return ((ItemVariant)s.getResource()).toStack((int)s.getAmount());
      }

      public void setStackInSlot(int slot, class_1799 stack) {
      }

      public int getSlotLimit(int slot) {
         return 0;
      }

      public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         return this.inventoryStorage.insert(resource, maxAmount, transaction);
      }

      public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
         return this.inventoryStorage.extract(resource, maxAmount, transaction);
      }
   }
}
