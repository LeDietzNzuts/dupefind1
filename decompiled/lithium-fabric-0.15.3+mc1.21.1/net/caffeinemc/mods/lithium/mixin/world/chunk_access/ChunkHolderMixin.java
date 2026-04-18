package net.caffeinemc.mods.lithium.mixin.world.chunk_access;

import net.caffeinemc.mods.lithium.common.world.chunk.ChunkHolderExtended;
import net.minecraft.class_3193;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_3193.class)
public class ChunkHolderMixin implements ChunkHolderExtended {
   @Unique
   private long lastRequestTime;

   @Override
   public boolean lithium$updateLastAccessTime(long time) {
      long prev = this.lastRequestTime;
      this.lastRequestTime = time;
      return prev != time;
   }
}
