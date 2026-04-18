package dev.architectury.networking.simple;

import dev.architectury.networking.NetworkManager;
import net.minecraft.class_9129;

@FunctionalInterface
public interface MessageDecoder<T extends Message> {
   T decode(class_9129 var1);

   default NetworkManager.NetworkReceiver<class_9129> createReceiver() {
      return (buf, context) -> {
         Message packet = this.decode(buf);
         context.queue(() -> packet.handle(context));
      };
   }
}
