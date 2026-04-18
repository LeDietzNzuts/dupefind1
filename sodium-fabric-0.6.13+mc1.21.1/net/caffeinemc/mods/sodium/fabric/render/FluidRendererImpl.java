package net.caffeinemc.mods.sodium.fabric.render;

import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.color.ColorProviderRegistry;
import net.caffeinemc.mods.sodium.client.model.light.LightPipelineProvider;
import net.caffeinemc.mods.sodium.client.model.quad.blender.BlendedColorProvider;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkBuildBuffers;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.buffers.ChunkModelBuilder;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.DefaultFluidRenderer;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.FluidRenderer;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.DefaultMaterials;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.Material;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TranslucentGeometryCollector;
import net.caffeinemc.mods.sodium.client.services.FluidRendererFactory;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRendering;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRendering.DefaultRenderer;
import net.minecraft.class_1163;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3486;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_4588;

public class FluidRendererImpl extends FluidRenderer {
   private final ColorProviderRegistry colorProviderRegistry;
   private final DefaultFluidRenderer defaultRenderer;
   private final FluidRendererImpl.DefaultRenderContext defaultContext;

   public FluidRendererImpl(ColorProviderRegistry colorProviderRegistry, LightPipelineProvider lighters) {
      this.colorProviderRegistry = colorProviderRegistry;
      this.defaultRenderer = new DefaultFluidRenderer(lighters);
      this.defaultContext = new FluidRendererImpl.DefaultRenderContext();
   }

   @Override
   public void render(
      LevelSlice level,
      class_2680 blockState,
      class_3610 fluidState,
      class_2338 blockPos,
      class_2338 offset,
      TranslucentGeometryCollector collector,
      ChunkBuildBuffers buffers
   ) {
      Material material = DefaultMaterials.forFluidState(fluidState);
      ChunkModelBuilder meshBuilder = buffers.get(material);
      FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(fluidState.method_15772());
      boolean hasModOverride = FluidRenderHandlerRegistry.INSTANCE.getOverride(fluidState.method_15772()) != null;
      if (handler == null) {
         boolean isLava = fluidState.method_15767(class_3486.field_15518);
         handler = FluidRenderHandlerRegistry.INSTANCE.get(isLava ? class_3612.field_15908 : class_3612.field_15910);
      }

      this.defaultContext
         .setUp(
            this.colorProviderRegistry,
            this.defaultRenderer,
            level,
            blockState,
            fluidState,
            blockPos,
            offset,
            collector,
            meshBuilder,
            material,
            handler,
            hasModOverride
         );

      try {
         FluidRendering.render(handler, level, blockPos, meshBuilder.asFallbackVertexConsumer(material, collector), blockState, fluidState, this.defaultContext);
      } finally {
         this.defaultContext.clear();
      }
   }

   private static class DefaultRenderContext implements DefaultRenderer {
      private DefaultFluidRenderer renderer;
      private LevelSlice level;
      private class_2680 blockState;
      private class_3610 fluidState;
      private class_2338 blockPos;
      private class_2338 offset;
      private TranslucentGeometryCollector collector;
      private ChunkModelBuilder meshBuilder;
      private Material material;
      private FluidRenderHandler handler;
      private ColorProviderRegistry colorProviderRegistry;
      private boolean hasModOverride;

      public void setUp(
         ColorProviderRegistry colorProviderRegistry,
         DefaultFluidRenderer renderer,
         LevelSlice level,
         class_2680 blockState,
         class_3610 fluidState,
         class_2338 blockPos,
         class_2338 offset,
         TranslucentGeometryCollector collector,
         ChunkModelBuilder meshBuilder,
         Material material,
         FluidRenderHandler handler,
         boolean hasModOverride
      ) {
         this.colorProviderRegistry = colorProviderRegistry;
         this.renderer = renderer;
         this.level = level;
         this.blockState = blockState;
         this.fluidState = fluidState;
         this.blockPos = blockPos;
         this.offset = offset;
         this.collector = collector;
         this.meshBuilder = meshBuilder;
         this.material = material;
         this.handler = handler;
         this.hasModOverride = hasModOverride;
      }

      public void clear() {
         this.renderer = null;
         this.level = null;
         this.blockState = null;
         this.fluidState = null;
         this.blockPos = null;
         this.offset = null;
         this.collector = null;
         this.meshBuilder = null;
         this.material = null;
         this.handler = null;
         this.hasModOverride = false;
      }

      public ColorProvider<class_3610> getColorProvider(class_3611 fluid) {
         ColorProvider<class_3610> override = this.colorProviderRegistry.getColorProvider(fluid);
         return !this.hasModOverride && override != null ? override : FabricColorProviders.adapt(this.handler);
      }

      public void render(FluidRenderHandler handler, class_1920 world, class_2338 pos, class_4588 vertexConsumer, class_2680 blockState, class_3610 fluidState) {
         this.renderer
            .render(
               this.level,
               this.blockState,
               this.fluidState,
               this.blockPos,
               this.offset,
               this.collector,
               this.meshBuilder,
               this.material,
               this.getColorProvider(fluidState.method_15772()),
               handler.getFluidSprites(this.level, this.blockPos, this.fluidState)
            );
      }
   }

   public static class FabricFactory implements FluidRendererFactory {
      @Override
      public FluidRenderer createPlatformFluidRenderer(ColorProviderRegistry colorRegistry, LightPipelineProvider lightPipelineProvider) {
         return new FluidRendererImpl(colorRegistry, lightPipelineProvider);
      }

      @Override
      public BlendedColorProvider<class_3610> getWaterColorProvider() {
         return new BlendedColorProvider<class_3610>() {
            protected int getColor(LevelSlice slice, class_3610 state, class_2338 pos) {
               return class_1163.method_4961(slice, pos) | 0xFF000000;
            }
         };
      }

      @Override
      public BlendedColorProvider<class_2680> getWaterBlockColorProvider() {
         return new BlendedColorProvider<class_2680>() {
            protected int getColor(LevelSlice slice, class_2680 state, class_2338 pos) {
               return class_1163.method_4961(slice, pos) | 0xFF000000;
            }
         };
      }
   }
}
