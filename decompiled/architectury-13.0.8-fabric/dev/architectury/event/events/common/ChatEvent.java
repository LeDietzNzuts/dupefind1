package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import org.jetbrains.annotations.Nullable;

public interface ChatEvent {
   Event<ChatEvent.Decorate> DECORATE = EventFactory.createLoop();
   Event<ChatEvent.Received> RECEIVED = EventFactory.createEventResult();

   public interface ChatComponent {
      class_2561 get();

      void set(class_2561 var1);
   }

   @FunctionalInterface
   public interface Decorate {
      void decorate(@Nullable class_3222 var1, ChatEvent.ChatComponent var2);
   }

   @FunctionalInterface
   public interface Received {
      EventResult received(@Nullable class_3222 var1, class_2561 var2);
   }
}
