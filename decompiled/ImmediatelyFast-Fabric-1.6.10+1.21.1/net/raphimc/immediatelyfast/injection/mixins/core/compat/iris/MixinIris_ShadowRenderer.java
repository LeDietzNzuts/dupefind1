package net.raphimc.immediatelyfast.injection.mixins.core.compat.iris;

import net.minecraft.class_4599;
import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/irisshaders/iris/shadows/ShadowRenderer", remap = false)
@Pseudo
public abstract class MixinIris_ShadowRenderer {
   @Shadow
   @Final
   private class_4599 buffers;

   @Inject(method = "renderShadows", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4597$class_4598;method_22993()V"))
   private void clearDataFromModsWhichRenderIntoTheWrongBuffer(CallbackInfo ci) {
      if (this.buffers.method_23003().field_21059 instanceof BatchableBufferSource batchableBufferSource) {
         batchableBufferSource.close();
      }
   }
}
