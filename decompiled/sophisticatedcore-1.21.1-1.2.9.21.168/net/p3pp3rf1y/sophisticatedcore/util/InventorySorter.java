package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_6862;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;

public class InventorySorter {
   public static final Comparator<Entry<ItemStackKey, Integer>> BY_NAME = Comparator.comparing(
      o -> o.getKey().getStack().method_7964().getString().toLowerCase()
   );
   public static final Comparator<Entry<ItemStackKey, Integer>> BY_MOD = Comparator.<Entry<ItemStackKey, Integer>, String>comparing(o -> {
      class_2960 registryName = class_7923.field_41178.method_10221(o.getKey().getStack().method_7909());
      return registryName.method_12836();
   }).thenComparing(o -> o.getKey().getStack().method_7964().getString());
   public static final Comparator<Entry<ItemStackKey, Integer>> BY_COUNT = (first, second) -> {
      int ret = second.getValue().compareTo(first.getValue());
      return ret != 0 ? ret : getRegistryName(first.getKey()).compareTo(getRegistryName(second.getKey()));
   };
   public static final Comparator<Entry<ItemStackKey, Integer>> BY_TAGS = new Comparator<Entry<ItemStackKey, Integer>>() {
      public int compare(Entry<ItemStackKey, Integer> first, Entry<ItemStackKey, Integer> second) {
         class_1799 firstStack = first.getKey().getStack();
         class_1792 firstItem = firstStack.method_7909();
         class_1799 secondStack = second.getKey().getStack();
         class_1792 secondItem = secondStack.method_7909();
         if (firstItem == secondItem) {
            return 0;
         } else {
            int ret = this.compareTags(firstStack.method_40133().collect(Collectors.toSet()), secondStack.method_40133().collect(Collectors.toSet()));
            return ret != 0 ? ret : InventorySorter.getRegistryName(first.getKey()).compareTo(InventorySorter.getRegistryName(second.getKey()));
         }
      }

      private int compareTags(Set<class_6862<class_1792>> firstTags, Set<class_6862<class_1792>> secondTags) {
         int ret = Integer.compare(secondTags.size(), firstTags.size());
         if (ret != 0) {
            return ret;
         } else if (firstTags.size() == 1) {
            return firstTags.iterator().next().comp_327().method_12833(secondTags.iterator().next().comp_327());
         } else {
            ArrayList<class_6862<class_1792>> firstTagsSorted = new ArrayList<>(firstTags);
            ArrayList<class_6862<class_1792>> secondTagsSorted = new ArrayList<>(secondTags);
            firstTagsSorted.sort(Comparator.comparing(class_6862::comp_327));
            secondTagsSorted.sort(Comparator.comparing(class_6862::comp_327));

            for (int i = 0; i < firstTagsSorted.size(); i++) {
               ret = firstTagsSorted.get(i).comp_327().method_12833(secondTagsSorted.get(i).comp_327());
               if (ret != 0) {
                  return ret;
               }
            }

            return 0;
         }
      }
   };

   private InventorySorter() {
   }

   private static String getRegistryName(ItemStackKey itemStackKey) {
      return class_7923.field_41178.method_10221(itemStackKey.getStack().method_7909()).toString();
   }

   public static void sortHandler(IItemHandlerSimpleInserter handler, Comparator<? super Entry<ItemStackKey, Integer>> comparator, Set<Integer> noSortSlots) {
      Map<ItemStackKey, Integer> compactedStacks = InventoryHelper.getCompactedStacks(handler, noSortSlots, false);
      List<Entry<ItemStackKey, Integer>> sortedList = new ArrayList<>(compactedStacks.entrySet());
      sortedList.sort(comparator);
      int slots = handler.getSlotCount();
      sortIntoNoSortSlots(handler, noSortSlots, sortedList);
      sortIntoOtherSlots(handler, noSortSlots, sortedList, slots);
   }

   private static void sortIntoOtherSlots(
      IItemHandlerSimpleInserter handler, Set<Integer> noSortSlots, List<Entry<ItemStackKey, Integer>> sortedList, int slots
   ) {
      Iterator<Entry<ItemStackKey, Integer>> ite = sortedList.iterator();
      ItemStackKey current = null;
      int count = 0;

      for (int slot = 0; slot < slots; slot++) {
         if (!noSortSlots.contains(slot)) {
            if ((current == null || count <= 0) && ite.hasNext()) {
               Entry<ItemStackKey, Integer> entry = ite.next();
               current = entry.getKey();
               count = entry.getValue();
            }

            if (current != null && count > 0) {
               count -= placeStack(handler, current, count, slot, false);
            } else {
               emptySlot(handler, slot);
            }
         }
      }
   }

   private static void sortIntoNoSortSlots(IItemHandlerSimpleInserter handler, Set<Integer> noSortSlots, List<Entry<ItemStackKey, Integer>> sortedList) {
      Iterator<Entry<ItemStackKey, Integer>> it = sortedList.iterator();
      if (!noSortSlots.isEmpty()) {
         while (it.hasNext()) {
            Entry<ItemStackKey, Integer> entry = it.next();
            ItemStackKey current = entry.getKey();
            Integer count = entry.getValue();

            for (int slot : noSortSlots) {
               class_1799 slotStack = handler.getStackInSlot(slot);
               if (class_1799.method_31577(slotStack, current.getStack())) {
                  int placedCount = placeStack(handler, current, count, slot, true);
                  count = count - placedCount;
                  entry.setValue(count);
                  if (count <= 0) {
                     it.remove();
                     break;
                  }
               }
            }
         }
      }
   }

   private static void emptySlot(IItemHandlerSimpleInserter handler, int slot) {
      if (!handler.getStackInSlot(slot).method_7960()) {
         if (handler instanceof InventoryHandler inventoryHandler) {
            inventoryHandler.setSlotStack(slot, class_1799.field_8037);
         } else {
            handler.setStackInSlot(slot, class_1799.field_8037);
         }
      }
   }

   private static int placeStack(IItemHandlerSimpleInserter handler, ItemStackKey current, int count, int slot, boolean countWithCurrentStack) {
      return handler instanceof InventoryHandler inventoryHandler
         ? placeStack(
            current, count, slot, countWithCurrentStack, inventoryHandler::getStackLimit, inventoryHandler::getSlotStack, inventoryHandler::setSlotStack
         )
         : placeStack(current, count, slot, countWithCurrentStack, (s, stack) -> handler.getSlotLimit(s), handler::getStackInSlot, handler::setStackInSlot);
   }

   private static int placeStack(
      ItemStackKey current,
      int count,
      int slot,
      boolean countWithCurrentStack,
      InventorySorter.IStackLimitGetter stackLimitGetter,
      InventorySorter.ISlotStackGetter slotStackGetter,
      InventorySorter.ISlotStackSetter slotStackSetter
   ) {
      class_1799 copy = current.getStack().method_7972();
      int slotLimit = stackLimitGetter.getStackLimit(slot, copy);
      int existingCount = slotStackGetter.getSlotStack(slot).method_7947();
      if (countWithCurrentStack) {
         count += existingCount;
      }

      int countPlaced = Math.min(count, slotLimit);
      copy.method_7939(countPlaced);
      if (!class_1799.method_7973(slotStackGetter.getSlotStack(slot), copy)) {
         slotStackSetter.setSlotStack(slot, copy);
      }

      return countWithCurrentStack ? countPlaced - existingCount : countPlaced;
   }

   private interface ISlotStackGetter {
      class_1799 getSlotStack(int var1);
   }

   private interface ISlotStackSetter {
      void setSlotStack(int var1, class_1799 var2);
   }

   private interface IStackLimitGetter {
      int getStackLimit(int var1, class_1799 var2);
   }
}
