package net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper;

import java.util.Optional;
import java.util.UUID;
import java.util.function.IntConsumer;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageFluidHandler;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.NoopStorageWrapper;

public interface IBackpackWrapper extends IStorageWrapper {
   IBackpackWrapper setBackpackStack(class_1799 var1);

   BackpackSettingsHandler getSettingsHandler();

   class_1799 getBackpack();

   class_1799 cloneBackpack();

   void copyDataTo(IStorageWrapper var1);

   void setSlotNumbers(int var1, int var2);

   void setLoot(class_2960 var1, float var2);

   void setContentsUuid(UUID var1);

   default void removeContentsUuid() {
   }

   default void removeContentsUUIDTag() {
   }

   default void registerOnSlotsChangeListener(IntConsumer onSlotsChange) {
   }

   default void unregisterOnSlotsChangeListener() {
   }

   default void registerOnInventoryHandlerRefreshListener(Runnable onInventoryHandlerRefresh) {
   }

   default void unregisterOnInventoryHandlerRefreshListener() {
   }

   default Optional<IStorageFluidHandler> getItemFluidHandler() {
      return Optional.empty();
   }

   public static class Noop extends NoopStorageWrapper implements IBackpackWrapper {
      public static final IBackpackWrapper.Noop INSTANCE = new IBackpackWrapper.Noop();
      private final class_1799 backpack = new class_1799((class_1935)ModItems.BACKPACK.get());
      private final BackpackSettingsHandler settingsHandler = new BackpackSettingsHandler(this, new class_2487(), () -> {});

      @Override
      public IBackpackWrapper setBackpackStack(class_1799 backpackStack) {
         return this;
      }

      @Override
      public BackpackSettingsHandler getSettingsHandler() {
         return this.settingsHandler;
      }

      @Override
      public class_1799 getBackpack() {
         return this.backpack;
      }

      @Override
      public class_1799 cloneBackpack() {
         return this.backpack;
      }

      @Override
      public void copyDataTo(IStorageWrapper otherStorageWrapper) {
      }

      @Override
      public void setSlotNumbers(int numberOfInventorySlots, int numberOfUpgradeSlots) {
      }

      @Override
      public void setLoot(class_2960 lootTableName, float lootPercentage) {
      }

      @Override
      public void setContentsUuid(UUID storageUuid) {
      }
   }
}
