package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1282;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1309.class, class_1657.class, class_3222.class})
public class LivingDeathInvoker {
   @Inject(method = "die", at = @At("HEAD"), cancellable = true)
   private void die(class_1282 source, CallbackInfo ci) {
      if (EntityEvent.LIVING_DEATH.invoker().die((class_1309)this, source).isFalse()) {
         ci.cancel();
      }
   }
}
