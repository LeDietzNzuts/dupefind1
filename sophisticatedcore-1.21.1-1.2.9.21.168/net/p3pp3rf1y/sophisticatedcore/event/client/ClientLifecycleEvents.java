package net.p3pp3rf1y.sophisticatedcore.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_310;
import net.minecraft.class_638;

@Environment(EnvType.CLIENT)
public interface ClientLifecycleEvents {
   Event<ClientLifecycleEvents.Load> CLIENT_LEVEL_LOAD = EventFactory.createArrayBacked(ClientLifecycleEvents.Load.class, callback -> (client, world) -> {
      for (ClientLifecycleEvents.Load event : callback) {
         event.onWorldLoad(client, world);
      }
   });

   @FunctionalInterface
   public interface Load {
      void onWorldLoad(class_310 var1, class_638 var2);
   }
}
