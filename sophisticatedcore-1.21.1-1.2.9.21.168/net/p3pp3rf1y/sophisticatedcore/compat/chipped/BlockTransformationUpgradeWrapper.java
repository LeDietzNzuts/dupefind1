package net.p3pp3rf1y.sophisticatedcore.compat.chipped;

import earth.terrarium.chipped.common.recipes.ChippedRecipe;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.minecraft.class_3956;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.SimpleItemContent;

public class BlockTransformationUpgradeWrapper extends UpgradeWrapperBase<BlockTransformationUpgradeWrapper, BlockTransformationUpgradeItem> {
   private final SlottedStackStorage inputInventory;
   private final class_3956<ChippedRecipe> recipeType;

   protected BlockTransformationUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.inputInventory = new ItemStackHandler(1) {
         protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            if (slot == 0) {
               upgrade.sophisticatedCore_set(ModCoreDataComponents.INPUT_ITEM, SimpleItemContent.copyOf(this.getStackInSlot(0)));
            }

            BlockTransformationUpgradeWrapper.this.save();
         }
      };
      this.inputInventory
         .setStackInSlot(0, ((SimpleItemContent)upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.INPUT_ITEM, SimpleItemContent.EMPTY)).copy());
      this.recipeType = this.upgradeItem.getRecipeType();
   }

   public SlottedStackStorage getInputInventory() {
      return this.inputInventory;
   }

   public void setResult(class_1799 result) {
      if (result.method_7960()) {
         this.upgrade.sophisticatedCore_remove(ModCoreDataComponents.RESULT_ITEM);
      } else {
         this.upgrade.sophisticatedCore_set(ModCoreDataComponents.RESULT_ITEM, SimpleItemContent.copyOf(result));
         this.save();
      }
   }

   public Optional<SimpleItemContent> getResult() {
      return Optional.ofNullable((SimpleItemContent)this.upgrade.sophisticatedCore_get(ModCoreDataComponents.RESULT_ITEM));
   }

   @Override
   public boolean canBeDisabled() {
      return false;
   }

   public boolean shouldShiftClickIntoStorage() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.SHIFT_CLICK_INTO_STORAGE, true);
   }

   public void setShiftClickIntoStorage(boolean shiftClickIntoStorage) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.SHIFT_CLICK_INTO_STORAGE, shiftClickIntoStorage);
      this.save();
   }

   public class_3956<ChippedRecipe> getRecipeType() {
      return this.recipeType;
   }
}
