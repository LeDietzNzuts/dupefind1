package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.ChunkEvent;
import net.minecraft.class_1923;
import net.minecraft.class_2487;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_3218;
import net.minecraft.class_3898;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_3898.class)
public class MixinChunkMap {
   @Shadow
   @Final
   class_3218 field_17214;

   @Inject(
      method = "save",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/server/level/ChunkMap;write(Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/nbt/CompoundTag;)Ljava/util/concurrent/CompletableFuture;",
         ordinal = 0
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void save(class_2791 chunkAccess, CallbackInfoReturnable<Boolean> cir, class_1923 pos, class_2806 chunkStatus, class_2487 nbt) {
      ChunkEvent.SAVE_DATA.invoker().save(chunkAccess, this.field_17214, nbt);
   }
}
