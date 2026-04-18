package dev.architectury.event.fabric;

import dev.architectury.event.Event;
import dev.architectury.event.EventActor;
import java.util.function.Consumer;
import org.jetbrains.annotations.ApiStatus.Internal;

public class EventFactoryImpl {
   public static <T> Event<Consumer<T>> attachToForge(Event<Consumer<T>> event) {
      return event;
   }

   @Internal
   public static <T> Event<EventActor<T>> attachToForgeEventActor(Event<EventActor<T>> event) {
      return event;
   }

   @Internal
   public static <T> Event<EventActor<T>> attachToForgeEventActorCancellable(Event<EventActor<T>> event) {
      return event;
   }
}
