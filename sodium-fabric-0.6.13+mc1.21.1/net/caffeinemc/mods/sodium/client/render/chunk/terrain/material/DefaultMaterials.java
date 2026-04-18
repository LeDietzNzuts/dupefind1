package net.caffeinemc.mods.sodium.client.render.chunk.terrain.material;

import net.caffeinemc.mods.sodium.client.render.chunk.terrain.DefaultTerrainRenderPasses;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.parameters.AlphaCutoffParameter;
import net.minecraft.class_1921;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_4696;

public class DefaultMaterials {
   public static final Material SOLID = new Material(DefaultTerrainRenderPasses.SOLID, AlphaCutoffParameter.ZERO, true);
   public static final Material CUTOUT = new Material(DefaultTerrainRenderPasses.CUTOUT, AlphaCutoffParameter.ONE_TENTH, false);
   public static final Material CUTOUT_MIPPED = new Material(DefaultTerrainRenderPasses.CUTOUT, AlphaCutoffParameter.HALF, true);
   public static final Material TRANSLUCENT = new Material(DefaultTerrainRenderPasses.TRANSLUCENT, AlphaCutoffParameter.ZERO, true);
   public static final Material TRIPWIRE = new Material(DefaultTerrainRenderPasses.TRANSLUCENT, AlphaCutoffParameter.ONE_TENTH, true);

   public static Material forBlockState(class_2680 state) {
      return forRenderLayer(class_4696.method_23679(state));
   }

   public static Material forFluidState(class_3610 state) {
      return forRenderLayer(class_4696.method_23680(state));
   }

   public static Material forRenderLayer(class_1921 layer) {
      if (layer == class_1921.method_23577()) {
         return SOLID;
      } else if (layer == class_1921.method_23581()) {
         return CUTOUT;
      } else if (layer == class_1921.method_23579()) {
         return CUTOUT_MIPPED;
      } else if (layer == class_1921.method_29997()) {
         return TRIPWIRE;
      } else if (layer == class_1921.method_23583()) {
         return TRANSLUCENT;
      } else {
         throw new IllegalArgumentException("No material mapping exists for " + layer);
      }
   }
}
