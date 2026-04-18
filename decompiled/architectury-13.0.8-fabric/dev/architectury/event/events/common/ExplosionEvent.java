package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_1937;

public interface ExplosionEvent {
   Event<ExplosionEvent.Pre> PRE = EventFactory.createEventResult();
   Event<ExplosionEvent.Detonate> DETONATE = EventFactory.createLoop();

   public interface Detonate {
      void explode(class_1937 var1, class_1927 var2, List<class_1297> var3);
   }

   public interface Pre {
      EventResult explode(class_1937 var1, class_1927 var2);
   }
}
