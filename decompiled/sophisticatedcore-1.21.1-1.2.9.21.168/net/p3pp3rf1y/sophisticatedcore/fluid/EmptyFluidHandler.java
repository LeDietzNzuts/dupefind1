package net.p3pp3rf1y.sophisticatedcore.fluid;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.Collections;
import java.util.Iterator;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_3611;
import net.minecraft.class_6862;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageFluidHandler;

public class EmptyFluidHandler implements IStorageFluidHandler {
   public static EmptyFluidHandler INSTANCE = new EmptyFluidHandler();

   @Override
   public long insert(FluidVariant resource, long maxFill, TransactionContext ctx, boolean ignoreInOutLimit) {
      return 0L;
   }

   @Override
   public long extract(FluidVariant resource, long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      return 0L;
   }

   @Override
   public FluidStack extract(class_6862<class_3611> resourceTag, long maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      return null;
   }

   @Override
   public FluidStack extract(int maxDrain, TransactionContext ctx, boolean ignoreInOutLimit) {
      return null;
   }

   @Override
   public FluidStack extract(FluidStack resource, TransactionContext ctx, boolean ignoreInOutLimit) {
      return null;
   }

   public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
      return 0L;
   }

   public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
      return 0L;
   }

   public Iterator<StorageView<FluidVariant>> iterator() {
      return Collections.emptyIterator();
   }
}
