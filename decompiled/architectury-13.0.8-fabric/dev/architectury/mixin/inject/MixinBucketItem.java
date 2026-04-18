package dev.architectury.mixin.inject;

import dev.architectury.extensions.injected.InjectedBucketItemExtension;
import net.minecraft.class_1755;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_1755.class)
public class MixinBucketItem implements InjectedBucketItemExtension {
}
