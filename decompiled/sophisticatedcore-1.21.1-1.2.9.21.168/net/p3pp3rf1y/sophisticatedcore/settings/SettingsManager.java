package net.p3pp3rf1y.sophisticatedcore.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.settings.main.MainSettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class SettingsManager {
   private static final Map<String, MainSetting<?>> settings = new HashMap<>();
   public static final MainSetting<Boolean> SHIFT_CLICK_INTO_OPEN_TAB_FIRST = new MainSetting<>(
      "shiftClickOpenTab", NBTHelper::getBoolean, class_2487::method_10556, true
   );
   public static final MainSetting<Boolean> KEEP_TAB_OPEN = new MainSetting<>("keepTabOpen", NBTHelper::getBoolean, class_2487::method_10556, true);
   public static final MainSetting<Boolean> KEEP_SEARCH_PHRASE = new MainSetting<>("keepSearchPhrase", NBTHelper::getBoolean, class_2487::method_10556, true);
   public static final MainSetting<String> SEARCH_PHRASE = new MainSetting<>("searchPhrase", NBTHelper::getString, class_2487::method_10582, "");

   private SettingsManager() {
   }

   public static void addSetting(MainSetting<?> setting) {
      settings.put(setting.getName(), setting);
   }

   public static Optional<MainSetting<?>> getSetting(String settingName) {
      return Optional.ofNullable(settings.get(settingName));
   }

   public static <T> T getPlayerSettingOrDefault(class_1657 player, String playerSettingsTagName, MainSetting<T> setting) {
      return getPlayerSetting(player, playerSettingsTagName, setting).orElse(setting.getDefaultValue());
   }

   public static <T> Optional<T> getPlayerSetting(class_1657 player, String playerSettingsTagName, MainSetting<T> setting) {
      return setting.getValue(getPlayerSettingsTag(player, playerSettingsTagName));
   }

   public static class_2487 getPlayerSettingsTag(class_1657 player, String playerSettingsTagName) {
      return player.getSophisticatedCustomData().method_10562(playerSettingsTagName);
   }

   public static void setPlayerSettingsTag(class_1657 player, String playerSettingsTagName, class_2487 settingsNbt) {
      player.getSophisticatedCustomData().method_10566(playerSettingsTagName, settingsNbt);
   }

   public static <T> void setPlayerSetting(class_1657 player, String playerSettingsTagName, MainSetting<T> setting, T value) {
      if (!player.getSophisticatedCustomData().method_10545(playerSettingsTagName)) {
         player.getSophisticatedCustomData().method_10566(playerSettingsTagName, new class_2487());
      }

      if (value != setting.getDefaultValue()) {
         setting.setValue(getPlayerSettingsTag(player, playerSettingsTagName), value);
      } else {
         setting.removeFrom(getPlayerSettingsTag(player, playerSettingsTagName));
      }
   }

   public static <T> void setSetting(class_1657 player, String playerSettingsTagName, MainSettingsCategory<?> category, MainSetting<T> setting, T value) {
      T playerSettingValue = getPlayerSetting(player, playerSettingsTagName, setting).orElse(setting.getDefaultValue());
      if (playerSettingValue != value) {
         category.setSettingValue(setting, value);
      } else {
         category.removeSetting(setting);
      }
   }

   public static <T> T getSettingValue(class_1657 player, String playerSettingsTagName, MainSettingsCategory<?> category, MainSetting<T> setting) {
      return category.getSettingValue(setting).orElse(getPlayerSetting(player, playerSettingsTagName, setting).orElse(setting.getDefaultValue()));
   }

   static {
      settings.put(SHIFT_CLICK_INTO_OPEN_TAB_FIRST.getName(), SHIFT_CLICK_INTO_OPEN_TAB_FIRST);
      settings.put(KEEP_TAB_OPEN.getName(), KEEP_TAB_OPEN);
      settings.put(KEEP_SEARCH_PHRASE.getName(), KEEP_SEARCH_PHRASE);
      settings.put(SEARCH_PHRASE.getName(), SEARCH_PHRASE);
   }
}
