package net.caffeinemc.mods.lithium.common.world.chunk.heightmap;

import java.util.Objects;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.mixin.world.combined_heightmap_update.HeightmapAccessor;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2902;
import net.minecraft.class_2338.class_2339;

public class CombinedHeightmapUpdate {
   public static void updateHeightmaps(
      class_2902 heightmap0, class_2902 heightmap1, class_2902 heightmap2, class_2902 heightmap3, class_2818 worldChunk, int x, int y, int z, class_2680 state
   ) {
      int height0 = heightmap0.method_12603(x, z);
      int height1 = heightmap1.method_12603(x, z);
      int height2 = heightmap2.method_12603(x, z);
      int height3 = heightmap3.method_12603(x, z);
      int heightmapsToUpdate = 4;
      if (y + 2 <= height0) {
         heightmap0 = null;
         heightmapsToUpdate--;
      }

      if (y + 2 <= height1) {
         heightmap1 = null;
         heightmapsToUpdate--;
      }

      if (y + 2 <= height2) {
         heightmap2 = null;
         heightmapsToUpdate--;
      }

      if (y + 2 <= height3) {
         heightmap3 = null;
         heightmapsToUpdate--;
      }

      if (heightmapsToUpdate != 0) {
         Predicate<class_2680> blockPredicate0 = heightmap0 == null ? null : Objects.requireNonNull(((HeightmapAccessor)heightmap0).getBlockPredicate());
         Predicate<class_2680> blockPredicate1 = heightmap1 == null ? null : Objects.requireNonNull(((HeightmapAccessor)heightmap1).getBlockPredicate());
         Predicate<class_2680> blockPredicate2 = heightmap2 == null ? null : Objects.requireNonNull(((HeightmapAccessor)heightmap2).getBlockPredicate());
         Predicate<class_2680> blockPredicate3 = heightmap3 == null ? null : Objects.requireNonNull(((HeightmapAccessor)heightmap3).getBlockPredicate());
         if (heightmap0 != null) {
            if (blockPredicate0.test(state)) {
               if (y >= height0) {
                  ((HeightmapAccessor)heightmap0).callSet(x, z, y + 1);
               }

               heightmap0 = null;
               heightmapsToUpdate--;
            } else if (height0 != y + 1) {
               heightmap0 = null;
               heightmapsToUpdate--;
            }
         }

         if (heightmap1 != null) {
            if (blockPredicate1.test(state)) {
               if (y >= height1) {
                  ((HeightmapAccessor)heightmap1).callSet(x, z, y + 1);
               }

               heightmap1 = null;
               heightmapsToUpdate--;
            } else if (height1 != y + 1) {
               heightmap1 = null;
               heightmapsToUpdate--;
            }
         }

         if (heightmap2 != null) {
            if (blockPredicate2.test(state)) {
               if (y >= height2) {
                  ((HeightmapAccessor)heightmap2).callSet(x, z, y + 1);
               }

               heightmap2 = null;
               heightmapsToUpdate--;
            } else if (height2 != y + 1) {
               heightmap2 = null;
               heightmapsToUpdate--;
            }
         }

         if (heightmap3 != null) {
            if (blockPredicate3.test(state)) {
               if (y >= height3) {
                  ((HeightmapAccessor)heightmap3).callSet(x, z, y + 1);
               }

               heightmap3 = null;
               heightmapsToUpdate--;
            } else if (height3 != y + 1) {
               heightmap3 = null;
               heightmapsToUpdate--;
            }
         }

         if (heightmapsToUpdate != 0) {
            class_2339 mutable = new class_2339();
            int bottomY = worldChunk.method_31607();

            for (int searchY = y - 1; searchY >= bottomY && heightmapsToUpdate > 0; searchY--) {
               mutable.method_10103(x, searchY, z);
               class_2680 blockState = worldChunk.method_8320(mutable);
               if (heightmap0 != null && blockPredicate0.test(blockState)) {
                  ((HeightmapAccessor)heightmap0).callSet(x, z, searchY + 1);
                  heightmap0 = null;
                  heightmapsToUpdate--;
               }

               if (heightmap1 != null && blockPredicate1.test(blockState)) {
                  ((HeightmapAccessor)heightmap1).callSet(x, z, searchY + 1);
                  heightmap1 = null;
                  heightmapsToUpdate--;
               }

               if (heightmap2 != null && blockPredicate2.test(blockState)) {
                  ((HeightmapAccessor)heightmap2).callSet(x, z, searchY + 1);
                  heightmap2 = null;
                  heightmapsToUpdate--;
               }

               if (heightmap3 != null && blockPredicate3.test(blockState)) {
                  ((HeightmapAccessor)heightmap3).callSet(x, z, searchY + 1);
                  heightmap3 = null;
                  heightmapsToUpdate--;
               }
            }

            if (heightmap0 != null) {
               ((HeightmapAccessor)heightmap0).callSet(x, z, bottomY);
            }

            if (heightmap1 != null) {
               ((HeightmapAccessor)heightmap1).callSet(x, z, bottomY);
            }

            if (heightmap2 != null) {
               ((HeightmapAccessor)heightmap2).callSet(x, z, bottomY);
            }

            if (heightmap3 != null) {
               ((HeightmapAccessor)heightmap3).callSet(x, z, bottomY);
            }
         }
      }
   }
}
