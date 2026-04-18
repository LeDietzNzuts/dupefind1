package dev.architectury.hooks.fluid;

import dev.architectury.hooks.fluid.fabric.LiquidBlockHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_2404;
import net.minecraft.class_3609;

public final class LiquidBlockHooks {
   @ExpectPlatform
   @Transformed
   public static class_3609 getFluid(class_2404 block) {
      return LiquidBlockHooksImpl.getFluid(block);
   }
}
