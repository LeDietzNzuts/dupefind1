package vectorwing.farmersdelight.common.mixin;

import java.util.function.BiConsumer;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3746;
import net.minecraft.class_4643;
import net.minecraft.class_5141;
import net.minecraft.class_5819;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mixin(class_5141.class)
public class KeepRichSoilTreeMixin {
   @Inject(
      at = @At("HEAD"),
      method = "method_27400(Lnet/minecraft/class_3746;Ljava/util/function/BiConsumer;Lnet/minecraft/class_5819;Lnet/minecraft/class_2338;Lnet/minecraft/class_4643;)V",
      cancellable = true
   )
   private static void cancelSetDirtIfRichSoil(
      class_3746 level, BiConsumer<class_2338, class_2680> blockSetter, class_5819 random, class_2338 pos, class_4643 config, CallbackInfo ci
   ) {
      if (level.method_16358(pos, state -> state.method_27852(ModBlocks.RICH_SOIL.get()))) {
         ci.cancel();
      }
   }
}
