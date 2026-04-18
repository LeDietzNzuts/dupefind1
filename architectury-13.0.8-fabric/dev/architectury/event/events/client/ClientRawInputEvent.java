package dev.architectury.event.events.client;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public interface ClientRawInputEvent {
   Event<ClientRawInputEvent.MouseScrolled> MOUSE_SCROLLED = EventFactory.createEventResult();
   Event<ClientRawInputEvent.MouseClicked> MOUSE_CLICKED_PRE = EventFactory.createEventResult();
   Event<ClientRawInputEvent.MouseClicked> MOUSE_CLICKED_POST = EventFactory.createEventResult();
   Event<ClientRawInputEvent.KeyPressed> KEY_PRESSED = EventFactory.createEventResult();

   public interface KeyPressed {
      EventResult keyPressed(class_310 var1, int var2, int var3, int var4, int var5);
   }

   public interface MouseClicked {
      EventResult mouseClicked(class_310 var1, int var2, int var3, int var4);
   }

   public interface MouseScrolled {
      EventResult mouseScrolled(class_310 var1, double var2, double var4);
   }
}
