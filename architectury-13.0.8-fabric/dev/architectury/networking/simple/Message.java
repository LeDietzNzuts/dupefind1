package dev.architectury.networking.simple;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.class_2596;
import net.minecraft.class_5455;
import net.minecraft.class_9129;

public abstract class Message {
   Message() {
   }

   public abstract MessageType getType();

   public abstract void write(class_9129 var1);

   public abstract void handle(NetworkManager.PacketContext var1);

   public final class_2596<?> toPacket(class_5455 access) {
      class_9129 buf = new class_9129(Unpooled.buffer(), access);
      this.write(buf);
      return NetworkManager.toPacket(this.getType().getSide(), this.getType().getId(), buf);
   }
}
