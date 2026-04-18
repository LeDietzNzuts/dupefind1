package net.caffeinemc.mods.sodium.fabric.render;

import java.util.Arrays;
import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.minecraft.class_2338;
import net.minecraft.class_3610;
import net.minecraft.class_2338.class_2339;

public class FabricColorProviders {
   public static ColorProvider<class_3610> adapt(FluidRenderHandler handler) {
      return new FabricColorProviders.FabricFluidAdapter(handler);
   }

   private static class FabricFluidAdapter implements ColorProvider<class_3610> {
      private final FluidRenderHandler handler;

      public FabricFluidAdapter(FluidRenderHandler handler) {
         this.handler = handler;
      }

      public void getColors(LevelSlice slice, class_2338 pos, class_2339 scratchPos, class_3610 state, ModelQuadView quad, int[] output) {
         Arrays.fill(output, 0xFF000000 | this.handler.getFluidColor(slice, pos, state));
      }
   }
}
