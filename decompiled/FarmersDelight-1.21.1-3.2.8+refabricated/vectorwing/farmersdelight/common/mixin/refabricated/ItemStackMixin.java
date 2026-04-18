package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.event.CommonEvents;

@Mixin(class_1799.class)
public class ItemStackMixin {
   @Inject(method = "method_7910(Lnet/minecraft/class_1937;Lnet/minecraft/class_1309;)Lnet/minecraft/class_1799;", at = @At("HEAD"))
   private void fdrf$onItemUseFinished(class_1937 level, class_1309 livingEntity, CallbackInfoReturnable<class_1799> cir) {
      CommonEvents.onItemUseFinished(level, livingEntity, (class_1799)this);
   }
}
