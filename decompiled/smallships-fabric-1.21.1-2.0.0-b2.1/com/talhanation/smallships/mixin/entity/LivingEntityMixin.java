package com.talhanation.smallships.mixin.entity;

import com.talhanation.smallships.world.entity.IMixinEntity;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1309.class)
public class LivingEntityMixin {
   @Redirect(method = "dismountVehicle", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;dismountTo(DDD)V"))
   public void redirectDismountTo(class_1309 instance, double x, double y, double z) {
      IMixinEntity entity = (IMixinEntity)instance;
      if (!instance.method_37908().method_8608() && entity.doNotDismountToCoordinates()) {
         entity.setPreventDismountToCoordinates(false);
      } else {
         instance.method_33567(x, y, z);
      }
   }
}
