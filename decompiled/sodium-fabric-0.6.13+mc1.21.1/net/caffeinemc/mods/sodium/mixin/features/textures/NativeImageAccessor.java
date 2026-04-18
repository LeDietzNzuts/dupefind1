package net.caffeinemc.mods.sodium.mixin.features.textures;

import net.minecraft.class_1011;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1011.class)
public interface NativeImageAccessor {
   @Accessor
   long getField_4988();
}
