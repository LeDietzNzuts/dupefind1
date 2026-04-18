package net.kikoz.mcwfurnitures.objects.counters;

import net.minecraft.class_1309;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2750;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class CupboardCounter extends Counter {
   protected static final class_265 BASE = class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
   public static final class_2754<class_2750> HINGE = class_2741.field_12520;

   public CupboardCounter(class_2251 prop) {
      super(prop);
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 reader, class_2338 pos, class_3726 context) {
      return BASE;
   }

   @Nullable
   @Override
   public class_2680 method_9605(class_1750 context) {
      class_2350 playerFacing = context.method_8042();
      class_2750 hinge = this.getHinge(context);
      return (class_2680)((class_2680)this.method_9564().method_11657(FACING, playerFacing)).method_11657(HINGE, hinge);
   }

   @Override
   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, HINGE});
   }

   private class_2750 getHinge(class_1750 context) {
      class_2338 blockpos = context.method_8037();
      class_2350 direction = context.method_8042();
      int j = direction.method_10148();
      int k = direction.method_10165();
      class_243 vector3d = context.method_17698();
      double d0 = vector3d.field_1352 - blockpos.method_10263();
      double d1 = vector3d.field_1350 - blockpos.method_10260();
      return j < 0 && d1 < 0.5 || j > 0 && d1 > 0.5 || k < 0 && d0 > 0.5 || k > 0 && d0 < 0.5 ? class_2750.field_12586 : class_2750.field_12588;
   }

   public void method_9567(class_1937 world, class_2338 pos, class_2680 state, class_1309 placer, class_1799 itemStack) {
      class_2680 blockBelow = world.method_8320(pos.method_10074());
      if (blockBelow.method_26204() == this) {
         class_2750 hinge = (class_2750)blockBelow.method_11654(HINGE);
         world.method_8501(pos, (class_2680)state.method_11657(HINGE, hinge));
      }
   }
}
