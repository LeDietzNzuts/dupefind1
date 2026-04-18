package net.caffeinemc.mods.sodium.mixin.features.render.entity.shadows;

import net.caffeinemc.mods.sodium.api.math.MatrixHelper;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.api.vertex.format.common.EntityVertex;
import net.caffeinemc.mods.sodium.client.render.vertex.VertexConsumerUtils;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2464;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_4538;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_765;
import net.minecraft.class_898;
import net.minecraft.class_4587.class_4665;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_898.class)
public class EntityRenderDispatcherMixin {
   @Unique
   private static final int SHADOW_COLOR = ColorABGR.pack(1.0F, 1.0F, 1.0F);

   @Inject(
      method = "method_23163(Lnet/minecraft/class_4587$class_4665;Lnet/minecraft/class_4588;Lnet/minecraft/class_2791;Lnet/minecraft/class_4538;Lnet/minecraft/class_2338;DDDFF)V",
      at = @At("HEAD"),
      cancellable = true
   )
   private static void renderShadowPartFast(
      class_4665 matrices,
      class_4588 vertices,
      class_2791 chunk,
      class_4538 level,
      class_2338 pos,
      double x,
      double y,
      double z,
      float radius,
      float opacity,
      CallbackInfo ci
   ) {
      VertexBufferWriter writer = VertexConsumerUtils.convertOrLog(vertices);
      if (writer != null) {
         ci.cancel();
         class_2338 blockPos = pos.method_10074();
         class_2680 blockState = level.method_8320(blockPos);
         if (blockState.method_26217() != class_2464.field_11455 && blockState.method_26234(level, blockPos)) {
            int light = level.method_22339(pos);
            if (light > 3) {
               class_265 voxelShape = blockState.method_26218(level, blockPos);
               if (!voxelShape.method_1110()) {
                  float brightness = class_765.method_23284(level.method_8597(), light);
                  float alpha = (float)((opacity - (y - pos.method_10264()) / 2.0) * 0.5 * brightness);
                  if (alpha >= 0.0F) {
                     if (alpha > 1.0F) {
                        alpha = 1.0F;
                     }

                     class_238 box = voxelShape.method_1107();
                     float minX = (float)(pos.method_10263() + box.field_1323 - x);
                     float maxX = (float)(pos.method_10263() + box.field_1320 - x);
                     float minY = (float)(pos.method_10264() + box.field_1322 - y);
                     float minZ = (float)(pos.method_10260() + box.field_1321 - z);
                     float maxZ = (float)(pos.method_10260() + box.field_1324 - z);
                     renderShadowPart(matrices, writer, radius, alpha, minX, maxX, minY, minZ, maxZ);
                  }
               }
            }
         }
      }
   }

   @Unique
   private static void renderShadowPart(
      class_4665 matrices, VertexBufferWriter writer, float radius, float alpha, float minX, float maxX, float minY, float minZ, float maxZ
   ) {
      float size = 0.5F * (1.0F / radius);
      float u1 = -minX * size + 0.5F;
      float u2 = -maxX * size + 0.5F;
      float v1 = -minZ * size + 0.5F;
      float v2 = -maxZ * size + 0.5F;
      Matrix3f matNormal = matrices.method_23762();
      Matrix4f matPosition = matrices.method_23761();
      int color = ColorABGR.withAlpha(SHADOW_COLOR, alpha);
      int normal = MatrixHelper.transformNormal(matNormal, matrices.field_48930, class_2350.field_11036);
      MemoryStack stack = MemoryStack.stackPush();

      try {
         long buffer = stack.nmalloc(144);
         writeShadowVertex(buffer, matPosition, minX, minY, minZ, u1, v1, color, normal);
         long ptr = buffer + 36L;
         writeShadowVertex(ptr, matPosition, minX, minY, maxZ, u1, v2, color, normal);
         ptr += 36L;
         writeShadowVertex(ptr, matPosition, maxX, minY, maxZ, u2, v2, color, normal);
         ptr += 36L;
         writeShadowVertex(ptr, matPosition, maxX, minY, minZ, u2, v1, color, normal);
         ptr += 36L;
         writer.push(stack, buffer, 4, EntityVertex.FORMAT);
      } catch (Throwable var24) {
         if (stack != null) {
            try {
               stack.close();
            } catch (Throwable var23) {
               var24.addSuppressed(var23);
            }
         }

         throw var24;
      }

      if (stack != null) {
         stack.close();
      }
   }

   @Unique
   private static void writeShadowVertex(long ptr, Matrix4f matPosition, float x, float y, float z, float u, float v, int color, int normal) {
      float xt = MatrixHelper.transformPositionX(matPosition, x, y, z);
      float yt = MatrixHelper.transformPositionY(matPosition, x, y, z);
      float zt = MatrixHelper.transformPositionZ(matPosition, x, y, z);
      EntityVertex.write(ptr, xt, yt, zt, color, u, v, 15728880, class_4608.field_21444, normal);
   }
}
