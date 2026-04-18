package net.caffeinemc.mods.lithium.mixin.alloc.chunk_random;

import net.caffeinemc.mods.lithium.common.world.ChunkRandomSource;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3610;
import net.minecraft.class_5819;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3218.class)
public abstract class ServerLevelMixin {
   private final class_2339 randomPosInChunkCachedPos = new class_2339();

   @Redirect(
      method = "method_18203(Lnet/minecraft/class_2818;I)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3218;method_8536(IIII)Lnet/minecraft/class_2338;")
   )
   private class_2338 redirectTickGetRandomPosInChunk(class_3218 serverWorld, int x, int y, int z, int mask) {
      ((ChunkRandomSource)serverWorld).lithium$getRandomPosInChunk(x, y, z, mask, this.randomPosInChunkCachedPos);
      return this.randomPosInChunkCachedPos;
   }

   @Redirect(
      method = "method_18203(Lnet/minecraft/class_2818;I)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2680;method_26199(Lnet/minecraft/class_3218;Lnet/minecraft/class_2338;Lnet/minecraft/class_5819;)V"
      )
   )
   private void redirectBlockStateTick(class_2680 blockState, class_3218 world, class_2338 pos, class_5819 rand) {
      blockState.method_26199(world, pos.method_10062(), rand);
   }

   @Redirect(
      method = "method_18203(Lnet/minecraft/class_2818;I)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_3610;method_15757(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_5819;)V"
      )
   )
   private void redirectFluidStateTick(class_3610 fluidState, class_1937 world, class_2338 pos, class_5819 rand) {
      fluidState.method_15757(world, pos.method_10062(), rand);
   }
}
