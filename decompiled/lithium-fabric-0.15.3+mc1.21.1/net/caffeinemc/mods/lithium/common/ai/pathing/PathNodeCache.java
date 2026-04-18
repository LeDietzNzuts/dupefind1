package net.caffeinemc.mods.lithium.common.ai.pathing;

import net.caffeinemc.mods.lithium.common.block.BlockCountingSection;
import net.caffeinemc.mods.lithium.common.block.BlockStateFlags;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.caffeinemc.mods.lithium.common.world.ChunkView;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.caffeinemc.mods.lithium.mixin.ai.pathing.PathfindingContextAccessor;
import net.minecraft.class_14;
import net.minecraft.class_1922;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_7;
import net.minecraft.class_9316;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_4970.class_4971;

public abstract class PathNodeCache {
   private static boolean isChunkSectionDangerousNeighbor(class_2826 section) {
      return section.method_12265().method_19526(state -> getNeighborPathNodeType(state) != class_7.field_7);
   }

   public static class_7 getPathNodeType(class_2680 state) {
      return ((BlockStatePathingCache)state).lithium$getPathNodeType();
   }

   public static class_7 getNeighborPathNodeType(class_4971 state) {
      return ((BlockStatePathingCache)state).lithium$getNeighborPathNodeType();
   }

   public static boolean isSectionSafeAsNeighbor(class_2826 section) {
      if (section.method_38292()) {
         return true;
      } else {
         return BlockStateFlags.ENABLED
            ? !((BlockCountingSection)section).lithium$mayContainAny(BlockStateFlags.PATH_NOT_OPEN)
            : !isChunkSectionDangerousNeighbor(section);
      }
   }

   public static class_7 getNodeTypeFromNeighbors(class_9316 context, int x, int y, int z, class_7 fallback) {
      class_1922 world = context.method_57621();
      class_2826 section = null;
      if (world instanceof ChunkView chunkView && WorldHelper.areNeighborsWithinSameChunkSection(x, y, z)) {
         if (!world.method_31601(y)) {
            class_2791 chunk = chunkView.lithium$getLoadedChunk(Pos.ChunkCoord.fromBlockCoord(x), Pos.ChunkCoord.fromBlockCoord(z));
            if (chunk != null) {
               section = chunk.method_12006()[Pos.SectionYIndex.fromBlockCoord(world, y)];
            }
         }

         if (section == null || isSectionSafeAsNeighbor(section)) {
            return fallback;
         }
      }

      int xStart = x - 1;
      int yStart = y - 1;
      int zStart = z - 1;
      int xEnd = x + 1;
      int yEnd = y + 1;
      int zEnd = z + 1;

      for (int adjX = xStart; adjX <= xEnd; adjX++) {
         for (int adjY = yStart; adjY <= yEnd; adjY++) {
            for (int adjZ = zStart; adjZ <= zEnd; adjZ++) {
               if (adjX != x || adjZ != z) {
                  class_2680 state;
                  if (section != null) {
                     state = section.method_12254(adjX & 15, adjY & 15, adjZ & 15);
                  } else {
                     class_2339 pos = ((PathfindingContextAccessor)context).getLastNodePos().method_10103(adjX, adjY, adjZ);
                     state = world.method_8320(pos);
                  }

                  if (!state.method_26215()) {
                     class_7 neighborType = getNeighborPathNodeType(state);
                     if (neighborType == null) {
                        neighborType = class_14.method_59(context, adjX + 1, adjY + 1, adjZ + 1, null);
                        if (neighborType == null) {
                           neighborType = class_7.field_7;
                        }
                     }

                     if (neighborType != class_7.field_7) {
                        return neighborType;
                     }
                  }
               }
            }
         }
      }

      return fallback;
   }
}
