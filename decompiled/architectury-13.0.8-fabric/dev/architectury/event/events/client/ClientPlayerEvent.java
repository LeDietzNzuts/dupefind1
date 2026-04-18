package dev.architectury.event.events.client;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_746;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface ClientPlayerEvent {
   Event<ClientPlayerEvent.ClientPlayerJoin> CLIENT_PLAYER_JOIN = EventFactory.createLoop();
   Event<ClientPlayerEvent.ClientPlayerQuit> CLIENT_PLAYER_QUIT = EventFactory.createLoop();
   Event<ClientPlayerEvent.ClientPlayerRespawn> CLIENT_PLAYER_RESPAWN = EventFactory.createLoop();

   @Environment(EnvType.CLIENT)
   public interface ClientPlayerJoin {
      void join(class_746 var1);
   }

   @Environment(EnvType.CLIENT)
   public interface ClientPlayerQuit {
      void quit(@Nullable class_746 var1);
   }

   @Environment(EnvType.CLIENT)
   public interface ClientPlayerRespawn {
      void respawn(class_746 var1, class_746 var2);
   }
}
