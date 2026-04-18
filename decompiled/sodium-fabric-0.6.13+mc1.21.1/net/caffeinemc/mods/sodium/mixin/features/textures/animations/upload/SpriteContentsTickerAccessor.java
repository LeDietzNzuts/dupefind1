package net.caffeinemc.mods.sodium.mixin.features.textures.animations.upload;

import net.minecraft.class_7764.class_5790;
import net.minecraft.class_7764.class_7765;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_7765.class)
public interface SpriteContentsTickerAccessor {
   @Accessor
   class_5790 getField_40546();

   @Accessor("field_40544")
   int getFrameIndex();

   @Accessor("field_40545")
   int getFrameTicks();
}
