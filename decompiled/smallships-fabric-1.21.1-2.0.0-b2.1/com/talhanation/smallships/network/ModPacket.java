package com.talhanation.smallships.network;

import net.minecraft.class_1657;
import net.minecraft.class_8710;

public interface ModPacket extends class_8710 {
   void handler(class_1657 var1);

   ModPacket.Side side();

   public static enum Side {
      CLIENTBOUND,
      SERVERBOUND;
   }
}
