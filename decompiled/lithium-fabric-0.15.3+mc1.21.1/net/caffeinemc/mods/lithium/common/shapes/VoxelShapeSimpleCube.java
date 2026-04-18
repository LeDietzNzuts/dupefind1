package net.caffeinemc.mods.lithium.common.shapes;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.List;
import net.minecraft.class_2335;
import net.minecraft.class_238;
import net.minecraft.class_251;
import net.minecraft.class_265;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_259.class_260;

public class VoxelShapeSimpleCube extends class_265 implements VoxelShapeCaster {
   static final double EPSILON = 1.0E-7;
   final double minX;
   final double minY;
   final double minZ;
   final double maxX;
   final double maxY;
   final double maxZ;
   public final boolean isTiny;

   public VoxelShapeSimpleCube(class_251 voxels, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
      super(voxels);
      this.minX = minX;
      this.minY = minY;
      this.minZ = minZ;
      this.maxX = maxX;
      this.maxY = maxY;
      this.maxZ = maxZ;
      this.isTiny = this.minX + 3.0E-7 >= this.maxX || this.minY + 3.0E-7 >= this.maxY || this.minZ + 3.0E-7 >= this.maxZ;
   }

   public class_265 method_1096(double x, double y, double z) {
      return new VoxelShapeSimpleCube(this.field_1401, this.minX + x, this.minY + y, this.minZ + z, this.maxX + x, this.maxY + y, this.maxZ + z);
   }

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
            return calculatePenetration(this.minX, this.maxX, box.field_1323, box.field_1320, maxDist);
         case field_10963:
            return calculatePenetration(this.minZ, this.maxZ, box.field_1321, box.field_1324, maxDist);
         case field_10965:
            return calculatePenetration(this.minY, this.maxY, box.field_1322, box.field_1325, maxDist);
         default:
            throw new IllegalArgumentException();
      }
   }

   boolean intersects(class_2335 dir, class_238 box) {
      switch (dir) {
         case field_10962:
            return lessThan(this.minY, box.field_1325)
               && lessThan(box.field_1322, this.maxY)
               && lessThan(this.minZ, box.field_1324)
               && lessThan(box.field_1321, this.maxZ);
         case field_10963:
            return lessThan(this.minX, box.field_1320)
               && lessThan(box.field_1323, this.maxX)
               && lessThan(this.minY, box.field_1325)
               && lessThan(box.field_1322, this.maxY);
         case field_10965:
            return lessThan(this.minZ, box.field_1324)
               && lessThan(box.field_1321, this.maxZ)
               && lessThan(this.minX, box.field_1320)
               && lessThan(box.field_1323, this.maxX);
         default:
            throw new IllegalArgumentException();
      }
   }

   private static double calculatePenetration(double a1, double a2, double b1, double b2, double maxDist) {
      double penetration;
      if (maxDist > 0.0) {
         penetration = a1 - b2;
         if (penetration < -1.0E-7 || maxDist < penetration) {
            return maxDist;
         }
      } else {
         penetration = a2 - b1;
         if (penetration > 1.0E-7 || maxDist > penetration) {
            return maxDist;
         }
      }

      return penetration;
   }

   public List<class_238> method_1090() {
      return Lists.newArrayList(new class_238[]{this.method_1107()});
   }

   public class_238 method_1107() {
      return new class_238(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
   }

   public double method_1091(class_2351 axis) {
      return axis.method_10172(this.minX, this.minY, this.minZ);
   }

   public double method_1105(class_2351 axis) {
      return axis.method_10172(this.maxX, this.maxY, this.maxZ);
   }

   protected double method_1099(class_2351 axis, int index) {
      if (index >= 0 && index <= 1) {
         switch (axis) {
            case field_11048:
               return index == 0 ? this.minX : this.maxX;
            case field_11052:
               return index == 0 ? this.minY : this.maxY;
            case field_11051:
               return index == 0 ? this.minZ : this.maxZ;
            default:
               throw new IllegalArgumentException();
         }
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public DoubleList method_1109(class_2351 axis) {
      switch (axis) {
         case field_11048:
            return DoubleArrayList.wrap(new double[]{this.minX, this.maxX});
         case field_11052:
            return DoubleArrayList.wrap(new double[]{this.minY, this.maxY});
         case field_11051:
            return DoubleArrayList.wrap(new double[]{this.minZ, this.maxZ});
         default:
            throw new IllegalArgumentException();
      }
   }

   public boolean method_1110() {
      return this.minX >= this.maxX || this.minY >= this.maxY || this.minZ >= this.maxZ;
   }

   protected int method_1100(class_2351 axis, double coord) {
      if (coord < this.method_1091(axis)) {
         return -1;
      } else {
         return coord >= this.method_1105(axis) ? 1 : 0;
      }
   }

   private static boolean lessThan(double a, double b) {
      return a + 1.0E-7 < b;
   }

   @Override
   public boolean intersects(class_238 box, double blockX, double blockY, double blockZ) {
      return box.field_1323 + 1.0E-7 < this.maxX + blockX
         && box.field_1320 - 1.0E-7 > this.minX + blockX
         && box.field_1322 + 1.0E-7 < this.maxY + blockY
         && box.field_1325 - 1.0E-7 > this.minY + blockY
         && box.field_1321 + 1.0E-7 < this.maxZ + blockZ
         && box.field_1324 - 1.0E-7 > this.minZ + blockZ;
   }

   public void method_1089(class_260 boxConsumer) {
      boxConsumer.consume(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
   }
}
