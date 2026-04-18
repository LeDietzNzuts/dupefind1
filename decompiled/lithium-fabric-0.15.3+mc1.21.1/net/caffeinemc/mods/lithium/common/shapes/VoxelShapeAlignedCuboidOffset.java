package net.caffeinemc.mods.lithium.common.shapes;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.caffeinemc.mods.lithium.common.shapes.lists.OffsetFractionalDoubleList;
import net.minecraft.class_2335;
import net.minecraft.class_238;
import net.minecraft.class_251;
import net.minecraft.class_265;
import net.minecraft.class_3532;
import net.minecraft.class_2350.class_2351;

public class VoxelShapeAlignedCuboidOffset extends VoxelShapeAlignedCuboid {
   private final double xOffset;
   private final double yOffset;
   private final double zOffset;

   public VoxelShapeAlignedCuboidOffset(VoxelShapeAlignedCuboid originalShape, class_251 voxels, double xOffset, double yOffset, double zOffset) {
      super(
         voxels,
         originalShape.minX + xOffset,
         originalShape.minY + yOffset,
         originalShape.minZ + zOffset,
         originalShape.maxX + xOffset,
         originalShape.maxY + yOffset,
         originalShape.maxZ + zOffset,
         originalShape.xyzResolution
      );
      if (originalShape instanceof VoxelShapeAlignedCuboidOffset) {
         this.xOffset = ((VoxelShapeAlignedCuboidOffset)originalShape).xOffset + xOffset;
         this.yOffset = ((VoxelShapeAlignedCuboidOffset)originalShape).yOffset + yOffset;
         this.zOffset = ((VoxelShapeAlignedCuboidOffset)originalShape).zOffset + zOffset;
      } else {
         this.xOffset = xOffset;
         this.yOffset = yOffset;
         this.zOffset = zOffset;
      }
   }

   @Override
   public class_265 method_1096(double x, double y, double z) {
      return new VoxelShapeAlignedCuboidOffset(this, this.field_1401, x, y, z);
   }

   @Override
   public double method_1103(class_2335 cycleDirection, class_238 box, double maxDist) {
      if (Math.abs(maxDist) < 1.0E-7) {
         return 0.0;
      } else {
         double penetration = this.calculatePenetration(cycleDirection, box, maxDist);
         return penetration != maxDist && this.intersects(cycleDirection, box) ? penetration : maxDist;
      }
   }

   private double calculatePenetration(class_2335 dir, class_238 box, double maxDist) {
      switch (dir) {
         case field_10962:
            return calculatePenetration(this.minX, this.maxX, this.getXSegments(), this.xOffset, box.field_1323, box.field_1320, maxDist);
         case field_10963:
            return calculatePenetration(this.minZ, this.maxZ, this.getZSegments(), this.zOffset, box.field_1321, box.field_1324, maxDist);
         case field_10965:
            return calculatePenetration(this.minY, this.maxY, this.getYSegments(), this.yOffset, box.field_1322, box.field_1325, maxDist);
         default:
            throw new IllegalArgumentException();
      }
   }

   private static double calculatePenetration(double aMin, double aMax, int segmentsPerUnit, double shapeOffset, double bMin, double bMax, double maxDist) {
      if (maxDist > 0.0) {
         double gap = aMin - bMax;
         if (gap >= -1.0E-7) {
            return Math.min(gap, maxDist);
         } else if (segmentsPerUnit == 1) {
            return maxDist;
         } else {
            int segment = class_3532.method_15384((bMax - 1.0E-6 - shapeOffset) * segmentsPerUnit);
            double wallPos = (double)segment / segmentsPerUnit + shapeOffset;
            if (wallPos < bMax - 1.0E-7) {
               segment++;
               wallPos = (double)segment / segmentsPerUnit + shapeOffset;
            }

            return wallPos < aMax - 1.0E-6 ? Math.min(maxDist, wallPos - bMax) : maxDist;
         }
      } else {
         double gap = aMax - bMin;
         if (gap <= 1.0E-7) {
            return Math.max(gap, maxDist);
         } else if (segmentsPerUnit == 1) {
            return maxDist;
         } else {
            int segment = class_3532.method_15357((bMin + 1.0E-6 - shapeOffset) * segmentsPerUnit);
            double wallPos = (double)segment / segmentsPerUnit + shapeOffset;
            if (wallPos > bMin + 1.0E-7) {
               segment--;
               wallPos = (double)segment / segmentsPerUnit + shapeOffset;
            }

            return wallPos > aMin + 1.0E-6 ? Math.max(maxDist, wallPos - bMin) : maxDist;
         }
      }
   }

   @Override
   public DoubleList method_1109(class_2351 axis) {
      return switch (axis) {
         case field_11048 -> new OffsetFractionalDoubleList(this.getXSegments(), this.xOffset);
         case field_11052 -> new OffsetFractionalDoubleList(this.getYSegments(), this.yOffset);
         case field_11051 -> new OffsetFractionalDoubleList(this.getZSegments(), this.zOffset);
         default -> throw new MatchException(null, null);
      };
   }

   @Override
   protected double method_1099(class_2351 axis, int index) {
      return switch (axis) {
         case field_11048 -> this.xOffset + (double)index / this.getXSegments();
         case field_11052 -> this.yOffset + (double)index / this.getYSegments();
         case field_11051 -> this.zOffset + (double)index / this.getZSegments();
         default -> throw new MatchException(null, null);
      };
   }

   @Override
   protected int method_1100(class_2351 axis, double coord) {
      int numSegments;

      coord = switch (axis) {
         case field_11048 -> (coord - this.xOffset) * (numSegments = this.getXSegments());
         case field_11052 -> (coord - this.yOffset) * (numSegments = this.getYSegments());
         case field_11051 -> (coord - this.zOffset) * (numSegments = this.getZSegments());
         default -> throw new MatchException(null, null);
      };
      return class_3532.method_15340(class_3532.method_15357(coord), -1, numSegments);
   }
}
