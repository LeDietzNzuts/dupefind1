package net.p3pp3rf1y.sophisticatedcore.controller;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public interface IControllableStorage extends IControllerBoundable {
   IStorageWrapper getStorageWrapper();

   @Override
   default boolean canConnectStorages() {
      return true;
   }

   default boolean hasStorageData() {
      return true;
   }

   default void tryToAddToController() {
      this.addToAdjacentController();
   }

   default void removeFromController() {
      class_1937 level = this.getStorageBlockLevel();
      if (!level.method_8608()) {
         this.getControllerPos()
            .flatMap(p -> WorldHelper.getBlockEntity(level, p, ControllerBlockEntityBase.class))
            .ifPresent(c -> c.removeStorage(this.getStorageBlockPos()));
         this.removeControllerPos();
      }
   }

   @Override
   default void addToController(class_1937 level, class_2338 pos, class_2338 controllerPos) {
      WorldHelper.getBlockEntity(level, controllerPos, ControllerBlockEntityBase.class).ifPresent(c -> c.addStorage(pos));
   }

   @Override
   default void registerController(ControllerBlockEntityBase controllerBlockEntity) {
      this.setControllerPos(controllerBlockEntity.method_11016());
      if (this.hasStorageData() && controllerBlockEntity.method_10997() != null && !controllerBlockEntity.method_10997().method_8608()) {
         this.registerListeners();
      }
   }

   @Override
   default void unregisterController() {
      this.removeControllerPos();
      this.getStorageWrapper().getInventoryForInputOutput().unregisterStackKeyListeners();
      this.getStorageWrapper().getSettingsHandler().getTypeCategory(MemorySettingsCategory.class).unregisterListeners();
      this.getStorageWrapper().getInventoryHandler().unregisterFilterItemsChangeListener();
   }

   private void registerListeners() {
      this.registerInventoryStackListeners();
      this.getStorageWrapper()
         .getSettingsHandler()
         .getTypeCategory(MemorySettingsCategory.class)
         .registerListeners(
            i -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.addStorageMemorizedItem(this.getStorageBlockPos(), i)),
            i -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.removeStorageMemorizedItem(this.getStorageBlockPos(), i)),
            i -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.addStorageMemorizedStack(this.getStorageBlockPos(), i)),
            i -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.removeStorageMemorizedStack(this.getStorageBlockPos(), i))
         );
      this.getStorageWrapper()
         .getInventoryHandler()
         .registerFilterItemsChangeListener(
            items -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.setStorageFilterItems(this.getStorageBlockPos(), items))
         );
   }

   default void registerInventoryStackListeners() {
      this.getStorageWrapper()
         .getInventoryForInputOutput()
         .registerTrackingListeners(
            isk -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.addStorageStack(this.getStorageBlockPos(), isk)),
            isk -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.removeStorageStack(this.getStorageBlockPos(), isk)),
            () -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.addStorageWithEmptySlots(this.getStorageBlockPos())),
            () -> this.runOnController(this.getStorageBlockLevel(), controller -> controller.removeStorageWithEmptySlots(this.getStorageBlockPos()))
         );
   }

   default void registerWithControllerOnLoad() {
      this.getControllerPos().ifPresent(controllerPos -> {
         class_1937 level = this.getStorageBlockLevel();
         if (!level.method_8608()) {
            WorldHelper.getLoadedBlockEntity(level, controllerPos, ControllerBlockEntityBase.class).ifPresent(controller -> {
               if (controller.isStorageConnected(this.getStorageBlockPos())) {
                  controller.addStorageStacksAndRegisterListeners(this.getStorageBlockPos());
               } else {
                  this.removeControllerPos();
                  this.tryToAddToController();
               }
            });
         }
      });
   }

   default void changeSlots(int newSlots) {
      this.getControllerPos()
         .ifPresent(
            controllerPos -> {
               class_1937 level = this.getStorageBlockLevel();
               if (!level.method_8608()) {
                  WorldHelper.getLoadedBlockEntity(level, controllerPos, ControllerBlockEntityBase.class)
                     .ifPresent(
                        controller -> controller.changeSlots(
                           this.getStorageBlockPos(), newSlots, this.getStorageWrapper().getInventoryForInputOutput().hasEmptySlots()
                        )
                     );
               }
            }
         );
   }

   default void updateEmptySlots() {
      this.getControllerPos()
         .ifPresent(
            controllerPos -> {
               class_1937 level = this.getStorageBlockLevel();
               if (!level.method_8608()) {
                  WorldHelper.getLoadedBlockEntity(level, controllerPos, ControllerBlockEntityBase.class)
                     .ifPresent(
                        controller -> controller.updateEmptySlots(
                           this.getStorageBlockPos(), this.getStorageWrapper().getInventoryForInputOutput().hasEmptySlots()
                        )
                     );
               }
            }
         );
   }
}
