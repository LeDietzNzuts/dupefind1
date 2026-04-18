package net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline;

import net.caffeinemc.mods.sodium.client.model.color.ColorProviderRegistry;
import net.caffeinemc.mods.sodium.client.model.light.LightPipelineProvider;
import net.caffeinemc.mods.sodium.client.model.light.data.ArrayLightDataCache;
import net.caffeinemc.mods.sodium.client.services.FluidRendererFactory;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.caffeinemc.mods.sodium.client.world.cloned.ChunkRenderContext;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_773;

public class BlockRenderCache {
   private final ArrayLightDataCache lightDataCache;
   private final BlockRenderer blockRenderer;
   private final FluidRenderer fluidRenderer;
   private final class_773 blockModels;
   private final LevelSlice levelSlice;

   public BlockRenderCache(class_310 minecraft, class_638 level) {
      this.levelSlice = new LevelSlice(level);
      this.lightDataCache = new ArrayLightDataCache(this.levelSlice);
      LightPipelineProvider lightPipelineProvider = new LightPipelineProvider(this.lightDataCache);
      ColorProviderRegistry colorRegistry = new ColorProviderRegistry(minecraft.method_1505());
      this.blockRenderer = new BlockRenderer(colorRegistry, lightPipelineProvider);
      this.fluidRenderer = FluidRendererFactory.getInstance().createPlatformFluidRenderer(colorRegistry, lightPipelineProvider);
      this.blockModels = minecraft.method_1554().method_4743();
   }

   public class_773 getBlockModels() {
      return this.blockModels;
   }

   public BlockRenderer getBlockRenderer() {
      return this.blockRenderer;
   }

   public FluidRenderer getFluidRenderer() {
      return this.fluidRenderer;
   }

   public void init(ChunkRenderContext context) {
      this.lightDataCache.reset(context.getOrigin());
      this.levelSlice.copyData(context);
   }

   public LevelSlice getWorldSlice() {
      return this.levelSlice;
   }

   public void cleanup() {
      this.levelSlice.reset();
   }
}
