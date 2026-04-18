package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1538;
import net.minecraft.class_1937;
import net.minecraft.class_243;

public interface LightningEvent {
   Event<LightningEvent.Strike> STRIKE = EventFactory.createLoop();

   public interface Strike {
      void onStrike(class_1538 var1, class_1937 var2, class_243 var3, List<class_1297> var4);
   }
}
