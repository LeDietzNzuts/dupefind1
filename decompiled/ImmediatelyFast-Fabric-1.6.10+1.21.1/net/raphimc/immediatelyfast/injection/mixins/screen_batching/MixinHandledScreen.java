package net.raphimc.immediatelyfast.injection.mixins.screen_batching;

import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_4597.class_4598;
import net.raphimc.immediatelyfast.feature.batching.BatchingBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_465.class, priority = 500)
public abstract class MixinHandledScreen {
   @Unique
   private class_4598 immediatelyFast$prevVertexConsumers;

   @Inject(
      method = "method_25394(Lnet/minecraft/class_332;IIF)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_465;field_2787:Lnet/minecraft/class_1735;", ordinal = 0)
   )
   private void beginBatching(class_332 drawContext, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      this.immediatelyFast$prevVertexConsumers = BatchingBuffers.beginHudBatching(drawContext);
   }

   @Inject(
      method = "method_25394(Lnet/minecraft/class_332;IIF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_465;method_2388(Lnet/minecraft/class_332;II)V")
   )
   private void endBatching(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      BatchingBuffers.endHudBatching(context, this.immediatelyFast$prevVertexConsumers);
   }
}
