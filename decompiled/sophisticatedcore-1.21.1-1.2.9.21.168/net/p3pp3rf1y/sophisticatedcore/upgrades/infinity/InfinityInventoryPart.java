package net.p3pp3rf1y.sophisticatedcore.upgrades.infinity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.inventory.IInventoryPartHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.util.SlotRange;
import net.p3pp3rf1y.sophisticatedcore.util.TriPredicate;
import org.apache.commons.lang3.function.TriFunction;

public abstract class InfinityInventoryPart implements IInventoryPartHandler {
   private final InventoryHandler parent;
   private final SlotRange slotRange;
   private final int permissionLevel;
   private final Map<Integer, class_1799> cachedStacks = new HashMap<>();

   protected InfinityInventoryPart(InventoryHandler parent, SlotRange slotRange, int permissionLevel) {
      this.parent = parent;
      this.slotRange = slotRange;
      this.permissionLevel = permissionLevel;
   }

   @Override
   public boolean isInfinite(int slot) {
      return !this.parent.getSlotStack(slot).method_7960();
   }

   @Override
   public int getSlotLimit(int slot) {
      return Integer.MAX_VALUE;
   }

   @Override
   public boolean isItemValid(
      int slot, ItemVariant resource, int count, @Nullable class_1657 player, TriPredicate<Integer, ItemVariant, Integer> isItemValidSuper
   ) {
      return player != null
         && player.method_5687(this.permissionLevel)
         && this.parent.getSlotStack(slot).method_7960()
         && isItemValidSuper.test(slot, resource, count);
   }

   @Override
   public boolean isSlotAccessible(int slot) {
      return true;
   }

   @Override
   public int getStackLimit(int slot, class_1799 stack) {
      return Integer.MAX_VALUE;
   }

   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      return this.parent.getSlotStack(slot).method_46651(amount);
   }

   @Override
   public class_1799 insertItem(int slot, class_1799 stack, boolean simulate, TriFunction<Integer, class_1799, Boolean, class_1799> insertSuper) {
      if (!this.parent.getSlotStack(slot).method_7960()) {
         return stack;
      } else {
         this.cachedStacks.remove(slot);
         return (class_1799)insertSuper.apply(slot, stack, simulate);
      }
   }

   @Override
   public void setStackInSlot(int slot, class_1799 stack, BiConsumer<Integer, class_1799> setStackInSlotSuper) {
      if (this.parent.getSlotStack(slot).method_7960()) {
         this.cachedStacks.remove(slot);
         this.parent.setSlotStack(slot, stack);
      }
   }

   @Override
   public class_1799 getStackInSlot(int slot, IntFunction<class_1799> getStackInSlotSuper) {
      if (this.cachedStacks.containsKey(slot) && this.cachedStacks.get(slot).method_7960() != this.parent.getSlotStack(slot).method_7960()) {
         this.cachedStacks.remove(slot);
      }

      return this.cachedStacks.computeIfAbsent(slot, s -> this.parent.getSlotStack(s).method_46651(Integer.MAX_VALUE));
   }

   @Override
   public int getSlots() {
      return this.slotRange.numberOfSlots();
   }

   public static class Admin extends InfinityInventoryPart {
      public static final String NAME = "infinity";

      protected Admin(InventoryHandler parent, SlotRange slotRange) {
         super(parent, slotRange, 2);
      }

      @Override
      public String getName() {
         return "infinity";
      }
   }

   public static class Survival extends InfinityInventoryPart {
      public static final String NAME = "survival_infinity";

      protected Survival(InventoryHandler parent, SlotRange slotRange) {
         super(parent, slotRange, 0);
      }

      @Override
      public String getName() {
         return "survival_infinity";
      }
   }
}
