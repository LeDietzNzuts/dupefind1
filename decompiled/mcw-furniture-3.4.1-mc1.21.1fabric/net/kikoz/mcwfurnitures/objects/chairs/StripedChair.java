package net.kikoz.mcwfurnitures.objects.chairs;

import net.kikoz.mcwfurnitures.sittable.ChairEntity;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_4970.class_2251;

public class StripedChair extends ModernChair {
   private static final class_265 WEST_SHAPE = class_259.method_17786(
      class_2248.method_9541(12.0, 10.9, 1.99, 14.0, 23.9, 13.99),
      new class_265[]{
         class_2248.method_9541(2.0, 11.0, 3.0, 12.0, 11.6, 13.0),
         class_2248.method_9541(11.0, 0.0, 12.0, 13.0, 9.0, 14.0),
         class_2248.method_9541(2.0, 0.0, 12.0, 4.0, 9.0, 14.0),
         class_2248.method_9541(11.0, 0.0, 2.0, 13.0, 9.0, 4.0),
         class_2248.method_9541(2.0, 0.0, 2.0, 4.0, 9.0, 4.0),
         class_2248.method_9541(1.0, 9.0, 2.0, 14.0, 11.0, 14.0)
      }
   );
   private static final class_265 NORTH_SHAPE = class_259.method_17786(
      class_2248.method_9541(2.0, 11.0, 12.0, 14.0, 24.0, 14.0),
      new class_265[]{
         class_2248.method_9541(3.0, 11.0, 2.0, 13.0, 11.6, 12.0),
         class_2248.method_9541(2.0, 0.0, 11.0, 4.0, 9.0, 13.0),
         class_2248.method_9541(2.0, 0.0, 2.0, 4.0, 9.0, 4.0),
         class_2248.method_9541(12.0, 0.0, 11.0, 14.0, 9.0, 13.0),
         class_2248.method_9541(12.0, 0.0, 2.0, 14.0, 9.0, 4.0),
         class_2248.method_9541(2.0, 9.0, 1.0, 14.0, 11.0, 14.0)
      }
   );
   private static final class_265 EAST_SHAPE = class_259.method_17786(
      class_2248.method_9541(2.0, 11.0, 2.0, 4.0, 24.0, 14.0),
      new class_265[]{
         class_2248.method_9541(4.0, 11.0, 3.0, 14.0, 11.6, 13.0),
         class_2248.method_9541(3.0, 0.0, 2.0, 5.0, 9.0, 4.0),
         class_2248.method_9541(12.0, 0.0, 2.0, 14.0, 9.0, 4.0),
         class_2248.method_9541(3.0, 0.0, 12.0, 5.0, 9.0, 14.0),
         class_2248.method_9541(12.0, 0.0, 12.0, 14.0, 9.0, 14.0),
         class_2248.method_9541(2.0, 9.0, 2.0, 15.0, 11.0, 14.0)
      }
   );
   private static final class_265 SOUTH_SHAPE = class_259.method_17786(
      class_2248.method_9541(2.0, 11.0, 2.0, 14.0, 24.0, 4.0),
      new class_265[]{
         class_2248.method_9541(3.0, 11.0, 4.0, 13.0, 11.6, 14.0),
         class_2248.method_9541(12.0, 0.0, 3.0, 14.0, 9.0, 5.0),
         class_2248.method_9541(12.0, 0.0, 12.0, 14.0, 9.0, 14.0),
         class_2248.method_9541(2.0, 0.0, 3.0, 4.0, 9.0, 5.0),
         class_2248.method_9541(2.0, 0.0, 12.0, 4.0, 9.0, 14.0),
         class_2248.method_9541(2.0, 9.0, 2.0, 14.0, 11.0, 15.0)
      }
   );

   @Override
   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      switch ((class_2350)state.method_11654(FACING)) {
         case field_11043:
            return NORTH_SHAPE;
         case field_11035:
            return SOUTH_SHAPE;
         case field_11039:
            return WEST_SHAPE;
         case field_11034:
         default:
            return EAST_SHAPE;
      }
   }

   public StripedChair(class_2251 properties) {
      super(properties);
   }

   @Override
   public class_9062 method_55765(
      class_1799 itemstack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 handIn, class_3965 hit
   ) {
      return ChairEntity.create(level, pos, 0.3, player);
   }
}
