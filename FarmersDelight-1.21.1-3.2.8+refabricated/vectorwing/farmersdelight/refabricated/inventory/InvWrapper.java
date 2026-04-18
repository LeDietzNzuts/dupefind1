package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.class_1263;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;

public class InvWrapper extends FabricWrappedInventory {
   private final class_1263 inv;

   public InvWrapper(class_1263 inv) {
      super(inv.method_5439());
      this.inv = inv;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else {
         return o instanceof InvWrapper wrapper ? this.getInv().equals(wrapper.getInv()) : false;
      }
   }

   @NotNull
   public class_1263 getInv() {
      return this.inv;
   }

   @Override
   public int getSlotCount() {
      return this.inv.method_5439();
   }

   @Override
   public int getSlotLimit(int slot) {
      return this.inv.method_5444();
   }

   @Override
   public class_1799 getStackInSlot(int slot) {
      return this.inv.method_5438(slot);
   }

   @Override
   public class_1799 insertItem(int slot, class_1799 stack, boolean simulate) {
      if (stack.method_7960()) {
         return class_1799.field_8037;
      } else {
         class_1799 stackInSlot = this.inv.method_5438(slot);
         if (!stackInSlot.method_7960()) {
            if (stackInSlot.method_7947() < Math.min(stackInSlot.method_7914(), this.getSlotLimit(slot))
               && !class_1799.method_31577(stack, stackInSlot)
               && this.inv.method_5437(slot, stack)) {
               int min = Math.min(stack.method_7914(), this.getSlotLimit(slot)) - stackInSlot.method_7947();
               if (stack.method_7947() <= min) {
                  if (!simulate) {
                     class_1799 copy = stack.method_7972();
                     copy.method_7933(stackInSlot.method_7947());
                     this.inv.method_5447(slot, copy);
                     this.inv.method_5431();
                  }

                  return class_1799.field_8037;
               } else {
                  class_1799 copy = stack.method_7972();
                  if (!simulate) {
                     class_1799 split = copy.method_7971(min);
                     split.method_7933(stackInSlot.method_7947());
                     this.inv.method_5447(slot, split);
                     this.inv.method_5431();
                     return stack;
                  } else {
                     copy.method_7934(min);
                     return copy;
                  }
               }
            } else {
               return stack;
            }
         } else if (!this.inv.method_5437(slot, stack)) {
            return stack;
         } else {
            int min = Math.min(stack.method_7914(), this.getSlotLimit(slot));
            if (min < stack.method_7947()) {
               class_1799 copy = stack.method_7972();
               if (!simulate) {
                  this.inv.method_5447(slot, copy.method_7971(min));
                  this.inv.method_5431();
                  return stack;
               } else {
                  copy.method_7934(min);
                  return copy;
               }
            } else {
               if (!simulate) {
                  this.inv.method_5447(slot, stack);
                  this.inv.method_5431();
               }

               return class_1799.field_8037;
            }
         }
      }
   }

   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      if (amount == 0) {
         return class_1799.field_8037;
      } else {
         class_1799 stackInSlot = this.inv.method_5438(slot);
         if (stackInSlot.method_7960()) {
            return class_1799.field_8037;
         } else if (simulate) {
            if (stackInSlot.method_7947() < amount) {
               return stackInSlot.method_7972();
            } else {
               class_1799 copy = stackInSlot.method_7972();
               copy.method_7939(amount);
               return copy;
            }
         } else {
            int min = Math.min(stackInSlot.method_7947(), amount);
            class_1799 decreased = this.inv.method_5434(slot, min);
            this.inv.method_5431();
            return decreased;
         }
      }
   }

   @Override
   public void setStackInSlot(int slot, class_1799 stack) {
      this.inv.method_5447(slot, stack);
   }

   @Override
   public boolean isItemValid(int slot, class_1799 stack) {
      return this.inv.method_5437(slot, stack);
   }
}
