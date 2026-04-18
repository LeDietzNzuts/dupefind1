package net.p3pp3rf1y.sophisticatedcore.upgrades.stonecutter;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.Optional;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.SimpleItemContent;

public class StonecutterUpgradeWrapper extends UpgradeWrapperBase<StonecutterUpgradeWrapper, StonecutterUpgradeItem> {
   private final SlottedStackStorage inputInventory;

   protected StonecutterUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.inputInventory = new ItemStackHandler(1) {
         protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            if (slot == 0) {
               upgrade.sophisticatedCore_set(ModCoreDataComponents.INPUT_ITEM, SimpleItemContent.copyOf(this.getStackInSlot(0)));
            }

            StonecutterUpgradeWrapper.this.save();
         }
      };
      this.inputInventory
         .setStackInSlot(0, ((SimpleItemContent)upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.INPUT_ITEM, SimpleItemContent.EMPTY)).copy());
   }

   public SlottedStackStorage getInputInventory() {
      return this.inputInventory;
   }

   public void setRecipeId(@Nullable class_2960 recipeId) {
      if (recipeId == null) {
         this.upgrade.sophisticatedCore_remove(ModCoreDataComponents.RECIPE_ID);
      } else {
         this.upgrade.sophisticatedCore_set(ModCoreDataComponents.RECIPE_ID, recipeId);
         this.save();
      }
   }

   public Optional<class_2960> getRecipeId() {
      return Optional.ofNullable((class_2960)this.upgrade.sophisticatedCore_get(ModCoreDataComponents.RECIPE_ID));
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
}
