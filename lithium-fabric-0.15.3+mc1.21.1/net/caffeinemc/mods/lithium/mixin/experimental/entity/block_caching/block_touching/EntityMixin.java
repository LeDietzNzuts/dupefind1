package net.caffeinemc.mods.lithium.mixin.experimental.entity.block_caching.block_touching;

import net.caffeinemc.mods.lithium.common.block.BlockStateFlagHolder;
import net.caffeinemc.mods.lithium.common.block.BlockStateFlags;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCache;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCacheProvider;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1297.class)
public abstract class EntityMixin implements BlockCacheProvider {
   @Inject(method = "method_5852()V", at = @At("HEAD"), cancellable = true)
   private void cancelIfSkippable(CallbackInfo ci) {
      if (!(this instanceof class_3222)) {
         BlockCache bc = this.getUpdatedBlockCache((class_1297)this);
         if (bc.canSkipBlockTouching()) {
            ci.cancel();
         }
      }
   }

   @Inject(method = "method_5852()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2338;method_10263()I", ordinal = 0))
   private void assumeNoTouchableBlock(CallbackInfo ci) {
      BlockCache bc = this.lithium$getBlockCache();
      if (bc.isTracking()) {
         bc.setCanSkipBlockTouching(true);
      }
   }

   @Inject(
      method = "method_5852()V",
      locals = LocalCapture.CAPTURE_FAILHARD,
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2680;method_26178(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_1297;)V"
      )
   )
   private void checkTouchableBlock(
      CallbackInfo ci, class_238 box, class_2338 blockPos, class_2338 blockPos2, class_2339 mutable, int i, int j, int k, class_2680 blockState
   ) {
      BlockCache bc = this.lithium$getBlockCache();
      if (bc.canSkipBlockTouching() && 0 != (((BlockStateFlagHolder)blockState).lithium$getAllFlags() & 1 << BlockStateFlags.ENTITY_TOUCHABLE.getIndex())) {
         bc.setCanSkipBlockTouching(false);
      }
   }
}
