package net.caffeinemc.mods.lithium.mixin.ai.pathing;

import net.caffeinemc.mods.lithium.common.ai.pathing.PathNodeCache;
import net.minecraft.class_14;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_7;
import net.minecraft.class_9316;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = class_14.class, priority = 990)
public abstract class WalkNodeEvaluatorMixin {
   @Inject(
      method = "method_58(Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;)Lnet/minecraft/class_7;",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/class_1922;method_8320(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;",
         shift = Shift.AFTER
      ),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private static void getLithiumCachedCommonNodeType(class_1922 world, class_2338 pos, CallbackInfoReturnable<class_7> cir, class_2680 blockState) {
      class_7 type = PathNodeCache.getPathNodeType(blockState);
      if (type != null) {
         cir.setReturnValue(type);
      }
   }

   @Inject(
      method = "method_59(Lnet/minecraft/class_9316;IIILnet/minecraft/class_7;)Lnet/minecraft/class_7;",
      locals = LocalCapture.CAPTURE_FAILHARD,
      at = @At(value = "INVOKE", shift = Shift.BEFORE, target = "Lnet/minecraft/class_9316;method_57622(III)Lnet/minecraft/class_7;"),
      cancellable = true
   )
   private static void doNotIteratePositionsIfLithiumSinglePosCall(
      class_9316 context, int x, int y, int z, class_7 fallback, CallbackInfoReturnable<class_7> cir, int i, int j, int k
   ) {
      if (fallback == null && (i != -1 || j != -1 || k != -1)) {
         cir.setReturnValue(null);
      }
   }

   @Redirect(
      method = "method_23476(Lnet/minecraft/class_9316;Lnet/minecraft/class_2338$class_2339;)Lnet/minecraft/class_7;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_14;method_59(Lnet/minecraft/class_9316;IIILnet/minecraft/class_7;)Lnet/minecraft/class_7;")
   )
   private static class_7 getNodeTypeFromNeighbors(class_9316 context, int x, int y, int z, class_7 fallback) {
      return PathNodeCache.getNodeTypeFromNeighbors(context, x, y, z, fallback);
   }
}
