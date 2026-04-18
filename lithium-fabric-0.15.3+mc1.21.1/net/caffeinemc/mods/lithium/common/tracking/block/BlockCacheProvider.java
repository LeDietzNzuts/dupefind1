package net.caffeinemc.mods.lithium.common.tracking.block;

import net.minecraft.class_1297;

public interface BlockCacheProvider {
   BlockCache lithium$getBlockCache();

   default BlockCache getUpdatedBlockCache(class_1297 entity) {
      BlockCache bc = this.lithium$getBlockCache();
      bc.updateCache(entity);
      return bc;
   }
}
