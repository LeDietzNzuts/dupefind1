package net.p3pp3rf1y.sophisticatedbackpacks.settings;

import java.util.function.Consumer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.class_2487;
import net.minecraft.class_3222;
import net.p3pp3rf1y.sophisticatedcore.settings.MainSetting;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsManager;
import net.p3pp3rf1y.sophisticatedcore.settings.main.MainSettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class BackpackMainSettingsCategory extends MainSettingsCategory<BackpackMainSettingsCategory> {
   public static final String SOPHISTICATED_BACKPACK_SETTINGS_PLAYER_TAG = "sophisticatedBackpackSettings";
   public static final MainSetting<Boolean> ANOTHER_PLAYER_CAN_OPEN = new MainSetting(
      "anotherPlayerCanOpen", NBTHelper::getBoolean, class_2487::method_10556, true
   );
   public static final String NAME = "backpackGlobal";

   public BackpackMainSettingsCategory(class_2487 categoryNbt, Consumer<class_2487> saveNbt) {
      super(categoryNbt, saveNbt, "sophisticatedBackpackSettings");
   }

   private static void onPlayerClone(class_3222 oldPlayer, class_3222 newPlayer, boolean wasDeath) {
      class_2487 oldData = oldPlayer.getSophisticatedCustomData();
      class_2487 newData = newPlayer.getSophisticatedCustomData();
      if (oldData.method_10545("sophisticatedBackpackSettings")) {
         newData.method_10566("sophisticatedBackpackSettings", oldData.method_10580("sophisticatedBackpackSettings"));
      }
   }

   static {
      SettingsManager.addSetting(ANOTHER_PLAYER_CAN_OPEN);
      ServerPlayerEvents.COPY_FROM.register(BackpackMainSettingsCategory::onPlayerClone);
   }
}
