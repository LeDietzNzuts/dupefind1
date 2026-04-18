package noobanidus.mods.lootr.common.mixin.poi;

import java.util.Optional;
import net.minecraft.class_2680;
import net.minecraft.class_4158;
import net.minecraft.class_6880;
import net.minecraft.class_7477;
import net.minecraft.class_7923;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_7477.class)
public class MixinPoiTypes {
   @Inject(method = "method_43989", at = @At("RETURN"), cancellable = true)
   private static void LootrForState(class_2680 state, CallbackInfoReturnable<Optional<class_6880<class_4158>>> cir) {
      if (LootrRegistry.isReady()) {
         if (state.method_27852(LootrRegistry.getBarrelBlock())) {
            cir.setReturnValue(Optional.of(class_7923.field_41128.method_40290(class_7477.field_39283)));
            cir.cancel();
         }
      }
   }

   @Inject(method = "method_46397", at = @At("RETURN"), cancellable = true)
   private static void LootrHasPoi(class_2680 state, CallbackInfoReturnable<Boolean> cir) {
      if (LootrRegistry.isReady()) {
         if (state.method_27852(LootrRegistry.getBarrelBlock())) {
            cir.setReturnValue(true);
            cir.cancel();
         }
      }
   }
}
