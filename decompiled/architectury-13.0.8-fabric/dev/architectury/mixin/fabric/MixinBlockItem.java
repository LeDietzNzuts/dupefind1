package dev.architectury.mixin.fabric;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.class_1269;
import net.minecraft.class_1747;
import net.minecraft.class_1750;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1747.class)
public abstract class MixinBlockItem {
   @Inject(
      method = "place",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/item/BlockItem;placeBlock(Lnet/minecraft/world/item/context/BlockPlaceContext;Lnet/minecraft/world/level/block/state/BlockState;)Z"
      ),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   private void place(class_1750 _0, CallbackInfoReturnable<class_1269> cir, class_1750 context, class_2680 placedState) {
      EventResult result = BlockEvent.PLACE.invoker().placeBlock(context.method_8045(), context.method_8037(), placedState, context.method_8036());
      if (result.isFalse()) {
         cir.setReturnValue(class_1269.field_5814);
      }
   }
}
