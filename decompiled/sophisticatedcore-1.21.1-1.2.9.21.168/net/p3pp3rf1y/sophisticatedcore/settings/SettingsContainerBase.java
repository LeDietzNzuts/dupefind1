package net.p3pp3rf1y.sophisticatedcore.settings;

import java.util.function.Supplier;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerClientDataPayload;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public abstract class SettingsContainerBase<C extends ISettingsCategory<?>> {
   private final SettingsContainerMenu<?> settingsContainer;
   private final String categoryName;
   private final C category;

   protected SettingsContainerBase(SettingsContainerMenu<?> settingsContainer, String categoryName, C category) {
      this.settingsContainer = settingsContainer;
      this.categoryName = categoryName;
      this.category = category;
   }

   protected C getCategory() {
      return this.category;
   }

   public SettingsContainerMenu<?> getSettingsContainer() {
      return this.settingsContainer;
   }

   public void sendIntToServer(String key, int value) {
      this.sendDataToServer(() -> {
         class_2487 data = new class_2487();
         data.method_10569(key, value);
         return data;
      });
   }

   public void sendStringToServer(String key, String value) {
      this.sendDataToServer(() -> {
         class_2487 data = new class_2487();
         data.method_10582(key, value);
         return data;
      });
   }

   public void sendBooleanToServer(String key, boolean value) {
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), key, value));
   }

   public void sendDataToServer(Supplier<class_2487> supplyData) {
      if (!this.isServer()) {
         class_2487 data = supplyData.get();
         data.method_10582("categoryName", this.categoryName);
         PacketDistributor.sendToServer(new SyncContainerClientDataPayload(data));
      }
   }

   protected boolean isServer() {
      return !this.settingsContainer.getPlayer().method_37908().field_9236;
   }

   public abstract void handlePacket(class_2487 var1);
}
