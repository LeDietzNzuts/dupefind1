package net.caffeinemc.mods.sodium.client.render.chunk.compile;

import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderCache;
import net.caffeinemc.mods.sodium.client.render.chunk.vertex.format.ChunkVertexType;
import net.minecraft.class_310;
import net.minecraft.class_638;

public class ChunkBuildContext {
   public final ChunkBuildBuffers buffers;
   public final BlockRenderCache cache;

   public ChunkBuildContext(class_638 level, ChunkVertexType vertexType) {
      this.buffers = new ChunkBuildBuffers(vertexType);
      this.cache = new BlockRenderCache(class_310.method_1551(), level);
   }

   public void cleanup() {
      this.buffers.destroy();
      this.cache.cleanup();
   }
}
