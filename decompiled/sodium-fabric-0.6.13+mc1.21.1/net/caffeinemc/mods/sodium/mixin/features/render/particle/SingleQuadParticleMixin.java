package net.caffeinemc.mods.sodium.mixin.features.render.particle;

import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.api.vertex.format.common.ParticleVertex;
import net.caffeinemc.mods.sodium.client.render.vertex.VertexConsumerUtils;
import net.minecraft.class_3940;
import net.minecraft.class_4588;
import net.minecraft.class_638;
import net.minecraft.class_703;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3940.class)
public abstract class SingleQuadParticleMixin extends class_703 {
   @Unique
   private final Vector3f sodium$scratchVertex = new Vector3f();

   @Shadow
   public abstract float method_18132(float var1);

   @Shadow
   protected abstract float method_18133();

   @Shadow
   protected abstract float method_18134();

   @Shadow
   protected abstract float method_18135();

   @Shadow
   protected abstract float method_18136();

   protected SingleQuadParticleMixin(class_638 level, double x, double y, double z) {
      super(level, x, y, z);
   }

   @Inject(method = "method_60374(Lnet/minecraft/class_4588;Lorg/joml/Quaternionf;FFFF)V", at = @At("HEAD"), cancellable = true)
   protected void renderRotatedQuad(class_4588 vertexConsumer, Quaternionf quaternionf, float x, float y, float z, float tickDelta, CallbackInfo ci) {
      VertexBufferWriter writer = VertexConsumerUtils.convertOrLog(vertexConsumer);
      if (writer != null) {
         ci.cancel();
         float size = this.method_18132(tickDelta);
         float minU = this.method_18133();
         float maxU = this.method_18134();
         float minV = this.method_18135();
         float maxV = this.method_18136();
         int light = this.method_3068(tickDelta);
         int color = ColorABGR.pack(this.field_3861, this.field_3842, this.field_3859, this.field_3841);
         MemoryStack stack = MemoryStack.stackPush();

         try {
            long buffer = stack.nmalloc(112);
            this.sodium$writeVertex(buffer, quaternionf, x, y, z, 1.0F, -1.0F, size, maxU, maxV, color, light);
            long ptr = buffer + 28L;
            this.sodium$writeVertex(ptr, quaternionf, x, y, z, 1.0F, 1.0F, size, maxU, minV, color, light);
            ptr += 28L;
            this.sodium$writeVertex(ptr, quaternionf, x, y, z, -1.0F, 1.0F, size, minU, minV, color, light);
            ptr += 28L;
            this.sodium$writeVertex(ptr, quaternionf, x, y, z, -1.0F, -1.0F, size, minU, maxV, color, light);
            ptr += 28L;
            writer.push(stack, buffer, 4, ParticleVertex.FORMAT);
         } catch (Throwable var22) {
            if (stack != null) {
               try {
                  stack.close();
               } catch (Throwable var21) {
                  var22.addSuppressed(var21);
               }
            }

            throw var22;
         }

         if (stack != null) {
            stack.close();
         }
      }
   }

   @Unique
   private void sodium$writeVertex(
      long ptr,
      Quaternionf quaternionf,
      float originX,
      float originY,
      float originZ,
      float posX,
      float posY,
      float size,
      float u,
      float v,
      int color,
      int light
   ) {
      Vector3f vertex = this.sodium$scratchVertex;
      vertex.set(posX, posY, 0.0F);
      vertex.rotate(quaternionf);
      vertex.mul(size);
      vertex.add(originX, originY, originZ);
      ParticleVertex.put(ptr, vertex.x(), vertex.y(), vertex.z(), u, v, color, light);
   }
}
