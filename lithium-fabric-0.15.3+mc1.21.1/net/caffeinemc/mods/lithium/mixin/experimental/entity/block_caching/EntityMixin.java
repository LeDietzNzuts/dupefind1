package net.caffeinemc.mods.lithium.mixin.experimental.entity.block_caching;

import net.caffeinemc.mods.lithium.common.tracking.block.BlockCache;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCacheProvider;
import net.minecraft.class_1297;
import net.minecraft.class_1297.class_5529;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1297.class)
public class EntityMixin implements BlockCacheProvider {
   private final BlockCache blockCache = new BlockCache();

   @Override
   public BlockCache lithium$getBlockCache() {
      return this.blockCache;
   }

   @Inject(method = "method_5650(Lnet/minecraft/class_1297$class_5529;)V", at = @At("HEAD"))
   private void removeBlockCache(class_5529 reason, CallbackInfo ci) {
      this.blockCache.remove();
   }
}
