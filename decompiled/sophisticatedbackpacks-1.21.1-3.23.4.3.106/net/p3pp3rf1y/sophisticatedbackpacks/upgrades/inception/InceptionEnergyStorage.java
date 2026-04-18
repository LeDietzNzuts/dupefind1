package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import team.reborn.energy.api.EnergyStorage;

public class InceptionEnergyStorage implements EnergyStorage {
   @Nullable
   private final EnergyStorage wrappedEnergyStorage;
   private final InventoryOrder inventoryOrder;
   private final SubBackpacksHandler subBackpacksHandler;
   private EnergyStorage[] energyStorages;

   public InceptionEnergyStorage(@Nullable EnergyStorage wrappedEnergyStorage, InventoryOrder inventoryOrder, SubBackpacksHandler subBackpacksHandler) {
      this.wrappedEnergyStorage = wrappedEnergyStorage;
      this.inventoryOrder = inventoryOrder;
      this.subBackpacksHandler = subBackpacksHandler;
      subBackpacksHandler.addRefreshListener(sbs -> this.refreshHandlers());
      this.refreshHandlers();
   }

   private void refreshHandlers() {
      List<EnergyStorage> storages = new ArrayList<>();
      if (this.wrappedEnergyStorage != null && this.inventoryOrder == InventoryOrder.MAIN_FIRST) {
         storages.add(this.wrappedEnergyStorage);
      }

      this.subBackpacksHandler.getSubBackpacks().forEach(sbp -> sbp.getEnergyStorage().ifPresent(storages::add));
      if (this.wrappedEnergyStorage != null && this.inventoryOrder == InventoryOrder.INCEPTED_FIRST) {
         storages.add(this.wrappedEnergyStorage);
      }

      this.energyStorages = storages.toArray(new EnergyStorage[0]);
   }

   public long insert(long maxAmount, TransactionContext transaction) {
      long totalReceived = 0L;

      for (EnergyStorage storage : this.energyStorages) {
         totalReceived += storage.insert(maxAmount - totalReceived, transaction);
         if (totalReceived == maxAmount) {
            break;
         }
      }

      return totalReceived;
   }

   public long extract(long maxAmount, TransactionContext transaction) {
      long totalExtracted = 0L;

      for (EnergyStorage storage : this.energyStorages) {
         totalExtracted += storage.extract(maxAmount - totalExtracted, transaction);
         if (totalExtracted == maxAmount) {
            break;
         }
      }

      return totalExtracted;
   }

   public long getAmount() {
      long totalEnergyStored = 0L;

      for (EnergyStorage storage : this.energyStorages) {
         totalEnergyStored += storage.getAmount();
      }

      return totalEnergyStored;
   }

   public long getCapacity() {
      long totalMaxEnergy = 0L;

      for (EnergyStorage storage : this.energyStorages) {
         if (totalMaxEnergy > 2147483647L - storage.getCapacity()) {
            return 2147483647L;
         }

         totalMaxEnergy += storage.getCapacity();
      }

      return totalMaxEnergy;
   }

   public boolean supportsExtraction() {
      return this.energyStorages.length > 0;
   }

   public boolean supportsInsertion() {
      return this.energyStorages.length > 0;
   }
}
