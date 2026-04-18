package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_3956;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ICraftingContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public class CraftingContainerRecipeTransferHandlerServer {
   private CraftingContainerRecipeTransferHandlerServer() {
   }

   public static void setItems(
      class_1657 player,
      class_2960 recipeId,
      class_3956<?> recipeType,
      Map<Integer, Integer> slotIdMap,
      List<Integer> craftingSlots,
      List<Integer> inventorySlots,
      boolean maxTransfer
   ) {
      if (player.field_7512 instanceof StorageContainerMenuBase<?> container) {
         HashMap var14 = new HashMap(slotIdMap.size());

         for (Entry<Integer, Integer> entry : slotIdMap.entrySet()) {
            class_1735 slot = container.method_7611(entry.getValue());
            class_1799 slotStack = slot.method_7677();
            if (slotStack.method_7960()) {
               return;
            }

            class_1799 stack = slotStack.method_7972();
            stack.method_7939(1);
            var14.put(entry.getKey(), stack);
         }

         Map<Integer, class_1799> toTransfer = removeItemsFromInventory(player, container, var14, craftingSlots, inventorySlots, maxTransfer);
         if (!toTransfer.isEmpty()) {
            List<class_1799> clearedCraftingItems = clearAndPutItemsIntoGrid(player, recipeId, recipeType, craftingSlots, container, toTransfer);
            putIntoInventory(player, inventorySlots, container, clearedCraftingItems);
            container.method_7623();
         }
      }
   }

   private static void putIntoInventory(
      class_1657 player, List<Integer> inventorySlots, StorageContainerMenuBase<?> container, List<class_1799> clearedCraftingItems
   ) {
      for (class_1799 oldCraftingItem : clearedCraftingItems) {
         int added = addStack(container, inventorySlots, oldCraftingItem);
         if (added < oldCraftingItem.method_7947() && !player.method_31548().method_7394(oldCraftingItem)) {
            player.method_7328(oldCraftingItem, false);
         }
      }
   }

   private static List<class_1799> clearAndPutItemsIntoGrid(
      class_1657 player, class_2960 recipeId, class_3956<?> recipeType, List<Integer> craftingSlots, class_1703 container, Map<Integer, class_1799> toTransfer
   ) {
      List<class_1799> clearedCraftingItems = new ArrayList<>();
      int minSlotStackLimit = Integer.MAX_VALUE;

      for (int craftingSlotNumberIndex = 0; craftingSlotNumberIndex < craftingSlots.size(); craftingSlotNumberIndex++) {
         int craftingSlotNumber = craftingSlots.get(craftingSlotNumberIndex);
         class_1735 craftingSlot = container.method_7611(craftingSlotNumber);
         if (craftingSlot.method_7674(player)) {
            if (craftingSlot.method_7681()) {
               class_1799 craftingItem = craftingSlot.method_7671(craftingSlot.method_7677().method_7947());
               clearedCraftingItems.add(craftingItem);
            }

            class_1799 transferItem = toTransfer.get(craftingSlotNumberIndex);
            if (transferItem != null) {
               int slotStackLimit = craftingSlot.method_7676(transferItem);
               minSlotStackLimit = Math.min(slotStackLimit, minSlotStackLimit);
            }
         }
      }

      putItemIntoGrid(container, toTransfer, clearedCraftingItems, minSlotStackLimit);
      if (container instanceof StorageContainerMenuBase<?> storageContainerMenu) {
         storageContainerMenu.getOpenOrFirstCraftingContainer(recipeType).ifPresent(c -> ((ICraftingContainer)c).setRecipeUsed(recipeId));
      }

      return clearedCraftingItems;
   }

   private static void putItemIntoGrid(class_1703 container, Map<Integer, class_1799> toTransfer, List<class_1799> clearedCraftingItems, int minSlotStackLimit) {
      for (Entry<Integer, class_1799> entry : toTransfer.entrySet()) {
         Integer craftingSlotIndex = entry.getKey();
         class_1735 slot = container.method_7611(craftingSlotIndex);
         class_1799 stack = entry.getValue();
         if (slot.method_7680(stack)) {
            if (stack.method_7947() > minSlotStackLimit) {
               class_1799 remainder = stack.method_7971(stack.method_7947() - minSlotStackLimit);
               clearedCraftingItems.add(remainder);
            }

            slot.method_7673(stack);
         } else {
            clearedCraftingItems.add(stack);
         }
      }
   }

   private static Map<Integer, class_1799> removeItemsFromInventory(
      class_1657 player,
      StorageContainerMenuBase<?> container,
      Map<Integer, class_1799> required,
      List<Integer> craftingSlots,
      List<Integer> inventorySlots,
      boolean maxTransfer
   ) {
      Map<Integer, class_1799> result = new HashMap<>(required.size());

      boolean noItemsFound;
      do {
         Map<class_1735, class_1799> originalSlotContents = new HashMap<>();
         Map<Integer, class_1799> foundItemsInSet = new HashMap<>(required.size());
         noItemsFound = true;

         for (Entry<Integer, class_1799> entry : required.entrySet()) {
            class_1799 requiredStack = entry.getValue().method_7972();
            class_1735 slot = getSlotWithStack(container, requiredStack, craftingSlots, inventorySlots);
            boolean itemFound = slot != null && !slot.method_7677().method_7960() && slot.method_7674(player);
            class_1799 resultItemStack = result.get(entry.getKey());
            boolean resultItemStackLimitReached = resultItemStack != null && resultItemStack.method_7947() == resultItemStack.method_7914();
            if (!itemFound || resultItemStackLimitReached) {
               for (Entry<class_1735, class_1799> slotEntry : originalSlotContents.entrySet()) {
                  class_1799 stack = slotEntry.getValue();
                  slotEntry.getKey().method_7673(stack);
               }

               return result;
            }

            if (!originalSlotContents.containsKey(slot)) {
               originalSlotContents.put(slot, slot.method_7677().method_7972());
            }

            class_1799 removedItemStack = slot.method_7671(1);
            foundItemsInSet.put(entry.getKey(), removedItemStack);
            noItemsFound = false;
         }

         for (Entry<Integer, class_1799> entry : foundItemsInSet.entrySet()) {
            class_1799 resultItemStackx = result.get(entry.getKey());
            if (resultItemStackx == null) {
               result.put(entry.getKey(), entry.getValue());
            } else {
               resultItemStackx.method_7933(1);
            }
         }
      } while (maxTransfer && !noItemsFound);

      return result;
   }

   @Nullable
   private static class_1735 getSlotWithStack(
      StorageContainerMenuBase<?> container, class_1799 stack, List<Integer> craftingSlots, List<Integer> inventorySlots
   ) {
      class_1735 slot = getSlotWithStack(container, craftingSlots, stack);
      if (slot == null) {
         slot = getSlotWithStack(container, inventorySlots, stack);
      }

      return slot;
   }

   private static int addStack(StorageContainerMenuBase<?> container, Collection<Integer> slotIndexes, class_1799 stack) {
      int added = 0;

      for (Integer slotIndex : slotIndexes) {
         if (slotIndex >= 0 && slotIndex < getTotalSlotsSize(container)) {
            class_1735 slot = container.method_7611(slotIndex);
            class_1799 inventoryStack = slot.method_7677();
            if (!inventoryStack.method_7960() && inventoryStack.method_7946() && class_1799.method_31577(inventoryStack, stack)) {
               int remain = stack.method_7947() - added;
               int maxStackSize = slot.method_7676(inventoryStack);
               int space = maxStackSize - inventoryStack.method_7947();
               if (space > 0) {
                  if (space >= remain) {
                     inventoryStack.method_7933(remain);
                     return stack.method_7947();
                  }

                  inventoryStack.method_7939(maxStackSize);
                  added += space;
               }
            }
         }
      }

      if (added >= stack.method_7947()) {
         return added;
      } else {
         for (Integer slotIndexx : slotIndexes) {
            if (slotIndexx >= 0 && slotIndexx < getTotalSlotsSize(container)) {
               class_1735 slot = container.method_7611(slotIndexx);
               class_1799 inventoryStack = slot.method_7677();
               if (inventoryStack.method_7960()) {
                  class_1799 stackToAdd = stack.method_7972();
                  stackToAdd.method_7939(stack.method_7947() - added);
                  slot.method_7673(stackToAdd);
                  return stack.method_7947();
               }
            }
         }

         return added;
      }
   }

   @Nullable
   private static class_1735 getSlotWithStack(StorageContainerMenuBase<?> container, Iterable<Integer> slotNumbers, class_1799 itemStack) {
      for (Integer slotNumber : slotNumbers) {
         if (slotNumber >= 0 && slotNumber < getTotalSlotsSize(container)) {
            class_1735 slot = container.method_7611(slotNumber);
            class_1799 slotStack = slot.method_7677();
            if (class_1799.method_31577(itemStack, slotStack)) {
               return slot;
            }
         }
      }

      return null;
   }

   private static int getTotalSlotsSize(StorageContainerMenuBase<?> container) {
      return container.upgradeSlots.size() + container.realInventorySlots.size();
   }
}
