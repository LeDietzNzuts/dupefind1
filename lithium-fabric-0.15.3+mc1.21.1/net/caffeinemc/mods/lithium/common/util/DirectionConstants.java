package net.caffeinemc.mods.lithium.common.util;

import net.minecraft.class_2350;

public final class DirectionConstants {
   public static final class_2350[] ALL = class_2350.values();
   public static final class_2350[] VERTICAL = new class_2350[]{class_2350.field_11033, class_2350.field_11036};
   public static final class_2350[] HORIZONTAL = new class_2350[]{
      class_2350.field_11039, class_2350.field_11034, class_2350.field_11043, class_2350.field_11035
   };
   public static final byte[] HORIZONTAL_OPPOSITE_INDICES = new byte[]{1, 0, 3, 2};

   private DirectionConstants() {
   }
}
