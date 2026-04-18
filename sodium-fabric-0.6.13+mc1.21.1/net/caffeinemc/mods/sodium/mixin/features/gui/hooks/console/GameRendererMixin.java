package net.caffeinemc.mods.sodium.mixin.features.gui.hooks.console;

import net.caffeinemc.mods.sodium.client.gui.console.ConsoleHooks;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4599;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_757.class)
public class GameRendererMixin {
   @Shadow
   @Final
   class_310 field_4015;
   @Shadow
   @Final
   private class_4599 field_20948;
   @Unique
   private static boolean HAS_RENDERED_OVERLAY_ONCE = false;

   @Inject(
      method = "method_3192(Lnet/minecraft/class_9779;Z)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_332;method_51452()V", shift = Shift.AFTER)
   )
   private void onRender(class_9779 deltaTracker, boolean bl, CallbackInfo ci) {
      if (class_310.method_1551().method_18506() == null || HAS_RENDERED_OVERLAY_ONCE) {
         this.field_4015.method_16011().method_15396("sodium_console_overlay");
         class_332 drawContext = new class_332(this.field_4015, this.field_20948.method_23000());
         ConsoleHooks.render(drawContext, GLFW.glfwGetTime());
         drawContext.method_51452();
         this.field_4015.method_16011().method_15407();
         HAS_RENDERED_OVERLAY_ONCE = true;
      }
   }
}
