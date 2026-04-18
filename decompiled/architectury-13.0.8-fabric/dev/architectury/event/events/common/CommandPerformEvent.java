package dev.architectury.event.events.common;

import com.mojang.brigadier.ParseResults;
import dev.architectury.event.Event;
import dev.architectury.event.EventActor;
import dev.architectury.event.EventFactory;
import net.minecraft.class_2168;
import org.jetbrains.annotations.Nullable;

public class CommandPerformEvent {
   public static final Event<EventActor<CommandPerformEvent>> EVENT = EventFactory.createEventActorLoop();
   private ParseResults<class_2168> results;
   @Nullable
   private Throwable throwable;

   public CommandPerformEvent(ParseResults<class_2168> results, @Nullable Throwable throwable) {
      this.results = results;
      this.throwable = throwable;
   }

   public ParseResults<class_2168> getResults() {
      return this.results;
   }

   public void setResults(ParseResults<class_2168> results) {
      this.results = results;
   }

   @Nullable
   public Throwable getThrowable() {
      return this.throwable;
   }

   public void setThrowable(@Nullable Throwable throwable) {
      this.throwable = throwable;
   }
}
