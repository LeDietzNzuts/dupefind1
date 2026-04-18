package net.p3pp3rf1y.sophisticatedcore.client.gui;

import java.util.Map.Entry;
import net.minecraft.class_1735;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;

public class UpgradeSettingsTabControl extends SettingsTabControl<StorageScreenBase<?>, UpgradeSettingsTab<?>> {
   public UpgradeSettingsTabControl(Position position, StorageScreenBase<?> screen, String storageSettingsTabTooltip) {
      super(position);
      this.addChild(new StorageSettingsTab(new Position(this.x, this.getTopY()), screen, storageSettingsTabTooltip));

      for (Entry<Integer, UpgradeContainerBase<?, ?>> entry : ((StorageContainerMenuBase)screen.method_17577()).getUpgradeContainers().entrySet()) {
         ((UpgradeSettingsTab)this.addSettingsTab(
               () -> ((StorageContainerMenuBase)screen.method_17577()).setOpenTabId(entry.getKey()),
               () -> ((StorageContainerMenuBase)screen.method_17577()).removeOpenTabId(),
               UpgradeGuiManager.getTab(entry.getValue(), new Position(this.x, this.getTopY()), screen)
            ))
            .onAfterInit();
      }
   }

   public boolean slotIsNotCoveredAt(class_1735 slot, double mouseX, double mouseY) {
      for (Tab tab : this.children) {
         if (tab instanceof UpgradeSettingsTab<?> upgradeSettingsTab && !upgradeSettingsTab.slotIsNotCoveredAt(slot, mouseX, mouseY)) {
            return false;
         }
      }

      return true;
   }

   public void tick() {
      this.children.forEach(Tab::tick);
   }
}
