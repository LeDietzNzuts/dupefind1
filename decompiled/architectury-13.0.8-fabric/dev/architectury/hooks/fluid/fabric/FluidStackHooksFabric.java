package dev.architectury.hooks.fluid.fabric;

import dev.architectury.fluid.FluidStack;
import dev.architectury.fluid.fabric.FluidStackImpl;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;

public final class FluidStackHooksFabric {
   private FluidStackHooksFabric() {
   }

   public static FluidStack fromFabric(StorageView<FluidVariant> storageView) {
      return fromFabric((FluidVariant)storageView.getResource(), storageView.getAmount());
   }

   public static FluidStack fromFabric(FluidVariant variant, long amount) {
      return FluidStackImpl.fromValue.apply(new FluidStackImpl.Pair(variant.getFluid(), variant.getComponents(), amount));
   }

   public static FluidVariant toFabric(FluidStack stack) {
      return ((FluidStackImpl.Pair)FluidStackImpl.toValue.apply(stack)).toVariant();
   }
}
