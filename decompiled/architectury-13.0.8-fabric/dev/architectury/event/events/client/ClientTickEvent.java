package dev.architectury.event.events.client;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_638;

@Environment(EnvType.CLIENT)
public interface ClientTickEvent<T> {
   Event<ClientTickEvent.Client> CLIENT_PRE = EventFactory.createLoop();
   Event<ClientTickEvent.Client> CLIENT_POST = EventFactory.createLoop();
   Event<ClientTickEvent.ClientLevel> CLIENT_LEVEL_PRE = EventFactory.createLoop();
   Event<ClientTickEvent.ClientLevel> CLIENT_LEVEL_POST = EventFactory.createLoop();

   void tick(T var1);

   @Environment(EnvType.CLIENT)
   public interface Client extends ClientTickEvent<class_310> {
   }

   @Environment(EnvType.CLIENT)
   public interface ClientLevel extends ClientTickEvent<class_638> {
   }
}
