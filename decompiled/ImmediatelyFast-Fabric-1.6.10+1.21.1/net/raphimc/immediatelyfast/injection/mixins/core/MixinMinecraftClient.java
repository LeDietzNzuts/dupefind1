package net.raphimc.immediatelyfast.injection.mixins.core;

import net.minecraft.class_310;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_310.class)
public abstract class MixinMinecraftClient {
   @Inject(method = "<init>(Lnet/minecraft/class_542;)V", at = @At("RETURN"))
   private void initImmediatelyFast(CallbackInfo ci) {
      ImmediatelyFast.lateInit();
   }

   @Inject(method = "method_1481(Lnet/minecraft/class_638;Lnet/minecraft/class_434$class_9678;)V", at = @At("HEAD"))
   private void callOnWorldJoin(CallbackInfo ci) {
      ImmediatelyFast.onWorldJoin();
   }
}
