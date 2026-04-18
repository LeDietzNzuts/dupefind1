package net.raphimc.immediatelyfast.injection.mixins.core;

import net.minecraft.class_1008;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1008.class)
public abstract class MixinGlDebug {
   @Unique
   private static long immediatelyFast$lastTime;

   @ModifyVariable(method = "method_4227(IZ)V", at = @At("HEAD"), index = 1, argsOnly = true)
   private static boolean enableSyncDebug(boolean sync) {
      return sync || ImmediatelyFast.config.debug_only_print_additional_error_information;
   }

   @Redirect(
      method = "method_4224(IIIIIJJ)V",
      at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;)V", remap = false)
   )
   private static void appendStackTrace(Logger instance, String message, Object argument) {
      if (ImmediatelyFast.config.debug_only_print_additional_error_information && System.currentTimeMillis() - immediatelyFast$lastTime > 1000L) {
         immediatelyFast$lastTime = System.currentTimeMillis();
         instance.info(message, argument, new Exception());
      } else {
         instance.info(message, argument);
      }
   }
}
