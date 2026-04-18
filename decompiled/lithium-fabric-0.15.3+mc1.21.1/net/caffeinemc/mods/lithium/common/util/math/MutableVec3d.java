package net.caffeinemc.mods.lithium.common.util.math;

import net.minecraft.class_243;

public class MutableVec3d {
   private double x;
   private double y;
   private double z;

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public void add(class_243 vec) {
      this.x = this.x + vec.field_1352;
      this.y = this.y + vec.field_1351;
      this.z = this.z + vec.field_1350;
   }

   public class_243 toImmutable() {
      return new class_243(this.x, this.y, this.z);
   }
}
