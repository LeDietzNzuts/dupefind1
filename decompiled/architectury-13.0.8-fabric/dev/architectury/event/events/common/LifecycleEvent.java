package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import net.minecraft.server.MinecraftServer;

public interface LifecycleEvent {
   Event<LifecycleEvent.ServerState> SERVER_BEFORE_START = EventFactory.createLoop();
   Event<LifecycleEvent.ServerState> SERVER_STARTING = EventFactory.createLoop();
   Event<LifecycleEvent.ServerState> SERVER_STARTED = EventFactory.createLoop();
   Event<LifecycleEvent.ServerState> SERVER_STOPPING = EventFactory.createLoop();
   Event<LifecycleEvent.ServerState> SERVER_STOPPED = EventFactory.createLoop();
   Event<LifecycleEvent.ServerLevelState> SERVER_LEVEL_LOAD = EventFactory.createLoop();
   Event<LifecycleEvent.ServerLevelState> SERVER_LEVEL_UNLOAD = EventFactory.createLoop();
   Event<LifecycleEvent.ServerLevelState> SERVER_LEVEL_SAVE = EventFactory.createLoop();
   Event<Runnable> SETUP = EventFactory.createLoop();

   public interface InstanceState<T> {
      void stateChanged(T var1);
   }

   public interface LevelState<T extends class_1937> {
      void act(T var1);
   }

   public interface ServerLevelState extends LifecycleEvent.LevelState<class_3218> {
   }

   public interface ServerState extends LifecycleEvent.InstanceState<MinecraftServer> {
   }
}
