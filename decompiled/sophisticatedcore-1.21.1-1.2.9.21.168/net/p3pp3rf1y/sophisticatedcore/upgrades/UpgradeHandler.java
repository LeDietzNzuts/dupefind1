package net.p3pp3rf1y.sophisticatedcore.upgrades;

import io.github.fabricators_of_create.porting_lib.transfer.callbacks.TransactionCallback;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerSlot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.renderdata.TankPosition;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public class UpgradeHandler extends ItemStackHandler {
   public static final String UPGRADE_INVENTORY_TAG = "upgradeInventory";
   private final IStorageWrapper storageWrapper;
   private final Runnable contentsSaveHandler;
   private final Runnable onInvalidateUpgradeCaches;
   private final class_2487 contentsNbt;
   @Nullable
   private Runnable refreshCallBack = null;
   private final Map<Integer, IUpgradeWrapper> slotWrappers = new LinkedHashMap<>();
   private final Map<UpgradeType<? extends IUpgradeWrapper>, List<? extends IUpgradeWrapper>> typeWrappers = new HashMap<>();
   private boolean justSavingNbtChange = false;
   private boolean wrappersInitialized = false;
   private boolean typeWrappersInitialized = false;
   @Nullable
   private IUpgradeWrapperAccessor wrapperAccessor = null;
   private boolean persistent = true;
   private final Map<Class<? extends IUpgradeWrapper>, Consumer<? extends IUpgradeWrapper>> upgradeDefaultsHandlers = new HashMap<>();

   public UpgradeHandler(
      int numberOfUpgradeSlots, IStorageWrapper storageWrapper, class_2487 contentsNbt, Runnable contentsSaveHandler, Runnable onInvalidateUpgradeCaches
   ) {
      super(numberOfUpgradeSlots);
      this.contentsNbt = contentsNbt;
      this.storageWrapper = storageWrapper;
      this.contentsSaveHandler = contentsSaveHandler;
      this.onInvalidateUpgradeCaches = onInvalidateUpgradeCaches;
      RegistryHelper.getRegistryAccess().ifPresent(registryAccess -> this.deserializeNBT(registryAccess, contentsNbt.method_10562("upgradeInventory")));
      if (SophisticatedCore.isLogicalServerThread() && storageWrapper.getRenderInfo().getUpgradeItems().size() != this.getSlotCount()) {
         this.setRenderUpgradeItems();
      }
   }

   public <T extends IUpgradeWrapper> void registerUpgradeDefaultsHandler(Class<T> upgradeClass, Consumer<T> defaultsHandler) {
      this.upgradeDefaultsHandlers.put(upgradeClass, defaultsHandler);
   }

   public final boolean isItemValid(int slot, ItemVariant resource, int count) {
      return this.isItemValid(slot, resource.toStack(count));
   }

   public boolean isItemValid(int slot, @Nonnull class_1799 stack) {
      return stack.method_7960() || stack.method_7909() instanceof IUpgradeItem;
   }

   protected void onContentsChanged(int slot) {
      super.onContentsChanged(slot);
      if (this.persistent) {
         this.saveInventory();
         this.contentsSaveHandler.run();
      }

      if (!this.justSavingNbtChange) {
         this.refreshUpgradeWrappers();
         this.setRenderUpgradeItems();
      }
   }

   public void setRenderUpgradeItems() {
      List<class_1799> upgradeItems = new ArrayList<>();
      InventoryHelper.iterate(this, (upgradeSlot, upgrade) -> upgradeItems.add(upgrade.method_46651(1)));
      this.storageWrapper.getRenderInfo().setUpgradeItems(upgradeItems);
   }

   public void setSize(int size) {
      super.setSize(this.getSlotCount());
   }

   public void saveInventory() {
      RegistryHelper.getRegistryAccess().ifPresent(registryAccess -> this.contentsNbt.method_10566("upgradeInventory", this.serializeNBT(registryAccess)));
   }

   public void setPersistent(boolean persistent) {
      this.persistent = persistent;
   }

   public void setRefreshCallBack(Runnable refreshCallBack) {
      this.refreshCallBack = refreshCallBack;
   }

   public void removeRefreshCallback() {
      this.refreshCallBack = null;
   }

   private void initializeWrappers() {
      if (!this.wrappersInitialized) {
         this.wrappersInitialized = true;
         this.slotWrappers.clear();
         this.typeWrappers.clear();
         if (this.wrapperAccessor != null) {
            this.wrapperAccessor.clearCache();
         }

         InventoryHelper.iterate(this, (slot, upgrade) -> {
            if (!upgrade.method_7960() && upgrade.method_7909() instanceof IUpgradeItem) {
               UpgradeType<?> type = ((IUpgradeItem)upgrade.method_7909()).getType();
               IUpgradeWrapper wrapper = type.create(this.storageWrapper, upgrade, upgradeStack -> {
                  this.justSavingNbtChange = true;
                  this.setStackInSlot(slot, upgradeStack);
                  this.justSavingNbtChange = false;
               });
               this.setUpgradeDefaults(wrapper);
               this.slotWrappers.put(slot, wrapper);
            }
         });
         this.initRenderInfoCallbacks(false);
      }
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      StoragePreconditions.notBlankNotNegative(resource, maxAmount);
      long inserted = 0L;

      for (int slot = 0; slot < this.getSlotCount(); slot++) {
         inserted += this.insertSlot(slot, resource, maxAmount - inserted, transaction);
         if (inserted >= maxAmount) {
            return inserted;
         }
      }

      return inserted;
   }

   public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      Transaction inner = Transaction.openNested(ctx);

      long inserted;
      try {
         inserted = super.insertSlot(slot, resource, maxAmount, inner);
         TransactionCallback.onSuccess(ctx, () -> {
            if (SophisticatedCore.isLogicalServerThread() && inserted > 0L && maxAmount > 0L) {
               this.onUpgradeAdded(slot);
            }
         });
         inner.commit();
      } catch (Throwable var12) {
         if (inner != null) {
            try {
               inner.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }
         }

         throw var12;
      }

      if (inner != null) {
         inner.close();
      }

      return inserted;
   }

   private void onUpgradeAdded(int slot) {
      Map<Integer, IUpgradeWrapper> wrappers = this.getSlotWrappers();
      if (wrappers.containsKey(slot)) {
         wrappers.get(slot).onAdded();
      }
   }

   private void setUpgradeDefaults(IUpgradeWrapper wrapper) {
      this.getUpgradeDefaultsHandler(wrapper).accept(wrapper);
   }

   private <T extends IUpgradeWrapper> Consumer<T> getUpgradeDefaultsHandler(T wrapper) {
      return (Consumer<T>)this.upgradeDefaultsHandlers.getOrDefault(wrapper.getClass(), w -> {});
   }

   public void setStackInSlot(int slot, @Nonnull class_1799 stack) {
      class_1799 originalStack = this.getStackInSlot(slot);
      Map<Integer, IUpgradeWrapper> wrappers = this.getSlotWrappers();
      boolean itemsDiffer = !class_1799.method_31577(originalStack, stack);
      if (SophisticatedCore.isLogicalServerThread() && itemsDiffer && wrappers.containsKey(slot)) {
         wrappers.get(slot).onBeforeRemoved();
      }

      super.setStackInSlot(slot, stack);
      if (SophisticatedCore.isLogicalServerThread() && itemsDiffer) {
         this.onUpgradeAdded(slot);
      }
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      StoragePreconditions.notBlankNotNegative(resource, maxAmount);
      class_1792 item = resource.getItem();
      SortedSet<ItemStackHandlerSlot> slots = this.getSlotsContaining(item);
      if (slots.isEmpty()) {
         return 0L;
      } else {
         long extracted = 0L;

         for (ItemStackHandlerSlot slot : slots) {
            extracted += this.extractSlot(slot.getIndex(), resource, maxAmount - extracted, transaction);
            if (extracted >= maxAmount) {
               return extracted;
            }
         }

         return extracted;
      }
   }

   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      Transaction inner = Transaction.openNested(ctx);

      long extracted;
      try {
         extracted = super.extractSlot(slot, resource, maxAmount, inner);
         TransactionCallback.onSuccess(ctx, () -> {
            if (SophisticatedCore.isLogicalServerThread()) {
               class_1799 slotStack = this.getStackInSlot(slot);
               if (this.persistent && !slotStack.method_7960() && maxAmount == 1L) {
                  Map<Integer, IUpgradeWrapper> wrappers = this.getSlotWrappers();
                  if (wrappers.containsKey(slot)) {
                     wrappers.get(slot).onBeforeRemoved();
                  }
               }
            }
         });
         inner.commit();
      } catch (Throwable var12) {
         if (inner != null) {
            try {
               inner.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }
         }

         throw var12;
      }

      if (inner != null) {
         inner.close();
      }

      return extracted;
   }

   private void initializeTypeWrappers() {
      if (!this.typeWrappersInitialized) {
         this.initializeWrappers();
         this.typeWrappersInitialized = true;
         this.typeWrappers.clear();
         this.slotWrappers.values().forEach(wrapper -> {
            if (wrapper.getUpgradeStack().method_7909() instanceof IUpgradeItem<?> upgradeItem) {
               UpgradeType<?> type = upgradeItem.getType();
               if (wrapper.isEnabled()) {
                  this.addTypeWrapper(type, wrapper);
               }
            }
         });
         this.initRenderInfoCallbacks(false);
      }
   }

   private <T extends IUpgradeWrapper> void addTypeWrapper(UpgradeType<?> type, T wrapper) {
      this.typeWrappers.computeIfAbsent((UpgradeType<? extends IUpgradeWrapper>)type, t -> new ArrayList<>()).add(wrapper);
   }

   public <T extends IUpgradeWrapper> List<T> getTypeWrappers(UpgradeType<T> type) {
      this.initializeTypeWrappers();
      return (List<T>)this.typeWrappers.getOrDefault(type, Collections.emptyList());
   }

   public <T extends IUpgradeWrapper> boolean hasUpgrade(UpgradeType<T> type) {
      return !this.getTypeWrappers(type).isEmpty();
   }

   public <T> List<T> getWrappersThatImplement(Class<T> upgradeClass) {
      this.initializeWrappers();
      return this.getWrapperAccessor().getWrappersThatImplement(upgradeClass);
   }

   private IUpgradeWrapperAccessor getWrapperAccessor() {
      if (this.wrapperAccessor == null) {
         IUpgradeWrapperAccessor accessor = new UpgradeHandler.Accessor(this);

         for (IUpgradeAccessModifier upgrade : this.getListOfWrappersThatImplement(IUpgradeAccessModifier.class)) {
            accessor = upgrade.wrapAccessor(accessor);
         }

         this.wrapperAccessor = accessor;
      }

      return this.wrapperAccessor;
   }

   public <T> List<T> getWrappersThatImplementFromMainStorage(Class<T> upgradeClass) {
      this.initializeWrappers();
      return this.getWrapperAccessor().getWrappersThatImplementFromMainStorage(upgradeClass);
   }

   public <T> List<T> getListOfWrappersThatImplement(Class<T> uc) {
      List<T> ret = new ArrayList<>();

      for (IUpgradeWrapper wrapper : this.slotWrappers.values()) {
         if (wrapper.isEnabled() && uc.isInstance(wrapper)) {
            ret.add((T)wrapper);
         }
      }

      return ret;
   }

   public Map<Integer, IUpgradeWrapper> getSlotWrappers() {
      this.initializeWrappers();
      return this.slotWrappers;
   }

   public void copyTo(UpgradeHandler otherHandler) {
      InventoryHelper.copyTo(this, otherHandler);
   }

   public void refreshWrappersThatImplementAndTypeWrappers() {
      this.typeWrappersInitialized = false;
      if (this.wrapperAccessor != null) {
         this.wrapperAccessor.clearCache();
      }

      if (this.refreshCallBack != null) {
         this.refreshCallBack.run();
      }
   }

   public void refreshUpgradeWrappers() {
      this.wrappersInitialized = false;
      this.typeWrappersInitialized = false;
      if (this.wrapperAccessor != null) {
         this.wrapperAccessor.onBeforeDeconstruct();
         this.wrapperAccessor = null;
      }

      if (this.refreshCallBack != null) {
         this.refreshCallBack.run();
      }

      this.onInvalidateUpgradeCaches.run();
      this.initRenderInfoCallbacks(true);
   }

   private void initRenderInfoCallbacks(boolean forceUpdateRenderInfo) {
      RenderInfo renderInfo = this.storageWrapper.getRenderInfo();
      if (forceUpdateRenderInfo) {
         renderInfo.resetUpgradeInfo(true);
      }

      this.initTankRenderInfoCallbacks(forceUpdateRenderInfo, renderInfo);
      this.initBatteryRenderInfoCallbacks(forceUpdateRenderInfo, renderInfo);
   }

   private void initBatteryRenderInfoCallbacks(boolean forceUpdateRenderInfo, RenderInfo renderInfo) {
      this.getSlotWrappers().forEach((slot, wrapper) -> {
         if (wrapper instanceof IRenderedBatteryUpgrade batteryWrapper) {
            batteryWrapper.setBatteryRenderInfoUpdateCallback(renderInfo::setBatteryRenderInfo);
            if (forceUpdateRenderInfo) {
               batteryWrapper.forceUpdateBatteryRenderInfo();
            }
         }
      });
   }

   private void initTankRenderInfoCallbacks(boolean forceUpdateRenderInfo, RenderInfo renderInfo) {
      AtomicBoolean singleTankRight = new AtomicBoolean(false);
      List<IRenderedTankUpgrade> tankRenderWrappers = new ArrayList<>();
      int minRightSlot = this.getSlotCount() / 2;
      this.getSlotWrappers().forEach((slot, wrapper) -> {
         if (wrapper instanceof IRenderedTankUpgrade tankUpgrade) {
            tankRenderWrappers.add(tankUpgrade);
            if (slot >= minRightSlot) {
               singleTankRight.set(true);
            }
         }
      });
      TankPosition currentTankPos = tankRenderWrappers.size() == 1 && singleTankRight.get() ? TankPosition.RIGHT : TankPosition.LEFT;

      for (IRenderedTankUpgrade tankRenderWrapper : tankRenderWrappers) {
         TankPosition finalCurrentTankPos = currentTankPos;
         tankRenderWrapper.setTankRenderInfoUpdateCallback(tankRenderInfo -> renderInfo.setTankRenderInfo(finalCurrentTankPos, tankRenderInfo));
         if (forceUpdateRenderInfo) {
            tankRenderWrapper.forceUpdateTankRenderInfo();
         }

         currentTankPos = TankPosition.RIGHT;
      }
   }

   public void increaseSize(int diff) {
      class_2371<class_1799> previousStacks = class_2371.method_10212(
         class_1799.field_8037, IntStream.range(0, this.getSlotCount()).mapToObj(this::getStackInSlot).toArray(class_1799[]::new)
      );
      super.setSize(previousStacks.size() + diff);

      for (int slot = 0; slot < previousStacks.size() && slot < this.getSlotCount(); slot++) {
         ((UpgradeHandler.UpgradeHandlerSlot)this.getSlot(slot)).setInternalNewStack((class_1799)previousStacks.get(slot));
      }

      this.saveInventory();
      this.setRenderUpgradeItems();
   }

   public int getSlotLimit(int slot) {
      return 1;
   }

   protected ItemStackHandlerSlot makeSlot(int index, class_1799 stack) {
      return new UpgradeHandler.UpgradeHandlerSlot(index, this, stack);
   }

   private static class Accessor implements IUpgradeWrapperAccessor {
      private final Map<Class<?>, List<?>> interfaceWrappers = new HashMap<>();
      private final UpgradeHandler upgradeHandler;

      public Accessor(UpgradeHandler upgradeHandler) {
         this.upgradeHandler = upgradeHandler;
      }

      @Override
      public <T> List<T> getWrappersThatImplement(Class<T> upgradeClass) {
         return (List<T>)this.interfaceWrappers.computeIfAbsent(upgradeClass, this.upgradeHandler::getListOfWrappersThatImplement);
      }

      @Override
      public <T> List<T> getWrappersThatImplementFromMainStorage(Class<T> upgradeClass) {
         return (List<T>)this.interfaceWrappers.computeIfAbsent(upgradeClass, this.upgradeHandler::getListOfWrappersThatImplement);
      }

      @Override
      public void clearCache() {
         this.interfaceWrappers.clear();
      }
   }

   private static class UpgradeHandlerSlot extends ItemStackHandlerSlot {
      public UpgradeHandlerSlot(int index, UpgradeHandler handler, class_1799 initial) {
         super(index, handler, initial);
      }

      public long insert(ItemVariant insertedVariant, long maxAmount, TransactionContext ctx) {
         TransactionCallback.onSuccess(ctx, () -> this.onFinalCommit());
         return super.insert(insertedVariant, maxAmount, ctx);
      }

      public long extract(ItemVariant variant, long maxAmount, TransactionContext ctx) {
         long extracted = super.extract(variant, maxAmount, ctx);
         TransactionCallback.onSuccess(ctx, () -> this.onFinalCommit());
         return extracted;
      }

      public void setInternalNewStack(class_1799 stack) {
         super.setStack(stack);
      }
   }
}
