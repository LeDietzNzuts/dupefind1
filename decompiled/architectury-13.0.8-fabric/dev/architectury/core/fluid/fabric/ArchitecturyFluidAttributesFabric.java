package dev.architectury.core.fluid.fabric;

import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.fluid.FluidStack;
import dev.architectury.hooks.fluid.fabric.FluidStackHooksFabric;
import java.util.Optional;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_3414;
import org.jetbrains.annotations.Nullable;

class ArchitecturyFluidAttributesFabric implements FluidVariantAttributeHandler {
   private final ArchitecturyFluidAttributes attributes;

   public ArchitecturyFluidAttributesFabric(ArchitecturyFluidAttributes attributes) {
      this.attributes = attributes;
   }

   public class_2561 getName(FluidVariant variant) {
      return this.attributes.getName(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount()));
   }

   public Optional<class_3414> getFillSound(FluidVariant variant) {
      return Optional.ofNullable(this.attributes.getFillSound(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount())));
   }

   public Optional<class_3414> getEmptySound(FluidVariant variant) {
      return Optional.ofNullable(this.attributes.getEmptySound(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount())));
   }

   public int getLuminance(FluidVariant variant) {
      return this.attributes.getLuminosity(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount()));
   }

   public int getTemperature(FluidVariant variant) {
      return this.attributes.getTemperature(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount()));
   }

   public int getViscosity(FluidVariant variant, @Nullable class_1937 world) {
      return this.attributes.getViscosity(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount()), world, null);
   }

   public boolean isLighterThanAir(FluidVariant variant) {
      return this.attributes.isLighterThanAir(FluidStackHooksFabric.fromFabric(variant, FluidStack.bucketAmount()));
   }
}
