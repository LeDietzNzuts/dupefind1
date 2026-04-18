package dev.architectury.mixin.fabric.client;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_636.class)
public class MixinMultiPlayerGameMode {
   @Inject(
      method = "interact",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V",
         shift = Shift.AFTER
      ),
      cancellable = true
   )
   private void entityInteract(class_1657 player, class_1297 entity, class_1268 interactionHand, CallbackInfoReturnable<class_1269> cir) {
      EventResult result = InteractionEvent.INTERACT_ENTITY.invoker().interact(player, entity, interactionHand);
      if (result.isPresent()) {
         cir.setReturnValue(result.asMinecraft());
      }
   }
}
