package net.caffeinemc.mods.lithium.mixin.util.block_tracking;

import net.caffeinemc.mods.lithium.common.block.BlockCountingSection;
import net.caffeinemc.mods.lithium.common.block.BlockListeningSection;
import net.caffeinemc.mods.lithium.common.block.BlockStateFlagHolder;
import net.caffeinemc.mods.lithium.common.block.BlockStateFlags;
import net.caffeinemc.mods.lithium.common.block.ListeningBlockStatePredicate;
import net.caffeinemc.mods.lithium.common.block.TrackedBlockStatePredicate;
import net.caffeinemc.mods.lithium.common.tracking.block.ChunkSectionChangeCallback;
import net.caffeinemc.mods.lithium.common.tracking.block.SectionedBlockChangeTracker;
import net.minecraft.class_1937;
import net.minecraft.class_2378;
import net.minecraft.class_2540;
import net.minecraft.class_2680;
import net.minecraft.class_2826;
import net.minecraft.class_2841;
import net.minecraft.class_2841.class_4464;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_2826.class)
public abstract class LevelChunkSectionMixin implements BlockCountingSection, BlockListeningSection {
   @Shadow
   @Final
   private class_2841<class_2680> field_12878;
   @Unique
   private short[] countsByFlag = null;
   @Unique
   private ChunkSectionChangeCallback changeListener;
   @Unique
   private short listeningMask;

   @Unique
   private static void addToFlagCount(short[] countsByFlag, class_2680 state, short change) {
      int flags = ((BlockStateFlagHolder)state).lithium$getAllFlags();

      int i;
      while ((i = Integer.numberOfTrailingZeros(flags)) < 32 && i < countsByFlag.length) {
         countsByFlag[i] += change;
         flags &= ~(1 << i);
      }
   }

   @Override
   public boolean lithium$mayContainAny(TrackedBlockStatePredicate trackedBlockStatePredicate) {
      if (this.countsByFlag == null) {
         this.fastInitClientCounts();
      }

      return this.countsByFlag[trackedBlockStatePredicate.getIndex()] != 0;
   }

   @Unique
   private void fastInitClientCounts() {
      this.countsByFlag = new short[BlockStateFlags.NUM_TRACKED_FLAGS];

      for (TrackedBlockStatePredicate trackedBlockStatePredicate : BlockStateFlags.TRACKED_FLAGS) {
         if (this.field_12878.method_19526(trackedBlockStatePredicate)) {
            this.countsByFlag[trackedBlockStatePredicate.getIndex()] = 4096;
         }
      }
   }

   @Inject(method = "Lnet/minecraft/class_2826;<init>(Lnet/minecraft/class_2378;)V", at = @At("RETURN"))
   private void initAirSection(class_2378<?> registry, CallbackInfo ci) {
      if (this.countsByFlag != null) {
         throw new IllegalStateException("CountsByFlag already initialized!");
      } else {
         this.countsByFlag = new short[BlockStateFlags.NUM_TRACKED_FLAGS];

         for (TrackedBlockStatePredicate trackedBlockStatePredicate : BlockStateFlags.TRACKED_FLAGS) {
            if (this.field_12878.method_19526(trackedBlockStatePredicate)) {
               this.countsByFlag[trackedBlockStatePredicate.getIndex()] = 4096;
            }
         }
      }
   }

   @Redirect(method = "method_12253()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2841;method_21732(Lnet/minecraft/class_2841$class_4464;)V"))
   private void initFlagCounters(class_2841<class_2680> palettedContainer, class_4464<class_2680> consumer) {
      palettedContainer.method_21732((state, count) -> {
         consumer.accept(state, count);
         addToFlagCount(this.countsByFlag, state, (short)count);
      });
   }

   @Inject(method = "method_12253()V", at = @At("HEAD"))
   private void createFlagCounters(CallbackInfo ci) {
      this.countsByFlag = new short[BlockStateFlags.NUM_TRACKED_FLAGS];
   }

   @Inject(method = "method_12258(Lnet/minecraft/class_2540;)V", at = @At("HEAD"))
   private void resetData(class_2540 buf, CallbackInfo ci) {
      this.countsByFlag = null;
   }

   @Inject(
      method = "method_12256(IIILnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_26227()Lnet/minecraft/class_3610;", ordinal = 0, shift = Shift.BEFORE),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void updateFlagCounters(int x, int y, int z, class_2680 newState, boolean lock, CallbackInfoReturnable<class_2680> cir, class_2680 oldState) {
      this.lithium$trackBlockStateChange(newState, oldState);
   }

   @Override
   public void lithium$trackBlockStateChange(class_2680 newState, class_2680 oldState) {
      short[] countsByFlag = this.countsByFlag;
      if (countsByFlag != null) {
         int prevFlags = ((BlockStateFlagHolder)oldState).lithium$getAllFlags();
         int flags = ((BlockStateFlagHolder)newState).lithium$getAllFlags();
         int flagsXOR = prevFlags ^ flags;
         int iterateFlags = ~BlockStateFlags.LISTENING_MASK_OR & flagsXOR | BlockStateFlags.LISTENING_MASK_OR & this.listeningMask & (prevFlags | flags);

         int flagIndex;
         while ((flagIndex = Integer.numberOfTrailingZeros(iterateFlags)) < 32 && flagIndex < countsByFlag.length) {
            int flagBit = 1 << flagIndex;
            if ((flagsXOR & flagBit) != 0) {
               countsByFlag[flagIndex] += (short)(1 - ((prevFlags >>> flagIndex & 1) << 1));
            }

            if ((this.listeningMask & flagBit) != 0) {
               this.listeningMask = this.changeListener.onBlockChange(flagIndex, this);
            }

            iterateFlags &= ~flagBit;
         }
      }
   }

   @Override
   public void lithium$addToCallback(ListeningBlockStatePredicate blockGroup, SectionedBlockChangeTracker tracker, long sectionPos, class_1937 world) {
      if (this.changeListener == null) {
         if (sectionPos == Long.MIN_VALUE || world == null) {
            throw new IllegalArgumentException("Expected world and section pos during intialization!");
         }

         this.changeListener = ChunkSectionChangeCallback.create(sectionPos, world);
      }

      this.listeningMask = this.changeListener.addTracker(tracker, blockGroup);
   }

   @Override
   public void lithium$removeFromCallback(ListeningBlockStatePredicate blockGroup, SectionedBlockChangeTracker tracker) {
      if (this.changeListener != null) {
         this.listeningMask = this.changeListener.removeTracker(tracker, blockGroup);
      }
   }
}
