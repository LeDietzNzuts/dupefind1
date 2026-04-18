package net.p3pp3rf1y.sophisticatedcore.api;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SortBy;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeHandler;
import net.p3pp3rf1y.sophisticatedcore.util.ITintable;
import team.reborn.energy.api.EnergyStorage;

public interface IStorageWrapper extends ITintable {
   void setContentsChangeHandler(Runnable var1);

   default void setInventorySlotChangeHandler(Runnable slotChangeHandler) {
   }

   ITrackedContentsItemHandler getInventoryForUpgradeProcessing();

   InventoryHandler getInventoryHandler();

   ITrackedContentsItemHandler getInventoryForInputOutput();

   default void setUpgradeCachesInvalidatedHandler(Runnable handler) {
   }

   SettingsHandler getSettingsHandler();

   UpgradeHandler getUpgradeHandler();

   Optional<UUID> getContentsUuid();

   Optional<Integer> getOpenTabId();

   void setOpenTabId(int var1);

   void removeOpenTabId();

   void setSortBy(SortBy var1);

   SortBy getSortBy();

   void sort();

   void onContentsNbtUpdated();

   void refreshInventoryForUpgradeProcessing();

   void refreshInventoryForInputOutput();

   void setPersistent(boolean var1);

   void fillWithLoot(class_1657 var1);

   RenderInfo getRenderInfo();

   void setColumnsTaken(int var1, boolean var2);

   int getColumnsTaken();

   default int getNumberOfSlotRows() {
      return 0;
   }

   default Optional<IStorageFluidHandler> getFluidHandler() {
      return Optional.empty();
   }

   default Optional<EnergyStorage> getEnergyStorage() {
      return Optional.empty();
   }

   default class_1799 getWrappedStorageStack() {
      return class_1799.field_8037;
   }

   default int getBaseStackSizeMultiplier() {
      return 1;
   }

   default void onInit() {
      this.getInventoryHandler().onInit();
   }

   String getStorageType();

   class_2561 getDisplayName();
}
