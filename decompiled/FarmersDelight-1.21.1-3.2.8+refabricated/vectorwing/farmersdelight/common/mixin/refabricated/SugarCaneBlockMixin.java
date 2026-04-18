package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4538;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.utility.SoilUtils;

@Mixin(class_2261.class)
public class SugarCaneBlockMixin {
   @ModifyReturnValue(method = "method_9558(Lnet/minecraft/class_2680;Lnet/minecraft/class_4538;Lnet/minecraft/class_2338;)Z", at = @At("RETURN"))
   private boolean farmersdelightrefabricated$allowPlantsOnSugarCane(boolean original, class_2680 state, class_4538 level, class_2338 pos) {
      if (state.method_26204() != this) {
         return original;
      } else if (level.method_8320(pos.method_10074()).method_26204() instanceof RichSoilBlock) {
         return SoilUtils.isAbleToPlaceRichSoil((class_2248)this);
      } else {
         return level.method_8320(pos.method_10074()).method_26204() instanceof RichSoilFarmlandBlock
            ? SoilUtils.isAbleToPlaceRichSoilFarmland((class_2248)this)
            : original;
      }
   }
}
