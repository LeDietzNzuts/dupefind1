package dev.architectury.networking.transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.class_2596;
import org.jetbrains.annotations.Nullable;

public class PacketCollector implements PacketSink {
   @Nullable
   private final Consumer<class_2596<?>> consumer;
   private final List<class_2596<?>> packets = new ArrayList<>();

   public PacketCollector(@Nullable Consumer<class_2596<?>> consumer) {
      this.consumer = consumer;
   }

   @Override
   public void accept(class_2596<?> packet) {
      this.packets.add(packet);
      if (this.consumer != null) {
         this.consumer.accept(packet);
      }
   }

   public List<class_2596<?>> collect() {
      return this.packets;
   }
}
