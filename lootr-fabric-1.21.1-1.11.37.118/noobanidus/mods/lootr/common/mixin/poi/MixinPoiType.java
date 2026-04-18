package noobanidus.mods.lootr.common.mixin.poi;

import net.minecraft.class_2680;
import net.minecraft.class_4158;
import net.minecraft.class_7477;
import net.minecraft.class_7923;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4158.class)
public class MixinPoiType {
   @Unique
   private boolean lootr$fishermanCheck;
   @Unique
   private boolean lootr$isFisherman;

   @Inject(method = "method_35159", at = @At("RETURN"), cancellable = true)
   private void LootrGetBlockStates(class_2680 state, CallbackInfoReturnable<Boolean> cir) {
      if (LootrRegistry.isReady()) {
         class_4158 thisPoi = (class_4158)this;
         if (!this.lootr$fishermanCheck) {
            this.lootr$fishermanCheck = true;
            this.lootr$isFisherman = class_7477.field_39283.method_29177().equals(class_7923.field_41128.method_10221(thisPoi));
         }

         if (this.lootr$isFisherman && state.method_27852(LootrRegistry.getBarrelBlock())) {
            cir.setReturnValue(true);
            cir.cancel();
         }
      }
   }
}
