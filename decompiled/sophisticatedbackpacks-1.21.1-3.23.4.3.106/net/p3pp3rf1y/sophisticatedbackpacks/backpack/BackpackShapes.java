package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import net.minecraft.class_2248;
import net.minecraft.class_2350;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.p3pp3rf1y.sophisticatedcore.util.RotatedShapes;

public class BackpackShapes {
   public static final BackpackShapes.IShapeProvider DEFAULT_SHAPE_PROVIDER = new BackpackShapes.IShapeProvider() {
      private static final Map<Integer, class_265> SHAPES = new ConcurrentHashMap<>();
      private static final RotatedShapes BODY = new RotatedShapes(
         new class_265[]{
            class_2248.method_9541(5.25, 0.0, 4.0, 5.75, 12.5, 5.0),
            class_2248.method_9541(4.25, 0.0, 4.0, 5.25, 12.5, 5.0),
            class_2248.method_9541(3.75, 0.0, 4.0, 4.25, 12.5, 5.0),
            class_2248.method_9541(10.25, 0.0, 4.0, 10.75, 12.5, 5.0),
            class_2248.method_9541(10.75, 0.0, 4.0, 11.75, 12.5, 5.0),
            class_2248.method_9541(11.75, 0.0, 4.0, 12.25, 12.5, 5.0),
            class_2248.method_9541(4.25, 8.25, 11.0, 11.75, 9.25, 11.25),
            class_2248.method_9541(11.75, 8.25, 5.25, 12.75, 13.25, 11.25),
            class_2248.method_9541(4.25, 9.25, 5.25, 11.75, 13.25, 11.25),
            class_2248.method_9541(3.25, 8.25, 5.25, 4.25, 13.25, 11.25),
            class_2248.method_9541(5.75, 13.25, 7.25, 6.5, 14.0, 8.25),
            class_2248.method_9541(5.75, 14.0, 7.25, 10.25, 14.5, 8.25),
            class_2248.method_9541(9.5, 13.25, 7.25, 10.25, 14.0, 8.25),
            class_2248.method_9541(4.5, 9.0, 4.75, 5.5, 13.5, 11.5),
            class_2248.method_9541(4.5, 7.0, 10.75, 5.5, 9.0, 11.5),
            class_2248.method_9541(10.5, 9.0, 4.75, 11.5, 13.5, 11.5),
            class_2248.method_9541(10.5, 7.0, 10.75, 11.5, 9.0, 11.5),
            class_2248.method_9541(3.0, 0.0, 5.0, 13.0, 13.0, 11.0),
            class_2248.method_9541(4.0, 1.0, 4.5, 12.0, 12.0, 5.0)
         }
      );
      private static final RotatedShapes BATTERY = new RotatedShapes(
         new class_265[]{
            class_2248.method_9541(4.0, 0.0, 11.0, 12.0, 6.0, 14.0),
            class_2248.method_9541(6.0, 5.25, 11.5, 7.0, 6.25, 12.5),
            class_2248.method_9541(7.25, 5.25, 11.5, 8.25, 6.25, 12.5),
            class_2248.method_9541(6.0, 7.0, 10.25, 7.0, 8.0, 11.25),
            class_2248.method_9541(7.25, 7.0, 10.25, 8.25, 8.0, 11.25),
            class_2248.method_9541(6.199999999999999, 5.6, 11.7, 6.800000000000001, 7.2, 12.3),
            class_2248.method_9541(6.199999999999999, 7.2, 10.7, 6.800000000000001, 7.8, 12.3),
            class_2248.method_9541(7.449999999999999, 5.6, 11.7, 8.05, 7.2, 12.3),
            class_2248.method_9541(7.449999999999999, 7.2, 10.7, 8.05, 7.8, 12.3),
            class_2248.method_9541(8.8, 4.05, 12.95, 10.2, 5.45, 14.35),
            class_2248.method_9541(8.8, 0.05, 12.95, 10.2, 1.45, 14.35),
            class_2248.method_9541(11.25, 4.25, 10.25, 12.25, 5.25, 14.25),
            class_2248.method_9541(4.5, 4.25, 13.25, 11.5, 5.25, 14.25),
            class_2248.method_9541(3.75, 4.25, 10.25, 4.75, 5.25, 14.25),
            class_2248.method_9541(11.25, 0.25, 10.25, 12.25, 1.25, 14.25),
            class_2248.method_9541(4.5, 0.25, 13.25, 11.5, 1.25, 14.25),
            class_2248.method_9541(3.75, 0.25, 10.25, 4.75, 1.25, 14.25)
         }
      );
      private static final RotatedShapes LEFT_TANK = new RotatedShapes(
         new class_265[]{
            class_2248.method_9541(2.5, 1.5, 6.0, 3.5, 7.5, 10.0),
            class_2248.method_9541(0.5, 10.5, 6.5, 3.5, 11.5, 9.5),
            class_2248.method_9541(0.0, 9.5, 6.0, 3.0, 10.5, 10.0),
            class_2248.method_9541(0.0, 8.5, 6.0, 3.0, 9.5, 10.0),
            class_2248.method_9541(0.0, 7.5, 6.0, 3.0, 8.5, 10.0),
            class_2248.method_9541(0.0, 0.5, 6.0, 3.0, 1.5, 10.0),
            class_2248.method_9541(0.5, 1.5, 6.5, 2.5, 7.5, 9.5)
         }
      );
      private static final RotatedShapes RIGHT_TANK = new RotatedShapes(
         new class_265[]{
            class_2248.method_9541(12.5, 1.5, 6.0, 13.5, 7.5, 10.0),
            class_2248.method_9541(12.5, 10.5, 6.5, 15.5, 11.5, 9.5),
            class_2248.method_9541(13.0, 9.5, 6.0, 16.0, 10.5, 10.0),
            class_2248.method_9541(13.0, 8.5, 6.0, 16.0, 9.5, 10.0),
            class_2248.method_9541(13.0, 7.5, 6.0, 16.0, 8.5, 10.0),
            class_2248.method_9541(13.0, 0.5, 6.0, 16.0, 1.5, 10.0),
            class_2248.method_9541(13.5, 1.5, 6.5, 15.5, 7.5, 9.5)
         }
      );
      private static final RotatedShapes LEFT_POUCHES = new RotatedShapes(
         new class_265[]{
            class_2248.method_9541(1.0, 2.0, 5.5, 3.0, 6.0, 10.5),
            class_2248.method_9541(1.0, 1.0, 5.5, 3.0, 2.0, 10.5),
            class_2248.method_9541(1.0, 0.0, 5.5, 3.0, 1.0, 10.5),
            class_2248.method_9541(0.75, 3.0, 7.5, 1.0, 5.0, 8.5),
            class_2248.method_9541(1.0, 4.0, 5.5, 3.0, 5.0, 10.5),
            class_2248.method_9541(2.0, 7.0, 5.5, 3.0, 11.0, 10.5),
            class_2248.method_9541(1.75, 8.0, 7.5, 2.0, 10.0, 8.5),
            class_2248.method_9541(2.0, 9.0, 5.5, 4.0, 10.0, 10.5)
         }
      );
      private static final RotatedShapes RIGHT_POUCHES = new RotatedShapes(
         new class_265[]{
            class_2248.method_9541(13.0, 2.0, 5.5, 15.0, 6.0, 10.5),
            class_2248.method_9541(13.0, 1.0, 5.5, 15.0, 2.0, 10.5),
            class_2248.method_9541(13.0, 0.0, 5.5, 15.0, 1.0, 10.5),
            class_2248.method_9541(15.0, 3.0, 7.5, 15.25, 5.0, 8.5),
            class_2248.method_9541(13.0, 4.0, 5.5, 15.0, 5.0, 10.5),
            class_2248.method_9541(13.0, 7.0, 5.5, 14.0, 11.0, 10.5),
            class_2248.method_9541(14.0, 8.0, 7.5, 14.25, 10.0, 8.5),
            class_2248.method_9541(12.0, 9.0, 5.5, 14.0, 10.0, 10.5)
         }
      );
      private static final RotatedShapes FRONT_POUCH = new RotatedShapes(
         new class_265[]{
            class_2248.method_9541(4.0, 2.0, 11.0, 12.0, 6.0, 13.0),
            class_2248.method_9541(4.0, 1.0, 11.0, 12.0, 2.0, 13.0),
            class_2248.method_9541(4.0, 0.0, 11.0, 12.0, 1.0, 13.0),
            class_2248.method_9541(5.0, 3.0, 13.0, 6.0, 5.0, 13.25),
            class_2248.method_9541(10.0, 3.0, 13.0, 11.0, 5.0, 13.25),
            class_2248.method_9541(4.0, 4.0, 11.0, 12.0, 5.0, 13.0)
         }
      );

      @Override
      public class_265 getShape(class_2248 backpackBlock, class_2350 dir, boolean leftTank, boolean rightTank, boolean battery) {
         int key = getKey(dir, leftTank, rightTank, battery);
         return SHAPES.computeIfAbsent(key, k -> composeShape(dir, leftTank, rightTank, battery));
      }

      private static class_265 composeShape(class_2350 dir, boolean leftTank, boolean rightTank, boolean battery) {
         List<RotatedShapes> shapes = new ArrayList<>();
         shapes.add(BODY);
         shapes.add(leftTank ? LEFT_TANK : LEFT_POUCHES);
         shapes.add(rightTank ? RIGHT_TANK : RIGHT_POUCHES);
         shapes.add(battery ? BATTERY : FRONT_POUCH);
         return or(shapes.stream().map(r -> r.getRotatedShape(dir)));
      }

      private static class_265 or(Stream<class_265> shapes) {
         return shapes.reduce((v1, v2) -> class_259.method_1082(v1, v2, class_247.field_1366))
            .<class_265>map(class_265::method_1097)
            .orElse(class_259.method_1073());
      }

      private static int getKey(class_2350 dir, boolean leftTank, boolean rightTank, boolean battery) {
         return dir.method_10161() << 3 | b2i(leftTank) << 2 | b2i(rightTank) << 1 | b2i(battery);
      }

      private static int b2i(boolean value) {
         return value ? 1 : 0;
      }
   };
   private static BackpackShapes.IShapeProvider shapeProvider = DEFAULT_SHAPE_PROVIDER;

   private BackpackShapes() {
   }

   public static void setShapeProvider(BackpackShapes.IShapeProvider provider) {
      shapeProvider = provider;
   }

   public static class_265 getShape(class_2248 backpackBlock, class_2350 dir, boolean leftTank, boolean rightTank, boolean battery) {
      return shapeProvider.getShape(backpackBlock, dir, leftTank, rightTank, battery);
   }

   public interface IShapeProvider {
      class_265 getShape(class_2248 var1, class_2350 var2, boolean var3, boolean var4, boolean var5);
   }
}
