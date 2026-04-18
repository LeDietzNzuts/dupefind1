package net.p3pp3rf1y.sophisticatedcore.common.gui;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1799;

public class SlotItemHandler<T extends SlottedStorage<ItemVariant>> extends class_1735 {
   private static final class_1263 emptyInventory = new class_1277(0);
   private final T slottedStackStorage;
   private final int index;

   public SlotItemHandler(T slottedStorage, int slot, int x, int y) {
      super(emptyInventory, slot, x, y);
      this.slottedStackStorage = slottedStorage;
      this.index = slot;
   }

   public T getItemHandler() {
      return this.slottedStackStorage;
   }

   public class_1799 method_7677() {
      if (this.slottedStackStorage instanceof SlottedStackStorage storage) {
         return storage.getStackInSlot(this.index);
      } else {
         SingleSlotStorage<ItemVariant> slot = this.slottedStackStorage.getSlot(this.index);
         return ((ItemVariant)slot.getResource()).toStack((int)slot.getAmount());
      }
   }

   public void method_7673(class_1799 stack) {
      if (this.slottedStackStorage instanceof SlottedStackStorage storage) {
         storage.setStackInSlot(this.index, stack);
         this.method_7668();
      } else {
         SingleSlotStorage<ItemVariant> slot = this.slottedStackStorage.getSlot(this.index);
         if (!slot.isResourceBlank()) {
            Transaction extract = Transaction.openOuter();

            try {
               this.slottedStackStorage.extract((ItemVariant)slot.getResource(), slot.getAmount(), extract);
               extract.commit();
            } catch (Throwable var10) {
               if (extract != null) {
                  try {
                     extract.close();
                  } catch (Throwable var8) {
                     var10.addSuppressed(var8);
                  }
               }

               throw var10;
            }

            if (extract != null) {
               extract.close();
            }
         }

         ItemVariant resource = ItemVariant.of(stack);
         if (!resource.isBlank()) {
            Transaction insert = Transaction.openOuter();

            try {
               this.slottedStackStorage.insert(ItemVariant.of(stack), stack.method_7947(), insert);
               insert.commit();
            } catch (Throwable var9) {
               if (insert != null) {
                  try {
                     insert.close();
                  } catch (Throwable var7) {
                     var9.addSuppressed(var7);
                  }
               }

               throw var9;
            }

            if (insert != null) {
               insert.close();
            }
         }

         this.method_7668();
      }
   }

   public class_1799 method_7671(int amount) {
      if (this.slottedStackStorage instanceof SlottedStackStorage storage) {
         class_1799 stack = storage.getStackInSlot(this.index).method_7972();
         class_1799 removed = stack.method_7971(amount);
         storage.setStackInSlot(this.index, stack);
         return removed;
      } else {
         SingleSlotStorage<ItemVariant> slot = this.slottedStackStorage.getSlot(this.index);
         Transaction extract = Transaction.openOuter();

         long extracted;
         try {
            extracted = this.slottedStackStorage.extract((ItemVariant)slot.getResource(), amount, extract);
            extract.commit();
         } catch (Throwable var10) {
            if (extract != null) {
               try {
                  extract.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }
            }

            throw var10;
         }

         if (extract != null) {
            extract.close();
         }

         return ((ItemVariant)slot.getResource()).toStack((int)extracted);
      }
   }

   public boolean method_7680(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else {
         return this.slottedStackStorage instanceof SlottedStackStorage storage
            ? storage.isItemValid(this.index, ItemVariant.of(stack), stack.method_7947())
            : true;
      }
   }

   public boolean method_7674(class_1657 player) {
      return this.slottedStackStorage instanceof SlottedStackStorage storage
         ? !storage.getStackInSlot(this.index).method_7960()
         : !this.slottedStackStorage.getSlot(this.index).isResourceBlank();
   }

   public int method_7675() {
      return this.slottedStackStorage instanceof SlottedStackStorage storage
         ? storage.getSlotLimit(this.index)
         : (int)this.slottedStackStorage.getSlot(this.index).getCapacity();
   }

   public int method_7676(class_1799 stack) {
      int maxSize;
      if (this.slottedStackStorage instanceof SlottedStackStorage storage) {
         maxSize = storage.getSlotLimit(this.index);
      } else {
         maxSize = (int)this.slottedStackStorage.getSlot(this.index).getCapacity();
      }

      return Math.min(stack.method_7914(), maxSize);
   }

   public void method_7670(class_1799 oldStack, class_1799 newStack) {
   }
}
