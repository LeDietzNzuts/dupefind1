package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ScopedMusketItem;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_746.class)
abstract class LocalPlayerMixin {
   private boolean insideAiStep;

   @Inject(method = "canStartSprinting", at = @At("HEAD"), cancellable = true)
   private void canStartSprinting(CallbackInfoReturnable<Boolean> ci) {
      if (ScopedMusketItem.isScoping) {
         ci.setReturnValue(false);
         ci.cancel();
      }
   }

   @Inject(method = "aiStep", at = @At("HEAD"))
   private void aiStepHead(CallbackInfo ci) {
      this.insideAiStep = true;
   }

   @Inject(method = "isUsingItem", at = @At("HEAD"), cancellable = true)
   private void isUsingItem(CallbackInfoReturnable<Boolean> ci) {
      if (this.insideAiStep && ScopedMusketItem.isScoping) {
         ci.setReturnValue(true);
         ci.cancel();
      }
   }

   @Inject(method = "aiStep", at = @At("TAIL"))
   private void aiStepTail(CallbackInfo ci) {
      this.insideAiStep = false;
      if (ScopedMusketItem.recoilTicks > 0) {
         ScopedMusketItem.recoilTicks--;
      }
   }

   @Inject(method = "getViewXRot", at = @At("HEAD"), cancellable = true)
   private void getViewXRot(float dt, CallbackInfoReturnable<Float> ci) {
      if (ScopedMusketItem.isScoping && ScopedMusketItem.recoilTicks > 0) {
         class_746 player = (class_746)this;
         float xRot = player.method_36455() - (ScopedMusketItem.recoilTicks - dt) * 0.25F;
         ci.setReturnValue(xRot);
         ci.cancel();
      }
   }
}
