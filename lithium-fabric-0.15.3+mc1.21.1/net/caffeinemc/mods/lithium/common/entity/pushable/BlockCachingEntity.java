package net.caffeinemc.mods.lithium.common.entity.pushable;

import net.minecraft.class_2680;

public interface BlockCachingEntity {
   default void lithium$OnBlockCacheDeleted() {
   }

   default void lithium$OnBlockCacheSet(class_2680 newState) {
   }

   default void lithium$SetClimbingMobCachingSectionUpdateBehavior(boolean listening) {
      throw new UnsupportedOperationException();
   }

   class_2680 lithium$getCachedFeetBlockState();
}
