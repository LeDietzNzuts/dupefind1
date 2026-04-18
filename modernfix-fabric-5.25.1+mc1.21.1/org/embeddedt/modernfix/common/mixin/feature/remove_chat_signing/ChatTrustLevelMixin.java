package org.embeddedt.modernfix.common.mixin.feature.remove_chat_signing;

import net.minecraft.class_7595;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_7595.class)
@ClientOnlyMixin
public class ChatTrustLevelMixin {
   @Inject(method = "evaluate", at = @At("HEAD"), cancellable = true)
   private static void alwaysShowSecure(CallbackInfoReturnable<class_7595> cir) {
      cir.setReturnValue(class_7595.field_39780);
   }
}
