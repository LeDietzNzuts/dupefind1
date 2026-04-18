package net.raphimc.immediatelyfast.injection.mixins.hud_batching;

import net.minecraft.class_332;
import net.minecraft.class_9080;
import net.minecraft.class_9779;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_9080.class)
public abstract class MixinLayeredDrawer {
   @Inject(
      method = "method_55813(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_9080$class_9081;render(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V",
         shift = Shift.AFTER
      )
   )
   private void renderBatch(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (ImmediatelyFast.runtimeConfig.hud_batching) {
         context.method_51452();
      }
   }
}
