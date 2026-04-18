package dev.architectury.mixin.fabric.client;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.event.events.client.ClientScreenInputEvent;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_312.class)
public class MixinMouseHandler {
   @Shadow
   @Final
   private class_310 field_1779;
   @Shadow
   private int field_1780;
   @Shadow
   private double field_1795;
   @Shadow
   private double field_1794;

   @Inject(
      method = "onScroll",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;mouseScrolled(DDDD)Z", ordinal = 0),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void onMouseScrolled(
      long handle,
      double xOffset,
      double yOffset,
      CallbackInfo info,
      boolean discreteMouseScroll,
      double mouseWheelSensitivity,
      double amountX,
      double amountY,
      double x,
      double y
   ) {
      if (!info.isCancelled()) {
         EventResult result = ClientScreenInputEvent.MOUSE_SCROLLED_PRE
            .invoker()
            .mouseScrolled(this.field_1779, this.field_1779.field_1755, x, y, amountX, amountY);
         if (result.isPresent()) {
            info.cancel();
         }
      }
   }

   @Inject(
      method = "onScroll",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;mouseScrolled(DDDD)Z", ordinal = 0, shift = Shift.AFTER),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void onMouseScrolledPost(
      long handle,
      double xOffset,
      double yOffset,
      CallbackInfo info,
      boolean discreteMouseScroll,
      double mouseWheelSensitivity,
      double amountX,
      double amountY,
      double x,
      double y
   ) {
      if (!info.isCancelled()) {
         EventResult var19 = ClientScreenInputEvent.MOUSE_SCROLLED_POST
            .invoker()
            .mouseScrolled(this.field_1779, this.field_1779.field_1755, x, y, amountX, amountY);
      }
   }

   @Inject(
      method = "onScroll",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z", ordinal = 0),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void onRawMouseScrolled(
      long handle, double xOffset, double yOffset, CallbackInfo info, boolean discreteMouseScroll, double mouseWheelSensitivity, double amountX, double doubleY
   ) {
      if (!info.isCancelled()) {
         EventResult result = ClientRawInputEvent.MOUSE_SCROLLED.invoker().mouseScrolled(this.field_1779, amountX, doubleY);
         if (result.isPresent()) {
            info.cancel();
         }
      }
   }

   @Inject(method = {"lambda$onPress$0", "method_1611"}, at = @At("HEAD"), cancellable = true, remap = false)
   private static void onGuiMouseClicked(boolean[] bls, class_437 screen, double d, double e, int button, CallbackInfo info) {
      class_310 minecraft = class_310.method_1551();
      if (!info.isCancelled()) {
         EventResult result = ClientScreenInputEvent.MOUSE_CLICKED_PRE.invoker().mouseClicked(minecraft, screen, d, e, button);
         if (result.isPresent()) {
            bls[0] = true;
            info.cancel();
         }
      }
   }

   @Inject(method = {"lambda$onPress$0", "method_1611"}, at = @At("RETURN"), cancellable = true, remap = false)
   private static void onGuiMouseClickedPost(boolean[] bls, class_437 screen, double d, double e, int button, CallbackInfo info) {
      class_310 minecraft = class_310.method_1551();
      if (!info.isCancelled() && !bls[0]) {
         EventResult result = ClientScreenInputEvent.MOUSE_CLICKED_POST.invoker().mouseClicked(minecraft, screen, d, e, button);
         if (result.isPresent()) {
            bls[0] = true;
            info.cancel();
         }
      }
   }

   @Inject(
      method = "onPress",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getOverlay()Lnet/minecraft/client/gui/screens/Overlay;", ordinal = 0),
      cancellable = true
   )
   public void onRawMouseClicked(long handle, int button, int action, int mods, CallbackInfo info) {
      if (!info.isCancelled()) {
         EventResult result = ClientRawInputEvent.MOUSE_CLICKED_PRE.invoker().mouseClicked(this.field_1779, button, action, mods);
         if (result.isPresent()) {
            info.cancel();
         }
      }
   }

   @Inject(method = "onPress", at = @At("RETURN"), cancellable = true)
   public void onRawMouseClickedPost(long handle, int button, int action, int mods, CallbackInfo info) {
      if (handle == this.field_1779.method_22683().method_4490()) {
         EventResult result = ClientRawInputEvent.MOUSE_CLICKED_POST.invoker().mouseClicked(this.field_1779, button, action, mods);
         if (result.isPresent()) {
            info.cancel();
         }
      }
   }

   @Inject(method = {"lambda$onPress$1", "method_1605"}, at = @At("HEAD"), cancellable = true, remap = false)
   private static void onGuiMouseReleased(boolean[] bls, class_437 screen, double d, double e, int button, CallbackInfo info) {
      class_310 minecraft = class_310.method_1551();
      if (!info.isCancelled()) {
         EventResult result = ClientScreenInputEvent.MOUSE_RELEASED_PRE.invoker().mouseReleased(minecraft, screen, d, e, button);
         if (result.isPresent()) {
            bls[0] = true;
            info.cancel();
         }
      }
   }

   @Inject(method = {"lambda$onPress$1", "method_1605"}, at = @At("RETURN"), cancellable = true, remap = false)
   private static void onGuiMouseReleasedPost(boolean[] bls, class_437 screen, double d, double e, int button, CallbackInfo info) {
      class_310 minecraft = class_310.method_1551();
      if (!info.isCancelled() && !bls[0]) {
         EventResult result = ClientScreenInputEvent.MOUSE_RELEASED_POST.invoker().mouseReleased(minecraft, screen, d, e, button);
         if (result.isPresent()) {
            bls[0] = true;
            info.cancel();
         }
      }
   }

   @Inject(method = {"method_55795", "lambda$handleAccumulatedMovement$11"}, at = @At("HEAD"), cancellable = true, remap = false)
   private void onGuiMouseDraggedPre(class_437 screen, double mouseX, double mouseY, double deltaX, double deltaY, CallbackInfo ci) {
      if (ClientScreenInputEvent.MOUSE_DRAGGED_PRE
         .invoker()
         .mouseDragged(class_310.method_1551(), screen, mouseX, mouseY, this.field_1780, deltaX, deltaY)
         .isPresent()) {
         ci.cancel();
      }
   }

   @Redirect(
      method = {"method_55795", "lambda$handleAccumulatedMovement$11"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;mouseDragged(DDIDD)Z", remap = true),
      remap = false
   )
   private boolean onGuiMouseDraggedPost(class_437 screen, double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      return screen.method_25403(mouseX, mouseY, button, deltaX, deltaY)
         ? true
         : ClientScreenInputEvent.MOUSE_DRAGGED_POST
            .invoker()
            .mouseDragged(class_310.method_1551(), screen, mouseX, mouseY, button, deltaX, deltaY)
            .isPresent();
   }
}
