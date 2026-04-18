package net.p3pp3rf1y.sophisticatedcore.upgrades.tank;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import io.github.fabricators_of_create.porting_lib.transfer.callbacks.TransactionCallback;
import io.github.fabricators_of_create.porting_lib.transfer.fluid.SimpleFluidContent;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_9334;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IRenderedTankUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IStackableContentsUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;
import net.p3pp3rf1y.sophisticatedcore.util.ComponentItemHandler;

public class TankUpgradeWrapper
   extends UpgradeWrapperBase<TankUpgradeWrapper, TankUpgradeItem>
   implements IRenderedTankUpgrade,
   ITickableUpgrade,
   IStackableContentsUpgrade,
   SingleSlotStorage<FluidVariant> {
   public static final int INPUT_SLOT = 0;
   public static final int OUTPUT_SLOT = 1;
   private Consumer<IRenderedTankUpgrade.TankRenderInfo> updateTankRenderInfoCallback;
   private final TankUpgradeWrapper.TankComponentItemHandler inventory;
   private FluidStack contents;
   private long cooldownTime = 0L;

   protected TankUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.inventory = new TankUpgradeWrapper.TankComponentItemHandler(upgrade);
      this.contents = getContents(upgrade).copy();
   }

   public static SimpleFluidContent getContents(class_1799 upgrade) {
      return (SimpleFluidContent)upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.FLUID_CONTENTS, SimpleFluidContent.EMPTY);
   }

   private boolean isValidFluidItem(class_1799 stack, boolean isOutput) {
      return CapabilityHelper.getFromFluidHandler(stack, fluidHandler -> this.isValidFluidHandler(fluidHandler, isOutput), false);
   }

   private boolean isValidFluidHandler(Storage<FluidVariant> storage, boolean isOutput) {
      boolean tankEmpty = this.contents.isEmpty();

      for (StorageView<FluidVariant> view : storage) {
         FluidStack fluidInTank = new FluidStack(view);
         if (isOutput && (view.isResourceBlank() || !tankEmpty && FluidStack.isSameFluidSameComponents(fluidInTank, this.contents))
            || !isOutput && !view.isResourceBlank() && (tankEmpty || FluidStack.isSameFluidSameComponents(this.contents, fluidInTank))) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void setTankRenderInfoUpdateCallback(Consumer<IRenderedTankUpgrade.TankRenderInfo> updateTankRenderInfoCallback) {
      this.updateTankRenderInfoCallback = updateTankRenderInfoCallback;
   }

   @Override
   public void forceUpdateTankRenderInfo() {
      IRenderedTankUpgrade.TankRenderInfo renderInfo = new IRenderedTankUpgrade.TankRenderInfo();
      if (!this.contents.isEmpty()) {
         renderInfo.setFluid(this.contents);
         renderInfo.setFillRatio(Math.round((float)this.contents.getAmount() / (float)this.getTankCapacity() * 10.0F) / 10.0F);
      }

      this.updateTankRenderInfoCallback.accept(renderInfo);
   }

   public FluidStack getContents() {
      return this.contents;
   }

   public long getTankCapacity() {
      return this.upgradeItem.getTankCapacity(this.storageWrapper);
   }

   public SlottedStackStorage getInventory() {
      return this.inventory;
   }

   private long getMaxInOut() {
      return (int)Math.max(
         81000.0,
         (Integer)this.upgradeItem.getTankUpgradeConfig().maxInputOutput.get() * this.storageWrapper.getNumberOfSlotRows()
            * this.upgradeItem.getAdjustedStackMultiplier(this.storageWrapper)
            * 81.0
      );
   }

   public long fill(FluidVariant resource, long maxFill, TransactionContext ctx, boolean ignoreInOutLimit) {
      long capacity = this.getTankCapacity();
      if (this.contents.getAmount() < capacity && (this.contents.isEmpty() || resource.isOf(this.contents.getFluid()))) {
         long toFill = Math.min(capacity - this.contents.getAmount(), maxFill);
         if (!ignoreInOutLimit) {
            toFill = Math.min(this.getMaxInOut(), toFill);
         }

         long finalToFill = toFill;
         TransactionCallback.onSuccess(ctx, () -> {
            if (this.contents.isEmpty()) {
               this.contents = new FluidStack(resource, finalToFill);
            } else {
               this.contents.setAmount(this.contents.getAmount() + finalToFill);
            }

            this.serializeContents();
         });
         return toFill;
      } else {
         return 0L;
      }
   }

   private void serializeContents() {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.FLUID_CONTENTS, SimpleFluidContent.copyOf(this.contents));
      this.save();
      this.forceUpdateTankRenderInfo();
   }

   public long drain(long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      if (this.contents.isEmpty()) {
         return 0L;
      } else {
         long toDrain = Math.min(maxDrain, this.contents.getAmount());
         if (!ignoreInOutLimit) {
            toDrain = Math.min(this.getMaxInOut(), toDrain);
         }

         long finalToDrain = toDrain;
         TransactionCallback.onSuccess(ctx, () -> {
            if (finalToDrain == this.contents.getAmount()) {
               this.contents = FluidStack.EMPTY;
            } else {
               this.contents.setAmount(this.contents.getAmount() - finalToDrain);
            }

            this.serializeContents();
         });
         return toDrain;
      }
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (level.method_8510() >= this.cooldownTime) {
         AtomicBoolean didSomething = new AtomicBoolean(false);
         CapabilityHelper.runOnFluidHandler(
            this.inventory.getStackInSlot(0),
            (cic, fluidHandler) -> didSomething.set(this.drainHandler(cic, fluidHandler, stack -> this.inventory.setStackInSlotWithoutValidation(0, stack)))
         );
         CapabilityHelper.runOnFluidHandler(
            this.inventory.getStackInSlot(1),
            (cic, fluidHandler) -> didSomething.set(this.fillHandler(cic, fluidHandler, stack -> this.inventory.setStackInSlotWithoutValidation(1, stack)))
         );
         if (didSomething.get()) {
            this.cooldownTime = level.method_8510() + ((Integer)this.upgradeItem.getTankUpgradeConfig().autoFillDrainContainerCooldown.get()).intValue();
         }
      }
   }

   public boolean fillHandler(ContainerItemContext cic, Storage<FluidVariant> fluidHandler, Consumer<class_1799> updateContainerStack) {
      if (!this.contents.isEmpty() && this.isValidFluidHandler(fluidHandler, true)) {
         long filled = StorageUtil.simulateInsert(fluidHandler, this.contents.getVariant(), Math.min(81000L, this.contents.getAmount()), null);
         if (filled <= 0L) {
            return false;
         } else {
            Transaction ctx = Transaction.openOuter();

            try {
               long drained = this.drain(filled, ctx, false);
               fluidHandler.insert(this.contents.getVariant(), drained, ctx);
               ctx.commit();
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

            updateContainerStack.accept(cic.getItemVariant().toStack((int)cic.getAmount()));
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean drainHandler(ContainerItemContext cic, Storage<FluidVariant> fluidHandler, Consumer<class_1799> updateContainerStack) {
      if (this.isValidFluidHandler(fluidHandler, false)) {
         FluidVariant resource = this.contents.isEmpty() ? TransferUtil.getFirstFluid(fluidHandler).getVariant() : this.contents.getVariant();
         long extracted = this.contents.isEmpty()
            ? StorageUtil.simulateExtract(fluidHandler, resource, 81000L, null)
            : StorageUtil.simulateExtract(fluidHandler, resource, Math.min(81000L, this.getTankCapacity() - this.contents.getAmount()), null);
         if (extracted <= 0L) {
            return false;
         } else {
            Transaction ctx = Transaction.openOuter();

            try {
               long filled = this.fill(resource, extracted, ctx, false);
               fluidHandler.extract(resource, filled, ctx);
               ctx.commit();
            } catch (Throwable var11) {
               if (ctx != null) {
                  try {
                     ctx.close();
                  } catch (Throwable var10) {
                     var11.addSuppressed(var10);
                  }
               }

               throw var11;
            }

            if (ctx != null) {
               ctx.close();
            }

            updateContainerStack.accept(cic.getItemVariant().toStack((int)cic.getAmount()));
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public int getMinimumMultiplierRequired() {
      return (int)Math.ceil((float)this.contents.getAmount() / (float)this.upgradeItem.getBaseCapacity(this.storageWrapper));
   }

   @Override
   public boolean canBeDisabled() {
      return false;
   }

   public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
      return this.fill(resource, maxAmount, transaction, false);
   }

   public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
      return this.contents != null && resource.isOf(this.contents.getFluid()) ? this.drain(maxAmount, transaction, false) : 0L;
   }

   public boolean isResourceBlank() {
      return this.contents == null || this.contents.isEmpty();
   }

   public FluidVariant getResource() {
      return this.contents.isEmpty() ? FluidVariant.blank() : this.contents.getVariant();
   }

   public long getAmount() {
      return this.contents.getAmount();
   }

   public long getCapacity() {
      return this.getMaxInOut();
   }

   private class TankComponentItemHandler extends ComponentItemHandler {
      public TankComponentItemHandler(class_1799 upgrade) {
         super(upgrade, class_9334.field_49622, 2);
      }

      @Override
      protected void onContentsChanged(int slot, class_1799 oldStack, class_1799 newStack) {
         super.onContentsChanged(slot, oldStack, newStack);
         TankUpgradeWrapper.this.save();
      }

      @Override
      public boolean isItemValid(int slot, @Nonnull class_1799 stack) {
         if (slot == 0) {
            return stack.method_7960() || this.isValidInputItem(stack);
         } else {
            return slot != 1 ? false : stack.method_7960() || this.isValidOutputItem(stack);
         }
      }

      private boolean isValidInputItem(class_1799 stack) {
         return TankUpgradeWrapper.this.isValidFluidItem(stack, false);
      }

      private boolean isValidOutputItem(class_1799 stack) {
         return TankUpgradeWrapper.this.isValidFluidItem(stack, true);
      }

      @Override
      public int getSlotLimit(int slot) {
         return 1;
      }

      public void setStackInSlotWithoutValidation(int slot, class_1799 stack) {
         super.updateContents(this.getContents(), stack, slot);
      }
   }
}
