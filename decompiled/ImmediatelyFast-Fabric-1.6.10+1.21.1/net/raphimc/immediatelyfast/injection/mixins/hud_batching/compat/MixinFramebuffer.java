package net.raphimc.immediatelyfast.injection.mixins.hud_batching.compat;

import net.minecraft.class_276;
import net.raphimc.immediatelyfast.feature.batching.BatchingBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_276.class)
public abstract class MixinFramebuffer {
   @Inject(method = "method_1233(IIZ)V", at = @At("HEAD"))
   private void checkForDrawCallWhileBatching(CallbackInfo ci) {
      if (BatchingBuffers.isHudBatching()) {
         BatchingBuffers.tryForceDrawHudBuffers();
      }
   }
}
