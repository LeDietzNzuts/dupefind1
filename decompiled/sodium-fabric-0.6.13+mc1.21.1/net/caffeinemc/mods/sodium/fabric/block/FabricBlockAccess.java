package net.caffeinemc.mods.sodium.fabric.block;

import net.caffeinemc.mods.sodium.api.util.NormI8;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.render.frapi.render.AmbientOcclusionMode;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.caffeinemc.mods.sodium.client.services.SodiumModelData;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_746;

public class FabricBlockAccess implements PlatformBlockAccess {
   private float normalShade(class_1920 blockView, float normalX, float normalY, float normalZ, boolean hasShade) {
      float sum = 0.0F;
      float div = 0.0F;
      if (normalX > 0.0F) {
         sum += normalX * blockView.method_24852(class_2350.field_11034, hasShade);
         div += normalX;
      } else if (normalX < 0.0F) {
         sum += -normalX * blockView.method_24852(class_2350.field_11039, hasShade);
         div -= normalX;
      }

      if (normalY > 0.0F) {
         sum += normalY * blockView.method_24852(class_2350.field_11036, hasShade);
         div += normalY;
      } else if (normalY < 0.0F) {
         sum += -normalY * blockView.method_24852(class_2350.field_11033, hasShade);
         div -= normalY;
      }

      if (normalZ > 0.0F) {
         sum += normalZ * blockView.method_24852(class_2350.field_11035, hasShade);
         div += normalZ;
      } else if (normalZ < 0.0F) {
         sum += -normalZ * blockView.method_24852(class_2350.field_11043, hasShade);
         div -= normalZ;
      }

      return sum / div;
   }

   @Override
   public int getLightEmission(class_2680 state, class_1920 level, class_2338 pos) {
      return state.method_26213();
   }

   @Override
   public boolean shouldSkipRender(class_1922 level, class_2680 selfState, class_2680 otherState, class_2338 selfPos, class_2338 otherPos, class_2350 facing) {
      return false;
   }

   @Override
   public boolean shouldShowFluidOverlay(class_2680 block, class_1920 level, class_2338 pos, class_3610 fluidState) {
      return FluidRenderHandlerRegistry.INSTANCE.isBlockTransparent(block.method_26204());
   }

   @Override
   public boolean platformHasBlockData() {
      return true;
   }

   @Override
   public float getNormalVectorShade(ModelQuadView quad, class_1920 level, boolean shade) {
      return this.normalShade(level, NormI8.unpackX(quad.getFaceNormal()), NormI8.unpackY(quad.getFaceNormal()), NormI8.unpackZ(quad.getFaceNormal()), shade);
   }

   @Override
   public AmbientOcclusionMode usesAmbientOcclusion(
      class_1087 model, class_2680 state, SodiumModelData data, class_1921 renderType, class_1920 level, class_2338 pos
   ) {
      return model.method_4708() ? AmbientOcclusionMode.DEFAULT : AmbientOcclusionMode.DISABLED;
   }

   @Override
   public boolean shouldBlockEntityGlow(class_2586 blockEntity, class_746 player) {
      return false;
   }

   @Override
   public boolean shouldOccludeFluid(class_2350 adjDirection, class_2680 adjBlockState, class_3610 fluid) {
      return adjBlockState.method_26227().method_15772().method_15780(fluid.method_15772());
   }
}
