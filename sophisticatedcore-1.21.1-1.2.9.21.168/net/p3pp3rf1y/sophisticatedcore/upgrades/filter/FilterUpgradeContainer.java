package net.p3pp3rf1y.sophisticatedcore.upgrades.filter;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ContentsFilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class FilterUpgradeContainer extends UpgradeContainerBase<FilterUpgradeWrapper, FilterUpgradeContainer> {
   public static final UpgradeContainerType<FilterUpgradeWrapper, FilterUpgradeContainer> BASIC_TYPE = new UpgradeContainerType<>(FilterUpgradeContainer::new);
   public static final UpgradeContainerType<FilterUpgradeWrapper, FilterUpgradeContainer> ADVANCED_TYPE = new UpgradeContainerType<>(
      FilterUpgradeContainer::new
   );
   private static final String DATA_DIRECTION = "direction";
   private final ContentsFilterLogicContainer filterLogicContainer = new ContentsFilterLogicContainer(
      () -> this.upgradeWrapper.getFilterLogic(), this, this.slots::add
   );

   private FilterUpgradeContainer(
      class_1657 player, int containerId, FilterUpgradeWrapper wrapper, UpgradeContainerType<FilterUpgradeWrapper, FilterUpgradeContainer> type
   ) {
      super(player, containerId, wrapper, type);
   }

   public ContentsFilterLogicContainer getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public Direction getDirection() {
      return this.upgradeWrapper.getDirection();
   }

   public void setDirection(Direction direction) {
      this.upgradeWrapper.setDirection(direction);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "direction", direction));
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (!this.filterLogicContainer.handlePacket(data)) {
         if (data.method_10545("direction")) {
            this.setDirection(Direction.fromName(data.method_10558("direction")));
         }
      }
   }
}
