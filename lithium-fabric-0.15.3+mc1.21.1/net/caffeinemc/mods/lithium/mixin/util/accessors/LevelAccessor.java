package net.caffeinemc.mods.lithium.mixin.util.accessors;

import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1937.class)
public interface LevelAccessor {
   @Accessor
   Thread getField_17086();
}
