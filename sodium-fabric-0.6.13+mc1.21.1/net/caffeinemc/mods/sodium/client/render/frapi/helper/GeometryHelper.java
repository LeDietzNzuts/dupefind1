package net.caffeinemc.mods.sodium.client.render.frapi.helper;

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadView;
import net.minecraft.class_2350;
import net.minecraft.class_3532;
import net.minecraft.class_2350.class_2351;
import org.joml.Vector3f;

public abstract class GeometryHelper {
   private GeometryHelper() {
   }

   public static boolean isQuadParallelToFace(class_2350 face, QuadView quad) {
      int i = face.method_10166().ordinal();
      float val = quad.posByIndex(0, i);
      return class_3532.method_15347(val, quad.posByIndex(1, i))
         && class_3532.method_15347(val, quad.posByIndex(2, i))
         && class_3532.method_15347(val, quad.posByIndex(3, i));
   }

   public static class_2350 lightFace(QuadView quad) {
      Vector3f normal = quad.faceNormal();

      return switch (longestAxis(normal)) {
         case field_11048 -> normal.x() > 0.0F ? class_2350.field_11034 : class_2350.field_11039;
         case field_11052 -> normal.y() > 0.0F ? class_2350.field_11036 : class_2350.field_11033;
         case field_11051 -> normal.z() > 0.0F ? class_2350.field_11035 : class_2350.field_11043;
         default -> class_2350.field_11036;
      };
   }

   public static class_2351 longestAxis(Vector3f vec) {
      return longestAxis(vec.x(), vec.y(), vec.z());
   }

   public static class_2351 longestAxis(float normalX, float normalY, float normalZ) {
      class_2351 result = class_2351.field_11052;
      float longest = Math.abs(normalY);
      float a = Math.abs(normalX);
      if (a > longest) {
         result = class_2351.field_11048;
         longest = a;
      }

      return Math.abs(normalZ) > longest ? class_2351.field_11051 : result;
   }
}
