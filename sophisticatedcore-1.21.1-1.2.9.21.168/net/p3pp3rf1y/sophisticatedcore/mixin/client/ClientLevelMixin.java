package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import java.util.function.Supplier;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2874;
import net.minecraft.class_310;
import net.minecraft.class_3695;
import net.minecraft.class_5321;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_6880;
import net.minecraft.class_761;
import net.minecraft.class_638.class_5271;
import net.p3pp3rf1y.sophisticatedcore.event.client.ClientLifecycleEvents;
import net.p3pp3rf1y.sophisticatedcore.event.common.EntityEvents;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_638.class)
public class ClientLevelMixin {
   @Shadow
   @Final
   private class_310 field_3729;

   @Inject(method = "<init>", at = @At("RETURN"))
   private void sophisticatedcore$construct(
      class_634 clientPacketListener,
      class_5271 clientLevelData,
      class_5321<class_1937> resourceKey,
      class_6880<class_2874> holder,
      int i,
      int j,
      Supplier<class_3695> supplier,
      class_761 levelRenderer,
      boolean bl,
      long l,
      CallbackInfo ci
   ) {
      ((ClientLifecycleEvents.Load)ClientLifecycleEvents.CLIENT_LEVEL_LOAD.invoker()).onWorldLoad(this.field_3729, MixinHelper.cast(this));
   }

   @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
   public void sophisticatedcore$addEntityEvent(class_1297 entity, CallbackInfo ci) {
      if (((EntityEvents.JoinWorld)EntityEvents.ON_JOIN_WORLD.invoker()).onJoinWorld(entity, MixinHelper.cast(this), false)) {
         ci.cancel();
      }
   }
}
