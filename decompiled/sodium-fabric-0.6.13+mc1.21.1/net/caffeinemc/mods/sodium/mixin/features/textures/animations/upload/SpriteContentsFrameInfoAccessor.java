package net.caffeinemc.mods.sodium.mixin.features.textures.animations.upload;

import net.minecraft.class_7764.class_5791;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_5791.class)
public interface SpriteContentsFrameInfoAccessor {
   @Accessor
   int getField_28475();

   @Accessor
   int getField_28476();
}
