package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1269;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.p3pp3rf1y.sophisticatedcore.event.client.ClientRawInputEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_312.class)
public class MouseHandlerMixin {
   @Shadow
   @Final
   private class_310 field_1779;

   @Inject(
      method = "onScroll",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/MouseHandler;accumulatedScrollY:D", ordinal = 6, shift = Shift.AFTER),
      cancellable = true
   )
   private void sophisticatedCore$onScroll(
      long handle, double xOffset, double yOffset, CallbackInfo ci, @Local(ordinal = 2) double deltaX, @Local(ordinal = 3) double deltaY
   ) {
      if (handle == this.field_1779.method_22683().method_4490()) {
         class_1269 result = ((ClientRawInputEvent.MouseScrolled)ClientRawInputEvent.MOUSE_SCROLLED.invoker()).mouseScrolled(this.field_1779, deltaX, deltaY);
         if (result != class_1269.field_5811) {
            ci.cancel();
         }
      }
   }
}
