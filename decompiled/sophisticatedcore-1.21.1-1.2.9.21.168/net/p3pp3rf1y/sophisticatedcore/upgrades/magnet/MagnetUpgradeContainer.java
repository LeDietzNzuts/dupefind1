package net.p3pp3rf1y.sophisticatedcore.upgrades.magnet;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ContentsFilterLogicContainer;

public class MagnetUpgradeContainer extends UpgradeContainerBase<MagnetUpgradeWrapper, MagnetUpgradeContainer> {
   private static final String DATA_PICKUP_ITEMS = "pickupItems";
   private static final String DATA_PICKUP_XP = "pickupXp";
   private final ContentsFilterLogicContainer filterLogicContainer = new ContentsFilterLogicContainer(
      () -> this.upgradeWrapper.getFilterLogic(), this, this.slots::add
   );

   public MagnetUpgradeContainer(
      class_1657 player, int containerId, MagnetUpgradeWrapper wrapper, UpgradeContainerType<MagnetUpgradeWrapper, MagnetUpgradeContainer> type
   ) {
      super(player, containerId, wrapper, type);
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("pickupItems")) {
         this.setPickupItems(data.method_10577("pickupItems"));
      } else if (data.method_10545("pickupXp")) {
         this.setPickupXp(data.method_10577("pickupXp"));
      }

      this.filterLogicContainer.handlePacket(data);
   }

   public ContentsFilterLogicContainer getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public void setPickupItems(boolean pickupItems) {
      this.upgradeWrapper.setPickupItems(pickupItems);
      this.sendBooleanToServer("pickupItems", pickupItems);
   }

   public boolean shouldPickupItems() {
      return this.upgradeWrapper.shouldPickupItems();
   }

   public void setPickupXp(boolean pickupXp) {
      this.upgradeWrapper.setPickupXp(pickupXp);
      this.sendBooleanToServer("pickupXp", pickupXp);
   }

   public boolean shouldPickupXp() {
      return this.upgradeWrapper.shouldPickupXp();
   }
}
