package net.p3pp3rf1y.sophisticatedcore.util;

import com.google.common.base.Preconditions;
import io.github.fabricators_of_create.porting_lib.transfer.callbacks.TransactionCallback;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_9288;
import net.minecraft.class_9331;
import net.p3pp3rf1y.sophisticatedcore.extensions.component.SophisticatedMutableDataComponentHolder;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import org.jetbrains.annotations.NotNull;

public abstract class ComponentItemHandler implements IItemHandlerSimpleInserter {
   protected final SophisticatedMutableDataComponentHolder parent;
   protected final class_9331<class_9288> component;
   protected final int size;

   public ComponentItemHandler(SophisticatedMutableDataComponentHolder parent, class_9331<class_9288> component, int size) {
      this.parent = parent;
      this.component = component;
      this.size = size;
      Preconditions.checkArgument(size <= 256, "The max size of ItemContainerContents is 256 slots.");
   }

   public SingleSlotStorage<ItemVariant> getSlot(int slot) {
      return null;
   }

   public int getSlotCount() {
      return this.size;
   }

   public class_1799 getStackInSlot(int slot) {
      class_9288 contents = this.getContents();
      return this.getStackFromContents(contents, slot);
   }

   public void setStackInSlot(int slot, class_1799 stack) {
      this.validateSlotIndex(slot);
      if (!this.isItemValid(slot, stack)) {
         throw new RuntimeException("Invalid stack " + stack + " for slot " + slot + ")");
      } else {
         class_9288 contents = this.getContents();
         class_1799 existing = this.getStackFromContents(contents, slot);
         if (!class_1799.method_7973(stack, existing)) {
            this.updateContents(contents, stack, slot);
         }
      }
   }

   public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return 0L;
   }

   public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return maxAmount - this.insertItem(slot, resource.toStack((int)maxAmount), ctx).method_7947();
   }

   @NotNull
   @Override
   public class_1799 insertItem(int slot, @NotNull class_1799 toInsert, boolean simulate) {
      Transaction ctx = Transaction.openOuter();

      class_1799 result;
      try {
         result = this.insertItem(slot, toInsert, ctx);
         if (!simulate) {
            ctx.commit();
         }
      } catch (Throwable var9) {
         if (ctx != null) {
            try {
               ctx.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }
         }

         throw var9;
      }

      if (ctx != null) {
         ctx.close();
      }

      return result;
   }

   public class_1799 insertItem(int slot, class_1799 toInsert, TransactionContext ctx) {
      this.validateSlotIndex(slot);
      if (toInsert.method_7960()) {
         return class_1799.field_8037;
      } else if (!this.isItemValid(slot, toInsert)) {
         return toInsert;
      } else {
         class_9288 contents = this.getContents();
         class_1799 existing = this.getStackFromContents(contents, slot);
         int insertLimit = Math.min(this.getSlotLimit(slot), toInsert.method_7914());
         if (!existing.method_7960()) {
            if (!class_1799.method_31577(toInsert, existing)) {
               return toInsert;
            }

            insertLimit -= existing.method_7947();
         }

         if (insertLimit <= 0) {
            return toInsert;
         } else {
            int inserted = Math.min(insertLimit, toInsert.method_7947());
            TransactionCallback.onSuccess(ctx, () -> this.updateContents(contents, toInsert.method_46651(existing.method_7947() + inserted), slot));
            return toInsert.method_46651(toInsert.method_7947() - inserted);
         }
      }
   }

   public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
      return 0L;
   }

   public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext ctx) {
      return this.extractItem(slot, (int)maxAmount, ctx).method_7947();
   }

   @NotNull
   @Override
   public class_1799 extractItem(int slot, int amount, boolean simulate) {
      Transaction ctx = Transaction.openOuter();

      class_1799 result;
      try {
         result = this.extractItem(slot, amount, ctx);
         if (!simulate) {
            ctx.commit();
         }
      } catch (Throwable var9) {
         if (ctx != null) {
            try {
               ctx.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }
         }

         throw var9;
      }

      if (ctx != null) {
         ctx.close();
      }

      return result;
   }

   public class_1799 extractItem(int slot, int amount, TransactionContext ctx) {
      this.validateSlotIndex(slot);
      if (amount == 0) {
         return class_1799.field_8037;
      } else {
         class_9288 contents = this.getContents();
         class_1799 existing = this.getStackFromContents(contents, slot);
         if (existing.method_7960()) {
            return class_1799.field_8037;
         } else {
            int toExtract = Math.min(amount, existing.method_7914());
            TransactionCallback.onSuccess(ctx, () -> this.updateContents(contents, existing.method_46651(existing.method_7947() - toExtract), slot));
            return existing.method_46651(toExtract);
         }
      }
   }

   public int getSlotLimit(int slot) {
      return 99;
   }

   @Override
   public boolean isItemValid(int slot, ItemVariant resource, int count) {
      return this.isItemValid(slot, resource.toStack(count));
   }

   @Override
   public boolean isItemValid(int slot, class_1799 stack) {
      return stack.method_7909().method_31568();
   }

   protected void onContentsChanged(int slot, class_1799 oldStack, class_1799 newStack) {
   }

   protected class_9288 getContents() {
      return (class_9288)this.parent.method_57825(this.component, class_9288.field_49334);
   }

   protected class_1799 getStackFromContents(class_9288 contents, int slot) {
      this.validateSlotIndex(slot);
      return contents.sophisticatedCore_getSlots() <= slot ? class_1799.field_8037 : contents.sophisticatedCore_getStackInSlot(slot);
   }

   protected void updateContents(class_9288 contents, class_1799 stack, int slot) {
      this.validateSlotIndex(slot);
      class_2371<class_1799> list = class_2371.method_10213(Math.max(contents.sophisticatedCore_getSlots(), this.getSlotCount()), class_1799.field_8037);
      contents.method_57492(list);
      class_1799 oldStack = (class_1799)list.get(slot);
      list.set(slot, stack);
      this.parent.sophisticatedCore_set(this.component, class_9288.method_57493(list));
      this.onContentsChanged(slot, oldStack, stack);
   }

   protected final void validateSlotIndex(int slot) {
      if (slot < 0 || slot >= this.getSlotCount()) {
         throw new RuntimeException("Slot " + slot + " not in valid range - [0," + this.getSlotCount() + ")");
      }
   }
}
