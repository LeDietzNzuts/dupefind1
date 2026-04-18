package net.caffeinemc.mods.lithium.mixin.util.chunk_status_tracking;

import java.util.concurrent.Executor;
import net.caffeinemc.mods.lithium.common.world.chunk.ChunkStatusTracker;
import net.minecraft.class_1923;
import net.minecraft.class_2806;
import net.minecraft.class_2818;
import net.minecraft.class_3193;
import net.minecraft.class_3194;
import net.minecraft.class_3218;
import net.minecraft.class_3898;
import net.minecraft.class_9761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = class_3193.class, priority = 1010)
public abstract class ChunkHolderMixin extends class_9761 {
   public ChunkHolderMixin(class_1923 chunkPos) {
      super(chunkPos);
   }

   @Inject(
      method = "method_14007(Lnet/minecraft/class_3898;Ljava/util/concurrent/Executor;)V",
      locals = LocalCapture.CAPTURE_FAILHARD,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3194;method_14014(Lnet/minecraft/class_3194;)Z", ordinal = 6),
      require = 0
   )
   private void trackUpdate(class_3898 chunkMap, Executor executor, CallbackInfo ci, class_3194 prevStatus, class_3194 status) {
      class_3218 serverLevel = chunkMap.field_17214;
      boolean loaded = status.method_14014(class_3194.field_44855);
      boolean wasLoaded = prevStatus.method_14014(class_3194.field_44855);
      if (!loaded && wasLoaded) {
         ChunkStatusTracker.onChunkInaccessible(serverLevel, this.field_51868);
      } else if (!wasLoaded && this.method_60457(class_2806.field_12803) instanceof class_2818 chunk) {
         ChunkStatusTracker.onChunkAccessible(serverLevel, chunk);
      }
   }
}
