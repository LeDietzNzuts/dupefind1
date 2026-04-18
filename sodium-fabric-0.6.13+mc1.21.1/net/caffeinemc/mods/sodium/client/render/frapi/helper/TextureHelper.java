package net.caffeinemc.mods.sodium.client.render.frapi.helper;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.minecraft.class_1058;
import net.minecraft.class_2350;

public class TextureHelper {
   private static final float NORMALIZER = 0.0625F;
   private static final TextureHelper.VertexModifier[] ROTATIONS = new TextureHelper.VertexModifier[]{
      null, (q, i) -> q.uv(i, q.v(i), 1.0F - q.u(i)), (q, i) -> q.uv(i, 1.0F - q.u(i), 1.0F - q.v(i)), (q, i) -> q.uv(i, 1.0F - q.v(i), q.u(i))
   };
   private static final TextureHelper.VertexModifier[] UVLOCKERS = new TextureHelper.VertexModifier[6];

   private TextureHelper() {
   }

   public static void bakeSprite(MutableQuadView quad, class_1058 sprite, int bakeFlags) {
      if (quad.nominalFace() != null && (4 & bakeFlags) != 0) {
         applyModifier(quad, UVLOCKERS[quad.nominalFace().method_10146()]);
      } else if ((32 & bakeFlags) == 0) {
         applyModifier(quad, (q, i) -> q.uv(i, q.u(i) * 0.0625F, q.v(i) * 0.0625F));
      }

      int rotation = bakeFlags & 3;
      if (rotation != 0) {
         applyModifier(quad, ROTATIONS[rotation]);
      }

      if ((8 & bakeFlags) != 0) {
         applyModifier(quad, (q, i) -> q.uv(i, 1.0F - q.u(i), q.v(i)));
      }

      if ((16 & bakeFlags) != 0) {
         applyModifier(quad, (q, i) -> q.uv(i, q.u(i), 1.0F - q.v(i)));
      }

      interpolate(quad, sprite);
   }

   private static void interpolate(MutableQuadView q, class_1058 sprite) {
      float uMin = sprite.method_4594();
      float uSpan = sprite.method_4577() - uMin;
      float vMin = sprite.method_4593();
      float vSpan = sprite.method_4575() - vMin;

      for (int i = 0; i < 4; i++) {
         q.uv(i, uMin + q.u(i) * uSpan, vMin + q.v(i) * vSpan);
      }
   }

   private static void applyModifier(MutableQuadView quad, TextureHelper.VertexModifier modifier) {
      for (int i = 0; i < 4; i++) {
         modifier.apply(quad, i);
      }
   }

   static {
      UVLOCKERS[class_2350.field_11034.method_10146()] = (q, i) -> q.uv(i, 1.0F - q.z(i), 1.0F - q.y(i));
      UVLOCKERS[class_2350.field_11039.method_10146()] = (q, i) -> q.uv(i, q.z(i), 1.0F - q.y(i));
      UVLOCKERS[class_2350.field_11043.method_10146()] = (q, i) -> q.uv(i, 1.0F - q.x(i), 1.0F - q.y(i));
      UVLOCKERS[class_2350.field_11035.method_10146()] = (q, i) -> q.uv(i, q.x(i), 1.0F - q.y(i));
      UVLOCKERS[class_2350.field_11033.method_10146()] = (q, i) -> q.uv(i, q.x(i), 1.0F - q.z(i));
      UVLOCKERS[class_2350.field_11036.method_10146()] = (q, i) -> q.uv(i, q.x(i), q.z(i));
   }

   @FunctionalInterface
   private interface VertexModifier {
      void apply(MutableQuadView var1, int var2);
   }
}
