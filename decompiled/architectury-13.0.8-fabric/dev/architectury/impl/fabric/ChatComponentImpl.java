package dev.architectury.impl.fabric;

import dev.architectury.event.events.common.ChatEvent;
import net.minecraft.class_2561;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class ChatComponentImpl implements ChatEvent.ChatComponent {
   private class_2561 component;

   public ChatComponentImpl(class_2561 component) {
      this.component = component;
   }

   @Override
   public class_2561 get() {
      return this.component;
   }

   @Override
   public void set(class_2561 component) {
      this.component = component;
   }
}
