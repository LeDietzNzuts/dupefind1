package dev.architectury.networking.transformers;

import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2596;
import net.minecraft.class_310;
import net.minecraft.class_3222;

@FunctionalInterface
public interface PacketSink {
   static PacketSink ofPlayer(class_3222 player) {
      return packet -> Objects.requireNonNull(player, "Unable to send packet to a 'null' player!").field_13987.method_14364(packet);
   }

   static PacketSink ofPlayers(Iterable<? extends class_3222> players) {
      return packet -> {
         for (class_3222 player : players) {
            Objects.requireNonNull(player, "Unable to send packet to a 'null' player!").field_13987.method_14364(packet);
         }
      };
   }

   @Environment(EnvType.CLIENT)
   static PacketSink client() {
      return packet -> {
         if (class_310.method_1551().method_1562() != null) {
            class_310.method_1551().method_1562().method_52787(packet);
         } else {
            throw new IllegalStateException("Unable to send packet to the server while not in game!");
         }
      };
   }

   void accept(class_2596<?> var1);
}
