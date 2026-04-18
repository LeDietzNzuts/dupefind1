package net.raphimc.immediatelyfast.fabric.injection.mixins.screen_batching;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_757;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.feature.batching.BatchingBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_757.class)
public abstract class MixinGameRenderer {
   @WrapOperation(
      method = "method_3192(Lnet/minecraft/class_9779;Z)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_437;method_47413(Lnet/minecraft/class_332;IIF)V")
   )
   private void screenBatching(class_437 instance, class_332 context, int mouseX, int mouseY, float delta, Operation<Void> original) {
      boolean batchScreen = instance instanceof class_408;
      if (ImmediatelyFast.runtimeConfig.experimental_screen_batching && batchScreen) {
         BatchingBuffers.runBatched(context, () -> original.call(new Object[]{instance, context, mouseX, mouseY, delta}));
      } else {
         original.call(new Object[]{instance, context, mouseX, mouseY, delta});
      }
   }
}
