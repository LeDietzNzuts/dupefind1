package com.talhanation.smallships.network.packet;

import com.talhanation.smallships.network.ModPacket;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.cannon.GroundCannonEntity;
import net.minecraft.class_1657;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public record ServerboundShootGroundCannonPacket(boolean placeholder) implements ModPacket {
   public static final class_9154<ServerboundShootGroundCannonPacket> TYPE = new class_9154(ModPackets.id("server_shoot_ground_cannon"));
   public static final class_9139<class_9129, ServerboundShootGroundCannonPacket> CODEC = class_9139.method_56434(
      class_9135.field_48547, ServerboundShootGroundCannonPacket::placeholder, ServerboundShootGroundCannonPacket::new
   );

   @Override
   public void handler(class_1657 player) {
      if (player.method_5854() != null && player.method_5854() instanceof GroundCannonEntity cannon) {
         cannon.trigger(player);
      }
   }

   @Override
   public ModPacket.Side side() {
      return ModPacket.Side.SERVERBOUND;
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
