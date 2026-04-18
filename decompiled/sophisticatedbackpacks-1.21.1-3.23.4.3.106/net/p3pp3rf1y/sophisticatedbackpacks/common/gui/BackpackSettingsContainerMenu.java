package net.p3pp3rf1y.sophisticatedbackpacks.common.gui;

import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_2487;
import net.minecraft.class_2540;
import net.minecraft.class_3222;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackContentsPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.settings.BackpackMainSettingsContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class BackpackSettingsContainerMenu extends SettingsContainerMenu<IBackpackWrapper> implements IContextAwareContainer {
   private final BackpackContext backpackContext;
   private class_2487 lastSettingsNbt = null;

   protected BackpackSettingsContainerMenu(int windowId, class_1657 player, BackpackContext backpackContext) {
      super(ModItems.SETTINGS_CONTAINER_TYPE.get(), windowId, player, backpackContext.getBackpackWrapper(player));
      this.backpackContext = backpackContext;
   }

   public static BackpackSettingsContainerMenu fromBuffer(int windowId, class_1661 playerInventory, class_2540 buffer) {
      return new BackpackSettingsContainerMenu(
         windowId, playerInventory.field_7546, BackpackContext.fromBuffer(buffer, playerInventory.field_7546.method_37908())
      );
   }

   public void detectSettingsChangeAndReload() {
      if (this.player.method_37908().field_9236) {
         ((IBackpackWrapper)this.storageWrapper).getContentsUuid().ifPresent(uuid -> {
            BackpackStorage storage = BackpackStorage.get();
            if (storage.removeUpdatedBackpackSettingsFlag(uuid)) {
               ((IBackpackWrapper)this.storageWrapper).getSettingsHandler().reloadFrom(storage.getOrCreateBackpackContents(uuid));
            }
         });
      }
   }

   public void method_7623() {
      super.method_7623();
      this.sendBackpackSettingsToClient();
   }

   private void sendBackpackSettingsToClient() {
      if (!this.player.method_37908().field_9236) {
         if (this.lastSettingsNbt == null || !this.lastSettingsNbt.equals(((IBackpackWrapper)this.storageWrapper).getSettingsHandler().getNbt())) {
            this.lastSettingsNbt = ((IBackpackWrapper)this.storageWrapper).getSettingsHandler().getNbt().method_10553();
            ((IBackpackWrapper)this.storageWrapper).getContentsUuid().ifPresent(uuid -> {
               class_2487 settingsContents = new class_2487();
               class_2487 settingsNbt = ((IBackpackWrapper)this.storageWrapper).getSettingsHandler().getNbt();
               if (!settingsNbt.method_33133()) {
                  settingsContents.method_10566("settings", settingsNbt);
                  if (this.player instanceof class_3222 serverPlayer) {
                     PacketDistributor.sendToPlayer(serverPlayer, new BackpackContentsPayload(uuid, settingsContents));
                  }
               }
            });
         }
      }
   }

   @Override
   public BackpackContext getBackpackContext() {
      return this.backpackContext;
   }

   static {
      SettingsContainerMenu.addFactory("backpackGlobal", BackpackMainSettingsContainer::new);
   }
}
