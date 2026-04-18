package net.caffeinemc.mods.lithium.mixin.ai.pathing;

import net.caffeinemc.mods.lithium.common.ai.pathing.BlockStatePathingCache;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2966;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2966.class)
public class BootstrapMixin {
   @Inject(method = "method_12851()V", at = @At("RETURN"))
   private static void afterBootstrap(CallbackInfo ci) {
      for (class_2680 blockState : class_2248.field_10651) {
         ((BlockStatePathingCache)blockState).lithium$initializePathNodeTypeCache();
      }
   }
}
