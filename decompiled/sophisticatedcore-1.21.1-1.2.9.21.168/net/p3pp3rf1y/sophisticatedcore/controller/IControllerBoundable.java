package net.p3pp3rf1y.sophisticatedcore.controller;

import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public interface IControllerBoundable {
   String CONTROLLER_POS_TAG = "controllerPos";

   void setControllerPos(class_2338 var1);

   Optional<class_2338> getControllerPos();

   void removeControllerPos();

   class_2338 getStorageBlockPos();

   class_1937 getStorageBlockLevel();

   default boolean canBeConnected() {
      return this.getControllerPos().isEmpty();
   }

   void registerController(ControllerBlockEntityBase var1);

   void unregisterController();

   boolean canConnectStorages();

   default void runOnController(class_1937 level, Consumer<ControllerBlockEntityBase> toRun) {
      this.getControllerPos().flatMap(pos -> WorldHelper.getLoadedBlockEntity(level, pos, ControllerBlockEntityBase.class)).ifPresent(toRun);
   }

   default void saveControllerPos(class_2487 tag) {
      this.getControllerPos().ifPresent(p -> tag.method_10544("controllerPos", p.method_10063()));
   }

   default void loadControllerPos(class_2487 tag) {
      NBTHelper.getLong(tag, "controllerPos").ifPresent(value -> {
         class_2338 controllerPos = class_2338.method_10092(value);
         this.setControllerPos(controllerPos);
      });
   }

   default void addToController(class_1937 level, class_2338 pos, class_2338 controllerPos) {
   }

   default void addToAdjacentController() {
      class_1937 level = this.getStorageBlockLevel();
      if (!level.method_8608()) {
         class_2338 pos = this.getStorageBlockPos();

         for (class_2350 dir : class_2350.values()) {
            class_2338 offsetPos = pos.method_10081(dir.method_10163());
            WorldHelper.getBlockEntity(level, offsetPos, IControllerBoundable.class).ifPresentOrElse(s -> {
               if (s.canConnectStorages()) {
                  s.getControllerPos().ifPresent(controllerPos -> this.addToController(level, pos, controllerPos));
               }
            }, () -> this.addToController(level, pos, offsetPos));
            if (this.getControllerPos().isPresent()) {
               break;
            }
         }
      }
   }
}
