package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_2350;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;

public class RotatedShapes {
   private final class_265[] rotatedShapes;
   private final boolean horizontal;

   public RotatedShapes(class_265... shapes) {
      this(true, shapes);
   }

   public RotatedShapes(boolean horizontal, class_265... shapes) {
      this.rotatedShapes = new class_265[horizontal ? 4 : 6];
      this.horizontal = horizontal;
      this.rotatedShapes[0] = this.or(Stream.of(shapes));
   }

   public class_265 getRotatedShape(class_2350 to) {
      int index = this.horizontal ? (to.method_10161() + 4) % 4 : to.method_10146();
      if (this.rotatedShapes[index] == null) {
         if (this.horizontal) {
            this.rotatedShapes[index] = this.rotateHorizontal(to);
         } else {
            this.rotatedShapes[index] = this.rotate(to);
         }
      }

      return this.rotatedShapes[index];
   }

   private class_265 rotateHorizontal(class_2350 dir) {
      return switch (dir) {
         case field_11043 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(1.0 - minX, minY, 1.0 - minZ, 1.0 - maxX, maxY, 1.0 - maxZ)
         );
         case field_11035 -> this.rotatedShapes[0];
         case field_11039 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(1.0 - minZ, minY, minX, 1.0 - maxZ, maxY, maxX)
         );
         case field_11034 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(minZ, minY, 1.0 - minX, maxZ, maxY, 1.0 - maxX)
         );
         default -> this.rotatedShapes[0];
      };
   }

   private class_265 box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
      return class_259.method_1081(
         Math.min(minX, maxX), Math.min(minY, maxY), Math.min(minZ, maxZ), Math.max(minX, maxX), Math.max(minY, maxY), Math.max(minZ, maxZ)
      );
   }

   private class_265 rotate(class_2350 dir) {
      return switch (dir) {
         case field_11043 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(minX, 1.0 - minZ, minY, maxX, 1.0 - maxZ, maxY)
         );
         case field_11035 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(1.0 - minX, 1.0 - minZ, 1.0 - minY, 1.0 - maxX, 1.0 - maxZ, 1.0 - maxY)
         );
         case field_11039 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(minY, 1.0 - minZ, 1.0 - minX, maxY, 1.0 - maxZ, 1.0 - maxX)
         );
         case field_11034 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(1.0 - minY, 1.0 - minZ, minX, 1.0 - maxY, 1.0 - maxZ, maxX)
         );
         case field_11033 -> this.rotatedShapes[0];
         case field_11036 -> this.rotateShape(
            this.rotatedShapes[0], (minX, minY, minZ, maxX, maxY, maxZ) -> this.box(minX, 1.0 - minY, 1.0 - minZ, maxX, 1.0 - maxY, 1.0 - maxZ)
         );
         default -> throw new MatchException(null, null);
      };
   }

   private class_265 rotateShape(class_265 shape, RotatedShapes.DoubleLineFunction rotate) {
      List<class_265> shapes = new ArrayList<>();
      shape.method_1089((minX, minY, minZ, maxX, maxY, maxZ) -> shapes.add(rotate.apply(minX, minY, minZ, maxX, maxY, maxZ)));
      return this.or(shapes.stream());
   }

   private class_265 or(Stream<class_265> shapes) {
      return shapes.reduce((v1, v2) -> class_259.method_1082(v1, v2, class_247.field_1366))
         .<class_265>map(class_265::method_1097)
         .orElse(class_259.method_1073());
   }

   public interface DoubleLineFunction {
      class_265 apply(double var1, double var3, double var5, double var7, double var9, double var11);
   }
}
