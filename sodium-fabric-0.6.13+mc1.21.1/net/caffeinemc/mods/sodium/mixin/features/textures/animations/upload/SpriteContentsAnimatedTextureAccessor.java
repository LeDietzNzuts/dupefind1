package net.caffeinemc.mods.sodium.mixin.features.textures.animations.upload;

import java.util.List;
import net.minecraft.class_7764.class_5790;
import net.minecraft.class_7764.class_5791;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_5790.class)
public interface SpriteContentsAnimatedTextureAccessor {
   @Accessor
   List<class_5791> getField_28472();

   @Accessor
   int getField_28473();
}
