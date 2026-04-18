package org.embeddedt.modernfix.common.mixin.feature.remove_telemetry;

import net.minecraft.class_6628;
import net.minecraft.class_7965;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = class_6628.class, priority = 1500)
@ClientOnlyMixin
public class ClientTelemetryManagerMixin {
   @Inject(method = "createEventSender", at = @At("HEAD"), cancellable = true)
   private void disableTelemetrySender(CallbackInfoReturnable<class_7965> cir) {
      cir.setReturnValue(class_7965.field_41434);
   }
}
