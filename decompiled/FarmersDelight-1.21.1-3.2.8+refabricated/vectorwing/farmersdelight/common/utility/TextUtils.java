package vectorwing.farmersdelight.common.utility;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.class_124;
import net.minecraft.class_1291;
import net.minecraft.class_1292;
import net.minecraft.class_1293;
import net.minecraft.class_1320;
import net.minecraft.class_1322;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_4174;
import net.minecraft.class_5244;
import net.minecraft.class_5250;
import net.minecraft.class_6880;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import net.minecraft.class_1322.class_1323;
import net.minecraft.class_4174.class_9423;

public class TextUtils {
   private static final class_5250 NO_EFFECTS = class_2561.method_43471("effect.none").method_27692(class_124.field_1080);

   public static class_5250 getTranslation(String key, Object... args) {
      return class_2561.method_43469("farmersdelight." + key, args);
   }

   public static void addFoodEffectTooltip(class_1799 stack, Consumer<class_2561> tooltipAdder, float durationFactor, float tickRate) {
      class_4174 foodStats = (class_4174)stack.method_57824(class_9334.field_50075);
      if (foodStats != null) {
         List<class_9423> effectList = foodStats.comp_2495();
         List<Pair<class_6880<class_1320>, class_1322>> attributeList = Lists.newArrayList();
         if (effectList.isEmpty()) {
            tooltipAdder.accept(NO_EFFECTS);
         } else {
            for (class_9423 possibleEffect : effectList) {
               class_1293 instance = possibleEffect.comp_2496();
               class_5250 mutableComponent = class_2561.method_43471(instance.method_5586());
               class_1291 effect = (class_1291)instance.method_5579().comp_349();
               effect.method_55650(
                  instance.method_5578(), (attributeHolder, attributeModifier) -> attributeList.add(new Pair(attributeHolder, attributeModifier))
               );
               if (instance.method_5578() > 0) {
                  mutableComponent = class_2561.method_43469(
                     "potion.withAmplifier", new Object[]{mutableComponent, class_2561.method_43471("potion.potency." + instance.method_5578())}
                  );
               }

               if (instance.method_5584() > 20) {
                  mutableComponent = class_2561.method_43469(
                     "potion.withDuration", new Object[]{mutableComponent, class_1292.method_5577(instance, durationFactor, tickRate)}
                  );
               }

               tooltipAdder.accept(mutableComponent.method_27692(effect.method_18792().method_18793()));
            }
         }

         if (!attributeList.isEmpty()) {
            tooltipAdder.accept(class_5244.field_39003);
            tooltipAdder.accept(class_2561.method_43471("potion.whenDrank").method_27692(class_124.field_1064));

            for (Pair<class_6880<class_1320>, class_1322> pair : attributeList) {
               class_1322 attributemodifier = (class_1322)pair.getSecond();
               double amount = attributemodifier.comp_2449();
               double formattedAmount;
               if (attributemodifier.comp_2450() != class_1323.field_6330 && attributemodifier.comp_2450() != class_1323.field_6331) {
                  formattedAmount = attributemodifier.comp_2449();
               } else {
                  formattedAmount = attributemodifier.comp_2449() * 100.0;
               }

               if (amount > 0.0) {
                  tooltipAdder.accept(
                     class_2561.method_43469(
                           "attribute.modifier.plus." + attributemodifier.comp_2450().method_56082(),
                           new Object[]{
                              class_9285.field_49329.format(formattedAmount),
                              class_2561.method_43471(((class_1320)((class_6880)pair.getFirst()).comp_349()).method_26830())
                           }
                        )
                        .method_27692(class_124.field_1078)
                  );
               } else if (amount < 0.0) {
                  formattedAmount *= -1.0;
                  tooltipAdder.accept(
                     class_2561.method_43469(
                           "attribute.modifier.take." + attributemodifier.comp_2450().method_56082(),
                           new Object[]{
                              class_9285.field_49329.format(formattedAmount),
                              class_2561.method_43471(((class_1320)((class_6880)pair.getFirst()).comp_349()).method_26830())
                           }
                        )
                        .method_27692(class_124.field_1061)
                  );
               }
            }
         }
      }
   }
}
