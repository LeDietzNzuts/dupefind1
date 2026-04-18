package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_2248;
import net.minecraft.class_2513;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.utility.SoilUtils;

@Mixin(class_2513.class)
public class StemBlockMixin {
   @ModifyExpressionValue(
      method = "method_9514(Lnet/minecraft/class_2680;Lnet/minecraft/class_3218;Lnet/minecraft/class_2338;Lnet/minecraft/class_5819;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_27852(Lnet/minecraft/class_2248;)Z")
   )
   private boolean fdrf$modifyRandomTickCallForStems(boolean original, @Local(ordinal = 1) class_2680 below) {
      if (below.method_26204() instanceof RichSoilBlock) {
         return SoilUtils.isAbleToPlaceRichSoil((class_2248)this);
      } else {
         return below.method_26204() instanceof RichSoilFarmlandBlock ? SoilUtils.isAbleToPlaceRichSoilFarmland((class_2248)this) : original;
      }
   }
}
