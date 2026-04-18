package net.raphimc.immediatelyfast.fabric.injection.mixins.hud_batching.compat.appleskin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_332;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "squeek/appleskin/client/HUDOverlayHandler", remap = false)
@Pseudo
public abstract class MixinAppleSkin_HUDOverlayHandler {
   @Inject(method = "drawExhaustionOverlay", at = @At("RETURN"))
   private void forceDrawBatch(CallbackInfo ci, @Local(argsOnly = true) class_332 drawContext) {
      if (ImmediatelyFast.runtimeConfig.hud_batching) {
         drawContext.method_51452();
      }
   }
}
