package net.caffeinemc.mods.lithium.common.world.block_pattern_matching;

import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_4076;
import net.minecraft.class_4538;
import net.minecraft.class_9380;

public class BlockSearch {
   public static boolean hasAtLeast(class_4538 levelReader, class_9380 searchBox, class_2248 requiredBlock, int requiredBlockCount) {
      Predicate<class_2680> predicate = blockState -> blockState.method_27852(requiredBlock);

      for (int chunkX = class_4076.method_18675(searchBox.comp_2466().method_10263());
         chunkX <= class_4076.method_18675(searchBox.comp_2467().method_10263());
         chunkX++
      ) {
         for (int chunkZ = class_4076.method_18675(searchBox.comp_2466().method_10260());
            chunkZ <= class_4076.method_18675(searchBox.comp_2467().method_10260());
            chunkZ++
         ) {
            class_2791 chunk = levelReader.method_8392(chunkX, chunkZ);
            int minSectionYIndex = Pos.SectionYIndex.fromBlockCoord(levelReader, searchBox.comp_2466().method_10264());
            int maxSectionYIndex = Pos.SectionYIndex.fromBlockCoord(levelReader, searchBox.comp_2467().method_10264());

            for (int sectionYIndex = minSectionYIndex; sectionYIndex <= maxSectionYIndex; sectionYIndex++) {
               if (sectionYIndex >= 0 && sectionYIndex < chunk.method_32890()) {
                  class_2826 section = chunk.method_38259(sectionYIndex);
                  if (section.method_19523(predicate)) {
                     int sectionYCoord = Pos.SectionYCoord.fromSectionIndex(levelReader, sectionYIndex);
                     requiredBlockCount -= countBlocksInBoxInSection(
                        section,
                        Math.max(searchBox.comp_2466().method_10263(), chunkX << 4),
                        Math.max(searchBox.comp_2466().method_10264(), sectionYCoord << 4),
                        Math.max(searchBox.comp_2466().method_10260(), chunkZ << 4),
                        Math.min(searchBox.comp_2467().method_10263(), (chunkX << 4) + 15),
                        Math.min(searchBox.comp_2467().method_10264(), (sectionYCoord << 4) + 15),
                        Math.min(searchBox.comp_2467().method_10260(), (chunkZ << 4) + 15),
                        requiredBlock,
                        requiredBlockCount
                     );
                     if (requiredBlockCount <= 0) {
                        return true;
                     }
                  }
               } else if (requiredBlock == class_2246.field_10243) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static int countBlocksInBoxInSection(
      class_2826 section, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, class_2248 requiredBlock, int findMax
   ) {
      int found = 0;

      for (int y = minY; y <= maxY; y++) {
         for (int z = minZ; z <= maxZ; z++) {
            for (int x = minX; x <= maxX; x++) {
               if (section.method_12254(x & 15, y & 15, z & 15).method_27852(requiredBlock)) {
                  if (++found >= findMax) {
                     return found;
                  }
               }
            }
         }
      }

      return found;
   }
}
