package dev.architectury.networking.simple;

import net.minecraft.class_2596;
import net.minecraft.class_2818;
import net.minecraft.class_3215;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.server.MinecraftServer;

public abstract class BaseS2CMessage extends Message {
   private void sendTo(class_3222 player, class_2596<?> packet) {
      if (player == null) {
         throw new NullPointerException("Unable to send packet '" + this.getType().getId() + "' to a 'null' player!");
      } else {
         player.field_13987.method_14364(packet);
      }
   }

   public final void sendTo(class_3222 player) {
      this.sendTo(player, this.toPacket(player.method_56673()));
   }

   public final void sendTo(Iterable<class_3222> players) {
      if (players.iterator().hasNext()) {
         class_2596<?> packet = this.toPacket(players.iterator().next().method_56673());

         for (class_3222 player : players) {
            this.sendTo(player, packet);
         }
      }
   }

   public final void sendToAll(MinecraftServer server) {
      this.sendTo(server.method_3760().method_14571());
   }

   public final void sendToLevel(class_3218 level) {
      this.sendTo(level.method_18456());
   }

   public final void sendToChunkListeners(class_2818 chunk) {
      class_2596<?> packet = this.toPacket(chunk.method_12200().method_30349());
      ((class_3215)chunk.method_12200().method_8398()).field_17254.method_17210(chunk.method_12004(), false).forEach(e -> this.sendTo(e, packet));
   }
}
