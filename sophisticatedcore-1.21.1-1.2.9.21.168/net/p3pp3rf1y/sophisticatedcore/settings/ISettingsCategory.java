package net.p3pp3rf1y.sophisticatedcore.settings;

import net.minecraft.class_2487;

public interface ISettingsCategory<T extends ISettingsCategory<?>> {
   void reloadFrom(class_2487 var1);

   void overwriteWith(T var1);

   boolean isLargerThanNumberOfSlots(int var1);

   void copyTo(T var1, int var2, int var3);

   void deleteSlotSettingsFrom(int var1);
}
