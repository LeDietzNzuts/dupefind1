package net.p3pp3rf1y.sophisticatedcore.inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;

public class InventoryHandlerSlotTracker implements ISlotTracker {
   private final Map<ItemStackKey, Set<Integer>> fullStackSlots = new HashMap<>();
   private final Map<Integer, ItemStackKey> fullSlotStacks = new HashMap<>();
   private final Map<ItemStackKey, Set<Integer>> partiallyFilledStackSlots = new HashMap<>();
   private final Map<Integer, ItemStackKey> partiallyFilledSlotStacks = new HashMap<>();
   private final Map<class_1792, Set<ItemStackKey>> itemStackKeys = new HashMap<>();
   private final Set<Integer> emptySlots = new TreeSet<>();
   private final MemorySettingsCategory memorySettings;
   private final Map<class_1792, Set<Integer>> filterItemSlots;
   private Consumer<ItemStackKey> onAddStackKey = sk -> {};
   private Consumer<ItemStackKey> onRemoveStackKey = sk -> {};
   private Runnable onAddFirstEmptySlot = () -> {};
   private Runnable onRemoveLastEmptySlot = () -> {};
   private BooleanSupplier shouldInsertIntoEmpty = () -> true;

   public InventoryHandlerSlotTracker(MemorySettingsCategory memorySettings, Map<class_1792, Set<Integer>> filterItemSlots) {
      this.memorySettings = memorySettings;
      this.filterItemSlots = filterItemSlots;
   }

   @Override
   public void setShouldInsertIntoEmpty(BooleanSupplier shouldInsertIntoEmpty) {
      this.shouldInsertIntoEmpty = shouldInsertIntoEmpty;
   }

   public void addPartiallyFilled(int slot, class_1799 stack) {
      ItemStackKey stackKey = ItemStackKey.of(stack);
      this.partiallyFilledStackSlots.computeIfAbsent(stackKey, k -> {
         if (!this.fullStackSlots.containsKey(k)) {
            this.onAddStackKey.accept(k);
         }

         return new TreeSet<>();
      }).add(slot);
      this.partiallyFilledSlotStacks.put(slot, stackKey);
      this.itemStackKeys.computeIfAbsent(stack.method_7909(), i -> new HashSet<>()).add(stackKey);
   }

   @Override
   public Set<ItemStackKey> getFullStacks() {
      return this.fullStackSlots.keySet();
   }

   @Override
   public Set<ItemStackKey> getPartialStacks() {
      return this.partiallyFilledStackSlots.keySet();
   }

   @Override
   public Set<class_1792> getItems() {
      return this.itemStackKeys.keySet();
   }

   public void addFull(int slot, class_1799 stack) {
      ItemStackKey stackKey = ItemStackKey.of(stack);
      this.fullStackSlots.computeIfAbsent(stackKey, k -> {
         if (!this.partiallyFilledStackSlots.containsKey(k)) {
            this.onAddStackKey.accept(k);
         }

         return new HashSet<>();
      }).add(slot);
      this.fullSlotStacks.put(slot, stackKey);
      this.itemStackKeys.computeIfAbsent(stack.method_7909(), i -> new HashSet<>()).add(stackKey);
   }

   public void removePartiallyFilled(int slot) {
      if (this.partiallyFilledSlotStacks.containsKey(slot)) {
         ItemStackKey stackKey = this.partiallyFilledSlotStacks.remove(slot);
         Set<Integer> partialSlots = this.partiallyFilledStackSlots.get(stackKey);
         if (partialSlots == null) {
            SophisticatedCore.LOGGER.error("Unstable ItemStack detected in slot tracking: {}", stackKey != null ? stackKey.stack().toString() : "null");
         } else {
            partialSlots.remove(slot);
         }

         if (partialSlots == null || partialSlots.isEmpty()) {
            this.partiallyFilledStackSlots.remove(stackKey);
            if (!this.fullStackSlots.containsKey(stackKey)) {
               this.onStackKeyRemoved(stackKey);
            }
         }
      }
   }

   private void removeFull(int slot) {
      if (this.fullSlotStacks.containsKey(slot)) {
         ItemStackKey stackKey = this.fullSlotStacks.remove(slot);
         Set<Integer> fullSlots = this.fullStackSlots.get(stackKey);
         if (fullSlots == null) {
            SophisticatedCore.LOGGER.error("Unstable ItemStack detected in slot tracking: {}", stackKey != null ? stackKey.stack().toString() : "null");
         } else {
            fullSlots.remove(slot);
         }

         if (fullSlots == null || fullSlots.isEmpty()) {
            this.fullStackSlots.remove(stackKey);
            if (!this.partiallyFilledStackSlots.containsKey(stackKey)) {
               this.onStackKeyRemoved(stackKey);
            }
         }
      }
   }

   private void onStackKeyRemoved(ItemStackKey stackKey) {
      this.itemStackKeys.computeIfPresent(stackKey.getStack().method_7909(), (i, stackKeys) -> {
         stackKeys.remove(stackKey);
         return stackKeys;
      });
      if (this.itemStackKeys.containsKey(stackKey.getStack().method_7909()) && this.itemStackKeys.get(stackKey.getStack().method_7909()).isEmpty()) {
         this.itemStackKeys.remove(stackKey.getStack().method_7909());
      }

      this.onRemoveStackKey.accept(stackKey);
   }

   @Override
   public void removeAndSetSlotIndexes(InventoryHandler inventoryHandler, int slot, class_1799 stack) {
      if (stack.method_7960()) {
         this.removePartiallyFilled(slot);
         this.removeFull(slot);
         this.addEmptySlot(slot);
      } else {
         if (this.emptySlots.contains(slot)) {
            this.removeEmpty(slot);
         }

         if (this.isPartiallyFilled(inventoryHandler, slot, stack)) {
            this.setPartiallyFilled(slot, stack);
         } else {
            this.setFull(slot, stack);
         }
      }
   }

   private void setFull(int slot, class_1799 stack) {
      boolean containsSlot = this.fullSlotStacks.containsKey(slot);
      if (!containsSlot || this.fullSlotStacks.get(slot).hashCodeNotEquals(stack)) {
         if (containsSlot) {
            this.removeFull(slot);
         }

         this.addFull(slot, stack);
      }

      if (this.partiallyFilledSlotStacks.containsKey(slot)) {
         this.removePartiallyFilled(slot);
      }
   }

   private void setPartiallyFilled(int slot, class_1799 stack) {
      boolean containsSlot = this.partiallyFilledSlotStacks.containsKey(slot);
      if (!containsSlot || this.partiallyFilledSlotStacks.get(slot).hashCodeNotEquals(stack)) {
         if (containsSlot) {
            this.removePartiallyFilled(slot);
         }

         this.addPartiallyFilled(slot, stack);
      }

      if (this.fullSlotStacks.containsKey(slot)) {
         this.removeFull(slot);
      }
   }

   private void removeEmpty(int slot) {
      this.emptySlots.remove(slot);
      if (this.emptySlots.isEmpty()) {
         this.onRemoveLastEmptySlot.run();
      }
   }

   private void set(InventoryHandler inventoryHandler, int slot, class_1799 stack) {
      if (stack.method_7960()) {
         this.addEmptySlot(slot);
      } else if (this.isPartiallyFilled(inventoryHandler, slot, stack)) {
         this.addPartiallyFilled(slot, stack);
      } else {
         this.addFull(slot, stack);
      }
   }

   private void addEmptySlot(int slot) {
      this.emptySlots.add(slot);
      if (this.emptySlots.size() == 1) {
         this.onAddFirstEmptySlot.run();
      }
   }

   @Override
   public void clear() {
      this.partiallyFilledStackSlots.clear();
      this.partiallyFilledSlotStacks.clear();
   }

   @Override
   public void refreshSlotIndexesFrom(InventoryHandler itemHandler) {
      this.fullStackSlots.keySet().forEach(sk -> this.onRemoveStackKey.accept(sk));
      this.fullStackSlots.clear();
      this.fullSlotStacks.clear();
      this.partiallyFilledStackSlots.keySet().forEach(sk -> this.onRemoveStackKey.accept(sk));
      this.partiallyFilledStackSlots.clear();
      this.partiallyFilledSlotStacks.clear();
      this.itemStackKeys.clear();
      this.emptySlots.clear();
      this.onRemoveLastEmptySlot.run();

      for (int slot = 0; slot < itemHandler.getSlotCount(); slot++) {
         class_1799 stack = itemHandler.getStackInSlot(slot);
         this.set(itemHandler, slot, stack);
      }
   }

   private boolean isPartiallyFilled(InventoryHandler itemHandler, int slot, class_1799 stack) {
      return stack.method_7947() < itemHandler.getStackLimit(slot, stack);
   }

   @Override
   public class_1799 insertItemIntoHandler(
      InventoryHandler itemHandler, ISlotTracker.IItemHandlerInserter inserter, UnaryOperator<class_1799> overflowHandler, class_1799 stack, boolean simulate
   ) {
      if (this.emptySlots.isEmpty() && !this.itemStackKeys.containsKey(stack.method_7909())) {
         return stack;
      } else {
         ItemStackKey stackKey = ItemStackKey.of(stack);
         class_1799 remainingStack = this.handleOverflow(overflowHandler, stackKey, stack);
         if (remainingStack.method_7960()) {
            return remainingStack;
         } else {
            remainingStack = this.insertIntoSlotsThatMatchStack(inserter, remainingStack, simulate, stackKey);
            if (!remainingStack.method_7960()) {
               remainingStack = this.insertIntoEmptySlots(inserter, remainingStack, simulate);
            }

            if (!remainingStack.method_7960()) {
               remainingStack = this.handleOverflow(overflowHandler, stackKey, remainingStack);
            }

            return remainingStack;
         }
      }
   }

   @Override
   public class_1799 insertItemIntoHandler(
      InventoryHandler itemHandler,
      ISlotTracker.IItemHandlerInserter inserter,
      UnaryOperator<class_1799> overflowHandler,
      int slot,
      class_1799 stack,
      boolean simulate
   ) {
      return this.insertItemIntoHandler(itemHandler, inserter, overflowHandler, stack, simulate);
   }

   @Override
   public void registerListeners(
      Consumer<ItemStackKey> onAddStackKey, Consumer<ItemStackKey> onRemoveStackKey, Runnable onAddFirstEmptySlot, Runnable onRemoveLastEmptySlot
   ) {
      this.onAddStackKey = onAddStackKey;
      this.onRemoveStackKey = onRemoveStackKey;
      this.onAddFirstEmptySlot = onAddFirstEmptySlot;
      this.onRemoveLastEmptySlot = onRemoveLastEmptySlot;
   }

   @Override
   public void unregisterStackKeyListeners() {
      this.onAddStackKey = sk -> {};
      this.onRemoveStackKey = sk -> {};
   }

   @Override
   public boolean hasEmptySlots() {
      return this.shouldInsertIntoEmpty.getAsBoolean() && !this.emptySlots.isEmpty();
   }

   private class_1799 handleOverflow(UnaryOperator<class_1799> overflowHandler, ItemStackKey stackKey, class_1799 remainingStack) {
      if (this.fullStackSlots.containsKey(stackKey) && !this.fullStackSlots.get(stackKey).isEmpty()) {
         remainingStack = overflowHandler.apply(remainingStack);
      }

      return remainingStack;
   }

   private class_1799 insertIntoSlotsThatMatchStack(ISlotTracker.IItemHandlerInserter inserter, class_1799 stack, boolean simulate, ItemStackKey stackKey) {
      class_1799 remainingStack = stack;
      Set<Integer> slots = this.partiallyFilledStackSlots.get(stackKey);
      if (slots != null && !slots.isEmpty()) {
         int sizeBefore = slots.size();
         int i = 0;

         while (this.partiallyFilledStackSlots.get(stackKey) != null && !this.partiallyFilledStackSlots.get(stackKey).isEmpty() && i++ < sizeBefore) {
            int matchingSlot = this.partiallyFilledStackSlots.get(stackKey).iterator().next();
            remainingStack = inserter.insertItem(matchingSlot, remainingStack, simulate);
            if (remainingStack.method_7960()) {
               break;
            }
         }

         return remainingStack;
      } else {
         return stack;
      }
   }

   private class_1799 insertIntoEmptySlots(ISlotTracker.IItemHandlerInserter inserter, class_1799 stack, boolean simulate) {
      class_1799 remainingStack = this.insertIntoEmptyMemorySlots(inserter, simulate, stack);
      remainingStack = this.insertIntoEmptyFilterSlots(inserter, simulate, remainingStack);
      if (this.shouldInsertIntoEmpty.getAsBoolean() && !remainingStack.method_7960()) {
         int sizeBefore = this.emptySlots.size();
         int i = 0;

         while (!this.emptySlots.isEmpty() && i++ < sizeBefore) {
            Iterator<Integer> it = this.emptySlots.iterator();

            int slot;
            for (slot = it.next(); this.memorySettings.isSlotSelected(slot); slot = it.next()) {
               if (!it.hasNext()) {
                  return remainingStack;
               }
            }

            remainingStack = inserter.insertItem(slot, remainingStack, simulate);
            if (remainingStack.method_7960()) {
               break;
            }
         }
      }

      return remainingStack;
   }

   private class_1799 insertIntoEmptyFilterSlots(ISlotTracker.IItemHandlerInserter inserter, boolean simulate, class_1799 remainingStack) {
      class_1792 item = remainingStack.method_7909();
      if (this.filterItemSlots.containsKey(item)) {
         for (int filterSlot : this.filterItemSlots.get(item)) {
            if (this.emptySlots.contains(filterSlot)) {
               remainingStack = inserter.insertItem(filterSlot, remainingStack, simulate);
               if (remainingStack.method_7960()) {
                  break;
               }
            }
         }
      }

      return remainingStack;
   }

   private class_1799 insertIntoEmptyMemorySlots(ISlotTracker.IItemHandlerInserter inserter, boolean simulate, class_1799 stack) {
      class_1799 remainingStack = stack;
      Map<class_1792, Set<Integer>> memoryFilterItemSlots = this.memorySettings.getFilterItemSlots();
      class_1792 item = stack.method_7909();
      if (memoryFilterItemSlots.containsKey(item)) {
         for (int memorySlot : memoryFilterItemSlots.get(item)) {
            if (this.emptySlots.contains(memorySlot)) {
               remainingStack = inserter.insertItem(memorySlot, remainingStack, simulate);
               if (remainingStack.method_7960()) {
                  break;
               }
            }
         }
      }

      Map<Integer, Set<Integer>> memoryFilterStackSlots = this.memorySettings.getFilterStackSlots();
      if (!memoryFilterStackSlots.isEmpty()) {
         int stackHash = class_1799.method_57355(remainingStack);
         if (memoryFilterStackSlots.containsKey(stackHash)) {
            for (int memorySlotx : memoryFilterStackSlots.get(stackHash)) {
               if (this.emptySlots.contains(memorySlotx)) {
                  remainingStack = inserter.insertItem(memorySlotx, remainingStack, simulate);
                  if (remainingStack.method_7960()) {
                     break;
                  }
               }
            }
         }
      }

      return remainingStack;
   }
}
