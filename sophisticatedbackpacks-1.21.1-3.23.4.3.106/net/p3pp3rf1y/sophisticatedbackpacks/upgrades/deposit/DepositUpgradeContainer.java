package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.deposit;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;

public class DepositUpgradeContainer extends UpgradeContainerBase<DepositUpgradeWrapper, DepositUpgradeContainer> {
   private final DepositFilterLogicContainer filterLogicContainer = new DepositFilterLogicContainer(
      () -> ((DepositUpgradeWrapper)this.upgradeWrapper).getFilterLogic(), this, this.slots::add
   );

   public DepositUpgradeContainer(
      class_1657 player, int containerId, DepositUpgradeWrapper wrapper, UpgradeContainerType<DepositUpgradeWrapper, DepositUpgradeContainer> type
   ) {
      super(player, containerId, wrapper, type);
   }

   public DepositFilterLogicContainer getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public void handlePacket(class_2487 data) {
      this.filterLogicContainer.handlePacket(data);
   }
}
