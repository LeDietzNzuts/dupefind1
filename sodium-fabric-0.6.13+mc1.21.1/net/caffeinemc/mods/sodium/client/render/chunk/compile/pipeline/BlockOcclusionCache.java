package net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline;

import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenCustomHashMap;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.caffeinemc.mods.sodium.client.util.DirectionUtil;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_2338.class_2339;

public class BlockOcclusionCache {
   private static final int CACHE_SIZE = 512;
   private static final int ENTRY_ABSENT = -1;
   private static final int ENTRY_FALSE = 0;
   private static final int ENTRY_TRUE = 1;
   private final Object2IntLinkedOpenCustomHashMap<BlockOcclusionCache.ShapeComparison> comparisonLookupTable;
   private final BlockOcclusionCache.ShapeComparison cachedComparisonObject = new BlockOcclusionCache.ShapeComparison();
   private final class_2339 cachedPositionObject = new class_2339();

   public BlockOcclusionCache() {
      this.comparisonLookupTable = new Object2IntLinkedOpenCustomHashMap(512, 0.5F, new BlockOcclusionCache.ShapeComparison.ShapeComparisonStrategy());
      this.comparisonLookupTable.defaultReturnValue(-1);
   }

   public boolean shouldDrawSide(class_2680 selfState, class_1922 view, class_2338 selfPos, class_2350 facing) {
      class_2339 otherPos = this.cachedPositionObject;
      otherPos.method_10103(
         selfPos.method_10263() + facing.method_10148(), selfPos.method_10264() + facing.method_10164(), selfPos.method_10260() + facing.method_10165()
      );
      class_2680 otherState = view.method_8320(otherPos);
      if (selfState.method_26187(otherState, facing)
         || PlatformBlockAccess.getInstance().shouldSkipRender(view, selfState, otherState, selfPos, otherPos, facing)) {
         return false;
      } else if (!otherState.method_26225()) {
         return true;
      } else {
         class_265 selfShape = selfState.method_26173(view, selfPos, facing);
         if (selfShape.method_1110()) {
            return true;
         } else {
            class_265 otherShape = otherState.method_26173(view, otherPos, DirectionUtil.getOpposite(facing));
            if (otherShape.method_1110()) {
               return true;
            } else {
               return selfShape == class_259.method_1077() && otherShape == class_259.method_1077() ? false : this.lookup(selfShape, otherShape);
            }
         }
      }
   }

   public boolean shouldDrawFullBlockFluidSide(
      class_2680 selfBlockState, class_1922 view, class_2338 selfPos, class_2350 facing, class_3610 fluid, class_265 fluidShape
   ) {
      boolean fluidShapeIsBlock = fluidShape == class_259.method_1077();
      if (selfBlockState.method_26225()) {
         class_265 selfShape = selfBlockState.method_26173(view, selfPos, facing);
         if (!selfShape.method_1110()) {
            if (selfShape == class_259.method_1077() && fluidShapeIsBlock) {
               return false;
            }

            if (!this.lookup(fluidShape, selfShape)) {
               return false;
            }
         }
      }

      class_2339 otherPos = this.cachedPositionObject;
      otherPos.method_10103(
         selfPos.method_10263() + facing.method_10148(), selfPos.method_10264() + facing.method_10164(), selfPos.method_10260() + facing.method_10165()
      );
      class_2680 otherState = view.method_8320(otherPos);
      if (otherState.method_26227() == fluid) {
         return false;
      } else if (PlatformBlockAccess.getInstance().shouldOccludeFluid(facing.method_10153(), otherState, fluid)) {
         return false;
      } else if (facing == class_2350.field_11036) {
         return true;
      } else if (!otherState.method_26225()) {
         return true;
      } else {
         class_265 otherShape = otherState.method_26173(view, otherPos, facing.method_10153());
         return otherShape.method_1110() ? true : otherShape != class_259.method_1077() || !fluidShapeIsBlock;
      }
   }

   private boolean lookup(class_265 self, class_265 other) {
      BlockOcclusionCache.ShapeComparison comparison = this.cachedComparisonObject;
      comparison.self = self;
      comparison.other = other;

      return switch (this.comparisonLookupTable.getAndMoveToFirst(comparison)) {
         case 0 -> false;
         case 1 -> true;
         default -> this.calculate(comparison);
      };
   }

   private boolean calculate(BlockOcclusionCache.ShapeComparison comparison) {
      boolean result = class_259.method_1074(comparison.self, comparison.other, class_247.field_16886);

      while (this.comparisonLookupTable.size() >= 512) {
         this.comparisonLookupTable.removeLastInt();
      }

      this.comparisonLookupTable.putAndMoveToFirst(comparison.copy(), result ? 1 : 0);
      return result;
   }

   private static boolean isFullShape(class_265 selfShape) {
      return selfShape == class_259.method_1077();
   }

   private static boolean isEmptyShape(class_265 voxelShape) {
      return voxelShape == class_259.method_1073() || voxelShape.method_1110();
   }

   private static final class ShapeComparison {
      private class_265 self;
      private class_265 other;

      private ShapeComparison() {
      }

      private ShapeComparison(class_265 self, class_265 other) {
         this.self = self;
         this.other = other;
      }

      public BlockOcclusionCache.ShapeComparison copy() {
         return new BlockOcclusionCache.ShapeComparison(this.self, this.other);
      }

      public static class ShapeComparisonStrategy implements Strategy<BlockOcclusionCache.ShapeComparison> {
         public int hashCode(BlockOcclusionCache.ShapeComparison value) {
            int result = System.identityHashCode(value.self);
            return 31 * result + System.identityHashCode(value.other);
         }

         public boolean equals(BlockOcclusionCache.ShapeComparison a, BlockOcclusionCache.ShapeComparison b) {
            if (a == b) {
               return true;
            } else {
               return a != null && b != null ? a.self == b.self && a.other == b.other : false;
            }
         }
      }
   }
}
