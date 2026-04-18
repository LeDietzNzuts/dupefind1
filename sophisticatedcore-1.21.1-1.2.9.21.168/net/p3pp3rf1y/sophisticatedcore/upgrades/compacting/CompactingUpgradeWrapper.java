package net.p3pp3rf1y.sophisticatedcore.upgrades.compacting;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IFilteredUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IInsertResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;

public class CompactingUpgradeWrapper
   extends UpgradeWrapperBase<CompactingUpgradeWrapper, CompactingUpgradeItem>
   implements IInsertResponseUpgrade,
   IFilteredUpgrade,
   ISlotChangeResponseUpgrade,
   ITickableUpgrade {
   private final FilterLogic filterLogic;
   private final Set<Integer> slotsToCompact = new HashSet<>();

   public CompactingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.filterLogic = new FilterLogic(
         upgrade,
         upgradeSaveHandler,
         this.upgradeItem.getFilterSlotCount(),
         stack -> RecipeHelper.getItemCompactingShapes(stack).stream().anyMatch(shape -> shape != RecipeHelper.CompactingShape.NONE),
         ModCoreDataComponents.FILTER_ATTRIBUTES
      );
   }

   @Override
   public class_1799 onBeforeInsert(IItemHandlerSimpleInserter inventoryHandler, int slot, class_1799 stack, boolean simulate) {
      return stack;
   }

   @Override
   public void onAfterInsert(IItemHandlerSimpleInserter inventoryHandler, int slot) {
      this.compactSlot(inventoryHandler, slot);
   }

   private void compactSlot(IItemHandlerSimpleInserter inventoryHandler, int slot) {
      class_1799 slotStack = inventoryHandler.getStackInSlot(slot);
      if (!slotStack.method_7960() && this.filterLogic.matchesFilter(slotStack)) {
         Set<RecipeHelper.CompactingShape> shapes = RecipeHelper.getItemCompactingShapes(slotStack);
         if (!this.upgradeItem.shouldCompactThreeByThree()
            || !shapes.contains(RecipeHelper.CompactingShape.THREE_BY_THREE_UNCRAFTABLE)
               && (!this.shouldCompactNonUncraftable() || !shapes.contains(RecipeHelper.CompactingShape.THREE_BY_THREE))) {
            if (shapes.contains(RecipeHelper.CompactingShape.TWO_BY_TWO_UNCRAFTABLE)
               || this.shouldCompactNonUncraftable() && shapes.contains(RecipeHelper.CompactingShape.TWO_BY_TWO)) {
               this.tryCompacting(inventoryHandler, slotStack, 2, 2);
            }
         } else {
            this.tryCompacting(inventoryHandler, slotStack, 3, 3);
         }
      }
   }

   private void tryCompacting(IItemHandlerSimpleInserter inventoryHandler, class_1799 stack, int width, int height) {
      int totalCount = width * height;
      RecipeHelper.CompactingResult compactingResult = RecipeHelper.getCompactingResult(stack, width, height);
      if (!compactingResult.getResult().method_7960()) {
         class_1799 extractedStack = InventoryHelper.extractFromInventory(stack.method_46651(totalCount), inventoryHandler, true);
         if (extractedStack.method_7947() != totalCount) {
            return;
         }

         while (extractedStack.method_7947() == totalCount) {
            class_1799 resultCopy = compactingResult.getResult().method_7972();
            List<class_1799> remainingItemsCopy = compactingResult.getRemainingItems().isEmpty()
               ? Collections.emptyList()
               : compactingResult.getRemainingItems().stream().<class_1799>map(class_1799::method_7972).toList();
            if (!this.fitsResultAndRemainingItems(inventoryHandler, remainingItemsCopy, resultCopy)) {
               break;
            }

            InventoryHelper.extractFromInventory(stack.method_46651(totalCount), inventoryHandler, false);
            inventoryHandler.insertItem(resultCopy, false);
            InventoryHelper.insertIntoInventory(remainingItemsCopy, inventoryHandler, false);
            extractedStack = InventoryHelper.extractFromInventory(stack.method_46651(totalCount), inventoryHandler, true);
         }
      }
   }

   private boolean fitsResultAndRemainingItems(IItemHandlerSimpleInserter inventoryHandler, List<class_1799> remainingItems, class_1799 result) {
      if (remainingItems.isEmpty()) {
         return InventoryHelper.insertIntoInventory(result, inventoryHandler, true).method_7960();
      } else {
         ItemStackHandler clonedHandler = InventoryHelper.cloneInventory(inventoryHandler);
         return InventoryHelper.insertIntoInventory(result, clonedHandler, false).method_7960()
            && InventoryHelper.insertIntoInventory(remainingItems, clonedHandler, false).isEmpty();
      }
   }

   @Override
   public FilterLogic getFilterLogic() {
      return this.filterLogic;
   }

   public boolean shouldCompactNonUncraftable() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.COMPACT_NON_UNCRAFTABLE, false);
   }

   public void setCompactNonUncraftable(boolean shouldCompactNonUncraftable) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.COMPACT_NON_UNCRAFTABLE, shouldCompactNonUncraftable);
      this.save();
   }

   @Override
   public void onSlotChange(IItemHandlerSimpleInserter inventoryHandler, int slot) {
      if (this.shouldWorkInGUI()) {
         this.slotsToCompact.add(slot);
      }
   }

   public void setShouldWorkdInGUI(boolean shouldWorkdInGUI) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.SHOULD_WORK_IN_GUI, shouldWorkdInGUI);
      this.save();
   }

   public boolean shouldWorkInGUI() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.SHOULD_WORK_IN_GUI, false);
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!this.slotsToCompact.isEmpty()) {
         for (int slot : this.slotsToCompact) {
            this.compactSlot(this.storageWrapper.getInventoryHandler(), slot);
         }

         this.slotsToCompact.clear();
      }
   }
}
