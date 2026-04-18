package net.raphimc.immediatelyfast.injection.mixins.hud_batching.compat;

import net.minecraft.class_1921;
import net.minecraft.class_332;
import net.minecraft.class_340;
import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_340.class, priority = 1500)
public abstract class MixinDebugHud {
   @Inject(method = "method_1846(Lnet/minecraft/class_332;)V", at = @At("RETURN"))
   private void fixDrawOrder(class_332 context, CallbackInfo ci) {
      if (context.field_44658 instanceof BatchableBufferSource batchableBufferSource) {
         batchableBufferSource.drawDirect(class_1921.method_51784());
         batchableBufferSource.drawDirect(class_1921.method_51785());
      }
   }
}
