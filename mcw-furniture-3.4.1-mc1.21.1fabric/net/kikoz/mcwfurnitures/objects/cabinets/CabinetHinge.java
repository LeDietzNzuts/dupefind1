package net.kikoz.mcwfurnitures.objects.cabinets;

import net.kikoz.mcwfurnitures.objects.counters.CupboardCounter;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_4970.class_2251;

public class CabinetHinge extends CupboardCounter {
   protected static final class_265 EAS = class_2248.method_9541(5.0, 0.0, 0.0, 16.0, 16.0, 16.0);
   protected static final class_265 SOU = class_2248.method_9541(0.0, 0.0, 5.0, 16.0, 16.0, 16.0);
   protected static final class_265 WES = class_2248.method_9541(0.0, 0.0, 0.0, 11.0, 16.0, 16.0);
   protected static final class_265 NOR = class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 16.0, 11.0);

   public CabinetHinge(class_2251 prop) {
      super(prop);
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 reader, class_2338 pos, class_3726 context) {
      switch ((class_2350)state.method_11654(FACING)) {
         case field_11043:
            return NOR;
         case field_11035:
            return SOU;
         case field_11039:
            return WES;
         case field_11034:
         default:
            return EAS;
      }
   }
}
