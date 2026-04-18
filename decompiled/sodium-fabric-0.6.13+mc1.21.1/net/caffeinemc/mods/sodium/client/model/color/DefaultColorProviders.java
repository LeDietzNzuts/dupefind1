package net.caffeinemc.mods.sodium.client.model.color;

import java.util.Arrays;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.model.quad.blender.BlendedColorProvider;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_1163;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_322;
import net.minecraft.class_2338.class_2339;

public class DefaultColorProviders {
   public static ColorProvider<class_2680> adapt(class_322 color) {
      return new DefaultColorProviders.VanillaAdapter(color);
   }

   public static class FoliageColorProvider<T> extends BlendedColorProvider<T> {
      public static final ColorProvider<class_2680> BLOCKS = new DefaultColorProviders.FoliageColorProvider<>();

      private FoliageColorProvider() {
      }

      @Override
      protected int getColor(LevelSlice slice, T state, class_2338 pos) {
         return 0xFF000000 | class_1163.method_4966(slice, pos);
      }
   }

   public static class GrassColorProvider<T> extends BlendedColorProvider<T> {
      public static final ColorProvider<class_2680> BLOCKS = new DefaultColorProviders.GrassColorProvider<>();

      private GrassColorProvider() {
      }

      @Override
      protected int getColor(LevelSlice slice, T state, class_2338 pos) {
         return 0xFF000000 | class_1163.method_4962(slice, pos);
      }
   }

   private static class VanillaAdapter implements ColorProvider<class_2680> {
      private final class_322 color;

      private VanillaAdapter(class_322 color) {
         this.color = color;
      }

      public void getColors(LevelSlice slice, class_2338 pos, class_2339 scratchPos, class_2680 state, ModelQuadView quad, int[] output) {
         Arrays.fill(output, 0xFF000000 | this.color.getColor(state, slice, pos, quad.getColorIndex()));
      }
   }
}
