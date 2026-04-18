package net.p3pp3rf1y.sophisticatedcore.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1269;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public interface ClientRawInputEvent {
   Event<ClientRawInputEvent.KeyPressed> KEY_PRESSED = EventFactory.createArrayBacked(
      ClientRawInputEvent.KeyPressed.class, callbacks -> (client, keyCode, scanCode, action, modifiers) -> {
         for (ClientRawInputEvent.KeyPressed event : callbacks) {
            class_1269 result = event.keyPressed(client, keyCode, scanCode, action, modifiers);
            if (result != class_1269.field_5811) {
               return result;
            }
         }

         return class_1269.field_5811;
      }
   );
   Event<ClientRawInputEvent.MouseScrolled> MOUSE_SCROLLED = EventFactory.createArrayBacked(
      ClientRawInputEvent.MouseScrolled.class, callbacks -> (client, deltaX, deltaY) -> {
         for (ClientRawInputEvent.MouseScrolled event : callbacks) {
            class_1269 result = event.mouseScrolled(client, deltaX, deltaY);
            if (result != class_1269.field_5811) {
               return result;
            }
         }

         return class_1269.field_5811;
      }
   );

   @FunctionalInterface
   public interface KeyPressed {
      class_1269 keyPressed(class_310 var1, int var2, int var3, int var4, int var5);
   }

   @FunctionalInterface
   public interface MouseScrolled {
      class_1269 mouseScrolled(class_310 var1, double var2, double var4);
   }
}
