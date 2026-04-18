package net.caffeinemc.mods.lithium.mixin.shapes.specialized_shapes;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.class_2335;
import net.minecraft.class_238;
import net.minecraft.class_251;
import net.minecraft.class_265;
import net.minecraft.class_2350.class_2351;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_265.class)
public abstract class VoxelShapeMixin {
   private static final double POSITIVE_EPSILON = 1.0E-7;
   private static final double NEGATIVE_EPSILON = -1.0E-7;
   @Shadow
   @Final
   public class_251 field_1401;

   @Shadow
   public abstract boolean method_1110();

   @Shadow
   protected abstract double method_1099(class_2351 var1, int var2);

   @Shadow
   public abstract DoubleList method_1109(class_2351 var1);

   @Overwrite
   public double method_1103(class_2335 cycleDirection, class_238 box, double maxDist) {
      if (this.method_1110()) {
         return maxDist;
      } else if (Math.abs(maxDist) < 1.0E-7) {
         return 0.0;
      } else {
         class_2335 cycle = cycleDirection.method_10055();
         class_2351 axisX = cycle.method_10058(class_2351.field_11048);
         class_2351 axisY = cycle.method_10058(class_2351.field_11052);
         class_2351 axisZ = cycle.method_10058(class_2351.field_11051);
         int minY = Integer.MIN_VALUE;
         int maxY = Integer.MIN_VALUE;
         int minZ = Integer.MIN_VALUE;
         int maxZ = Integer.MIN_VALUE;
         if (maxDist > 0.0) {
            double max = box.method_990(axisX);
            int maxIdx = this.method_1100(axisX, max - 1.0E-7);
            int maxX = this.field_1401.method_1051(axisX);

            for (int x = maxIdx + 1; x < maxX; x++) {
               minY = minY == Integer.MIN_VALUE ? Math.max(0, this.method_1100(axisY, box.method_1001(axisY) + 1.0E-7)) : minY;
               maxY = maxY == Integer.MIN_VALUE
                  ? Math.min(this.field_1401.method_1051(axisY), this.method_1100(axisY, box.method_990(axisY) - 1.0E-7) + 1)
                  : maxY;

               for (int y = minY; y < maxY; y++) {
                  minZ = minZ == Integer.MIN_VALUE ? Math.max(0, this.method_1100(axisZ, box.method_1001(axisZ) + 1.0E-7)) : minZ;
                  maxZ = maxZ == Integer.MIN_VALUE
                     ? Math.min(this.field_1401.method_1051(axisZ), this.method_1100(axisZ, box.method_990(axisZ) - 1.0E-7) + 1)
                     : maxZ;

                  for (int z = minZ; z < maxZ; z++) {
                     if (this.field_1401.method_1062(cycle, x, y, z)) {
                        double dist = this.method_1099(axisX, x) - max;
                        if (dist >= -1.0E-7) {
                           maxDist = Math.min(maxDist, dist);
                        }

                        return maxDist;
                     }
                  }
               }
            }
         } else if (maxDist < 0.0) {
            double min = box.method_1001(axisX);
            int minIdx = this.method_1100(axisX, min + 1.0E-7);

            for (int x = minIdx - 1; x >= 0; x--) {
               minY = minY == Integer.MIN_VALUE ? Math.max(0, this.method_1100(axisY, box.method_1001(axisY) + 1.0E-7)) : minY;
               maxY = maxY == Integer.MIN_VALUE
                  ? Math.min(this.field_1401.method_1051(axisY), this.method_1100(axisY, box.method_990(axisY) - 1.0E-7) + 1)
                  : maxY;

               for (int y = minY; y < maxY; y++) {
                  minZ = minZ == Integer.MIN_VALUE ? Math.max(0, this.method_1100(axisZ, box.method_1001(axisZ) + 1.0E-7)) : minZ;
                  maxZ = maxZ == Integer.MIN_VALUE
                     ? Math.min(this.field_1401.method_1051(axisZ), this.method_1100(axisZ, box.method_990(axisZ) - 1.0E-7) + 1)
                     : maxZ;

                  for (int zx = minZ; zx < maxZ; zx++) {
                     if (this.field_1401.method_1062(cycle, x, y, zx)) {
                        double dist = this.method_1099(axisX, x + 1) - min;
                        if (dist <= 1.0E-7) {
                           maxDist = Math.max(maxDist, dist);
                        }

                        return maxDist;
                     }
                  }
               }
            }
         }

         return maxDist;
      }
   }

   @Overwrite
   public int method_1100(class_2351 axis, double coord) {
      DoubleList list = this.method_1109(axis);
      int size = this.field_1401.method_1051(axis);
      int start = 0;
      int end = size + 1 - start;

      while (end > 0) {
         int middle = end / 2;
         int idx = start + middle;
         if (idx < 0 || idx <= size && !(coord < list.getDouble(idx))) {
            start = idx + 1;
            end -= middle + 1;
         } else {
            end = middle;
         }
      }

      return start - 1;
   }
}
