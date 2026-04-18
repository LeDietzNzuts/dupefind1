package net.caffeinemc.mods.sodium.mixin.core.render.texture;

import net.minecraft.class_1059;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1059.class)
public interface TextureAtlasAccessor {
   @Accessor
   int getField_43113();

   @Accessor
   int getField_43114();
}
