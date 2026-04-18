package org.embeddedt.modernfix.common.mixin.feature.direct_stack_trace;

import net.minecraft.class_128;
import net.minecraft.class_129;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_128.class)
public class CrashReportMixin {
   @Shadow
   @Final
   private Throwable field_1093;

   @Inject(
      method = "addCategory(Ljava/lang/String;I)Lnet/minecraft/CrashReportCategory;",
      at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", remap = false)
   )
   private void dumpStacktrace(String s, int i, CallbackInfoReturnable<class_129> cir) {
      new Exception("ModernFix crash stacktrace").printStackTrace();
      if (this.field_1093 != null) {
         this.field_1093.printStackTrace();
      }
   }
}
