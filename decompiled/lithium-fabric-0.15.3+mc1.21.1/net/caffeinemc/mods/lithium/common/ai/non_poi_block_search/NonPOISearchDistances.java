package net.caffeinemc.mods.lithium.common.ai.non_poi_block_search;

import net.caffeinemc.mods.lithium.common.util.Distances;
import net.minecraft.class_1923;
import net.minecraft.class_2338;

public class NonPOISearchDistances {
   public static class MoveToBlockGoalDistances {
      public static int getMinimumSortOrderOfChunk(class_2338 center, long chunkPos) {
         return getMinimumSortOrderOfChunk(center, class_1923.method_8325(chunkPos), class_1923.method_8332(chunkPos));
      }

      public static int getMinimumSortOrderOfChunk(class_2338 center, int chunkX, int chunkZ) {
         long closest = Distances.getClosestPositionWithinChunk(center, chunkX, chunkZ);
         int dX = class_2338.method_10061(closest) - center.method_10263();
         int dZ = class_2338.method_10083(closest) - center.method_10260();
         return getVanillaSortOrderInt(getRing(dX, dZ), dX, dZ);
      }

      public static int getRing(int dX, int dZ) {
         return Math.max(Math.abs(dX), Math.abs(dZ));
      }

      public static int getVanillaSortOrderInt(int ring, int dX, int dZ) {
         return (ring << 16 | Math.abs(dX) << 9 | Math.abs(dZ) << 1) - ((dX > 0 ? 1 : 0) << 8 | (dZ > 0 ? 1 : 0));
      }
   }
}
