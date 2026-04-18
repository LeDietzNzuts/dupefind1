package net.p3pp3rf1y.sophisticatedcore.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2487;
import net.minecraft.class_2497;
import net.minecraft.class_2503;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.EmptyItemHandler;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public abstract class ControllerBlockEntityBase extends class_2586 implements IItemHandlerSimpleInserter {
   public static final int SEARCH_RANGE = 15;
   private List<class_2338> storagePositions = new ArrayList<>();
   private List<Integer> baseIndexes = new ArrayList<>();
   private int totalSlots = 0;
   private final Map<ItemStackKey, Set<class_2338>> stackStorages = new HashMap<>();
   private final Map<class_2338, Set<ItemStackKey>> storageStacks = new HashMap<>();
   private final Map<class_1792, Set<ItemStackKey>> itemStackKeys = new HashMap<>();
   private final Comparator<class_2338> distanceComparator = Comparator.<class_2338>comparingDouble(p -> p.method_10262(this.method_11016()))
      .thenComparing(Comparator.naturalOrder());
   private final Set<class_2338> emptySlotsStorages = new TreeSet<>(this.distanceComparator);
   private final Map<class_1792, Set<class_2338>> memorizedItemStorages = new HashMap<>();
   private final Map<class_2338, Set<class_1792>> storageMemorizedItems = new HashMap<>();
   private final Map<Integer, Set<class_2338>> memorizedStackStorages = new HashMap<>();
   private final Map<class_2338, Set<Integer>> storageMemorizedStacks = new HashMap<>();
   private final Map<class_1792, Set<class_2338>> filterItemStorages = new HashMap<>();
   private final Map<class_2338, Set<class_1792>> storageFilterItems = new HashMap<>();
   private Set<class_2338> linkedBlocks = new TreeSet<>(this.distanceComparator);
   private Set<class_2338> connectingBlocks = new TreeSet<>(this.distanceComparator);
   private Set<class_2338> nonConnectingBlocks = new TreeSet<>(this.distanceComparator);

   public boolean addLinkedBlock(class_2338 linkedPos) {
      if (this.field_11863 != null
         && !this.field_11863.method_8608()
         && this.isWithinRange(linkedPos)
         && !this.linkedBlocks.contains(linkedPos)
         && !this.storagePositions.contains(linkedPos)) {
         this.linkedBlocks.add(linkedPos);
         this.method_5431();
         WorldHelper.getBlockEntity(this.field_11863, linkedPos, ILinkable.class).ifPresent(l -> {
            if (l.connectLinkedSelf()) {
               Set<class_2338> positionsToCheck = new LinkedHashSet<>();
               positionsToCheck.add(linkedPos);
               this.searchAndAddBoundables(positionsToCheck, true);
            }

            this.searchAndAddBoundables(new LinkedHashSet<>(l.getConnectablePositions()), false);
         });
         WorldHelper.notifyBlockUpdate(this);
         return true;
      } else {
         return false;
      }
   }

   public void removeLinkedBlock(class_2338 storageBlockPos) {
      this.linkedBlocks.remove(storageBlockPos);
      this.method_5431();
      this.verifyStoragesConnected();
      WorldHelper.notifyBlockUpdate(this);
   }

   public void sophisticatedCore_onLoad() {
      super.sophisticatedCore_onLoad();
      if (this.field_11863 != null && !this.field_11863.method_8608()) {
         this.stackStorages.clear();
         this.storageStacks.clear();
         this.itemStackKeys.clear();
         this.emptySlotsStorages.clear();
         this.storagePositions.forEach(this::addStorageStacksAndRegisterListeners);
      }
   }

   public boolean isStorageConnected(class_2338 storagePos) {
      return this.storagePositions.contains(storagePos);
   }

   public void searchAndAddBoundables() {
      Set<class_2338> positionsToCheck = new HashSet<>();

      for (class_2350 dir : class_2350.values()) {
         positionsToCheck.add(this.method_11016().method_10081(dir.method_10163()));
      }

      this.searchAndAddBoundables(positionsToCheck, false);
   }

   public void changeSlots(class_2338 storagePos, int newSlots, boolean hasEmptySlots) {
      this.updateBaseIndexesAndTotalSlots(storagePos, newSlots);
      this.updateEmptySlots(storagePos, hasEmptySlots);
   }

   public void updateEmptySlots(class_2338 storagePos, boolean hasEmptySlots) {
      if (this.emptySlotsStorages.contains(storagePos) && !hasEmptySlots) {
         this.emptySlotsStorages.remove(storagePos);
      } else if (!this.emptySlotsStorages.contains(storagePos) && hasEmptySlots) {
         this.emptySlotsStorages.add(storagePos);
      }
   }

   private void updateBaseIndexesAndTotalSlots(class_2338 storagePos, int newSlots) {
      int index = this.storagePositions.indexOf(storagePos);
      int originalSlots = this.getStorageSlots(index);
      int diff = newSlots - originalSlots;

      for (int i = index; i < this.baseIndexes.size(); i++) {
         this.baseIndexes.set(i, this.baseIndexes.get(i) + diff);
      }

      this.totalSlots += diff;
      WorldHelper.notifyBlockUpdate(this);
   }

   private int getStorageSlots(int index) {
      int previousBaseIndex = index == 0 ? 0 : this.baseIndexes.get(index - 1);
      return this.baseIndexes.get(index) - previousBaseIndex;
   }

   public int getSlots(int storageIndex) {
      return storageIndex >= 0 && storageIndex < this.baseIndexes.size() ? this.getStorageSlots(storageIndex) : 0;
   }

   private void searchAndAddBoundables(Set<class_2338> positionsToCheck, boolean addingLinkedSelf) {
      Set<class_2338> positionsChecked = new HashSet<>();

      for (boolean first = true; !positionsToCheck.isEmpty(); first = false) {
         Iterator<class_2338> it = positionsToCheck.iterator();
         class_2338 posToCheck = it.next();
         it.remove();
         boolean finalFirst = first;
         WorldHelper.getLoadedBlockEntity(this.field_11863, posToCheck, IControllerBoundable.class)
            .ifPresentOrElse(
               boundable -> this.tryToConnectStorageAndAddPositionsToCheckAround(
                  positionsToCheck, addingLinkedSelf, positionsChecked, posToCheck, finalFirst, boundable
               ),
               () -> positionsChecked.add(posToCheck)
            );
      }
   }

   private void tryToConnectStorageAndAddPositionsToCheckAround(
      Set<class_2338> positionsToCheck,
      boolean addingLinkedSelf,
      Set<class_2338> positionsChecked,
      class_2338 posToCheck,
      boolean finalFirst,
      IControllerBoundable boundable
   ) {
      if (boundable.canBeConnected() || addingLinkedSelf && finalFirst) {
         if (boundable instanceof ILinkable linkable && linkable.isLinked() && (!addingLinkedSelf || !finalFirst)) {
            this.linkedBlocks.remove(posToCheck);
            linkable.setNotLinked();
         } else if (boundable instanceof IControllableStorage storage && storage.hasStorageData()) {
            this.addStorageData(posToCheck);
         } else {
            if (boundable.canConnectStorages()) {
               this.connectingBlocks.add(posToCheck);
            } else {
               this.nonConnectingBlocks.add(posToCheck);
            }

            boundable.registerController(this);
         }

         if (boundable.canConnectStorages()) {
            this.addUncheckedPositionsAround(positionsToCheck, positionsChecked, posToCheck);
         }
      }
   }

   private void addUncheckedPositionsAround(Set<class_2338> positionsToCheck, Set<class_2338> positionsChecked, class_2338 currentPos) {
      for (class_2350 dir : class_2350.values()) {
         class_2338 pos = currentPos.method_10081(dir.method_10163());
         if (!positionsChecked.contains(pos)
            && (
               !this.storagePositions.contains(pos) && !this.connectingBlocks.contains(pos) && !this.nonConnectingBlocks.contains(pos)
                  || this.linkedBlocks.contains(pos)
            )
            && this.isWithinRange(pos)) {
            positionsToCheck.add(pos);
         }
      }
   }

   private boolean isWithinRange(class_2338 pos) {
      return Math.abs(pos.method_10263() - this.method_11016().method_10263()) <= 15
         && Math.abs(pos.method_10264() - this.method_11016().method_10264()) <= 15
         && Math.abs(pos.method_10260() - this.method_11016().method_10260()) <= 15;
   }

   public void addStorage(class_2338 storagePos) {
      if (this.storagePositions.contains(storagePos)) {
         this.removeStorageInventoryData(storagePos);
      }

      if (this.isWithinRange(storagePos)) {
         HashSet<class_2338> positionsToCheck = new LinkedHashSet<>();
         positionsToCheck.add(storagePos);
         this.searchAndAddBoundables(positionsToCheck, false);
      }
   }

   private void addStorageData(class_2338 storagePos) {
      this.storagePositions.add(storagePos);
      this.totalSlots = this.totalSlots + this.<Integer>getInventoryHandlerValueFromHolder(storagePos, SlottedStorage::getSlotCount).orElse(0);
      this.baseIndexes.add(this.totalSlots);
      this.addStorageStacksAndRegisterListeners(storagePos);
      this.method_5431();
      WorldHelper.notifyBlockUpdate(this);
   }

   public void addStorageStacksAndRegisterListeners(class_2338 storagePos) {
      WorldHelper.getLoadedBlockEntity(this.field_11863, storagePos, IControllableStorage.class).ifPresent(storage -> {
         ITrackedContentsItemHandler handler = storage.getStorageWrapper().getInventoryForInputOutput();
         handler.getTrackedStacks().forEach(k -> this.addStorageStack(storagePos, k));
         if (handler.hasEmptySlots()) {
            this.emptySlotsStorages.add(storagePos);
         }

         MemorySettingsCategory memorySettings = storage.getStorageWrapper().getSettingsHandler().getTypeCategory(MemorySettingsCategory.class);
         memorySettings.getFilterItemSlots().keySet().forEach(i -> this.addStorageMemorizedItem(storagePos, i));
         memorySettings.getFilterStackSlots().keySet().forEach(stackHash -> this.addStorageMemorizedStack(storagePos, stackHash));
         this.setStorageFilterItems(storagePos, storage.getStorageWrapper().getInventoryHandler().getFilterItems());
         storage.registerController(this);
      });
   }

   public void addStorageMemorizedItem(class_2338 storagePos, class_1792 item) {
      this.memorizedItemStorages.computeIfAbsent(item, stackKey -> new LinkedHashSet<>()).add(storagePos);
      this.storageMemorizedItems.computeIfAbsent(storagePos, pos -> new HashSet<>()).add(item);
   }

   public void addStorageMemorizedStack(class_2338 storagePos, int stackHash) {
      this.memorizedStackStorages.computeIfAbsent(stackHash, stackKey -> new LinkedHashSet<>()).add(storagePos);
      this.storageMemorizedStacks.computeIfAbsent(storagePos, pos -> new HashSet<>()).add(stackHash);
   }

   public void removeStorageMemorizedItem(class_2338 storagePos, class_1792 item) {
      this.memorizedItemStorages.computeIfPresent(item, (i, positions) -> {
         positions.remove(storagePos);
         return positions;
      });
      if (this.memorizedItemStorages.containsKey(item) && this.memorizedItemStorages.get(item).isEmpty()) {
         this.memorizedItemStorages.remove(item);
      }

      this.storageMemorizedItems.remove(storagePos);
   }

   public void removeStorageMemorizedStack(class_2338 storagePos, int stackHash) {
      this.memorizedStackStorages.computeIfPresent(stackHash, (i, positions) -> {
         positions.remove(storagePos);
         return positions;
      });
      if (this.memorizedStackStorages.containsKey(stackHash) && this.memorizedStackStorages.get(stackHash).isEmpty()) {
         this.memorizedStackStorages.remove(stackHash);
      }

      this.storageMemorizedStacks.remove(storagePos);
   }

   private <T> Optional<T> getInventoryHandlerValueFromHolder(class_2338 storagePos, Function<IItemHandlerSimpleInserter, T> valueGetter) {
      return this.getWrapperValueFromHolder(storagePos, wrapper -> valueGetter.apply(wrapper.getInventoryForInputOutput()));
   }

   private <T> Optional<T> getWrapperValueFromHolder(class_2338 storagePos, Function<IStorageWrapper, T> valueGetter) {
      return WorldHelper.getLoadedBlockEntity(this.field_11863, storagePos, IControllableStorage.class)
         .map(holder -> valueGetter.apply(holder.getStorageWrapper()));
   }

   public void addStorageStack(class_2338 storagePos, ItemStackKey itemStackKey) {
      this.stackStorages.computeIfAbsent(itemStackKey, stackKey -> new LinkedHashSet<>()).add(storagePos);
      this.storageStacks.computeIfAbsent(storagePos, pos -> new HashSet<>()).add(itemStackKey);
      this.itemStackKeys.computeIfAbsent(itemStackKey.getStack().method_7909(), item -> new LinkedHashSet<>()).add(itemStackKey);
   }

   public void removeStorageStack(class_2338 storagePos, ItemStackKey stackKey) {
      this.stackStorages.computeIfPresent(stackKey, (sk, positions) -> {
         positions.remove(storagePos);
         return positions;
      });
      if (this.stackStorages.containsKey(stackKey) && this.stackStorages.get(stackKey).isEmpty()) {
         this.stackStorages.remove(stackKey);
         this.itemStackKeys.computeIfPresent(stackKey.getStack().method_7909(), (i, stackKeys) -> {
            stackKeys.remove(stackKey);
            return stackKeys;
         });
         if (this.itemStackKeys.containsKey(stackKey.getStack().method_7909()) && this.itemStackKeys.get(stackKey.getStack().method_7909()).isEmpty()) {
            this.itemStackKeys.remove(stackKey.getStack().method_7909());
         }
      }

      this.storageStacks.computeIfPresent(storagePos, (pos, stackKeys) -> {
         stackKeys.remove(stackKey);
         return stackKeys;
      });
      if (this.storageStacks.containsKey(storagePos) && this.storageStacks.get(storagePos).isEmpty()) {
         this.storageStacks.remove(storagePos);
      }
   }

   public void removeStorageStacks(class_2338 storagePos) {
      this.storageStacks
         .computeIfPresent(
            storagePos,
            (pos, stackKeys) -> {
               stackKeys.forEach(
                  stackKey -> {
                     Set<class_2338> storages = this.stackStorages.get(stackKey);
                     if (storages != null) {
                        storages.remove(storagePos);
                        if (storages.isEmpty()) {
                           this.stackStorages.remove(stackKey);
                           this.itemStackKeys.computeIfPresent(stackKey.getStack().method_7909(), (i, positions) -> {
                              positions.remove(stackKey);
                              return positions;
                           });
                           if (this.itemStackKeys.containsKey(stackKey.getStack().method_7909())
                              && this.itemStackKeys.get(stackKey.getStack().method_7909()).isEmpty()) {
                              this.itemStackKeys.remove(stackKey.getStack().method_7909());
                           }
                        }
                     }
                  }
               );
               return stackKeys;
            }
         );
      this.storageStacks.remove(storagePos);
   }

   protected boolean hasItem(class_1792 item) {
      return this.itemStackKeys.containsKey(item);
   }

   protected boolean isMemorizedItem(class_1799 stack) {
      return this.memorizedItemStorages.containsKey(stack.method_7909()) || this.memorizedStackStorages.containsKey(class_1799.method_57355(stack));
   }

   protected boolean isFilterItem(class_1792 item) {
      return this.filterItemStorages.containsKey(item);
   }

   public void removeStorage(class_2338 storagePos) {
      this.removeConnectingBlock(storagePos);
      this.removeStorageInventoryDataAndUnregisterController(storagePos);
      this.verifyStoragesConnected();
   }

   private void removeConnectingBlock(class_2338 storagePos) {
      if (this.connectingBlocks.remove(storagePos)) {
         WorldHelper.getLoadedBlockEntity(this.field_11863, storagePos, IControllableStorage.class).ifPresent(IControllableStorage::unregisterController);
      }
   }

   public void removeNonConnectingBlock(class_2338 storagePos) {
      if (this.nonConnectingBlocks.remove(storagePos)) {
         WorldHelper.getLoadedBlockEntity(this.field_11863, storagePos, IControllerBoundable.class).ifPresent(IControllerBoundable::unregisterController);
      }
   }

   private void removeStorageInventoryDataAndUnregisterController(class_2338 storagePos) {
      if (this.storagePositions.contains(storagePos)) {
         this.removeStorageInventoryData(storagePos);
         this.linkedBlocks.remove(storagePos);
         WorldHelper.getLoadedBlockEntity(this.field_11863, storagePos, IControllableStorage.class).ifPresent(IControllableStorage::unregisterController);
         this.method_5431();
         WorldHelper.notifyBlockUpdate(this);
      }
   }

   private void removeStorageInventoryData(class_2338 storagePos) {
      int idx = this.storagePositions.indexOf(storagePos);
      this.totalSlots = this.totalSlots - this.getStorageSlots(idx);
      this.removeStorageStacks(storagePos);
      this.removeStorageMemorizedItems(storagePos);
      this.removeStorageMemorizedStacks(storagePos);
      this.removeStorageWithEmptySlots(storagePos);
      this.removeStorageFilterItems(storagePos);
      this.storagePositions.remove(idx);
      this.removeBaseIndexAt(idx);
   }

   private void removeStorageFilterItems(class_2338 storagePos) {
      this.storageFilterItems.computeIfPresent(storagePos, (pos, items) -> {
         items.forEach(item -> {
            Set<class_2338> storages = this.filterItemStorages.get(item);
            if (storages != null) {
               storages.remove(storagePos);
               if (storages.isEmpty()) {
                  this.filterItemStorages.remove(item);
               }
            }
         });
         return items;
      });
      this.storageFilterItems.remove(storagePos);
   }

   private void removeStorageMemorizedItems(class_2338 storagePos) {
      this.storageMemorizedItems.computeIfPresent(storagePos, (pos, items) -> {
         items.forEach(item -> {
            Set<class_2338> storages = this.memorizedItemStorages.get(item);
            if (storages != null) {
               storages.remove(storagePos);
               if (storages.isEmpty()) {
                  this.memorizedItemStorages.remove(item);
               }
            }
         });
         return items;
      });
      this.storageMemorizedItems.remove(storagePos);
   }

   private void removeStorageMemorizedStacks(class_2338 storagePos) {
      this.storageMemorizedStacks.computeIfPresent(storagePos, (pos, items) -> {
         items.forEach(stackHash -> {
            Set<class_2338> storages = this.memorizedStackStorages.get(stackHash);
            if (storages != null) {
               storages.remove(storagePos);
               if (storages.isEmpty()) {
                  this.memorizedStackStorages.remove(stackHash);
               }
            }
         });
         return items;
      });
      this.storageMemorizedStacks.remove(storagePos);
   }

   private void verifyStoragesConnected() {
      HashSet<class_2338> toVerify = new HashSet<>(this.storagePositions);
      toVerify.addAll(this.connectingBlocks);
      toVerify.addAll(this.nonConnectingBlocks);
      Set<class_2338> positionsToCheck = new HashSet<>();

      for (class_2350 dir : class_2350.values()) {
         class_2338 offsetPos = this.method_11016().method_10081(dir.method_10163());
         if (toVerify.contains(offsetPos)) {
            positionsToCheck.add(offsetPos);
         }
      }

      Set<class_2338> positionsChecked = new HashSet<>();
      this.verifyConnected(toVerify, positionsToCheck, positionsChecked);
      this.linkedBlocks.forEach(linkedPosition -> WorldHelper.getBlockEntity(this.method_10997(), linkedPosition, ILinkable.class).ifPresent(l -> {
         if (l.connectLinkedSelf() && toVerify.contains(linkedPosition)) {
            positionsToCheck.add(linkedPosition);
         }

         l.getConnectablePositions().forEach(p -> {
            if (toVerify.contains(p)) {
               positionsToCheck.add(p);
            }
         });
      }));
      this.verifyConnected(toVerify, positionsToCheck, positionsChecked);
      toVerify.forEach(storagePos -> {
         this.removeConnectingBlock(storagePos);
         this.removeNonConnectingBlock(storagePos);
         this.removeStorageInventoryDataAndUnregisterController(storagePos);
      });
   }

   private void verifyConnected(HashSet<class_2338> toVerify, Set<class_2338> positionsToCheck, Set<class_2338> positionsChecked) {
      while (!positionsToCheck.isEmpty()) {
         Iterator<class_2338> it = positionsToCheck.iterator();
         class_2338 posToCheck = it.next();
         it.remove();
         positionsChecked.add(posToCheck);
         WorldHelper.getLoadedBlockEntity(this.field_11863, posToCheck, IControllerBoundable.class).ifPresent(h -> {
            toVerify.remove(posToCheck);
            if (h.canConnectStorages()) {
               for (class_2350 dir : class_2350.values()) {
                  class_2338 pos = posToCheck.method_10081(dir.method_10163());
                  if (!positionsChecked.contains(pos) && toVerify.contains(pos)) {
                     positionsToCheck.add(pos);
                  }
               }
            }
         });
      }
   }

   private void removeBaseIndexAt(int idx) {
      if (idx < this.baseIndexes.size()) {
         int slotsRemoved = this.getStorageSlots(idx);
         this.baseIndexes.remove(idx);

         for (int i = idx; i < this.baseIndexes.size(); i++) {
            this.baseIndexes.set(i, this.baseIndexes.get(i) - slotsRemoved);
         }
      }
   }

   protected ControllerBlockEntityBase(class_2591<?> blockEntityType, class_2338 pos, class_2680 state) {
      super(blockEntityType, pos, state);
   }

   public int getSlotCount() {
      return (R)this.totalSlots;
   }

   private int getIndexForSlot(int slot) {
      if (slot < 0) {
         return -1;
      } else {
         for (int i = 0; i < this.baseIndexes.size(); i++) {
            if (slot - this.baseIndexes.get(i) < 0) {
               return i;
            }
         }

         return -1;
      }
   }

   protected IItemHandlerSimpleInserter getHandlerFromIndex(int index) {
      return (IItemHandlerSimpleInserter)(index >= 0 && index < this.storagePositions.size()
         ? this.getHandlerFromBlockPos(this.storagePositions.get(index))
         : EmptyItemHandler.INSTANCE);
   }

   private IItemHandlerSimpleInserter getHandlerFromBlockPos(class_2338 pos) {
      return this.<IItemHandlerSimpleInserter>getWrapperValueFromHolder(pos, wrapper -> wrapper.getInventoryForInputOutput()).orElse(EmptyItemHandler.INSTANCE);
   }

   protected int getSlotFromIndex(int slot, int index) {
      return index > 0 && index < this.baseIndexes.size() ? slot - this.baseIndexes.get(index - 1) : slot;
   }

   @Nonnull
   public class_1799 getStackInSlot(int slot) {
      if (this.isSlotIndexInvalid(slot)) {
         return class_1799.field_8037;
      } else {
         int handlerIndex = this.getIndexForSlot(slot);
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(handlerIndex);
         slot = this.getSlotFromIndex(slot, handlerIndex);
         return this.validateHandlerSlotIndex(handler, handlerIndex, slot, "getStackInSlot") ? handler.getStackInSlot(slot) : class_1799.field_8037;
      }
   }

   private boolean isSlotIndexInvalid(int slot) {
      return slot < 0 || slot >= this.totalSlots;
   }

   private boolean validateHandlerSlotIndex(IItemHandlerSimpleInserter handler, int handlerIndex, int slot, String methodName) {
      if (slot >= 0 && slot < handler.getSlotCount()) {
         return true;
      } else {
         if (handlerIndex >= 0 && handlerIndex < this.storagePositions.size()) {
            SophisticatedCore.LOGGER
               .debug(
                  "Invalid slot {} passed into controller's {} method for storage at {}. If you see many of these messages try replacing controller at {}",
                  new Object[]{slot, methodName, this.storagePositions.get(handlerIndex).method_23854(), this.method_11016().method_23854()}
               );
         } else {
            SophisticatedCore.LOGGER
               .debug(
                  "Invalid handler index calculated {} in controller's {} method. If you see many of these messages try replacing controller at {}",
                  new Object[]{handlerIndex, methodName, this.method_11016().method_23854()}
               );
         }

         return false;
      }
   }

   public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.isItemValid(slot, resource, (int)maxAmount) ? this.insert(resource, maxAmount, ctx, true) : 0L;
   }

   @Override
   public class_1799 insertItem(int slot, class_1799 stack, boolean simulate) {
      Transaction ctx = Transaction.openOuter();

      long inserted;
      try {
         inserted = this.insertSlot(slot, ItemVariant.of(stack), stack.method_7947(), ctx);
         if (!simulate) {
            ctx.commit();
         }
      } catch (Throwable var10) {
         if (ctx != null) {
            try {
               ctx.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }
         }

         throw var10;
      }

      if (ctx != null) {
         ctx.close();
      }

      return stack.method_46651(stack.method_7947() - (int)inserted);
   }

   protected class_1799 insertItem(class_1799 stack, boolean simulate, boolean insertIntoAnyEmpty) {
      Transaction ctx = Transaction.openOuter();

      long inserted;
      try {
         inserted = this.insert(ItemVariant.of(stack), stack.method_7947(), ctx, insertIntoAnyEmpty);
         if (!simulate) {
            ctx.commit();
         }
      } catch (Throwable var10) {
         if (ctx != null) {
            try {
               ctx.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }
         }

         throw var10;
      }

      if (ctx != null) {
         ctx.close();
      }

      return inserted < stack.method_7947() ? stack.method_46651(stack.method_7947() - (int)inserted) : class_1799.field_8037;
   }

   public long insert(ItemVariant resource, long maxAmount, @Nullable TransactionContext ctx) {
      return this.insert(resource, maxAmount, ctx, true);
   }

   public long insert(ItemVariant resource, long maxAmount, @Nullable TransactionContext ctx, boolean insertIntoAnyEmpty) {
      ItemStackKey stackKey = ItemStackKey.of(resource.toStack());
      long remaining = maxAmount - this.insertIntoStoragesThatMatchStack(resource, maxAmount, stackKey, ctx);
      if (remaining == 0L) {
         return maxAmount;
      } else {
         remaining -= this.insertIntoStoragesThatMatchItem(resource, remaining, ctx);
         if (remaining == 0L) {
            return maxAmount;
         } else {
            if (this.memorizedItemStorages.containsKey(resource.getItem())) {
               remaining -= this.insertIntoStorages(this.memorizedItemStorages.get(resource.getItem()), resource, remaining, ctx, false);
               if (remaining == 0L) {
                  return maxAmount;
               }
            }

            int stackHash = stackKey.hashCode();
            if (this.memorizedStackStorages.containsKey(stackHash)) {
               remaining -= this.insertIntoStorages(this.memorizedStackStorages.get(stackHash), resource, remaining, ctx, false);
               if (remaining == 0L) {
                  return maxAmount;
               }
            }

            if (this.filterItemStorages.containsKey(resource.getItem())) {
               remaining -= this.insertIntoStorages(this.filterItemStorages.get(resource.getItem()), resource, remaining, ctx, false);
               if (remaining == 0L) {
                  return maxAmount;
               }
            }

            return insertIntoAnyEmpty
               ? maxAmount - remaining + this.insertIntoStorages(this.emptySlotsStorages, resource, remaining, ctx, false)
               : maxAmount - remaining;
         }
      }
   }

   private long insertIntoStoragesThatMatchStack(ItemVariant resource, long maxAmount, ItemStackKey stackKey, @Nullable TransactionContext ctx) {
      long remaining = maxAmount;
      if (this.stackStorages.containsKey(stackKey)) {
         Set<class_2338> positions = this.stackStorages.get(stackKey);
         remaining = maxAmount - this.insertIntoStorages(positions, resource, maxAmount, ctx, false);
      }

      return maxAmount - remaining;
   }

   private long insertIntoStoragesThatMatchItem(ItemVariant resource, long maxAmount, @Nullable TransactionContext ctx) {
      long remaining = maxAmount;
      if (!this.emptySlotsStorages.isEmpty() && this.itemStackKeys.containsKey(resource.getItem())) {
         Set<ItemStackKey> matchingStackKeys = this.itemStackKeys.get(resource.getItem());
         if (maxAmount > resource.toStack().method_7914()) {
            matchingStackKeys = new LinkedHashSet<>(matchingStackKeys);
         }

         for (ItemStackKey key : matchingStackKeys) {
            if (this.stackStorages.containsKey(key)) {
               Set<class_2338> positions = this.stackStorages.get(key);
               remaining -= this.insertIntoStorages(positions, resource, remaining, ctx, true);
               if (remaining == 0L) {
                  return maxAmount;
               }
            }
         }
      }

      return maxAmount - remaining;
   }

   private long insertIntoStorages(
      Set<class_2338> positions, ItemVariant resource, long maxAmount, @Nullable TransactionContext ctx, boolean checkHasEmptySlotFirst
   ) {
      long remaining = maxAmount;

      for (class_2338 storagePos : new LinkedHashSet<>(positions)) {
         if (!checkHasEmptySlotFirst || this.emptySlotsStorages.contains(storagePos)) {
            remaining -= this.insertIntoStorage(storagePos, resource, remaining, ctx);
            if (remaining == 0L) {
               return maxAmount;
            }
         }
      }

      return maxAmount - remaining;
   }

   private long insertIntoStorage(class_2338 storagePos, ItemVariant resource, long maxAmount, @Nullable TransactionContext ctx) {
      return this.<Long>getInventoryHandlerValueFromHolder(storagePos, ins -> {
         Transaction inner = Transaction.openNested(ctx);

         Long var8;
         try {
            long inserted = ins.insert(resource, maxAmount, ctx);
            inner.commit();
            var8 = inserted;
         } catch (Throwable var10) {
            if (inner != null) {
               try {
                  inner.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }
            }

            throw var10;
         }

         if (inner != null) {
            inner.close();
         }

         return var8;
      }).orElse(0L);
   }

   @Nonnull
   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      if (this.isSlotIndexInvalid(slot)) {
         return class_1799.field_8037;
      } else {
         int handlerIndex = this.getIndexForSlot(slot);
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(handlerIndex);
         slot = this.getSlotFromIndex(slot, handlerIndex);
         return this.validateHandlerSlotIndex(handler, handlerIndex, slot, "extractItem(int slot, int amount, boolean simulate)")
            ? handler.extractItem(slot, amount, simulate)
            : class_1799.field_8037;
      }
   }

   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      if (this.isSlotIndexInvalid(slot)) {
         return 0L;
      } else {
         int handlerIndex = this.getIndexForSlot(slot);
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(handlerIndex);
         slot = this.getSlotFromIndex(slot, handlerIndex);
         return this.validateHandlerSlotIndex(handler, handlerIndex, slot, "extractItem(int slot, int amount, boolean simulate)")
            ? handler.extractSlot(slot, resource, maxAmount, ctx)
            : 0L;
      }
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      long remaining = maxAmount;

      for (int i = 0; i < this.storagePositions.size(); i++) {
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(i);
         remaining -= handler.extract(resource, remaining, ctx);
         if (remaining == 0L) {
            return maxAmount;
         }
      }

      return maxAmount - remaining;
   }

   public int getSlotLimit(int slot) {
      if (this.isSlotIndexInvalid(slot)) {
         return 0;
      } else {
         int handlerIndex = this.getIndexForSlot(slot);
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(handlerIndex);
         int localSlot = this.getSlotFromIndex(slot, handlerIndex);
         return this.validateHandlerSlotIndex(handler, handlerIndex, localSlot, "getSlotLimit(int slot)") ? handler.getSlotLimit(localSlot) : 0;
      }
   }

   @Override
   public boolean isItemValid(int slot, class_1799 stack) {
      if (this.isSlotIndexInvalid(slot)) {
         return false;
      } else {
         int handlerIndex = this.getIndexForSlot(slot);
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(handlerIndex);
         int localSlot = this.getSlotFromIndex(slot, handlerIndex);
         return this.validateHandlerSlotIndex(handler, handlerIndex, localSlot, "isItemValid(int slot, ItemStack stack)")
            ? handler.isItemValid(localSlot, stack)
            : false;
      }
   }

   public void setStackInSlot(int slot, class_1799 stack) {
      if (!this.isSlotIndexInvalid(slot)) {
         int handlerIndex = this.getIndexForSlot(slot);
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(handlerIndex);
         slot = this.getSlotFromIndex(slot, handlerIndex);
         if (this.validateHandlerSlotIndex(handler, handlerIndex, slot, "setStackInSlot(int slot, ItemStack stack)")) {
            handler.setStackInSlot(slot, stack);
         }
      }
   }

   public void sophisticatedCore_onChunkUnloaded() {
      super.sophisticatedCore_onChunkUnloaded();
      this.detachFromStoragesAndUnlinkBlocks();
   }

   public void detachFromStoragesAndUnlinkBlocks() {
      this.storagePositions
         .forEach(
            pos -> WorldHelper.getLoadedBlockEntity(this.field_11863, pos, IControllableStorage.class).ifPresent(IControllableStorage::unregisterController)
         );
      this.connectingBlocks
         .forEach(
            pos -> WorldHelper.getLoadedBlockEntity(this.field_11863, pos, IControllableStorage.class).ifPresent(IControllableStorage::unregisterController)
         );
      this.nonConnectingBlocks
         .forEach(
            pos -> WorldHelper.getLoadedBlockEntity(this.field_11863, pos, IControllerBoundable.class).ifPresent(IControllerBoundable::unregisterController)
         );
      new HashSet<>(this.linkedBlocks)
         .forEach(linkedPos -> WorldHelper.getLoadedBlockEntity(this.field_11863, linkedPos, ILinkable.class).ifPresent(ILinkable::unlinkFromController));
   }

   protected void method_11007(class_2487 tag, class_7874 registries) {
      super.method_11007(tag, registries);
      this.saveData(tag);
   }

   private class_2487 saveData(class_2487 tag) {
      NBTHelper.putList(tag, "storagePositions", this.storagePositions, p -> class_2503.method_23251(p.method_10063()));
      NBTHelper.putList(tag, "connectingBlocks", this.connectingBlocks, p -> class_2503.method_23251(p.method_10063()));
      NBTHelper.putList(tag, "nonConnectingBlocks", this.nonConnectingBlocks, p -> class_2503.method_23251(p.method_10063()));
      NBTHelper.putList(tag, "linkedBlocks", this.linkedBlocks, p -> class_2503.method_23251(p.method_10063()));
      NBTHelper.putList(tag, "baseIndexes", this.baseIndexes, class_2497::method_23247);
      tag.method_10569("totalSlots", this.totalSlots);
      return tag;
   }

   public void method_11014(class_2487 tag, class_7874 registries) {
      super.method_11014(tag, registries);
      this.storagePositions = (List<class_2338>)NBTHelper.<class_2338, ArrayList<E>>getCollection(
            tag, "storagePositions", (byte)4, t -> Optional.of(class_2338.method_10092(((class_2503)t).method_10699())), ArrayList::new
         )
         .orElseGet(ArrayList::new);
      this.connectingBlocks = (Set<class_2338>)NBTHelper.<class_2338, LinkedHashSet<E>>getCollection(
            tag, "connectingBlocks", (byte)4, t -> Optional.of(class_2338.method_10092(((class_2503)t).method_10699())), LinkedHashSet::new
         )
         .orElseGet(LinkedHashSet::new);
      this.nonConnectingBlocks = (Set<class_2338>)NBTHelper.<class_2338, LinkedHashSet<E>>getCollection(
            tag, "nonConnectingBlocks", (byte)4, t -> Optional.of(class_2338.method_10092(((class_2503)t).method_10699())), LinkedHashSet::new
         )
         .orElseGet(LinkedHashSet::new);
      this.baseIndexes = (List<Integer>)NBTHelper.<Integer, ArrayList<E>>getCollection(
            tag, "baseIndexes", (byte)3, t -> Optional.of(((class_2497)t).method_10701()), ArrayList::new
         )
         .orElseGet(ArrayList::new);
      this.totalSlots = tag.method_10550("totalSlots");
      this.linkedBlocks = (Set<class_2338>)NBTHelper.<class_2338, LinkedHashSet<E>>getCollection(
            tag, "linkedBlocks", (byte)4, t -> Optional.of(class_2338.method_10092(((class_2503)t).method_10699())), LinkedHashSet::new
         )
         .orElseGet(LinkedHashSet::new);
   }

   public class_2487 method_16887(class_7874 registries) {
      return this.saveData(super.method_16887(registries));
   }

   @Nullable
   public class_2622 getUpdatePacket() {
      return class_2622.method_38585(this);
   }

   public void addStorageWithEmptySlots(class_2338 storageBlockPos) {
      this.emptySlotsStorages.add(storageBlockPos);
   }

   public void removeStorageWithEmptySlots(class_2338 storageBlockPos) {
      this.emptySlotsStorages.remove(storageBlockPos);
   }

   public Set<class_2338> getLinkedBlocks() {
      return this.linkedBlocks;
   }

   public List<class_2338> getStoragePositions() {
      return this.storagePositions;
   }

   public void setStorageFilterItems(class_2338 storagePos, Set<class_1792> filterItems) {
      this.removeStorageFilterItems(storagePos);
      if (!filterItems.isEmpty()) {
         for (class_1792 item : filterItems) {
            this.filterItemStorages.computeIfAbsent(item, stackKey -> new LinkedHashSet<>()).add(storagePos);
         }

         this.storageFilterItems.put(storagePos, new LinkedHashSet<>(filterItems));
      }
   }

   @Nonnull
   public SingleSlotStorage<ItemVariant> getSlot(int slot) {
      if (this.isSlotIndexInvalid(slot)) {
         throw new IndexOutOfBoundsException(slot);
      } else {
         int handlerIndex = this.getIndexForSlot(slot);
         IItemHandlerSimpleInserter handler = this.getHandlerFromIndex(handlerIndex);
         slot = this.getSlotFromIndex(slot, handlerIndex);
         if (!this.validateHandlerSlotIndex(handler, handlerIndex, slot, "getStackInSlot")) {
            throw new IndexOutOfBoundsException("Slot in handler out of range: " + slot);
         } else {
            return handler.getSlot(slot);
         }
      }
   }

   public Iterator<StorageView<ItemVariant>> iterator() {
      return new ControllerBlockEntityBase.CombinedIterator();
   }

   private class CombinedIterator implements Iterator<StorageView<ItemVariant>> {
      final Iterator<class_2338> positionIterator = ControllerBlockEntityBase.this.storagePositions.iterator();
      Iterator<? extends StorageView<ItemVariant>> currentHandlerIterator = null;

      CombinedIterator() {
         this.advanceCurrentPartIterator();
      }

      @Override
      public boolean hasNext() {
         return this.currentHandlerIterator != null && this.currentHandlerIterator.hasNext();
      }

      public StorageView<ItemVariant> next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            StorageView<ItemVariant> returned = (StorageView<ItemVariant>)this.currentHandlerIterator.next();
            if (!this.currentHandlerIterator.hasNext()) {
               this.advanceCurrentPartIterator();
            }

            return returned;
         }
      }

      private void advanceCurrentPartIterator() {
         while (this.positionIterator.hasNext()) {
            this.currentHandlerIterator = ControllerBlockEntityBase.this.getHandlerFromBlockPos(this.positionIterator.next()).iterator();
            if (!this.currentHandlerIterator.hasNext()) {
               continue;
            }
            break;
         }
      }
   }
}
