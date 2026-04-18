package net.caffeinemc.mods.lithium.mixin.block.moving_block_shapes;

import net.caffeinemc.mods.lithium.common.shapes.OffsetVoxelShapeCache;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_265.class)
public class VoxelShapeMixin implements OffsetVoxelShapeCache {
   private volatile class_265[] offsetAndSimplified;

   @Override
   public void lithium$setShape(float offset, class_2350 direction, class_265 offsetShape) {
      if (offsetShape == null) {
         throw new IllegalArgumentException("offsetShape must not be null!");
      } else {
         int index = getIndexForOffsetSimplifiedShapes(offset, direction);
         class_265[] offsetAndSimplifiedShapes = this.offsetAndSimplified;
         if (offsetAndSimplifiedShapes == null) {
            offsetAndSimplifiedShapes = new class_265[13];
         } else {
            offsetAndSimplifiedShapes = (class_265[])offsetAndSimplifiedShapes.clone();
         }

         offsetAndSimplifiedShapes[index] = offsetShape;
         this.offsetAndSimplified = offsetAndSimplifiedShapes;
      }
   }

   @Override
   public class_265 lithium$getOffsetSimplifiedShape(float offset, class_2350 direction) {
      class_265[] offsetAndSimplified = this.offsetAndSimplified;
      if (offsetAndSimplified == null) {
         return null;
      } else {
         int index = getIndexForOffsetSimplifiedShapes(offset, direction);
         return offsetAndSimplified[index];
      }
   }

   private static int getIndexForOffsetSimplifiedShapes(float offset, class_2350 direction) {
      if (offset != 0.0F && offset != 0.5F && offset != 1.0F) {
         throw new IllegalArgumentException("offset must be one of {0f, 0.5f, 1f}");
      } else {
         return offset == 0.0F ? 0 : (int)(2.0F * offset) + 2 * direction.method_10146();
      }
   }
}
