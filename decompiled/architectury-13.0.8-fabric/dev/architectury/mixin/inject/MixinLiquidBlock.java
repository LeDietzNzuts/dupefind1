package dev.architectury.mixin.inject;

import dev.architectury.extensions.injected.InjectedLiquidBlockExtension;
import net.minecraft.class_2404;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_2404.class)
public class MixinLiquidBlock implements InjectedLiquidBlockExtension {
}
