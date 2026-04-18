package net.caffeinemc.mods.sodium.client.render.viewport;

import net.caffeinemc.mods.sodium.client.render.viewport.frustum.Frustum;
import net.minecraft.class_2338;
import net.minecraft.class_4076;
import org.joml.Vector3d;

public final class Viewport {
   private final Frustum frustum;
   private final CameraTransform transform;
   private final class_4076 sectionCoords;
   private final class_2338 blockCoords;

   public Viewport(Frustum frustum, Vector3d position) {
      this.frustum = frustum;
      this.transform = new CameraTransform(position.x, position.y, position.z);
      this.sectionCoords = class_4076.method_18676(
         class_4076.method_32204(position.x), class_4076.method_32204(position.y), class_4076.method_32204(position.z)
      );
      this.blockCoords = class_2338.method_49637(position.x, position.y, position.z);
   }

   public boolean isBoxVisible(int intOriginX, int intOriginY, int intOriginZ, float floatSizeX, float floatSizeY, float floatSizeZ) {
      float floatOriginX = intOriginX - this.transform.intX - this.transform.fracX;
      float floatOriginY = intOriginY - this.transform.intY - this.transform.fracY;
      float floatOriginZ = intOriginZ - this.transform.intZ - this.transform.fracZ;
      return this.frustum
         .testAab(
            floatOriginX - floatSizeX,
            floatOriginY - floatSizeY,
            floatOriginZ - floatSizeZ,
            floatOriginX + floatSizeX,
            floatOriginY + floatSizeY,
            floatOriginZ + floatSizeZ
         );
   }

   public CameraTransform getTransform() {
      return this.transform;
   }

   public class_4076 getChunkCoord() {
      return this.sectionCoords;
   }

   public class_2338 getBlockCoord() {
      return this.blockCoords;
   }
}
