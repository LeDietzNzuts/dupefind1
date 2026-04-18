package dev.architectury.mixin.inject;

import dev.architectury.extensions.injected.InjectedFluidExtension;
import net.minecraft.class_3611;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_3611.class)
public class MixinFluid implements InjectedFluidExtension {
}
