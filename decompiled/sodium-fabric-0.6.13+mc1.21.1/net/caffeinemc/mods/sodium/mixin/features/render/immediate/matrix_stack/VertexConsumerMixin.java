package net.caffeinemc.mods.sodium.mixin.features.render.immediate.matrix_stack;

import net.caffeinemc.mods.sodium.api.math.MatrixHelper;
import net.minecraft.class_4588;
import net.minecraft.class_4587.class_4665;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4588.class)
public interface VertexConsumerMixin {
   @Shadow
   class_4588 method_22914(float var1, float var2, float var3);

   @Shadow
   class_4588 method_22912(float var1, float var2, float var3);

   @Overwrite
   default class_4588 method_22918(Matrix4f matrix, float x, float y, float z) {
      float xt = MatrixHelper.transformPositionX(matrix, x, y, z);
      float yt = MatrixHelper.transformPositionY(matrix, x, y, z);
      float zt = MatrixHelper.transformPositionZ(matrix, x, y, z);
      return this.method_22912(xt, yt, zt);
   }

   @Overwrite
   default class_4588 method_60831(class_4665 pose, float x, float y, float z) {
      Matrix3f matrix = pose.method_23762();
      float xt = MatrixHelper.transformNormalX(matrix, x, y, z);
      float yt = MatrixHelper.transformNormalY(matrix, x, y, z);
      float zt = MatrixHelper.transformNormalZ(matrix, x, y, z);
      if (!pose.field_48930) {
         float scalar = Math.invsqrt(Math.fma(xt, xt, Math.fma(yt, yt, zt * zt)));
         xt *= scalar;
         yt *= scalar;
         zt *= scalar;
      }

      return this.method_22914(xt, yt, zt);
   }
}
