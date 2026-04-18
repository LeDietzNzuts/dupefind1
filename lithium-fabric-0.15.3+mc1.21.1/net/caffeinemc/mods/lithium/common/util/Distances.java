package net.caffeinemc.mods.lithium.common.util;

import net.minecraft.class_2338;
import net.minecraft.class_4076;

public class Distances {
   public static double getMinChunkToBlockDistanceL2Sq(class_2338 origin, int chunkX, int chunkZ) {
      int chunkMinX = class_4076.method_18688(chunkX);
      int chunkMinZ = class_4076.method_18688(chunkZ);
      int xDistance = origin.method_10263() - chunkMinX;
      if (xDistance > 0) {
         xDistance = Math.max(0, xDistance - 15);
      }

      int zDistance = origin.method_10260() - chunkMinZ;
      if (zDistance > 0) {
         zDistance = Math.max(0, zDistance - 15);
      }

      return xDistance * xDistance + zDistance * zDistance;
   }

   public static boolean isWithinSquareRadius(class_2338 origin, int radius, class_2338 pos) {
      return Math.abs(pos.method_10263() - origin.method_10263()) <= radius && Math.abs(pos.method_10260() - origin.method_10260()) <= radius;
   }

   public static boolean isWithinCircleRadius(class_2338 origin, double radiusSq, class_2338 pos) {
      return origin.method_10262(pos) <= radiusSq;
   }

   public static int getClosestAlongSectionAxis(int originAxis, int chunkAxis) {
      int chunkMinAxis = class_4076.method_18688(chunkAxis);
      return Math.min(Math.max(originAxis, chunkMinAxis), chunkMinAxis + 15);
   }

   public static long getClosestPositionWithinChunk(class_2338 origin, int chunkX, int chunkZ) {
      return class_2338.method_10064(
         getClosestAlongSectionAxis(origin.method_10263(), chunkX), origin.method_10264(), getClosestAlongSectionAxis(origin.method_10260(), chunkZ)
      );
   }
}
