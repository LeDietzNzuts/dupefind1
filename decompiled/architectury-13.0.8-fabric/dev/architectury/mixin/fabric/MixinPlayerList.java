package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.class_2535;
import net.minecraft.class_3222;
import net.minecraft.class_3324;
import net.minecraft.class_8792;
import net.minecraft.class_1297.class_5529;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3324.class)
public class MixinPlayerList {
   @Inject(method = "placeNewPlayer", at = @At("RETURN"))
   private void placeNewPlayer(class_2535 connection, class_3222 serverPlayer, class_8792 commonListenerCookie, CallbackInfo ci) {
      PlayerEvent.PLAYER_JOIN.invoker().join(serverPlayer);
   }

   @Inject(method = "remove", at = @At("HEAD"))
   private void remove(class_3222 serverPlayer, CallbackInfo ci) {
      PlayerEvent.PLAYER_QUIT.invoker().quit(serverPlayer);
   }

   @Inject(method = "respawn", at = @At("RETURN"))
   private void respawn(class_3222 serverPlayer, boolean bl, class_5529 removalReason, CallbackInfoReturnable<class_3222> cir) {
      PlayerEvent.PLAYER_RESPAWN.invoker().respawn((class_3222)cir.getReturnValue(), bl, removalReason);
   }
}
