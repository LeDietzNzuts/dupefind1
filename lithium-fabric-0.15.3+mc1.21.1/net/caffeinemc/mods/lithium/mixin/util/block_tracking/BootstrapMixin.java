package net.caffeinemc.mods.lithium.mixin.util.block_tracking;

import net.caffeinemc.mods.lithium.common.block.BlockStateFlagHolder;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2966;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_2966.class, priority = 1010)
public class BootstrapMixin {
   @Inject(method = "method_12851()V", at = @At("RETURN"))
   private static void afterBootstrap(CallbackInfo ci) {
      for (class_2680 blockState : class_2248.field_10651) {
         ((BlockStateFlagHolder)blockState).lithium$initializeFlags();
      }
   }
}
