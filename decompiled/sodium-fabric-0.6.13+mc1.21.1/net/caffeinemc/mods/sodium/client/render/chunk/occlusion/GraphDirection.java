package net.caffeinemc.mods.sodium.client.render.chunk.occlusion;

import net.minecraft.class_2350;

public class GraphDirection {
   public static final int DOWN = 0;
   public static final int UP = 1;
   public static final int NORTH = 2;
   public static final int SOUTH = 3;
   public static final int WEST = 4;
   public static final int EAST = 5;
   public static final int COUNT = 6;
   private static final class_2350[] ENUMS = new class_2350[6];
   private static final int[] OPPOSITE = new int[6];
   private static final int[] X = new int[6];
   private static final int[] Y = new int[6];
   private static final int[] Z = new int[6];

   public static int opposite(int direction) {
      return OPPOSITE[direction];
   }

   public static int x(int direction) {
      return X[direction];
   }

   public static int y(int direction) {
      return Y[direction];
   }

   public static int z(int direction) {
      return Z[direction];
   }

   public static class_2350 toEnum(int direction) {
      return ENUMS[direction];
   }

   static {
      OPPOSITE[0] = 1;
      OPPOSITE[1] = 0;
      OPPOSITE[2] = 3;
      OPPOSITE[3] = 2;
      OPPOSITE[4] = 5;
      OPPOSITE[5] = 4;
      X[4] = -1;
      X[5] = 1;
      Y[0] = -1;
      Y[1] = 1;
      Z[2] = -1;
      Z[3] = 1;
      ENUMS[0] = class_2350.field_11033;
      ENUMS[1] = class_2350.field_11036;
      ENUMS[2] = class_2350.field_11043;
      ENUMS[3] = class_2350.field_11035;
      ENUMS[4] = class_2350.field_11039;
      ENUMS[5] = class_2350.field_11034;
   }
}
