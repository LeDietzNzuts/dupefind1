package vectorwing.farmersdelight.common.mixin;

import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_776;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mixin(class_776.class)
public abstract class HideBlockBreakProgressMixin {
   @Inject(
      method = "method_23071(Lnet/minecraft/class_2680;Lnet/minecraft/class_2338;Lnet/minecraft/class_1920;Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;)V",
      at = @At("HEAD"),
      cancellable = true
   )
   private void hideBlockDamage(class_2680 state, class_2338 pos, class_1920 level, class_4587 poseStack, class_4588 consumer, CallbackInfo ci) {
      if (state.method_26204() == ModBlocks.CANVAS_RUG.get()) {
         ci.cancel();
      }
   }
}
