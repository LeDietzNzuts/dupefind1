package net.caffeinemc.mods.lithium.mixin.ai.useless_sensors.parent_animal_sensor;

import java.util.Optional;
import net.caffeinemc.mods.lithium.common.ai.brain.SensorHelper;
import net.minecraft.class_1296;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2940;
import net.minecraft.class_4140;
import net.minecraft.class_4149;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1296.class)
public abstract class AgeableMobMixin extends class_1309 {
   @Shadow
   public abstract boolean method_6109();

   protected AgeableMobMixin(class_1299<? extends class_1309> entityType, class_1937 world) {
      super(entityType, world);
   }

   @Inject(
      method = "method_5674(Lnet/minecraft/class_2940;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1296;method_18382()V"),
      require = 1,
      allow = 1
   )
   private void handleParentSensor(class_2940<?> data, CallbackInfo ci) {
      if (!this.method_37908().method_8608()) {
         if (this.method_6109()) {
            SensorHelper.enableSensor((AgeableMobMixin & class_1296)this, class_4149.field_25362, true);
         } else {
            SensorHelper.disableSensor((class_1296)this, class_4149.field_25362);
            if (this.method_18868().method_18896(class_4140.field_25359)) {
               this.method_18868().method_18879(class_4140.field_25359, Optional.empty());
            }
         }
      }
   }
}
