package net.p3pp3rf1y.sophisticatedcore.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1863;

@Environment(EnvType.CLIENT)
public interface ClientRecipesUpdated {
   Event<ClientRecipesUpdated> EVENT = EventFactory.createArrayBacked(ClientRecipesUpdated.class, callbacks -> manager -> {
      for (ClientRecipesUpdated event : callbacks) {
         event.onRecipesUpdated(manager);
      }
   });

   void onRecipesUpdated(class_1863 var1);
}
