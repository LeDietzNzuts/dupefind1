package net.p3pp3rf1y.sophisticatedcore.upgrades.battery;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_9334;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedBatteryUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IStackableContentsUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.ComponentItemHandler;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleSidedEnergyContainer;

public class BatteryUpgradeWrapper
   extends UpgradeWrapperBase<BatteryUpgradeWrapper, BatteryUpgradeItem>
   implements IRenderedBatteryUpgrade,
   EnergyStorage,
   ITickableUpgrade,
   IStackableContentsUpgrade {
   public static final int INPUT_SLOT = 0;
   public static final int OUTPUT_SLOT = 1;
   private Consumer<IRenderedBatteryUpgrade.BatteryRenderInfo> updateTankRenderInfoCallback;
   private final BatteryUpgradeWrapper.BatteryComponentItemHandler inventory;
   private final SimpleSidedEnergyContainer energyStorage;

   protected BatteryUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.inventory = new BatteryUpgradeWrapper.BatteryComponentItemHandler(upgrade);
      this.energyStorage = new SimpleSidedEnergyContainer() {
         protected void onFinalCommit() {
            BatteryUpgradeWrapper.this.serializeEnergyStored();
         }

         public long getCapacity() {
            return BatteryUpgradeWrapper.this.getCapacity();
         }

         public long getMaxInsert(@Nullable class_2350 side) {
            return BatteryUpgradeWrapper.this.getMaxInOut();
         }

         public long getMaxExtract(@Nullable class_2350 side) {
            return BatteryUpgradeWrapper.this.getMaxInOut();
         }
      };
      this.energyStorage.amount = getEnergyStored(upgrade);
   }

   public static long getEnergyStored(class_1799 upgrade) {
      return (Long)upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.ENERGY_STORED, 0L);
   }

   public EnergyStorage getSideEnergyStorage(@Nullable class_2350 side) {
      return this.energyStorage.getSideStorage(side);
   }

   public long insert(long maxAmount, TransactionContext ctx) {
      return this.getSideEnergyStorage(null).insert(maxAmount, ctx);
   }

   private void serializeEnergyStored() {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.ENERGY_STORED, this.energyStorage.amount);
      this.save();
      this.forceUpdateBatteryRenderInfo();
   }

   public long extract(long maxAmount, TransactionContext ctx) {
      return this.getSideEnergyStorage(null).extract(maxAmount, ctx);
   }

   public long getAmount() {
      return this.energyStorage.amount;
   }

   public long getCapacity() {
      return this.upgradeItem.getMaxEnergyStored(this.storageWrapper);
   }

   public boolean supportsExtraction() {
      return true;
   }

   public boolean supportsInsertion() {
      return true;
   }

   private int getMaxInOut() {
      double stackMultiplier = this.upgradeItem.getAdjustedStackMultiplier(this.storageWrapper);
      int baseInOut = (Integer)this.upgradeItem.getBatteryUpgradeConfig().maxInputOutput.get() * this.storageWrapper.getNumberOfSlotRows();
      return stackMultiplier > Integer.MAX_VALUE / baseInOut ? Integer.MAX_VALUE : (int)(baseInOut * stackMultiplier);
   }

   private boolean isValidEnergyItem(class_1799 stack, boolean isOutput) {
      EnergyStorage energyStorage = (EnergyStorage)ContainerItemContext.withConstant(stack).find(EnergyStorage.ITEM);
      if (energyStorage == null) {
         return false;
      } else {
         return isOutput ? energyStorage.supportsInsertion() : energyStorage.supportsExtraction() && energyStorage.getAmount() > 0L;
      }
   }

   @Override
   public void setBatteryRenderInfoUpdateCallback(Consumer<IRenderedBatteryUpgrade.BatteryRenderInfo> updateTankRenderInfoCallback) {
      this.updateTankRenderInfoCallback = updateTankRenderInfoCallback;
   }

   @Override
   public void forceUpdateBatteryRenderInfo() {
      IRenderedBatteryUpgrade.BatteryRenderInfo batteryRenderInfo = new IRenderedBatteryUpgrade.BatteryRenderInfo(1.0F);
      batteryRenderInfo.setChargeRatio((float)this.getAmount() / (float)this.getCapacity());
      this.updateTankRenderInfoCallback.accept(batteryRenderInfo);
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (this.getAmount() < this.getCapacity()) {
         EnergyStorageUtil.move(
            (EnergyStorage)ContainerItemContext.ofSingleSlot(new BatteryUpgradeWrapper.EnergyStackWrapper(0)).find(EnergyStorage.ITEM),
            this.getSideEnergyStorage(null),
            Long.MAX_VALUE,
            null
         );
      }

      if (this.getAmount() > 0L) {
         EnergyStorageUtil.move(
            this.getSideEnergyStorage(null),
            (EnergyStorage)ContainerItemContext.ofSingleSlot(new BatteryUpgradeWrapper.EnergyStackWrapper(1)).find(EnergyStorage.ITEM),
            Long.MAX_VALUE,
            null
         );

         for (class_2350 side : class_2350.values()) {
            EnergyStorageUtil.move(
               this.getSideEnergyStorage(side),
               (EnergyStorage)EnergyStorage.SIDED.find(level, pos.method_10093(side), side.method_10153()),
               Long.MAX_VALUE,
               null
            );
         }
      }
   }

   public SlottedStackStorage getInventory() {
      return this.inventory;
   }

   @Override
   public int getMinimumMultiplierRequired() {
      return (int)Math.ceil((float)this.getAmount() / this.upgradeItem.getMaxEnergyBase(this.storageWrapper));
   }

   @Override
   public boolean canBeDisabled() {
      return false;
   }

   private class BatteryComponentItemHandler extends ComponentItemHandler {
      public BatteryComponentItemHandler(class_1799 upgrade) {
         super(upgrade, class_9334.field_49622, 2);
      }

      @Override
      protected void onContentsChanged(int slot, class_1799 oldStack, class_1799 newStack) {
         super.onContentsChanged(slot, oldStack, newStack);
         BatteryUpgradeWrapper.this.save();
      }

      @Override
      public boolean isItemValid(int slot, class_1799 stack) {
         if (slot == 0) {
            return stack.method_7960() || this.isValidInputItem(stack);
         } else {
            return slot != 1 ? false : stack.method_7960() || this.isValidOutputItem(stack);
         }
      }

      private boolean isValidInputItem(class_1799 stack) {
         return BatteryUpgradeWrapper.this.isValidEnergyItem(stack, false);
      }

      private boolean isValidOutputItem(class_1799 stack) {
         return BatteryUpgradeWrapper.this.isValidEnergyItem(stack, true);
      }

      @Override
      public int getSlotLimit(int slot) {
         return 1;
      }

      public void setStackInSlotWithoutValidation(int slot, class_1799 stack) {
         super.updateContents(this.getContents(), stack, slot);
      }
   }

   private class EnergyStackWrapper extends SingleStackStorage {
      private final int slot;

      public EnergyStackWrapper(int slot) {
         this.slot = slot;
      }

      protected class_1799 getStack() {
         return BatteryUpgradeWrapper.this.inventory.getStackInSlot(this.slot);
      }

      protected void setStack(class_1799 stack) {
         BatteryUpgradeWrapper.this.inventory.setStackInSlot(this.slot, stack);
      }
   }
}
