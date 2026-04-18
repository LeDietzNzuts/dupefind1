package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;
import net.minecraft.class_3611;
import net.minecraft.class_6862;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageFluidHandler;

public class InceptionFluidHandler implements IStorageFluidHandler {
   @Nullable
   private final IStorageFluidHandler wrappedFluidHandler;
   private final InventoryOrder inventoryOrder;
   private final SubBackpacksHandler subBackpacksHandler;
   private IStorageFluidHandler[] fluidHandlers;
   private final class_1799 backpack;

   public InceptionFluidHandler(
      @Nullable IStorageFluidHandler wrappedFluidHandler, class_1799 backpack, InventoryOrder inventoryOrder, SubBackpacksHandler subBackpacksHandler
   ) {
      this.wrappedFluidHandler = wrappedFluidHandler;
      this.backpack = backpack;
      this.inventoryOrder = inventoryOrder;
      this.subBackpacksHandler = subBackpacksHandler;
      subBackpacksHandler.addRefreshListener(sbs -> this.refreshHandlers());
      this.refreshHandlers();
   }

   private void refreshHandlers() {
      List<IStorageFluidHandler> handlers = new ArrayList<>();
      if (this.wrappedFluidHandler != null && this.inventoryOrder == InventoryOrder.MAIN_FIRST) {
         handlers.add(this.wrappedFluidHandler);
      }

      this.subBackpacksHandler.getSubBackpacks().forEach(sbp -> sbp.getFluidHandler().ifPresent(handlers::add));
      if (this.wrappedFluidHandler != null && this.inventoryOrder == InventoryOrder.INCEPTED_FIRST) {
         handlers.add(this.wrappedFluidHandler);
      }

      this.fluidHandlers = handlers.toArray(new IStorageFluidHandler[0]);
   }

   public long insert(FluidVariant resource, long maxFill, TransactionContext ctx, boolean ignoreInOutLimit) {
      long remaining = maxFill;

      for (IStorageFluidHandler fluidHandler : this.fluidHandlers) {
         remaining -= fluidHandler.insert(resource, remaining, ctx, ignoreInOutLimit);
         if (remaining <= 0L) {
            return maxFill;
         }
      }

      return maxFill - remaining;
   }

   public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
      return this.insert(resource, maxAmount, transaction, false);
   }

   public FluidStack extract(class_6862<class_3611> resourceTag, long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      FluidStack drainedStack = FluidStack.EMPTY;
      FluidStack stackToDrain = FluidStack.EMPTY;

      for (IStorageFluidHandler fluidHandler : this.fluidHandlers) {
         if (drainedStack.isEmpty()) {
            drainedStack = fluidHandler.extract(resourceTag, maxDrain, ctx, ignoreInOutLimit);
            if (drainedStack.getAmount() == maxDrain) {
               return drainedStack;
            }

            if (!drainedStack.isEmpty()) {
               stackToDrain = new FluidStack(drainedStack.getFluid(), maxDrain - drainedStack.getAmount());
            }
         } else {
            long amountDrained = fluidHandler.extract(stackToDrain, ctx, ignoreInOutLimit).getAmount();
            stackToDrain.shrink(amountDrained);
            drainedStack.grow(amountDrained);
            if (drainedStack.getAmount() == maxDrain) {
               return drainedStack;
            }
         }
      }

      return drainedStack;
   }

   public FluidStack extract(FluidStack resource, TransactionContext ctx, boolean ignoreInOutLimit) {
      long drained = 0L;
      FluidStack toDrain = resource;

      for (IStorageFluidHandler fluidHandler : this.fluidHandlers) {
         drained += fluidHandler.extract(toDrain, ctx, ignoreInOutLimit).getAmount();
         if (drained == resource.getAmount()) {
            return resource;
         }

         toDrain = new FluidStack(toDrain.getFluid(), resource.getAmount() - drained);
      }

      return drained == 0L ? FluidStack.EMPTY : new FluidStack(resource.getFluid(), drained);
   }

   @Nonnull
   public FluidStack extract(int maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      for (IStorageFluidHandler fluidHandler : this.fluidHandlers) {
         FluidStack drained = fluidHandler.extract(maxDrain, ctx, ignoreInOutLimit);
         if (!drained.isEmpty()) {
            return drained;
         }
      }

      return FluidStack.EMPTY;
   }

   public long extract(FluidVariant resource, long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      long remaining = maxDrain;

      for (IStorageFluidHandler fluidHandler : this.fluidHandlers) {
         remaining -= fluidHandler.extract(resource, remaining, ctx, ignoreInOutLimit);
         if (remaining <= 0L) {
            return maxDrain;
         }
      }

      return maxDrain - remaining;
   }

   @Nonnull
   public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
      return this.extract(resource, maxAmount, transaction, false);
   }

   public Iterator<StorageView<FluidVariant>> iterator() {
      return new CombinedStorage(List.of(this.fluidHandlers)).iterator();
   }
}
