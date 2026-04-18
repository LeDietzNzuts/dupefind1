package com.talhanation.smallships.network.packet;

import com.talhanation.smallships.network.ModPacket;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_1657;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.NotNull;

public record ServerboundUpdateShipControlPacket(boolean forward, boolean backward, boolean left, boolean right) implements ModPacket {
   public static final class_9154<ServerboundUpdateShipControlPacket> TYPE = new class_9154(ModPackets.id("server_update_ship_control"));
   public static final class_9139<class_9129, ServerboundUpdateShipControlPacket> CODEC = class_9139.method_56905(
      class_9135.field_48547,
      ServerboundUpdateShipControlPacket::forward,
      class_9135.field_48547,
      ServerboundUpdateShipControlPacket::backward,
      class_9135.field_48547,
      ServerboundUpdateShipControlPacket::left,
      class_9135.field_48547,
      ServerboundUpdateShipControlPacket::right,
      ServerboundUpdateShipControlPacket::new
   );

   @NotNull
   public class_9154<ServerboundUpdateShipControlPacket> method_56479() {
      return TYPE;
   }

   @Override
   public void handler(class_1657 player) {
      if (player.method_5854() != null && player.method_5854() instanceof Ship ship) {
         ship.updateControls(this.forward, this.backward, this.left, this.right, player);
      }
   }

   @Override
   public ModPacket.Side side() {
      return ModPacket.Side.SERVERBOUND;
   }
}
