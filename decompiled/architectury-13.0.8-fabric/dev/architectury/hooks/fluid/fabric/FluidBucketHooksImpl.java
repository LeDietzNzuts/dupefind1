package dev.architectury.hooks.fluid.fabric;

import dev.architectury.mixin.fabric.BucketItemAccessor;
import net.minecraft.class_1755;
import net.minecraft.class_3611;

public class FluidBucketHooksImpl {
   public static class_3611 getFluid(class_1755 item) {
      return ((BucketItemAccessor)item).getContent();
   }
}
