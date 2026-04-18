package net.kikoz.mcwfurnitures.objects;

import net.kikoz.mcwfurnitures.sittable.ChairEntity;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_4970.class_2251;

public class Chair extends FurnitureObjectNonFaceable {
   private static final class_265 BASE = class_259.method_17786(
      class_2248.method_9541(4.0, 11.0, 4.0, 12.0, 11.6, 12.0),
      new class_265[]{
         class_2248.method_9541(10.0, 0.0, 10.0, 12.0, 9.0, 12.0),
         class_2248.method_9541(4.0, 0.0, 10.0, 6.0, 9.0, 12.0),
         class_2248.method_9541(10.0, 0.0, 4.0, 12.0, 9.0, 6.0),
         class_2248.method_9541(4.0, 0.0, 4.0, 6.0, 9.0, 6.0),
         class_2248.method_9541(3.0, 9.0, 3.0, 13.0, 11.0, 13.0)
      }
   );

   public Chair(class_2251 properties) {
      super(properties);
      this.method_9590(this.method_9564());
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      return BASE;
   }

   @Override
   public class_9062 method_55765(
      class_1799 itemstack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 handIn, class_3965 hit
   ) {
      return ChairEntity.create(level, pos, 0.3, player);
   }

   @Override
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return null;
   }
}
