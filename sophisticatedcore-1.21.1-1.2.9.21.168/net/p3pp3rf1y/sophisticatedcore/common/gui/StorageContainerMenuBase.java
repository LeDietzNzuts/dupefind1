package net.p3pp3rf1y.sophisticatedcore.common.gui;

import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.IntComparators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1712;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1734;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import net.minecraft.class_3917;
import net.minecraft.class_3956;
import net.minecraft.class_5536;
import net.minecraft.class_5916;
import net.minecraft.class_6880;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncAdditionalSlotInfoPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerClientDataPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncEmptySlotIconsPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncSlotChangeErrorPayload;
import net.p3pp3rf1y.sophisticatedcore.network.TransferItemsPayload;
import net.p3pp3rf1y.sophisticatedcore.settings.ISlotColorCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsHandler;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsManager;
import net.p3pp3rf1y.sophisticatedcore.settings.main.MainSettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.nosort.NoSortSettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IOverflowResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeHandler;
import net.p3pp3rf1y.sophisticatedcore.util.DummySlot;
import net.p3pp3rf1y.sophisticatedcore.util.NoopStorageWrapper;
import org.jetbrains.annotations.NotNull;

public abstract class StorageContainerMenuBase<S extends IStorageWrapper> extends class_1703 implements IAdditionalSlotInfoMenu {
   public static final int NUMBER_OF_PLAYER_SLOTS = 36;
   public static final class_2960 EMPTY_UPGRADE_SLOT_BACKGROUND = class_2960.method_60655("sophisticatedcore", "item/empty_upgrade_slot");
   public static final Pair<class_2960, class_2960> INACCESSIBLE_SLOT_BACKGROUND = new Pair(
      class_1723.field_21668, SophisticatedCore.getRL("item/inaccessible_slot")
   );
   protected static final String UPGRADE_ENABLED_TAG = "upgradeEnabled";
   protected static final String UPGRADE_SLOT_TAG = "upgradeSlot";
   protected static final String ACTION_TAG = "action";
   protected static final String OPEN_TAB_ID_TAG = "openTabId";
   protected static final String SORT_BY_TAG = "sortBy";
   private static final String SEARCH_PHRASE_TAG = "searchPhrase";
   public final class_2371<class_1799> lastUpgradeSlots = class_2371.method_10211();
   public final List<class_1735> upgradeSlots = Lists.newArrayList();
   public final class_2371<class_1799> remoteUpgradeSlots = class_2371.method_10211();
   public final class_2371<class_1799> lastRealSlots = class_2371.method_10211();
   public final List<class_1735> realInventorySlots = Lists.newArrayList();
   private final Map<Integer, UpgradeContainerBase<?, ?>> upgradeContainers = new LinkedHashMap<>();
   private final class_2371<class_1799> remoteRealSlots = class_2371.method_10211();
   protected final class_1657 player;
   protected final S storageWrapper;
   protected final IStorageWrapper parentStorageWrapper;
   private final int storageItemSlotIndex;
   private final boolean shouldLockStorageItemSlot;
   private int storageItemSlotNumber = -1;
   private Consumer<StorageContainerMenuBase<?>> upgradeChangeListener = null;
   private boolean isUpdatingFromPacket = false;
   private long errorResultExpirationTime = 0L;
   @Nullable
   private UpgradeSlotChangeResult errorUpgradeSlotChangeResult;
   private class_2487 lastSettingsNbt = null;
   private boolean inventorySlotStackChanged = false;
   private final Set<Integer> inaccessibleSlots = new HashSet<>();
   private final Map<Integer, Integer> slotLimitOverrides = new HashMap<>();
   private final Set<Integer> infiniteSlots = new HashSet<>();
   private final Map<Integer, class_1799> slotFilterItems = new HashMap<>();
   private final Map<Integer, Pair<class_2960, class_2960>> emptySlotIcons = new HashMap<>();
   private boolean slotsChangedSinceStartOfClick = false;
   private boolean tryingToMergeUpgrade = false;
   private boolean initialBroadcast = true;

   protected StorageContainerMenuBase(
      class_3917<?> menuType,
      int containerId,
      class_1657 player,
      S storageWrapper,
      IStorageWrapper parentStorageWrapper,
      int storageItemSlotIndex,
      boolean shouldLockStorageItemSlot
   ) {
      super(menuType, containerId);
      this.player = player;
      this.storageWrapper = storageWrapper;
      this.parentStorageWrapper = parentStorageWrapper;
      this.storageItemSlotIndex = storageItemSlotIndex;
      this.shouldLockStorageItemSlot = shouldLockStorageItemSlot;
      this.removeOpenTabIfKeepOff();
      storageWrapper.fillWithLoot(player);
      this.initSlotsAndContainers(player, storageItemSlotIndex, shouldLockStorageItemSlot);
   }

   public abstract Optional<class_2338> getBlockPosition();

   public abstract Optional<class_1297> getEntity();

   protected void initSlotsAndContainers(class_1657 player, int storageItemSlotIndex, boolean shouldLockStorageItemSlot) {
      this.addStorageInventorySlots();
      this.addPlayerInventorySlots(player.method_31548(), storageItemSlotIndex, shouldLockStorageItemSlot);
      this.addUpgradeSlots();
      this.addUpgradeSettingsContainers(player);
   }

   public S getStorageWrapper() {
      return this.storageWrapper;
   }

   protected void addUpgradeSettingsContainers(class_1657 player) {
      UpgradeHandler upgradeHandler = this.storageWrapper.getUpgradeHandler();
      upgradeHandler.getSlotWrappers()
         .forEach(
            (slot, wrapper) -> UpgradeContainerRegistry.instantiateContainer(player, slot, wrapper)
               .ifPresent(containerx -> this.upgradeContainers.put(slot, containerx))
         );

      for (UpgradeContainerBase<?, ?> container : this.upgradeContainers.values()) {
         container.getSlots().forEach(this::addUpgradeSlot);
         container.onInit();
      }

      this.storageWrapper.getOpenTabId().ifPresent(id -> {
         if (this.upgradeContainers.containsKey(id)) {
            this.upgradeContainers.get(id).setIsOpen(true);
         }
      });
   }

   private void addUpgradeSlots() {
      UpgradeHandler upgradeHandler = this.storageWrapper.getUpgradeHandler();
      int numberOfSlots = upgradeHandler.getSlotCount();
      if (numberOfSlots != 0) {
         for (int slotIndex = 0; slotIndex < upgradeHandler.getSlotCount(); slotIndex++) {
            this.addUpgradeSlot(this.instantiateUpgradeSlot(upgradeHandler, slotIndex));
         }
      }
   }

   public int getColumnsTaken() {
      return this.storageWrapper.getColumnsTaken();
   }

   public Optional<UpgradeSlotChangeResult> getErrorUpgradeSlotChangeResult() {
      if (this.errorUpgradeSlotChangeResult != null && this.player.method_37908().method_8510() >= this.errorResultExpirationTime) {
         this.clearErrorUpgradeSlotChangeResult();
      }

      return Optional.ofNullable(this.errorUpgradeSlotChangeResult);
   }

   private void clearErrorUpgradeSlotChangeResult() {
      this.errorResultExpirationTime = 0L;
      this.errorUpgradeSlotChangeResult = null;
   }

   protected void sendStorageSettingsToClient() {
   }

   protected abstract StorageContainerMenuBase<S>.StorageUpgradeSlot instantiateUpgradeSlot(UpgradeHandler var1, int var2);

   protected void addUpgradeSlot(class_1735 slot) {
      slot.field_7874 = this.getTotalSlotsNumber();
      this.upgradeSlots.add(slot);
      this.lastUpgradeSlots.add(class_1799.field_8037);
      this.remoteUpgradeSlots.add(class_1799.field_8037);
   }

   protected void addNoSortSlot(class_1735 slot) {
      slot.field_7874 = this.getInventorySlotsSize();
      this.realInventorySlots.add(slot);
      this.lastRealSlots.add(class_1799.field_8037);
      this.remoteRealSlots.add(class_1799.field_8037);
   }

   protected class_1735 method_7621(class_1735 slot) {
      slot.field_7874 = this.getInventorySlotsSize();
      this.field_7761.add(slot);
      this.field_7764.add(class_1799.field_8037);
      this.field_29206.add(class_1799.field_8037);
      this.realInventorySlots.add(slot);
      this.lastRealSlots.add(class_1799.field_8037);
      this.remoteRealSlots.add(class_1799.field_8037);
      return slot;
   }

   public int getInventorySlotsSize() {
      return this.realInventorySlots.size();
   }

   public int getNumberOfStorageInventorySlots() {
      return this.storageWrapper.getInventoryHandler().getSlotCount();
   }

   public int getNumberOfUpgradeSlots() {
      return this.storageWrapper.getUpgradeHandler().getSlotCount();
   }

   public Map<Integer, UpgradeContainerBase<?, ?>> getUpgradeContainers() {
      return this.upgradeContainers;
   }

   protected void addStorageInventorySlots() {
      InventoryHandler inventoryHandler = this.storageWrapper.getInventoryHandler();
      int slotIndex = 0;

      for (Set<Integer> noSortSlotIndexes = this.getNoSortSlotIndexes(); slotIndex < inventoryHandler.getSlotCount(); slotIndex++) {
         final int finalSlotIndex = slotIndex;
         StorageInventorySlot slot = new StorageInventorySlot(this.player.method_37908().field_9236, this.storageWrapper, finalSlotIndex, this.player) {
            @Override
            public void method_7673(@Nonnull class_1799 stack) {
               super.method_7673(stack);
               StorageContainerMenuBase.this.onStorageInventorySlotSet(finalSlotIndex);
            }

            @Nullable
            public Pair<class_2960, class_2960> method_7679() {
               return StorageContainerMenuBase.this.inaccessibleSlots.contains(finalSlotIndex)
                  ? StorageContainerMenuBase.INACCESSIBLE_SLOT_BACKGROUND
                  : StorageContainerMenuBase.this.emptySlotIcons.getOrDefault(finalSlotIndex, null);
            }

            @Override
            public boolean method_7680(@Nonnull class_1799 stack) {
               return !StorageContainerMenuBase.this.inaccessibleSlots.contains(finalSlotIndex) && super.method_7680(stack);
            }

            @Override
            public boolean method_7674(class_1657 playerIn) {
               return !StorageContainerMenuBase.this.inaccessibleSlots.contains(finalSlotIndex) && super.method_7674(playerIn);
            }

            @Override
            public int method_7676(class_1799 stack) {
               return StorageContainerMenuBase.this.slotLimitOverrides.containsKey(finalSlotIndex)
                  ? StorageContainerMenuBase.this.slotLimitOverrides.get(finalSlotIndex)
                  : super.method_7676(stack);
            }

            @Override
            public int method_7675() {
               return StorageContainerMenuBase.this.slotLimitOverrides.containsKey(finalSlotIndex)
                  ? StorageContainerMenuBase.this.slotLimitOverrides.get(finalSlotIndex)
                  : super.method_7675();
            }
         };
         if (noSortSlotIndexes.contains(slotIndex)) {
            this.addNoSortSlot(slot);
         } else {
            this.method_7621(slot);
         }
      }
   }

   protected void onStorageInventorySlotSet(int slotIndex) {
   }

   protected void addPlayerInventorySlots(class_1661 playerInventory, int storageItemSlotIndex, boolean shouldLockStorageItemSlot) {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            int slotIndex = j + i * 9 + 9;
            class_1735 slot = this.addStorageItemSafeSlot(playerInventory, slotIndex, storageItemSlotIndex, shouldLockStorageItemSlot);
            this.addSlotAndUpdateStorageItemSlotNumber(storageItemSlotIndex, shouldLockStorageItemSlot, slotIndex, slot);
         }
      }

      for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
         class_1735 slot = this.addStorageItemSafeSlot(playerInventory, slotIndex, storageItemSlotIndex, shouldLockStorageItemSlot);
         this.addSlotAndUpdateStorageItemSlotNumber(storageItemSlotIndex, shouldLockStorageItemSlot, slotIndex, slot);
      }
   }

   private class_1735 addStorageItemSafeSlot(class_1661 playerInventory, int slotIndex, int storageItemSlotIndex, boolean shouldLockStorageItemSlot) {
      class_1735 slot;
      if (shouldLockStorageItemSlot && slotIndex == storageItemSlotIndex) {
         slot = new class_1735(playerInventory, slotIndex, 0, 0) {
            public boolean method_7674(class_1657 playerIn) {
               return false;
            }
         };
      } else {
         slot = new class_1735(playerInventory, slotIndex, 0, 0);
      }

      return this.method_7621(slot);
   }

   public boolean hasSomethingMessedWithStorage() {
      return !this.isClientSide()
         && (this.storageItemHasChanged() || this.realInventorySlots.size() != this.storageWrapper.getInventoryHandler().getSlotCount() + 36);
   }

   protected boolean isClientSide() {
      return this.player.method_37908().field_9236;
   }

   private void addSlotAndUpdateStorageItemSlotNumber(int storageItemSlotIndex, boolean lockStorageItemSlot, int slotIndex, class_1735 slot) {
      if (lockStorageItemSlot && slotIndex == storageItemSlotIndex) {
         this.storageItemSlotNumber = slot.field_7874;
      }
   }

   public int getNumberOfRows() {
      return this.storageWrapper.getNumberOfSlotRows();
   }

   public int getFirstUpgradeSlot() {
      return this.getInventorySlotsSize();
   }

   public boolean isFirstLevelStorage() {
      return this.parentStorageWrapper == NoopStorageWrapper.INSTANCE;
   }

   public void method_7610(int stateId, List<class_1799> items, class_1799 carried) {
      this.storageWrapper.setPersistent(this.player.method_37908().field_9236);
      this.isUpdatingFromPacket = true;
      super.method_7610(stateId, items, carried);
      this.isUpdatingFromPacket = false;
      this.storageWrapper.setPersistent(true);
      this.storageWrapper.getInventoryHandler().saveInventory();
      this.storageWrapper.getUpgradeHandler().saveInventory();
   }

   protected boolean isUpgradeSettingsSlot(int index) {
      return index >= this.getNumberOfStorageInventorySlots() + this.getNumberOfUpgradeSlots() + 36 && index < this.getTotalSlotsNumber();
   }

   public boolean isStorageInventorySlot(int index) {
      return index >= 0 && index < this.getNumberOfStorageInventorySlots();
   }

   protected boolean isUpgradeSlot(int index) {
      return index >= this.getFirstUpgradeSlot() && index - this.getFirstUpgradeSlot() < this.getNumberOfUpgradeSlots();
   }

   public void method_7593(int slotId, int dragType, class_1713 clickType, class_1657 player) {
      if (this.isUpgradeSettingsSlot(slotId) && this.method_7611(slotId) instanceof IFilterSlot && this.method_7611(slotId).method_7680(this.method_34255())) {
         class_1735 slot = this.method_7611(slotId);
         class_1799 cursorStack = this.method_34255().method_7972();
         if (cursorStack.method_7947() > 1) {
            cursorStack.method_7939(1);
         }

         slot.method_7673(cursorStack);
      } else if (this.isUpgradeSlot(slotId) && this.method_7611(slotId) instanceof StorageContainerMenuBase<?>.StorageUpgradeSlot slot) {
         class_1799 slotStack = slot.method_7677();
         if (slot.method_7680(this.method_34255())) {
            class_1799 carriedStack = this.method_34255();
            IUpgradeItem<?> upgradeItem = (IUpgradeItem<?>)carriedStack.method_7909();
            int newColumnsTaken = upgradeItem.getInventoryColumnsTaken();
            int currentColumnsTaken = 0;
            if (!slotStack.method_7960()) {
               currentColumnsTaken = ((IUpgradeItem)slotStack.method_7909()).getInventoryColumnsTaken();
            }

            if (this.needsSlotsThatAreOccupied(carriedStack, currentColumnsTaken, newColumnsTaken)) {
               return;
            }

            int columnsToRemove = newColumnsTaken - currentColumnsTaken;
            if (slotStack.method_7960()) {
               slot.method_7673(carriedStack.method_7971(1));
               if (carriedStack.method_7960()) {
                  this.method_34254(class_1799.field_8037);
               }
            } else if (carriedStack.method_7947() == 1) {
               slot.method_7673(carriedStack);
               this.method_34254(upgradeItem.getCleanedUpgradeStack(slotStack.method_7972()));
            }

            this.updateColumnsTaken(columnsToRemove);
            slot.method_7668();
            if (columnsToRemove != 0 && player.method_37908().method_8608()) {
               this.onUpgradesChanged();
            }
         } else if (this.method_34255().method_7960() && !slotStack.method_7960() && slot.method_7674(player)) {
            int k2 = dragType == 0
               ? Math.min(slotStack.method_7947(), slotStack.method_7914())
               : Math.min(slotStack.method_7914() + 1, slotStack.method_7947() + 1) / 2;
            IUpgradeItem<?> upgradeItemx = (IUpgradeItem<?>)slotStack.method_7909();
            int columnsTaken = upgradeItemx.getInventoryColumnsTaken();
            if (clickType == class_1713.field_7794) {
               this.method_7601(player, slotId);
            } else {
               this.method_34254(upgradeItemx.getCleanedUpgradeStack(slot.method_7671(k2)));
            }

            slot.wasEmpty = false;
            this.updateColumnsTaken(-columnsTaken);
            slot.method_7667(player, this.method_34255());
         }
      } else if (!this.isOverflowLogicSlotAndAction(slotId, clickType) || !this.handleOverflow(slotId, clickType, dragType, player)) {
         super.method_7593(slotId, dragType, clickType, player);
      }
   }

   public boolean method_40442(int slotIndex) {
      return slotIndex == -1 || slotIndex == -999 || slotIndex < this.getTotalSlotsNumber();
   }

   private boolean handleOverflow(int slotId, class_1713 clickType, int dragType, class_1657 player) {
      class_1799 cursorStack = clickType == class_1713.field_7791 ? player.method_31548().method_5438(dragType) : this.method_34255();
      Consumer<class_1799> updateCursorStack = clickType == class_1713.field_7791 ? s -> player.method_31548().method_5447(dragType, s) : this::method_34254;
      class_1735 slot = this.method_7611(slotId);
      if ((clickType == class_1713.field_7791 || !cursorStack.method_7960()) && slot.method_7680(cursorStack)) {
         class_1799 slotStack = slot.method_7677();
         if (!slotStack.method_7960()
            && (
               !slot.method_7674(player)
                  || slotStack.method_7909() == cursorStack.method_7909()
                  || cursorStack.method_7947() > slot.method_7676(cursorStack)
                  || slotStack.method_7947() > slotStack.method_7914()
            )) {
            return slotStack.method_7909() == cursorStack.method_7909()
               ? this.processOverflowForAnythingOverSlotMaxSize(cursorStack, updateCursorStack, slot, slotStack)
               : false;
         } else {
            return this.processOverflowIfSlotWithSameItemFound(slotId, cursorStack, updateCursorStack);
         }
      } else {
         return false;
      }
   }

   private boolean processOverflowForAnythingOverSlotMaxSize(
      class_1799 cursorStack, Consumer<class_1799> updateCursorStack, class_1735 slot, class_1799 slotStack
   ) {
      int remainingSpaceInSlot = slot.method_7676(cursorStack) - slotStack.method_7947();
      if (remainingSpaceInSlot < cursorStack.method_7947()) {
         class_1799 overflow = cursorStack.method_7972();
         int overflowCount = cursorStack.method_7947() - remainingSpaceInSlot;
         overflow.method_7939(overflowCount);
         class_1799 result = this.processOverflowLogic(overflow);
         if (result.method_7947() < overflowCount) {
            cursorStack.method_7934(overflowCount - result.method_7947());
            if (cursorStack.method_7960()) {
               updateCursorStack.accept(class_1799.field_8037);
               return true;
            }

            updateCursorStack.accept(cursorStack);
         }
      }

      return false;
   }

   private boolean processOverflowIfSlotWithSameItemFound(int slotId, class_1799 cursorStack, Consumer<class_1799> updateCursorStack) {
      for (IOverflowResponseUpgrade overflowUpgrade : this.storageWrapper
         .getUpgradeHandler()
         .getWrappersThatImplementFromMainStorage(IOverflowResponseUpgrade.class)) {
         if (overflowUpgrade.stackMatchesFilter(cursorStack)
            && overflowUpgrade.worksInGui()
            && this.findSlotWithMatchingStack(slotId, cursorStack, updateCursorStack, overflowUpgrade)) {
            return true;
         }
      }

      return false;
   }

   private boolean findSlotWithMatchingStack(
      int slotId, class_1799 cursorStack, Consumer<class_1799> updateCursorStack, IOverflowResponseUpgrade overflowUpgrade
   ) {
      for (int slotIndex = 0; slotIndex < this.getNumberOfStorageInventorySlots(); slotIndex++) {
         if (slotIndex != slotId && overflowUpgrade.stackMatchesFilterStack(this.method_7611(slotIndex).method_7677(), cursorStack)) {
            class_1799 result = overflowUpgrade.onOverflow(cursorStack);
            updateCursorStack.accept(result);
            if (result.method_7960()) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean isOverflowLogicSlotAndAction(int slotId, class_1713 clickType) {
      return this.isStorageInventorySlot(slotId) && (clickType == class_1713.field_7791 || clickType == class_1713.field_7790);
   }

   protected void updateColumnsTaken(int columnsChange) {
      if (columnsChange != 0) {
         this.storageWrapper.setColumnsTaken(Math.max(0, this.storageWrapper.getColumnsTaken() + columnsChange), true);
         this.storageWrapper.onContentsNbtUpdated();
         this.refreshAllSlots();
      }
   }

   protected boolean needsSlotsThatAreOccupied(class_1799 cursorStack, int currentColumnsTaken, int newColumnsTaken) {
      if (currentColumnsTaken >= newColumnsTaken) {
         return false;
      } else {
         int slotsToCheck = (newColumnsTaken - currentColumnsTaken) * this.getNumberOfRows();
         InventoryHandler invHandler = this.storageWrapper.getInventoryHandler();
         Set<Integer> errorSlots = new HashSet<>();
         int slots = this.getNumberOfStorageInventorySlots();

         for (int slotIndex = slots - 1; slotIndex >= slots - slotsToCheck; slotIndex--) {
            if (!invHandler.getStackInSlot(slotIndex).method_7960()) {
               errorSlots.add(slotIndex);
            }
         }

         if (!errorSlots.isEmpty()) {
            this.updateSlotChangeError(
               UpgradeSlotChangeResult.fail(
                  TranslationHelper.INSTANCE.translError("add.needs_occupied_inventory_slots", slotsToCheck, cursorStack.method_7964()),
                  Collections.emptySet(),
                  errorSlots,
                  Collections.emptySet()
               )
            );
            return true;
         } else {
            return false;
         }
      }
   }

   public int getUpgradeSlotsSize() {
      return this.upgradeSlots.size();
   }

   public List<Integer> getSlotOverlayColors(int slot) {
      List<Integer> ret = new ArrayList<>();
      this.storageWrapper.getSettingsHandler().getCategoriesThatImplement(ISlotColorCategory.class).forEach(c -> c.getSlotColor(slot).ifPresent(ret::add));
      return ret;
   }

   public Optional<UpgradeContainerBase<?, ?>> getOpenContainer() {
      return this.storageWrapper
         .getOpenTabId()
         .flatMap(id -> this.upgradeContainers.containsKey(id) ? Optional.of(this.upgradeContainers.get(id)) : Optional.empty());
   }

   protected void sendToServer(Consumer<class_2487> addData) {
      class_2487 data = new class_2487();
      addData.accept(data);
      PacketDistributor.sendToServer(new SyncContainerClientDataPayload(data));
   }

   public void setUpgradeEnabled(int upgradeSlot, boolean enabled) {
      Map<Integer, IUpgradeWrapper> slotWrappers = this.storageWrapper.getUpgradeHandler().getSlotWrappers();
      if (slotWrappers.containsKey(upgradeSlot)) {
         if (this.isClientSide()) {
            this.sendToServer(data -> {
               data.method_10556("upgradeEnabled", enabled);
               data.method_10569("upgradeSlot", upgradeSlot);
            });
         }

         slotWrappers.get(upgradeSlot).setEnabled(enabled);
      }
   }

   public boolean getUpgradeEnabled(int upgradeSlot) {
      Map<Integer, IUpgradeWrapper> slotWrappers = this.storageWrapper.getUpgradeHandler().getSlotWrappers();
      return !slotWrappers.containsKey(upgradeSlot) ? false : slotWrappers.get(upgradeSlot).isEnabled();
   }

   public boolean canDisableUpgrade(int upgradeSlot) {
      Map<Integer, IUpgradeWrapper> slotWrappers = this.storageWrapper.getUpgradeHandler().getSlotWrappers();
      return !slotWrappers.containsKey(upgradeSlot) ? false : slotWrappers.get(upgradeSlot).canBeDisabled();
   }

   public void sort() {
      if (this.isClientSide()) {
         this.sendToServer(data -> data.method_10582("action", "sort"));
      } else {
         this.storageWrapper.sort();
      }
   }

   public void setOpenTabId(int tabId) {
      if (this.isClientSide()) {
         this.sendToServer(data -> data.method_10569("openTabId", tabId));
      }

      if (tabId == -1) {
         this.storageWrapper.removeOpenTabId();
      } else {
         this.storageWrapper.setOpenTabId(tabId);
      }
   }

   public void removeOpenTabId() {
      this.setOpenTabId(-1);
   }

   public SortBy getSortBy() {
      return this.storageWrapper.getSortBy();
   }

   public void setSortBy(SortBy sortBy) {
      if (this.isClientSide()) {
         this.sendToServer(data -> data.method_10582("sortBy", sortBy.method_15434()));
      }

      this.storageWrapper.setSortBy(sortBy);
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("containerId")) {
         int containerId = data.method_10550("containerId");
         if (this.upgradeContainers.containsKey(containerId)) {
            this.upgradeContainers.get(containerId).handlePacket(data);
         }
      } else if (data.method_10545("openTabId")) {
         this.setOpenTabId(data.method_10550("openTabId"));
      } else if (data.method_10545("sortBy")) {
         this.setSortBy(SortBy.fromName(data.method_10558("sortBy")));
      } else if (data.method_10545("searchPhrase")) {
         this.setSearchPhrase(data.method_10558("searchPhrase"));
      } else if (data.method_10545("action")) {
         String actionName = data.method_10558("action");
         switch (actionName) {
            case "sort":
               this.sort();
               break;
            case "openSettings":
               this.openSettings();
         }
      } else if (data.method_10545("upgradeEnabled")) {
         this.setUpgradeEnabled(data.method_10550("upgradeSlot"), data.method_10577("upgradeEnabled"));
      }
   }

   public Optional<UpgradeContainerBase<?, ?>> getSlotUpgradeContainer(class_1735 slot) {
      if (this.isUpgradeSettingsSlot(slot.field_7874)) {
         for (UpgradeContainerBase<?, ?> upgradeContainer : this.upgradeContainers.values()) {
            if (upgradeContainer.containsSlot(slot)) {
               return Optional.of(upgradeContainer);
            }
         }
      }

      return Optional.empty();
   }

   public class_1799 method_7601(class_1657 player, int index) {
      class_1799 itemstack = class_1799.field_8037;
      class_1735 slot = this.method_7611(index);
      if (slot.method_7681()) {
         Optional<UpgradeContainerBase<?, ?>> upgradeContainer = this.getSlotUpgradeContainer(slot);
         class_1799 slotStack = upgradeContainer.<class_1799>map(c -> c.getSlotStackToTransfer(slot)).orElse(slot.method_7677());
         itemstack = slotStack.method_7972();
         class_1799 stackToMerge = this.isUpgradeSlot(index) && slotStack.method_7909() instanceof IUpgradeItem<?> upgradeItem
            ? upgradeItem.getCleanedUpgradeStack(slotStack.method_7972())
            : slotStack;
         if (!this.mergeSlotStack(slot, index, stackToMerge)) {
            return class_1799.field_8037;
         }

         if (stackToMerge.method_7960()) {
            slot.method_7673(class_1799.field_8037);
         } else {
            slot.method_7668();
         }

         slot.method_7670(slotStack, itemstack);
         if (upgradeContainer.isPresent()) {
            upgradeContainer.ifPresent(c -> c.onTakeFromSlot(slot, player, slotStack));
         } else {
            slot.method_7667(player, slotStack);
         }
      }

      return itemstack;
   }

   private boolean mergeSlotStack(class_1735 slot, int index, class_1799 slotStack) {
      if (this.isUpgradeSlot(index)) {
         return this.mergeStackToPlayersInventory(slot, slotStack) || this.mergeStackToStorage(slot, slotStack);
      } else if (this.isStorageInventorySlot(index)) {
         return this.shouldShiftClickIntoOpenTabFirst()
            ? this.mergeStackToOpenUpgradeTab(slot, slotStack) || this.mergeStackToPlayersInventory(slot, slotStack)
            : this.mergeStackToPlayersInventory(slot, slotStack) || this.mergeStackToOpenUpgradeTab(slot, slotStack);
      } else if (this.isUpgradeSettingsSlot(index)) {
         return this.getSlotUpgradeContainer(slot).map(c -> c.mergeIntoStorageFirst(slot)).orElse(true)
            ? this.mergeStackToStorage(slot, slotStack) || this.mergeStackToPlayersInventory(slot, slotStack)
            : this.mergeStackToPlayersInventory(slot, slotStack) || this.mergeStackToStorage(slot, slotStack);
      } else {
         return this.shouldShiftClickIntoOpenTabFirst()
            ? this.mergeStackToOpenUpgradeTab(slot, slotStack) || this.mergeStackToUpgradeSlots(slot, slotStack) || this.mergeStackToStorage(slot, slotStack)
            : this.mergeStackToUpgradeSlots(slot, slotStack) || this.mergeStackToStorage(slot, slotStack) || this.mergeStackToOpenUpgradeTab(slot, slotStack);
      }
   }

   private boolean shouldShiftClickIntoOpenTabFirst() {
      MainSettingsCategory<?> category = this.storageWrapper.getSettingsHandler().getGlobalSettingsCategory();
      return SettingsManager.getSettingValue(this.player, category.getPlayerSettingsTagName(), category, SettingsManager.SHIFT_CLICK_INTO_OPEN_TAB_FIRST);
   }

   public boolean shouldKeepSearchPhrase() {
      MainSettingsCategory<?> category = this.storageWrapper.getSettingsHandler().getGlobalSettingsCategory();
      return SettingsManager.getSettingValue(this.player, category.getPlayerSettingsTagName(), category, SettingsManager.KEEP_SEARCH_PHRASE);
   }

   public String getSearchPhrase() {
      String searchPhrase = "";
      MainSettingsCategory<?> category = this.storageWrapper.getSettingsHandler().getGlobalSettingsCategory();
      if (SettingsManager.getPlayerSetting(this.player, category.getPlayerSettingsTagName(), SettingsManager.KEEP_SEARCH_PHRASE)
         .orElse(SettingsManager.KEEP_SEARCH_PHRASE.getDefaultValue())) {
         searchPhrase = SettingsManager.getPlayerSetting(this.player, category.getPlayerSettingsTagName(), SettingsManager.SEARCH_PHRASE).orElse("");
      }

      if (searchPhrase.isEmpty()
         && Boolean.TRUE
            .equals(SettingsManager.getSettingValue(this.player, category.getPlayerSettingsTagName(), category, SettingsManager.KEEP_SEARCH_PHRASE))) {
         searchPhrase = SettingsManager.getSettingValue(this.player, category.getPlayerSettingsTagName(), category, SettingsManager.SEARCH_PHRASE);
      }

      return searchPhrase;
   }

   public void setSearchPhrase(String searchPhrase) {
      MainSettingsCategory<?> category = this.storageWrapper.getSettingsHandler().getGlobalSettingsCategory();
      if (SettingsManager.getPlayerSetting(this.player, category.getPlayerSettingsTagName(), SettingsManager.KEEP_SEARCH_PHRASE)
         .orElse(SettingsManager.KEEP_SEARCH_PHRASE.getDefaultValue())) {
         SettingsManager.setPlayerSetting(this.player, category.getPlayerSettingsTagName(), SettingsManager.SEARCH_PHRASE, searchPhrase);
         SettingsManager.setSetting(this.player, category.getPlayerSettingsTagName(), category, SettingsManager.SEARCH_PHRASE, "");
      } else {
         SettingsManager.setSetting(this.player, category.getPlayerSettingsTagName(), category, SettingsManager.SEARCH_PHRASE, searchPhrase);
      }

      if (this.isClientSide()) {
         this.sendToServer(data -> data.method_10582("searchPhrase", searchPhrase));
      }
   }

   private boolean mergeStackToUpgradeSlots(class_1735 sourceSlot, class_1799 slotStack) {
      if (!(slotStack.method_7909() instanceof IUpgradeItem)) {
         return false;
      } else {
         this.clearErrorUpgradeSlotChangeResult();
         this.tryingToMergeUpgrade = true;
         boolean result = !this.upgradeSlots.isEmpty()
            && this.moveItemStackTo(sourceSlot, slotStack, this.getInventorySlotsSize(), this.getInventorySlotsSize() + this.getNumberOfUpgradeSlots(), false);
         this.tryingToMergeUpgrade = false;
         this.showUpgradeSlotChangeError();
         return result;
      }
   }

   private boolean mergeStackToOpenUpgradeTab(class_1735 sourceSlot, class_1799 slotStack) {
      return this.getOpenContainer().map(c -> {
         List<class_1735> slots = c.getSlots();
         if (slots.isEmpty()) {
            return false;
         } else {
            int firstSlotIndex = slots.get(0).field_7874;
            int lastSlotIndex = slots.get(slots.size() - 1).field_7874;
            return this.mergeItemStack(sourceSlot, slotStack, firstSlotIndex, lastSlotIndex + 1, false, true);
         }
      }).orElse(false);
   }

   private boolean mergeStackToStorage(class_1735 slot, class_1799 slotStack) {
      class_1799 remaining = this.mergeItemStack(slotStack, 0, this.getNumberOfStorageInventorySlots(), false, false, true);
      if (remaining.method_7947() != slotStack.method_7947()) {
         slot.method_7673(remaining);
         return true;
      } else {
         return false;
      }
   }

   private boolean mergeStackToPlayersInventory(class_1735 sourceSlot, class_1799 slotStack) {
      return this.mergeItemStack(sourceSlot, slotStack, this.getNumberOfStorageInventorySlots(), this.getInventorySlotsSize(), true, true);
   }

   public boolean isNotPlayersInventorySlot(int slotNumber) {
      return slotNumber < this.getNumberOfStorageInventorySlots() || slotNumber >= this.getInventorySlotsSize();
   }

   public Optional<class_1799> getMemorizedStackInSlot(int slotId) {
      return this.storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class).getSlotFilterStack(slotId, false);
   }

   public void setUpgradeChangeListener(Consumer<StorageContainerMenuBase<?>> upgradeChangeListener) {
      this.upgradeChangeListener = upgradeChangeListener;
   }

   public abstract void openSettings();

   protected abstract boolean storageItemHasChanged();

   public <T extends UpgradeContainerBase<?, ?> & ICraftingContainer> Optional<T> getOpenOrFirstCraftingContainer(class_3956<?> recipeType) {
      T firstContainer = null;

      for (UpgradeContainerBase<?, ?> container : this.upgradeContainers.values()) {
         if (container instanceof ICraftingContainer craftingContainer && craftingContainer.getRecipeType() == recipeType) {
            if (container.isOpen()) {
               return Optional.of((T)container);
            }

            if (firstContainer == null) {
               firstContainer = (T)container;
            }
         }
      }

      return Optional.ofNullable(firstContainer);
   }

   public int getTotalSlotsNumber() {
      return this.getInventorySlotsSize() + this.upgradeSlots.size();
   }

   protected void removeOpenTabIfKeepOff() {
      MainSettingsCategory<?> category = this.storageWrapper.getSettingsHandler().getGlobalSettingsCategory();
      if (Boolean.FALSE.equals(SettingsManager.getSettingValue(this.player, category.getPlayerSettingsTagName(), category, SettingsManager.KEEP_TAB_OPEN))) {
         this.storageWrapper.removeOpenTabId();
      }
   }

   protected Set<Integer> getNoSortSlotIndexes() {
      SettingsHandler settingsHandler = this.storageWrapper.getSettingsHandler();
      Set<Integer> slotIndexesExcludedFromSort = new HashSet<>();
      slotIndexesExcludedFromSort.addAll(settingsHandler.getTypeCategory(NoSortSettingsCategory.class).getNoSortSlots());
      slotIndexesExcludedFromSort.addAll(settingsHandler.getTypeCategory(MemorySettingsCategory.class).getSlotIndexes());
      return slotIndexesExcludedFromSort;
   }

   public void method_37420() {
      this.broadcastFullStateOf(this.lastUpgradeSlots, this.upgradeSlots, this.getFirstUpgradeSlot());
      this.broadcastFullStateOf(this.lastRealSlots, this.realInventorySlots, 0);
      this.method_34252();
   }

   private void broadcastFullStateOf(class_2371<class_1799> lastSlotsCollection, List<class_1735> slotsCollection, int slotIndexOffset) {
      for (int i = 0; i < slotsCollection.size(); i++) {
         class_1735 slot = slotsCollection.get(i);
         class_1799 itemstack = slot.method_7677();
         this.triggerSlotListeners(i, itemstack, itemstack::method_7972, lastSlotsCollection, slotIndexOffset, slot);
      }
   }

   protected void triggerSlotListeners(
      int stackIndex,
      class_1799 slotStack,
      Supplier<class_1799> slotStackCopy,
      class_2371<class_1799> lastSlotsCollection,
      int slotIndexOffset,
      class_1735 slot
   ) {
      class_1799 itemstack = (class_1799)lastSlotsCollection.get(stackIndex);
      if (!class_1799.method_7973(itemstack, slotStack)) {
         class_1799 stackCopy = slotStackCopy.get();
         lastSlotsCollection.set(stackIndex, stackCopy);

         for (class_1712 containerlistener : this.field_7765) {
            containerlistener.method_7635(this, stackIndex + slotIndexOffset, stackCopy);
         }

         if (!this.initialBroadcast && this.isUpgradeSettingsSlot(slot.field_7874)) {
            slot.method_7668();
         }
      }
   }

   public void method_34252() {
      for (int i = 0; i < this.getInventorySlotsSize(); i++) {
         this.remoteRealSlots.set(i, this.realInventorySlots.get(i).method_7677().method_7972());
      }

      for (int i = 0; i < this.upgradeSlots.size(); i++) {
         this.remoteUpgradeSlots.set(i, this.upgradeSlots.get(i).method_7677().method_7972());
      }

      class_2371<class_1799> allRemoteSlots = class_2371.method_10211();
      allRemoteSlots.addAll(this.remoteRealSlots);
      allRemoteSlots.addAll(this.remoteUpgradeSlots);
      this.field_29207 = this.method_34255().method_7972();
      if (this.field_29208 != null) {
         this.field_29208.method_34263(this, allRemoteSlots, this.field_29207, new int[0]);
      }

      this.sendEmptySlotIcons();
      this.sendAdditionalSlotInfo();
   }

   public boolean isInfiniteSlot(int slot) {
      return this.infiniteSlots.contains(slot);
   }

   private void sendEmptySlotIcons() {
      if (this.player instanceof class_3222 serverPlayer) {
         HashMap var5 = new HashMap();

         for (int slot = 0; slot < this.storageWrapper.getInventoryHandler().getSlotCount(); slot++) {
            Pair<class_2960, class_2960> noItemIcon = this.storageWrapper.getInventoryHandler().getNoItemIcon(slot);
            if (noItemIcon != null) {
               var5.computeIfAbsent((class_2960)noItemIcon.getSecond(), rl -> new HashSet<>()).add(slot);
            }
         }

         PacketDistributor.sendToPlayer(serverPlayer, new SyncEmptySlotIconsPayload(var5));
      }
   }

   private void sendAdditionalSlotInfo() {
      if (this.player instanceof class_3222 serverPlayer) {
         HashSet var10 = new HashSet();
         HashMap slotLimitOverrides = new HashMap();
         HashSet infiniteSlots = new HashSet();
         InventoryHandler inventoryHandler = this.storageWrapper.getInventoryHandler();
         HashMap slotFilterItems = new HashMap();

         for (int slot = 0; slot < inventoryHandler.getSlotCount(); slot++) {
            if (!inventoryHandler.isSlotAccessible(slot)) {
               var10.add(slot);
            }

            if (inventoryHandler.isInfinite(slot)) {
               infiniteSlots.add(slot);
            }

            class_1799 stackInSlot = inventoryHandler.getStackInSlot(slot);
            int stackLimit = inventoryHandler.getStackLimit(slot, stackInSlot);
            if (stackLimit != inventoryHandler.getBaseStackLimit(stackInSlot)) {
               slotLimitOverrides.put(slot, stackLimit);
            }

            if (inventoryHandler.getFilterItem(slot) != class_1802.field_8162) {
               slotFilterItems.put(slot, inventoryHandler.getFilterItem(slot).method_40131());
            }
         }

         PacketDistributor.sendToPlayer(serverPlayer, new SyncAdditionalSlotInfoPayload(var10, slotLimitOverrides, infiniteSlots, slotFilterItems));
      }
   }

   public void method_34245(int slotIndex, class_1799 stack) {
      if (slotIndex < this.getInventorySlotsSize()) {
         this.remoteRealSlots.set(slotIndex, stack.method_7972());
      } else {
         this.remoteUpgradeSlots.set(slotIndex, stack.method_7972());
      }
   }

   public void method_37449(int slotIndex, class_1799 stack) {
      if (slotIndex < this.getInventorySlotsSize()) {
         class_1799 previous = (class_1799)this.remoteRealSlots.get(slotIndex);
         this.remoteRealSlots.set(slotIndex, stack);
         if (this.isStorageInventorySlot(slotIndex) && (previous.method_7960() || stack.method_7960())) {
            this.inventorySlotStackChanged = true;
         }
      } else {
         this.remoteUpgradeSlots.set(slotIndex - this.getInventorySlotsSize(), stack);
      }
   }

   public OptionalInt method_37418(class_1263 container, int slotIdx) {
      for (int i = 0; i < this.getTotalSlotsNumber(); i++) {
         class_1735 slot = this.method_7611(i);
         if (slot.field_7871 == container && slotIdx == slot.method_34266()) {
            return OptionalInt.of(i);
         }
      }

      return OptionalInt.empty();
   }

   private void refreshAllSlots() {
      this.field_7761.clear();
      this.field_7764.clear();
      this.realInventorySlots.clear();
      this.lastRealSlots.clear();
      this.remoteRealSlots.clear();
      this.upgradeSlots.clear();
      this.lastUpgradeSlots.clear();
      this.remoteUpgradeSlots.clear();
      this.upgradeContainers.clear();
      this.initSlotsAndContainers(this.player, this.storageItemSlotIndex, this.shouldLockStorageItemSlot);
      this.slotsChangedSinceStartOfClick = true;
   }

   protected class_1799 processOverflowLogic(class_1799 stack) {
      class_1799 result = stack;

      for (IOverflowResponseUpgrade overflowUpgrade : this.storageWrapper
         .getUpgradeHandler()
         .getWrappersThatImplementFromMainStorage(IOverflowResponseUpgrade.class)) {
         if (overflowUpgrade.worksInGui()) {
            result = overflowUpgrade.onOverflow(result);
            if (result.method_7960()) {
               break;
            }
         }
      }

      return result;
   }

   private void onSwapCraft(class_1735 slot, int numItemsCrafted) {
      slot.method_7672(numItemsCrafted);
   }

   public static int getQuickCraftPlaceCount(class_1735 slot, int quickCraftSlotsSize, int quickCraftingType, class_1799 carriedStack) {
      return Math.min(slot.method_7676(carriedStack), switch (quickCraftingType) {
         case 0 -> class_3532.method_15375((float)carriedStack.method_7947() / quickCraftSlotsSize);
         case 1 -> 1;
         case 2 -> carriedStack.method_7914();
         default -> carriedStack.method_7947();
      });
   }

   protected void method_30010(int slotId, int dragType, class_1713 clickType, class_1657 player) {
      if (slotId < this.getTotalSlotsNumber()) {
         this.slotsChangedSinceStartOfClick = false;
         class_1661 inventory = player.method_31548();
         if (clickType == class_1713.field_7789) {
            int i = this.field_7759;
            this.field_7759 = method_7594(dragType);
            if ((i != 1 || this.field_7759 != 2) && i != this.field_7759) {
               this.method_7605();
            } else if (this.method_34255().method_7960()) {
               this.method_7605();
            } else if (this.field_7759 == 0) {
               this.field_7762 = method_7620(dragType);
               if (method_7600(this.field_7762, player)) {
                  this.field_7759 = 1;
                  this.field_7757.clear();
               } else {
                  this.method_7605();
               }
            } else if (this.field_7759 == 1) {
               class_1735 slot = this.method_7611(slotId);
               class_1799 itemstack = this.method_34255();
               if (canItemQuickReplace(slot, itemstack)
                  && slot.method_7680(itemstack)
                  && (this.field_7762 == 2 || itemstack.method_7947() > this.field_7757.size())
                  && this.method_7615(slot)) {
                  this.field_7757.add(slot);
               }
            } else if (this.field_7759 == 2) {
               if (!this.field_7757.isEmpty()) {
                  if (this.field_7757.size() == 1) {
                     int l = ((class_1735)this.field_7757.iterator().next()).field_7874;
                     this.method_7605();
                     this.method_7593(l, this.field_7762, class_1713.field_7790, player);
                     return;
                  }

                  class_1799 carried = this.method_34255().method_7972();
                  int j1 = this.method_34255().method_7947();

                  for (class_1735 slot1 : this.field_7757) {
                     class_1799 itemstack1 = this.method_34255();
                     if (slot1 != null
                        && canItemQuickReplace(slot1, itemstack1)
                        && slot1.method_7680(itemstack1)
                        && (this.field_7762 == 2 || itemstack1.method_7947() >= this.field_7757.size())
                        && this.method_7615(slot1)) {
                        class_1799 carriedCopy = carried.method_7972();
                        int j = slot1.method_7681() ? slot1.method_7677().method_7947() : 0;
                        int slotStackLimit = slot1.method_7676(carriedCopy);
                        if (!(slot1 instanceof StorageInventorySlot) && slotStackLimit > carriedCopy.method_7914()) {
                           slotStackLimit = carriedCopy.method_7914();
                        }

                        int l = Math.min(getQuickCraftPlaceCount(slot1, this.field_7757.size(), this.field_7762, carriedCopy) + j, slotStackLimit);
                        j1 -= l - j;
                        slot1.method_53512(carriedCopy.method_46651(l));
                     }
                  }

                  carried.method_7939(j1);
                  this.method_34254(carried);
               }

               this.method_7605();
            } else {
               this.method_7605();
            }
         } else if (this.field_7759 != 0) {
            this.method_7605();
         } else if ((clickType == class_1713.field_7790 || clickType == class_1713.field_7794) && (dragType == 0 || dragType == 1)) {
            class_5536 clickaction = dragType == 0 ? class_5536.field_27013 : class_5536.field_27014;
            if (slotId == -999) {
               if (!this.method_34255().method_7960()) {
                  if (clickaction == class_5536.field_27013) {
                     player.method_7328(this.method_34255(), true);
                     this.method_34254(class_1799.field_8037);
                  } else {
                     player.method_7328(this.method_34255().method_7971(1), true);
                  }
               }
            } else if (clickType == class_1713.field_7794) {
               if (slotId < 0) {
                  return;
               }

               class_1735 slot6 = this.method_7611(slotId);
               if (!slot6.method_7674(player)) {
                  return;
               }

               if (this.isStorageInventorySlot(slotId)) {
                  this.method_7601(this.player, slotId).method_7972();
               } else {
                  class_1799 itemstack8 = this.method_7601(this.player, slotId);

                  while (!this.slotsChangedSinceStartOfClick && !itemstack8.method_7960() && class_1799.method_7984(slot6.method_7677(), itemstack8)) {
                     itemstack8 = this.method_7601(this.player, slotId);
                  }
               }
            } else {
               if (slotId < 0) {
                  return;
               }

               class_1735 slot7 = this.method_7611(slotId);
               class_1799 slotStack = slot7.method_7677();
               class_1799 carriedStack = this.method_34255();
               player.method_33592(carriedStack, slot7.method_7677(), clickaction);
               if (!carriedStack.method_31575(slot7, clickaction, player)
                  && !slotStack.method_31576(carriedStack, slot7, clickaction, player, this.method_34259())) {
                  if (slotStack.method_7960()) {
                     if (!carriedStack.method_7960()) {
                        int l2 = clickaction == class_5536.field_27013 ? carriedStack.method_7947() : 1;
                        this.method_34254(slot7.method_32755(carriedStack, l2));
                     }
                  } else if (slot7.method_7674(player)) {
                     if (carriedStack.method_7960()) {
                        int countToRemove = Math.min(slotStack.method_7947(), slotStack.method_7914());
                        if (clickaction == class_5536.field_27014) {
                           countToRemove = countToRemove / 2 + countToRemove % 2;
                        }

                        Optional<class_1799> optional1 = slot7.method_34264(countToRemove, Integer.MAX_VALUE, player);
                        optional1.ifPresent(p_150421_ -> {
                           this.method_34254(p_150421_);
                           slot7.method_7667(player, p_150421_);
                        });
                     } else if (slot7.method_7680(carriedStack)) {
                        if (class_1799.method_31577(slotStack, carriedStack)) {
                           int j3 = clickaction == class_5536.field_27013 ? carriedStack.method_7947() : 1;
                           this.method_34254(slot7.method_32755(carriedStack, j3));
                        } else if (carriedStack.method_7947() <= slot7.method_7676(carriedStack) && slotStack.method_7947() <= slotStack.method_7914()) {
                           slot7.method_7673(carriedStack);
                           this.method_34254(slotStack);
                        }
                     } else if (class_1799.method_31577(slotStack, carriedStack)) {
                        Optional<class_1799> optional = slot7.method_34264(
                           slotStack.method_7947(), carriedStack.method_7914() - carriedStack.method_7947(), player
                        );
                        optional.ifPresent(p_150428_ -> {
                           carriedStack.method_7933(p_150428_.method_7947());
                           slot7.method_7667(player, p_150428_);
                        });
                     }
                  }
               }

               slot7.method_7668();
            }
         } else if (clickType == class_1713.field_7791) {
            class_1735 slot2 = this.method_7611(slotId);
            class_1799 itemstack4 = inventory.method_5438(dragType);
            class_1799 slotStack = slot2.method_7677();
            if (!itemstack4.method_7960() || !slotStack.method_7960()) {
               if (itemstack4.method_7960()) {
                  if (slot2.method_7674(player)) {
                     if (slotStack.method_7947() <= slotStack.method_7914()) {
                        inventory.method_5447(dragType, slotStack);
                        this.onSwapCraft(slot2, slotStack.method_7947());
                        slot2.method_7673(class_1799.field_8037);
                        slot2.method_7667(player, slotStack);
                     } else {
                        inventory.method_5447(dragType, slotStack.method_7971(slotStack.method_7914()));
                        slot2.method_7668();
                     }
                  }
               } else if (slotStack.method_7960()) {
                  if (slot2.method_7680(itemstack4)) {
                     int l1 = slot2.method_7676(itemstack4);
                     if (itemstack4.method_7947() > l1) {
                        slot2.method_7673(itemstack4.method_7971(l1));
                     } else {
                        slot2.method_7673(itemstack4);
                        inventory.method_5447(dragType, class_1799.field_8037);
                     }
                  }
               } else if (slotStack.method_7947() <= slotStack.method_7914() && slot2.method_7674(player) && slot2.method_7680(itemstack4)) {
                  int i2 = slot2.method_7676(itemstack4);
                  if (itemstack4.method_7947() > i2) {
                     slot2.method_7673(itemstack4.method_7971(i2));
                     slot2.method_7667(player, slotStack);
                     if (!inventory.method_7394(slotStack)) {
                        player.method_7328(slotStack, true);
                     }
                  } else {
                     slot2.method_7673(itemstack4);
                     inventory.method_5447(dragType, slotStack);
                     slot2.method_7667(player, slotStack);
                  }
               }
            }
         } else if (clickType == class_1713.field_7796 && player.method_31549().field_7477 && this.method_34255().method_7960() && slotId >= 0) {
            class_1735 slot5 = this.method_7611(slotId);
            if (slot5.method_7681()) {
               class_1799 itemstack6 = slot5.method_7677().method_7972();
               itemstack6.method_7939(itemstack6.method_7914());
               this.method_34254(itemstack6);
            }
         } else if (clickType == class_1713.field_7795 && this.method_34255().method_7960() && slotId >= 0) {
            class_1735 slot4 = this.method_7611(slotId);
            int i1 = dragType == 0 ? 1 : slot4.method_7677().method_7947();
            class_1799 itemstack8 = slot4.method_32753(i1, slot4.method_7677().method_7914(), player);
            player.method_7328(itemstack8, true);
         } else if (clickType == class_1713.field_7793 && slotId >= 0) {
            class_1735 slot3 = this.method_7611(slotId);
            class_1799 carriedStack = this.method_34255();
            if (!carriedStack.method_7960() && (!slot3.method_7681() || !slot3.method_7674(player))) {
               int k1 = dragType == 0 ? 0 : this.getInventorySlotsSize() - 1;
               int j2 = dragType == 0 ? 1 : -1;

               for (int k2 = 0; k2 < 2; k2++) {
                  for (int k3 = k1; k3 >= 0 && k3 < this.getInventorySlotsSize() && carriedStack.method_7947() < carriedStack.method_7914(); k3 += j2) {
                     class_1735 slot8 = this.method_7611(k3);
                     if (slot8.method_7681() && canItemQuickReplace(slot8, carriedStack) && slot8.method_7674(player) && this.method_7613(carriedStack, slot8)) {
                        class_1799 itemstack12 = slot8.method_7677();
                        if (k2 != 0 || itemstack12.method_7947() != itemstack12.method_7914()) {
                           class_1799 itemstack13 = slot8.method_32753(
                              itemstack12.method_7947(), carriedStack.method_7914() - carriedStack.method_7947(), player
                           );
                           carriedStack.method_7933(itemstack13.method_7947());
                        }
                     }
                  }
               }

               k1 = dragType == 0 ? 0 : this.upgradeSlots.size() - 1;

               for (int j = 0; j < 2; j++) {
                  for (int upgradeSlotId = k1;
                     upgradeSlotId >= 0 && upgradeSlotId < this.upgradeSlots.size() && carriedStack.method_7947() < carriedStack.method_7914();
                     upgradeSlotId += j2
                  ) {
                     class_1735 upgradeSlot = this.upgradeSlots.get(upgradeSlotId);
                     if (upgradeSlot.method_7681()
                        && canItemQuickReplace(upgradeSlot, carriedStack)
                        && upgradeSlot.method_7674(this.player)
                        && this.method_7613(carriedStack, upgradeSlot)) {
                        class_1799 itemstack3 = upgradeSlot.method_7677();
                        if (j != 0 || itemstack3.method_7947() != itemstack3.method_7914()) {
                           int l = Math.min(carriedStack.method_7914() - carriedStack.method_7947(), itemstack3.method_7947());
                           class_1799 upgradeStack = upgradeSlot.method_7671(l);
                           carriedStack.method_7933(l);
                           if (upgradeStack.method_7960()) {
                              upgradeSlot.method_7673(class_1799.field_8037);
                           }

                           upgradeSlot.method_7667(this.player, upgradeStack);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean method_7613(class_1799 stack, class_1735 slot) {
      return this.isUpgradeSlot(slot.field_7874)
            && slot.method_7677().method_7909() instanceof IUpgradeItem<?> upgradeItem
            && upgradeItem.getInventoryColumnsTaken() > 0
         ? false
         : super.method_7613(stack, slot);
   }

   public void method_7595(class_1657 player) {
      for (class_1735 slot : this.upgradeSlots) {
         if (!(slot instanceof StorageContainerMenuBase.StorageUpgradeSlot)
            && this.isInventorySlotInUpgradeTab(player, slot)
            && this.shouldSlotItemBeDroppedFromStorage(slot)) {
            class_1799 slotStack = slot.method_7677();
            slot.method_7673(class_1799.field_8037);
            if (!player.method_7270(slotStack)) {
               player.method_7328(slotStack, false);
            }
         }
      }

      super.method_7595(player);
      if (!player.method_37908().field_9236) {
         this.removeOpenTabIfKeepOff();
      }
   }

   protected class_1799 mergeItemStack(
      class_1799 sourceStack, int startIndex, int endIndex, boolean reverseDirection, boolean transferMaxStackSizeFromSource, boolean runOverflowLogic
   ) {
      int i = startIndex;
      if (reverseDirection) {
         i = endIndex - 1;
      }

      class_1799 result = sourceStack.method_7972();
      int toTransfer = transferMaxStackSizeFromSource ? Math.min(result.method_7914(), result.method_7947()) : result.method_7947();
      if (runOverflowLogic || result.method_7946() || this.method_7611(startIndex).method_7675() > 64) {
         while (toTransfer > 0 && (reverseDirection ? i >= startIndex : i < endIndex)) {
            class_1735 slot = this.method_7611(i);
            if (slot.method_7680(result)) {
               class_1799 destStack = slot.method_7677();
               if (!destStack.method_7960() && class_1799.method_31577(result, destStack)) {
                  int j = destStack.method_7947() + toTransfer;
                  int maxSize = slot.method_7676(result);
                  if (j <= maxSize) {
                     result.method_7934(toTransfer);
                     class_1799 copy = destStack.method_7972();
                     copy.method_7939(j);
                     slot.method_7673(copy);
                     toTransfer = 0;
                     slot.method_7668();
                  } else if (destStack.method_7947() < maxSize) {
                     result.method_7934(maxSize - destStack.method_7947());
                     toTransfer -= maxSize - destStack.method_7947();
                     class_1799 copy = destStack.method_7972();
                     copy.method_7939(maxSize);
                     slot.method_7673(copy);
                     slot.method_7668();
                  }

                  if (runOverflowLogic && !result.method_7960()) {
                     class_1799 overflowResult = this.processOverflowLogic(result);
                     if (overflowResult != result) {
                        result.method_7939(overflowResult.method_7947());
                     }
                  }
               }
            }

            if (reverseDirection) {
               i--;
            } else {
               i++;
            }
         }
      }

      if (toTransfer > 0) {
         int firstIndex = reverseDirection ? endIndex - 1 : startIndex;
         int increment = reverseDirection ? -1 : 1;
         MemorySettingsCategory memory = this.storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class);

         for (int slotIndex = firstIndex; (reverseDirection ? slotIndex >= startIndex : slotIndex < endIndex) && toTransfer > 0; slotIndex += increment) {
            if (memory.isSlotSelected(slotIndex) && memory.matchesFilter(slotIndex, result)) {
               class_1735 slotx = this.method_7611(slotIndex);
               if (slotx.method_7680(result)) {
                  class_1799 destStack = slotx.method_7677();
                  if (destStack.method_7960()) {
                     slotx.method_7673(result.method_7971(slotx.method_7675()));
                     slotx.method_7668();
                     toTransfer = result.method_7947();
                  }
               }
            }
         }
      }

      if (toTransfer > 0) {
         if (reverseDirection) {
            i = endIndex - 1;
         } else {
            i = startIndex;
         }

         while (reverseDirection ? i >= startIndex : i < endIndex) {
            class_1735 destSlot = this.method_7611(i);
            class_1799 itemstack1 = destSlot.method_7677();
            if (itemstack1.method_7960() && destSlot.method_7680(result) && !(destSlot instanceof IFilterSlot)) {
               boolean errorMerging = false;
               if (toTransfer > destSlot.method_7675()) {
                  if (runOverflowLogic && this.processOverflowIfSlotWithSameItemFound(i, result, s -> {})) {
                     result.method_7934(result.method_7947());
                  } else if (this.isUpgradeSlot(i)) {
                     IUpgradeItem<?> upgradeItem = (IUpgradeItem<?>)result.method_7909();
                     int newColumnsTaken = upgradeItem.getInventoryColumnsTaken();
                     if (!this.needsSlotsThatAreOccupied(result, 0, newColumnsTaken)) {
                        destSlot.method_7673(result.method_7971(destSlot.method_7675()));
                        this.updateColumnsTaken(newColumnsTaken);
                     } else {
                        errorMerging = true;
                     }
                  } else {
                     destSlot.method_7673(result.method_7971(destSlot.method_7675()));
                  }
               } else if (this.isUpgradeSlot(i)) {
                  IUpgradeItem<?> upgradeItem = (IUpgradeItem<?>)result.method_7909();
                  int newColumnsTaken = upgradeItem.getInventoryColumnsTaken();
                  if (!this.needsSlotsThatAreOccupied(result, 0, newColumnsTaken)) {
                     destSlot.method_7673(result.method_7971(toTransfer));
                     this.updateColumnsTaken(newColumnsTaken);
                  } else {
                     errorMerging = true;
                  }
               } else if (runOverflowLogic && this.processOverflowIfSlotWithSameItemFound(i, result, s -> {})) {
                  result.method_7934(result.method_7947());
               } else {
                  destSlot.method_7673(result.method_7971(toTransfer));
               }

               if (!errorMerging) {
                  destSlot.method_7668();
                  break;
               }
            }

            if (reverseDirection) {
               i--;
            } else {
               i++;
            }
         }
      }

      return result;
   }

   protected boolean moveItemStackTo(class_1735 sourceSlot, class_1799 stack, int startIndex, int endIndex, boolean reverseDirection) {
      return this.mergeItemStack(sourceSlot, stack, startIndex, endIndex, reverseDirection, false);
   }

   protected boolean mergeItemStack(
      class_1735 sourceSlot, class_1799 sourceStack, int startIndex, int endIndex, boolean reverseDirection, boolean transferMaxStackSizeFromSource
   ) {
      class_1799 remaining = this.mergeItemStack(sourceStack, startIndex, endIndex, reverseDirection, transferMaxStackSizeFromSource, false);
      if (remaining.method_7947() != sourceStack.method_7947()) {
         sourceSlot.method_7673(remaining);
         return true;
      } else {
         return false;
      }
   }

   public void method_34248(class_5916 synchronizer) {
      if (this.player instanceof class_3222 serverPlayer) {
         super.method_34248(new HighStackCountSynchronizer(serverPlayer));
      } else {
         super.method_34248(synchronizer);
      }
   }

   public static boolean canItemQuickReplace(@Nullable class_1735 slot, class_1799 stack) {
      boolean flag = slot == null || !slot.method_7681();
      return !flag && class_1799.method_31577(stack, slot.method_7677()) ? slot.method_7677().method_7947() <= slot.method_7676(stack) : flag;
   }

   public class_1735 method_7611(int slotId) {
      if (slotId >= this.getInventorySlotsSize()) {
         int upgradeSlotId = slotId - this.getInventorySlotsSize();
         return (class_1735)(this.upgradeSlots.size() > upgradeSlotId ? this.upgradeSlots.get(upgradeSlotId) : DummySlot.INSTANCE);
      } else {
         return this.realInventorySlots.get(slotId);
      }
   }

   public void method_7619(int slotId, int stateId, class_1799 stack) {
      if (this.getTotalSlotsNumber() > slotId) {
         super.method_7619(slotId, stateId, stack);
      }
   }

   public void method_7623() {
      if (this.hasSomethingMessedWithStorage()) {
         this.player.method_7346();
      } else {
         this.method_34258();
         this.broadcastChangesIn(this.lastUpgradeSlots, this.remoteUpgradeSlots, this.upgradeSlots, this.getFirstUpgradeSlot());
         this.broadcastChangesIn(this.lastRealSlots, this.remoteRealSlots, this.realInventorySlots, 0);
         if (this.inventorySlotStackChanged) {
            this.inventorySlotStackChanged = false;
            this.sendAdditionalSlotInfo();
         }

         if (this.lastSettingsNbt == null || !this.lastSettingsNbt.equals(this.storageWrapper.getSettingsHandler().getNbt())) {
            this.lastSettingsNbt = this.storageWrapper.getSettingsHandler().getNbt().method_10553();
            this.sendStorageSettingsToClient();
            this.refreshInventorySlotsIfNeeded();
         }

         this.initialBroadcast = false;
      }
   }

   public Optional<class_1799> getVisibleStorageItem() {
      return this.storageItemSlotNumber != -1 ? Optional.of(this.method_7611(this.storageItemSlotNumber).method_7677()) : Optional.empty();
   }

   private void broadcastChangesIn(
      class_2371<class_1799> lastSlotsCollection, class_2371<class_1799> remoteSlotsCollection, List<class_1735> slotsCollection, int slotIndexOffset
   ) {
      for (int i = 0; i < slotsCollection.size(); i++) {
         class_1735 slot = slotsCollection.get(i);
         class_1799 itemstack = slot.method_7677();
         Supplier<class_1799> supplier = Suppliers.memoize(itemstack::method_7972);
         this.triggerSlotListeners(i, itemstack, supplier, lastSlotsCollection, slotIndexOffset, slot);
         this.synchronizeSlotToRemote(i, itemstack, supplier, remoteSlotsCollection, slotIndexOffset);
      }
   }

   private void synchronizeSlotToRemote(
      int slotIndex, class_1799 slotStack, Supplier<class_1799> slotStackCopy, class_2371<class_1799> remoteSlotsCollection, int slotIndexOffset
   ) {
      if (!this.field_29209) {
         class_1799 remoteStack = (class_1799)remoteSlotsCollection.get(slotIndex);
         if (!class_1799.method_7973(remoteStack, slotStack)) {
            class_1799 stackCopy = slotStackCopy.get();
            remoteSlotsCollection.set(slotIndex, stackCopy);
            if (this.isStorageInventorySlot(slotIndex + slotIndexOffset) && (remoteStack.method_7960() || slotStack.method_7960())) {
               this.inventorySlotStackChanged = true;
            }

            if (this.field_29208 != null) {
               this.field_29208.method_34261(this, slotIndex + slotIndexOffset, stackCopy);
            }
         }
      }
   }

   protected void refreshInventorySlotsIfNeeded() {
      Set<Integer> noSortSlotIndexes = this.getNoSortSlotIndexes();
      boolean needRefresh = false;
      if (this.getInventorySlotsSize() - this.field_7761.size() != noSortSlotIndexes.size()) {
         needRefresh = true;
      } else {
         for (class_1735 slot : this.realInventorySlots) {
            if (!this.field_7761.contains(slot) && !noSortSlotIndexes.contains(slot.field_7874)) {
               needRefresh = true;
               break;
            }
         }
      }

      if (needRefresh) {
         this.field_7761.clear();
         this.field_7764.clear();
         this.realInventorySlots.clear();
         this.lastRealSlots.clear();
         this.remoteRealSlots.clear();
         this.addStorageInventorySlots();
         this.addPlayerInventorySlots(this.player.method_31548(), this.storageItemSlotIndex, this.shouldLockStorageItemSlot);
      }
   }

   public class_2371<class_1799> method_7602() {
      class_2371<class_1799> list = class_2371.method_10211();
      this.realInventorySlots.forEach(slot -> list.add(slot.method_7677()));
      this.upgradeSlots.forEach(upgradeSlot -> list.add(upgradeSlot.method_7677()));
      return list;
   }

   public abstract boolean detectSettingsChangeAndReload();

   protected boolean shouldSlotItemBeDroppedFromStorage(class_1735 slot) {
      return false;
   }

   private boolean isInventorySlotInUpgradeTab(class_1657 player, class_1735 slot) {
      return slot.method_7674(player) && !(slot instanceof class_1734);
   }

   private void reloadUpgradeControl() {
      if (!this.isUpdatingFromPacket) {
         this.storageWrapper.removeOpenTabId();
      }

      this.removeUpgradeSettingsSlots();
      this.upgradeContainers.clear();
      this.addUpgradeSettingsContainers(this.player);
      this.onUpgradesChanged();
   }

   private void removeUpgradeSettingsSlots() {
      List<Integer> slotNumbersToRemove = new ArrayList<>();

      for (UpgradeContainerBase<?, ?> container : this.upgradeContainers.values()) {
         container.getSlots().forEach(slot -> {
            int upgradeSlotIndex = slot.field_7874 - this.getInventorySlotsSize();
            slotNumbersToRemove.add(upgradeSlotIndex);
            this.upgradeSlots.remove(slot);
         });
      }

      slotNumbersToRemove.sort(IntComparators.OPPOSITE_COMPARATOR);

      for (int slotNumber : slotNumbersToRemove) {
         this.lastUpgradeSlots.remove(slotNumber);
         this.remoteUpgradeSlots.remove(slotNumber);
      }
   }

   private void onUpgradesChanged() {
      if (this.upgradeChangeListener != null) {
         this.upgradeChangeListener.accept(this);
      }

      this.sendEmptySlotIcons();
      this.sendAdditionalSlotInfo();
   }

   @Override
   public void updateAdditionalSlotInfo(
      Set<Integer> inaccessibleSlots,
      Map<Integer, Integer> slotLimitOverrides,
      Set<Integer> infiniteSlots,
      Map<Integer, class_6880<class_1792>> slotFilterItems
   ) {
      this.inaccessibleSlots.clear();
      this.inaccessibleSlots.addAll(inaccessibleSlots);
      this.slotLimitOverrides.clear();
      this.slotLimitOverrides.putAll(slotLimitOverrides);
      this.infiniteSlots.clear();
      this.infiniteSlots.addAll(infiniteSlots);
      Set<Integer> noSort = this.getNoSortSlotIndexes();
      noSort.addAll(infiniteSlots);
      List<class_1735> slotsToMakeIntoNoSort = new ArrayList<>();
      List<class_1735> slotsToMakeSortable = new ArrayList<>();

      for (int i = 0; i < this.getNumberOfStorageInventorySlots(); i++) {
         class_1735 slot = this.realInventorySlots.get(i);
         if (noSort.contains(slot.field_7874) && this.field_7761.contains(slot)) {
            slotsToMakeIntoNoSort.add(slot);
         } else if (!noSort.contains(slot.field_7874) && !this.field_7761.contains(slot)) {
            slotsToMakeSortable.add(slot);
         }
      }

      slotsToMakeIntoNoSort.forEach(this.field_7761::remove);
      this.field_7761.addAll(slotsToMakeSortable);
      this.slotFilterItems.clear();
      slotFilterItems.forEach((slotx, item) -> this.slotFilterItems.put(slotx, new class_1799(item)));
   }

   @Override
   public void updateEmptySlotIcons(Map<class_2960, Set<Integer>> emptySlotIcons) {
      this.emptySlotIcons.clear();
      emptySlotIcons.forEach((textureName, slots) -> slots.forEach(slot -> this.emptySlotIcons.put(slot, new Pair(class_1723.field_21668, textureName))));
   }

   public class_1799 getSlotFilterItem(int slot) {
      return this.slotFilterItems.getOrDefault(slot, class_1799.field_8037);
   }

   public void updateSlotChangeError(UpgradeSlotChangeResult result) {
      this.errorUpgradeSlotChangeResult = result;
      this.showUpgradeSlotChangeError();
   }

   private void showUpgradeSlotChangeError() {
      if (this.errorUpgradeSlotChangeResult != null && !this.errorUpgradeSlotChangeResult.successful() && !this.tryingToMergeUpgrade) {
         if (this.player.method_37908().method_8608()) {
            this.errorResultExpirationTime = this.player.method_37908().method_8510() + 60L;
         } else if (this.player instanceof class_3222 serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new SyncSlotChangeErrorPayload(this.errorUpgradeSlotChangeResult));
         }
      }
   }

   public void transferItemsToPlayerInventory(boolean filterByContents) {
      PacketDistributor.sendToServer(new TransferItemsPayload(true, filterByContents));
   }

   public void transferItemsToStorage(boolean filterByContents) {
      PacketDistributor.sendToServer(new TransferItemsPayload(false, filterByContents));
   }

   public class StorageUpgradeSlot extends SlotItemHandler<UpgradeHandler> {
      private boolean wasEmpty = true;
      private final int slotIndex;

      public StorageUpgradeSlot(UpgradeHandler upgradeHandler, int slotIndex) {
         super(upgradeHandler, slotIndex, -15, 0);
         this.slotIndex = slotIndex;
      }

      public void method_7668() {
         super.method_7668();
         if (!StorageContainerMenuBase.this.isUpdatingFromPacket && this.wasEmpty != this.method_7677().method_7960()
            || this.updateWrappersAndCheckForReloadNeeded()) {
            StorageContainerMenuBase.this.reloadUpgradeControl();
            if (!StorageContainerMenuBase.this.isFirstLevelStorage()) {
               StorageContainerMenuBase.this.parentStorageWrapper.getUpgradeHandler().refreshUpgradeWrappers();
            }

            this.onUpgradeChanged();
         }

         this.wasEmpty = this.method_7677().method_7960();
      }

      @Override
      public void method_7673(@NotNull class_1799 stack) {
         super.method_7673(stack);
         this.wasEmpty = this.method_7677().method_7960();
      }

      protected void onUpgradeChanged() {
      }

      @Override
      public boolean method_7680(class_1799 stack) {
         if (!stack.method_7960() && this.getItemHandler().isItemValid(this.slotIndex, ItemVariant.of(stack), stack.method_7947())) {
            UpgradeSlotChangeResult result;
            if (this.method_7677().method_7960()) {
               result = ((IUpgradeItem)stack.method_7909())
                  .canAddUpgradeTo(
                     StorageContainerMenuBase.this.storageWrapper,
                     stack,
                     StorageContainerMenuBase.this.isFirstLevelStorage(),
                     StorageContainerMenuBase.this.player.method_37908().method_8608()
                  );
            } else {
               if (stack.method_7947() > 1) {
                  return false;
               }

               result = ((IUpgradeItem)this.method_7677().method_7909())
                  .canSwapUpgradeFor(
                     stack, this.slotIndex, StorageContainerMenuBase.this.storageWrapper, StorageContainerMenuBase.this.player.method_37908().method_8608()
                  );
            }

            StorageContainerMenuBase.this.updateSlotChangeError(result);
            return result.successful();
         } else {
            return false;
         }
      }

      @Override
      public boolean method_7674(class_1657 player) {
         boolean ret = super.method_7674(player);
         if (!ret) {
            return false;
         } else {
            UpgradeSlotChangeResult result = ((IUpgradeItem)this.method_7677().method_7909())
               .canRemoveUpgradeFrom(StorageContainerMenuBase.this.storageWrapper, player.method_37908().method_8608(), player);
            StorageContainerMenuBase.this.updateSlotChangeError(result);
            return result.successful();
         }
      }

      private boolean updateWrappersAndCheckForReloadNeeded() {
         int checkedContainersCount = 0;

         for (Entry<Integer, IUpgradeWrapper> slotWrapper : StorageContainerMenuBase.this.storageWrapper.getUpgradeHandler().getSlotWrappers().entrySet()) {
            UpgradeContainerBase<?, ?> container = StorageContainerMenuBase.this.upgradeContainers.get(slotWrapper.getKey());
            if (!slotWrapper.getValue().hideSettingsTab()) {
               if (container == null || container.getUpgradeWrapper().isEnabled() != slotWrapper.getValue().isEnabled()) {
                  return true;
               }

               if (container.getUpgradeWrapper() != slotWrapper.getValue()) {
                  if (container.getUpgradeWrapper().getUpgradeStack().method_7909() != slotWrapper.getValue().getUpgradeStack().method_7909()) {
                     return true;
                  }

                  container.setUpgradeWrapper(slotWrapper.getValue());
                  checkedContainersCount++;
               }
            } else if (container != null) {
               return true;
            }
         }

         return checkedContainersCount != StorageContainerMenuBase.this.upgradeContainers.size();
      }

      @Nullable
      public Pair<class_2960, class_2960> method_7679() {
         return new Pair(class_1723.field_21668, StorageContainerMenuBase.EMPTY_UPGRADE_SLOT_BACKGROUND);
      }
   }
}
