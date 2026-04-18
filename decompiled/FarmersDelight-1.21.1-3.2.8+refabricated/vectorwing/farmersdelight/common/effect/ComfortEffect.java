package vectorwing.farmersdelight.common.effect;

import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_4081;

public class ComfortEffect extends class_1291 {
   public ComfortEffect() {
      super(class_4081.field_18271, 14545909);
   }

   public boolean method_5572(class_1309 entity, int amplifier) {
      if (entity.method_6059(class_1294.field_5924)) {
         return true;
      } else if (entity instanceof class_1657 player && player.method_7344().method_7589() > 0.0) {
         return true;
      } else {
         if (entity.method_6032() < entity.method_6063()) {
            entity.method_6025(1.0F);
         }

         return true;
      }
   }

   public boolean method_5552(int duration, int amplifier) {
      return duration % 80 == 0;
   }
}
