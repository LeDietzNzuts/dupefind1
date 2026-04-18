package dev.architectury.event.events.client;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;

@Environment(EnvType.CLIENT)
public interface ClientSystemMessageEvent {
   Event<ClientSystemMessageEvent.Received> RECEIVED = EventFactory.createCompoundEventResult();

   @Environment(EnvType.CLIENT)
   public interface Received {
      CompoundEventResult<class_2561> process(class_2561 var1);
   }
}
