package dev.architectury.hooks.level.entity.fabric;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.minecraft.class_1657;

public interface FakePlayers {
   Event<FakePlayers> EVENT = EventFactory.createEventResult();

   EventResult isFakePlayer(class_1657 var1);
}
