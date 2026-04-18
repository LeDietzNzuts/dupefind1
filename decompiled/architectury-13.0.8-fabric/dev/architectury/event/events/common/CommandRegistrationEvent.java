package dev.architectury.event.events.common;

import com.mojang.brigadier.CommandDispatcher;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.class_2168;
import net.minecraft.class_7157;
import net.minecraft.class_2170.class_5364;

public interface CommandRegistrationEvent {
   Event<CommandRegistrationEvent> EVENT = EventFactory.createLoop();

   void register(CommandDispatcher<class_2168> var1, class_7157 var2, class_5364 var3);
}
