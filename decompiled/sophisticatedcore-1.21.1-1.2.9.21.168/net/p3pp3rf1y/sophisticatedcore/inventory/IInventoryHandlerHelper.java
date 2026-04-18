package net.p3pp3rf1y.sophisticatedcore.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;

public interface IInventoryHandlerHelper extends SlottedStorage<ItemVariant> {
   default class_1799 insertItem(int slot, @NotNull class_1799 stack, boolean simulate) {
      Transaction ctx = Transaction.openOuter();

      long inserted;
      try {
         inserted = this.getSlot(slot).insert(ItemVariant.of(stack), stack.method_7947(), ctx);
         if (!simulate) {
            ctx.commit();
         }
      } catch (Throwable var10) {
         if (ctx != null) {
            try {
               ctx.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }
         }

         throw var10;
      }

      if (ctx != null) {
         ctx.close();
      }

      return inserted < stack.method_7947() ? stack.method_46651(stack.method_7947() - (int)inserted) : class_1799.field_8037;
   }

   @NotNull
   default class_1799 insertItem(@NotNull class_1799 stack, boolean simulate) {
      Transaction ctx = Transaction.openOuter();

      long inserted;
      try {
         inserted = this.insert(ItemVariant.of(stack), stack.method_7947(), ctx);
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

      return inserted < stack.method_7947() ? stack.method_46651(stack.method_7947() - (int)inserted) : class_1799.field_8037;
   }

   @NotNull
   default class_1799 extractItem(int slot, int amount, boolean simulate) {
      SingleSlotStorage<ItemVariant> slotStorage = this.getSlot(slot);
      ItemVariant resource = (ItemVariant)slotStorage.getResource();
      Transaction outer = Transaction.openOuter();

      long extracted;
      try {
         extracted = slotStorage.extract(resource, amount, outer);
         if (!simulate) {
            outer.commit();
         }
      } catch (Throwable var12) {
         if (outer != null) {
            try {
               outer.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }
         }

         throw var12;
      }

      if (outer != null) {
         outer.close();
      }

      return resource.toStack((int)extracted);
   }
}
