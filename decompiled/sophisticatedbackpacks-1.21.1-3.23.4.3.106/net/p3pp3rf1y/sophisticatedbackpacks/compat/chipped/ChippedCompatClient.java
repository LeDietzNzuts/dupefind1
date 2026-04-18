package net.p3pp3rf1y.sophisticatedbackpacks.compat.chipped;

import net.minecraft.class_2960;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPButtonDefinitions;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeGuiManager;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeTab;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeWrapper;

public class ChippedCompatClient {
   private ChippedCompatClient() {
   }

   public static void registerUpgradeTab(
      class_2960 itemId, UpgradeContainerType<BlockTransformationUpgradeWrapper, BlockTransformationUpgradeContainer> containerType
   ) {
      UpgradeGuiManager.registerTab(
         containerType,
         (upgradeContainer, position, screen) -> {
            String itemName = itemId.method_12832();
            return new BlockTransformationUpgradeTab(
               upgradeContainer,
               position,
               screen,
               SBPButtonDefinitions.SHIFT_CLICK_TARGET,
               itemName.replace('/', '_').substring(0, itemName.length() - "_upgrade".length())
            );
         }
      );
   }
}
