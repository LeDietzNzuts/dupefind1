package net.p3pp3rf1y.sophisticatedcore.upgrades.compacting;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class CompactingUpgradeContainer extends UpgradeContainerBase<CompactingUpgradeWrapper, CompactingUpgradeContainer> {
   private static final String DATA_SHOULD_WORKD_IN_GUI = "shouldWorkdInGUI";
   private final FilterLogicContainer<FilterLogic> filterLogicContainer = new FilterLogicContainer<>(this.upgradeWrapper::getFilterLogic, this, this.slots::add);
   private static final String DATA_SHOULD_COMPACT_NON_UNCRAFTABLE = "shouldCompactNonUncraftable";

   public CompactingUpgradeContainer(
      class_1657 player, int containerId, CompactingUpgradeWrapper wrapper, UpgradeContainerType<CompactingUpgradeWrapper, CompactingUpgradeContainer> type
   ) {
      super(player, containerId, wrapper, type);
   }

   public FilterLogicContainer<FilterLogic> getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public void setCompactNonUncraftable(boolean shouldCompactNonUncraftable) {
      this.upgradeWrapper.setCompactNonUncraftable(shouldCompactNonUncraftable);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shouldCompactNonUncraftable", shouldCompactNonUncraftable));
   }

   public boolean shouldCompactNonUncraftable() {
      return this.upgradeWrapper.shouldCompactNonUncraftable();
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("shouldCompactNonUncraftable")) {
         this.setCompactNonUncraftable(data.method_10577("shouldCompactNonUncraftable"));
      } else if (data.method_10545("shouldWorkdInGUI")) {
         this.setShouldWorkdInGUI(data.method_10577("shouldWorkdInGUI"));
      } else {
         this.filterLogicContainer.handlePacket(data);
      }
   }

   public void setShouldWorkdInGUI(boolean shouldWorkdInGUI) {
      this.upgradeWrapper.setShouldWorkdInGUI(shouldWorkdInGUI);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shouldWorkdInGUI", shouldWorkdInGUI));
   }

   public boolean shouldWorkInGUI() {
      return this.upgradeWrapper.shouldWorkInGUI();
   }
}
