package net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline;

import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkBuildBuffers;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TranslucentGeometryCollector;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3610;

public abstract class FluidRenderer {
   public abstract void render(
      LevelSlice var1, class_2680 var2, class_3610 var3, class_2338 var4, class_2338 var5, TranslucentGeometryCollector var6, ChunkBuildBuffers var7
   );
}
