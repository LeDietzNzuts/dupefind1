package net.raphimc.immediatelyfast.injection.mixins.hud_batching.consumer;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597.class_4598;
import net.minecraft.class_5253.class_5254;
import net.raphimc.immediatelyfast.feature.batching.BatchingRenderLayers;
import net.raphimc.immediatelyfast.feature.batching.BlendFuncDepthFuncState;
import net.raphimc.immediatelyfast.feature.batching.HudBatchingBufferSource;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_332.class, priority = 1500)
public abstract class MixinDrawContext {
   @Shadow
   @Final
   private class_4587 field_44657;
   @Shadow
   @Final
   private class_310 field_44656;
   @Shadow
   @Mutable
   public class_4598 field_44658;

   @ModifyVariable(method = "method_48196(Lnet/minecraft/class_1921;IIIIII)V", at = @At("HEAD"), index = 6, argsOnly = true)
   private int mixColor(int color) {
      return this.field_44658 instanceof HudBatchingBufferSource ? this.immediatelyFast$mixWithShaderColor(color) : color;
   }

   @ModifyVariable(method = "method_51740(Lnet/minecraft/class_1921;IIIIIII)V", at = @At("HEAD"), index = 5, argsOnly = true)
   private int mixStartColor(int color) {
      return this.field_44658 instanceof HudBatchingBufferSource ? this.immediatelyFast$mixWithShaderColor(color) : color;
   }

   @ModifyVariable(method = "method_51740(Lnet/minecraft/class_1921;IIIIIII)V", at = @At("HEAD"), index = 6, argsOnly = true)
   private int mixEndColor(int color) {
      return this.field_44658 instanceof HudBatchingBufferSource ? this.immediatelyFast$mixWithShaderColor(color) : color;
   }

   @Inject(method = "method_25295(Lnet/minecraft/class_2960;IIIIIFFFF)V", at = @At("HEAD"), cancellable = true)
   private void drawTexturedQuadIntoBuffer(class_2960 texture, int x1, int x2, int y1, int y2, int z, float u1, float u2, float v1, float v2, CallbackInfo ci) {
      if (this.field_44658 instanceof HudBatchingBufferSource) {
         ci.cancel();
         Matrix4f matrix = this.field_44657.method_23760().method_23761();
         float[] shaderColor = RenderSystem.getShaderColor();
         int r = class_3532.method_15340((int)(shaderColor[0] * 255.0F), 0, 255);
         int g = class_3532.method_15340((int)(shaderColor[1] * 255.0F), 0, 255);
         int b = class_3532.method_15340((int)(shaderColor[2] * 255.0F), 0, 255);
         int a = class_3532.method_15340((int)(shaderColor[3] * 255.0F), 0, 255);
         if (r == 255 && g == 255 && b == 255 && a == 255) {
            class_4588 vertexConsumer = this.field_44658
               .getBuffer(
                  BatchingRenderLayers.TEXTURE.apply(this.field_44656.method_1531().method_4619(texture).method_4624(), BlendFuncDepthFuncState.current())
               );
            vertexConsumer.method_22918(matrix, x1, y2, z).method_22913(u1, v2);
            vertexConsumer.method_22918(matrix, x2, y2, z).method_22913(u2, v2);
            vertexConsumer.method_22918(matrix, x2, y1, z).method_22913(u2, v1);
            vertexConsumer.method_22918(matrix, x1, y1, z).method_22913(u1, v1);
         } else {
            class_4588 vertexConsumer = this.field_44658
               .getBuffer(
                  BatchingRenderLayers.COLORED_TEXTURE
                     .apply(this.field_44656.method_1531().method_4619(texture).method_4624(), BlendFuncDepthFuncState.current())
               );
            vertexConsumer.method_22918(matrix, x1, y2, z).method_22913(u1, v2).method_1336(r, g, b, a);
            vertexConsumer.method_22918(matrix, x2, y2, z).method_22913(u2, v2).method_1336(r, g, b, a);
            vertexConsumer.method_22918(matrix, x2, y1, z).method_22913(u2, v1).method_1336(r, g, b, a);
            vertexConsumer.method_22918(matrix, x1, y1, z).method_22913(u1, v1).method_1336(r, g, b, a);
         }
      }
   }

   @Inject(method = "method_48466(Lnet/minecraft/class_2960;IIIIIFFFFFFFF)V", at = @At("HEAD"), cancellable = true)
   private void drawTexturedQuadIntoBuffer(
      class_2960 texture,
      int x1,
      int x2,
      int y1,
      int y2,
      int z,
      float u1,
      float u2,
      float v1,
      float v2,
      float red,
      float green,
      float blue,
      float alpha,
      CallbackInfo ci
   ) {
      if (this.field_44658 instanceof HudBatchingBufferSource) {
         ci.cancel();
         Matrix4f matrix = this.field_44657.method_23760().method_23761();
         int color = this.immediatelyFast$mixWithShaderColor(
            (int)(alpha * 255.0F) << 24 | (int)(red * 255.0F) << 16 | (int)(green * 255.0F) << 8 | (int)(blue * 255.0F)
         );
         RenderSystem.enableBlend();
         class_4588 vertexConsumer = this.field_44658
            .getBuffer(
               BatchingRenderLayers.COLORED_TEXTURE.apply(this.field_44656.method_1531().method_4619(texture).method_4624(), BlendFuncDepthFuncState.current())
            );
         vertexConsumer.method_22918(matrix, x1, y2, z).method_22913(u1, v2).method_39415(color);
         vertexConsumer.method_22918(matrix, x2, y2, z).method_22913(u2, v2).method_39415(color);
         vertexConsumer.method_22918(matrix, x2, y1, z).method_22913(u2, v1).method_39415(color);
         vertexConsumer.method_22918(matrix, x1, y1, z).method_22913(u1, v1).method_39415(color);
         RenderSystem.disableBlend();
      }
   }

   @Inject(method = "method_51744()V", at = @At("HEAD"), cancellable = true)
   private void dontTryDrawIfBatching(CallbackInfo ci) {
      if (this.field_44658 instanceof HudBatchingBufferSource) {
         ci.cancel();
      }
   }

   @WrapWithCondition(
      method = "method_51425(Lnet/minecraft/class_1309;Lnet/minecraft/class_1937;Lnet/minecraft/class_1799;IIII)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_332;method_51452()V")
   )
   private boolean dontDrawIfBatching(class_332 instance) {
      return !(instance.field_44658 instanceof HudBatchingBufferSource);
   }

   @Unique
   private int immediatelyFast$mixWithShaderColor(int color) {
      float[] shaderColor = RenderSystem.getShaderColor();
      int argb = class_3532.method_15340((int)(shaderColor[3] * 255.0F), 0, 255) << 24
         | class_3532.method_15340((int)(shaderColor[0] * 255.0F), 0, 255) << 16
         | class_3532.method_15340((int)(shaderColor[1] * 255.0F), 0, 255) << 8
         | class_3532.method_15340((int)(shaderColor[2] * 255.0F), 0, 255);
      return class_5254.method_27763(color, argb);
   }
}
