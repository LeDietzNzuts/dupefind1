package com.magistuarmory.network;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.NetworkManager.Side;

public class ModPackets {
   public static void init() {
      NetworkManager.registerReceiver(Side.C2S, PacketLanceCollision.TYPE, PacketLanceCollision.STREAM_CODEC, PacketLanceCollision::apply);
   }
}
