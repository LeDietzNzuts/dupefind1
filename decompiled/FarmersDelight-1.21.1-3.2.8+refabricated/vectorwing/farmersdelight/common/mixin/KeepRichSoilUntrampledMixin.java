package vectorwing.farmersdelight.common.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2344;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;

@Mixin(class_2344.class)
public class KeepRichSoilUntrampledMixin {
   @Inject(
      at = @At("HEAD"),
      method = "method_10125(Lnet/minecraft/class_1297;Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;)V",
      cancellable = true
   )
   private static void turnToDirt(class_1297 entity, class_2680 state, class_1937 level, class_2338 pos, CallbackInfo ci) {
      if (state.method_26204() instanceof RichSoilFarmlandBlock) {
         ci.cancel();
      }
   }
}
