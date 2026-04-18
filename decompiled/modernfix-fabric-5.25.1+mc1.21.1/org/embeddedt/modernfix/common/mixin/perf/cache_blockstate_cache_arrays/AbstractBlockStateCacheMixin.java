package org.embeddedt.modernfix.common.mixin.perf.cache_blockstate_cache_arrays;

import net.minecraft.class_5431;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_4970.class_4971.class_3752;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3752.class)
public class AbstractBlockStateCacheMixin {
   private static final class_5431[] MF_BLOCK_VOXEL_SHAPES = class_5431.values();
   private static final class_2351[] DIRECTION_AXIS_VALUES = class_2351.values();

   @Redirect(
      method = "<init>(Lnet/minecraft/world/level/block/state/BlockState;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SupportType;values()[Lnet/minecraft/world/level/block/SupportType;")
   )
   private class_5431[] getVoxelShapeValues() {
      return MF_BLOCK_VOXEL_SHAPES;
   }

   @Redirect(
      method = "<init>(Lnet/minecraft/world/level/block/state/BlockState;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Direction$Axis;values()[Lnet/minecraft/core/Direction$Axis;")
   )
   private class_2351[] getDirectionAxisValues() {
      return DIRECTION_AXIS_VALUES;
   }
}
