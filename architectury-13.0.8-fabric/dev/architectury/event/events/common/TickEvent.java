package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import net.minecraft.server.MinecraftServer;

public interface TickEvent<T> {
   Event<TickEvent.Server> SERVER_PRE = EventFactory.createLoop();
   Event<TickEvent.Server> SERVER_POST = EventFactory.createLoop();
   Event<TickEvent.ServerLevelTick> SERVER_LEVEL_PRE = EventFactory.createLoop();
   Event<TickEvent.ServerLevelTick> SERVER_LEVEL_POST = EventFactory.createLoop();
   Event<TickEvent.Player> PLAYER_PRE = EventFactory.createLoop();
   Event<TickEvent.Player> PLAYER_POST = EventFactory.createLoop();

   void tick(T var1);

   public interface LevelTick<T extends class_1937> extends TickEvent<T> {
   }

   public interface Player extends TickEvent<class_1657> {
   }

   public interface Server extends TickEvent<MinecraftServer> {
   }

   public interface ServerLevelTick extends TickEvent.LevelTick<class_3218> {
   }
}
