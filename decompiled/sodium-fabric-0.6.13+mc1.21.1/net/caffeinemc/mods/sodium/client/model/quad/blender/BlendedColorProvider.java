package net.caffeinemc.mods.sodium.client.model.quad.blender;

import net.caffeinemc.mods.sodium.api.util.ColorMixer;
import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_2338;
import net.minecraft.class_3532;
import net.minecraft.class_2338.class_2339;

public abstract class BlendedColorProvider<T> implements ColorProvider<T> {
   @Override
   public void getColors(LevelSlice slice, class_2338 pos, class_2339 scratchPos, T state, ModelQuadView quad, int[] output) {
      for (int vertexIndex = 0; vertexIndex < 4; vertexIndex++) {
         output[vertexIndex] = this.getVertexColor(slice, pos, scratchPos, quad, state, vertexIndex);
      }
   }

   private int getVertexColor(LevelSlice slice, class_2338 pos, class_2339 scratchPos, ModelQuadView quad, T state, int vertexIndex) {
      float x = quad.getX(vertexIndex) - 0.5F;
      float y = quad.getY(vertexIndex) - 0.5F;
      float z = quad.getZ(vertexIndex) - 0.5F;
      int intX = class_3532.method_15375(x);
      int intY = class_3532.method_15375(y);
      int intZ = class_3532.method_15375(z);
      float fracX = x - intX;
      float fracY = y - intY;
      float fracZ = z - intZ;
      int blockX = pos.method_10263() + intX;
      int blockY = pos.method_10264() + intY;
      int blockZ = pos.method_10260() + intZ;
      int m00 = this.getColor(slice, state, scratchPos.method_10103(blockX + 0, blockY, blockZ + 0));
      int m01 = this.getColor(slice, state, scratchPos.method_10103(blockX + 0, blockY, blockZ + 1));
      int m10 = this.getColor(slice, state, scratchPos.method_10103(blockX + 1, blockY, blockZ + 0));
      int m11 = this.getColor(slice, state, scratchPos.method_10103(blockX + 1, blockY, blockZ + 1));
      return ColorMixer.mix2d(m00, m01, m10, m11, fracX, fracZ);
   }

   protected abstract int getColor(LevelSlice var1, T var2, class_2338 var3);
}
