package dev.architectury.mixin.fabric;

import net.minecraft.class_2404;
import net.minecraft.class_3609;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_2404.class)
public interface LiquidBlockAccessor {
   @Accessor("fluid")
   class_3609 getFluid();
}
