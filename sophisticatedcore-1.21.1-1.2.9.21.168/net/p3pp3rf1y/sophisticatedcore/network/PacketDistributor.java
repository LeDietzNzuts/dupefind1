package net.p3pp3rf1y.sophisticatedcore.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;

public class PacketDistributor {
   private PacketDistributor() {
   }

   public static <T extends class_8710> void sendToServer(T packet) {
      ClientPlayNetworking.send(packet);
   }

   public static <T extends class_8710> void sendToAllNear(T packet, class_1297 entity, double range) {
      for (class_3222 player : PlayerLookup.around((class_3218)entity.method_37908(), entity.method_19538(), range)) {
         ServerPlayNetworking.send(player, packet);
      }
   }

   public static <T extends class_8710> void sendToAllNear(T message, class_1937 level, class_243 pos, int range) {
      if (level instanceof class_3218 serverLevel) {
         sendToAllNear(message, serverLevel, pos, range);
      }
   }

   public static <T extends class_8710> void sendToAllNear(T message, class_3218 level, class_243 pos, int range) {
      for (class_3222 player : PlayerLookup.around(level, pos, range)) {
         ServerPlayNetworking.send(player, message);
      }
   }

   public static <T extends class_8710> void sendToPlayer(class_1657 player, T packet) {
      if (player instanceof class_3222 serverPlayer) {
         ServerPlayNetworking.send(serverPlayer, packet);
      }
   }

   public static <T extends class_8710> void sendToPlayer(class_3222 player, T packet) {
      ServerPlayNetworking.send(player, packet);
   }
}
