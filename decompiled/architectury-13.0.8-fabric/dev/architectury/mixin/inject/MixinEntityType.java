package dev.architectury.mixin.inject;

import dev.architectury.extensions.injected.InjectedEntityTypeExtension;
import net.minecraft.class_1299;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_1299.class)
public class MixinEntityType implements InjectedEntityTypeExtension {
}
