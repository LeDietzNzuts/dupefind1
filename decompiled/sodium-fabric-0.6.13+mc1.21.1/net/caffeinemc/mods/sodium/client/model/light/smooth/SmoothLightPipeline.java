package net.caffeinemc.mods.sodium.client.model.light.smooth;

import net.caffeinemc.mods.sodium.api.util.NormI8;
import net.caffeinemc.mods.sodium.client.model.light.LightPipeline;
import net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess;
import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_3532;
import org.joml.Vector3f;

public class SmoothLightPipeline implements LightPipeline {
   private final LightDataAccess lightCache;
   private final AoFaceData[] cachedFaceData = new AoFaceData[12];
   private long cachedPos = Long.MIN_VALUE;
   private final float[] weights = new float[4];
   private final Vector3f vertexNormal = new Vector3f();
   private final AoFaceData tmpFace = new AoFaceData();

   public SmoothLightPipeline(LightDataAccess cache) {
      this.lightCache = cache;

      for (int i = 0; i < this.cachedFaceData.length; i++) {
         this.cachedFaceData[i] = new AoFaceData();
      }
   }

   @Override
   public void calculate(ModelQuadView quad, class_2338 pos, QuadLightData out, class_2350 cullFace, class_2350 lightFace, boolean shade, boolean enhanced) {
      this.updateCachedData(pos.method_10063());
      int flags = quad.getFlags();
      AoNeighborInfo neighborInfo = AoNeighborInfo.get(lightFace);
      if ((flags & 4) != 0 || (flags & 2) != 0 && LightDataAccess.unpackFC(this.lightCache.get(pos))) {
         if ((flags & 1) == 0) {
            this.applyAlignedFullFace(neighborInfo, pos, lightFace, out, shade);
         } else {
            this.applyAlignedPartialFace(neighborInfo, quad, pos, lightFace, out, shade);
         }
      } else if ((flags & 2) != 0) {
         this.applyParallelFace(neighborInfo, quad, pos, lightFace, out, shade);
      } else if (enhanced) {
         this.applyIrregularFace(pos, quad, out, shade);
      } else {
         this.applyNonParallelFace(neighborInfo, quad, pos, lightFace, out, shade);
      }
   }

   private void applyAlignedFullFace(AoNeighborInfo neighborInfo, class_2338 pos, class_2350 dir, QuadLightData out, boolean shade) {
      AoFaceData faceData = this.getCachedFaceData(pos, dir, true, shade);
      neighborInfo.mapCorners(faceData.lm, faceData.ao, out.lm, out.br);
      this.applyAmbientLighting(out.br, dir, shade);
   }

   private void applyAlignedPartialFace(AoNeighborInfo neighborInfo, ModelQuadView quad, class_2338 pos, class_2350 dir, QuadLightData out, boolean shade) {
      for (int i = 0; i < 4; i++) {
         float cx = clamp(quad.getX(i));
         float cy = clamp(quad.getY(i));
         float cz = clamp(quad.getZ(i));
         float[] weights = this.weights;
         neighborInfo.calculateCornerWeights(cx, cy, cz, weights);
         this.applyAlignedPartialFaceVertex(pos, dir, weights, i, out, true, shade);
      }

      this.applyAmbientLighting(out.br, dir, shade);
   }

   private void applyParallelFace(AoNeighborInfo neighborInfo, ModelQuadView quad, class_2338 pos, class_2350 dir, QuadLightData out, boolean shade) {
      for (int i = 0; i < 4; i++) {
         float cx = clamp(quad.getX(i));
         float cy = clamp(quad.getY(i));
         float cz = clamp(quad.getZ(i));
         float[] weights = this.weights;
         neighborInfo.calculateCornerWeights(cx, cy, cz, weights);
         float depth = neighborInfo.getDepth(cx, cy, cz);
         if (class_3532.method_15347(depth, 1.0F)) {
            this.applyAlignedPartialFaceVertex(pos, dir, weights, i, out, false, shade);
         } else {
            this.applyInsetPartialFaceVertex(pos, dir, depth, 1.0F - depth, weights, i, out, shade);
         }
      }

      this.applyAmbientLighting(out.br, dir, shade);
   }

   private void applyNonParallelFace(AoNeighborInfo neighborInfo, ModelQuadView quad, class_2338 pos, class_2350 dir, QuadLightData out, boolean shade) {
      for (int i = 0; i < 4; i++) {
         float cx = clamp(quad.getX(i));
         float cy = clamp(quad.getY(i));
         float cz = clamp(quad.getZ(i));
         float[] weights = this.weights;
         neighborInfo.calculateCornerWeights(cx, cy, cz, weights);
         float depth = neighborInfo.getDepth(cx, cy, cz);
         if (class_3532.method_15347(depth, 0.0F)) {
            this.applyAlignedPartialFaceVertex(pos, dir, weights, i, out, true, shade);
         } else if (class_3532.method_15347(depth, 1.0F)) {
            this.applyAlignedPartialFaceVertex(pos, dir, weights, i, out, false, shade);
         } else {
            this.applyInsetPartialFaceVertex(pos, dir, depth, 1.0F - depth, weights, i, out, shade);
         }
      }

      this.applyAmbientLighting(out.br, dir, shade);
   }

   private void applyAlignedPartialFaceVertex(class_2338 pos, class_2350 dir, float[] w, int i, QuadLightData out, boolean offset, boolean shade) {
      AoFaceData faceData = this.getCachedFaceData(pos, dir, offset, shade);
      if (!faceData.hasUnpackedLightData()) {
         faceData.unpackLightData();
      }

      float sl = faceData.getBlendedSkyLight(w);
      float bl = faceData.getBlendedBlockLight(w);
      float ao = faceData.getBlendedShade(w);
      out.br[i] = ao;
      out.lm[i] = getLightMapCoord(sl, bl);
   }

   private void applyInsetPartialFaceVertex(class_2338 pos, class_2350 dir, float n1d, float n2d, float[] w, int i, QuadLightData out, boolean shade) {
      AoFaceData n1 = this.getCachedFaceData(pos, dir, false, shade);
      if (!n1.hasUnpackedLightData()) {
         n1.unpackLightData();
      }

      AoFaceData n2 = this.getCachedFaceData(pos, dir, true, shade);
      if (!n2.hasUnpackedLightData()) {
         n2.unpackLightData();
      }

      float ao = n1.getBlendedShade(w) * n1d + n2.getBlendedShade(w) * n2d;
      float sl = n1.getBlendedSkyLight(w) * n1d + n2.getBlendedSkyLight(w) * n2d;
      float bl = n1.getBlendedBlockLight(w) * n1d + n2.getBlendedBlockLight(w) * n2d;
      out.br[i] = ao;
      out.lm[i] = getLightMapCoord(sl, bl);
   }

   private AoFaceData gatherInsetFace(ModelQuadView quad, class_2338 blockPos, int vertexIndex, class_2350 lightFace, boolean shade) {
      float w1 = AoNeighborInfo.get(lightFace).getDepth(quad.getX(vertexIndex), quad.getY(vertexIndex), quad.getZ(vertexIndex));
      if (class_3532.method_15347(w1, 0.0F)) {
         return this.getCachedFaceData(blockPos, lightFace, true, shade);
      } else if (class_3532.method_15347(w1, 1.0F)) {
         return this.getCachedFaceData(blockPos, lightFace, false, shade);
      } else {
         this.tmpFace.reset();
         float w0 = 1.0F - w1;
         return AoFaceData.weightedMean(
            this.getCachedFaceData(blockPos, lightFace, true, shade), w0, this.getCachedFaceData(blockPos, lightFace, false, shade), w1, this.tmpFace
         );
      }
   }

   private void applyIrregularFace(class_2338 blockPos, ModelQuadView quad, QuadLightData out, boolean shade) {
      float[] w = this.weights;
      float[] aoResult = out.br;
      int[] lightResult = out.lm;

      for (int i = 0; i < 4; i++) {
         Vector3f normal = NormI8.unpack(quad.getAccurateNormal(i), this.vertexNormal);
         float ao = 0.0F;
         float sky = 0.0F;
         float block = 0.0F;
         float maxAo = 0.0F;
         float maxSky = 0.0F;
         float maxBlock = 0.0F;
         float x = normal.x();
         if (!class_3532.method_15347(0.0F, x)) {
            class_2350 face = x > 0.0F ? class_2350.field_11034 : class_2350.field_11039;
            AoFaceData fd = this.gatherInsetFace(quad, blockPos, i, face, shade);
            AoNeighborInfo.get(face).calculateCornerWeights(quad.getX(i), quad.getY(i), quad.getZ(i), w);
            float n = x * x;
            float a = fd.getBlendedShade(w) * this.getAmbientBrightness(face, shade);
            float s = fd.getBlendedSkyLight(w);
            float b = fd.getBlendedBlockLight(w);
            ao += n * a;
            sky += n * s;
            block += n * b;
            maxAo = a;
            maxSky = s;
            maxBlock = b;
         }

         float y = normal.y();
         if (!class_3532.method_15347(0.0F, y)) {
            class_2350 face = y > 0.0F ? class_2350.field_11036 : class_2350.field_11033;
            AoFaceData fd = this.gatherInsetFace(quad, blockPos, i, face, shade);
            AoNeighborInfo.get(face).calculateCornerWeights(quad.getX(i), quad.getY(i), quad.getZ(i), w);
            float n = y * y;
            float a = fd.getBlendedShade(w) * this.getAmbientBrightness(face, shade);
            float s = fd.getBlendedSkyLight(w);
            float b = fd.getBlendedBlockLight(w);
            ao += n * a;
            sky += n * s;
            block += n * b;
            maxAo = Math.max(maxAo, a);
            maxSky = Math.max(maxSky, s);
            maxBlock = Math.max(maxBlock, b);
         }

         float z = normal.z();
         if (!class_3532.method_15347(0.0F, z)) {
            class_2350 face = z > 0.0F ? class_2350.field_11035 : class_2350.field_11043;
            AoFaceData fd = this.gatherInsetFace(quad, blockPos, i, face, shade);
            AoNeighborInfo.get(face).calculateCornerWeights(quad.getX(i), quad.getY(i), quad.getZ(i), w);
            float n = z * z;
            float a = fd.getBlendedShade(w) * this.getAmbientBrightness(face, shade);
            float s = fd.getBlendedSkyLight(w);
            float b = fd.getBlendedBlockLight(w);
            ao += n * a;
            sky += n * s;
            block += n * b;
            maxAo = Math.max(maxAo, a);
            maxSky = Math.max(maxSky, s);
            maxBlock = Math.max(maxBlock, b);
         }

         aoResult[i] = (ao + maxAo) * 0.5F;
         lightResult[i] = ((int)((sky + maxSky) * 0.5F) & 240) << 16 | (int)((block + maxBlock) * 0.5F) & 240;
      }
   }

   private void applyAmbientLighting(float[] brightness, class_2350 face, boolean shade) {
      float multiplier = this.getAmbientBrightness(face, shade);

      for (int i = 0; i < brightness.length; i++) {
         brightness[i] *= multiplier;
      }
   }

   private float getAmbientBrightness(class_2350 face, boolean shade) {
      return this.lightCache.getLevel().method_24852(face, shade);
   }

   private AoFaceData getCachedFaceData(class_2338 pos, class_2350 face, boolean offset, boolean shade) {
      AoFaceData data = this.cachedFaceData[offset ? face.ordinal() : face.ordinal() + 6];
      if (data.hasLightData()) {
         return data;
      } else {
         data.initLightData(this.lightCache, pos, face, offset);
         data.unpackLightData();
         return data;
      }
   }

   private void updateCachedData(long key) {
      if (this.cachedPos != key) {
         for (AoFaceData data : this.cachedFaceData) {
            data.reset();
         }

         this.cachedPos = key;
      }
   }

   private static float clamp(float v) {
      if (v < 0.0F) {
         return 0.0F;
      } else {
         return v > 1.0F ? 1.0F : v;
      }
   }

   private static int getLightMapCoord(float sl, float bl) {
      return ((int)sl & 0xFF) << 16 | (int)bl & 0xFF;
   }
}
