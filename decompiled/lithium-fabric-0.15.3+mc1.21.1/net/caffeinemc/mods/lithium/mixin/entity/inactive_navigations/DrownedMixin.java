package net.caffeinemc.mods.lithium.mixin.entity.inactive_navigations;

import net.caffeinemc.mods.lithium.common.entity.NavigatingEntity;
import net.minecraft.class_1551;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1551.class)
public class DrownedMixin {
   @Inject(method = "method_5790()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1551;method_5796(Z)V"))
   private void updateInactivityState(CallbackInfo ci) {
      ((NavigatingEntity)this).lithium$updateNavigationRegistration();
   }
}
