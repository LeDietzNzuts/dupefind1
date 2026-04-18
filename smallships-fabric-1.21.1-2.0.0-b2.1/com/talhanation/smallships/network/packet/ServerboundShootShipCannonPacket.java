package com.talhanation.smallships.network.packet;

import com.talhanation.smallships.network.ModPacket;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import net.minecraft.class_1657;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.NotNull;

public record ServerboundShootShipCannonPacket(boolean trigger) implements ModPacket {
   public static final class_9154<ServerboundShootShipCannonPacket> TYPE = new class_9154(ModPackets.id("server_shoot_ship_cannon"));
   public static final class_9139<class_9129, ServerboundShootShipCannonPacket> CODEC = class_9139.method_56434(
      class_9135.field_48547, ServerboundShootShipCannonPacket::trigger, ServerboundShootShipCannonPacket::new
   );

   @NotNull
   public class_9154<ServerboundShootShipCannonPacket> method_56479() {
      return TYPE;
   }

   @Override
   public void handler(class_1657 player) {
      if (player.method_5854() != null && player.method_5854() instanceof Cannonable cannonShip) {
         cannonShip.self().setCannonKeyPressed(this.trigger);
      }
   }

   @Override
   public ModPacket.Side side() {
      return ModPacket.Side.SERVERBOUND;
   }
}
