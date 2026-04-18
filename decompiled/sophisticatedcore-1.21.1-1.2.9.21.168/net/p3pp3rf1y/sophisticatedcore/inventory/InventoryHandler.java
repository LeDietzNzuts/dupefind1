package net.p3pp3rf1y.sophisticatedcore.inventory;

import com.mojang.datafixers.util.Pair;
import io.github.fabricators_of_create.porting_lib.transfer.callbacks.TransactionCallback;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerSlot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2509;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IInsertResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IOverflowResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ISlotLimitUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stack.StackUpgradeConfig;
import net.p3pp3rf1y.sophisticatedcore.util.CodecHelper;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.MathHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;
import org.jetbrains.annotations.NotNull;

public abstract class InventoryHandler extends ItemStackHandler implements ITrackedContentsItemHandler {
   public static final String INVENTORY_TAG = "inventory";
   private static final String PARTITIONER_TAG = "partitioner";
   protected final IStorageWrapper storageWrapper;
   private final class_2487 contentsNbt;
   private final Runnable saveHandler;
   private final List<IntConsumer> onContentsChangedListeners = new ArrayList<>();
   private boolean persistent = true;
   private final Map<Integer, class_2520> stackNbts = new LinkedHashMap<>();
   private ISlotTracker slotTracker = new ISlotTracker.Noop();
   private int baseSlotLimit;
   private int slotLimit;
   private double maxStackSizeMultiplier;
   private boolean isInitializing;
   private final StackUpgradeConfig stackUpgradeConfig;
   private final InventoryPartitioner inventoryPartitioner;
   private Consumer<Set<class_1792>> filterItemsChangeListener = s -> {};
   private final Map<class_1792, Set<Integer>> filterItemSlots = new HashMap<>();
   private BooleanSupplier shouldInsertIntoEmpty = () -> true;
   private boolean slotLimitInitialized = false;
   private List<InventoryHandler.InventoryHandlerSlot> backingList;

   protected InventoryHandler(
      int numberOfInventorySlots,
      IStorageWrapper storageWrapper,
      class_2487 contentsNbt,
      Runnable saveHandler,
      int baseSlotLimit,
      StackUpgradeConfig stackUpgradeConfig
   ) {
      super(numberOfInventorySlots);
      this.stackUpgradeConfig = stackUpgradeConfig;
      this.isInitializing = true;
      this.storageWrapper = storageWrapper;
      this.contentsNbt = contentsNbt;
      this.saveHandler = saveHandler;
      this.setBaseSlotLimit(baseSlotLimit);
      RegistryHelper.getRegistryAccess().ifPresent(registryAccess -> this.deserializeNBT(registryAccess, contentsNbt.method_10562("inventory")));
      this.inventoryPartitioner = new InventoryPartitioner(
         contentsNbt.method_10562("partitioner"), this, () -> storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class)
      );
      this.initStackNbts();
      this.isInitializing = false;
   }

   public ISlotTracker getSlotTracker() {
      this.initSlotTracker();
      return this.slotTracker;
   }

   public void setSize(int size) {
      super.setSize(this.getSlotCount());
   }

   private void initStackNbts() {
      this.stackNbts.clear();

      for (int slot = 0; slot < this.getSlotCount(); slot++) {
         class_1799 slotStack = this.getSlotStack(slot);
         if (!slotStack.method_7960()) {
            this.stackNbts.put(slot, this.getSlotsStackNbt(slot, slotStack));
         }
      }
   }

   public void onContentsChanged(int slot) {
      super.onContentsChanged(slot);
      if (this.persistent && this.updateSlotNbt(slot)) {
         this.saveInventory();
         this.triggerOnChangeListeners(slot);
      }
   }

   public void triggerOnChangeListeners(int slot) {
      for (IntConsumer onContentsChangedListener : this.onContentsChangedListeners) {
         onContentsChangedListener.accept(slot);
      }
   }

   private boolean updateSlotNbt(int slot) {
      class_1799 slotStack = this.getSlotStack(slot);
      if (slotStack.method_7960()) {
         if (this.stackNbts.containsKey(slot)) {
            this.stackNbts.remove(slot);
            return true;
         }
      } else {
         class_2520 itemTag = this.getSlotsStackNbt(slot, slotStack);
         if (!this.stackNbts.containsKey(slot) || !this.stackNbts.get(slot).equals(itemTag)) {
            this.stackNbts.put(slot, itemTag);
            return true;
         }
      }

      return false;
   }

   private class_2520 getSlotsStackNbt(int slot, class_1799 slotStack) {
      class_2487 itemTag = new class_2487();
      itemTag.method_10569("Slot", slot);
      return RegistryHelper.getRegistryAccess()
         .map(
            registryAccess -> (class_2520)CodecHelper.OVERSIZED_ITEM_STACK_CODEC
               .encode(slotStack, registryAccess.method_57093(class_2509.field_11560), itemTag)
               .getOrThrow()
         )
         .orElse(itemTag);
   }

   private Optional<class_1799> getStackFromNbt(class_2520 itemTag, class_7874 lookupProvider) {
      return CodecHelper.OVERSIZED_ITEM_STACK_CODEC
         .parse(lookupProvider.method_57093(class_2509.field_11560), itemTag)
         .resultOrPartial(itemName -> SophisticatedCore.LOGGER.error("Tried to load invalid item: '{}'", itemName));
   }

   public void deserializeNBT(class_7874 registries, class_2487 nbt) {
      this.slotTracker.clear();
      this.setSize(nbt.method_10573("Size", 3) ? nbt.method_10550("Size") : this.getSlotCount());
      class_2499 tagList = nbt.method_10554("Items", 10);
      RegistryHelper.getRegistryAccess().ifPresent(registryAccess -> {
         for (int i = 0; i < tagList.size(); i++) {
            class_2487 itemTag = tagList.method_10602(i);
            int slot = itemTag.method_10550("Slot");
            if (slot >= 0 && slot < this.getSlotCount()) {
               this.getSlot(slot).load(registryAccess, itemTag);
            }
         }
      });
      this.slotTracker.refreshSlotIndexesFrom(this);
      this.onLoad();
   }

   public int getBaseSlotLimit() {
      return this.baseSlotLimit;
   }

   @Override
   public int getInternalSlotLimit(int slot) {
      return this.inventoryPartitioner.getPartBySlot(slot).getSlotLimit(slot);
   }

   public int getSlotLimit(int slot) {
      if (!this.slotLimitInitialized) {
         this.slotLimitInitialized = true;
         this.updateSlotLimit();
         this.inventoryPartitioner.onSlotLimitChange();
      }

      return this.slotLimit > this.baseSlotLimit ? this.slotLimit : this.inventoryPartitioner.getPartBySlot(slot).getSlotLimit(slot);
   }

   public int getBaseStackLimit(class_1799 stack) {
      if (!this.stackUpgradeConfig.canStackItem(stack.method_7909())) {
         return stack.method_7914();
      } else {
         int maxStackSize = stack.method_7960() ? this.getBaseSlotLimit() : stack.method_7914();
         if (this.baseSlotLimit < 64) {
            return (int)Math.max(1.0, (double)maxStackSize * this.baseSlotLimit / 64.0);
         } else {
            int limit = MathHelper.intMaxCappedMultiply(maxStackSize, this.baseSlotLimit / 64);
            int remainder = this.baseSlotLimit % 64;
            if (remainder > 0) {
               limit = MathHelper.intMaxCappedAddition(limit, remainder * maxStackSize / 64);
            }

            return limit;
         }
      }
   }

   protected int getStackLimit(int slot, ItemVariant resource) {
      return this.getStackLimit(slot, resource.toStack());
   }

   public int getStackLimit(int slot, class_1799 stack) {
      return this.inventoryPartitioner.getPartBySlot(slot).getStackLimit(slot, stack);
   }

   public class_1792 getFilterItem(int slot) {
      return this.inventoryPartitioner.getPartBySlot(slot).getFilterItem(slot);
   }

   public boolean isFilterItem(class_1792 item) {
      return this.inventoryPartitioner.isFilterItem(item);
   }

   public void setBaseSlotLimit(int baseSlotLimit) {
      this.slotLimitInitialized = false;
      this.baseSlotLimit = baseSlotLimit;
      this.maxStackSizeMultiplier = baseSlotLimit / 64.0F;
      if (this.inventoryPartitioner != null) {
         this.inventoryPartitioner.onSlotLimitChange();
      }

      if (!this.isInitializing) {
         this.slotTracker.refreshSlotIndexesFrom(this);
      }
   }

   private void updateSlotLimit() {
      AtomicInteger slotLimitOverride = new AtomicInteger(this.baseSlotLimit);
      this.storageWrapper.getUpgradeHandler().getWrappersThatImplement(ISlotLimitUpgrade.class).forEach(slu -> {
         if (slu.getSlotLimit() > slotLimitOverride.get()) {
            slotLimitOverride.set(slu.getSlotLimit());
         }
      });
      this.slotLimit = slotLimitOverride.get();
   }

   public class_1799 extractItemInternal(int slot, int amount, boolean simulate) {
      if (amount == 0) {
         return class_1799.field_8037;
      } else {
         class_1799 existing = this.getSlotStack(slot);
         if (existing.method_7960()) {
            return class_1799.field_8037;
         } else {
            int toExtract = Math.min(amount, existing.method_7914());
            if (existing.method_7947() <= toExtract) {
               if (!simulate) {
                  this.setSlotStack(slot, class_1799.field_8037);
                  return existing;
               } else {
                  return existing.method_7972();
               }
            } else {
               if (!simulate) {
                  this.setSlotStack(slot, existing.method_46651(existing.method_7947() - toExtract));
               }

               return existing.method_46651(toExtract);
            }
         }
      }
   }

   @Nonnull
   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      return this.inventoryPartitioner.getPartBySlot(slot).extractItem(slot, amount, simulate);
   }

   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      TransactionCallback.onSuccess(ctx, () -> this.inventoryPartitioner.getPartBySlot(slot).extractItem(slot, (int)maxAmount, false));
      return this.inventoryPartitioner.getPartBySlot(slot).extractItem(slot, (int)maxAmount, true).method_7947();
   }

   public class_1799 getSlotStack(int slot) {
      return ((InventoryHandler.InventoryHandlerSlot)this.getSlot(slot)).getInternalStack();
   }

   public void setSlotStack(int slot, class_1799 stack) {
      ((InventoryHandler.InventoryHandlerSlot)this.getSlot(slot)).setInternalNewStack(stack);
      this.slotTracker.removeAndSetSlotIndexes(this, slot, stack);
      this.onContentsChanged(slot);
   }

   @Override
   public class_1799 insertItem(int slot, @NotNull class_1799 stack, boolean simulate) {
      this.initSlotTracker();
      return this.slotTracker.insertItemIntoHandler(this, this::insertItemInternal, this::triggerOverflowUpgrades, slot, stack, simulate);
   }

   public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      this.initSlotTracker();
      TransactionCallback.onSuccess(
         ctx,
         () -> this.slotTracker
            .insertItemIntoHandler(this, this::insertItemInternal, this::triggerOverflowUpgrades, slot, resource.toStack((int)maxAmount), false)
      );
      return maxAmount
         - this.slotTracker
            .insertItemIntoHandler(this, this::insertItemInternal, this::triggerOverflowUpgrades, slot, resource.toStack((int)maxAmount), true)
            .method_7947();
   }

   @Nonnull
   public class_1799 insertItemOnlyToSlot(int slot, class_1799 stack, boolean simulate) {
      this.initSlotTracker();
      return class_1799.method_31577(this.getStackInSlot(slot), stack)
         ? this.triggerOverflowUpgrades(this.insertItemInternal(slot, stack, simulate))
         : this.insertItemInternal(slot, stack, simulate);
   }

   private void initSlotTracker() {
      if (!(this.slotTracker instanceof InventoryHandlerSlotTracker)) {
         this.slotTracker = new InventoryHandlerSlotTracker(
            this.storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class), this.filterItemSlots
         );
         this.slotTracker.refreshSlotIndexesFrom(this);
         this.slotTracker.setShouldInsertIntoEmpty(this.shouldInsertIntoEmpty);
      }
   }

   private class_1799 superInsertItem(int slot, class_1799 stack, boolean simulate) {
      if (stack.method_7960()) {
         return class_1799.field_8037;
      } else if (!this.isItemValid(slot, stack)) {
         return stack;
      } else if (slot >= 0 && slot < this.getSlotCount()) {
         class_1799 existing = this.getSlotStack(slot);
         int limit = this.getStackLimit(slot, stack);
         if (!existing.method_7960()) {
            if (!class_1799.method_31577(stack, existing)) {
               return stack;
            }

            limit -= existing.method_7947();
         }

         if (limit <= 0) {
            return stack;
         } else {
            boolean reachedLimit = stack.method_7947() > limit;
            if (!simulate) {
               class_1799 result;
               if (existing.method_7960()) {
                  result = reachedLimit ? stack.method_46651(limit) : stack;
               } else {
                  existing.method_7933(reachedLimit ? limit : stack.method_7947());
                  result = existing;
               }

               this.getSlot(slot).setNewStack(result);
            }

            return reachedLimit ? stack.method_46651(stack.method_7947() - limit) : class_1799.field_8037;
         }
      } else {
         throw new RuntimeException("Slot " + slot + " not in valid range - [0," + this.getSlotCount() + ")");
      }
   }

   private class_1799 insertItemInternal(int slot, class_1799 stack, boolean simulate) {
      class_1799 ret = this.runOnBeforeInsert(slot, stack, simulate, this, this.storageWrapper);
      if (ret.method_7960()) {
         return ret;
      } else {
         ret = this.inventoryPartitioner.getPartBySlot(slot).insertItem(slot, ret, simulate, this::superInsertItem);
         if (!simulate) {
            this.slotTracker.removeAndSetSlotIndexes(this, slot, this.getStackInSlot(slot));
         }

         if (ret == stack) {
            return ret;
         } else {
            this.runOnAfterInsert(slot, simulate, this, this.storageWrapper);
            return ret;
         }
      }
   }

   private class_1799 triggerOverflowUpgrades(class_1799 ret) {
      for (IOverflowResponseUpgrade overflowUpgrade : this.storageWrapper.getUpgradeHandler().getWrappersThatImplement(IOverflowResponseUpgrade.class)) {
         ret = overflowUpgrade.onOverflow(ret);
         if (ret.method_7960()) {
            break;
         }
      }

      return ret;
   }

   private void runOnAfterInsert(int slot, boolean simulate, IItemHandlerSimpleInserter handler, IStorageWrapper storageWrapper) {
      if (!simulate) {
         storageWrapper.getUpgradeHandler().getWrappersThatImplementFromMainStorage(IInsertResponseUpgrade.class).forEach(u -> u.onAfterInsert(handler, slot));
      }
   }

   private class_1799 runOnBeforeInsert(int slot, class_1799 stack, boolean simulate, IItemHandlerSimpleInserter handler, IStorageWrapper storageWrapper) {
      List<IInsertResponseUpgrade> wrappers = storageWrapper.getUpgradeHandler().getWrappersThatImplementFromMainStorage(IInsertResponseUpgrade.class);
      class_1799 remaining = stack;

      for (IInsertResponseUpgrade upgrade : wrappers) {
         remaining = upgrade.onBeforeInsert(handler, slot, remaining, simulate);
         if (remaining.method_7960()) {
            return class_1799.field_8037;
         }
      }

      return remaining;
   }

   public void setStackInSlot(int slot, @Nonnull class_1799 stack) {
      this.inventoryPartitioner.getPartBySlot(slot).setStackInSlot(slot, stack, (x$0, x$1) -> super.setStackInSlot(x$0, x$1));
      this.slotTracker.removeAndSetSlotIndexes(this, slot, stack);
   }

   public void setPersistent(boolean persistent) {
      this.persistent = persistent;
   }

   @Override
   public boolean isItemValid(int slot, class_1799 stack) {
      return this.isItemValid(slot, stack, null);
   }

   public boolean isItemValid(int slot, class_1799 stack, @Nullable class_1657 player) {
      return this.isItemValid(slot, ItemVariant.of(stack), stack.method_7947(), player);
   }

   @Override
   public boolean isItemValid(int slot, ItemVariant resource, int count) {
      return this.isItemValid(slot, resource, count, null);
   }

   public boolean isItemValid(int slot, ItemVariant resource, int count, @Nullable class_1657 player) {
      class_1799 stack = resource.toStack(count);
      return this.inventoryPartitioner.getPartBySlot(slot).isItemValid(slot, resource, count, player, (x$0, x$1, x$2) -> super.isItemValid(x$0, x$1, x$2))
         && this.isAllowed(stack)
         && this.storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class).matchesFilter(slot, stack);
   }

   public ItemVariant getVariantInSlot(int slot) {
      return ItemVariant.of(this.getStackInSlot(slot));
   }

   @Nonnull
   public class_1799 getStackInSlot(int slot) {
      return this.inventoryPartitioner.getPartBySlot(slot).getStackInSlot(slot, x$0 -> super.getStackInSlot(x$0));
   }

   protected final boolean isAllowed(ItemVariant resource) {
      return this.isAllowed(resource.toStack());
   }

   protected abstract boolean isAllowed(class_1799 var1);

   public void saveInventory() {
      RegistryHelper.getRegistryAccess().ifPresent(registryAccess -> this.contentsNbt.method_10566("inventory", this.serializeNBT(registryAccess)));
      if (this.inventoryPartitioner != null) {
         this.contentsNbt.method_10566("partitioner", this.inventoryPartitioner.serializeNBT());
      }

      this.saveHandler.run();
   }

   @Nullable
   public Pair<class_2960, class_2960> getNoItemIcon(int slotIndex) {
      return this.inventoryPartitioner.getNoItemIcon(slotIndex);
   }

   public void copyStacksTo(InventoryHandler otherHandler) {
      InventoryHelper.copyTo(this, otherHandler);
   }

   public void addListener(IntConsumer onContentsChanged) {
      this.onContentsChangedListeners.add(onContentsChanged);
   }

   public void clearListeners() {
      this.onContentsChangedListeners.clear();
   }

   public class_2487 serializeNBT(class_7874 registries) {
      class_2499 nbtTagList = new class_2499();
      nbtTagList.addAll(this.stackNbts.values());
      class_2487 nbt = new class_2487();
      nbt.method_10566("Items", nbtTagList);
      nbt.method_10569("Size", this.getSlotCount());
      return nbt;
   }

   public double getStackSizeMultiplier() {
      return this.maxStackSizeMultiplier;
   }

   @NotNull
   @Override
   public class_1799 insertItem(class_1799 stack, boolean simulate) {
      this.initSlotTracker();
      return this.slotTracker.insertItemIntoHandler(this, this::insertItemInternal, this::triggerOverflowUpgrades, stack, simulate);
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      this.initSlotTracker();
      TransactionCallback.onSuccess(
         ctx,
         () -> this.slotTracker.insertItemIntoHandler(this, this::insertItemInternal, this::triggerOverflowUpgrades, resource.toStack((int)maxAmount), false)
      );
      return maxAmount
         - this.slotTracker
            .insertItemIntoHandler(this, this::insertItemInternal, this::triggerOverflowUpgrades, resource.toStack((int)maxAmount), true)
            .method_7947();
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext ctx) {
      long remaining = maxAmount;

      for (int i = 0; i < this.getSlotCount() && remaining > 0L; i++) {
         if (this.getVariantInSlot(i).equals(resource)) {
            remaining -= this.extractSlot(i, resource, remaining, ctx);
         }
      }

      return maxAmount - remaining;
   }

   public void changeSlots(int diff) {
      class_2371<class_1799> previousStacks = class_2371.method_10212(
         class_1799.field_8037, IntStream.range(0, this.getSlotCount()).mapToObj(this::getStackInSlot).toArray(class_1799[]::new)
      );
      super.setSize(previousStacks.size() + diff);

      for (int slot = 0; slot < previousStacks.size() && slot < this.getSlotCount(); slot++) {
         ((InventoryHandler.InventoryHandlerSlot)this.getSlot(slot)).setInternalNewStack((class_1799)previousStacks.get(slot));
      }

      this.initStackNbts();
      this.saveInventory();
      this.slotTracker.refreshSlotIndexesFrom(this);
   }

   @Override
   public Set<ItemStackKey> getTrackedStacks() {
      this.initSlotTracker();
      HashSet<ItemStackKey> ret = new HashSet<>(this.slotTracker.getFullStacks());
      ret.addAll(this.slotTracker.getPartialStacks());
      return ret;
   }

   @Override
   public void registerTrackingListeners(
      Consumer<ItemStackKey> onAddStackKey, Consumer<ItemStackKey> onRemoveStackKey, Runnable onAddFirstEmptySlot, Runnable onRemoveLastEmptySlot
   ) {
      this.initSlotTracker();
      this.slotTracker.registerListeners(onAddStackKey, onRemoveStackKey, onAddFirstEmptySlot, onRemoveLastEmptySlot);
   }

   @Override
   public void unregisterStackKeyListeners() {
      this.slotTracker.unregisterStackKeyListeners();
   }

   @Override
   public boolean hasEmptySlots() {
      return this.slotTracker.hasEmptySlots();
   }

   public InventoryPartitioner getInventoryPartitioner() {
      return this.inventoryPartitioner;
   }

   public boolean isSlotAccessible(int slot) {
      return this.inventoryPartitioner.getPartBySlot(slot).isSlotAccessible(slot);
   }

   public Set<Integer> getNoSortSlots() {
      return this.inventoryPartitioner.getNoSortSlots();
   }

   public void onSlotFilterChanged(int slot) {
      this.inventoryPartitioner.getPartBySlot(slot).onSlotFilterChanged(slot);
   }

   public void registerFilterItemsChangeListener(Consumer<Set<class_1792>> listener) {
      this.filterItemsChangeListener = listener;
   }

   public void unregisterFilterItemsChangeListener() {
      this.filterItemsChangeListener = s -> {};
   }

   public void initFilterItems() {
      this.filterItemSlots.putAll(this.inventoryPartitioner.getFilterItems());
   }

   public void onFilterItemsChanged() {
      if (this.inventoryPartitioner != null) {
         this.filterItemSlots.clear();
         this.filterItemSlots.putAll(this.inventoryPartitioner.getFilterItems());
         this.filterItemsChangeListener.accept(this.filterItemSlots.keySet());
      }
   }

   public Set<class_1792> getFilterItems() {
      return this.filterItemSlots.keySet();
   }

   public void onInit() {
      if (this.inventoryPartitioner != null) {
         this.inventoryPartitioner.onInit();
         this.slotTracker = new ISlotTracker.Noop();
      }
   }

   public void setShouldInsertIntoEmpty(BooleanSupplier shouldInsertIntoEmpty) {
      this.shouldInsertIntoEmpty = shouldInsertIntoEmpty;
      this.slotTracker.setShouldInsertIntoEmpty(shouldInsertIntoEmpty);
   }

   public boolean isInfinite(int slot) {
      return this.inventoryPartitioner.isInfinite(slot);
   }

   protected ItemStackHandlerSlot makeSlot(int index, class_1799 stack) {
      if (this.backingList == null) {
         this.backingList = new ArrayList<>();
      }

      while (this.backingList.size() <= index) {
         this.backingList.add(new InventoryHandler.InventoryHandlerSlot(index, this, class_1799.field_8037));
      }

      InventoryHandler.InventoryHandlerSlot slot = this.backingList.get(index);
      slot.setInternalNewStack(stack);
      return slot;
   }

   private class InventoryHandlerSlot extends ItemStackHandlerSlot {
      public InventoryHandlerSlot(int index, InventoryHandler handler, class_1799 initial) {
         super(index, handler, initial);
      }

      protected class_1799 getInternalStack() {
         return super.getStack().method_7972();
      }

      protected void setInternalNewStack(class_1799 stack) {
         super.setStack(stack);
         this.onStackChange();
      }

      public long insert(ItemVariant variant, long maxAmount, TransactionContext ctx) {
         return !variant.isBlank() && maxAmount >= 0L ? InventoryHandler.this.insertSlot(this.getIndex(), variant, maxAmount, ctx) : 0L;
      }

      public long extract(ItemVariant variant, long maxAmount, TransactionContext ctx) {
         return !variant.isBlank() && maxAmount >= 0L ? InventoryHandler.this.extractSlot(this.getIndex(), variant, maxAmount, ctx) : 0L;
      }

      public void load(class_7874 provider, class_2487 tag) {
         InventoryHandler.this.getStackFromNbt(tag, provider).ifPresent(x$0 -> this.setStack(x$0));
         this.onStackChange();
      }
   }
}
