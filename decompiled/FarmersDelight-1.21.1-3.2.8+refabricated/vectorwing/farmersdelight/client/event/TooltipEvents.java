package vectorwing.farmersdelight.client.event;

import java.util.List;
import net.minecraft.class_1291;
import net.minecraft.class_1292;
import net.minecraft.class_1293;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_4174;
import net.minecraft.class_5250;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_4174.class_9423;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;

public class TooltipEvents {
   public static void addTooltipToVanillaSoups(class_1799 stack, class_9635 tooltipContext, class_1836 tooltipType, List<class_2561> lines) {
      if (Configuration.VANILLA_SOUP_EXTRA_EFFECTS.get() && Configuration.FOOD_EFFECT_TOOLTIP.get()) {
         class_1792 food = stack.method_7909();
         class_4174 soupEffects = FoodValues.VANILLA_SOUP_EFFECTS.get(food);
         if (soupEffects != null) {
            for (class_9423 effect : soupEffects.comp_2495()) {
               class_1293 effectInstance = effect.comp_2496();
               class_5250 effectText = class_2561.method_43471(effectInstance.method_5586());
               class_1657 player = class_310.method_1551().field_1724;
               if (effectInstance.method_5584() > 20) {
                  effectText = class_2561.method_43469(
                     "potion.withDuration",
                     new Object[]{
                        effectText, class_1292.method_5577(effectInstance, 1.0F, player == null ? 20.0F : player.method_37908().method_54719().method_54748())
                     }
                  );
               }

               lines.add(effectText.method_27692(((class_1291)effectInstance.method_5579().comp_349()).method_18792().method_18793()));
            }
         }
      }
   }
}
