package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1282;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1657.class)
public class PlayerAttackInvoker {
   @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
   private void hurt(class_1282 damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
      if (EntityEvent.LIVING_HURT.invoker().hurt((class_1309)this, damageSource, f).isFalse() && this instanceof class_1657) {
         cir.setReturnValue(false);
      }
   }
}
