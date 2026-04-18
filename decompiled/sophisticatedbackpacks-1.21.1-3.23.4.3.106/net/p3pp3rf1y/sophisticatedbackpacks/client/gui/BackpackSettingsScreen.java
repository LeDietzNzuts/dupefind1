package net.p3pp3rf1y.sophisticatedbackpacks.client.gui;

import net.minecraft.class_1661;
import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackOpenPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.settings.BackpackSettingsTabControl;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.settings.StorageSettingsTabControlBase;

public class BackpackSettingsScreen extends SettingsScreen {
   public BackpackSettingsScreen(SettingsContainerMenu<?> screenContainer, class_1661 inv, class_2561 titleIn) {
      super(screenContainer, inv, titleIn);
   }

   protected StorageSettingsTabControlBase initializeTabControl() {
      return new BackpackSettingsTabControl(this, new Position(this.field_2776 + this.field_2792, this.field_2800 + 4));
   }

   public static BackpackSettingsScreen constructScreen(SettingsContainerMenu<?> settingsContainer, class_1661 playerInventory, class_2561 title) {
      return new BackpackSettingsScreen(settingsContainer, playerInventory, title);
   }

   protected void sendStorageInventoryScreenOpenMessage() {
      PacketDistributor.sendToServer(new BackpackOpenPayload());
   }
}
