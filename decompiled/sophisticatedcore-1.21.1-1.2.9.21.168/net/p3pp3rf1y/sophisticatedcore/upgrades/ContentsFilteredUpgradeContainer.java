package net.p3pp3rf1y.sophisticatedcore.upgrades;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;

public class ContentsFilteredUpgradeContainer<W extends IUpgradeWrapper & IContentsFilteredUpgrade>
   extends UpgradeContainerBase<W, ContentsFilteredUpgradeContainer<W>> {
   private final ContentsFilterLogicContainer filterLogicContainer = new ContentsFilterLogicContainer(
      () -> this.upgradeWrapper.getFilterLogic(), this, this.slots::add
   );

   public ContentsFilteredUpgradeContainer(class_1657 player, int containerId, W wrapper, UpgradeContainerType<W, ContentsFilteredUpgradeContainer<W>> type) {
      super(player, containerId, wrapper, type);
   }

   public ContentsFilterLogicContainer getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   @Override
   public void handlePacket(class_2487 data) {
      this.filterLogicContainer.handlePacket(data);
   }
}
