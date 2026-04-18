package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2420;
import net.minecraft.class_2680;
import net.minecraft.class_4538;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.utility.SoilUtils;

@Mixin(class_2420.class)
public class MushroomBlockMixin {
   @ModifyExpressionValue(
      method = "method_9558(Lnet/minecraft/class_2680;Lnet/minecraft/class_4538;Lnet/minecraft/class_2338;)Z",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2420;method_9695(Lnet/minecraft/class_2680;Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;)Z"
      )
   )
   private boolean farmersdelightrefabricated$allowPlantsOnMushroom(boolean original, class_2680 state, class_4538 level, class_2338 pos) {
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
