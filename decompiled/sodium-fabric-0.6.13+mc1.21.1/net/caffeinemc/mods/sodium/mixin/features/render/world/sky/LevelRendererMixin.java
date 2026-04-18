package net.caffeinemc.mods.sodium.mixin.features.render.world.sky;

import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_761;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_761.class)
public abstract class LevelRendererMixin {
   @Shadow
   protected abstract boolean method_43788(class_4184 var1);

   @Inject(
      method = "method_3257(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;FLnet/minecraft/class_4184;ZLjava/lang/Runnable;)V",
      at = @At("HEAD"),
      cancellable = true
   )
   private void preRenderSky(Matrix4f matrix4f, Matrix4f matrix4f2, float f, class_4184 camera, boolean bl, Runnable runnable, CallbackInfo ci) {
      if (camera.method_19334() != class_5636.field_27888 || this.method_43788(camera)) {
         ci.cancel();
      }
   }
}
