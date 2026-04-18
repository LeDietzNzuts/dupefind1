package net.p3pp3rf1y.sophisticatedcore.controller;

import java.util.Optional;
import java.util.Set;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public interface ILinkable extends IControllerBoundable {
   default void linkToController(class_2338 controllerPos) {
      if (this.canBeLinked() && !this.isLinked()) {
         class_1937 level = this.getStorageBlockLevel();
         if (!level.method_8608() && WorldHelper.getLoadedBlockEntity(level, controllerPos, ControllerBlockEntityBase.class).isPresent()) {
            boolean hadControllerPos = this.getControllerPos().isPresent();
            if (!hadControllerPos) {
               this.setControllerPos(controllerPos);
            }

            this.runOnController(level, controller -> {
               if (!controller.addLinkedBlock(this.getStorageBlockPos()) && !hadControllerPos) {
                  this.removeControllerPos();
               }
            });
         }
      }
   }

   default void unlinkFromController() {
      class_1937 level = this.getStorageBlockLevel();
      if (!level.method_8608() && this.isLinked()) {
         this.runOnController(level, controller -> controller.removeLinkedBlock(this.getStorageBlockPos()));
         this.removeControllerPos();
         this.setNotLinked();
      }
   }

   default void setNotLinked() {
   }

   Set<class_2338> getConnectablePositions();

   boolean connectLinkedSelf();

   @Override
   void setControllerPos(class_2338 var1);

   @Override
   Optional<class_2338> getControllerPos();

   boolean isLinked();

   default boolean canBeLinked() {
      return true;
   }
}
