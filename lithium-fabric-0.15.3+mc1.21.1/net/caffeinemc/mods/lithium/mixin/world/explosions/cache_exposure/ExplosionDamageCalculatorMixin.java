package net.caffeinemc.mods.lithium.mixin.world.explosions.cache_exposure;

import net.caffeinemc.mods.lithium.common.world.ExplosionCache;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_243;
import net.minecraft.class_5362;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5362.class)
public class ExplosionDamageCalculatorMixin {
   @Unique
   private ExplosionCache explosion;

   @Inject(method = "method_55115(Lnet/minecraft/class_1927;Lnet/minecraft/class_1297;)F", at = @At("HEAD"))
   private void captureExplosion(class_1927 explosion, class_1297 entity, CallbackInfoReturnable<Float> cir) {
      this.explosion = (ExplosionCache)explosion;
   }

   @Redirect(
      method = "method_55115(Lnet/minecraft/class_1927;Lnet/minecraft/class_1297;)F",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1927;method_17752(Lnet/minecraft/class_243;Lnet/minecraft/class_1297;)F")
   )
   private float useCachedExposure(class_243 source, class_1297 entity) {
      float exposure = class_1927.method_17752(source, entity);
      this.explosion.lithium_fabric$cacheExposure(entity, exposure);
      return exposure;
   }
}
