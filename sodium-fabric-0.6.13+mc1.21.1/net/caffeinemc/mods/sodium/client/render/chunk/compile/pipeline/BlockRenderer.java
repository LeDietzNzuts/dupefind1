package net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline;

import java.util.Iterator;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.util.ColorMixer;
import net.caffeinemc.mods.sodium.client.compatibility.workarounds.Workarounds;
import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.color.ColorProviderRegistry;
import net.caffeinemc.mods.sodium.client.model.light.LightMode;
import net.caffeinemc.mods.sodium.client.model.light.LightPipelineProvider;
import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFacing;
import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadOrientation;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkBuildBuffers;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.buffers.ChunkModelBuilder;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.DefaultTerrainRenderPasses;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.DefaultMaterials;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.Material;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.parameters.AlphaCutoffParameter;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.parameters.MaterialParameters;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TranslucentGeometryCollector;
import net.caffeinemc.mods.sodium.client.render.chunk.vertex.builder.ChunkMeshBufferBuilder;
import net.caffeinemc.mods.sodium.client.render.chunk.vertex.format.ChunkVertexEncoder;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.MutableQuadViewImpl;
import net.caffeinemc.mods.sodium.client.render.frapi.render.AbstractBlockRenderContext;
import net.caffeinemc.mods.sodium.client.render.texture.SpriteFinderCache;
import net.caffeinemc.mods.sodium.client.services.PlatformModelAccess;
import net.caffeinemc.mods.sodium.client.services.SodiumModelData;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.material.ShadeMode;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_4696;
import net.minecraft.class_6575;
import net.minecraft.class_2338.class_2339;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class BlockRenderer extends AbstractBlockRenderContext {
   private final ColorProviderRegistry colorProviderRegistry;
   private final int[] vertexColors = new int[4];
   private final ChunkVertexEncoder.Vertex[] vertices = ChunkVertexEncoder.Vertex.uninitializedQuad();
   private ChunkBuildBuffers buffers;
   private final Vector3f posOffset = new Vector3f();
   private final class_2339 scratchPos = new class_2339();
   @Nullable
   private ColorProvider<class_2680> colorProvider;
   private TranslucentGeometryCollector collector;
   private boolean allowDowngrade;

   public BlockRenderer(ColorProviderRegistry colorRegistry, LightPipelineProvider lighters) {
      this.colorProviderRegistry = colorRegistry;
      this.lighters = lighters;
      this.random = new class_6575(42L);
   }

   public void prepare(ChunkBuildBuffers buffers, LevelSlice level, TranslucentGeometryCollector collector) {
      this.buffers = buffers;
      this.level = level;
      this.collector = collector;
      this.slice = level;
   }

   public void release() {
      this.buffers = null;
      this.level = null;
      this.collector = null;
      this.slice = null;
   }

   public void renderModel(class_1087 model, class_2680 state, class_2338 pos, class_2338 origin) {
      this.state = state;
      this.pos = pos;
      this.randomSeed = state.method_26190(pos);
      this.posOffset.set(origin.method_10263(), origin.method_10264(), origin.method_10260());
      if (state.method_49228()) {
         class_243 modelOffset = state.method_26226(this.level, pos);
         this.posOffset.add((float)modelOffset.field_1352, (float)modelOffset.field_1351, (float)modelOffset.field_1350);
      }

      this.colorProvider = this.colorProviderRegistry.getColorProvider(state.method_26204());
      this.type = class_4696.method_23679(state);
      this.prepareCulling(true);
      this.prepareAoInfo(model.method_4708());
      this.modelData = PlatformModelAccess.getInstance().getModelData(this.slice, model, state, pos, this.slice.getPlatformModelData(pos));
      Iterable<class_1921> renderTypes = PlatformModelAccess.getInstance().getModelRenderTypes(this.level, model, state, pos, this.random, this.modelData);
      this.allowDowngrade = true;
      Iterator<class_1921> it = renderTypes.iterator();

      for (class_1921 defaultType = class_4696.method_23679(state);
         it.hasNext();
         ((FabricBakedModel)model).emitBlockQuads(this.level, state, pos, this.randomSupplier, this)
      ) {
         this.type = it.next();
         if (it.hasNext() || this.type != defaultType) {
            this.allowDowngrade = false;
         }
      }

      this.type = null;
      this.modelData = SodiumModelData.EMPTY;
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
      BlendMode blendMode = mat.blendMode();
      Material material;
      if (blendMode == BlendMode.DEFAULT) {
         material = DefaultMaterials.forRenderLayer(this.type);
      } else {
         material = DefaultMaterials.forRenderLayer(blendMode.blockRenderLayer == null ? this.type : blendMode.blockRenderLayer);
      }

      this.colorizeQuad(quad, colorIndex);
      this.shadeQuad(quad, lightMode, emissive, shadeMode);
      this.bufferQuad(quad, this.quadLightData.br, material);
   }

   private void colorizeQuad(MutableQuadViewImpl quad, int colorIndex) {
      if (colorIndex != -1) {
         ColorProvider<class_2680> colorProvider = this.colorProvider;
         if (colorProvider != null) {
            int[] vertexColors = this.vertexColors;
            colorProvider.getColors(this.slice, this.pos, this.scratchPos, this.state, quad, vertexColors);

            for (int i = 0; i < 4; i++) {
               quad.color(i, ColorMixer.mulComponentWise(vertexColors[i], quad.color(i)));
            }
         }
      }
   }

   private void bufferQuad(MutableQuadViewImpl quad, float[] brightnesses, Material material) {
      ModelQuadOrientation orientation = ModelQuadOrientation.NORMAL;
      ChunkVertexEncoder.Vertex[] vertices = this.vertices;
      Vector3f offset = this.posOffset;

      for (int dstIndex = 0; dstIndex < 4; dstIndex++) {
         int srcIndex = orientation.getVertexIndex(dstIndex);
         ChunkVertexEncoder.Vertex out = vertices[dstIndex];
         out.x = quad.x(srcIndex) + offset.x;
         out.y = quad.y(srcIndex) + offset.y;
         out.z = quad.z(srcIndex) + offset.z;
         out.color = ColorARGB.toABGR(quad.color(srcIndex));
         out.ao = brightnesses[srcIndex];
         out.u = quad.u(srcIndex);
         out.v = quad.v(srcIndex);
         out.light = quad.lightmap(srcIndex);
      }

      class_1058 atlasSprite = quad.sprite(SpriteFinderCache.forBlockAtlas());
      int materialBits = material.bits();
      ModelQuadFacing normalFace = quad.normalFace();
      TerrainRenderPass pass = material.pass;
      TerrainRenderPass downgradedPass = this.attemptPassDowngrade(atlasSprite, pass);
      if (downgradedPass != null) {
         pass = downgradedPass;
      }

      if (pass.isTranslucent() && this.collector != null) {
         this.collector.appendQuad(quad.getFaceNormal(), vertices, normalFace);
      }

      if (downgradedPass != null && material == DefaultMaterials.TRANSLUCENT && pass == DefaultTerrainRenderPasses.CUTOUT) {
         materialBits = MaterialParameters.pack(AlphaCutoffParameter.ONE_TENTH, material.mipped);
      }

      ChunkModelBuilder builder = this.buffers.get(pass);
      ChunkMeshBufferBuilder vertexBuffer = builder.getVertexBuffer(normalFace);
      vertexBuffer.push(vertices, materialBits);
      if (atlasSprite != null) {
         builder.addSprite(atlasSprite);
      }
   }

   private boolean validateQuadUVs(class_1058 atlasSprite) {
      float spriteUMin = atlasSprite.method_4594();
      float spriteUMax = atlasSprite.method_4577();
      float spriteVMin = atlasSprite.method_4593();
      float spriteVMax = atlasSprite.method_4575();

      for (int i = 0; i < 4; i++) {
         float u = this.vertices[i].u;
         float v = this.vertices[i].v;
         if (u < spriteUMin || u > spriteUMax || v < spriteVMin || v > spriteVMax) {
            return false;
         }
      }

      return true;
   }

   @Nullable
   private TerrainRenderPass attemptPassDowngrade(class_1058 sprite, TerrainRenderPass pass) {
      if (this.allowDowngrade && !Workarounds.isWorkaroundEnabled(Workarounds.Reference.INTEL_DEPTH_BUFFER_COMPARISON_UNRELIABLE)) {
         boolean attemptDowngrade = true;
         boolean hasNonOpaqueVertex = false;

         for (int i = 0; i < 4; i++) {
            hasNonOpaqueVertex |= ColorABGR.unpackAlpha(this.vertices[i].color) != 255;
         }

         if (pass.isTranslucent() && hasNonOpaqueVertex) {
            attemptDowngrade = false;
         }

         if (attemptDowngrade) {
            attemptDowngrade = this.validateQuadUVs(sprite);
         }

         return attemptDowngrade ? getDowngradedPass(sprite, pass) : null;
      } else {
         return null;
      }
   }

   private static TerrainRenderPass getDowngradedPass(class_1058 sprite, TerrainRenderPass pass) {
      if (sprite instanceof TextureAtlasSpriteExtension spriteExt) {
         if (spriteExt.sodium$hasUnknownImageContents()) {
            return pass;
         }

         if (sprite.method_45851() instanceof SpriteContentsExtension contentsExt) {
            if (pass == DefaultTerrainRenderPasses.TRANSLUCENT && !contentsExt.sodium$hasTranslucentPixels()) {
               pass = DefaultTerrainRenderPasses.CUTOUT;
            }

            if (pass == DefaultTerrainRenderPasses.CUTOUT && !contentsExt.sodium$hasTransparentPixels()) {
               pass = DefaultTerrainRenderPasses.SOLID;
            }
         }
      }

      return pass;
   }
}
