package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.class_2985;
import net.minecraft.class_3222;
import net.minecraft.class_8779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2985.class)
public class MixinPlayerAdvancements {
   @Shadow
   private class_3222 field_13391;

   @Inject(
      method = "award",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/advancements/AdvancementRewards;grant(Lnet/minecraft/server/level/ServerPlayer;)V",
         shift = Shift.AFTER
      )
   )
   private void award(class_8779 advancement, String string, CallbackInfoReturnable<Boolean> cir) {
      PlayerEvent.PLAYER_ADVANCEMENT.invoker().award(this.field_13391, advancement);
   }
}
