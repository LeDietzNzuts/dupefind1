package dev.architectury.hooks.item.fabric;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class ItemStackHooksImpl {
   public static boolean hasCraftingRemainingItem(class_1799 stack) {
      return stack.method_7909().method_7857();
   }

   public static class_1799 getCraftingRemainingItem(class_1799 stack) {
      if (!hasCraftingRemainingItem(stack)) {
         return class_1799.field_8037;
      } else {
         class_1792 item = stack.method_7909().method_7858();
         return item != null && item != class_1802.field_8162 ? item.method_7854() : class_1799.field_8037;
      }
   }
}
