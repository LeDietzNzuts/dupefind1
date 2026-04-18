package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class ToolSwapperUpgradeContainer extends UpgradeContainerBase<ToolSwapperUpgradeWrapper, ToolSwapperUpgradeContainer> {
   private static final String DATA_SHOULD_SWAP_WEAPON = "shouldSwapWeapon";
   private static final String DATA_TOOL_SWAP_MODE = "toolSwapMode";
   private final FilterLogicContainer<FilterLogic> filterLogicContainer;

   public ToolSwapperUpgradeContainer(
      class_1657 player,
      int upgradeContainerId,
      ToolSwapperUpgradeWrapper upgradeWrapper,
      UpgradeContainerType<ToolSwapperUpgradeWrapper, ToolSwapperUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
      this.filterLogicContainer = new FilterLogicContainer(upgradeWrapper::getFilterLogic, this, this.slots::add);
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("shouldSwapWeapon")) {
         this.setSwapWeapon(data.method_10577("shouldSwapWeapon"));
      } else if (data.method_10545("toolSwapMode")) {
         this.setToolSwapMode(ToolSwapMode.fromName(data.method_10558("toolSwapMode")));
      } else {
         this.filterLogicContainer.handlePacket(data);
      }
   }

   public FilterLogicContainer<FilterLogic> getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public void setSwapWeapon(boolean shouldSwapWeapon) {
      ((ToolSwapperUpgradeWrapper)this.upgradeWrapper).setSwapWeapon(shouldSwapWeapon);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shouldSwapWeapon", shouldSwapWeapon));
   }

   public boolean shouldSwapWeapon() {
      return ((ToolSwapperUpgradeWrapper)this.upgradeWrapper).shouldSwapWeapon();
   }

   public void setToolSwapMode(ToolSwapMode toolSwapMode) {
      ((ToolSwapperUpgradeWrapper)this.upgradeWrapper).setToolSwapMode(toolSwapMode);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "toolSwapMode", toolSwapMode));
   }

   public ToolSwapMode getToolSwapMode() {
      return ((ToolSwapperUpgradeWrapper)this.upgradeWrapper).getToolSwapMode();
   }
}
