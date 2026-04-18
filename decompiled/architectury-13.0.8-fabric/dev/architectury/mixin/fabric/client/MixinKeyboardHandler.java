package dev.architectury.mixin.fabric.client;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.event.events.client.ClientScreenInputEvent;
import dev.architectury.impl.fabric.ScreenInputDelegate;
import net.minecraft.class_309;
import net.minecraft.class_310;
import net.minecraft.class_364;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_309.class)
public class MixinKeyboardHandler {
   @Shadow
   @Final
   private class_310 field_1678;

   @ModifyVariable(method = {"method_1458", "lambda$charTyped$5"}, at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private static class_364 wrapCharTypedFirst(class_364 screen) {
      return (class_364)(screen instanceof ScreenInputDelegate delegate ? delegate.architectury_delegateInputs() : screen);
   }

   @ModifyVariable(method = {"method_1473", "lambda$charTyped$6"}, at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private static class_364 wrapCharTypedSecond(class_364 screen) {
      return (class_364)(screen instanceof ScreenInputDelegate delegate ? delegate.architectury_delegateInputs() : screen);
   }

   @Inject(
      method = "keyPress",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/Screen;wrapScreenError(Ljava/lang/Runnable;Ljava/lang/String;Ljava/lang/String;)V",
         ordinal = 0
      ),
      cancellable = true
   )
   public void onKey(long long_1, int int_1, int int_2, int int_3, int int_4, CallbackInfo info) {
      if (!info.isCancelled()) {
         if (int_3 == 1 || int_3 == 2) {
            EventResult result = ClientScreenInputEvent.KEY_PRESSED_PRE.invoker().keyPressed(this.field_1678, this.field_1678.field_1755, int_1, int_2, int_4);
            if (result.isPresent()) {
               info.cancel();
            }
         } else if (int_3 == 0) {
            EventResult result = ClientScreenInputEvent.KEY_RELEASED_PRE
               .invoker()
               .keyReleased(this.field_1678, this.field_1678.field_1755, int_1, int_2, int_4);
            if (result.isPresent()) {
               info.cancel();
            }
         }
      }
   }

   @Inject(
      method = "keyPress",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/Screen;wrapScreenError(Ljava/lang/Runnable;Ljava/lang/String;Ljava/lang/String;)V",
         ordinal = 0,
         shift = Shift.AFTER
      ),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   public void onKeyAfter(long long_1, int int_1, int int_2, int int_3, int int_4, CallbackInfo info, boolean f3Pressed, class_437 screen, boolean[] bls) {
      if (!info.isCancelled() && !bls[0]) {
         EventResult result;
         if (int_3 != 1 && int_3 != 2) {
            result = ClientScreenInputEvent.KEY_RELEASED_POST.invoker().keyReleased(this.field_1678, screen, int_1, int_2, int_4);
         } else {
            result = ClientScreenInputEvent.KEY_PRESSED_POST.invoker().keyPressed(this.field_1678, screen, int_1, int_2, int_4);
         }

         if (result.isPresent()) {
            info.cancel();
         }
      }
   }

   @Inject(method = "keyPress", at = @At("RETURN"), cancellable = true)
   public void onRawKey(long handle, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
      if (handle == this.field_1678.method_22683().method_4490()) {
         EventResult result = ClientRawInputEvent.KEY_PRESSED.invoker().keyPressed(this.field_1678, key, scanCode, action, modifiers);
         if (result.isPresent()) {
            info.cancel();
         }
      }
   }
}
