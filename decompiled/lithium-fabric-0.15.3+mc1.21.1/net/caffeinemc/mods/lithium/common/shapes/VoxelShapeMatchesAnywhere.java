package net.caffeinemc.mods.lithium.common.shapes;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.class_247;
import net.minecraft.class_251;
import net.minecraft.class_265;
import net.minecraft.class_2350.class_2351;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class VoxelShapeMatchesAnywhere {
   public static void cuboidMatchesAnywhere(class_265 shapeA, class_265 shapeB, class_247 predicate, CallbackInfoReturnable<Boolean> cir) {
      if (shapeA instanceof VoxelShapeSimpleCube && shapeB instanceof VoxelShapeSimpleCube) {
         if (((VoxelShapeSimpleCube)shapeA).isTiny || ((VoxelShapeSimpleCube)shapeB).isTiny) {
            return;
         }

         if (predicate.apply(true, true)) {
            if (intersects((VoxelShapeSimpleCube)shapeA, (VoxelShapeSimpleCube)shapeB)) {
               cir.setReturnValue(true);
               return;
            }

            cir.setReturnValue(predicate.apply(true, false) || predicate.apply(false, true));
         } else {
            if (predicate.apply(true, false) && exceedsShape((VoxelShapeSimpleCube)shapeA, (VoxelShapeSimpleCube)shapeB)) {
               cir.setReturnValue(true);
               return;
            }

            if (predicate.apply(false, true) && exceedsShape((VoxelShapeSimpleCube)shapeB, (VoxelShapeSimpleCube)shapeA)) {
               cir.setReturnValue(true);
               return;
            }
         }

         cir.setReturnValue(false);
      } else if (shapeA instanceof VoxelShapeSimpleCube || shapeB instanceof VoxelShapeSimpleCube) {
         VoxelShapeSimpleCube simpleCube = (VoxelShapeSimpleCube)(shapeA instanceof VoxelShapeSimpleCube ? shapeA : shapeB);
         class_265 otherShape = simpleCube == shapeA ? shapeB : shapeA;
         if (simpleCube.isTiny || isTiny(otherShape)) {
            return;
         }

         boolean acceptSimpleCubeAlone = predicate.apply(shapeA == simpleCube, shapeB == simpleCube);
         if (acceptSimpleCubeAlone
            && exceedsCube(
               simpleCube,
               otherShape.method_1091(class_2351.field_11048),
               otherShape.method_1091(class_2351.field_11052),
               otherShape.method_1091(class_2351.field_11051),
               otherShape.method_1105(class_2351.field_11048),
               otherShape.method_1105(class_2351.field_11052),
               otherShape.method_1105(class_2351.field_11051)
            )) {
            cir.setReturnValue(true);
            return;
         }

         boolean acceptAnd = predicate.apply(true, true);
         boolean acceptOtherShapeAlone = predicate.apply(shapeA == otherShape, shapeB == otherShape);
         class_251 voxelSet = otherShape.field_1401;
         DoubleList pointPositionsX = otherShape.method_1109(class_2351.field_11048);
         DoubleList pointPositionsY = otherShape.method_1109(class_2351.field_11052);
         DoubleList pointPositionsZ = otherShape.method_1109(class_2351.field_11051);
         int xMax = voxelSet.method_1045(class_2351.field_11048);
         int yMax = voxelSet.method_1045(class_2351.field_11052);
         int zMax = voxelSet.method_1045(class_2351.field_11051);
         double simpleCubeMaxX = simpleCube.method_1105(class_2351.field_11048);
         double simpleCubeMinX = simpleCube.method_1091(class_2351.field_11048);
         double simpleCubeMaxY = simpleCube.method_1105(class_2351.field_11052);
         double simpleCubeMinY = simpleCube.method_1091(class_2351.field_11052);
         double simpleCubeMaxZ = simpleCube.method_1105(class_2351.field_11051);
         double simpleCubeMinZ = simpleCube.method_1091(class_2351.field_11051);

         for (int x = voxelSet.method_1055(class_2351.field_11048); x < xMax; x++) {
            boolean simpleCubeIntersectsXSlice = simpleCubeMaxX - 1.0E-7 > pointPositionsX.getDouble(x)
               && simpleCubeMinX < pointPositionsX.getDouble(x + 1) - 1.0E-7;
            if (acceptOtherShapeAlone || simpleCubeIntersectsXSlice) {
               boolean xSliceExceedsCube = acceptOtherShapeAlone
                  && (!(simpleCubeMaxX >= pointPositionsX.getDouble(x + 1) - 1.0E-7) || !(simpleCubeMinX - 1.0E-7 <= pointPositionsX.getDouble(x)));

               for (int y = voxelSet.method_1055(class_2351.field_11052); y < yMax; y++) {
                  boolean simpleCubeIntersectsYSlice = simpleCubeMaxY - 1.0E-7 > pointPositionsY.getDouble(y)
                     && simpleCubeMinY < pointPositionsY.getDouble(y + 1) - 1.0E-7;
                  if (acceptOtherShapeAlone || simpleCubeIntersectsYSlice) {
                     boolean ySliceExceedsCube = acceptOtherShapeAlone
                        && (!(simpleCubeMaxY >= pointPositionsY.getDouble(y + 1) - 1.0E-7) || !(simpleCubeMinY - 1.0E-7 <= pointPositionsY.getDouble(y)));

                     for (int z = voxelSet.method_1055(class_2351.field_11051); z < zMax; z++) {
                        boolean simpleCubeIntersectsZSlice = simpleCubeMaxZ - 1.0E-7 > pointPositionsZ.getDouble(z)
                           && simpleCubeMinZ < pointPositionsZ.getDouble(z + 1) - 1.0E-7;
                        if (acceptOtherShapeAlone || simpleCubeIntersectsZSlice) {
                           boolean zSliceExceedsCube = acceptOtherShapeAlone
                              && (!(simpleCubeMaxZ >= pointPositionsZ.getDouble(z + 1) - 1.0E-7) || !(simpleCubeMinZ - 1.0E-7 <= pointPositionsZ.getDouble(z)));
                           boolean o = voxelSet.method_1044(x, y, z);
                           boolean s = simpleCubeIntersectsXSlice && simpleCubeIntersectsYSlice && simpleCubeIntersectsZSlice;
                           if (acceptAnd && o && s
                              || acceptSimpleCubeAlone && !o && s
                              || acceptOtherShapeAlone && o && (xSliceExceedsCube || ySliceExceedsCube || zSliceExceedsCube)) {
                              cir.setReturnValue(true);
                              return;
                           }
                        }
                     }
                  }
               }
            }
         }

         cir.setReturnValue(false);
      }
   }

   private static boolean isTiny(class_265 shapeA) {
      return shapeA.method_1091(class_2351.field_11048) > shapeA.method_1105(class_2351.field_11048) - 3.0E-7
         || shapeA.method_1091(class_2351.field_11052) > shapeA.method_1105(class_2351.field_11052) - 3.0E-7
         || shapeA.method_1091(class_2351.field_11051) > shapeA.method_1105(class_2351.field_11051) - 3.0E-7;
   }

   private static boolean exceedsCube(VoxelShapeSimpleCube a, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
      return a.method_1091(class_2351.field_11048) < minX - 1.0E-7
         || a.method_1105(class_2351.field_11048) > maxX + 1.0E-7
         || a.method_1091(class_2351.field_11052) < minY - 1.0E-7
         || a.method_1105(class_2351.field_11052) > maxY + 1.0E-7
         || a.method_1091(class_2351.field_11051) < minZ - 1.0E-7
         || a.method_1105(class_2351.field_11051) > maxZ + 1.0E-7;
   }

   private static boolean exceedsShape(VoxelShapeSimpleCube a, VoxelShapeSimpleCube b) {
      return a.method_1091(class_2351.field_11048) < b.method_1091(class_2351.field_11048) - 1.0E-7
         || a.method_1105(class_2351.field_11048) > b.method_1105(class_2351.field_11048) + 1.0E-7
         || a.method_1091(class_2351.field_11052) < b.method_1091(class_2351.field_11052) - 1.0E-7
         || a.method_1105(class_2351.field_11052) > b.method_1105(class_2351.field_11052) + 1.0E-7
         || a.method_1091(class_2351.field_11051) < b.method_1091(class_2351.field_11051) - 1.0E-7
         || a.method_1105(class_2351.field_11051) > b.method_1105(class_2351.field_11051) + 1.0E-7;
   }

   private static boolean intersects(VoxelShapeSimpleCube a, VoxelShapeSimpleCube b) {
      return a.method_1091(class_2351.field_11048) < b.method_1105(class_2351.field_11048) - 1.0E-7
         && a.method_1105(class_2351.field_11048) > b.method_1091(class_2351.field_11048) + 1.0E-7
         && a.method_1091(class_2351.field_11052) < b.method_1105(class_2351.field_11052) - 1.0E-7
         && a.method_1105(class_2351.field_11052) > b.method_1091(class_2351.field_11052) + 1.0E-7
         && a.method_1091(class_2351.field_11051) < b.method_1105(class_2351.field_11051) - 1.0E-7
         && a.method_1105(class_2351.field_11051) > b.method_1091(class_2351.field_11051) + 1.0E-7;
   }
}
