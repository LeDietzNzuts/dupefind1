package net.kikoz.mcwfurnitures.objects;

import net.minecraft.class_1750;
import net.minecraft.class_2248;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2470;
import net.minecraft.class_2680;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class FurnitureObject extends FurnitureObjectNonFaceable {
   public static final class_2753 FACING = class_2383.field_11177;

   public FurnitureObject(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)this.method_9564().method_11657(FACING, class_2350.field_11043));
   }

   @Override
   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8042().method_10170());
   }

   public class_2680 method_9598(class_2680 state, class_2470 rotation) {
      return (class_2680)state.method_11657(FACING, rotation.method_10503((class_2350)state.method_11654(FACING)));
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING});
   }
}
