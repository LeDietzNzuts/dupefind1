package vectorwing.farmersdelight.common.effect;

import net.minecraft.class_1291;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1702;
import net.minecraft.class_1928;
import net.minecraft.class_4081;

public class NourishmentEffect extends class_1291 {
   public NourishmentEffect() {
      super(class_4081.field_18271, 15971072);
   }

   public boolean method_5572(class_1309 entity, int amplifier) {
      if (!entity.method_5770().field_9236 && entity instanceof class_1657 player) {
         class_1702 foodData = player.method_7344();
         boolean isPlayerHealingWithHunger = player.method_37908().method_8450().method_8355(class_1928.field_19395)
            && player.method_7317()
            && foodData.method_7586() >= 18;
         if (!isPlayerHealingWithHunger) {
            float exhaustion = foodData.method_35219();
            float reduction = Math.min(exhaustion, 4.0F);
            if (exhaustion > 0.0F) {
               player.method_7322(-reduction);
            }
         }
      }

      return true;
   }

   public boolean method_5552(int duration, int amplifier) {
      return true;
   }
}
