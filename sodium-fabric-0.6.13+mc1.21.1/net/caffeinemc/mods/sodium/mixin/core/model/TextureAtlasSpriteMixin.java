package net.caffeinemc.mods.sodium.mixin.core.model;

import net.minecraft.class_1058;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_1058.class)
public class TextureAtlasSpriteMixin {
   @Overwrite
   public float method_23842() {
      return 0.0F;
   }
}
