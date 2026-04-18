package dev.architectury.extensions.injected;

import dev.architectury.hooks.fluid.LiquidBlockHooks;
import net.minecraft.class_2404;
import net.minecraft.class_3609;

public interface InjectedLiquidBlockExtension {
   default class_3609 arch$getFluid() {
      return LiquidBlockHooks.getFluid((class_2404)this);
   }
}
