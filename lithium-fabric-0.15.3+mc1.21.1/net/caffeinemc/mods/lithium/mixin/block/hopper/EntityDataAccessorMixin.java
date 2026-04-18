package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_2487;
import net.minecraft.class_3169;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3169.class)
public class EntityDataAccessorMixin {
   @Shadow
   @Final
   private class_1297 field_13801;

   @Inject(
      method = "method_13880(Lnet/minecraft/class_2487;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_5826(Ljava/util/UUID;)V", shift = Shift.AFTER)
   )
   private void updateEntityTrackerEngine(class_2487 nbt, CallbackInfo ci) {
      class_1297 entity = this.field_13801;
      if (entity instanceof class_1542) {
         ((EntityAccessor)entity).getChangeListener().method_31749();
      }
   }
}
