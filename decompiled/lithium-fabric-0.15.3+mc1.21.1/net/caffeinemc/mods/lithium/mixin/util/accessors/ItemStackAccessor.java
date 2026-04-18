package net.caffeinemc.mods.lithium.mixin.util.accessors;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1799.class)
public interface ItemStackAccessor {
   @Accessor("field_8038")
   class_1792 lithium$getItem();
}
