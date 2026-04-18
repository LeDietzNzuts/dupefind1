package net.p3pp3rf1y.sophisticatedcore.upgrades.voiding;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class VoidUpgradeContainer extends UpgradeContainerBase<VoidUpgradeWrapper, VoidUpgradeContainer> {
   private static final String DATA_SHOULD_WORKD_IN_GUI = "shouldWorkdInGUI";
   private static final String DATA_SHOULD_VOID_OVERFLOW = "shouldVoidOverflow";
   private final FilterLogicContainer<FilterLogic> filterLogicContainer = new FilterLogicContainer<>(this.upgradeWrapper::getFilterLogic, this, this.slots::add);

   public VoidUpgradeContainer(
      class_1657 player, int containerId, VoidUpgradeWrapper wrapper, UpgradeContainerType<VoidUpgradeWrapper, VoidUpgradeContainer> type
   ) {
      super(player, containerId, wrapper, type);
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("shouldWorkdInGUI")) {
         this.setShouldWorkdInGUI(data.method_10577("shouldWorkdInGUI"));
      } else if (data.method_10545("shouldVoidOverflow")) {
         this.setShouldVoidOverflow(data.method_10577("shouldVoidOverflow"));
      }

      this.filterLogicContainer.handlePacket(data);
   }

   public FilterLogicContainer<FilterLogic> getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public void setShouldWorkdInGUI(boolean shouldWorkdInGUI) {
      this.upgradeWrapper.setShouldWorkdInGUI(shouldWorkdInGUI);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shouldWorkdInGUI", shouldWorkdInGUI));
   }

   public void setShouldVoidOverflow(boolean shouldVoidOverflow) {
      this.upgradeWrapper.setShouldVoidOverflow(shouldVoidOverflow);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shouldVoidOverflow", shouldVoidOverflow));
   }

   public boolean shouldWorkInGUI() {
      return this.upgradeWrapper.shouldWorkInGUI();
   }

   public boolean shouldVoidOverflow() {
      return this.upgradeWrapper.shouldVoidOverflow();
   }
}
