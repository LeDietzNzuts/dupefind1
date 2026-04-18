package dev.architectury.init.fabric;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.networking.SpawnEntityPacket;

public class ArchitecturyServer {
   public static void init() {
      LifecycleEvent.SETUP.invoker().run();
      SpawnEntityPacket.register();
   }
}
