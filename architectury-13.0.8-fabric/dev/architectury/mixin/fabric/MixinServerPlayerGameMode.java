package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3225;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_3225.class)
public class MixinServerPlayerGameMode {
   @Shadow
   protected class_3218 field_14007;
   @Shadow
   @Final
   protected class_3222 field_14008;

   @Inject(
      method = "destroyBlock",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;", ordinal = 0),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   private void onBreak(class_2338 blockPos, CallbackInfoReturnable<Boolean> cir, class_2586 entity, class_2680 state) {
      if (BlockEvent.BREAK.invoker().breakBlock(this.field_14007, blockPos, state, this.field_14008, null).isFalse()) {
         cir.setReturnValue(false);
      }
   }
}
