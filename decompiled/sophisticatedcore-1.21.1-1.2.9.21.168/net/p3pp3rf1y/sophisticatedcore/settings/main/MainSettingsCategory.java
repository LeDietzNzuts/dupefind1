package net.p3pp3rf1y.sophisticatedcore.settings.main;

import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.settings.ISettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.MainSetting;

public class MainSettingsCategory<T extends MainSettingsCategory<?>> implements ISettingsCategory<T> {
   public static final String NAME = "global";
   private class_2487 categoryNbt;
   private final Consumer<class_2487> saveNbt;
   private final String playerSettingsTagName;

   public MainSettingsCategory(class_2487 categoryNbt, Consumer<class_2487> saveNbt, String playerSettingsTagName) {
      this.categoryNbt = categoryNbt;
      this.saveNbt = saveNbt;
      this.playerSettingsTagName = playerSettingsTagName;
   }

   public String getPlayerSettingsTagName() {
      return this.playerSettingsTagName;
   }

   public <S> Optional<S> getSettingValue(MainSetting<S> setting) {
      return setting.getValue(this.categoryNbt);
   }

   public <S> void setSettingValue(MainSetting<S> setting, S value) {
      setting.setValue(this.categoryNbt, value);
      this.saveNbt.accept(this.categoryNbt);
   }

   public <S> void removeSetting(MainSetting<S> setting) {
      setting.removeFrom(this.categoryNbt);
      this.saveNbt.accept(this.categoryNbt);
   }

   @Override
   public void reloadFrom(class_2487 categoryNbt) {
      this.categoryNbt = categoryNbt;
   }

   public void overwriteWith(T otherCategory) {
   }

   @Override
   public boolean isLargerThanNumberOfSlots(int slots) {
      return false;
   }

   public void copyTo(T otherCategory, int startFromSlot, int slotOffset) {
   }

   @Override
   public void deleteSlotSettingsFrom(int slotIndex) {
   }
}
