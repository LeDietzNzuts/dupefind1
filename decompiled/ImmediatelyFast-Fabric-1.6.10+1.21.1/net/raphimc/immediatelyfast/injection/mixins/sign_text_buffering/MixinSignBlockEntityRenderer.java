package net.raphimc.immediatelyfast.injection.mixins.sign_text_buffering;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_5481;
import net.minecraft.class_758;
import net.minecraft.class_8242;
import net.minecraft.class_8251;
import net.minecraft.class_837;
import net.minecraft.class_9799;
import net.minecraft.class_4597.class_4598;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.feature.core.BufferAllocatorPool;
import net.raphimc.immediatelyfast.feature.sign_text_buffering.NoSetTextAnglesMatrixStack;
import net.raphimc.immediatelyfast.feature.sign_text_buffering.SignAtlasFramebuffer;
import net.raphimc.immediatelyfast.injection.interfaces.ISignText;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_837.class)
public abstract class MixinSignBlockEntityRenderer {
   @Shadow
   @Final
   private class_327 field_27755;

   @Shadow
   abstract void method_45798(class_2338 var1, class_8242 var2, class_4587 var3, class_4597 var4, int var5, int var6, int var7, boolean var8);

   @Shadow
   protected abstract void method_49919(class_4587 var1, boolean var2, class_243 var3);

   @Shadow
   abstract class_243 method_45790();

   @Inject(
      method = "method_45798(Lnet/minecraft/class_2338;Lnet/minecraft/class_8242;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IIIZ)V",
      at = @At("HEAD"),
      cancellable = true
   )
   private void renderBufferedSignText(
      class_2338 pos,
      class_8242 signText,
      class_4587 matrices,
      class_4597 vertexConsumers,
      int light,
      int lineHeight,
      int lineWidth,
      boolean front,
      CallbackInfo ci
   ) {
      if (!(matrices instanceof NoSetTextAnglesMatrixStack)) {
         ISignText iSignText = (ISignText)signText;
         if (iSignText.immediatelyFast$shouldCache()) {
            SignAtlasFramebuffer.Slot slot = (SignAtlasFramebuffer.Slot)ImmediatelyFast.signTextCache.slotCache.getIfPresent(signText);
            if (slot == null) {
               int width = this.immediatelyFast$getTextWidth(signText, lineWidth);
               int height = 4 * lineHeight;
               if (width <= 0 || height <= 0) {
                  iSignText.immediatelyFast$setShouldCache(false);
                  return;
               }

               int padding = signText.method_49856() ? 2 : 0;
               slot = ImmediatelyFast.signTextCache.signAtlasFramebuffer.findSlot(width + padding, height + padding);
               if (slot == null) {
                  ImmediatelyFast.LOGGER
                     .warn(
                        "Failed to find a free slot for sign text ("
                           + ImmediatelyFast.signTextCache.slotCache.size()
                           + " sign texts in atlas). Falling back to immediate mode rendering."
                     );
                  iSignText.immediatelyFast$setShouldCache(false);
                  return;
               }

               Matrix4f projectionMatrix = new Matrix4f().setOrtho(0.0F, 4096.0F, 4096.0F, 0.0F, -1000.0F, 1000.0F);
               RenderSystem.backupProjectionMatrix();
               RenderSystem.setProjectionMatrix(projectionMatrix, class_8251.field_43361);
               Matrix4fStack modelViewMatrix = RenderSystem.getModelViewStack();
               modelViewMatrix.pushMatrix();
               modelViewMatrix.identity();
               RenderSystem.applyModelViewMatrix();
               float fogStart = RenderSystem.getShaderFogStart();
               class_758.method_23792();
               ImmediatelyFast.signTextCache.signAtlasFramebuffer.method_1235(true);
               class_9799 bufferAllocator = BufferAllocatorPool.borrowBufferAllocator();
               class_4598 immediate = class_4597.method_22991(bufferAllocator);
               class_4587 matrixStack = new NoSetTextAnglesMatrixStack();
               matrixStack.method_46416(slot.x, slot.y, 0.0F);
               matrixStack.method_46416(slot.width / 2.0F, slot.height / 2.0F, 0.0F);
               this.method_45798(class_310.method_1551().field_1719.method_24515(), signText, matrixStack, immediate, light, lineHeight, lineWidth, front);
               immediate.method_22993();
               BufferAllocatorPool.returnBufferAllocatorSafe(bufferAllocator);
               class_310.method_1551().method_1522().method_1235(true);
               RenderSystem.setShaderFogStart(fogStart);
               modelViewMatrix.popMatrix();
               RenderSystem.applyModelViewMatrix();
               RenderSystem.restoreProjectionMatrix();
               ImmediatelyFast.signTextCache.slotCache.put(signText, slot);
            }

            float u1 = slot.x / 4096.0F;
            float u2 = ((float)slot.x + slot.width) / 4096.0F;
            float v1 = 1.0F - slot.y / 4096.0F;
            float v2 = 1.0F - ((float)slot.y + slot.height) / 4096.0F;
            if (signText.method_49856()) {
               light = 15728880;
            }

            matrices.method_22903();
            this.method_49919(matrices, front, this.method_45790());
            matrices.method_46416(-slot.width / 2.0F, -slot.height / 2.0F, 0.0F);
            Matrix4f matrix4f = matrices.method_23760().method_23761();
            class_4588 vertexConsumer = vertexConsumers.getBuffer(ImmediatelyFast.signTextCache.renderLayer);
            vertexConsumer.method_22918(matrix4f, 0.0F, slot.height, 0.0F).method_1336(255, 255, 255, 255).method_22913(u1, v2).method_60803(light);
            vertexConsumer.method_22918(matrix4f, slot.width, slot.height, 0.0F).method_1336(255, 255, 255, 255).method_22913(u2, v2).method_60803(light);
            vertexConsumer.method_22918(matrix4f, slot.width, 0.0F, 0.0F).method_1336(255, 255, 255, 255).method_22913(u2, v1).method_60803(light);
            vertexConsumer.method_22918(matrix4f, 0.0F, 0.0F, 0.0F).method_1336(255, 255, 255, 255).method_22913(u1, v1).method_60803(light);
            matrices.method_22909();
            ci.cancel();
         }
      }
   }

   @Redirect(
      method = "method_45798(Lnet/minecraft/class_2338;Lnet/minecraft/class_8242;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IIIZ)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_837;method_49919(Lnet/minecraft/class_4587;ZLnet/minecraft/class_243;)V")
   )
   private void dontSetTextAngles(class_837 instance, class_4587 matrices, boolean front, class_243 translation) {
      if (!(matrices instanceof NoSetTextAnglesMatrixStack)) {
         this.method_49919(matrices, front, translation);
      }
   }

   @Unique
   private int immediatelyFast$getTextWidth(class_8242 signText, int lineWidth) {
      class_5481[] orderedTexts = signText.method_49868(class_310.method_1551().method_33883(), text -> {
         List<class_5481> list = this.field_27755.method_1728(text, lineWidth);
         return list.isEmpty() ? class_5481.field_26385 : list.get(0);
      });
      int width = 0;

      for (class_5481 orderedText : orderedTexts) {
         width = Math.max(width, this.field_27755.method_30880(orderedText));
      }

      if (width % 2 != 0) {
         width++;
      }

      return width;
   }
}
