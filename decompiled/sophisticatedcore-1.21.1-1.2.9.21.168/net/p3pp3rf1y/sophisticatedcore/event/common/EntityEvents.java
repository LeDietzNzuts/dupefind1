package net.p3pp3rf1y.sophisticatedcore.event.common;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1297;
import net.minecraft.class_1937;

public interface EntityEvents {
   Event<EntityEvents.JoinWorld> ON_JOIN_WORLD = EventFactory.createArrayBacked(EntityEvents.JoinWorld.class, callbacks -> (entity, world, loadedFromDisk) -> {
      for (EntityEvents.JoinWorld callback : callbacks) {
         if (!callback.onJoinWorld(entity, world, loadedFromDisk)) {
            return true;
         }
      }

      return false;
   });

   @FunctionalInterface
   public interface JoinWorld {
      boolean onJoinWorld(class_1297 var1, class_1937 var2, boolean var3);
   }
}
