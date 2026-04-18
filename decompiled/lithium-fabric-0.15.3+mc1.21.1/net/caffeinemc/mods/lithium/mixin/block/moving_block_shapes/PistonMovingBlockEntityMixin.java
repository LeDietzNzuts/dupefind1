package net.caffeinemc.mods.lithium.mixin.block.moving_block_shapes;

import net.caffeinemc.mods.lithium.common.shapes.OffsetVoxelShapeCache;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2665;
import net.minecraft.class_2669;
import net.minecraft.class_2671;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_2669.class)
public abstract class PistonMovingBlockEntityMixin {
   private static final class_265[] PISTON_BASE_WITH_MOVING_HEAD_SHAPES = precomputePistonBaseWithMovingHeadShapes();
   @Shadow
   private class_2350 field_12201;
   @Shadow
   private boolean field_12203;
   @Shadow
   private boolean field_12202;
   @Shadow
   private class_2680 field_12204;

   @Inject(
      method = "method_11512(Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;)Lnet/minecraft/class_265;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2350;method_10148()I", shift = Shift.BEFORE),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   private void skipVoxelShapeUnion(
      class_1922 world, class_2338 pos, CallbackInfoReturnable<class_265> cir, class_265 voxelShape, class_2350 direction, class_2680 blockState, float offset
   ) {
      float absOffset = Math.abs(offset);
      if (absOffset == 0.0F || absOffset == 0.5F || absOffset == 1.0F) {
         if (!this.field_12203 && this.field_12202 && this.field_12204.method_26204() instanceof class_2665) {
            int index = getIndexForMergedShape(offset, this.field_12201);
            cir.setReturnValue(PISTON_BASE_WITH_MOVING_HEAD_SHAPES[index]);
         } else {
            class_265 blockShape = blockState.method_26220(world, pos);
            class_265 offsetAndSimplified = getOffsetAndSimplified(blockShape, absOffset, offset < 0.0F ? this.field_12201.method_10153() : this.field_12201);
            cir.setReturnValue(offsetAndSimplified);
         }
      }
   }

   private static class_265 getOffsetAndSimplified(class_265 blockShape, float offset, class_2350 direction) {
      class_265 offsetSimplifiedShape = ((OffsetVoxelShapeCache)blockShape).lithium$getOffsetSimplifiedShape(offset, direction);
      if (offsetSimplifiedShape == null) {
         offsetSimplifiedShape = blockShape.method_1096(direction.method_10148() * offset, direction.method_10164() * offset, direction.method_10165() * offset)
            .method_1097();
         ((OffsetVoxelShapeCache)blockShape).lithium$setShape(offset, direction, offsetSimplifiedShape);
      }

      return offsetSimplifiedShape;
   }

   private static class_265[] precomputePistonBaseWithMovingHeadShapes() {
      float[] offsets = new float[]{0.0F, 0.5F, 1.0F};
      class_2350[] directions = class_2350.values();
      class_265[] mergedShapes = new class_265[offsets.length * directions.length];

      for (class_2350 facing : directions) {
         class_265 baseShape = ((class_2680)((class_2680)class_2246.field_10560.method_9564().method_11657(class_2665.field_12191, true))
               .method_11657(class_2665.field_10927, facing))
            .method_26220(null, null);

         for (float offset : offsets) {
            boolean isShort = offset < 0.25F;
            class_265 headShape = ((class_2680)((class_2680)class_2246.field_10379.method_9564().method_11657(class_2671.field_10927, facing))
                  .method_11657(class_2671.field_12227, isShort))
               .method_26220(null, null);
            class_265 offsetHead = headShape.method_1096(facing.method_10148() * offset, facing.method_10164() * offset, facing.method_10165() * offset);
            mergedShapes[getIndexForMergedShape(offset, facing)] = class_259.method_1084(baseShape, offsetHead);
         }
      }

      return mergedShapes;
   }

   private static int getIndexForMergedShape(float offset, class_2350 direction) {
      return offset != 0.0F && offset != 0.5F && offset != 1.0F ? -1 : (int)(2.0F * offset) + 3 * direction.method_10146();
   }
}
