package dev.architectury.event.events.client;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.events.common.LifecycleEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_638;

@Environment(EnvType.CLIENT)
public interface ClientLifecycleEvent {
   Event<ClientLifecycleEvent.ClientState> CLIENT_STARTED = EventFactory.createLoop();
   Event<ClientLifecycleEvent.ClientState> CLIENT_STOPPING = EventFactory.createLoop();
   Event<ClientLifecycleEvent.ClientLevelState> CLIENT_LEVEL_LOAD = EventFactory.createLoop();
   Event<ClientLifecycleEvent.ClientState> CLIENT_SETUP = EventFactory.createLoop();

   @Environment(EnvType.CLIENT)
   public interface ClientLevelState extends LifecycleEvent.LevelState<class_638> {
   }

   @Environment(EnvType.CLIENT)
   public interface ClientState extends LifecycleEvent.InstanceState<class_310> {
   }
}
