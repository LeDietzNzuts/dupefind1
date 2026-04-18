package net.p3pp3rf1y.sophisticatedcore.inventory;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;

public class SimpleItemStackHandler extends ItemStackHandler implements IItemHandlerSimpleInserter {
   public SimpleItemStackHandler(int size) {
      super(size);
   }

   @Override
   public boolean isItemValid(int slot, class_1799 stack) {
      return true;
   }

   @Override
   public boolean isItemValid(int slot, ItemVariant resource, int count) {
      return true;
   }

   @Override
   public class_1799 insertItem(int slot, @NotNull class_1799 stack, boolean simulate) {
      if (stack.method_7960()) {
         return class_1799.field_8037;
      } else if (!this.isItemValid(slot, stack)) {
         return stack;
      } else if (slot >= 0 && slot < this.getSlotCount()) {
         class_1799 existing = this.getStackInSlot(slot);
         int limit = this.getStackLimit(slot, ItemVariant.of(stack));
         if (!existing.method_7960()) {
            if (!class_1799.method_31577(stack, existing)) {
               return stack;
            }

            limit -= existing.method_7947();
         }

         if (limit <= 0) {
            return stack;
         } else {
            boolean reachedLimit = stack.method_7947() > limit;
            if (!simulate) {
               class_1799 result;
               if (existing.method_7960()) {
                  result = reachedLimit ? stack.method_46651(limit) : stack;
               } else {
                  existing.method_7933(reachedLimit ? limit : stack.method_7947());
                  result = existing;
               }

               this.setStackInSlot(slot, result);
            }

            return reachedLimit ? stack.method_46651(stack.method_7947() - limit) : class_1799.field_8037;
         }
      } else {
         throw new RuntimeException("Slot " + slot + " not in valid range - [0," + this.getSlotCount() + ")");
      }
   }

   @NotNull
   @Override
   public class_1799 insertItem(@NotNull class_1799 stack, boolean simulate) {
      class_1799 remaining = stack;

      for (int slot = 0; slot < this.getSlotCount(); slot++) {
         remaining = this.insertItem(slot, remaining, simulate);
         if (remaining.method_7960()) {
            return remaining;
         }
      }

      return remaining;
   }

   @NotNull
   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      if (amount == 0) {
         return class_1799.field_8037;
      } else {
         class_1799 existing = this.getStackInSlot(slot);
         if (existing.method_7960()) {
            return class_1799.field_8037;
         } else {
            int toExtract = Math.min(amount, existing.method_7914());
            if (existing.method_7947() <= toExtract) {
               if (!simulate) {
                  this.setStackInSlot(slot, class_1799.field_8037);
                  return existing;
               } else {
                  return existing.method_7972();
               }
            } else {
               if (!simulate) {
                  this.setStackInSlot(slot, existing.method_46651(existing.method_7947() - toExtract));
               }

               return existing.method_46651(toExtract);
            }
         }
      }
   }
}
