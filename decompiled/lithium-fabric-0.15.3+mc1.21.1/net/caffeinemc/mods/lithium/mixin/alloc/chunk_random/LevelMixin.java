package net.caffeinemc.mods.lithium.mixin.alloc.chunk_random;

import net.caffeinemc.mods.lithium.common.world.ChunkRandomSource;
import net.minecraft.class_1937;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_1937.class)
public class LevelMixin implements ChunkRandomSource {
   @Shadow
   protected int field_9256;

   @Override
   public void lithium$getRandomPosInChunk(int x, int y, int z, int mask, class_2339 out) {
      this.field_9256 = this.field_9256 * 3 + 1013904223;
      int rand = this.field_9256 >> 2;
      out.method_10103(x + (rand & 15), y + (rand >> 16 & mask), z + (rand >> 8 & 15));
   }
}
