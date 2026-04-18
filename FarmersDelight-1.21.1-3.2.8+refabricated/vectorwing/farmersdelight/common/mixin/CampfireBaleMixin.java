package vectorwing.farmersdelight.common.mixin;

import net.minecraft.class_2680;
import net.minecraft.class_3922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.tag.ModTags;

@Mixin(class_3922.class)
public abstract class CampfireBaleMixin {
   @Inject(at = @At("HEAD"), method = "method_17456(Lnet/minecraft/class_2680;)Z", cancellable = true)
   public void isFDSmokeSource(class_2680 state, CallbackInfoReturnable<Boolean> cir) {
      if (state.method_26164(ModTags.CAMPFIRE_SIGNAL_SMOKE)) {
         cir.setReturnValue(true);
      }
   }
}
