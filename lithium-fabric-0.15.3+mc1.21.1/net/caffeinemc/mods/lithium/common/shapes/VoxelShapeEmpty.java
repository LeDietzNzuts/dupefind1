package net.caffeinemc.mods.lithium.common.shapes;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.class_2335;
import net.minecraft.class_238;
import net.minecraft.class_251;
import net.minecraft.class_265;
import net.minecraft.class_2350.class_2351;

public class VoxelShapeEmpty extends class_265 implements VoxelShapeCaster {
   private static final DoubleList EMPTY_LIST = DoubleArrayList.wrap(new double[]{0.0});

   public VoxelShapeEmpty(class_251 voxels) {
      super(voxels);
   }

   protected double method_1103(class_2335 axisCycle, class_238 box, double maxDist) {
      return maxDist;
   }

   public DoubleList method_1109(class_2351 axis) {
      return EMPTY_LIST;
   }

   public double method_1091(class_2351 axis) {
      return Double.POSITIVE_INFINITY;
   }

   public double method_1105(class_2351 axis) {
      return Double.NEGATIVE_INFINITY;
   }

   public boolean method_1110() {
      return true;
   }

   @Override
   public boolean intersects(class_238 box, double blockX, double blockY, double blockZ) {
      return false;
   }
}
