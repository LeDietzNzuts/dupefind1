package net.caffeinemc.mods.sodium.client.util;

import java.util.Arrays;
import net.minecraft.class_2350;

public class DirectionUtil {
   public static final class_2350[] ALL_DIRECTIONS = class_2350.values();
   public static final class_2350[] HORIZONTAL_DIRECTIONS = new class_2350[]{
      class_2350.field_11043, class_2350.field_11035, class_2350.field_11039, class_2350.field_11034
   };
   private static final class_2350[] OPPOSITE_DIRECTIONS = Arrays.stream(ALL_DIRECTIONS).map(class_2350::method_10153).toArray(class_2350[]::new);

   public static class_2350 getOpposite(class_2350 dir) {
      return OPPOSITE_DIRECTIONS[dir.ordinal()];
   }
}
