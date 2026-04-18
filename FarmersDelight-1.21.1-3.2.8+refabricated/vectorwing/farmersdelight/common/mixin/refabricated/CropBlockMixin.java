package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.utility.SoilUtils;

@Mixin(class_2302.class)
public class CropBlockMixin {
   @ModifyVariable(
      method = "method_9830(Lnet/minecraft/class_2248;Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;)F",
      at = @At(value = "LOAD", ordinal = 1),
      ordinal = 1
   )
   private static float farmersdelightrefabricated$modifyGrowthSpeedForNonFarmland(float original, class_2248 block, class_1922 level, class_2338 pos) {
      class_2680 belowState = level.method_8320(pos.method_10074());
      if (belowState.method_26204() instanceof RichSoilBlock && SoilUtils.isAbleToPlaceRichSoil(block) && original < 1.0E-5F) {
         return 1.0F;
      } else if (!(belowState.method_26204() instanceof RichSoilFarmlandBlock) || !SoilUtils.isAbleToPlaceRichSoilFarmland(block) || !(original < 1.0E-5F)) {
         return original;
      } else {
         return belowState.method_28498(RichSoilFarmlandBlock.field_11009) && belowState.method_11654(RichSoilFarmlandBlock.field_11009) > 0 ? 3.0F : 1.0F;
      }
   }
}
