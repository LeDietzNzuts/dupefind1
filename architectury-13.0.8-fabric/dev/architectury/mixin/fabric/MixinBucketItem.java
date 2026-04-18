package dev.architectury.mixin.fabric;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1657;
import net.minecraft.class_1755;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_3965;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1755.class)
public class MixinBucketItem {
   @Inject(
      method = "use",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/BlockHitResult;getType()Lnet/minecraft/world/phys/HitResult$Type;", ordinal = 0),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   public void fillBucket(
      class_1937 level, class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1271<class_1799>> cir, class_1799 stack, class_3965 target
   ) {
      CompoundEventResult<class_1799> result = PlayerEvent.FILL_BUCKET.invoker().fill(player, level, stack, target);
      if (result.interruptsFurtherEvaluation() && result.value() != null) {
         cir.setReturnValue(result.asMinecraft());
         cir.cancel();
      }
   }
}
