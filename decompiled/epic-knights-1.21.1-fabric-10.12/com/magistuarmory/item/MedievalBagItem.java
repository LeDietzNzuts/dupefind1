package com.magistuarmory.item;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1271;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_9288;
import net.minecraft.class_9334;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;

public class MedievalBagItem extends class_1792 {
   public MedievalBagItem() {
      super(new class_1793().method_7889(1).method_57349(class_9334.field_49622, class_9288.field_49334));
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      class_1271<class_1799> result = super.method_7836(level, player, hand);
      if (!level.method_8608() && !result.method_5467().method_23665()) {
         class_1799 bagstack = player.method_5998(hand);
         player.method_31548().method_5447(player.method_31548().method_7395(bagstack), class_1799.field_8037);
         getContents(bagstack).method_59712().forEach(s -> {
            if (!player.method_7270(s)) {
               level.method_8649(new class_1542(level, player.method_23317(), player.method_23318(), player.method_23321(), s));
            }
         });
         return new class_1271(class_1269.field_5812, bagstack);
      } else {
         return result;
      }
   }

   public void method_7851(class_1799 stack, class_9635 tooltipContext, List<class_2561> tooltip, class_1836 flag) {
      super.method_7851(stack, tooltipContext, tooltip, flag);
      tooltip.add(class_2561.method_43471("medieval_bag.rightclick").method_27696(class_2583.field_24360.method_10977(class_124.field_1078).method_10978(true)));
   }

   public static void setContents(class_1799 bagstack, List<class_1799> stacks) {
      bagstack.method_57379(class_9334.field_49622, class_9288.method_57493(stacks));
   }

   public static class_9288 getContents(class_1799 bagstack) {
      return (class_9288)bagstack.method_57824(class_9334.field_49622);
   }
}
