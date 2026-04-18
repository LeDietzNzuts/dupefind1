package net.caffeinemc.mods.lithium.mixin.entity.inactive_navigations;

import net.caffeinemc.mods.lithium.common.entity.NavigatingEntity;
import net.minecraft.class_1551;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/class_1551$class_1554")
public class DrownedEntityLeaveWaterGoalMixin {
   @Shadow
   @Final
   private class_1551 field_7237;

   @Inject(method = "method_6269()V", at = @At("RETURN"))
   private void updateInactivityState(CallbackInfo ci) {
      ((NavigatingEntity)this.field_7237).lithium$updateNavigationRegistration();
   }
}
