package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.p3pp3rf1y.sophisticatedcore.event.common.EntityEvents;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3218.class)
public abstract class ServerLevelMixin {
   @Inject(method = "addPlayer", at = @At("HEAD"), cancellable = true)
   public void sophisticatedcore$addEntityEvent(class_3222 serverPlayer, CallbackInfo ci) {
      if (((EntityEvents.JoinWorld)EntityEvents.ON_JOIN_WORLD.invoker()).onJoinWorld(serverPlayer, MixinHelper.cast(this), false)) {
         ci.cancel();
      }
   }
}
