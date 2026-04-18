package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.minecraft.class_1058;
import net.minecraft.class_332;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_332.class)
public class GuiGraphicsMixin {
   @Inject(method = "method_25298(IIIIILnet/minecraft/class_1058;)V", at = @At("HEAD"))
   private void preDrawSprite(int x, int y, int z, int width, int height, class_1058 sprite, CallbackInfo ci) {
      SpriteUtil.INSTANCE.markSpriteActive(sprite);
   }

   @Inject(method = "method_48465(IIIIILnet/minecraft/class_1058;FFFF)V", at = @At("HEAD"))
   private void preDrawSprite(int x, int y, int z, int width, int height, class_1058 sprite, float red, float green, float blue, float alpha, CallbackInfo ci) {
      SpriteUtil.INSTANCE.markSpriteActive(sprite);
   }
}
