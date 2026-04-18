package dev.architectury.hooks.fluid;

import dev.architectury.hooks.fluid.fabric.FluidBucketHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1755;
import net.minecraft.class_3611;

public final class FluidBucketHooks {
   @ExpectPlatform
   @Transformed
   public static class_3611 getFluid(class_1755 item) {
      return FluidBucketHooksImpl.getFluid(item);
   }
}
