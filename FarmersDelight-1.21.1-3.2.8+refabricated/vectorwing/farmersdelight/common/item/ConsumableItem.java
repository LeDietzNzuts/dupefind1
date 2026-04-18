package vectorwing.farmersdelight.common.item;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_174;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3468;
import net.minecraft.class_5250;
import net.minecraft.class_7923;
import net.minecraft.class_9334;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class ConsumableItem extends class_1792 {
   private final boolean hasFoodEffectTooltip;
   private final boolean hasCustomTooltip;

   public ConsumableItem(class_1793 properties) {
      super(properties);
      this.hasFoodEffectTooltip = false;
      this.hasCustomTooltip = false;
   }

   public ConsumableItem(class_1793 properties, boolean hasFoodEffectTooltip) {
      super(properties);
      this.hasFoodEffectTooltip = hasFoodEffectTooltip;
      this.hasCustomTooltip = false;
   }

   public ConsumableItem(class_1793 properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
      super(properties);
      this.hasFoodEffectTooltip = hasFoodEffectTooltip;
      this.hasCustomTooltip = hasCustomTooltip;
   }

   public class_1799 method_7861(class_1799 stack, class_1937 level, class_1309 consumer) {
      if (!level.field_9236) {
         this.affectConsumer(stack, level, consumer);
      }

      class_1799 containerStack = stack.getRecipeRemainder();
      if (stack.method_57824(class_9334.field_50075) != null) {
         super.method_7861(stack, level, consumer);
      } else {
         class_1657 player = consumer instanceof class_1657 ? (class_1657)consumer : null;
         if (player instanceof class_3222) {
            class_174.field_1198.method_8821((class_3222)player, stack);
         }

         if (player != null) {
            player.method_7259(class_3468.field_15372.method_14956(this));
            if (!player.method_31549().field_7477) {
               stack.method_7934(1);
            }
         }
      }

      if (stack.method_7960()) {
         return containerStack;
      } else {
         if (consumer instanceof class_1657 playerx && !((class_1657)consumer).method_31549().field_7477 && !playerx.method_31548().method_7394(containerStack)
            )
          {
            playerx.method_7328(containerStack, false);
         }

         return stack;
      }
   }

   public void affectConsumer(class_1799 stack, class_1937 level, class_1309 consumer) {
   }

   public void method_7851(class_1799 stack, class_9635 context, List<class_2561> tooltip, class_1836 isAdvanced) {
      if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
         if (this.hasCustomTooltip) {
            class_5250 textEmpty = TextUtils.getTranslation("tooltip." + class_7923.field_41178.method_10221(this).method_12832());
            tooltip.add(textEmpty.method_27692(class_124.field_1078));
         }

         if (this.hasFoodEffectTooltip) {
            TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, context.method_59531());
         }
      }
   }
}
