package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import net.minecraft.class_1269;
import net.minecraft.class_309;
import net.minecraft.class_310;
import net.p3pp3rf1y.sophisticatedcore.event.client.ClientRawInputEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_309.class)
public class KeyboardHandlerMixin {
   @Shadow
   @Final
   private class_310 field_1678;

   @Inject(method = "keyPress", at = @At("RETURN"), cancellable = true)
   public void sophisticatedcore$keyPress(long handle, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
      if (handle == this.field_1678.method_22683().method_4490()) {
         class_1269 result = ((ClientRawInputEvent.KeyPressed)ClientRawInputEvent.KEY_PRESSED.invoker())
            .keyPressed(this.field_1678, key, scanCode, action, modifiers);
         if (result != class_1269.field_5811) {
            ci.cancel();
         }
      }
   }
}
