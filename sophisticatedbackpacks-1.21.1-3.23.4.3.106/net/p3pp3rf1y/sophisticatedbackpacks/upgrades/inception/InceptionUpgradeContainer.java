package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class InceptionUpgradeContainer extends UpgradeContainerBase<InceptionUpgradeWrapper, InceptionUpgradeContainer> {
   private static final String DATA_INVENTORY_ORDER = "inventoryOrder";

   public InceptionUpgradeContainer(
      class_1657 player,
      int upgradeContainerId,
      InceptionUpgradeWrapper upgradeWrapper,
      UpgradeContainerType<InceptionUpgradeWrapper, InceptionUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("inventoryOrder")) {
         this.setInventoryOrder(InventoryOrder.fromName(data.method_10558("inventoryOrder")));
      }
   }

   public InventoryOrder getInventoryOrder() {
      return ((InceptionUpgradeWrapper)this.upgradeWrapper).getInventoryOrder();
   }

   public void setInventoryOrder(InventoryOrder inventoryOrder) {
      ((InceptionUpgradeWrapper)this.upgradeWrapper).setInventoryOrder(inventoryOrder);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "inventoryOrder", inventoryOrder));
   }
}
