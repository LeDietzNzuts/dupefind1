package net.caffeinemc.mods.lithium.mixin.entity.fast_hand_swing;

import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin {
   @Shadow
   public boolean field_6252;
   @Shadow
   public int field_6279;

   @Inject(method = "method_6119()V", at = @At("HEAD"), cancellable = true)
   private void skipGetDuration(CallbackInfo ci) {
      if (!this.field_6252 && this.field_6279 == 0) {
         ci.cancel();
      }
   }
}
