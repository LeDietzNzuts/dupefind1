package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.minecraft.class_1058;
import net.minecraft.class_3940;
import net.minecraft.class_4003;
import net.minecraft.class_4184;
import net.minecraft.class_4588;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4003.class)
public abstract class TextureSheetParticleMixin extends class_3940 {
   @Shadow
   protected class_1058 field_17886;
   @Unique
   private boolean shouldTickSprite;

   protected TextureSheetParticleMixin(class_638 level, double x, double y, double z) {
      super(level, x, y, z);
   }

   @Inject(method = "method_18141(Lnet/minecraft/class_1058;)V", at = @At("RETURN"))
   private void afterSetSprite(class_1058 sprite, CallbackInfo ci) {
      this.shouldTickSprite = sprite != null && SpriteUtil.INSTANCE.hasAnimation(sprite);
   }

   public void method_3074(class_4588 vertexConsumer, class_4184 camera, float tickDelta) {
      if (this.shouldTickSprite) {
         SpriteUtil.INSTANCE.markSpriteActive(this.field_17886);
      }

      super.method_3074(vertexConsumer, camera, tickDelta);
   }
}
