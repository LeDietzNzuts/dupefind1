package net.caffeinemc.mods.lithium.mixin.util.chunk_status_tracking;

import java.util.function.Supplier;
import net.caffeinemc.mods.lithium.common.world.chunk.ChunkStatusTracker;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_1959;
import net.minecraft.class_2378;
import net.minecraft.class_2791;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_3194;
import net.minecraft.class_3218;
import net.minecraft.class_5539;
import net.minecraft.class_6749;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2818.class)
public abstract class LevelChunkMixin extends class_2791 {
   public LevelChunkMixin(
      class_1923 chunkPos,
      class_2843 upgradeData,
      class_5539 levelHeightAccessor,
      class_2378<class_1959> registry,
      long l,
      @Nullable class_2826[] levelChunkSections,
      @Nullable class_6749 blendingData
   ) {
      super(chunkPos, upgradeData, levelHeightAccessor, registry, l, levelChunkSections, blendingData);
   }

   @Shadow
   public abstract class_1937 method_12200();

   @Inject(method = "method_12207(Ljava/util/function/Supplier;)V", at = @At("RETURN"))
   private void onChunkFull(Supplier<class_3194> supplier, CallbackInfo ci) {
      if (supplier != null && this.method_12200() instanceof class_3218 serverLevel) {
         ChunkStatusTracker.onChunkAccessible(serverLevel, (class_2818)this);
      }
   }
}
