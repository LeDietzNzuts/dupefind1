package net.caffeinemc.mods.sodium.client.render.frapi.render;

import java.util.function.Supplier;
import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.caffeinemc.mods.sodium.api.util.ColorMixer;
import net.caffeinemc.mods.sodium.client.render.frapi.helper.ColorHelper;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.EncodingFormat;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.MutableQuadViewImpl;
import net.caffeinemc.mods.sodium.client.render.texture.SpriteFinderCache;
import net.caffeinemc.mods.sodium.mixin.features.render.frapi.ItemRendererAccessor;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext.BakedModelConsumer;
import net.fabricmc.fabric.api.util.TriState;
import net.fabricmc.fabric.impl.renderer.VanillaModelEncoder;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_325;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4696;
import net.minecraft.class_4722;
import net.minecraft.class_5819;
import net.minecraft.class_6575;
import net.minecraft.class_7837;
import net.minecraft.class_811;
import net.minecraft.class_918;
import net.minecraft.class_4587.class_4665;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class ItemRenderContext extends AbstractRenderContext {
   private static final long ITEM_RANDOM_SEED = 42L;
   private final MutableQuadViewImpl editorQuad = new MutableQuadViewImpl() {
      {
         this.data = new int[EncodingFormat.TOTAL_STRIDE];
         this.clear();
      }

      @Override
      public void emitDirectly() {
         ItemRenderContext.this.renderQuad(this);
      }
   };
   @Deprecated
   private final ItemRenderContext.BakedModelConsumerImpl vanillaModelConsumer = new ItemRenderContext.BakedModelConsumerImpl();
   private final class_325 colorMap;
   private final ItemRenderContext.VanillaModelBufferer vanillaBufferer;
   private final class_5819 random = new class_6575(42L);
   private final Supplier<class_5819> randomSupplier = () -> {
      this.random.method_43052(42L);
      return this.random;
   };
   private class_1799 itemStack;
   private class_811 transformMode;
   private class_4587 poseStack;
   private Matrix4f matPosition;
   private boolean trustedNormals;
   private Matrix3f matNormal;
   private class_4597 bufferSource;
   private int lightmap;
   private int overlay;
   private boolean isDefaultTranslucent;
   private boolean isTranslucentDirect;
   private boolean isDefaultGlint;
   private boolean isGlintDynamicDisplay;
   private class_4665 dynamicDisplayGlintEntry;
   private class_4588 translucentVertexConsumer;
   private class_4588 cutoutVertexConsumer;
   private class_4588 translucentGlintVertexConsumer;
   private class_4588 cutoutGlintVertexConsumer;
   private class_4588 defaultVertexConsumer;

   public ItemRenderContext(class_325 colorMap, ItemRenderContext.VanillaModelBufferer vanillaBufferer) {
      this.colorMap = colorMap;
      this.vanillaBufferer = vanillaBufferer;
   }

   public QuadEmitter getEmitter() {
      this.editorQuad.clear();
      return this.editorQuad;
   }

   public boolean isFaceCulled(@Nullable class_2350 face) {
      throw new UnsupportedOperationException("isFaceCulled can only be called on a block render context.");
   }

   public class_811 itemTransformationMode() {
      return this.transformMode;
   }

   @Deprecated
   public BakedModelConsumer bakedModelConsumer() {
      return this.vanillaModelConsumer;
   }

   public void renderModel(
      class_1799 itemStack, class_811 transformMode, boolean invert, class_4587 poseStack, class_4597 bufferSource, int lightmap, int overlay, class_1087 model
   ) {
      this.itemStack = itemStack;
      this.transformMode = transformMode;
      this.poseStack = poseStack;
      this.matPosition = poseStack.method_23760().method_23761();
      this.trustedNormals = poseStack.method_23760().field_48930;
      this.matNormal = poseStack.method_23760().method_23762();
      this.bufferSource = bufferSource;
      this.lightmap = lightmap;
      this.overlay = overlay;
      this.computeOutputInfo();
      ((FabricBakedModel)model).emitItemQuads(itemStack, this.randomSupplier, this);
      this.itemStack = null;
      this.poseStack = null;
      this.bufferSource = null;
      this.dynamicDisplayGlintEntry = null;
      this.translucentVertexConsumer = null;
      this.cutoutVertexConsumer = null;
      this.translucentGlintVertexConsumer = null;
      this.cutoutGlintVertexConsumer = null;
      this.defaultVertexConsumer = null;
   }

   private void computeOutputInfo() {
      this.isDefaultTranslucent = true;
      this.isTranslucentDirect = true;
      if (this.itemStack.method_7909() instanceof class_1747 blockItem) {
         class_2680 state = blockItem.method_7711().method_9564();
         class_1921 renderType = class_4696.method_23679(state);
         if (renderType != class_1921.method_23583()) {
            this.isDefaultTranslucent = false;
         }

         if (this.transformMode != class_811.field_4317 && !this.transformMode.method_29998()) {
            this.isTranslucentDirect = false;
         }
      }

      this.isDefaultGlint = this.itemStack.method_7958();
      this.isGlintDynamicDisplay = ItemRendererAccessor.sodium$hasAnimatedTexture(this.itemStack);
      this.defaultVertexConsumer = this.getVertexConsumer(BlendMode.DEFAULT, TriState.DEFAULT);
   }

   private void renderQuad(MutableQuadViewImpl quad) {
      if (this.transform(quad)) {
         RenderMaterial mat = quad.material();
         int colorIndex = mat.disableColorIndex() ? -1 : quad.colorIndex();
         boolean emissive = mat.emissive();
         class_4588 vertexConsumer = this.getVertexConsumer(mat.blendMode(), mat.glint());
         this.colorizeQuad(quad, colorIndex);
         this.shadeQuad(quad, emissive);
         this.bufferQuad(quad, vertexConsumer);
      }
   }

   private void colorizeQuad(MutableQuadViewImpl quad, int colorIndex) {
      if (colorIndex != -1) {
         int itemColor = this.colorMap.method_1704(this.itemStack, colorIndex);

         for (int i = 0; i < 4; i++) {
            quad.color(i, ColorMixer.mulComponentWise(itemColor, quad.color(i)));
         }
      }
   }

   private void shadeQuad(MutableQuadViewImpl quad, boolean emissive) {
      if (emissive) {
         for (int i = 0; i < 4; i++) {
            quad.lightmap(i, 15728880);
         }
      } else {
         int lightmap = this.lightmap;

         for (int i = 0; i < 4; i++) {
            quad.lightmap(i, ColorHelper.maxBrightness(quad.lightmap(i), lightmap));
         }
      }
   }

   private void bufferQuad(MutableQuadViewImpl quad, class_4588 vertexConsumer) {
      QuadEncoder.writeQuadVertices(quad, vertexConsumer, this.overlay, this.matPosition, this.trustedNormals, this.matNormal);
      class_1058 sprite = quad.sprite(SpriteFinderCache.forBlockAtlas());
      if (sprite != null) {
         SpriteUtil.INSTANCE.markSpriteActive(sprite);
      }
   }

   private class_4588 getVertexConsumer(BlendMode blendMode, TriState glintMode) {
      boolean translucent;
      if (blendMode == BlendMode.DEFAULT) {
         translucent = this.isDefaultTranslucent;
      } else {
         translucent = blendMode == BlendMode.TRANSLUCENT;
      }

      boolean glint;
      if (glintMode == TriState.DEFAULT) {
         glint = this.isDefaultGlint;
      } else {
         glint = glintMode == TriState.TRUE;
      }

      if (translucent) {
         if (glint) {
            if (this.translucentGlintVertexConsumer == null) {
               this.translucentGlintVertexConsumer = this.createTranslucentVertexConsumer(true);
            }

            return this.translucentGlintVertexConsumer;
         } else {
            if (this.translucentVertexConsumer == null) {
               this.translucentVertexConsumer = this.createTranslucentVertexConsumer(false);
            }

            return this.translucentVertexConsumer;
         }
      } else if (glint) {
         if (this.cutoutGlintVertexConsumer == null) {
            this.cutoutGlintVertexConsumer = this.createCutoutVertexConsumer(true);
         }

         return this.cutoutGlintVertexConsumer;
      } else {
         if (this.cutoutVertexConsumer == null) {
            this.cutoutVertexConsumer = this.createCutoutVertexConsumer(false);
         }

         return this.cutoutVertexConsumer;
      }
   }

   private class_4588 createTranslucentVertexConsumer(boolean glint) {
      if (glint && this.isGlintDynamicDisplay) {
         return this.createDynamicDisplayGlintVertexConsumer(
            class_310.method_29611() && !this.isTranslucentDirect ? class_4722.method_29382() : class_4722.method_24076()
         );
      } else if (this.isTranslucentDirect) {
         return class_918.method_29711(this.bufferSource, class_4722.method_24076(), true, glint);
      } else {
         return class_310.method_29611()
            ? class_918.method_23181(this.bufferSource, class_4722.method_29382(), true, glint)
            : class_918.method_23181(this.bufferSource, class_4722.method_29382(), true, glint);
      }
   }

   private class_4588 createCutoutVertexConsumer(boolean glint) {
      return glint && this.isGlintDynamicDisplay
         ? this.createDynamicDisplayGlintVertexConsumer(class_4722.method_24074())
         : class_918.method_29711(this.bufferSource, class_4722.method_24074(), true, glint);
   }

   private class_4588 createDynamicDisplayGlintVertexConsumer(class_1921 type) {
      if (this.dynamicDisplayGlintEntry == null) {
         this.dynamicDisplayGlintEntry = this.poseStack.method_23760().method_56822();
         if (this.transformMode == class_811.field_4317) {
            class_7837.method_46414(this.dynamicDisplayGlintEntry.method_23761(), 0.5F);
         } else if (this.transformMode.method_29998()) {
            class_7837.method_46414(this.dynamicDisplayGlintEntry.method_23761(), 0.75F);
         }
      }

      return class_918.method_30114(this.bufferSource, type, this.dynamicDisplayGlintEntry);
   }

   public void bufferDefaultModel(class_1087 model, @Nullable class_2680 state) {
      if (!this.hasTransform() && this.vanillaBufferer != null) {
         this.vanillaBufferer.accept(model, this.itemStack, this.lightmap, this.overlay, this.poseStack, this.defaultVertexConsumer);
      } else {
         VanillaModelEncoder.emitItemQuads(model, state, this.randomSupplier, this);
      }
   }

   @Deprecated
   private class BakedModelConsumerImpl implements BakedModelConsumer {
      public void accept(class_1087 model) {
         this.accept(model, null);
      }

      public void accept(class_1087 model, @Nullable class_2680 state) {
         ItemRenderContext.this.bufferDefaultModel(model, state);
      }
   }

   @FunctionalInterface
   public interface VanillaModelBufferer {
      void accept(class_1087 var1, class_1799 var2, int var3, int var4, class_4587 var5, class_4588 var6);
   }
}
