package net.caffeinemc.mods.lithium.mixin.shapes.specialized_shapes;

import net.caffeinemc.mods.lithium.common.shapes.VoxelShapeAlignedCuboid;
import net.caffeinemc.mods.lithium.common.shapes.VoxelShapeEmpty;
import net.caffeinemc.mods.lithium.common.shapes.VoxelShapeSimpleCube;
import net.minecraft.class_244;
import net.minecraft.class_251;
import net.minecraft.class_259;
import net.minecraft.class_265;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_259.class)
public abstract class ShapesMixin {
   @Mutable
   @Shadow
   @Final
   public static final class_265 field_17669 = new VoxelShapeSimpleCube(
      ShapesMixin.FULL_CUBE_VOXELS,
      Double.NEGATIVE_INFINITY,
      Double.NEGATIVE_INFINITY,
      Double.NEGATIVE_INFINITY,
      Double.POSITIVE_INFINITY,
      Double.POSITIVE_INFINITY,
      Double.POSITIVE_INFINITY
   );
   @Mutable
   @Shadow
   @Final
   private static final class_265 field_1385 = new VoxelShapeSimpleCube(ShapesMixin.FULL_CUBE_VOXELS, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
   @Mutable
   @Shadow
   @Final
   private static final class_265 field_1384 = new VoxelShapeEmpty(new class_244(0, 0, 0));
   private static final class_251 FULL_CUBE_VOXELS = new class_244(1, 1, 1);

   @Overwrite
   public static class_265 method_31943(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
      if (maxX - minX < 1.0E-7 || maxY - minY < 1.0E-7 || maxZ - minZ < 1.0E-7) {
         return field_1384;
      } else {
         int xRes;
         int yRes;
         int zRes;
         if ((xRes = class_259.method_1086(minX, maxX)) < 0 || (yRes = class_259.method_1086(minY, maxY)) < 0 || (zRes = class_259.method_1086(minZ, maxZ)) < 0
            )
          {
            return new VoxelShapeSimpleCube(FULL_CUBE_VOXELS, minX, minY, minZ, maxX, maxY, maxZ);
         } else {
            return (class_265)(xRes == 0 && yRes == 0 && zRes == 0
               ? field_1385
               : new VoxelShapeAlignedCuboid(
                  Math.round(minX * 8.0) / 8.0,
                  Math.round(minY * 8.0) / 8.0,
                  Math.round(minZ * 8.0) / 8.0,
                  Math.round(maxX * 8.0) / 8.0,
                  Math.round(maxY * 8.0) / 8.0,
                  Math.round(maxZ * 8.0) / 8.0,
                  xRes,
                  yRes,
                  zRes
               ));
         }
      }
   }

   static {
      FULL_CUBE_VOXELS.method_1049(0, 0, 0);
   }
}
