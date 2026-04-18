package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1321;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1321.class)
public class MixinTamableAnimal {
   @Inject(method = "tame", at = @At("HEAD"), cancellable = true)
   private void tame(class_1657 player, CallbackInfo ci) {
      if (EntityEvent.ANIMAL_TAME.invoker().tame((class_1429)this, player).isFalse()) {
         ci.cancel();
      }
   }
}
