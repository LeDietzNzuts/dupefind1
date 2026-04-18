package com.natamus.youritemsaresafe_common_fabric.util;

import com.natamus.collective_common_fabric.functions.CompareItemFunctions;
import com.natamus.collective_common_fabric.functions.MessageFunctions;
import com.natamus.youritemsaresafe_common_fabric.config.ConfigHandler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1890;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_9701;

public class Util {
   public static int processCheck(List<class_1799> itemStacks, int itemsLeft, String compare, int decrease) {
      for (class_1799 itemStack : itemStacks) {
         if (itemsLeft <= 0) {
            break;
         }

         int count = itemStack.method_7947();
         if (comparePassed(compare, itemStack)) {
            while (count > 0 && itemsLeft > 0) {
               itemsLeft -= decrease;
               itemStack.method_7939(--count);
            }
         }
      }

      return itemsLeft;
   }

   public static boolean comparePassed(String compare, class_1799 itemStack) {
      return switch (compare) {
         case "log" -> CompareItemFunctions.isLog(itemStack);
         case "plank" -> CompareItemFunctions.isPlank(itemStack);
         case "chest" -> CompareItemFunctions.isChest(itemStack);
         case "stone" -> CompareItemFunctions.isStone(itemStack);
         case "slab" -> CompareItemFunctions.isSlab(itemStack);
         default -> false;
      };
   }

   public static int processLogCheck(List<class_1799> itemStacks, int planksLeft) {
      return processCheck(itemStacks, planksLeft, "log", 4);
   }

   public static int processPlankCheck(List<class_1799> itemStacks, int planksLeft) {
      return processCheck(itemStacks, planksLeft, "plank", 1);
   }

   public static int processChestCheck(List<class_1799> itemStacks, int planksLeft) {
      return processCheck(itemStacks, planksLeft, "chest", 8);
   }

   public static int processStoneCheck(List<class_1799> itemStacks, int stoneLeft) {
      return processCheck(itemStacks, stoneLeft, "stone", 1);
   }

   public static int processSlabCheck(List<class_1799> itemStacks, int stoneLeft) {
      return processCheck(itemStacks, stoneLeft, "slab", 1);
   }

   public static boolean hasCurseOfVanishing(class_1799 itemStack) {
      return class_1890.method_60142(itemStack, class_9701.field_51655);
   }

   public static List<class_1799> getInventoryItems(class_1657 player) {
      List<class_1799> itemStacks = new ArrayList<>(player.method_31548().field_7547);
      itemStacks.removeIf(Util::hasCurseOfVanishing);
      return itemStacks;
   }

   public static void failureMessage(class_1657 player, int planksLeft, int stoneLeft, int planksNeeded, int stoneNeeded) {
      if (ConfigHandler.sendMessageOnCreationFailure) {
         String failureString = ConfigHandler.creationFailureMessage;
         failureString = failureString.replaceAll("%plankamount%", planksLeft + "").replaceAll("%stoneamount%", stoneLeft + "");
         MessageFunctions.sendMessage(player, failureString, class_124.field_1061, true);
      }

      class_1937 level = player.method_37908();
      class_243 vec = player.method_19538();
      if (planksLeft != planksNeeded) {
         class_1542 planks = new class_1542(
            level, vec.field_1352, vec.field_1351 + 1.0, vec.field_1350, new class_1799(class_1802.field_8118, planksNeeded - planksLeft)
         );
         level.method_8649(planks);
      }

      if (stoneLeft != stoneNeeded) {
         class_1542 stones = new class_1542(
            level, vec.field_1352, vec.field_1351 + 1.0, vec.field_1350, new class_1799(class_1802.field_20391, stoneNeeded - stoneLeft)
         );
         level.method_8649(stones);
      }

      deathCoordinatesMessage(player);
   }

   public static void successMessage(class_1657 player) {
      if (ConfigHandler.sendMessageOnCreationSuccess) {
         MessageFunctions.sendMessage(player, ConfigHandler.creationSuccessMessage, class_124.field_1077, true);
      }

      deathCoordinatesMessage(player);
   }

   public static void deathCoordinatesMessage(class_1657 player) {
      if (ConfigHandler.sendDeathCoordinatesInChat) {
         class_2338 pPos = player.method_24515();
         String deathLocationString = " Death Coordinates; x: " + pPos.method_10263() + ", y: " + pPos.method_10264() + ", z: " + pPos.method_10260() + ".";
         MessageFunctions.sendMessage(player, deathLocationString, class_124.field_1080);
      }
   }
}
