package net.kikoz.mcwfurnitures.objects.chairs;

import net.kikoz.mcwfurnitures.objects.FurnitureObject;
import net.kikoz.mcwfurnitures.sittable.ChairEntity;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class ModernChair extends FurnitureObject {
   public static final class_2753 FACING = class_2383.field_11177;
   private static final class_265 NORTH_SHAPE = class_259.method_17786(
      class_2248.method_9541(2.0, 12.0, 12.0, 14.0, 18.0, 15.0),
      new class_265[]{
         class_2248.method_9541(0.0, 0.0, 0.0, 2.0, 15.0, 2.0),
         class_2248.method_9541(14.0, 0.0, 0.0, 16.0, 15.0, 2.0),
         class_2248.method_9541(0.0, 0.0, 14.0, 2.0, 15.0, 16.0),
         class_2248.method_9541(14.0, 0.0, 14.0, 16.0, 15.0, 16.0),
         class_2248.method_9541(0.0, 15.0, 0.0, 2.0, 16.0, 16.0),
         class_2248.method_9541(14.0, 15.0, 0.0, 16.0, 16.0, 16.0),
         class_2248.method_9541(1.0, 8.0, 0.1, 15.0, 10.0, 15.9),
         class_2248.method_9541(2.0, 9.0, 1.0, 14.0, 12.0, 15.0)
      }
   );
   private static final class_265 EAST_SHAPE = class_259.method_17786(
      class_2248.method_9541(1.0, 12.0, 2.0, 4.0, 18.0, 14.0),
      new class_265[]{
         class_2248.method_9541(14.0, 0.0, 0.0, 16.0, 15.0, 2.0),
         class_2248.method_9541(14.0, 0.0, 14.0, 16.0, 15.0, 16.0),
         class_2248.method_9541(0.0, 0.0, 0.0, 2.0, 15.0, 2.0),
         class_2248.method_9541(0.0, 0.0, 14.0, 2.0, 15.0, 16.0),
         class_2248.method_9541(0.0, 15.0, 0.0, 16.0, 16.0, 2.0),
         class_2248.method_9541(0.0, 15.0, 14.0, 16.0, 16.0, 16.0),
         class_2248.method_9541(0.1, 8.0, 1.0, 15.9, 10.0, 15.0),
         class_2248.method_9541(1.0, 9.0, 2.0, 15.0, 12.0, 14.0)
      }
   );
   private static final class_265 SOUTH_SHAPE = class_259.method_17786(
      class_2248.method_9541(2.0, 12.0, 1.0, 14.0, 18.0, 4.0),
      new class_265[]{
         class_2248.method_9541(14.0, 0.0, 14.0, 16.0, 15.0, 16.0),
         class_2248.method_9541(0.0, 0.0, 14.0, 2.0, 15.0, 16.0),
         class_2248.method_9541(14.0, 0.0, 0.0, 16.0, 15.0, 2.0),
         class_2248.method_9541(0.0, 0.0, 0.0, 2.0, 15.0, 2.0),
         class_2248.method_9541(0.0, 15.0, 0.0, 2.0, 16.0, 16.0),
         class_2248.method_9541(14.0, 15.0, 0.0, 16.0, 16.0, 16.0),
         class_2248.method_9541(1.0, 8.0, 0.1, 15.0, 10.0, 15.9),
         class_2248.method_9541(2.0, 9.0, 1.0, 14.0, 12.0, 15.0)
      }
   );
   private static final class_265 WEST_SHAPE = class_259.method_17786(
      class_2248.method_9541(12.0, 12.0, 2.0, 15.0, 18.0, 14.0),
      new class_265[]{
         class_2248.method_9541(14.0, 0.0, 0.0, 16.0, 15.0, 2.0),
         class_2248.method_9541(14.0, 0.0, 14.0, 16.0, 15.0, 16.0),
         class_2248.method_9541(0.0, 0.0, 0.0, 2.0, 15.0, 2.0),
         class_2248.method_9541(0.0, 0.0, 14.0, 2.0, 15.0, 16.0),
         class_2248.method_9541(0.0, 15.0, 0.0, 16.0, 16.0, 2.0),
         class_2248.method_9541(0.0, 15.0, 14.0, 16.0, 16.0, 16.0),
         class_2248.method_9541(0.1, 8.0, 1.0, 15.9, 10.0, 15.0),
         class_2248.method_9541(1.0, 9.0, 2.0, 15.0, 12.0, 14.0)
      }
   );

   public ModernChair(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)this.method_9564().method_11657(FACING, class_2350.field_11043));
   }

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

   @Override
   public class_9062 method_55765(
      class_1799 itemstack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 handIn, class_3965 hit
   ) {
      return ChairEntity.create(level, pos, 0.3, player);
   }

   @Override
   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8042().method_10160().method_10160());
   }

   @Override
   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING});
   }

   @Override
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return null;
   }
}
