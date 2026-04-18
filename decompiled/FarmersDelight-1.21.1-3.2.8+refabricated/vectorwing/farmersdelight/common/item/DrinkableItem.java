package vectorwing.farmersdelight.common.item;

import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1839;
import net.minecraft.class_1937;
import net.minecraft.class_4174;
import net.minecraft.class_5328;
import net.minecraft.class_9334;
import net.minecraft.class_1792.class_1793;

public class DrinkableItem extends ConsumableItem {
   public DrinkableItem(class_1793 properties) {
      super(properties);
   }

   public DrinkableItem(class_1793 properties, boolean hasFoodEffectTooltip) {
      super(properties, hasFoodEffectTooltip);
   }

   public DrinkableItem(class_1793 properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
      super(properties, hasPotionEffectTooltip, hasCustomTooltip);
   }

   public class_1839 method_7853(class_1799 stack) {
      return class_1839.field_8946;
   }

   public int method_7881(class_1799 stack, class_1309 entity) {
      return 32;
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      class_1799 heldStack = player.method_5998(hand);
      if (heldStack.method_57824(class_9334.field_50075) != null) {
         if (player.method_7332(((class_4174)heldStack.method_57824(class_9334.field_50075)).comp_2493())) {
            player.method_6019(hand);
            return class_1271.method_22428(heldStack);
         } else {
            return class_1271.method_22431(heldStack);
         }
      } else {
         return class_5328.method_29282(level, player, hand);
      }
   }
}
