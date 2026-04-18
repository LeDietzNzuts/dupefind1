package net.p3pp3rf1y.sophisticatedcore.upgrades;

import net.minecraft.class_1799;

public interface IUpgradeWrapper {
   boolean isEnabled();

   void setEnabled(boolean var1);

   default boolean canBeDisabled() {
      return true;
   }

   class_1799 getUpgradeStack();

   default boolean hideSettingsTab() {
      return false;
   }

   default void onBeforeRemoved() {
   }

   default void onAdded() {
   }
}
