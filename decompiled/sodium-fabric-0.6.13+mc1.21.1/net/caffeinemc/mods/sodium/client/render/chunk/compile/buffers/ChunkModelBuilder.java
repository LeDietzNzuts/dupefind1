package net.caffeinemc.mods.sodium.client.render.chunk.compile.buffers;

import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFacing;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.Material;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TranslucentGeometryCollector;
import net.caffeinemc.mods.sodium.client.render.chunk.vertex.builder.ChunkMeshBufferBuilder;
import net.minecraft.class_1058;
import net.minecraft.class_4588;
import org.jetbrains.annotations.NotNull;

public interface ChunkModelBuilder {
   ChunkMeshBufferBuilder getVertexBuffer(ModelQuadFacing var1);

   void addSprite(@NotNull class_1058 var1);

   class_4588 asFallbackVertexConsumer(Material var1, TranslucentGeometryCollector var2);
}
