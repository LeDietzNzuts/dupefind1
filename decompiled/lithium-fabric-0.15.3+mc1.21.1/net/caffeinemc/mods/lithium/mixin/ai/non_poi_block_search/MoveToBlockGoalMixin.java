package net.caffeinemc.mods.lithium.mixin.ai.non_poi_block_search;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongListIterator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.ai.non_poi_block_search.CheckAndCacheBlockChecker;
import net.caffeinemc.mods.lithium.common.ai.non_poi_block_search.LithiumMoveToBlockGoal;
import net.caffeinemc.mods.lithium.common.ai.non_poi_block_search.NonPOISearchDistances;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.minecraft.class_1314;
import net.minecraft.class_1367;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_4076;
import net.minecraft.class_4538;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_1367.class)
public abstract class MoveToBlockGoalMixin implements LithiumMoveToBlockGoal {
   @Shadow
   @Final
   protected class_1314 field_6516;
   @Shadow
   @Final
   private int field_6510;
   @Shadow
   @Final
   private int field_6519;
   @Shadow
   protected int field_6515;
   @Shadow
   protected class_2338 field_6512;

   @Override
   public boolean lithium$findNearestBlock(
      Predicate<class_2680> requiredBlock, BiPredicate<class_2791, class_2339> lithium$isValidTarget, boolean shouldChunkLoad
   ) {
      class_2338 center = this.field_6516.method_24515().method_10069(0, -1, 0);
      class_4538 levelReader = this.field_6516.method_37908();
      CheckAndCacheBlockChecker checker = new CheckAndCacheBlockChecker(
         center, this.field_6510 - 1, this.field_6519, levelReader, requiredBlock, shouldChunkLoad
      );
      LongArrayList sortedChunksMaybeWithBlock = new LongArrayList(checker.getChunkSize());
      checker.initializeChunks(sortedChunksMaybeWithBlock::addLast);
      if (checker.shouldStop()) {
         return false;
      } else {
         int minY = Pos.BlockCoord.getMinY(levelReader);
         int maxY = Pos.BlockCoord.getMaxYInclusive(levelReader);
         return !checker.hasUnloadedPossibleChunks()
            ? this.lithium$chunkAwareSearch(center, lithium$isValidTarget, checker, sortedChunksMaybeWithBlock, minY, maxY)
            : this.lithium$vanillaOrderSearch(center, lithium$isValidTarget, checker, minY, maxY);
      }
   }

   @Unique
   private boolean lithium$vanillaOrderSearch(
      class_2338 center, BiPredicate<class_2791, class_2339> lithium$isValidTarget, CheckAndCacheBlockChecker checker, int minY, int maxY
   ) {
      class_2339 currentPos = new class_2339();
      int centerY = center.method_10264();

      for (int layer = this.field_6515; layer <= this.field_6519; layer = layer > 0 ? -layer : 1 - layer) {
         int y = centerY + layer;
         if (y >= minY && y <= maxY) {
            for (int ring = 0; ring < this.field_6510; ring++) {
               for (int dX = 0; dX <= ring; dX = dX > 0 ? -dX : 1 - dX) {
                  for (int dZ = dX < ring && dX > -ring ? ring : 0; dZ <= ring; dZ = dZ > 0 ? -dZ : 1 - dZ) {
                     currentPos.method_25504(center, dX, layer, dZ);
                     if (this.field_6516.method_18407(currentPos) && checker.checkPosition(currentPos)) {
                        class_2791 chunkAccess = checker.getCachedChunkAccess(currentPos);
                        if (lithium$isValidTarget.test(chunkAccess, currentPos)) {
                           this.field_6512 = currentPos;
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   @Unique
   private boolean lithium$chunkAwareSearch(
      class_2338 center,
      BiPredicate<class_2791, class_2339> lithium$isValidTarget,
      CheckAndCacheBlockChecker checker,
      LongArrayList sortedChunksMaybeWithBlock,
      int minY,
      int maxY
   ) {
      sortedChunksMaybeWithBlock.sort(
         (chunkLong0, chunkLong1) -> NonPOISearchDistances.MoveToBlockGoalDistances.getMinimumSortOrderOfChunk(center, chunkLong0)
            - NonPOISearchDistances.MoveToBlockGoalDistances.getMinimumSortOrderOfChunk(center, chunkLong1)
      );
      Predicate<class_2680> requiredBlock = checker.blockStatePredicate;
      int minSectionY = checker.minSectionY;
      class_2339 foundPos = new class_2339();
      class_2339 currentPos = new class_2339();

      for (int layer = this.field_6515; layer <= this.field_6519; layer = layer > 0 ? -layer : 1 - layer) {
         int y = center.method_10264() + layer;
         if (y >= minY && y <= maxY) {
            int chunkY = class_4076.method_18675(y);
            int ySectionIndex = chunkY - minSectionY;
            int closestFound = Integer.MAX_VALUE;
            int ringMax = this.field_6510 - 1;
            LongListIterator var17 = sortedChunksMaybeWithBlock.iterator();

            while (var17.hasNext()) {
               long chunkPos = (Long)var17.next();
               int chunkX = class_1923.method_8325(chunkPos);
               int chunkZ = class_1923.method_8332(chunkPos);
               if (closestFound < NonPOISearchDistances.MoveToBlockGoalDistances.getMinimumSortOrderOfChunk(center, chunkX, chunkZ)) {
                  break;
               }

               if (checker.checkCachedSection(chunkX, chunkY, chunkZ)) {
                  class_2791 chunkAccess = checker.getCachedChunkAccess(chunkPos);
                  int chunkBlockX = class_4076.method_18688(chunkX);
                  int xMin = Math.max(center.method_10263() - ringMax, chunkBlockX);
                  int xMax = Math.min(center.method_10263() + ringMax, chunkBlockX + 15);
                  int chunkBlockZ = class_4076.method_18688(chunkZ);
                  int zMin = Math.max(center.method_10260() - ringMax, chunkBlockZ);
                  int zMax = Math.min(center.method_10260() + ringMax, chunkBlockZ + 15);
                  class_2826 levelChunkSection = chunkAccess.method_12006()[ySectionIndex];

                  for (int z = zMin; z <= zMax; z++) {
                     for (int x = xMin; x <= xMax; x++) {
                        int dX = x - center.method_10263();
                        int dZ = z - center.method_10260();
                        int ring = NonPOISearchDistances.MoveToBlockGoalDistances.getRing(dX, dZ);
                        int currentDistance = NonPOISearchDistances.MoveToBlockGoalDistances.getVanillaSortOrderInt(ring, dX, dZ);
                        if (currentDistance < closestFound
                           && this.field_6516.method_18407(currentPos.method_10103(x, y, z))
                           && requiredBlock.test(levelChunkSection.method_12254(x & 15, y & 15, z & 15))
                           && lithium$isValidTarget.test(chunkAccess, currentPos)) {
                           ringMax = ring;
                           xMin = Math.max(center.method_10263() - ring, chunkBlockX);
                           xMax = Math.min(center.method_10263() + ring, chunkBlockX + 15);
                           zMax = Math.min(center.method_10260() + ring, chunkBlockZ + 15);
                           foundPos.method_10103(x, y, z);
                           closestFound = currentDistance;
                        }
                     }
                  }
               }
            }

            if (closestFound < Integer.MAX_VALUE) {
               this.field_6512 = foundPos;
               return true;
            }
         }
      }

      return false;
   }
}
