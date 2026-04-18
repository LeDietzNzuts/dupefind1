package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1387;
import net.minecraft.class_1496;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1387.class)
public class HorseTameInvoker {
   @Shadow
   @Final
   private class_1496 field_6602;

   @Inject(
      method = "tick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;tameWithName(Lnet/minecraft/world/entity/player/Player;)Z"),
      cancellable = true
   )
   private void tick(CallbackInfo ci) {
      if (EntityEvent.ANIMAL_TAME.invoker().tame(this.field_6602, (class_1657)this.field_6602.method_5685().get(0)).isFalse()) {
         ci.cancel();
      }
   }
}
