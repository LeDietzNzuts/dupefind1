package dev.architectury.extensions.injected;

import dev.architectury.hooks.fluid.FluidBucketHooks;
import net.minecraft.class_1755;
import net.minecraft.class_3611;

public interface InjectedBucketItemExtension {
   default class_3611 arch$getFluid() {
      return FluidBucketHooks.getFluid((class_1755)this);
   }
}
