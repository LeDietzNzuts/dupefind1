package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.common.hopper.BlockStateOnlyInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ComposterMixin {
   @Mixin(targets = "net/minecraft/class_3962$class_3963")
   abstract static class ComposterBlockComposterInventoryMixin implements BlockStateOnlyInventory {
      @Shadow
      private boolean field_17572;

      @Inject(method = "method_5431()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3962$class_3963;method_5441(I)Lnet/minecraft/class_1799;"))
      private void resetDirty(CallbackInfo ci) {
         this.field_17572 = false;
      }
   }

   @Mixin(targets = "net/minecraft/class_3962$class_3925")
   abstract static class ComposterBlockDummyInventoryMixin implements BlockStateOnlyInventory {
   }

   @Mixin(targets = "net/minecraft/class_3962$class_3964")
   abstract static class ComposterBlockFullComposterInventoryMixin implements BlockStateOnlyInventory {
   }
}
