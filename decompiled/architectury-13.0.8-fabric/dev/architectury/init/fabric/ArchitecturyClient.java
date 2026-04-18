package dev.architectury.init.fabric;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.networking.SpawnEntityPacket;
import net.minecraft.class_310;

public class ArchitecturyClient {
   public static void init() {
      LifecycleEvent.SETUP.invoker().run();
      ClientLifecycleEvent.CLIENT_SETUP.invoker().stateChanged(class_310.method_1551());
      SpawnEntityPacket.Client.register();
   }
}
