package net.caffeinemc.mods.lithium.common.hopper;

import java.util.Map;
import net.caffeinemc.mods.lithium.api.inventory.LithiumTransferConditionInventory;
import net.caffeinemc.mods.lithium.common.util.DirectionConstants;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.caffeinemc.mods.lithium.common.world.blockentity.BlockEntityGetter;
import net.minecraft.class_1258;
import net.minecraft.class_1263;
import net.minecraft.class_1278;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2614;
import net.minecraft.class_2621;
import net.minecraft.class_2818;
import net.minecraft.class_3532;
import org.jetbrains.annotations.Nullable;

public class HopperHelper {
   public static boolean tryMoveSingleItem(class_1263 to, class_1799 stack, @Nullable class_2350 fromDirection) {
      class_1799 transferChecker;
      if (((LithiumTransferConditionInventory)to).lithium$itemInsertionTestRequiresStackSize1()) {
         transferChecker = stack.method_7972();
         transferChecker.method_7939(1);
      } else {
         transferChecker = stack;
      }

      class_1278 toSided = to instanceof class_1278 ? (class_1278)to : null;
      if (toSided != null && fromDirection != null) {
         int[] slots = toSided.method_5494(fromDirection);

         for (int slotIndex = 0; slotIndex < slots.length; slotIndex++) {
            if (tryMoveSingleItem(to, toSided, stack, transferChecker, slots[slotIndex], fromDirection)) {
               return true;
            }
         }
      } else {
         int j = to.method_5439();

         for (int slot = 0; slot < j; slot++) {
            if (tryMoveSingleItem(to, toSided, stack, transferChecker, slot, fromDirection)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean tryMoveSingleItem(
      class_1263 to, @Nullable class_1278 toSided, class_1799 transferStack, class_1799 transferChecker, int targetSlot, @Nullable class_2350 fromDirection
   ) {
      class_1799 toStack = to.method_5438(targetSlot);
      if (to.method_5437(targetSlot, transferChecker) && (toSided == null || toSided.method_5492(targetSlot, transferChecker, fromDirection))) {
         if (toStack.method_7960()) {
            class_1799 singleItem = transferStack.method_7971(1);
            to.method_5447(targetSlot, singleItem);
            return true;
         }

         int toCount;
         if (toStack.method_7914() > (toCount = toStack.method_7947()) && to.method_5444() > toCount && class_1799.method_31577(toStack, transferStack)) {
            transferStack.method_7934(1);
            toStack.method_7933(1);
            return true;
         }
      }

      return false;
   }

   private static int calculateReducedSignalStrength(
      float contentWeight, int inventorySize, int inventoryMaxCountPerStack, int numOccupiedSlots, int itemStackCount, int itemStackMaxCount
   ) {
      int maxStackSize = Math.min(inventoryMaxCountPerStack, itemStackMaxCount);
      int newNumOccupiedSlots = numOccupiedSlots - (itemStackCount == 1 ? 1 : 0);
      float newContentWeight = contentWeight - 1.0F / maxStackSize;
      newContentWeight /= inventorySize;
      return class_3532.method_15375(newContentWeight * 14.0F) + (newNumOccupiedSlots > 0 ? 1 : 0);
   }

   public static ComparatorUpdatePattern determineComparatorUpdatePattern(class_1263 from, LithiumStackList fromStackList) {
      if (!(from instanceof class_2614) && from instanceof class_2621) {
         float contentWeight = 0.0F;
         int numOccupiedSlots = 0;

         for (int j = 0; j < from.method_5439(); j++) {
            class_1799 itemStack = from.method_5438(j);
            if (!itemStack.method_7960()) {
               int maxStackSize = Math.min(from.method_5444(), itemStack.method_7914());
               contentWeight += (float)itemStack.method_7947() / maxStackSize;
               numOccupiedSlots++;
            }
         }

         float var14 = contentWeight / from.method_5439();
         int originalSignalStrength = class_3532.method_15375(var14 * 14.0F) + (numOccupiedSlots > 0 ? 1 : 0);
         ComparatorUpdatePattern updatePattern = ComparatorUpdatePattern.NO_UPDATE;
         int[] availableSlots = from instanceof class_1278 ? ((class_1278)from).method_5494(class_2350.field_11033) : null;
         class_1278 sidedInventory = from instanceof class_1278 ? (class_1278)from : null;
         int fromSize = availableSlots != null ? availableSlots.length : from.method_5439();

         for (int i = 0; i < fromSize; i++) {
            int fromSlot = availableSlots != null ? availableSlots[i] : i;
            class_1799 itemStack = (class_1799)fromStackList.get(fromSlot);
            if (!itemStack.method_7960() && (sidedInventory == null || sidedInventory.method_5493(fromSlot, itemStack, class_2350.field_11033))) {
               int newSignalStrength = calculateReducedSignalStrength(
                  contentWeight, from.method_5439(), from.method_5444(), numOccupiedSlots, itemStack.method_7947(), itemStack.method_7914()
               );
               if (newSignalStrength != originalSignalStrength) {
                  updatePattern = updatePattern.thenDecrementUpdateIncrementUpdate();
               } else {
                  updatePattern = updatePattern.thenUpdate();
               }

               if (!updatePattern.isChainable()) {
                  break;
               }
            }
         }

         return updatePattern;
      } else {
         return ComparatorUpdatePattern.NO_UPDATE;
      }
   }

   public static class_1263 replaceDoubleInventory(class_1263 blockInventory) {
      if (blockInventory instanceof class_1258 doubleInventory) {
         class_1258 var2 = LithiumDoubleInventory.getLithiumInventory(doubleInventory);
         if (var2 != null) {
            return var2;
         }
      }

      return blockInventory;
   }

   public static void updateHopperOnUpdateSuppression(class_1937 level, class_2338 pos, int flags, class_2818 worldChunk, boolean stateChange) {
      if ((flags & 1) == 0 && stateChange) {
         Map<class_2338, class_2586> blockEntities = WorldHelper.areNeighborsWithinSameChunk(pos) ? worldChunk.method_12214() : null;
         if (blockEntities == null || !blockEntities.isEmpty()) {
            for (class_2350 direction : DirectionConstants.ALL) {
               class_2338 offsetPos = pos.method_10093(direction);
               if ((blockEntities != null ? blockEntities.get(offsetPos) : ((BlockEntityGetter)level).lithium$getLoadedExistingBlockEntity(offsetPos)) instanceof UpdateReceiver updateReceiver
                  )
                {
                  updateReceiver.lithium$invalidateCacheOnNeighborUpdate(direction == class_2350.field_11033);
               }
            }
         }
      }
   }
}
