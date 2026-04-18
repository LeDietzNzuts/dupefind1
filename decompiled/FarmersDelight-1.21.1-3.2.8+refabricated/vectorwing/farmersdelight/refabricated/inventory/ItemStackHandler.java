package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_7225.class_7874;

public class ItemStackHandler extends FabricWrappedInventory {
   private final class_2371<class_1799> stacks;

   public ItemStackHandler() {
      this(1);
   }

   public ItemStackHandler(int size) {
      super(size);
      this.stacks = class_2371.method_10213(size, class_1799.field_8037);
   }

   @Override
   public int getSlotCount() {
      return this.stacks.size();
   }

   @Override
   public int getSlotLimit(int slot) {
      return 99;
   }

   protected int getStackLimit(int slot, class_1799 stack) {
      return Math.min(this.getSlotLimit(slot), stack.method_7914());
   }

   @Override
   public boolean isItemValid(int slot, class_1799 stack) {
      return true;
   }

   @Override
   public class_1799 getStackInSlot(int slot) {
      return (class_1799)this.stacks.get(slot);
   }

   @Override
   public class_1799 insertItem(int slot, class_1799 stack, boolean simulate) {
      if (stack.method_7960()) {
         return class_1799.field_8037;
      } else if (!this.isItemValid(slot, stack)) {
         return stack;
      } else {
         class_1799 existing = (class_1799)this.stacks.get(slot);
         int limit = this.getStackLimit(slot, stack);
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
               if (existing.method_7960()) {
                  this.stacks.set(slot, reachedLimit ? stack.method_46651(limit) : stack);
               } else {
                  existing.method_7933(reachedLimit ? limit : stack.method_7947());
               }

               this.onContentsChanged(slot);
            }

            return reachedLimit ? stack.method_46651(stack.method_7947() - limit) : class_1799.field_8037;
         }
      }
   }

   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      if (amount == 0) {
         return class_1799.field_8037;
      } else {
         class_1799 existing = (class_1799)this.stacks.get(slot);
         if (existing.method_7960()) {
            return class_1799.field_8037;
         } else {
            int toExtract = Math.min(amount, existing.method_7914());
            if (existing.method_7947() <= toExtract) {
               if (!simulate) {
                  this.stacks.set(slot, class_1799.field_8037);
                  this.onContentsChanged(slot);
                  return existing;
               } else {
                  return existing.method_7972();
               }
            } else {
               if (!simulate) {
                  this.stacks.set(slot, existing.method_46651(existing.method_7947() - toExtract));
                  this.onContentsChanged(slot);
               }

               return existing.method_46651(toExtract);
            }
         }
      }
   }

   @Override
   public void setStackInSlot(int slot, class_1799 stack) {
      this.stacks.set(slot, stack);
      this.onContentsChanged(slot);
   }

   public class_2487 serializeNBT(class_7874 provider) {
      class_2499 listTag = new class_2499();

      for (int i = 0; i < this.stacks.size(); i++) {
         if (!((class_1799)this.stacks.get(i)).method_7960()) {
            class_2487 itemTag = new class_2487();
            itemTag.method_10569("Slot", i);
            listTag.add(((class_1799)this.stacks.get(i)).method_57376(provider, itemTag));
         }
      }

      class_2487 tag = new class_2487();
      tag.method_10566("Items", listTag);
      return tag;
   }

   public void deserializeNBT(class_7874 provider, class_2487 tag) {
      this.stacks.clear();
      class_2499 listTag = tag.method_10554("Items", 10);

      for (int i = 0; i < listTag.size(); i++) {
         class_2487 itemTag = listTag.method_10602(i);
         int slot = itemTag.method_10550("Slot");
         if (slot >= 0 && slot < this.stacks.size()) {
            class_1799.method_57360(provider, itemTag).ifPresent(stack -> this.stacks.set(slot, stack));
         }
      }
   }

   protected void onContentsChanged(int slot) {
   }

   @Deprecated(forRemoval = true, since = "3.1.0")
   public class_1799 removeItem(int slot, int amount, boolean simulate) {
      return this.extractItem(slot, amount, simulate);
   }
}
