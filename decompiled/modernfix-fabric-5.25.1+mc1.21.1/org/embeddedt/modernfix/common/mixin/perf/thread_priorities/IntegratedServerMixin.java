package org.embeddedt.modernfix.common.mixin.perf.thread_priorities;

import net.minecraft.class_1132;
import net.minecraft.class_310;
import net.minecraft.class_3283;
import net.minecraft.class_3950;
import net.minecraft.class_6904;
import net.minecraft.class_7497;
import net.minecraft.class_32.class_5143;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1132.class)
@ClientOnlyMixin
public class IntegratedServerMixin {
   @Inject(method = "<init>", at = @At("RETURN"))
   private void adjustServerPriority(
      Thread thread, class_310 arg, class_5143 arg2, class_3283 arg3, class_6904 arg4, class_7497 arg5, class_3950 arg6, CallbackInfo ci
   ) {
      int pri = 4;
      thread.setPriority(pri);
   }
}
