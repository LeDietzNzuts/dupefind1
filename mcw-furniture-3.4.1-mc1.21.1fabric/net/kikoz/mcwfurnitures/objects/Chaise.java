package net.kikoz.mcwfurnitures.objects;

import net.kikoz.mcwfurnitures.sittable.CouchEntity;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_4970.class_2251;

public class Chaise extends class_2248 {
   private static final class_265 BASE = class_259.method_17786(
      class_2248.method_9541(0.0, 0.0, 14.0, 2.0, 2.0, 16.0),
      new class_265[]{
         class_2248.method_9541(14.0, 0.0, 14.0, 16.0, 2.0, 16.0),
         class_2248.method_9541(14.0, 0.0, 0.0, 16.0, 2.0, 2.0),
         class_2248.method_9541(0.0, 2.0, 0.0, 16.0, 7.0, 16.0),
         class_2248.method_9541(0.0, 0.0, 0.0, 2.0, 2.0, 2.0),
         class_2248.method_9541(1.0, 1.0, 1.0, 15.0, 2.0, 15.0)
      }
   );

   public Chaise(class_2251 properties) {
      super(properties);
      this.method_9590(this.method_9564());
   }

   public class_9062 method_55765(
      class_1799 itemstack, class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1268 handIn, class_3965 hit
   ) {
      return CouchEntity.create(world, pos, 0.3, player);
   }

   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      return BASE;
   }
}
