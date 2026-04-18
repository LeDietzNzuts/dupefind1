package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import net.minecraft.class_3701;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3701.class)
public class MixinOcelot {
   @Inject(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Ocelot;setTrusting(Z)V"), cancellable = true)
   private void mobInteract(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
      if (EntityEvent.ANIMAL_TAME.invoker().tame((class_1429)this, player).isFalse()) {
         cir.setReturnValue(class_1269.field_5811);
      }
   }
}
