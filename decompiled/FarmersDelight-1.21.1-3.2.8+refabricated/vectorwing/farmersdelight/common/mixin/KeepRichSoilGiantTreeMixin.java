package vectorwing.farmersdelight.common.mixin;

import net.minecraft.class_2338;
import net.minecraft.class_3031;
import net.minecraft.class_3746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mixin(class_3031.class)
public class KeepRichSoilGiantTreeMixin {
   @Inject(at = @At("HEAD"), method = "method_27368(Lnet/minecraft/class_3746;Lnet/minecraft/class_2338;)Z", cancellable = true)
   private static void keepRichSoil(class_3746 world, class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
      if (world.method_16358(pos, state -> state.method_27852(ModBlocks.RICH_SOIL.get()))) {
         cir.setReturnValue(false);
         cir.cancel();
      }
   }
}
