package com.talhanation.smallships.network;

import com.talhanation.smallships.network.fabric.ModPacketsImpl;
import com.talhanation.smallships.network.packet.ServerboundEnterCannonBarrelPacket;
import com.talhanation.smallships.network.packet.ServerboundOpenShipScreenPacket;
import com.talhanation.smallships.network.packet.ServerboundSetSailStatePacket;
import com.talhanation.smallships.network.packet.ServerboundShootGroundCannonPacket;
import com.talhanation.smallships.network.packet.ServerboundShootShipCannonPacket;
import com.talhanation.smallships.network.packet.ServerboundToggleShipSailPacket;
import com.talhanation.smallships.network.packet.ServerboundUpdateShipControlPacket;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public class ModPackets {
   public static void registerPackets() {
      registerNonPacket(ServerboundOpenShipScreenPacket.TYPE, ServerboundOpenShipScreenPacket.CODEC, ModPacket.Side.SERVERBOUND);
      registerNonPacket(ServerboundToggleShipSailPacket.TYPE, ServerboundToggleShipSailPacket.CODEC, ModPacket.Side.SERVERBOUND);
      registerNonPacket(ServerboundShootShipCannonPacket.TYPE, ServerboundShootShipCannonPacket.CODEC, ModPacket.Side.SERVERBOUND);
      registerNonPacket(ServerboundShootGroundCannonPacket.TYPE, ServerboundShootGroundCannonPacket.CODEC, ModPacket.Side.SERVERBOUND);
      registerNonPacket(ServerboundEnterCannonBarrelPacket.TYPE, ServerboundEnterCannonBarrelPacket.CODEC, ModPacket.Side.SERVERBOUND);
      registerNonPacket(ServerboundSetSailStatePacket.TYPE, ServerboundSetSailStatePacket.CODEC, ModPacket.Side.SERVERBOUND);
      registerNonPacket(ServerboundUpdateShipControlPacket.TYPE, ServerboundUpdateShipControlPacket.CODEC, ModPacket.Side.SERVERBOUND);
   }

   private static <T extends ModPacket> void registerNonPacket(class_9154<T> type, class_9139<class_9129, T> codec, ModPacket.Side side) {
      registerPacket(type, codec, side);
   }

   @ExpectPlatform
   @Transformed
   public static void registerPacket(class_9154<ModPacket> type, class_9139<class_9129, ModPacket> codec, ModPacket.Side side) {
      ModPacketsImpl.registerPacket(type, codec, side);
   }

   @ExpectPlatform
   @Transformed
   public static void serverSendPacket(class_3222 player, ModPacket packet) {
      ModPacketsImpl.serverSendPacket(player, packet);
   }

   @ExpectPlatform
   @Transformed
   public static void clientSendPacket(ModPacket packet) {
      ModPacketsImpl.clientSendPacket(packet);
   }

   public static class_2960 id(String id) {
      return class_2960.method_60655("smallships", id);
   }
}
