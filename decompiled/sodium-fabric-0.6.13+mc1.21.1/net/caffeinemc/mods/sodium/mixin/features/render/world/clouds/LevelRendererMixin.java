package net.caffeinemc.mods.sodium.mixin.features.render.world.clouds;

import java.util.Objects;
import net.caffeinemc.mods.sodium.client.render.immediate.CloudRenderer;
import net.minecraft.class_310;
import net.minecraft.class_3300;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_638;
import net.minecraft.class_761;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_761.class)
public class LevelRendererMixin {
   @Shadow
   @Nullable
   private class_638 field_4085;
   @Shadow
   private int field_4073;
   @Shadow
   @Final
   private class_310 field_4088;
   @Unique
   private CloudRenderer cloudRenderer;

   @Inject(
      method = "method_3259(Lnet/minecraft/class_4587;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;FDDD)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5294;method_28108()F"),
      cancellable = true
   )
   public void renderClouds(class_4587 poseStack, Matrix4f matrix4f, Matrix4f projectionMatrix, float tickDelta, double x, double y, double z, CallbackInfo ci) {
      ci.cancel();
      if (this.cloudRenderer == null) {
         this.cloudRenderer = new CloudRenderer(this.field_4088.method_1478());
      }

      poseStack.method_22903();
      poseStack.method_34425(matrix4f);
      class_638 level = Objects.requireNonNull(this.field_4085);
      class_4184 camera = this.field_4088.field_1773.method_19418();
      this.cloudRenderer.render(camera, level, projectionMatrix, poseStack, this.field_4073, tickDelta);
      poseStack.method_22909();
   }

   @Inject(method = "method_14491(Lnet/minecraft/class_3300;)V", at = @At("RETURN"))
   private void onReload(class_3300 manager, CallbackInfo ci) {
      if (this.cloudRenderer != null) {
         this.cloudRenderer.reload(manager);
      }
   }

   @Inject(method = "method_3279()V", at = @At("RETURN"))
   private void onReload(CallbackInfo ci) {
      if (this.cloudRenderer != null) {
         this.cloudRenderer.destroy();
         this.cloudRenderer = null;
      }
   }

   @Inject(method = "close()V", at = @At("RETURN"))
   private void onClose(CallbackInfo ci) {
      if (this.cloudRenderer != null) {
         this.cloudRenderer.destroy();
         this.cloudRenderer = null;
      }
   }
}
