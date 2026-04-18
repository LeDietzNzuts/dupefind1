package net.caffeinemc.mods.sodium.client.world.biome;

import net.minecraft.class_1926;
import net.minecraft.class_1933;

public class BiomeColorMaps {
   private static final int WIDTH = 256;
   private static final int HEIGHT = 256;
   private static final int INVALID_INDEX = -1;

   public static int getGrassColor(int index) {
      return index != -1 && index < class_1933.field_9214.length ? class_1933.field_9214[index] : class_1933.method_49724();
   }

   public static int getFoliageColor(int index) {
      return index != -1 && index < class_1926.field_9183.length ? class_1926.field_9183[index] : class_1926.method_8341();
   }

   public static int getIndex(double temperature, double humidity) {
      humidity *= temperature;
      int x = (int)((1.0 - temperature) * 255.0);
      int y = (int)((1.0 - humidity) * 255.0);
      if (x < 0 || x >= 256) {
         return -1;
      } else {
         return y >= 0 && y < 256 ? y << 8 | x : -1;
      }
   }
}
