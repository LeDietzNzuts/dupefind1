package net.caffeinemc.mods.lithium.mixin.ai.useless_sensors.goat_item_sensor;

import net.caffeinemc.mods.lithium.common.ai.brain.SensorHelper;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_155;
import net.minecraft.class_1937;
import net.minecraft.class_4095;
import net.minecraft.class_4140;
import net.minecraft.class_4149;
import net.minecraft.class_6053;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_6053.class)
public abstract class GoatMixin extends class_1309 {
   protected GoatMixin(class_1299<? extends class_1309> entityType, class_1937 world) {
      super(entityType, world);
   }

   @Shadow
   public abstract class_4095<class_6053> method_18868();

   @Inject(method = "<init>(Lnet/minecraft/class_1299;Lnet/minecraft/class_1937;)V", at = @At("RETURN"))
   private void disableItemSensor(CallbackInfo ci) {
      if (!this.method_18868().method_18896(class_4140.field_22332)) {
         SensorHelper.disableSensor(this, class_4149.field_22358);
      } else if (class_155.field_1125) {
         throw new IllegalStateException(
            "Goat Entity has a nearest visible wanted item memory module! The mixin.ai.useless_sensors.goat_item_sensor should probably be removed permanently!"
         );
      }
   }
}
