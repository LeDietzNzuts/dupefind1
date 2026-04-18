package dev.architectury.mixin.fabric.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.event.events.common.EntityEvent;
import java.util.function.Supplier;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2874;
import net.minecraft.class_3695;
import net.minecraft.class_5321;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_6880;
import net.minecraft.class_761;
import net.minecraft.class_638.class_5271;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_638.class)
public class MixinClientLevel {
   @Inject(method = "<init>", at = @At("RETURN"))
   private void construct(
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
      ClientLifecycleEvent.CLIENT_LEVEL_LOAD.invoker().act((class_638)this);
   }

   @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
   private void addEntity(class_1297 entity, CallbackInfo ci) {
      if (EntityEvent.ADD.invoker().add(entity, (class_638)this).isFalse()) {
         ci.cancel();
      }
   }
}
