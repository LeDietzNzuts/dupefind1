package net.p3pp3rf1y.sophisticatedcore.upgrades.crafting;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1662;
import net.minecraft.class_1703;
import net.minecraft.class_1715;
import net.minecraft.class_1799;
import net.minecraft.class_3917;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public class CraftingItemHandler extends class_1715 {
   private final Supplier<SlottedStackStorage> supplyInventory;
   private final Consumer<class_1263> onCraftingMatrixChanged;
   private boolean itemsInitialized = false;
   private List<class_1799> items = List.of();

   public CraftingItemHandler(Supplier<SlottedStackStorage> supplyInventory, Consumer<class_1263> onCraftingMatrixChanged) {
      super(new class_1703(null, -1) {
         public class_1799 method_7601(class_1657 player, int index) {
            return class_1799.field_8037;
         }

         public boolean method_7597(class_1657 playerIn) {
            return false;
         }
      }, 3, 3);
      this.supplyInventory = supplyInventory;
      this.onCraftingMatrixChanged = onCraftingMatrixChanged;
   }

   public int method_5439() {
      return this.supplyInventory.get().getSlotCount();
   }

   public boolean method_5442() {
      return InventoryHelper.isEmpty(this.supplyInventory.get());
   }

   public class_1799 method_5438(int index) {
      SlottedStackStorage itemHandler = this.supplyInventory.get();
      return index >= itemHandler.getSlotCount() ? class_1799.field_8037 : itemHandler.getStackInSlot(index);
   }

   public List<class_1799> method_51305() {
      if (!this.itemsInitialized) {
         this.items = new ArrayList<>();

         for (int slot = 0; slot < this.supplyInventory.get().getSlotCount(); slot++) {
            this.items.add(this.supplyInventory.get().getStackInSlot(slot));
         }

         this.itemsInitialized = true;
      }

      return this.items;
   }

   public class_1799 method_5441(int index) {
      return InventoryHelper.getAndRemove((SlottedStorage<ItemVariant>)this.supplyInventory.get(), index);
   }

   public class_1799 method_5434(int index, int count) {
      ItemVariant resource = ItemVariant.of(this.supplyInventory.get().getStackInSlot(index));
      Transaction ctx = Transaction.openOuter();

      long amount;
      try {
         amount = this.supplyInventory.get().extractSlot(index, resource, count, ctx);
         ctx.commit();
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

      if (amount > 0L) {
         this.itemsInitialized = false;
         this.onCraftingMatrixChanged.accept(this);
      }

      return resource.toStack((int)amount);
   }

   public void method_5447(int index, class_1799 stack) {
      this.supplyInventory.get().setStackInSlot(index, stack);
      this.onCraftingMatrixChanged.accept(this);
      this.itemsInitialized = false;
   }

   public void method_5431() {
      super.method_5431();
      this.itemsInitialized = false;
   }

   public void method_7683(class_1662 helper) {
      InventoryHelper.iterate((SlottedStorage<ItemVariant>)this.supplyInventory.get(), (slot, stack) -> helper.method_7404(stack));
   }
}
