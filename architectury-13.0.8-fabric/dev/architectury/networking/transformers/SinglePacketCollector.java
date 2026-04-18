package dev.architectury.networking.transformers;

import java.util.function.Consumer;
import net.minecraft.class_2596;
import org.jetbrains.annotations.Nullable;

public class SinglePacketCollector implements PacketSink {
   @Nullable
   private final Consumer<class_2596<?>> consumer;
   private class_2596<?> packet;

   public SinglePacketCollector(@Nullable Consumer<class_2596<?>> consumer) {
      this.consumer = consumer;
   }

   @Override
   public void accept(class_2596<?> packet) {
      if (this.packet == null) {
         this.packet = packet;
         if (this.consumer != null) {
            this.consumer.accept(packet);
         }
      } else {
         throw new IllegalStateException("Already accepted one packet!");
      }
   }

   public class_2596<?> getPacket() {
      return this.packet;
   }
}
