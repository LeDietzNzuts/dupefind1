package net.caffeinemc.mods.sodium.mixin.core.world.map;

import java.util.Objects;
import net.caffeinemc.mods.sodium.client.render.chunk.map.ChunkTracker;
import net.caffeinemc.mods.sodium.client.render.chunk.map.ChunkTrackerHolder;
import net.minecraft.class_1923;
import net.minecraft.class_2818;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_638.class)
public class ClientLevelMixin implements ChunkTrackerHolder {
   @Unique
   private final ChunkTracker chunkTracker = new ChunkTracker();

   @Override
   public ChunkTracker sodium$getTracker() {
      return Objects.requireNonNull(this.chunkTracker);
   }

   @Inject(method = "method_18110(Lnet/minecraft/class_2818;)V", at = @At("HEAD"))
   private void sodium$trackChunkUnload(class_2818 levelChunk, CallbackInfo ci) {
      class_1923 pos = levelChunk.method_12004();
      this.chunkTracker.onChunkStatusRemoved(pos.field_9181, pos.field_9180, 3);
   }
}
