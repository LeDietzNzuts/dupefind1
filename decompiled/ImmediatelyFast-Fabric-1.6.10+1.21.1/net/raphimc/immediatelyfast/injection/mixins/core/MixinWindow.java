package net.raphimc.immediatelyfast.injection.mixins.core;

import net.minecraft.class_1041;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.feature.core.BufferAllocatorPool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1041.class)
public abstract class MixinWindow {
   @Inject(
      method = "<init>(Lnet/minecraft/class_3678;Lnet/minecraft/class_323;Lnet/minecraft/class_543;Ljava/lang/String;Ljava/lang/String;)V",
      at = @At("RETURN")
   )
   private void initImmediatelyFast(CallbackInfo ci) {
      ImmediatelyFast.windowInit();
   }

   @Inject(method = "method_15998()V", at = @At("HEAD"))
   private void endFrame(CallbackInfo ci) {
      BufferAllocatorPool.onEndFrame();
   }
}
