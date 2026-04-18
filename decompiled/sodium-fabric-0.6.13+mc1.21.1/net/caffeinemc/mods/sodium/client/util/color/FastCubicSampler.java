package net.caffeinemc.mods.sodium.client.util.color;

import java.util.function.Function;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class FastCubicSampler {
   private static final double[] DENSITY_CURVE = new double[]{0.0, 1.0, 4.0, 6.0, 4.0, 1.0, 0.0};
   private static final int DIAMETER = 6;

   public static class_243 sampleColor(class_243 pos, FastCubicSampler.ColorFetcher colorFetcher, Function<class_243, class_243> transformer) {
      int intX = class_3532.method_15357(pos.method_10216());
      int intY = class_3532.method_15357(pos.method_10214());
      int intZ = class_3532.method_15357(pos.method_10215());
      int[] values = new int[216];

      for (int x = 0; x < 6; x++) {
         int blockX = intX - 2 + x;

         for (int y = 0; y < 6; y++) {
            int blockY = intY - 2 + y;

            for (int z = 0; z < 6; z++) {
               int blockZ = intZ - 2 + z;
               values[index(x, y, z)] = colorFetcher.fetch(blockX, blockY, blockZ);
            }
         }
      }

      if (isHomogenousArray(values)) {
         return transformer.apply(class_243.method_24457(values[0]));
      } else {
         double deltaX = pos.method_10216() - intX;
         double deltaY = pos.method_10214() - intY;
         double deltaZ = pos.method_10215() - intZ;
         class_243 sum = class_243.field_1353;
         double totalFactor = 0.0;

         for (int x = 0; x < 6; x++) {
            double densityX = class_3532.method_16436(deltaX, DENSITY_CURVE[x + 1], DENSITY_CURVE[x]);

            for (int y = 0; y < 6; y++) {
               double densityY = class_3532.method_16436(deltaY, DENSITY_CURVE[y + 1], DENSITY_CURVE[y]);

               for (int z = 0; z < 6; z++) {
                  double densityZ = class_3532.method_16436(deltaZ, DENSITY_CURVE[z + 1], DENSITY_CURVE[z]);
                  double factor = densityX * densityY * densityZ;
                  totalFactor += factor;
                  class_243 color = transformer.apply(class_243.method_24457(values[index(x, y, z)]));
                  sum = sum.method_1019(color.method_1021(factor));
               }
            }
         }

         return sum.method_1021(1.0 / totalFactor);
      }
   }

   private static int index(int x, int y, int z) {
      return 36 * z + 6 * y + x;
   }

   private static boolean isHomogenousArray(int[] arr) {
      int val = arr[0];

      for (int i = 1; i < arr.length; i++) {
         if (arr[i] != val) {
            return false;
         }
      }

      return true;
   }

   public interface ColorFetcher {
      int fetch(int var1, int var2, int var3);
   }
}
