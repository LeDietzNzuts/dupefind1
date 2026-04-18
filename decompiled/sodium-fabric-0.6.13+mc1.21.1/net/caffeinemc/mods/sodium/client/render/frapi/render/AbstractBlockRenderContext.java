package net.caffeinemc.mods.sodium.client.render.frapi.render;

import java.util.List;
import java.util.function.Supplier;
import net.caffeinemc.mods.sodium.client.model.light.LightMode;
import net.caffeinemc.mods.sodium.client.model.light.LightPipeline;
import net.caffeinemc.mods.sodium.client.model.light.LightPipelineProvider;
import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache;
import net.caffeinemc.mods.sodium.client.render.frapi.SodiumRenderer;
import net.caffeinemc.mods.sodium.client.render.frapi.helper.ColorHelper;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.EncodingFormat;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.MutableQuadViewImpl;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.caffeinemc.mods.sodium.client.services.PlatformModelAccess;
import net.caffeinemc.mods.sodium.client.services.SodiumModelData;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.material.ShadeMode;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext.BakedModelConsumer;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_5819;
import net.minecraft.class_777;
import net.minecraft.class_811;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractBlockRenderContext extends AbstractRenderContext {
   private static final RenderMaterial[] STANDARD_MATERIALS = new RenderMaterial[AmbientOcclusionMode.values().length];
   private static final RenderMaterial TRANSLUCENT_MATERIAL = SodiumRenderer.INSTANCE.materialFinder().blendMode(BlendMode.TRANSLUCENT).find();
   private final MutableQuadViewImpl editorQuad = new MutableQuadViewImpl() {
      {
         this.data = new int[EncodingFormat.TOTAL_STRIDE];
         this.clear();
      }

      @Override
      public void emitDirectly() {
         if (AbstractBlockRenderContext.this.type == null) {
            throw new IllegalStateException("No render type is set but an FRAPI object was asked to render!");
         } else {
            AbstractBlockRenderContext.this.renderQuad(this);
         }
      }
   };
   @Deprecated
   private final AbstractBlockRenderContext.BakedModelConsumerImpl vanillaModelConsumer = new AbstractBlockRenderContext.BakedModelConsumerImpl();
   protected class_1920 level;
   protected LevelSlice slice;
   protected class_2680 state;
   protected class_2338 pos;
   protected class_1921 type;
   protected SodiumModelData modelData;
   private final BlockOcclusionCache occlusionCache = new BlockOcclusionCache();
   private boolean enableCulling = true;
   private int cullCompletionFlags;
   private int cullResultFlags;
   protected class_5819 random;
   protected long randomSeed;
   protected final Supplier<class_5819> randomSupplier = () -> {
      this.random.method_43052(this.randomSeed);
      return this.random;
   };
   protected LightPipelineProvider lighters;
   protected final QuadLightData quadLightData = new QuadLightData();
   protected boolean useAmbientOcclusion;
   protected LightMode defaultLightMode;

   public QuadEmitter getEmitter() {
      this.editorQuad.clear();
      return this.editorQuad;
   }

   public boolean isFaceCulled(@Nullable class_2350 face) {
      if (face != null && this.enableCulling) {
         int mask = 1 << face.method_10146();
         if ((this.cullCompletionFlags & mask) == 0) {
            this.cullCompletionFlags |= mask;
            if (this.occlusionCache.shouldDrawSide(this.state, this.level, this.pos, face)) {
               this.cullResultFlags |= mask;
               return false;
            } else {
               return true;
            }
         } else {
            return (this.cullResultFlags & mask) == 0;
         }
      } else {
         return false;
      }
   }

   public class_811 itemTransformationMode() {
      throw new UnsupportedOperationException("itemTransformationMode can only be called on an item render context.");
   }

   @Deprecated
   public BakedModelConsumer bakedModelConsumer() {
      return this.vanillaModelConsumer;
   }

   private void renderQuad(MutableQuadViewImpl quad) {
      if (this.transform(quad)) {
         if (!this.isFaceCulled(quad.cullFace())) {
            this.processQuad(quad);
         }
      }
   }

   protected abstract void processQuad(MutableQuadViewImpl var1);

   protected void prepareCulling(boolean enableCulling) {
      this.enableCulling = enableCulling;
      this.cullCompletionFlags = 0;
      this.cullResultFlags = 0;
   }

   protected void prepareAoInfo(boolean modelAo) {
      this.useAmbientOcclusion = class_310.method_1588();
      this.defaultLightMode = this.useAmbientOcclusion && modelAo && PlatformBlockAccess.getInstance().getLightEmission(this.state, this.level, this.pos) == 0
         ? LightMode.SMOOTH
         : LightMode.FLAT;
   }

   protected void shadeQuad(MutableQuadViewImpl quad, LightMode lightMode, boolean emissive, ShadeMode shadeMode) {
      LightPipeline lighter = this.lighters.getLighter(lightMode);
      QuadLightData data = this.quadLightData;
      lighter.calculate(quad, this.pos, data, quad.cullFace(), quad.lightFace(), quad.hasShade(), shadeMode == ShadeMode.ENHANCED);
      if (emissive) {
         for (int i = 0; i < 4; i++) {
            quad.lightmap(i, 15728880);
         }
      } else {
         int[] lightmaps = data.lm;

         for (int i = 0; i < 4; i++) {
            quad.lightmap(i, ColorHelper.maxBrightness(quad.lightmap(i), lightmaps[i]));
         }
      }
   }

   public void bufferDefaultModel(class_1087 model, @Nullable class_2680 state) {
      MutableQuadViewImpl editorQuad = this.editorQuad;
      boolean noTransform = !this.hasTransform();

      for (int i = 0; i <= 6; i++) {
         class_2350 cullFace = ModelHelper.faceFromIndex(i);
         class_5819 random = this.randomSupplier.get();
         AmbientOcclusionMode ao = PlatformBlockAccess.getInstance().usesAmbientOcclusion(model, state, this.modelData, this.type, this.slice, this.pos);
         if (noTransform) {
            if (!this.isFaceCulled(cullFace)) {
               List<class_777> quads = PlatformModelAccess.getInstance()
                  .getQuads(this.level, this.pos, model, state, cullFace, random, this.type, this.modelData);
               int count = quads.size();

               for (int j = 0; j < count; j++) {
                  class_777 q = quads.get(j);
                  editorQuad.fromVanilla(
                     q,
                     this.type != class_1921.method_29997() && this.type != class_1921.method_23583() ? STANDARD_MATERIALS[ao.ordinal()] : TRANSLUCENT_MATERIAL,
                     cullFace
                  );
                  this.processQuad(editorQuad);
               }
            }
         } else {
            List<class_777> quads = PlatformModelAccess.getInstance().getQuads(this.level, this.pos, model, state, cullFace, random, this.type, this.modelData);
            int count = quads.size();

            for (int j = 0; j < count; j++) {
               class_777 q = quads.get(j);
               editorQuad.fromVanilla(
                  q,
                  this.type != class_1921.method_29997() && this.type != class_1921.method_23583() ? STANDARD_MATERIALS[ao.ordinal()] : TRANSLUCENT_MATERIAL,
                  cullFace
               );
               this.renderQuad(editorQuad);
            }
         }
      }

      editorQuad.clear();
   }

   static {
      AmbientOcclusionMode[] values = AmbientOcclusionMode.values();

      for (int i = 0; i < values.length; i++) {
         TriState state = switch (values[i]) {
            case ENABLED -> TriState.TRUE;
            case DISABLED -> TriState.FALSE;
            case DEFAULT -> TriState.DEFAULT;
         };
         STANDARD_MATERIALS[i] = SodiumRenderer.INSTANCE.materialFinder().ambientOcclusion(state).find();
      }
   }

   @Deprecated
   private class BakedModelConsumerImpl implements BakedModelConsumer {
      public void accept(class_1087 model) {
         this.accept(model, AbstractBlockRenderContext.this.state);
      }

      public void accept(class_1087 model, @Nullable class_2680 state) {
         AbstractBlockRenderContext.this.bufferDefaultModel(model, state);
      }
   }
}
