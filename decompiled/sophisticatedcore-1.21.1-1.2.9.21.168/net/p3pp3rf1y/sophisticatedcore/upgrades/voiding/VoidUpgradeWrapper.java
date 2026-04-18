package net.p3pp3rf1y.sophisticatedcore.upgrades.voiding;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.p3pp3rf1y.sophisticatedcore.api.ISlotChangeResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IFilteredUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IInsertResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IOverflowResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ISlotLimitUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.PrimaryMatch;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;

public class VoidUpgradeWrapper
   extends UpgradeWrapperBase<VoidUpgradeWrapper, VoidUpgradeItem>
   implements IInsertResponseUpgrade,
   IFilteredUpgrade,
   ISlotChangeResponseUpgrade,
   ITickableUpgrade,
   IOverflowResponseUpgrade,
   ISlotLimitUpgrade {
   private final FilterLogic filterLogic;
   private final Set<Integer> slotsToVoid = new HashSet<>();
   private boolean shouldVoidOverflow;

   public VoidUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.filterLogic = new FilterLogic(upgrade, upgradeSaveHandler, this.upgradeItem.getFilterSlotCount(), ModCoreDataComponents.FILTER_ATTRIBUTES);
      this.filterLogic.setAllowByDefault(true);
      this.setShouldVoidOverflowDefaultOrLoadFromNbt(false);
   }

   @Override
   public class_1799 onBeforeInsert(IItemHandlerSimpleInserter inventoryHandler, int slot, class_1799 stack, boolean simulate) {
      if (this.shouldVoidOverflow
         && inventoryHandler.getStackInSlot(slot).method_7960()
         && (!this.filterLogic.shouldMatchComponents() || !this.filterLogic.shouldMatchDurability() || this.filterLogic.getPrimaryMatch() != PrimaryMatch.ITEM)
         && this.filterLogic.matchesFilter(stack)) {
         for (int s = 0; s < inventoryHandler.getSlotCount(); s++) {
            if (s != slot && this.stackMatchesFilterStack(inventoryHandler.getStackInSlot(s), stack)) {
               return class_1799.field_8037;
            }
         }

         return stack;
      } else {
         return !this.shouldVoidOverflow && this.filterLogic.matchesFilter(stack) ? class_1799.field_8037 : stack;
      }
   }

   @Override
   public void onAfterInsert(IItemHandlerSimpleInserter inventoryHandler, int slot) {
   }

   @Override
   public FilterLogic getFilterLogic() {
      return this.filterLogic;
   }

   public void setShouldWorkdInGUI(boolean shouldWorkdInGUI) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.SHOULD_WORK_IN_GUI, shouldWorkdInGUI);
      this.save();
   }

   public boolean shouldWorkInGUI() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.SHOULD_WORK_IN_GUI, false);
   }

   public void setShouldVoidOverflow(boolean shouldVoidOverflow) {
      if (shouldVoidOverflow || this.upgradeItem.isVoidAnythingEnabled()) {
         this.shouldVoidOverflow = shouldVoidOverflow;
         this.upgrade.sophisticatedCore_set(ModCoreDataComponents.SHOULD_VOID_OVERFLOW, shouldVoidOverflow);
         this.save();
      }
   }

   public void setShouldVoidOverflowDefaultOrLoadFromNbt(boolean shouldVoidOverflowDefault) {
      this.shouldVoidOverflow = !this.upgradeItem.isVoidAnythingEnabled()
         || (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.SHOULD_VOID_OVERFLOW, shouldVoidOverflowDefault);
   }

   public boolean shouldVoidOverflow() {
      return !this.upgradeItem.isVoidAnythingEnabled() || this.shouldVoidOverflow;
   }

   @Override
   public void onSlotChange(IItemHandlerSimpleInserter inventoryHandler, int slot) {
      if (this.shouldWorkInGUI() && !this.shouldVoidOverflow()) {
         class_1799 slotStack = inventoryHandler.getStackInSlot(slot);
         if (this.filterLogic.matchesFilter(slotStack)) {
            this.slotsToVoid.add(slot);
         }
      }
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!this.slotsToVoid.isEmpty()) {
         InventoryHandler storageInventory = this.storageWrapper.getInventoryHandler();

         for (int slot : this.slotsToVoid) {
            storageInventory.extractItem(slot, storageInventory.getStackInSlot(slot).method_7947(), false);
         }

         this.slotsToVoid.clear();
      }
   }

   @Override
   public boolean worksInGui() {
      return this.shouldWorkInGUI();
   }

   @Override
   public class_1799 onOverflow(class_1799 stack) {
      return this.filterLogic.matchesFilter(stack) ? class_1799.field_8037 : stack;
   }

   @Override
   public boolean stackMatchesFilter(class_1799 stack) {
      return this.filterLogic.matchesFilter(stack);
   }

   public boolean isVoidAnythingEnabled() {
      return this.upgradeItem.isVoidAnythingEnabled();
   }

   @Override
   public int getSlotLimit() {
      return Integer.MAX_VALUE;
   }
}
