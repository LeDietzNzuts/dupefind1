package com.talhanation.smallships.network.fabric;

import com.talhanation.smallships.network.ModPacket;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1657;
import net.minecraft.class_3222;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public class ModPacketsImpl {
   private static final List<class_9154<ModPacket>> clientReceivers = new ArrayList<>();
   private static final List<class_9154<ModPacket>> serverReceivers = new ArrayList<>();

   public static void registerPacket(class_9154<ModPacket> type, class_9139<class_9129, ModPacket> codec, ModPacket.Side side) {
      switch (side) {
         case CLIENTBOUND:
            PayloadTypeRegistry.playS2C().register(type, codec);
            clientReceivers.add(type);
            break;
         case SERVERBOUND:
            PayloadTypeRegistry.playC2S().register(type, codec);
            serverReceivers.add(type);
      }
   }

   @Environment(EnvType.CLIENT)
   public static void registerClientReceivers() {
      ModPacketsClientHelper.registerClientReceivers(clientReceivers);
   }

   public static void registerServerReceivers() {
      for (class_9154<ModPacket> type : serverReceivers) {
         ServerPlayNetworking.registerGlobalReceiver(type, (packet, context) -> {
            class_1657 player = context.player();
            packet.handler(player);
         });
      }
   }

   public static void serverSendPacket(class_3222 player, ModPacket packet) {
      ServerPlayNetworking.send(player, packet);
   }

   @Environment(EnvType.CLIENT)
   public static void clientSendPacket(ModPacket packet) {
      ModPacketsClientHelper.clientSendPacket(packet);
   }
}
