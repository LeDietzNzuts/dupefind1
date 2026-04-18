package vectorwing.farmersdelight.common.event;

import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_4174;
import net.minecraft.class_4174.class_9423;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;

public class CommonEvents {
   public static void onItemUseFinished(class_1937 level, class_1309 livingEntity, class_1799 stack) {
      handleVanillaSoupEffects(level, livingEntity, stack);
   }

   public static void handleVanillaSoupEffects(class_1937 level, class_1309 livingEntity, class_1799 stack) {
      class_1792 food = stack.method_7909();
      if (Configuration.RABBIT_STEW_BUFF.get() && food.equals(class_1802.field_8308)) {
         livingEntity.method_6092(new class_1293(class_1294.field_5913, 200, 1));
      }

      if (Configuration.VANILLA_SOUP_EXTRA_EFFECTS.get()) {
         class_4174 soupEffects = FoodValues.VANILLA_SOUP_EFFECTS.get(food);
         if (soupEffects != null) {
            for (class_9423 effect : soupEffects.comp_2495()) {
               livingEntity.method_6092(effect.comp_2496());
            }
         }
      }
   }
}
