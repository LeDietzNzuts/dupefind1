package net.caffeinemc.mods.sodium.client.render.chunk.map;

import net.minecraft.class_638;

public interface ChunkTrackerHolder {
   static ChunkTracker get(class_638 level) {
      return ((ChunkTrackerHolder)level).sodium$getTracker();
   }

   ChunkTracker sodium$getTracker();
}
