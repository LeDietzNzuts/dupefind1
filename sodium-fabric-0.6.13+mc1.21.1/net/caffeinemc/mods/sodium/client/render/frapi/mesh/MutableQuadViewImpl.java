package net.caffeinemc.mods.sodium.client.render.frapi.mesh;

import net.caffeinemc.mods.sodium.api.util.NormI8;
import net.caffeinemc.mods.sodium.client.model.quad.BakedQuadView;
import net.caffeinemc.mods.sodium.client.render.frapi.SodiumRenderer;
import net.caffeinemc.mods.sodium.client.render.frapi.helper.ColorHelper;
import net.caffeinemc.mods.sodium.client.render.frapi.helper.TextureHelper;
import net.caffeinemc.mods.sodium.client.render.frapi.material.RenderMaterialImpl;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadView;
import net.fabricmc.fabric.api.renderer.v1.model.SpriteFinder;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.class_1058;
import net.minecraft.class_2350;
import net.minecraft.class_777;
import org.jetbrains.annotations.Nullable;

public abstract class MutableQuadViewImpl extends QuadViewImpl implements QuadEmitter {
   @Nullable
   private class_1058 cachedSprite;

   @Nullable
   public class_1058 cachedSprite() {
      return this.cachedSprite;
   }

   public void cachedSprite(@Nullable class_1058 sprite) {
      this.cachedSprite = sprite;
   }

   public class_1058 sprite(SpriteFinder finder) {
      class_1058 sprite = this.cachedSprite;
      if (sprite == null) {
         this.cachedSprite = sprite = finder.find(this);
      }

      return sprite;
   }

   public void clear() {
      System.arraycopy(EncodingFormat.EMPTY, 0, this.data, this.baseIndex, EncodingFormat.TOTAL_STRIDE);
      this.isGeometryInvalid = true;
      this.nominalFace = null;
      this.normalFlags(0);
      this.tag(0);
      this.colorIndex(-1);
      this.cullFace(null);
      this.material(SodiumRenderer.STANDARD_MATERIAL);
      this.cachedSprite(null);
   }

   @Override
   public void load() {
      super.load();
      this.cachedSprite(null);
   }

   public MutableQuadViewImpl pos(int vertexIndex, float x, float y, float z) {
      int index = this.baseIndex + vertexIndex * EncodingFormat.VERTEX_STRIDE + EncodingFormat.VERTEX_X;
      this.data[index] = Float.floatToRawIntBits(x);
      this.data[index + 1] = Float.floatToRawIntBits(y);
      this.data[index + 2] = Float.floatToRawIntBits(z);
      this.isGeometryInvalid = true;
      return this;
   }

   public MutableQuadViewImpl color(int vertexIndex, int color) {
      this.data[this.baseIndex + vertexIndex * EncodingFormat.VERTEX_STRIDE + EncodingFormat.VERTEX_COLOR] = color;
      return this;
   }

   public MutableQuadViewImpl uv(int vertexIndex, float u, float v) {
      int i = this.baseIndex + vertexIndex * EncodingFormat.VERTEX_STRIDE + EncodingFormat.VERTEX_U;
      this.data[i] = Float.floatToRawIntBits(u);
      this.data[i + 1] = Float.floatToRawIntBits(v);
      this.cachedSprite(null);
      return this;
   }

   public MutableQuadViewImpl spriteBake(class_1058 sprite, int bakeFlags) {
      TextureHelper.bakeSprite(this, sprite, bakeFlags);
      this.cachedSprite(sprite);
      return this;
   }

   public MutableQuadViewImpl lightmap(int vertexIndex, int lightmap) {
      this.data[this.baseIndex + vertexIndex * EncodingFormat.VERTEX_STRIDE + EncodingFormat.VERTEX_LIGHTMAP] = lightmap;
      return this;
   }

   protected void normalFlags(int flags) {
      this.data[this.baseIndex + 0] = EncodingFormat.normalFlags(this.data[this.baseIndex + 0], flags);
   }

   public MutableQuadViewImpl normal(int vertexIndex, float x, float y, float z) {
      this.normalFlags(this.normalFlags() | 1 << vertexIndex);
      this.data[this.baseIndex + vertexIndex * EncodingFormat.VERTEX_STRIDE + EncodingFormat.VERTEX_NORMAL] = NormI8.pack(x, y, z);
      return this;
   }

   public final void populateMissingNormals() {
      int normalFlags = this.normalFlags();
      if (normalFlags != 15) {
         int packedFaceNormal = this.packedFaceNormal();

         for (int v = 0; v < 4; v++) {
            if ((normalFlags & 1 << v) == 0) {
               this.data[this.baseIndex + v * EncodingFormat.VERTEX_STRIDE + EncodingFormat.VERTEX_NORMAL] = packedFaceNormal;
            }
         }

         this.normalFlags(15);
      }
   }

   public final MutableQuadViewImpl cullFace(@Nullable class_2350 face) {
      this.data[this.baseIndex + 0] = EncodingFormat.cullFace(this.data[this.baseIndex + 0], face);
      this.nominalFace(face);
      return this;
   }

   public final MutableQuadViewImpl nominalFace(@Nullable class_2350 face) {
      this.nominalFace = face;
      return this;
   }

   public final MutableQuadViewImpl material(RenderMaterial material) {
      if (material == null) {
         material = SodiumRenderer.STANDARD_MATERIAL;
      }

      this.data[this.baseIndex + 0] = EncodingFormat.material(this.data[this.baseIndex + 0], (RenderMaterialImpl)material);
      return this;
   }

   public final MutableQuadViewImpl colorIndex(int colorIndex) {
      this.data[this.baseIndex + 2] = colorIndex;
      return this;
   }

   public final MutableQuadViewImpl tag(int tag) {
      this.data[this.baseIndex + 3] = tag;
      return this;
   }

   public MutableQuadViewImpl copyFrom(QuadView quad) {
      QuadViewImpl q = (QuadViewImpl)quad;
      q.computeGeometry();
      System.arraycopy(q.data, q.baseIndex, this.data, this.baseIndex, EncodingFormat.TOTAL_STRIDE);
      this.faceNormal.set(q.faceNormal);
      this.nominalFace = q.nominalFace;
      this.isGeometryInvalid = false;
      if (quad instanceof MutableQuadViewImpl mutableQuad) {
         this.cachedSprite(mutableQuad.cachedSprite());
      } else {
         this.cachedSprite(null);
      }

      return this;
   }

   private void fromVanillaInternal(int[] quadData, int startIndex) {
      System.arraycopy(quadData, startIndex, this.data, this.baseIndex + 4, QuadView.VANILLA_QUAD_STRIDE);
      int colorIndex = this.baseIndex + EncodingFormat.VERTEX_COLOR;

      for (int i = 0; i < 4; i++) {
         this.data[colorIndex] = ColorHelper.fromVanillaColor(this.data[colorIndex]);
         colorIndex += EncodingFormat.VERTEX_STRIDE;
      }
   }

   public final MutableQuadViewImpl fromVanilla(int[] quadData, int startIndex) {
      this.fromVanillaInternal(quadData, startIndex);
      this.isGeometryInvalid = true;
      this.cachedSprite(null);
      return this;
   }

   public final MutableQuadViewImpl fromVanilla(class_777 quad, RenderMaterial material, @Nullable class_2350 cullFace) {
      this.fromVanillaInternal(quad.method_3357(), 0);
      this.data[this.baseIndex + 0] = EncodingFormat.cullFace(0, cullFace);
      this.nominalFace(quad.method_3358());
      this.colorIndex(quad.method_3359());
      if (!((BakedQuadView)quad).hasShade()) {
         material = RenderMaterialImpl.setDisableDiffuse((RenderMaterialImpl)material, true);
      }

      if (material.ambientOcclusion().orElse(true) && !((BakedQuadView)quad).hasAO()) {
         material = RenderMaterialImpl.setAmbientOcclusion((RenderMaterialImpl)material, TriState.FALSE);
      }

      this.material(material);
      this.tag(0);
      BakedQuadView bakedView = (BakedQuadView)quad;
      NormI8.unpack(bakedView.getFaceNormal(), this.faceNormal);
      this.data[this.baseIndex + 1] = bakedView.getFaceNormal();
      int headerBits = EncodingFormat.lightFace(this.data[this.baseIndex + 0], bakedView.getLightFace());
      headerBits = EncodingFormat.normalFace(headerBits, bakedView.getNormalFace());
      this.data[this.baseIndex + 0] = EncodingFormat.geometryFlags(headerBits, bakedView.getFlags());
      this.isGeometryInvalid = false;
      this.cachedSprite(quad.method_35788());
      return this;
   }

   public abstract void emitDirectly();

   public final MutableQuadViewImpl emit() {
      this.emitDirectly();
      this.clear();
      return this;
   }
}
