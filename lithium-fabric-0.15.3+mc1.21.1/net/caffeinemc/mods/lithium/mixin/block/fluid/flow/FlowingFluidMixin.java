package net.caffeinemc.mods.lithium.mixin.block.fluid.flow;

import com.google.common.collect.Maps;
import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.bytes.Byte2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.bytes.Byte2ByteOpenHashMap;
import it.unimi.dsi.fastutil.bytes.Byte2ByteMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Map;
import net.caffeinemc.mods.lithium.common.util.DirectionConstants;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2478;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_3609;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_4538;
import net.minecraft.class_6862;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3609.class)
public abstract class FlowingFluidMixin {
   @Shadow
   public abstract class_3611 method_15750();

   @Shadow
   protected abstract class_3610 method_15727(class_1937 var1, class_2338 var2, class_2680 var3);

   @Shadow
   protected abstract boolean method_15754(class_1922 var1, class_2338 var2, class_2680 var3, class_3611 var4);

   @Shadow
   public abstract class_3611 method_15751();

   @Shadow
   protected abstract boolean method_15752(class_3610 var1);

   @Shadow
   protected abstract boolean method_15732(class_2350 var1, class_1922 var2, class_2338 var3, class_2680 var4, class_2338 var5, class_2680 var6);

   @Shadow
   protected abstract int method_15733(class_4538 var1);

   @Unique
   private static int getNumIndicesFromRadius(int radius) {
      return (radius + 1) * (2 * radius + 1);
   }

   @Unique
   private static byte indexFromDiamondXZOffset(class_2338 originPos, class_2338 offsetPos, int radius) {
      int xOffset = offsetPos.method_10263() - originPos.method_10263();
      int zOffset = offsetPos.method_10260() - originPos.method_10260();
      int row = (xOffset + zOffset + radius) / 2;
      int column = xOffset - zOffset + radius;
      int rowLength = 2 * radius + 1;
      return (byte)(row * rowLength + column);
   }

   @Inject(
      method = "method_15726(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)Ljava/util/Map;",
      at = @At("HEAD"),
      cancellable = true
   )
   public void getSpread(class_1937 world, class_2338 pos, class_2680 state, CallbackInfoReturnable<Map<class_2350, class_3610>> cir) {
      Map<class_2350, class_3610> flowResultByDirection = Maps.newEnumMap(class_2350.class);
      int searchRadius = this.method_15733(world) + 1;
      int numIndicesFromRadius = getNumIndicesFromRadius(searchRadius);
      if (numIndicesFromRadius <= 256) {
         class_2680[] blockStateCache = new class_2680[numIndicesFromRadius];
         class_2350 onlyPossibleFlowDirection = null;
         class_2338 onlyBlockPos = null;
         class_2680 onlyBlockState = null;

         for (class_2350 flowDirection : DirectionConstants.HORIZONTAL) {
            class_2338 flowTargetPos = pos.method_10093(flowDirection);
            byte blockIndex = indexFromDiamondXZOffset(pos, flowTargetPos, searchRadius);
            class_2680 flowTargetBlock = world.method_8320(flowTargetPos);
            blockStateCache[blockIndex] = flowTargetBlock;
            if (this.canMaybeFlowIntoBlock(world, flowTargetBlock, flowTargetPos)) {
               if (onlyPossibleFlowDirection != null) {
                  this.calculateComplexFluidFlowDirections(world, pos, state, blockStateCache, flowResultByDirection);
                  cir.setReturnValue(flowResultByDirection);
                  return;
               }

               onlyPossibleFlowDirection = flowDirection;
               onlyBlockPos = flowTargetPos;
               onlyBlockState = flowTargetBlock;
            }
         }

         if (onlyPossibleFlowDirection != null) {
            class_3610 targetNewFluidState = this.method_15727(world, onlyBlockPos, onlyBlockState);
            if (this.method_15746(
               world, targetNewFluidState.method_15772(), pos, state, onlyPossibleFlowDirection, onlyBlockPos, onlyBlockState, onlyBlockState.method_26227()
            )) {
               flowResultByDirection.put(onlyPossibleFlowDirection, targetNewFluidState);
            }
         }

         cir.setReturnValue(flowResultByDirection);
      }
   }

   @Overwrite
   private boolean method_15746(
      class_1922 world, class_3611 fluid, class_2338 pos, class_2680 state, class_2350 face, class_2338 fromPos, class_2680 fromState, class_3610 fluidState
   ) {
      return this.method_15754(world, fromPos, fromState, fluid)
         && !this.method_15752(fluidState)
         && this.method_15732(face, world, pos, state, fromPos, fromState);
   }

   @Overwrite
   private boolean method_15736(class_1922 world, class_3611 fluid, class_2338 pos, class_2680 state, class_2338 fromPos, class_2680 fromState) {
      return (fromState.method_26227().method_15772().method_15780((class_3609)this) || this.method_15754(world, fromPos, fromState, fluid))
         && this.method_15732(class_2350.field_11033, world, pos, state, fromPos, fromState);
   }

   @Unique
   private void calculateComplexFluidFlowDirections(
      class_1937 world, class_2338 startPos, class_2680 startState, class_2680[] blockStateCache, Map<class_2350, class_3610> flowResultByDirection
   ) {
      Byte2ByteOpenHashMap prevPositions = new Byte2ByteOpenHashMap();
      Byte2ByteOpenHashMap currentPositions = new Byte2ByteOpenHashMap();
      Byte2BooleanOpenHashMap holeCache = new Byte2BooleanOpenHashMap();
      byte holeAccess = 0;
      int searchRadius = this.method_15733(world) + 1;

      for (int i = 0; i < DirectionConstants.HORIZONTAL.length; i++) {
         class_2350 flowDirection = DirectionConstants.HORIZONTAL[i];
         class_2338 flowTargetPos = startPos.method_10093(flowDirection);
         byte blockIndex = indexFromDiamondXZOffset(startPos, flowTargetPos, searchRadius);
         class_2680 targetBlockState = this.getBlock(world, flowTargetPos, blockStateCache, blockIndex);
         class_3610 targetNewFluidState = this.method_15727(world, flowTargetPos, targetBlockState);
         flowResultByDirection.put(flowDirection, targetNewFluidState);
         if (this.method_15746(
            world, targetNewFluidState.method_15772(), startPos, startState, flowDirection, flowTargetPos, targetBlockState, targetBlockState.method_26227()
         )) {
            prevPositions.put(blockIndex, (byte)(17 << i));
            if (this.isHoleBelow(world, holeCache, blockIndex, flowTargetPos, targetBlockState)) {
               holeAccess |= (byte)(1 << i);
            }
         }
      }

      for (int ix = 0; ix < this.method_15733(world) && holeAccess == 0; ix++) {
         class_3611 targetFluid = this.method_15750();
         ObjectIterator<Entry> iterator = prevPositions.byte2ByteEntrySet().fastIterator();

         while (iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            byte blockIndex = entry.getByteKey();
            byte currentInfo = entry.getByteValue();
            int rowLength = 2 * searchRadius + 1;
            int row = blockIndex / rowLength;
            int column = blockIndex % rowLength;
            int unevenColumn = column % 2;
            int xOffset = (row * 2 + column + unevenColumn - searchRadius * 2) / 2;
            int zOffset = xOffset - column + searchRadius;
            class_2338 currentPos = startPos.method_10069(xOffset, 0, zOffset);
            class_2680 currentState = blockStateCache[blockIndex];

            for (int j = 0; j < DirectionConstants.HORIZONTAL.length; j++) {
               class_2350 flowDirection = DirectionConstants.HORIZONTAL[j];
               int oppositeDirection = DirectionConstants.HORIZONTAL_OPPOSITE_INDICES[j];
               if ((currentInfo >> 4 & 1 << oppositeDirection) == 0) {
                  class_2338 flowTargetPos = currentPos.method_10093(flowDirection);
                  byte targetPosBlockIndex = indexFromDiamondXZOffset(startPos, flowTargetPos, searchRadius);
                  if (!prevPositions.containsKey(targetPosBlockIndex)) {
                     byte oldInfo = currentPositions.getOrDefault(targetPosBlockIndex, (byte)0);
                     byte newInfo = (byte)(oldInfo | (byte)(16 << j));
                     newInfo = (byte)(newInfo | (byte)(currentInfo & 15));
                     if ((newInfo & 15) == (oldInfo & 15)) {
                        currentPositions.put(targetPosBlockIndex, newInfo);
                     } else {
                        class_2680 targetBlockState = this.getBlock(world, flowTargetPos, blockStateCache, targetPosBlockIndex);
                        if (this.method_15746(
                           world, targetFluid, currentPos, currentState, flowDirection, flowTargetPos, targetBlockState, targetBlockState.method_26227()
                        )) {
                           currentPositions.put(targetPosBlockIndex, newInfo);
                           if (this.isHoleBelow(world, holeCache, targetPosBlockIndex, flowTargetPos, targetBlockState)) {
                              holeAccess |= (byte)(currentInfo & 15);
                           }
                        }
                     }
                  }
               }
            }
         }

         Byte2ByteOpenHashMap tmp = prevPositions;
         prevPositions = currentPositions;
         currentPositions = tmp;
         tmp.clear();
      }

      if (holeAccess != 0) {
         this.removeDirectionsWithoutHoleAccess(holeAccess, flowResultByDirection);
      }
   }

   @Unique
   private class_2680 getBlock(class_1937 world, class_2338 pos, class_2680[] blockStateCache, byte byteKey) {
      int key = Byte.toUnsignedInt(byteKey);
      class_2680 blockState = blockStateCache[key];
      if (blockState == null) {
         blockState = world.method_8320(pos);
         blockStateCache[key] = blockState;
      }

      return blockState;
   }

   @Unique
   private void removeDirectionsWithoutHoleAccess(byte holeAccess, Map<class_2350, class_3610> flowResultByDirection) {
      for (int i = 0; i < DirectionConstants.HORIZONTAL.length; i++) {
         if ((holeAccess & 1 << i) == 0) {
            flowResultByDirection.remove(DirectionConstants.HORIZONTAL[i]);
         }
      }
   }

   @Unique
   private boolean canMaybeFlowIntoBlock(class_1937 world, class_2680 blockState, class_2338 flowTargetPos) {
      return this.method_15754(world, flowTargetPos, blockState, this.method_15751());
   }

   @Unique
   private boolean isHoleBelow(class_4538 world, Byte2BooleanOpenHashMap holeCache, byte key, class_2338 flowTargetPos, class_2680 targetBlockState) {
      if (holeCache.containsKey(key)) {
         return holeCache.get(key);
      } else {
         class_2338 downPos = flowTargetPos.method_10074();
         class_2680 downBlock = world.method_8320(downPos);
         boolean holeFound = this.method_15736(world, this.method_15750(), flowTargetPos, targetBlockState, downPos, downBlock);
         holeCache.put(key, holeFound);
         return holeFound;
      }
   }

   @Redirect(
      method = "method_15754(Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_3611;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_26164(Lnet/minecraft/class_6862;)Z")
   )
   private boolean isSign(class_2680 blockState, class_6862<class_2248> tagKey, @Local class_2248 block) {
      return tagKey == class_3481.field_15500 ? block instanceof class_2478 : blockState.method_26164(tagKey);
   }
}
