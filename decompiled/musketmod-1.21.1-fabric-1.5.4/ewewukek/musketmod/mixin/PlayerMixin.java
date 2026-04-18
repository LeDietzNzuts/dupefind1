package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ScopedMusketItem;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1657.class)
abstract class PlayerMixin {
   @Inject(method = "isScoping", at = @At("HEAD"), cancellable = true)
   private void isScoping(CallbackInfoReturnable<Boolean> ci) {
      if (ScopedMusketItem.isScoping) {
         ci.setReturnValue(true);
         ci.cancel();
      }
   }
}
