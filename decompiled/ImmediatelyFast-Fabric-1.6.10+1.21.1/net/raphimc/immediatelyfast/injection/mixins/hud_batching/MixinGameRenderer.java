package net.raphimc.immediatelyfast.injection.mixins.hud_batching;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.feature.batching.BatchingBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_757.class)
public abstract class MixinGameRenderer {
   @WrapOperation(
      method = "method_3192(Lnet/minecraft/class_9779;Z)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_329;method_1753(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V")
   )
   private void hudBatching(class_329 instance, class_332 context, class_9779 tickCounter, Operation<Void> original) {
      if (ImmediatelyFast.runtimeConfig.hud_batching) {
         BatchingBuffers.runBatched(context, () -> original.call(new Object[]{instance, context, tickCounter}));
      } else {
         original.call(new Object[]{instance, context, tickCounter});
      }
   }
}
