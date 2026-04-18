package ewewukek.musketmod.mixin;

import ewewukek.musketmod.Config;
import ewewukek.musketmod.ScopedMusketItem;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_742.class)
abstract class AbstractClientPlayerMixin {
   @Inject(method = "getFieldOfViewModifier", at = @At("HEAD"), cancellable = true)
   private void getFieldOfViewModifier(CallbackInfoReturnable<Float> ci) {
      if (ScopedMusketItem.isScoping) {
         ci.setReturnValue(1.0F / Config.scopeZoom);
         ci.cancel();
      }
   }
}
