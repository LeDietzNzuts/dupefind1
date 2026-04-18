package net.caffeinemc.mods.sodium.client.model.light.smooth;

import net.minecraft.class_2350;
import net.minecraft.class_3532;

enum AoNeighborInfo {
   DOWN(new class_2350[]{class_2350.field_11039, class_2350.field_11034, class_2350.field_11043, class_2350.field_11035}, 0.5F) {
      @Override
      public void calculateCornerWeights(float x, float y, float z, float[] out) {
         float u = class_3532.method_15363(z, 0.0F, 1.0F);
         float v = class_3532.method_15363(1.0F - x, 0.0F, 1.0F);
         out[0] = v * u;
         out[1] = v * (1.0F - u);
         out[2] = (1.0F - v) * (1.0F - u);
         out[3] = (1.0F - v) * u;
      }

      @Override
      public void mapCorners(int[] lm0, float[] ao0, int[] lm1, float[] ao1) {
         lm1[0] = lm0[0];
         lm1[1] = lm0[1];
         lm1[2] = lm0[2];
         lm1[3] = lm0[3];
         ao1[0] = ao0[0];
         ao1[1] = ao0[1];
         ao1[2] = ao0[2];
         ao1[3] = ao0[3];
      }

      @Override
      public float getDepth(float x, float y, float z) {
         return class_3532.method_15363(y, 0.0F, 1.0F);
      }
   },
   UP(new class_2350[]{class_2350.field_11034, class_2350.field_11039, class_2350.field_11043, class_2350.field_11035}, 1.0F) {
      @Override
      public void calculateCornerWeights(float x, float y, float z, float[] out) {
         float u = class_3532.method_15363(z, 0.0F, 1.0F);
         float v = class_3532.method_15363(x, 0.0F, 1.0F);
         out[0] = v * u;
         out[1] = v * (1.0F - u);
         out[2] = (1.0F - v) * (1.0F - u);
         out[3] = (1.0F - v) * u;
      }

      @Override
      public void mapCorners(int[] lm0, float[] ao0, int[] lm1, float[] ao1) {
         lm1[2] = lm0[0];
         lm1[3] = lm0[1];
         lm1[0] = lm0[2];
         lm1[1] = lm0[3];
         ao1[2] = ao0[0];
         ao1[3] = ao0[1];
         ao1[0] = ao0[2];
         ao1[1] = ao0[3];
      }

      @Override
      public float getDepth(float x, float y, float z) {
         return 1.0F - class_3532.method_15363(y, 0.0F, 1.0F);
      }
   },
   NORTH(new class_2350[]{class_2350.field_11036, class_2350.field_11033, class_2350.field_11034, class_2350.field_11039}, 0.8F) {
      @Override
      public void calculateCornerWeights(float x, float y, float z, float[] out) {
         float u = class_3532.method_15363(1.0F - x, 0.0F, 1.0F);
         float v = class_3532.method_15363(y, 0.0F, 1.0F);
         out[0] = v * u;
         out[1] = v * (1.0F - u);
         out[2] = (1.0F - v) * (1.0F - u);
         out[3] = (1.0F - v) * u;
      }

      @Override
      public void mapCorners(int[] lm0, float[] ao0, int[] lm1, float[] ao1) {
         lm1[3] = lm0[0];
         lm1[0] = lm0[1];
         lm1[1] = lm0[2];
         lm1[2] = lm0[3];
         ao1[3] = ao0[0];
         ao1[0] = ao0[1];
         ao1[1] = ao0[2];
         ao1[2] = ao0[3];
      }

      @Override
      public float getDepth(float x, float y, float z) {
         return class_3532.method_15363(z, 0.0F, 1.0F);
      }
   },
   SOUTH(new class_2350[]{class_2350.field_11039, class_2350.field_11034, class_2350.field_11033, class_2350.field_11036}, 0.8F) {
      @Override
      public void calculateCornerWeights(float x, float y, float z, float[] out) {
         float u = class_3532.method_15363(y, 0.0F, 1.0F);
         float v = class_3532.method_15363(1.0F - x, 0.0F, 1.0F);
         out[0] = u * v;
         out[1] = (1.0F - u) * v;
         out[2] = (1.0F - u) * (1.0F - v);
         out[3] = u * (1.0F - v);
      }

      @Override
      public void mapCorners(int[] lm0, float[] ao0, int[] lm1, float[] ao1) {
         lm1[0] = lm0[0];
         lm1[1] = lm0[1];
         lm1[2] = lm0[2];
         lm1[3] = lm0[3];
         ao1[0] = ao0[0];
         ao1[1] = ao0[1];
         ao1[2] = ao0[2];
         ao1[3] = ao0[3];
      }

      @Override
      public float getDepth(float x, float y, float z) {
         return 1.0F - class_3532.method_15363(z, 0.0F, 1.0F);
      }
   },
   WEST(new class_2350[]{class_2350.field_11036, class_2350.field_11033, class_2350.field_11043, class_2350.field_11035}, 0.6F) {
      @Override
      public void calculateCornerWeights(float x, float y, float z, float[] out) {
         float u = class_3532.method_15363(z, 0.0F, 1.0F);
         float v = class_3532.method_15363(y, 0.0F, 1.0F);
         out[0] = v * u;
         out[1] = v * (1.0F - u);
         out[2] = (1.0F - v) * (1.0F - u);
         out[3] = (1.0F - v) * u;
      }

      @Override
      public void mapCorners(int[] lm0, float[] ao0, int[] lm1, float[] ao1) {
         lm1[3] = lm0[0];
         lm1[0] = lm0[1];
         lm1[1] = lm0[2];
         lm1[2] = lm0[3];
         ao1[3] = ao0[0];
         ao1[0] = ao0[1];
         ao1[1] = ao0[2];
         ao1[2] = ao0[3];
      }

      @Override
      public float getDepth(float x, float y, float z) {
         return class_3532.method_15363(x, 0.0F, 1.0F);
      }
   },
   EAST(new class_2350[]{class_2350.field_11033, class_2350.field_11036, class_2350.field_11043, class_2350.field_11035}, 0.6F) {
      @Override
      public void calculateCornerWeights(float x, float y, float z, float[] out) {
         float u = class_3532.method_15363(z, 0.0F, 1.0F);
         float v = class_3532.method_15363(1.0F - y, 0.0F, 1.0F);
         out[0] = v * u;
         out[1] = v * (1.0F - u);
         out[2] = (1.0F - v) * (1.0F - u);
         out[3] = (1.0F - v) * u;
      }

      @Override
      public void mapCorners(int[] lm0, float[] ao0, int[] lm1, float[] ao1) {
         lm1[1] = lm0[0];
         lm1[2] = lm0[1];
         lm1[3] = lm0[2];
         lm1[0] = lm0[3];
         ao1[1] = ao0[0];
         ao1[2] = ao0[1];
         ao1[3] = ao0[2];
         ao1[0] = ao0[3];
      }

      @Override
      public float getDepth(float x, float y, float z) {
         return 1.0F - class_3532.method_15363(x, 0.0F, 1.0F);
      }
   };

   public final class_2350[] faces;
   public final float strength;
   private static final AoNeighborInfo[] VALUES = values();

   private AoNeighborInfo(class_2350[] directions, float strength) {
      this.faces = directions;
      this.strength = strength;
   }

   public abstract void calculateCornerWeights(float var1, float var2, float var3, float[] var4);

   public abstract void mapCorners(int[] var1, float[] var2, int[] var3, float[] var4);

   public abstract float getDepth(float var1, float var2, float var3);

   public static AoNeighborInfo get(class_2350 direction) {
      return VALUES[direction.method_10146()];
   }
}
