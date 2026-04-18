package net.raphimc.immediatelyfast.injection.mixins.hud_batching.compat;

import net.minecraft.class_286;
import net.raphimc.immediatelyfast.feature.batching.BatchingBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_286.class)
public abstract class MixinBufferRenderer {
   @Inject(method = {"method_43437(Lnet/minecraft/class_9801;)V", "method_43438(Lnet/minecraft/class_9801;)V"}, at = @At("HEAD"))
   private static void checkForDrawCallWhileBatching(CallbackInfo ci) {
      if (BatchingBuffers.isHudBatching()) {
         BatchingBuffers.tryForceDrawHudBuffers();
      }
   }
}
