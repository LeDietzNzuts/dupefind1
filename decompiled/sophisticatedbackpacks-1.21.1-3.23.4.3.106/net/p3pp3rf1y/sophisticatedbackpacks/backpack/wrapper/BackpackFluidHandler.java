package net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_3611;
import net.minecraft.class_6862;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageFluidHandler;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.tank.TankUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.tank.TankUpgradeWrapper;

public class BackpackFluidHandler implements IStorageFluidHandler {
   private final IStorageWrapper backpackWrapper;

   public BackpackFluidHandler(IStorageWrapper backpackWrapper) {
      this.backpackWrapper = backpackWrapper;
   }

   @Nonnull
   private List<TankUpgradeWrapper> getAllTanks() {
      return this.backpackWrapper.getUpgradeHandler().getTypeWrappers(TankUpgradeItem.TYPE);
   }

   public long insert(FluidVariant resource, long maxFill, TransactionContext ctx, boolean ignoreInOutLimit) {
      long remaining = maxFill;

      for (TankUpgradeWrapper tank : this.getAllTanks()) {
         remaining -= tank.fill(resource, remaining, ctx, ignoreInOutLimit);
         if (remaining <= 0L) {
            return maxFill;
         }
      }

      return maxFill - remaining;
   }

   public long insert(FluidVariant resource, long maxFill, TransactionContext ctx) {
      return this.insert(resource, maxFill, ctx, false);
   }

   public FluidStack extract(class_6862<class_3611> resourceTag, long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      FluidStack drained = FluidStack.EMPTY;
      long toDrain = maxDrain;

      for (TankUpgradeWrapper tank : this.getAllTanks()) {
         if (drained.isEmpty() && tank.getContents().is(resourceTag) || FluidStack.isSameFluidSameComponents(tank.getContents(), drained)) {
            if (drained.isEmpty()) {
               drained = new FluidStack(tank.getContents().getFluid(), tank.drain(toDrain, ctx, ignoreInOutLimit));
            } else {
               drained.grow(tank.drain(toDrain, ctx, ignoreInOutLimit));
            }

            if (drained.getAmount() == maxDrain) {
               return drained;
            }

            toDrain = maxDrain - drained.getAmount();
         }
      }

      return drained;
   }

   public FluidStack extract(FluidStack resource, TransactionContext ctx, boolean ignoreInOutLimit) {
      long drained = 0L;
      long toDrain = resource.getAmount();

      for (TankUpgradeWrapper tank : this.getAllTanks()) {
         if (FluidStack.isSameFluidSameComponents(tank.getContents(), resource)) {
            drained += tank.drain(toDrain, ctx, ignoreInOutLimit);
            if (drained == resource.getAmount()) {
               return resource;
            }

            toDrain = resource.getAmount() - drained;
         }
      }

      return drained == 0L ? FluidStack.EMPTY : new FluidStack(resource.getFluid(), drained);
   }

   public FluidStack extract(int maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      for (TankUpgradeWrapper tank : this.getAllTanks()) {
         FluidStack drained = new FluidStack(tank.getResource(), tank.drain(maxDrain, ctx, ignoreInOutLimit));
         if (!drained.isEmpty()) {
            return drained;
         }
      }

      return FluidStack.EMPTY;
   }

   public long extract(FluidVariant resource, long maxAmount, TransactionContext ctx, boolean ignoreInOutLimit) {
      FluidStack stack = new FluidStack(resource, maxAmount);
      long remaining = maxAmount;

      for (TankUpgradeWrapper tank : this.getAllTanks()) {
         if (FluidStack.isSameFluidSameComponents(tank.getContents(), stack)) {
            remaining -= tank.drain(remaining, ctx, ignoreInOutLimit);
            if (remaining >= maxAmount) {
               return maxAmount;
            }
         }
      }

      return maxAmount - remaining;
   }

   public long extract(FluidVariant resource, long maxAmount, TransactionContext ctx) {
      return this.extract(resource, maxAmount, ctx, false);
   }

   public Iterator<StorageView<FluidVariant>> iterator() {
      return new CombinedStorage(this.getAllTanks()).iterator();
   }
}
