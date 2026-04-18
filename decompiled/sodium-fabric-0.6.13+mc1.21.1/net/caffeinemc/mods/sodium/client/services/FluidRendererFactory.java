package net.caffeinemc.mods.sodium.client.services;

import net.caffeinemc.mods.sodium.client.model.color.ColorProviderRegistry;
import net.caffeinemc.mods.sodium.client.model.light.LightPipelineProvider;
import net.caffeinemc.mods.sodium.client.model.quad.blender.BlendedColorProvider;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.FluidRenderer;
import net.minecraft.class_2680;
import net.minecraft.class_3610;

public interface FluidRendererFactory {
   FluidRendererFactory INSTANCE = Services.load(FluidRendererFactory.class);

   static FluidRendererFactory getInstance() {
      return INSTANCE;
   }

   FluidRenderer createPlatformFluidRenderer(ColorProviderRegistry var1, LightPipelineProvider var2);

   BlendedColorProvider<class_3610> getWaterColorProvider();

   BlendedColorProvider<class_2680> getWaterBlockColorProvider();
}
