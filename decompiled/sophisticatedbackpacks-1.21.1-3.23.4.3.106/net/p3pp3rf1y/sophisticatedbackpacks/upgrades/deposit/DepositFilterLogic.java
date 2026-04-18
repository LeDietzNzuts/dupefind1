package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.deposit;

import io.github.fabricators_of_create.porting_lib.util.DeferredHolder;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.class_1799;
import net.minecraft.class_9331;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterAttributes;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public class DepositFilterLogic extends FilterLogic {
   private Set<ItemStackKey> inventoryFilterStacks = new HashSet<>();

   public DepositFilterLogic(
      class_1799 upgrade, Consumer<class_1799> saveHandler, int filterSlotCount, DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> contentsComponent
   ) {
      super(upgrade, saveHandler, filterSlotCount, contentsComponent);
   }

   public DepositFilterType getDepositFilterType() {
      if (this.shouldFilterByInventory()) {
         return DepositFilterType.INVENTORY;
      } else {
         return this.isAllowList() ? DepositFilterType.ALLOW : DepositFilterType.BLOCK;
      }
   }

   public void setDepositFilterType(DepositFilterType depositFilterType) {
      switch (depositFilterType) {
         case ALLOW:
            this.setFilterByInventory(false);
            this.setAllowList(true);
            break;
         case BLOCK:
            this.setFilterByInventory(false);
            this.setAllowList(false);
            break;
         case INVENTORY:
         default:
            this.setFilterByInventory(true);
            this.save();
      }
   }

   public void setInventory(Storage<ItemVariant> inventory) {
      this.inventoryFilterStacks = InventoryHelper.getUniqueStacks(inventory);
   }

   public boolean matchesFilter(class_1799 stack) {
      if (!this.shouldFilterByInventory()) {
         return super.matchesFilter(stack);
      } else {
         for (ItemStackKey filterStack : this.inventoryFilterStacks) {
            if (this.stackMatchesFilter(stack, filterStack.getStack())) {
               return true;
            }
         }

         return false;
      }
   }

   private void setFilterByInventory(boolean filterByInventory) {
      this.upgrade.sophisticatedCore_set(ModDataComponents.FILTER_BY_INVENTORY, filterByInventory);
      this.save();
   }

   private boolean shouldFilterByInventory() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModDataComponents.FILTER_BY_INVENTORY, false);
   }
}
