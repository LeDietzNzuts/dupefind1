package net.caffeinemc.mods.lithium.common.ai.non_poi_block_search;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_1309;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4538;

public class CommonBlockSearchesCheckAndCache {
   public static Optional<class_2338> blockPosFindClosestMatch(
      class_4538 levelReader,
      class_1309 livingEntity,
      int horizontalRange,
      int verticalRange,
      Predicate<class_2680> blockStatePredicate,
      boolean shouldChunkLoad
   ) {
      class_2338 mobPos = livingEntity.method_24515();
      CheckAndCacheBlockChecker checker = new CheckAndCacheBlockChecker(
         mobPos, horizontalRange, verticalRange, levelReader, blockStatePredicate, shouldChunkLoad
      );
      checker.initializeChunks();
      return checker.shouldStop() ? Optional.empty() : class_2338.method_25997(mobPos, horizontalRange, verticalRange, checker::checkPosition);
   }
}
