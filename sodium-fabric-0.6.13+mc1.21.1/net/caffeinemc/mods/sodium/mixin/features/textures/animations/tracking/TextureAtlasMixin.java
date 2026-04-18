package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.minecraft.class_1058;
import net.minecraft.class_1059;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1059.class)
public class TextureAtlasMixin {
   @Inject(method = "method_4608(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1058;", at = @At("RETURN"))
   private void preReturnSprite(CallbackInfoReturnable<class_1058> cir) {
      class_1058 sprite = (class_1058)cir.getReturnValue();
      if (sprite != null) {
         SpriteUtil.INSTANCE.markSpriteActive(sprite);
      }
   }
}
