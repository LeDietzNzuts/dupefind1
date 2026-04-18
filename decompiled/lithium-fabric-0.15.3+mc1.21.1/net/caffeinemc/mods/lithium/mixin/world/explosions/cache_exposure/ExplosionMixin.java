package net.caffeinemc.mods.lithium.mixin.world.explosions.cache_exposure;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.lithium.common.world.ExplosionCache;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1927.class)
public abstract class ExplosionMixin implements ExplosionCache {
   @Unique
   private float cachedExposure;
   @Unique
   private class_1297 cachedEntity;

   @Override
   public void lithium_fabric$cacheExposure(class_1297 entity, float exposure) {
      this.cachedExposure = exposure;
      this.cachedEntity = entity;
   }

   @WrapOperation(
      method = "method_8348()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1927;method_17752(Lnet/minecraft/class_243;Lnet/minecraft/class_1297;)F")
   )
   private float returnCachedExposure(class_243 source, class_1297 entity, Operation<Float> original) {
      return this.cachedEntity == entity ? this.cachedExposure : (Float)original.call(new Object[]{source, entity});
   }
}
