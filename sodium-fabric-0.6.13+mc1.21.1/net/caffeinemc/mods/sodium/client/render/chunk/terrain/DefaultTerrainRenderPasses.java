package net.caffeinemc.mods.sodium.client.render.chunk.terrain;

import net.minecraft.class_1921;

public class DefaultTerrainRenderPasses {
   public static final TerrainRenderPass SOLID = new TerrainRenderPass(class_1921.method_23577(), false, false);
   public static final TerrainRenderPass CUTOUT = new TerrainRenderPass(class_1921.method_23579(), false, true);
   public static final TerrainRenderPass TRANSLUCENT = new TerrainRenderPass(class_1921.method_23583(), true, false);
   public static final TerrainRenderPass[] ALL = new TerrainRenderPass[]{SOLID, CUTOUT, TRANSLUCENT};
}
