package net.caffeinemc.mods.lithium.common.shapes;

import net.minecraft.class_251;
import net.minecraft.class_2350.class_2351;

public class CuboidVoxelSet extends class_251 {
   private final int minX;
   private final int minY;
   private final int minZ;
   private final int maxX;
   private final int maxY;
   private final int maxZ;

   protected CuboidVoxelSet(int xSize, int ySize, int zSize, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
      super(xSize, ySize, zSize);
      this.minX = (int)Math.round(minX * xSize);
      this.maxX = (int)Math.round(maxX * xSize);
      this.minY = (int)Math.round(minY * ySize);
      this.maxY = (int)Math.round(maxY * ySize);
      this.minZ = (int)Math.round(minZ * zSize);
      this.maxZ = (int)Math.round(maxZ * zSize);
   }

   public boolean method_1063(int x, int y, int z) {
      return x >= this.minX && x < this.maxX && y >= this.minY && y < this.maxY && z >= this.minZ && z < this.maxZ;
   }

   public void method_1049(int x, int y, int z) {
      throw new UnsupportedOperationException();
   }

   public int method_1055(class_2351 axis) {
      return axis.method_10173(this.minX, this.minY, this.minZ);
   }

   public int method_1045(class_2351 axis) {
      return axis.method_10173(this.maxX, this.maxY, this.maxZ);
   }

   public boolean method_1056() {
      return this.minX >= this.maxX || this.minY >= this.maxY || this.minZ >= this.maxZ;
   }
}
