package net.raphimc.immediatelyfast.injection.mixins.core;

import net.minecraft.class_310;
import net.minecraft.class_4599;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import net.minecraft.class_4597.class_4598;
import net.raphimc.immediatelyfast.feature.batching.BatchingBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_757.class)
public abstract class MixinGameRenderer {
   @Redirect(
      method = "method_3192(Lnet/minecraft/class_9779;Z)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4599;method_23000()Lnet/minecraft/class_4597$class_4598;")
   )
   private class_4598 returnNonBatchingVertexConsumer(class_4599 instance) {
      return BatchingBuffers.getNonBatchingEntityVertexConsumers();
   }

   @Inject(
      method = "method_3192(Lnet/minecraft/class_9779;Z)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_332;method_51452()V", shift = Shift.AFTER)
   )
   private void drawDataFromModsWhichRenderIntoTheWrongBuffer(class_9779 tickCounter, boolean tick, CallbackInfo ci) {
      class_310.method_1551().method_22940().method_23000().method_22993();
      class_310.method_1551().method_22940().method_23003().method_23285();
   }
}
