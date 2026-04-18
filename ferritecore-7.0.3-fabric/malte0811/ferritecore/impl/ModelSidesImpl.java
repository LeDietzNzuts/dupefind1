package malte0811.ferritecore.impl;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_156;
import net.minecraft.class_2350;
import net.minecraft.class_777;

public class ModelSidesImpl {
   private static final class_2350[] SIDES = class_2350.values();
   private static final Map<class_2350, List<class_777>> EMPTY = (Map<class_2350, List<class_777>>)class_156.method_654(new EnumMap(class_2350.class), m -> {
      for (class_2350 side : SIDES) {
         m.put(side, List.of());
      }
   });

   public static List<class_777> minimizeUnculled(List<class_777> quads) {
      return List.copyOf(quads);
   }

   public static Map<class_2350, List<class_777>> minimizeCulled(Map<class_2350, List<class_777>> quadsBySide) {
      if (quadsBySide.isEmpty()) {
         return quadsBySide;
      } else {
         boolean allEmpty = true;

         for (class_2350 face : SIDES) {
            List<class_777> sideQuads = quadsBySide.get(face);
            quadsBySide.put(face, List.copyOf(sideQuads));
            allEmpty &= sideQuads.isEmpty();
         }

         return allEmpty ? EMPTY : quadsBySide;
      }
   }
}
