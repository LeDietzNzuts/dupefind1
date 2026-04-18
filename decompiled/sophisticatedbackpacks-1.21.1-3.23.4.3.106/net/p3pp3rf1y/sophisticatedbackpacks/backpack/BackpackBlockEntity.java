package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageFluidHandler;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.controller.ControllerBlockEntityBase;
import net.p3pp3rf1y.sophisticatedcore.controller.IControllableStorage;
import net.p3pp3rf1y.sophisticatedcore.fluid.EmptyFluidHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.CachedFailedInsertInventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.renderdata.TankPosition;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;
import team.reborn.energy.api.EnergyStorage;

public class BackpackBlockEntity extends class_2586 implements IControllableStorage {
   @Nullable
   private class_2338 controllerPos = null;
   private IBackpackWrapper backpackWrapper = IBackpackWrapper.Noop.INSTANCE;
   private boolean updateBlockRender = true;
   private boolean chunkBeingUnloaded = false;
   @Nullable
   private SlottedStackStorage externalItemHandler;
   @Nullable
   private IStorageFluidHandler externalFluidHandler;
   @Nullable
   private EnergyStorage externalEnergyStorage;

   public BackpackBlockEntity(class_2338 pos, class_2680 state) {
      super(ModBlocks.BACKPACK_TILE_TYPE.get(), pos, state);
   }

   public void setBackpack(class_1799 backpack) {
      this.backpackWrapper = BackpackWrapper.fromStack(backpack);
      this.backpackWrapper.setContentsChangeHandler(() -> {
         this.method_5431();
         this.updateBlockRender = false;
         WorldHelper.notifyBlockUpdate(this);
      });
      this.backpackWrapper.setInventorySlotChangeHandler(this::method_5431);
      this.backpackWrapper.setUpgradeCachesInvalidatedHandler(this::invalidateHandlers);
   }

   public void method_11014(class_2487 tag, class_7874 registries) {
      super.method_11014(tag, registries);
      this.setBackpackFromNbt(tag);
      if (tag.method_10545("updateBlockRender")) {
         if (tag.method_10577("updateBlockRender")) {
            WorldHelper.notifyBlockUpdate(this);
         }
      } else {
         this.loadControllerPos(tag);
         if (this.field_11863 != null && !this.field_11863.method_8608()) {
            this.removeControllerPos();
            this.tryToAddToController();
         }

         WorldHelper.notifyBlockUpdate(this);
      }
   }

   public void sophisticatedCore_onLoad() {
      super.sophisticatedCore_onLoad();
      this.registerWithControllerOnLoad();
   }

   private void setBackpackFromNbt(class_2487 nbt) {
      RegistryHelper.getRegistryAccess()
         .ifPresent(registryAccess -> this.setBackpack(class_1799.method_57359(registryAccess, nbt.method_10562("backpackData"))));
   }

   protected void method_11007(class_2487 tag, class_7874 registries) {
      super.method_11007(tag, registries);
      this.writeBackpack(tag, registries);
      this.saveControllerPos(tag);
   }

   private void writeBackpack(class_2487 ret, class_7874 registries) {
      class_1799 backpackCopy = this.backpackWrapper.getBackpack().method_7972();
      ret.method_10566("backpackData", backpackCopy.method_57358(registries));
   }

   public class_2487 method_16887(class_7874 registries) {
      class_2487 ret = super.method_16887(registries);
      this.writeBackpack(ret, registries);
      ret.method_10556("updateBlockRender", this.updateBlockRender);
      this.updateBlockRender = true;
      return ret;
   }

   @Nullable
   public class_2622 getUpdatePacket() {
      return class_2622.method_38585(this);
   }

   public IBackpackWrapper getBackpackWrapper() {
      return this.backpackWrapper;
   }

   private void invalidateHandlers() {
      this.sophisticatedCore_invalidateCapabilities();
      this.externalItemHandler = null;
      this.externalFluidHandler = null;
      this.externalEnergyStorage = null;
   }

   private boolean isBlockConnectionDisallowed(@Nullable class_2350 direction) {
      return direction != null
         && this.field_11863 != null
         && Config.SERVER
            .noConnectionBlocks
            .isBlockConnectionDisallowed(this.field_11863.method_8320(this.method_11016().method_10093(direction)).method_26204());
   }

   @Nullable
   public SlottedStackStorage getExternalItemHandler(@Nullable class_2350 direction) {
      if (this.isBlockConnectionDisallowed(direction)) {
         return null;
      } else {
         if (this.externalItemHandler == null) {
            this.externalItemHandler = new CachedFailedInsertInventoryHandler(
               () -> this.getBackpackWrapper().getInventoryForInputOutput(), () -> this.field_11863 != null ? this.field_11863.method_8510() : 0L
            );
         }

         return this.externalItemHandler;
      }
   }

   @Nullable
   public IStorageFluidHandler getExternalFluidHandler(@Nullable class_2350 direction) {
      if (this.isBlockConnectionDisallowed(direction)) {
         return null;
      } else {
         if (this.externalFluidHandler == null) {
            this.externalFluidHandler = this.getBackpackWrapper().getFluidHandler().map(IStorageFluidHandler.class::cast).orElse(EmptyFluidHandler.INSTANCE);
         }

         return this.externalFluidHandler;
      }
   }

   @Nullable
   public EnergyStorage getExternalEnergyStorage(@Nullable class_2350 direction) {
      if (this.isBlockConnectionDisallowed(direction)) {
         return null;
      } else {
         if (this.externalEnergyStorage == null) {
            this.externalEnergyStorage = this.getBackpackWrapper().getEnergyStorage().map(EnergyStorage.class::cast).orElse(EnergyStorage.EMPTY);
         }

         return this.externalEnergyStorage;
      }
   }

   public void refreshRenderState() {
      class_2680 state = this.method_11010();
      state = (class_2680)state.method_11657(BackpackBlock.LEFT_TANK, false);
      state = (class_2680)state.method_11657(BackpackBlock.RIGHT_TANK, false);
      RenderInfo renderInfo = this.backpackWrapper.getRenderInfo();

      for (TankPosition pos : renderInfo.getTankRenderInfos().keySet()) {
         if (pos == TankPosition.LEFT) {
            state = (class_2680)state.method_11657(BackpackBlock.LEFT_TANK, true);
         } else if (pos == TankPosition.RIGHT) {
            state = (class_2680)state.method_11657(BackpackBlock.RIGHT_TANK, true);
         }
      }

      state = (class_2680)state.method_11657(BackpackBlock.BATTERY, renderInfo.getBatteryRenderInfo().isPresent());
      class_1937 l = Objects.requireNonNull(this.field_11863);
      l.method_8501(this.field_11867, state);
      l.method_8452(this.field_11867, state.method_26204());
      WorldHelper.notifyBlockUpdate(this);
   }

   public static void serverTick(class_1937 level, class_2338 blockPos, BackpackBlockEntity backpackBlockEntity) {
      if (!level.field_9236) {
         backpackBlockEntity.backpackWrapper
            .getUpgradeHandler()
            .getWrappersThatImplement(ITickableUpgrade.class)
            .forEach(upgrade -> upgrade.tick(null, level, blockPos));
      }
   }

   public IStorageWrapper getStorageWrapper() {
      return this.backpackWrapper;
   }

   public void setControllerPos(class_2338 controllerPos) {
      this.controllerPos = controllerPos;
      this.method_5431();
   }

   public Optional<class_2338> getControllerPos() {
      return Optional.ofNullable(this.controllerPos);
   }

   public void removeControllerPos() {
      this.controllerPos = null;
   }

   public class_2338 getStorageBlockPos() {
      return this.method_11016();
   }

   public class_1937 getStorageBlockLevel() {
      return Objects.requireNonNull(this.method_10997());
   }

   public boolean canConnectStorages() {
      return false;
   }

   public void unregisterController() {
      super.unregisterController();
      this.backpackWrapper.unregisterOnSlotsChangeListener();
      this.backpackWrapper.unregisterOnInventoryHandlerRefreshListener();
   }

   public void registerController(ControllerBlockEntityBase controllerBlockEntity) {
      super.registerController(controllerBlockEntity);
      if (this.field_11863 != null && !this.field_11863.field_9236) {
         this.backpackWrapper.registerOnSlotsChangeListener(this::changeSlots);
         this.backpackWrapper.registerOnInventoryHandlerRefreshListener(this::registerInventoryStackListeners);
      }
   }

   public void sophisticatedCore_onChunkUnloaded() {
      super.sophisticatedCore_onChunkUnloaded();
      this.chunkBeingUnloaded = true;
   }

   public void method_11012() {
      if (!this.chunkBeingUnloaded && this.field_11863 != null) {
         this.removeFromController();
      }

      super.method_11012();
   }
}
