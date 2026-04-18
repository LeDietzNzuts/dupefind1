package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import net.minecraft.class_7764.class_5791;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_5791.class)
public interface SpriteContentsFrameInfoAccessor {
   @Accessor("field_28476")
   int getTime();
}
