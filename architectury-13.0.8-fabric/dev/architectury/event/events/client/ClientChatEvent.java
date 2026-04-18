package dev.architectury.event.events.client;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2556.class_7602;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface ClientChatEvent {
   Event<ClientChatEvent.Send> SEND = EventFactory.createEventResult();
   Event<ClientChatEvent.Received> RECEIVED = EventFactory.createCompoundEventResult();

   @Environment(EnvType.CLIENT)
   public interface Received {
      CompoundEventResult<class_2561> process(class_7602 var1, class_2561 var2);
   }

   @Environment(EnvType.CLIENT)
   public interface Send {
      EventResult send(String var1, @Nullable class_2561 var2);
   }
}
