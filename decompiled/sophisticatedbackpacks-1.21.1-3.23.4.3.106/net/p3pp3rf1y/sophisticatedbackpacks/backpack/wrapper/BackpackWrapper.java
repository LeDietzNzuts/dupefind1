package net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.IntConsumer;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3611;
import net.minecraft.class_6862;
import net.minecraft.class_9288;
import net.minecraft.class_9334;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IEnergyStorageUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IFluidHandlerWrapperUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModDataComponents;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageFluidHandler;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SortBy;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryIOHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.inventory.StorageWrapperRepository;
import net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay.ItemDisplaySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.nosort.NoSortSettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stack.StackUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.tank.TankUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.InventorySorter;
import net.p3pp3rf1y.sophisticatedcore.util.LootHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RandHelper;
import team.reborn.energy.api.EnergyStorage;

public class BackpackWrapper implements IBackpackWrapper {
   public static final int DEFAULT_MAIN_COLOR = -3382982;
   public static final int DEFAULT_ACCENT_COLOR = -10342886;
   @Nullable
   private class_1799 backpack;
   private Runnable backpackSaveHandler = () -> {};
   private Runnable inventorySlotChangeHandler = () -> {};
   @Nullable
   private InventoryHandler handler = null;
   @Nullable
   private UpgradeHandler upgradeHandler = null;
   @Nullable
   private InventoryIOHandler inventoryIOHandler = null;
   @Nullable
   private InventoryModificationHandler inventoryModificationHandler = null;
   @Nullable
   private BackpackSettingsHandler settingsHandler = null;
   private boolean fluidHandlerInitialized = false;
   @Nullable
   private IStorageFluidHandler fluidHandler = null;
   private boolean energyStorageInitialized = false;
   @Nullable
   private EnergyStorage energyStorage = null;
   @Nullable
   private BackpackRenderInfo renderInfo;
   private IntConsumer onSlotsChange = diff -> {};
   private Runnable onInventoryHandlerRefresh = () -> {};
   private Runnable upgradeCachesInvalidatedHandler = () -> {};

   public BackpackWrapper(class_1799 backpackStack) {
      this.setBackpackStack(backpackStack);
   }

   public static IBackpackWrapper fromStack(class_1799 stack) {
      return (IBackpackWrapper)StorageWrapperRepository.getStorageWrapper(stack, IBackpackWrapper.class, BackpackWrapper::new);
   }

   public static Optional<IBackpackWrapper> fromExistingData(class_1799 stack) {
      return stack.method_7909() instanceof BackpackItem ? StorageWrapperRepository.getExistingStorageWrapper(stack, IBackpackWrapper.class) : Optional.empty();
   }

   public void setContentsChangeHandler(Runnable contentsChangeHandler) {
      this.backpackSaveHandler = contentsChangeHandler;
      this.refreshInventoryForUpgradeProcessing();
   }

   public void setInventorySlotChangeHandler(Runnable slotChangeHandler) {
      this.inventorySlotChangeHandler = slotChangeHandler;
   }

   public ITrackedContentsItemHandler getInventoryForUpgradeProcessing() {
      if (this.inventoryModificationHandler == null) {
         this.inventoryModificationHandler = new InventoryModificationHandler(this);
      }

      return this.inventoryModificationHandler.getModifiedInventoryHandler();
   }

   public InventoryHandler getInventoryHandler() {
      if (this.handler == null) {
         this.handler = new BackpackInventoryHandler(
            this.getNumberOfInventorySlots() - this.getNumberOfSlotRows() * this.getColumnsTaken(), this, this.getBackpackContentsNbt(), () -> {
               this.markBackpackContentsDirty();
               this.inventorySlotChangeHandler.run();
            }, StackUpgradeItem.getInventorySlotLimit(this)
         );
         this.handler.addListener(((ItemDisplaySettingsCategory)this.getSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class))::itemChanged);
      }

      return this.handler;
   }

   private int getNumberOfInventorySlots() {
      Integer inventorySlots = (Integer)this.getBackpackStack().sophisticatedCore_get(ModCoreDataComponents.NUMBER_OF_INVENTORY_SLOTS);
      if (inventorySlots != null) {
         return inventorySlots;
      } else {
         int itemInventorySlots = ((BackpackItem)this.getBackpackStack().method_7909()).getNumberOfSlots();
         this.setNumberOfInventorySlots(itemInventorySlots);
         return itemInventorySlots;
      }
   }

   public int getNumberOfSlotRows() {
      int itemInventorySlots = this.getNumberOfInventorySlots();
      return (int)Math.ceil(itemInventorySlots <= 81 ? itemInventorySlots / 9.0 : itemInventorySlots / 12.0);
   }

   private void setNumberOfInventorySlots(int itemInventorySlots) {
      this.getBackpackStack().sophisticatedCore_set(ModCoreDataComponents.NUMBER_OF_INVENTORY_SLOTS, itemInventorySlots);
   }

   private class_2487 getBackpackContentsNbt() {
      return BackpackStorage.get().getOrCreateBackpackContents(this.getOrCreateContentsUuid());
   }

   private void markBackpackContentsDirty() {
      BackpackStorage.get().method_80();
   }

   public ITrackedContentsItemHandler getInventoryForInputOutput() {
      if (this.inventoryIOHandler == null) {
         this.inventoryIOHandler = new InventoryIOHandler(this);
      }

      return this.inventoryIOHandler.getFilteredItemHandler();
   }

   public Optional<IStorageFluidHandler> getFluidHandler() {
      if (!this.fluidHandlerInitialized) {
         IStorageFluidHandler wrappedHandler = this.getUpgradeHandler().getTypeWrappers(TankUpgradeItem.TYPE).isEmpty() ? null : new BackpackFluidHandler(this);

         for (IFluidHandlerWrapperUpgrade fluidHandlerWrapperUpgrade : this.getUpgradeHandler().getWrappersThatImplement(IFluidHandlerWrapperUpgrade.class)) {
            wrappedHandler = fluidHandlerWrapperUpgrade.wrapHandler(wrappedHandler, this.getBackpackStack());
         }

         this.fluidHandler = wrappedHandler;
      }

      return Optional.ofNullable(this.fluidHandler);
   }

   @Override
   public Optional<IStorageFluidHandler> getItemFluidHandler() {
      return this.getFluidHandler().map(fh -> new BackpackWrapper.FluidHandlerItemWrapper(this.getBackpackStack(), fh));
   }

   public Optional<EnergyStorage> getEnergyStorage() {
      if (!this.energyStorageInitialized) {
         EnergyStorage wrappedStorage = (EnergyStorage)this.getUpgradeHandler().getWrappersThatImplement(EnergyStorage.class).stream().findFirst().orElse(null);

         for (IEnergyStorageUpgradeWrapper energyStorageWrapperUpgrade : this.getUpgradeHandler().getWrappersThatImplement(IEnergyStorageUpgradeWrapper.class)) {
            wrappedStorage = energyStorageWrapperUpgrade.wrapStorage(wrappedStorage);
         }

         this.energyStorage = wrappedStorage;
      }

      return this.energyStorage != null && this.energyStorage.getCapacity() != 0L ? Optional.of(this.energyStorage) : Optional.empty();
   }

   @Override
   public void copyDataTo(IStorageWrapper otherStorageWrapper) {
      this.getContentsUuid().ifPresent(originalUuid -> {
         this.getInventoryHandler().copyStacksTo(otherStorageWrapper.getInventoryHandler());
         this.getUpgradeHandler().copyTo(otherStorageWrapper.getUpgradeHandler());
         this.getSettingsHandler().copyTo(otherStorageWrapper.getSettingsHandler());
      });
   }

   @Override
   public IBackpackWrapper setBackpackStack(class_1799 backpack) {
      this.backpack = backpack;
      if (this.renderInfo == null) {
         this.renderInfo = new BackpackRenderInfo(backpack, () -> this.backpackSaveHandler);
      }

      return this;
   }

   @Override
   public BackpackSettingsHandler getSettingsHandler() {
      if (this.settingsHandler == null) {
         if (this.getContentsUuid().isPresent()) {
            this.settingsHandler = new BackpackSettingsHandler(this, this.getBackpackContentsNbt(), this::markBackpackContentsDirty);
         } else {
            this.settingsHandler = IBackpackWrapper.Noop.INSTANCE.getSettingsHandler();
         }
      }

      return this.settingsHandler;
   }

   public UpgradeHandler getUpgradeHandler() {
      if (this.upgradeHandler == null) {
         if (this.getContentsUuid().isPresent()) {
            this.upgradeHandler = new UpgradeHandler(
               this.getNumberOfUpgradeSlots(),
               this,
               this.getBackpackContentsNbt(),
               this::markBackpackContentsDirty,
               () -> {
                  if (this.handler != null) {
                     this.handler.clearListeners();
                     this.handler.setBaseSlotLimit(StackUpgradeItem.getInventorySlotLimit(this));
                  }

                  this.getInventoryHandler().clearListeners();
                  this.handler
                     .addListener(((ItemDisplaySettingsCategory)this.getSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class))::itemChanged);
                  this.inventoryIOHandler = null;
                  this.inventoryModificationHandler = null;
                  this.fluidHandlerInitialized = false;
                  this.fluidHandler = null;
                  this.energyStorageInitialized = false;
                  this.energyStorage = null;
                  this.upgradeCachesInvalidatedHandler.run();
               }
            ) {
               public boolean isItemValid(int slot, class_1799 stack) {
                  return super.isItemValid(slot, stack) && (stack.method_7960() || stack.method_31573(ModItems.BACKPACK_UPGRADE_TAG));
               }
            };
         } else {
            this.upgradeHandler = IBackpackWrapper.Noop.INSTANCE.getUpgradeHandler();
         }
      }

      return this.upgradeHandler;
   }

   public void setUpgradeCachesInvalidatedHandler(Runnable handler) {
      this.upgradeCachesInvalidatedHandler = handler;
   }

   private int getNumberOfUpgradeSlots() {
      Integer upgradeSlots = (Integer)this.getBackpackStack().sophisticatedCore_get(ModCoreDataComponents.NUMBER_OF_UPGRADE_SLOTS);
      if (upgradeSlots != null) {
         return upgradeSlots;
      } else {
         int itemUpgradeSlots = ((BackpackItem)this.getBackpackStack().method_7909()).getNumberOfUpgradeSlots();
         this.setNumberOfUpgradeSlots(itemUpgradeSlots);
         return itemUpgradeSlots;
      }
   }

   public Optional<UUID> getContentsUuid() {
      return Optional.ofNullable((UUID)this.getBackpackStack().sophisticatedCore_get(ModCoreDataComponents.STORAGE_UUID));
   }

   private UUID getOrCreateContentsUuid() {
      Optional<UUID> contentsUuid = this.getContentsUuid();
      if (contentsUuid.isPresent()) {
         return contentsUuid.get();
      } else {
         this.clearDummyHandlers();
         UUID newUuid = UUID.randomUUID();
         this.setContentsUuid(newUuid);
         return newUuid;
      }
   }

   private void clearDummyHandlers() {
      if (this.upgradeHandler == IBackpackWrapper.Noop.INSTANCE.getUpgradeHandler()) {
         this.upgradeHandler = null;
      }

      if (this.settingsHandler == IBackpackWrapper.Noop.INSTANCE.getSettingsHandler()) {
         this.settingsHandler = null;
      }
   }

   public int getMainColor() {
      return (Integer)this.getBackpackStack().sophisticatedCore_getOrDefault(ModCoreDataComponents.MAIN_COLOR, -3382982);
   }

   public int getAccentColor() {
      return (Integer)this.getBackpackStack().sophisticatedCore_getOrDefault(ModCoreDataComponents.ACCENT_COLOR, -10342886);
   }

   public Optional<Integer> getOpenTabId() {
      return Optional.ofNullable((Integer)this.getBackpackStack().sophisticatedCore_get(ModCoreDataComponents.OPEN_TAB_ID));
   }

   public void setOpenTabId(int openTabId) {
      this.getBackpackStack().sophisticatedCore_set(ModCoreDataComponents.OPEN_TAB_ID, openTabId);
      this.backpackSaveHandler.run();
   }

   public void removeOpenTabId() {
      this.getBackpackStack().sophisticatedCore_remove(ModCoreDataComponents.OPEN_TAB_ID);
      this.backpackSaveHandler.run();
   }

   public void setColors(int mainColor, int accentColor) {
      class_1799 backpackStack = this.getBackpackStack();
      BackpackItem.setColors(backpackStack, mainColor, accentColor);
      this.backpackSaveHandler.run();
   }

   public void setSortBy(SortBy sortBy) {
      this.getBackpackStack().sophisticatedCore_set(ModCoreDataComponents.SORT_BY, sortBy);
      this.backpackSaveHandler.run();
   }

   public SortBy getSortBy() {
      return (SortBy)this.getBackpackStack().sophisticatedCore_getOrDefault(ModCoreDataComponents.SORT_BY, SortBy.NAME);
   }

   public void sort() {
      Set<Integer> slotIndexesExcludedFromSort = new HashSet<>();
      slotIndexesExcludedFromSort.addAll(((NoSortSettingsCategory)this.getSettingsHandler().getTypeCategory(NoSortSettingsCategory.class)).getNoSortSlots());
      slotIndexesExcludedFromSort.addAll(((MemorySettingsCategory)this.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class)).getSlotIndexes());
      InventorySorter.sortHandler(this.getInventoryHandler(), this.getComparator(), slotIndexesExcludedFromSort);
   }

   private Comparator<Entry<ItemStackKey, Integer>> getComparator() {
      return switch (this.getSortBy()) {
         case COUNT -> InventorySorter.BY_COUNT;
         case TAGS -> InventorySorter.BY_TAGS;
         case NAME -> InventorySorter.BY_NAME;
         case MOD -> InventorySorter.BY_MOD;
         default -> throw new MatchException(null, null);
      };
   }

   @Override
   public class_1799 getBackpack() {
      return this.getBackpackStack();
   }

   @Override
   public class_1799 cloneBackpack() {
      class_1799 clonedBackpack = this.cloneBackpack(this);
      this.cloneSubbackpacks(fromStack(clonedBackpack));
      return clonedBackpack;
   }

   private void cloneSubbackpacks(IStorageWrapper wrapperCloned) {
      InventoryHandler inventoryHandler = wrapperCloned.getInventoryHandler();
      InventoryHelper.iterate(inventoryHandler, (slot, stack) -> {
         if (stack.method_7909() instanceof BackpackItem) {
            inventoryHandler.setStackInSlot(slot, this.cloneBackpack(fromStack(stack)));
         }
      });
   }

   private class_1799 cloneBackpack(IBackpackWrapper originalWrapper) {
      class_1799 backpackCopy = originalWrapper.getBackpack().method_7972();
      backpackCopy.sophisticatedCore_remove(ModCoreDataComponents.STORAGE_UUID);
      IBackpackWrapper wrapperCopy = fromStack(backpackCopy);
      originalWrapper.copyDataTo(wrapperCopy);
      return wrapperCopy.getBackpack();
   }

   public void refreshInventoryForInputOutput() {
      this.inventoryIOHandler = null;
      this.upgradeCachesInvalidatedHandler.run();
   }

   public void setPersistent(boolean persistent) {
      this.getInventoryHandler().setPersistent(persistent);
      this.getUpgradeHandler().setPersistent(persistent);
   }

   @Override
   public void setSlotNumbers(int numberOfInventorySlots, int numberOfUpgradeSlots) {
      this.setNumberOfInventorySlots(numberOfInventorySlots);
      this.setNumberOfUpgradeSlots(numberOfUpgradeSlots);
   }

   @Override
   public void setLoot(class_2960 lootTableName, float lootFactor) {
      this.getBackpackStack().sophisticatedCore_set(ModDataComponents.LOOT_TABLE, lootTableName);
      this.getBackpackStack().sophisticatedCore_set(ModDataComponents.LOOT_FACTOR, lootFactor);
      this.backpackSaveHandler.run();
   }

   public void fillWithLoot(class_1657 player) {
      class_1937 level = player.method_37908();
      if (!level.field_9236) {
         class_2338 pos = player.method_24515();
         this.fillWithLoot(level, pos);
         this.fillWithExtraItems(player);
      }
   }

   private void fillWithExtraItems(class_1657 player) {
      class_1799 backpack = this.getBackpackStack();
      if (backpack.method_57826(class_9334.field_49622)) {
         class_9288 containerItems = (class_9288)backpack.method_57825(class_9334.field_49622, class_9288.field_49334);

         for (int slot = 0; slot < containerItems.sophisticatedCore_getSlots(); slot++) {
            class_1799 stack = containerItems.sophisticatedCore_getStackInSlot(slot);
            if (!stack.method_7960()) {
               InventoryHelper.insertOrDropItem(player, stack, new Storage[]{this.getInventoryHandler()});
            }
         }

         backpack.method_57381(class_9334.field_49622);
      }
   }

   private void fillWithLoot(class_1937 level, class_2338 pos) {
      class_2960 lootTable = (class_2960)this.getBackpackStack().sophisticatedCore_get(ModDataComponents.LOOT_TABLE);
      if (lootTable != null) {
         this.fillWithLootFromTable(level, pos, lootTable);
      }
   }

   @Override
   public void setContentsUuid(UUID storageUuid) {
      this.getBackpackStack().sophisticatedCore_set(ModCoreDataComponents.STORAGE_UUID, storageUuid);
   }

   @Override
   public void removeContentsUuid() {
      this.getContentsUuid().ifPresent(BackpackStorage.get()::removeBackpackContents);
      this.removeContentsUUIDTag();
   }

   @Override
   public void removeContentsUUIDTag() {
      this.getBackpackStack().sophisticatedCore_remove(ModCoreDataComponents.STORAGE_UUID);
   }

   private class_1799 getBackpackStack() {
      if (this.backpack == null) {
         throw new IllegalStateException("Backpack stack not set");
      } else {
         return this.backpack;
      }
   }

   public BackpackRenderInfo getRenderInfo() {
      return this.renderInfo;
   }

   public void setColumnsTaken(int columnsTaken, boolean hasChanged) {
      int originalColumnsTaken = this.getColumnsTaken();
      this.getBackpackStack().sophisticatedCore_set(ModDataComponents.COLUMNS_TAKEN, columnsTaken);
      if (hasChanged) {
         int diff = (columnsTaken - originalColumnsTaken) * this.getNumberOfSlotRows();
         this.onSlotsChange.accept(diff);
      }

      this.backpackSaveHandler.run();
   }

   @Override
   public void registerOnSlotsChangeListener(IntConsumer onSlotsChange) {
      this.onSlotsChange = onSlotsChange;
   }

   @Override
   public void unregisterOnSlotsChangeListener() {
      this.onSlotsChange = diff -> {};
   }

   public int getColumnsTaken() {
      return (Integer)this.getBackpackStack().sophisticatedCore_getOrDefault(ModDataComponents.COLUMNS_TAKEN, 0);
   }

   private void fillWithLootFromTable(class_1937 level, class_2338 pos, class_2960 lootTable) {
      MinecraftServer server = level.method_8503();
      if (server != null && level instanceof class_3218 serverLevel) {
         float lootFactor = (Float)this.getBackpackStack().sophisticatedCore_getOrDefault(ModDataComponents.LOOT_FACTOR, 0.0F);
         this.getBackpackStack().sophisticatedCore_remove(ModDataComponents.LOOT_TABLE);
         this.getBackpackStack().sophisticatedCore_remove(ModDataComponents.LOOT_FACTOR);
         List<class_1799> loot = LootHelper.getLoot(lootTable, server, serverLevel, pos);
         loot.removeIf(stack -> stack.method_7909() instanceof BackpackItem);
         loot = RandHelper.getNRandomElements(loot, (int)(loot.size() * lootFactor));
         LootHelper.fillWithLoot(serverLevel.field_9229, loot, this.getInventoryHandler());
      }
   }

   private void setNumberOfUpgradeSlots(int numberOfUpgradeSlots) {
      this.getBackpackStack().sophisticatedCore_set(ModCoreDataComponents.NUMBER_OF_UPGRADE_SLOTS, numberOfUpgradeSlots);
   }

   public void refreshInventoryForUpgradeProcessing() {
      this.inventoryModificationHandler = null;
      this.fluidHandler = null;
      this.fluidHandlerInitialized = false;
      this.energyStorage = null;
      this.energyStorageInitialized = false;
      this.refreshInventoryForInputOutput();
   }

   public void onContentsNbtUpdated() {
      this.handler = null;
      this.upgradeHandler = null;
      this.refreshInventoryForUpgradeProcessing();
      this.onInventoryHandlerRefresh.run();
   }

   @Override
   public void registerOnInventoryHandlerRefreshListener(Runnable onInventoryHandlerRefresh) {
      this.onInventoryHandlerRefresh = onInventoryHandlerRefresh;
   }

   @Override
   public void unregisterOnInventoryHandlerRefreshListener() {
      this.onInventoryHandlerRefresh = () -> {};
   }

   public class_1799 getWrappedStorageStack() {
      return this.getBackpack();
   }

   public String getStorageType() {
      return "backpack";
   }

   public class_2561 getDisplayName() {
      return this.getBackpack().method_7964();
   }

   private static class FluidHandlerItemWrapper implements IStorageFluidHandler {
      private final IStorageFluidHandler delegate;
      private final class_1799 container;

      public FluidHandlerItemWrapper(class_1799 container, IStorageFluidHandler delegate) {
         this.container = container;
         this.delegate = delegate;
      }

      public class_1799 getContainer() {
         return this.container;
      }

      public long insert(FluidVariant resource, long maxFill, TransactionContext ctx, boolean ignoreInOutLimit) {
         return this.delegate.insert(resource, maxFill, ctx, ignoreInOutLimit);
      }

      public long extract(FluidVariant resource, long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
         return this.delegate.extract(resource, maxDrain, ctx, ignoreInOutLimit);
      }

      public FluidStack extract(class_6862<class_3611> resourceTag, long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
         return this.delegate.extract(resourceTag, maxDrain, ctx, ignoreInOutLimit);
      }

      public FluidStack extract(int maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
         return this.delegate.extract(maxDrain, ctx, ignoreInOutLimit);
      }

      public FluidStack extract(FluidStack resource, TransactionContext ctx, boolean ignoreInOutLimit) {
         return this.delegate.extract(resource, ctx, ignoreInOutLimit);
      }

      public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
         return this.delegate.insert(resource, maxAmount, transaction);
      }

      public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
         return this.delegate.extract(resource, maxAmount, transaction);
      }

      public Iterator<StorageView<FluidVariant>> iterator() {
         return this.delegate.iterator();
      }
   }
}
