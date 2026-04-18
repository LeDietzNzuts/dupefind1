package com.talhanation.smallships.network.fabric;

import com.talhanation.smallships.network.ModPacket;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1657;
import net.minecraft.class_8710.class_9154;

@Environment(EnvType.CLIENT)
public class ModPacketsClientHelper {
   public static void registerClientReceivers(List<class_9154<ModPacket>> clientReceivers) {
      for (class_9154<ModPacket> type : clientReceivers) {
         ClientPlayNetworking.registerGlobalReceiver(type, (packet, context) -> context.client().execute(() -> {
            class_1657 player = context.client().field_1724;
            packet.handler(player);
         }));
      }
   }

   public static void clientSendPacket(ModPacket packet) {
      ClientPlayNetworking.send(packet);
   }
}
