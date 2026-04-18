package net.caffeinemc.mods.lithium.mixin.ai.sensor.secondary_poi;

import net.minecraft.class_1646;
import net.minecraft.class_3218;
import net.minecraft.class_4140;
import net.minecraft.class_4221;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4221.class)
public class SecondaryPoiSensorMixin {
   @Inject(method = "method_19617(Lnet/minecraft/class_3218;Lnet/minecraft/class_1646;)V", at = @At("HEAD"), cancellable = true)
   private void skipUselessSense(class_3218 serverWorld, class_1646 villagerEntity, CallbackInfo ci) {
      if (villagerEntity.method_7231().method_16924().comp_822().isEmpty()) {
         villagerEntity.method_18868().method_18875(class_4140.field_18873);
         ci.cancel();
      }
   }
}
