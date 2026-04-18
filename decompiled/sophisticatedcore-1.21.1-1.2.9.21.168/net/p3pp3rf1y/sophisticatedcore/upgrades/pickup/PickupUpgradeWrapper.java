package net.p3pp3rf1y.sophisticatedcore.upgrades.pickup;

import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ContentsFilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IContentsFilteredUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IPickupResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;

public class PickupUpgradeWrapper
   extends UpgradeWrapperBase<PickupUpgradeWrapper, PickupUpgradeItem>
   implements IPickupResponseUpgrade,
   IContentsFilteredUpgrade {
   private final ContentsFilterLogic filterLogic;

   public PickupUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.filterLogic = new ContentsFilterLogic(
         upgrade,
         stack -> this.save(),
         this.upgradeItem.getFilterSlotCount(),
         storageWrapper::getInventoryHandler,
         storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class),
         ModCoreDataComponents.FILTER_ATTRIBUTES
      );
   }

   @Override
   public class_1799 pickup(class_1937 level, class_1799 stack, boolean simulate) {
      return !this.filterLogic.matchesFilter(stack) ? stack : this.storageWrapper.getInventoryForUpgradeProcessing().insertItem(stack, simulate);
   }

   @Override
   public ContentsFilterLogic getFilterLogic() {
      return this.filterLogic;
   }
}
