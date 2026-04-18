package com.magistuarmory.block;

import net.minecraft.class_1542;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_2215;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2498;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_7718;
import net.minecraft.class_7833;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class PaviseUpperCollisionBlock extends class_2248 {
   private static final class_238 COLLISION_AABB = new class_238(0.0, 0.0, 0.46875, 1.0, 0.5, 0.53125);
   private static final Vector3d CENTER = new Vector3d(0.5, 0.5, 0.5);
   private static final Vector3d BOXMIN = new Vector3d(0.0, 0.0, 0.0);
   private static final Vector3d BOXMAX = new Vector3d(1.0, 1.0, 1.0);

   public PaviseUpperCollisionBlock() {
      super(class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_44608));
   }

   public class_2680 method_9605(class_1750 context) {
      return this.method_9564();
   }

   @NotNull
   public class_1799 method_9574(class_4538 reader, class_2338 blockpos, class_2680 blockstate) {
      return reader.method_8321(blockpos.method_10074()) instanceof PaviseBlockEntity pavise ? pavise.getStack() : class_1799.field_8037;
   }

   public class_265 method_9530(class_2680 blockstate, class_1922 blockgetter, class_2338 blockpos, class_3726 context) {
      class_238 aabb = COLLISION_AABB;
      if (blockgetter.method_8321(blockpos.method_10074()) instanceof PaviseBlockEntity pavise) {
         class_2680 blockstate2 = pavise.method_11010();
         float yrot = -class_7718.method_45482((Integer)blockstate2.method_11654(class_2215.field_9924));
         aabb = rotateAABB(aabb, class_7833.field_40716.rotationDegrees(yrot));
      }

      return class_259.method_1078(aabb);
   }

   private static class_238 rotateAABB(class_238 axisAlignedBB, Quaternionf quaternion) {
      Vector3d mincoords = new Vector3d(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1321);
      Vector3d maxcoords = new Vector3d(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1324);
      mincoords.sub(CENTER);
      maxcoords.sub(CENTER);
      quaternion.transform(mincoords);
      quaternion.transform(maxcoords);
      mincoords.add(CENTER).max(BOXMIN);
      maxcoords.add(CENTER).min(BOXMAX);
      return new class_238(mincoords.x(), mincoords.y(), mincoords.z(), maxcoords.x(), maxcoords.y(), maxcoords.z());
   }

   public void method_9585(class_1936 accessor, class_2338 blockpos, class_2680 blockstate) {
      if (accessor.method_8321(blockpos.method_10074()) instanceof PaviseBlockEntity pavise && pavise.method_10997() != null) {
         accessor.method_8649(
            new class_1542(
               pavise.method_10997(), blockpos.method_10263() + 0.5, blockpos.method_10264() - 0.5, blockpos.method_10260() + 0.5, pavise.getStack()
            )
         );
      }

      if (accessor.method_8320(blockpos.method_10074()).method_26204() instanceof PaviseBlock) {
         accessor.method_22352(blockpos.method_10074(), false);
      }

      super.method_9585(accessor, blockpos, blockstate);
   }
}
