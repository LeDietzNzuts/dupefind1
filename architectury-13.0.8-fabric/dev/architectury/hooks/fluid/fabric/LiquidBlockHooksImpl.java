package dev.architectury.hooks.fluid.fabric;

import dev.architectury.mixin.fabric.LiquidBlockAccessor;
import net.minecraft.class_2404;
import net.minecraft.class_3609;

public class LiquidBlockHooksImpl {
   public static class_3609 getFluid(class_2404 block) {
      return ((LiquidBlockAccessor)block).getFluid();
   }
}
