package net.p3pp3rf1y.sophisticatedcore.inventory;

import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.SlotRange;
import net.p3pp3rf1y.sophisticatedcore.util.TriPredicate;
import org.apache.commons.lang3.function.TriFunction;

public interface IInventoryPartHandler {
   IInventoryPartHandler EMPTY = () -> "EMPTY";

   default int getSlotLimit(int slot) {
      return 0;
   }

   default boolean isSlotAccessible(int slot) {
      return false;
   }

   default int getStackLimit(int slot, class_1799 stack) {
      return 0;
   }

   default class_1799 extractItem(int slot, int amount, boolean simulate) {
      return class_1799.field_8037;
   }

   default class_1799 insertItem(int slot, class_1799 stack, boolean simulate, TriFunction<Integer, class_1799, Boolean, class_1799> insertSuper) {
      return stack;
   }

   default void setStackInSlot(int slot, class_1799 stack, BiConsumer<Integer, class_1799> setStackInSlotSuper) {
   }

   default boolean isItemValid(
      int slot, ItemVariant resource, int count, @Nullable class_1657 player, TriPredicate<Integer, ItemVariant, Integer> isItemValidSuper
   ) {
      return false;
   }

   default class_1799 getStackInSlot(int slot, IntFunction<class_1799> getStackInSlotSuper) {
      return class_1799.field_8037;
   }

   default boolean canBeReplaced() {
      return false;
   }

   default int getSlots() {
      return 0;
   }

   String getName();

   @Nullable
   default Pair<class_2960, class_2960> getNoItemIcon(int slot) {
      return null;
   }

   default class_1792 getFilterItem(int slot) {
      return class_1802.field_8162;
   }

   default void onSlotLimitChange() {
   }

   default Set<Integer> getNoSortSlots() {
      return Set.of();
   }

   default void onSlotFilterChanged(int slot) {
   }

   default boolean isFilterItem(class_1792 item) {
      return false;
   }

   default Map<class_1792, Set<Integer>> getFilterItems() {
      return Map.of();
   }

   default void onInit() {
   }

   default boolean isInfinite(int slot) {
      return false;
   }

   public static class Default implements IInventoryPartHandler {
      public static final String NAME = "default";
      private final InventoryHandler parent;
      private final int slots;

      public Default(InventoryHandler parent, int slots) {
         this.parent = parent;
         this.slots = slots;
      }

      @Override
      public int getSlotLimit(int slot) {
         return this.parent.getBaseSlotLimit();
      }

      @Override
      public int getStackLimit(int slot, class_1799 stack) {
         return this.parent.getBaseStackLimit(stack);
      }

      @Override
      public class_1799 extractItem(int slot, int amount, boolean simulate) {
         return this.parent.extractItemInternal(slot, amount, simulate);
      }

      @Override
      public class_1799 insertItem(int slot, class_1799 stack, boolean simulate, TriFunction<Integer, class_1799, Boolean, class_1799> insertSuper) {
         return (class_1799)insertSuper.apply(slot, stack, simulate);
      }

      @Override
      public void setStackInSlot(int slot, class_1799 stack, BiConsumer<Integer, class_1799> setStackInSlotSuper) {
         setStackInSlotSuper.accept(slot, stack);
      }

      @Override
      public boolean isItemValid(
         int slot, ItemVariant resource, int count, @Nullable class_1657 player, TriPredicate<Integer, ItemVariant, Integer> isItemValidSuper
      ) {
         return true;
      }

      @Override
      public class_1799 getStackInSlot(int slot, IntFunction<class_1799> getStackInSlotSuper) {
         return getStackInSlotSuper.apply(slot);
      }

      @Override
      public boolean canBeReplaced() {
         return true;
      }

      @Override
      public boolean isSlotAccessible(int slot) {
         return true;
      }

      @Override
      public int getSlots() {
         return this.slots;
      }

      @Override
      public String getName() {
         return "default";
      }
   }

   public interface Factory {
      IInventoryPartHandler create(InventoryHandler var1, SlotRange var2, Supplier<MemorySettingsCategory> var3);
   }
}
