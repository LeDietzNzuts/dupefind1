package net.caffeinemc.mods.lithium.common.shapes;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.class_2335;
import net.minecraft.class_238;
import net.minecraft.class_246;
import net.minecraft.class_251;
import net.minecraft.class_265;
import net.minecraft.class_3532;
import net.minecraft.class_2350.class_2351;

public class VoxelShapeAlignedCuboid extends VoxelShapeSimpleCube {
   static final double LARGE_EPSILON = 1.0E-6;
   protected final byte xyzResolution;

   public VoxelShapeAlignedCuboid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int xRes, int yRes, int zRes) {
      super(new CuboidVoxelSet(1 << xRes, 1 << yRes, 1 << zRes, minX, minY, minZ, maxX, maxY, maxZ), minX, minY, minZ, maxX, maxY, maxZ);
      if (xRes <= 3 && yRes <= 3 && zRes <= 3 && xRes >= 0 && yRes >= 0 && zRes >= 0) {
         this.xyzResolution = (byte)(xRes << 4 | yRes << 2 | zRes);
      } else {
         throw new IllegalArgumentException("Resolution must be between 0 and 3");
      }
   }

   public VoxelShapeAlignedCuboid(class_251 voxels, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, byte xyzResolution) {
      super(voxels, minX, minY, minZ, maxX, maxY, maxZ);
      this.xyzResolution = xyzResolution;
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
            return calculatePenetration(this.minX, this.maxX, this.getXSegments(), box.field_1323, box.field_1320, maxDist);
         case field_10963:
            return calculatePenetration(this.minZ, this.maxZ, this.getZSegments(), box.field_1321, box.field_1324, maxDist);
         case field_10965:
            return calculatePenetration(this.minY, this.maxY, this.getYSegments(), box.field_1322, box.field_1325, maxDist);
         default:
            throw new IllegalArgumentException();
      }
   }

   private static double calculatePenetration(double aMin, double aMax, int segmentsPerUnit, double bMin, double bMax, double maxDist) {
      if (maxDist > 0.0) {
         double gap = aMin - bMax;
         if (gap >= -1.0E-7) {
            return Math.min(gap, maxDist);
         } else if (segmentsPerUnit == 1) {
            return maxDist;
         } else {
            double wallPos = (double)class_3532.method_15384((bMax - 1.0E-7) * segmentsPerUnit) / segmentsPerUnit;
            return wallPos < aMax - 1.0E-6 ? Math.min(maxDist, wallPos - bMax) : maxDist;
         }
      } else {
         double gap = aMax - bMin;
         if (gap <= 1.0E-7) {
            return Math.max(gap, maxDist);
         } else if (segmentsPerUnit == 1) {
            return maxDist;
         } else {
            double wallPos = (double)class_3532.method_15357((bMin + 1.0E-7) * segmentsPerUnit) / segmentsPerUnit;
            return wallPos > aMin + 1.0E-6 ? Math.max(maxDist, wallPos - bMin) : maxDist;
         }
      }
   }

   @Override
   public DoubleList method_1109(class_2351 axis) {
      return switch (axis) {
         case field_11048 -> new class_246(this.getXSegments());
         case field_11052 -> new class_246(this.getYSegments());
         case field_11051 -> new class_246(this.getZSegments());
         default -> throw new MatchException(null, null);
      };
   }

   @Override
   protected double method_1099(class_2351 axis, int index) {
      return switch (axis) {
         case field_11048 -> (double)index / this.getXSegments();
         case field_11052 -> (double)index / this.getYSegments();
         case field_11051 -> (double)index / this.getZSegments();
         default -> throw new MatchException(null, null);
      };
   }

   @Override
   protected int method_1100(class_2351 axis, double coord) {
      int i = switch (axis) {
         case field_11048 -> this.getXSegments();
         case field_11052 -> this.getYSegments();
         case field_11051 -> this.getZSegments();
         default -> throw new MatchException(null, null);
      };
      return class_3532.method_15340(class_3532.method_15357(coord * i), -1, i);
   }

   protected int getXSegments() {
      return 1 << (this.xyzResolution >>> 4);
   }

   protected int getYSegments() {
      return 1 << (this.xyzResolution >>> 2 & 3);
   }

   protected int getZSegments() {
      return 1 << (this.xyzResolution & 3);
   }
}
