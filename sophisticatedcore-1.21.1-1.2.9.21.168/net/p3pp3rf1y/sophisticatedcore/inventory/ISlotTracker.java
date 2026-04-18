package net.p3pp3rf1y.sophisticatedcore.inventory;

import java.util.Collections;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import net.minecraft.class_1792;
import net.minecraft.class_1799;

public interface ISlotTracker {
   void setShouldInsertIntoEmpty(BooleanSupplier var1);

   Set<ItemStackKey> getFullStacks();

   Set<ItemStackKey> getPartialStacks();

   Set<class_1792> getItems();

   void removeAndSetSlotIndexes(InventoryHandler var1, int var2, class_1799 var3);

   void clear();

   void refreshSlotIndexesFrom(InventoryHandler var1);

   class_1799 insertItemIntoHandler(
      InventoryHandler var1, ISlotTracker.IItemHandlerInserter var2, UnaryOperator<class_1799> var3, class_1799 var4, boolean var5
   );

   class_1799 insertItemIntoHandler(
      InventoryHandler var1, ISlotTracker.IItemHandlerInserter var2, UnaryOperator<class_1799> var3, int var4, class_1799 var5, boolean var6
   );

   void registerListeners(Consumer<ItemStackKey> var1, Consumer<ItemStackKey> var2, Runnable var3, Runnable var4);

   void unregisterStackKeyListeners();

   boolean hasEmptySlots();

   public interface IItemHandlerInserter {
      class_1799 insertItem(int var1, class_1799 var2, boolean var3);
   }

   public static class Noop implements ISlotTracker {
      @Override
      public void setShouldInsertIntoEmpty(BooleanSupplier shouldInsertIntoEmpty) {
      }

      @Override
      public Set<ItemStackKey> getFullStacks() {
         return Collections.emptySet();
      }

      @Override
      public Set<ItemStackKey> getPartialStacks() {
         return Collections.emptySet();
      }

      @Override
      public Set<class_1792> getItems() {
         return Collections.emptySet();
      }

      @Override
      public void removeAndSetSlotIndexes(InventoryHandler inventoryHandler, int slot, class_1799 stack) {
      }

      @Override
      public void clear() {
      }

      @Override
      public void refreshSlotIndexesFrom(InventoryHandler itemHandler) {
      }

      @Override
      public class_1799 insertItemIntoHandler(
         InventoryHandler itemHandler,
         ISlotTracker.IItemHandlerInserter inserter,
         UnaryOperator<class_1799> overflowHandler,
         class_1799 stack,
         boolean simulate
      ) {
         return stack;
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
         return inserter.insertItem(slot, stack, simulate);
      }

      @Override
      public void registerListeners(
         Consumer<ItemStackKey> onAddStackKey, Consumer<ItemStackKey> onRemoveStackKey, Runnable onAddFirstEmptySlot, Runnable onRemoveLastEmptySlot
      ) {
      }

      @Override
      public void unregisterStackKeyListeners() {
      }

      @Override
      public boolean hasEmptySlots() {
         return false;
      }
   }
}
