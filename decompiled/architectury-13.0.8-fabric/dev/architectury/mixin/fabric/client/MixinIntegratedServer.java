package dev.architectury.mixin.fabric.client;

import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.class_1132;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1132.class)
public class MixinIntegratedServer {
   @Inject(method = "initServer", at = @At("RETURN"), cancellable = true)
   private void initServer(CallbackInfoReturnable<Boolean> cir) {
      if (cir.getReturnValueZ()) {
         LifecycleEvent.SERVER_STARTING.invoker().stateChanged((MinecraftServer)this);
      }
   }
}
