package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.chunk_tickable;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalLongRef;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1937.class)
public class LevelMixin {
   @Redirect(method = "method_18471()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_41411(Lnet/minecraft/class_2338;)Z"))
   private boolean optimizedShouldTick(class_1937 instance, class_2338 pos, @Share("ShouldTickPos") LocalLongRef lastTickableChunk) {
      if (pos == null) {
         return false;
      } else {
         long chunkPos = class_1923.method_37232(pos);
         if (chunkPos == lastTickableChunk.get()) {
            return true;
         } else {
            boolean b = instance.method_39425(chunkPos);
            if (b) {
               lastTickableChunk.set(chunkPos);
            }

            return b;
         }
      }
   }
}
