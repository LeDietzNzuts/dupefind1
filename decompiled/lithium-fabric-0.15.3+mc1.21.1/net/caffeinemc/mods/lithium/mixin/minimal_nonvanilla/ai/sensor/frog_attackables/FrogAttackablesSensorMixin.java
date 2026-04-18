package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.ai.sensor.frog_attackables;

import net.minecraft.class_1309;
import net.minecraft.class_4148;
import net.minecraft.class_7100;
import net.minecraft.class_7102;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_7100.class)
public class FrogAttackablesSensorMixin {
   @Redirect(
      method = "method_35148(Lnet/minecraft/class_1309;Lnet/minecraft/class_1309;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4148;method_36982(Lnet/minecraft/class_1309;Lnet/minecraft/class_1309;)Z")
   )
   private boolean checkTypeAndPredicate(class_1309 entity, class_1309 target) {
      return class_7102.method_41358(target) && class_4148.method_36982(entity, target);
   }

   @Redirect(
      method = "method_35148(Lnet/minecraft/class_1309;Lnet/minecraft/class_1309;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_7102;method_41358(Lnet/minecraft/class_1309;)Z"),
      require = 0
   )
   private boolean skipCheckTypeAgain(class_1309 entity) {
      return true;
   }
}
