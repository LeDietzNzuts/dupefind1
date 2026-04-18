package net.caffeinemc.mods.lithium.mixin.entity.inactive_navigations;

import net.caffeinemc.mods.lithium.common.entity.NavigatingEntity;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1309.class)
public class LivingEntityMixin {
   @Inject(method = "method_5848()V", at = @At("RETURN"))
   public void handleStopRiding(CallbackInfo ci) {
      if (this instanceof NavigatingEntity navigatingEntity) {
         navigatingEntity.lithium$updateNavigationRegistration();
      }
   }
}
