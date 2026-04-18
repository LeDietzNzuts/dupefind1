package dev.architectury.event.events.client;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1863;

@Environment(EnvType.CLIENT)
public interface ClientRecipeUpdateEvent {
   Event<ClientRecipeUpdateEvent> EVENT = EventFactory.createLoop();

   void update(class_1863 var1);
}
