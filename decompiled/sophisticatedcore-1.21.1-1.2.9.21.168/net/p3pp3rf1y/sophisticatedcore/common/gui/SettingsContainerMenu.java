package net.p3pp3rf1y.sophisticatedcore.common.gui;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1712;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3917;
import net.minecraft.class_5916;
import net.minecraft.class_6880;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncAdditionalSlotInfoPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerClientDataPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncEmptySlotIconsPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncTemplateSettingsPayload;
import net.p3pp3rf1y.sophisticatedcore.settings.ISettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsContainerBase;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsHandler;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsTemplateStorage;
import net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay.ItemDisplaySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay.ItemDisplaySettingsContainer;
import net.p3pp3rf1y.sophisticatedcore.settings.main.MainSettingsContainer;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsContainer;
import net.p3pp3rf1y.sophisticatedcore.settings.nosort.NoSortSettingsContainer;
import net.p3pp3rf1y.sophisticatedcore.util.DummySlot;

public abstract class SettingsContainerMenu<S extends IStorageWrapper> extends class_1703 implements ISyncedContainer, IAdditionalSlotInfoMenu {
   private static final Map<String, SettingsContainerMenu.ISettingsContainerFactory<?, ?>> SETTINGS_CONTAINER_FACTORIES = new HashMap<>();
   protected final class_1657 player;
   protected final S storageWrapper;
   private final List<class_1735> storageInventorySlots = new ArrayList<>();
   public final class_2371<class_1799> lastGhostSlots = class_2371.method_10211();
   public final class_2371<class_1799> remoteGhostSlots = class_2371.method_10211();
   private final Map<String, SettingsContainerBase<?>> settingsContainers = new LinkedHashMap<>();
   private final TemplatePersistanceContainer templatePersistanceContainer;
   public final List<class_1735> ghostSlots = new ArrayList<>();
   private boolean inventorySlotStackChanged = false;
   private final Set<Integer> inaccessibleSlots = new HashSet<>();
   private final Map<Integer, class_1799> slotFilterItems = new HashMap<>();
   private final Map<Integer, Pair<class_2960, class_2960>> emptySlotIcons = new HashMap<>();

   protected SettingsContainerMenu(class_3917<?> menuType, int windowId, class_1657 player, S storageWrapper) {
      super(menuType, windowId);
      this.player = player;
      this.storageWrapper = storageWrapper;
      this.addStorageInventorySlots();
      this.addSettingsContainers();
      this.templatePersistanceContainer = new TemplatePersistanceContainer(this, player.method_37908().method_30349());
      if (FabricLoader.getInstance().isModLoaded("emi")) {
         this.field_7761.add(DummySlot.INSTANCE);
      }
   }

   public int getNumberOfStorageInventorySlots() {
      return this.storageWrapper.getInventoryHandler().getSlotCount();
   }

   public S getStorageWrapper() {
      return this.storageWrapper;
   }

   private void addSettingsContainers() {
      SettingsHandler settingsHandler = this.storageWrapper.getSettingsHandler();
      settingsHandler.getSettingsCategories().forEach((name, category) -> this.settingsContainers.put(name, instantiateContainer(this, name, category)));
   }

   private void addStorageInventorySlots() {
      InventoryHandler inventoryHandler = this.storageWrapper.getInventoryHandler();

      for (int slotIndex = 0; slotIndex < inventoryHandler.getSlotCount(); slotIndex++) {
         this.storageInventorySlots.add(this.method_7621(new SettingsContainerMenu.ViewOnlyStorageInventorySlot(inventoryHandler, slotIndex)));
      }
   }

   public int getColumnsTaken() {
      return this.storageWrapper.getColumnsTaken();
   }

   protected class_1735 method_7621(class_1735 slot) {
      slot.field_7874 = this.ghostSlots.size();
      this.ghostSlots.add(slot);
      this.lastGhostSlots.add(class_1799.field_8037);
      this.remoteGhostSlots.add(class_1799.field_8037);
      return slot;
   }

   public void method_7623() {
      for (int slot = 0; slot < this.ghostSlots.size(); slot++) {
         class_1799 itemstack = this.ghostSlots.get(slot).method_7677();
         Supplier<class_1799> supplier = Suppliers.memoize(itemstack::method_7972);
         this.triggerSlotListeners(slot, itemstack, supplier);
         this.synchronizeSlotToRemote(slot, itemstack, supplier);
      }

      if (this.inventorySlotStackChanged) {
         this.inventorySlotStackChanged = false;
         this.sendAdditionalSlotInfo();
      }
   }

   public void method_37420() {
      for (int slotIndex = 0; slotIndex < this.ghostSlots.size(); slotIndex++) {
         class_1799 itemstack = this.ghostSlots.get(slotIndex).method_7677();
         this.triggerSlotListeners(slotIndex, itemstack, itemstack::method_7972);
      }

      this.method_34252();
   }

   private void triggerSlotListeners(int slotIndex, class_1799 slotStack, Supplier<class_1799> slotStackCopy) {
      class_1799 itemstack = (class_1799)this.lastGhostSlots.get(slotIndex);
      if (!class_1799.method_7973(itemstack, slotStack)) {
         class_1799 itemstack1 = slotStackCopy.get();
         this.lastGhostSlots.set(slotIndex, itemstack1);

         for (class_1712 containerlistener : this.field_7765) {
            containerlistener.method_7635(this, slotIndex, itemstack1);
         }
      }
   }

   private void synchronizeSlotToRemote(int slotIndex, class_1799 slotStack, Supplier<class_1799> slotStackCopy) {
      if (!this.field_29209) {
         class_1799 remoteStack = (class_1799)this.remoteGhostSlots.get(slotIndex);
         if (!class_1799.method_7973(remoteStack, slotStack)) {
            class_1799 stackCopy = slotStackCopy.get();
            this.remoteGhostSlots.set(slotIndex, stackCopy);
            if (remoteStack.method_7960() || slotStack.method_7960()) {
               this.inventorySlotStackChanged = true;
            }

            if (this.field_29208 != null) {
               this.field_29208.method_34261(this, slotIndex, stackCopy);
            }
         }
      }
   }

   public int getNumberOfSlots() {
      return this.storageWrapper.getInventoryHandler().getSlotCount();
   }

   public void method_34252() {
      for (int slotIndex = 0; slotIndex < this.ghostSlots.size(); slotIndex++) {
         this.remoteGhostSlots.set(slotIndex, this.ghostSlots.get(slotIndex).method_7677().method_7972());
      }

      if (this.field_29208 != null) {
         this.field_29208.method_34263(this, this.remoteGhostSlots, this.field_29207, new int[0]);
      }

      if (this.player instanceof class_3222 serverPlayer) {
         SettingsTemplateStorage settingsTemplateStorage = SettingsTemplateStorage.get();
         PacketDistributor.sendToPlayer(
            serverPlayer,
            new SyncTemplateSettingsPayload(
               settingsTemplateStorage.getPlayerTemplates(serverPlayer), settingsTemplateStorage.getPlayerNamedTemplates(serverPlayer)
            )
         );
      }

      this.sendEmptySlotIcons();
      this.sendAdditionalSlotInfo();
   }

   public abstract void detectSettingsChangeAndReload();

   public void method_34248(class_5916 synchronizer) {
      if (this.player instanceof class_3222 serverPlayer && this.storageWrapper.getInventoryHandler().getStackSizeMultiplier() > 1.0) {
         super.method_34248(new HighStackCountSynchronizer(serverPlayer));
      } else {
         super.method_34248(synchronizer);
      }
   }

   public class_1735 method_7611(int slotId) {
      return this.ghostSlots.get(slotId);
   }

   public void onMemorizedStackAdded(int slotNumber) {
      this.storageWrapper.getInventoryHandler().onSlotFilterChanged(slotNumber);
      this.sendAdditionalSlotInfo();
   }

   public void onMemorizedStackRemoved(int slotNumber) {
      if (this.method_7611(slotNumber).method_7677().method_7960()) {
         this.storageWrapper.getSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class).itemChanged(slotNumber);
         this.storageWrapper.getInventoryHandler().onSlotFilterChanged(slotNumber);
         this.sendAdditionalSlotInfo();
      }
   }

   public void onMemorizedItemsChanged() {
      this.storageWrapper.getSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class).itemsChanged();
   }

   public boolean method_7597(class_1657 player) {
      return true;
   }

   public List<class_1735> getStorageInventorySlots() {
      return this.storageInventorySlots;
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("categoryName")) {
         String categoryName = data.method_10558("categoryName");
         if (this.settingsContainers.containsKey(categoryName)) {
            this.settingsContainers.get(categoryName).handlePacket(data);
         }
      } else if (data.method_10573("templatePersistance", 10)) {
         this.templatePersistanceContainer.handlePacket(data.method_10562("templatePersistance"));
      }
   }

   public void method_7593(int slotId, int dragType, class_1713 clickTypeIn, class_1657 player) {
   }

   public class_1799 method_7601(class_1657 player, int index) {
      return class_1799.field_8037;
   }

   public void forEachSettingsContainer(BiConsumer<String, ? super SettingsContainerBase<?>> consumer) {
      this.settingsContainers.forEach(consumer);
   }

   public class_1657 getPlayer() {
      return this.player;
   }

   public class_2338 getBlockPosition() {
      return class_2338.field_10980;
   }

   public <T extends ISettingsCategory<?>> Optional<T> getSelectedTemplatesCategory(Class<T> categoryClass) {
      return this.templatePersistanceContainer.getSelectedTemplate().map(selectedTemplate -> selectedTemplate.getTypeCategory(categoryClass));
   }

   public TemplatePersistanceContainer getTemplatePersistanceContainer() {
      return this.templatePersistanceContainer;
   }

   public void refreshTemplateSlots() {
      this.templatePersistanceContainer.refreshTemplateSlots();
   }

   public int getNumberOfRows() {
      return this.storageWrapper.getNumberOfSlotRows();
   }

   protected static <C extends ISettingsCategory<?>, T extends SettingsContainerBase<C>> void addFactory(
      String categoryName, SettingsContainerMenu.ISettingsContainerFactory<C, T> factory
   ) {
      SETTINGS_CONTAINER_FACTORIES.put(categoryName, factory);
   }

   private static <C extends ISettingsCategory<?>> SettingsContainerBase<C> instantiateContainer(
      SettingsContainerMenu<?> settingsContainer, String name, C category
   ) {
      return getSettingsContainerFactory(name).create(settingsContainer, name, category);
   }

   private static <C extends ISettingsCategory<?>, T extends SettingsContainerBase<C>> SettingsContainerMenu.ISettingsContainerFactory<C, T> getSettingsContainerFactory(
      String name
   ) {
      return (SettingsContainerMenu.ISettingsContainerFactory<C, T>)SETTINGS_CONTAINER_FACTORIES.get(name);
   }

   public void sendDataToServer(Supplier<class_2487> supplyData) {
      if (!this.isServer()) {
         class_2487 data = supplyData.get();
         PacketDistributor.sendToServer(new SyncContainerClientDataPayload(data));
      }
   }

   protected boolean isServer() {
      return !this.player.method_37908().field_9236;
   }

   public void sendAdditionalSlotInfo() {
      if (this.player instanceof class_3222 serverPlayer) {
         HashSet var6 = new HashSet();
         InventoryHandler inventoryHandler = this.storageWrapper.getInventoryHandler();
         HashMap slotFilterItems = new HashMap();

         for (int slot = 0; slot < inventoryHandler.getSlotCount(); slot++) {
            if (!inventoryHandler.isSlotAccessible(slot)) {
               var6.add(slot);
            }

            if (inventoryHandler.getFilterItem(slot) != class_1802.field_8162) {
               slotFilterItems.put(slot, inventoryHandler.getFilterItem(slot).method_40131());
            }
         }

         PacketDistributor.sendToPlayer(serverPlayer, new SyncAdditionalSlotInfoPayload(var6, Map.of(), Set.of(), slotFilterItems));
      }
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
      this.slotFilterItems.clear();
      slotFilterItems.forEach((slot, item) -> this.slotFilterItems.put(slot, new class_1799(item)));
   }

   @Override
   public void updateEmptySlotIcons(Map<class_2960, Set<Integer>> emptySlotIcons) {
      this.emptySlotIcons.clear();
      emptySlotIcons.forEach((textureName, slots) -> slots.forEach(slot -> this.emptySlotIcons.put(slot, new Pair(class_1723.field_21668, textureName))));
   }

   public void method_37449(int slot, class_1799 stack) {
      class_1799 previous = this.method_7611(slot).method_7677();
      super.method_37449(slot, stack);
      if (previous.method_7960() || stack.method_7960()) {
         this.inventorySlotStackChanged = true;
      }
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

   public class_1799 getSlotFilterItem(int slot) {
      return this.slotFilterItems.getOrDefault(slot, class_1799.field_8037);
   }

   static {
      addFactory("global", MainSettingsContainer::new);
      addFactory("no_sort", NoSortSettingsContainer::new);
      addFactory("memory", MemorySettingsContainer::new);
      addFactory("item_display", ItemDisplaySettingsContainer::new);
   }

   public interface ISettingsContainerFactory<C extends ISettingsCategory<?>, T extends SettingsContainerBase<C>> {
      T create(SettingsContainerMenu<?> var1, String var2, C var3);
   }

   private class ViewOnlyStorageInventorySlot extends SlotItemHandler {
      public ViewOnlyStorageInventorySlot(IItemHandlerSimpleInserter inventoryHandler, int slotIndex) {
         super(inventoryHandler, slotIndex, 0, 0);
      }

      @Override
      public boolean method_7674(class_1657 playerIn) {
         return false;
      }

      @Nullable
      public Pair<class_2960, class_2960> method_7679() {
         return SettingsContainerMenu.this.inaccessibleSlots.contains(this.sophisticatedCore_getSlotIndex())
            ? StorageContainerMenuBase.INACCESSIBLE_SLOT_BACKGROUND
            : SettingsContainerMenu.this.emptySlotIcons.getOrDefault(this.sophisticatedCore_getSlotIndex(), null);
      }
   }
}
