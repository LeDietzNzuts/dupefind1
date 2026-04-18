package net.caffeinemc.mods.sodium.mixin.features.textures.animations.upload;

import net.minecraft.class_1011;
import net.minecraft.class_7764;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_7764.class)
public interface SpriteContentsAccessor {
   @Accessor("field_40540")
   class_1011[] getImages();
}
