package net.raphimc.immediatelyfast.injection.mixins.screen_batching.compat;

import net.minecraft.class_332;
import net.minecraft.class_408;
import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_408.class)
public abstract class MixinChatScreen {
   @Inject(
      method = "method_25394(Lnet/minecraft/class_332;IIF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4717;method_23923(Lnet/minecraft/class_332;II)V")
   )
   private void forceDraw(class_332 drawContext, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (drawContext.field_44658 instanceof BatchableBufferSource) {
         drawContext.method_51452();
      }
   }
}
