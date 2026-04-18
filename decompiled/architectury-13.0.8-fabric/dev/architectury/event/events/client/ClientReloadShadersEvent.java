package dev.architectury.event.events.client;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5912;
import net.minecraft.class_5944;

@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface ClientReloadShadersEvent {
   Event<ClientReloadShadersEvent> EVENT = EventFactory.createLoop();

   void reload(class_5912 var1, ClientReloadShadersEvent.ShadersSink var2);

   @FunctionalInterface
   public interface ShadersSink {
      void registerShader(class_5944 var1, Consumer<class_5944> var2);
   }
}
