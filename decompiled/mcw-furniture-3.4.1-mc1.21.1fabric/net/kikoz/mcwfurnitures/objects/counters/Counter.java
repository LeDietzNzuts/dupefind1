package net.kikoz.mcwfurnitures.objects.counters;

import net.kikoz.mcwfurnitures.objects.FurnitureObject;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class Counter extends FurnitureObject {
   public static final class_2753 FACING = class_2383.field_11177;
   protected static final class_265 BASE = class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

   public Counter(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)this.method_9564().method_11657(FACING, class_2350.field_11043));
   }

   public class_265 method_9571(class_2680 state, class_1922 world, class_2338 pos) {
      return class_259.method_1073();
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 reader, class_2338 pos, class_3726 context) {
      return BASE;
   }

   @Override
   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8042());
   }

   @Override
   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING});
   }
}
