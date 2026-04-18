package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import java.util.List;
import net.minecraft.class_7764.class_5790;
import net.minecraft.class_7764.class_5791;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_5790.class)
public interface AnimatedTextureAccessor {
   @Accessor("field_28472")
   List<class_5791> getFrames();
}
