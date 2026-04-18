package net.caffeinemc.mods.lithium.mixin.entity.fast_elytra_check;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin extends class_1297 {
   public LivingEntityMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Inject(
      method = "method_6053()V",
      at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/class_1309;method_5795(I)Z", shift = Shift.AFTER),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   private void skipStopFlying(CallbackInfo ci, boolean isFlying) {
      if (!isFlying) {
         ci.cancel();
      }
   }
}
