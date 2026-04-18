package net.caffeinemc.mods.sodium.client.render.frapi.render;

import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.util.ColorMixer;
import net.caffeinemc.mods.sodium.client.model.light.LightMode;
import net.caffeinemc.mods.sodium.client.model.light.LightPipelineProvider;
import net.caffeinemc.mods.sodium.client.model.light.data.SingleBlockLightDataCache;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.MutableQuadViewImpl;
import net.caffeinemc.mods.sodium.client.render.texture.SpriteFinderCache;
import net.caffeinemc.mods.sodium.client.services.SodiumModelData;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.material.ShadeMode;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_324;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4696;
import net.minecraft.class_5819;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class NonTerrainBlockRenderContext extends AbstractBlockRenderContext {
   private final class_324 colorMap;
   private final SingleBlockLightDataCache lightDataCache = new SingleBlockLightDataCache();
   private class_4588 vertexConsumer;
   private Matrix4f matPosition;
   private boolean trustedNormals;
   private Matrix3f matNormal;
   private int overlay;

   public NonTerrainBlockRenderContext(class_324 colorMap) {
      this.colorMap = colorMap;
      this.lighters = new LightPipelineProvider(this.lightDataCache);
   }

   public void renderModel(
      class_1920 blockView,
      class_1087 model,
      class_2680 state,
      class_2338 pos,
      class_4587 poseStack,
      class_4588 buffer,
      boolean cull,
      class_5819 random,
      long seed,
      int overlay
   ) {
      this.level = blockView;
      this.state = state;
      this.pos = pos;
      this.random = random;
      this.randomSeed = seed;
      this.vertexConsumer = buffer;
      this.matPosition = poseStack.method_23760().method_23761();
      this.trustedNormals = poseStack.method_23760().field_48930;
      this.matNormal = poseStack.method_23760().method_23762();
      this.overlay = overlay;
      this.type = class_4696.method_23679(state);
      this.modelData = SodiumModelData.EMPTY;
      this.lightDataCache.reset(pos, blockView);
      this.prepareCulling(cull);
      this.prepareAoInfo(model.method_4708());
      ((FabricBakedModel)model).emitBlockQuads(blockView, state, pos, this.randomSupplier, this);
      this.level = null;
      this.type = null;
      this.modelData = null;
      this.lightDataCache.release();
      this.random = null;
      this.vertexConsumer = null;
   }

   @Override
   protected void processQuad(MutableQuadViewImpl quad) {
      RenderMaterial mat = quad.material();
      int colorIndex = mat.disableColorIndex() ? -1 : quad.colorIndex();
      TriState aoMode = mat.ambientOcclusion();
      ShadeMode shadeMode = mat.shadeMode();
      LightMode lightMode;
      if (aoMode == TriState.DEFAULT) {
         lightMode = this.defaultLightMode;
      } else {
         lightMode = this.useAmbientOcclusion && aoMode.get() ? LightMode.SMOOTH : LightMode.FLAT;
      }

      boolean emissive = mat.emissive();
      this.colorizeQuad(quad, colorIndex);
      this.shadeQuad(quad, lightMode, emissive, shadeMode);
      this.bufferQuad(quad);
   }

   private void colorizeQuad(MutableQuadViewImpl quad, int colorIndex) {
      if (colorIndex != -1) {
         int blockColor = 0xFF000000 | this.colorMap.method_1697(this.state, this.level, this.pos, colorIndex);

         for (int i = 0; i < 4; i++) {
            quad.color(i, ColorMixer.mulComponentWise(blockColor, quad.color(i)));
         }
      }
   }

   @Override
   protected void shadeQuad(MutableQuadViewImpl quad, LightMode lightMode, boolean emissive, ShadeMode shadeMode) {
      super.shadeQuad(quad, lightMode, emissive, shadeMode);
      float[] brightnesses = this.quadLightData.br;

      for (int i = 0; i < 4; i++) {
         quad.color(i, ColorARGB.mulRGB(quad.color(i), brightnesses[i]));
      }
   }

   private void bufferQuad(MutableQuadViewImpl quad) {
      QuadEncoder.writeQuadVertices(quad, this.vertexConsumer, this.overlay, this.matPosition, this.trustedNormals, this.matNormal);
      class_1058 sprite = quad.sprite(SpriteFinderCache.forBlockAtlas());
      if (sprite != null) {
         SpriteUtil.INSTANCE.markSpriteActive(sprite);
      }
   }
}
