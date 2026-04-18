package net.caffeinemc.mods.lithium.fabric.mixin.experimental.entity.block_caching.fluid_pushing;

import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCache;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCacheProvider;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3611;
import net.minecraft.class_6862;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1297.class)
public abstract class EntityMixin implements BlockCacheProvider {
   @Shadow
   protected Object2DoubleMap<class_6862<class_3611>> field_5964;

   @Inject(method = "method_5692(Lnet/minecraft/class_6862;D)Z", at = @At("HEAD"), cancellable = true)
   private void skipFluidSearchUsingCache(class_6862<class_3611> fluid, double speed, CallbackInfoReturnable<Boolean> cir) {
      BlockCache bc = this.getUpdatedBlockCache((class_1297)this);
      double fluidHeight = bc.getStationaryFluidHeightOrDefault(fluid, -1.0);
      if (fluidHeight != -1.0) {
         this.field_5964.put(fluid, fluidHeight);
         boolean touchingFluid = fluidHeight != 0.0;
         cir.setReturnValue(touchingFluid);
      }
   }

   @Inject(method = "method_5692(Lnet/minecraft/class_6862;D)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_243;method_1033()D", ordinal = 0))
   private void cacheFluidSearchResult(
      class_6862<class_3611> fluid,
      double speed,
      CallbackInfoReturnable<Boolean> cir,
      @Local(ordinal = 1) double fluidHeight,
      @Local(ordinal = 1) boolean touchingFluid,
      @Local class_243 fluidPush
   ) {
      BlockCache bc = this.lithium$getBlockCache();
      if (bc.isTracking() && fluidPush.method_1027() == 0.0) {
         if (touchingFluid == (fluidHeight == 0.0)) {
            throw new IllegalArgumentException(
               "Expected fluid touching IFF fluid height is not 0! Fluid height: " + fluidHeight + " Touching fluid: " + touchingFluid + " Fluid Tag: " + fluid
            );
         }

         bc.setCachedFluidHeight(fluid, fluidHeight);
      }
   }
}
