package net.caffeinemc.mods.sodium.mixin.features.textures.animations.tracking;

import java.util.List;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.render.texture.SpriteContentsExtension;
import net.minecraft.class_7764;
import net.minecraft.class_7764.class_4728;
import net.minecraft.class_7764.class_5790;
import net.minecraft.class_7764.class_5791;
import net.minecraft.class_7764.class_7765;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_7765.class)
public class SpriteContentsTickerMixin {
   @Shadow
   int field_40545;
   @Shadow
   @Final
   class_5790 field_40546;
   @Shadow
   int field_40544;
   @Unique
   private class_7764 parent;

   @Inject(method = "<init>(Lnet/minecraft/class_7764;Lnet/minecraft/class_7764$class_5790;Lnet/minecraft/class_7764$class_4728;)V", at = @At("RETURN"))
   public void assignParent(class_7764 spriteContents, class_5790 animation, class_4728 interpolation, CallbackInfo ci) {
      this.parent = spriteContents;
   }

   @Inject(method = "method_45824(II)V", at = @At("HEAD"), cancellable = true)
   private void preTick(CallbackInfo ci) {
      SpriteContentsExtension parent = (SpriteContentsExtension)this.parent;
      boolean onDemand = SodiumClientMod.options().performance.animateOnlyVisibleTextures;
      if (onDemand && !parent.sodium$isActive()) {
         this.field_40545++;
         List<class_5791> frames = ((AnimatedTextureAccessor)this.field_40546).getFrames();
         if (this.field_40545 >= ((SpriteContentsFrameInfoAccessor)frames.get(this.field_40544)).getTime()) {
            this.field_40544 = (this.field_40544 + 1) % frames.size();
            this.field_40545 = 0;
         }

         ci.cancel();
      }
   }

   @Inject(method = "method_45824(II)V", at = @At("TAIL"))
   private void postTick(CallbackInfo ci) {
      SpriteContentsExtension parent = (SpriteContentsExtension)this.parent;
      parent.sodium$setActive(false);
   }
}
