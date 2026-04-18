package net.p3pp3rf1y.sophisticatedcore.api;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_3611;
import net.minecraft.class_6862;

public interface IStorageFluidHandler extends Storage<FluidVariant> {
   default long simulateInsert(class_6862<class_3611> fluidTag, long maxFill, class_3611 fallbackFluid, @Nullable TransactionContext transaction) {
      Transaction simulateTransaction = Transaction.openNested(transaction);

      long var7;
      try {
         var7 = this.insert(fluidTag, maxFill, fallbackFluid, simulateTransaction);
      } catch (Throwable var10) {
         if (simulateTransaction != null) {
            try {
               simulateTransaction.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }
         }

         throw var10;
      }

      if (simulateTransaction != null) {
         simulateTransaction.close();
      }

      return var7;
   }

   default long insert(class_6862<class_3611> fluidTag, long maxFill, class_3611 fallbackFluid, TransactionContext ctx) {
      return this.insert(fluidTag, maxFill, fallbackFluid, ctx, false);
   }

   default long insert(class_6862<class_3611> fluidTag, long maxFill, class_3611 fallbackFluid, TransactionContext ctx, boolean ignoreInOutLimit) {
      for (StorageView<FluidVariant> view : this.nonEmptyViews()) {
         if (((FluidVariant)view.getResource()).getFluid().method_15785().method_15767(fluidTag)) {
            return this.insert((FluidVariant)view.getResource(), maxFill, ctx, ignoreInOutLimit);
         }
      }

      return this.insert(FluidVariant.of(fallbackFluid), maxFill, ctx, ignoreInOutLimit);
   }

   long insert(FluidVariant var1, long var2, TransactionContext var4, boolean var5);

   long extract(FluidVariant var1, long var2, TransactionContext var4, boolean var5);

   default FluidStack simulateExtract(class_6862<class_3611> fluidTag, long maxFill, @Nullable TransactionContext transaction) {
      Transaction simulateTransaction = Transaction.openNested(transaction);

      FluidStack var6;
      try {
         var6 = this.extract(fluidTag, maxFill, simulateTransaction);
      } catch (Throwable var9) {
         if (simulateTransaction != null) {
            try {
               simulateTransaction.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }
         }

         throw var9;
      }

      if (simulateTransaction != null) {
         simulateTransaction.close();
      }

      return var6;
   }

   default FluidStack extract(class_6862<class_3611> fluidTag, long maxDrain, TransactionContext ctx) {
      return this.extract(fluidTag, maxDrain, ctx, false);
   }

   FluidStack extract(class_6862<class_3611> var1, long var2, TransactionContext var4, boolean var5);

   FluidStack extract(int var1, TransactionContext var2, boolean var3);

   FluidStack extract(FluidStack var1, TransactionContext var2, boolean var3);
}
