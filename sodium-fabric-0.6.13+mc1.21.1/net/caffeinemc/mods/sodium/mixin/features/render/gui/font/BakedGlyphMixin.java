package net.caffeinemc.mods.sodium.mixin.features.render.gui.font;

import net.caffeinemc.mods.sodium.api.math.MatrixHelper;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.api.vertex.format.common.GlyphVertex;
import net.caffeinemc.mods.sodium.client.render.vertex.VertexConsumerUtils;
import net.minecraft.class_382;
import net.minecraft.class_4588;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_382.class)
public class BakedGlyphMixin {
   @Shadow
   @Final
   private float field_2272;
   @Shadow
   @Final
   private float field_2280;
   @Shadow
   @Final
   private float field_2279;
   @Shadow
   @Final
   private float field_2278;
   @Shadow
   @Final
   private float field_2276;
   @Shadow
   @Final
   private float field_2274;
   @Shadow
   @Final
   private float field_2273;
   @Shadow
   @Final
   private float field_2275;

   @Inject(method = "method_2025(ZFFLorg/joml/Matrix4f;Lnet/minecraft/class_4588;FFFFI)V", at = @At("HEAD"), cancellable = true)
   private void drawFast(
      boolean italic, float x, float y, Matrix4f matrix, class_4588 vertexConsumer, float red, float green, float blue, float alpha, int light, CallbackInfo ci
   ) {
      VertexBufferWriter writer = VertexConsumerUtils.convertOrLog(vertexConsumer);
      if (writer != null) {
         ci.cancel();
         float x1 = x + this.field_2272;
         float x2 = x + this.field_2280;
         float h1 = y + this.field_2279;
         float h2 = y + this.field_2278;
         float w1 = italic ? 1.0F - 0.25F * this.field_2279 : 0.0F;
         float w2 = italic ? 1.0F - 0.25F * this.field_2278 : 0.0F;
         int color = ColorABGR.pack(red, green, blue, alpha);
         MemoryStack stack = MemoryStack.stackPush();

         try {
            long buffer = stack.nmalloc(112);
            write(buffer, matrix, x1 + w1, h1, 0.0F, color, this.field_2276, this.field_2274, light);
            long ptr = buffer + 28L;
            write(ptr, matrix, x1 + w2, h2, 0.0F, color, this.field_2276, this.field_2273, light);
            ptr += 28L;
            write(ptr, matrix, x2 + w2, h2, 0.0F, color, this.field_2275, this.field_2273, light);
            ptr += 28L;
            write(ptr, matrix, x2 + w1, h1, 0.0F, color, this.field_2275, this.field_2274, light);
            ptr += 28L;
            writer.push(stack, buffer, 4, GlyphVertex.FORMAT);
         } catch (Throwable var26) {
            if (stack != null) {
               try {
                  stack.close();
               } catch (Throwable var25) {
                  var26.addSuppressed(var25);
               }
            }

            throw var26;
         }

         if (stack != null) {
            stack.close();
         }
      }
   }

   @Unique
   private static void write(long buffer, Matrix4f matrix, float x, float y, float z, int color, float u, float v, int light) {
      float x2 = MatrixHelper.transformPositionX(matrix, x, y, z);
      float y2 = MatrixHelper.transformPositionY(matrix, x, y, z);
      float z2 = MatrixHelper.transformPositionZ(matrix, x, y, z);
      GlyphVertex.put(buffer, x2, y2, z2, color, u, v, light);
   }
}
