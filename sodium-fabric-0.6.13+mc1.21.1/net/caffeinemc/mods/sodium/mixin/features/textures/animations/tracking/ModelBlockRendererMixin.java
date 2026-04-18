package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4588;
import net.minecraft.class_777;
import net.minecraft.class_778;
import net.minecraft.class_4587.class_4665;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_778.class)
public class ModelBlockRendererMixin {
   @Inject(
      method = "method_23073(Lnet/minecraft/class_1920;Lnet/minecraft/class_2680;Lnet/minecraft/class_2338;Lnet/minecraft/class_4588;Lnet/minecraft/class_4587$class_4665;Lnet/minecraft/class_777;FFFFIIIII)V",
      at = @At("HEAD")
   )
   private void preRenderQuad(
      class_1920 level,
      class_2680 state,
      class_2338 pos,
      class_4588 vertexConsumer,
      class_4665 matrices,
      class_777 quad,
      float brightness0,
      float brightness1,
      float brightness2,
      float brightness3,
      int light0,
      int light1,
      int light2,
      int light3,
      int overlay,
      CallbackInfo ci
   ) {
      if (quad.method_35788() != null) {
         SpriteUtil.INSTANCE.markSpriteActive(quad.method_35788());
      }
   }
}
