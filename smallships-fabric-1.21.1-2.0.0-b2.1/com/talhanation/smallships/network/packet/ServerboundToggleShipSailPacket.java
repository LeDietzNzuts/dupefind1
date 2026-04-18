package com.talhanation.smallships.network.packet;

import com.talhanation.smallships.network.ModPacket;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.entity.ship.abilities.Sailable;
import net.minecraft.class_1657;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.NotNull;

public record ServerboundToggleShipSailPacket() implements ModPacket {
   public static final class_9154<ServerboundToggleShipSailPacket> TYPE = new class_9154(ModPackets.id("server_toggle_ship_sail"));
   public static final class_9139<class_9129, ServerboundToggleShipSailPacket> CODEC = class_9139.method_56431(new ServerboundToggleShipSailPacket());

   @NotNull
   public class_9154<ServerboundToggleShipSailPacket> method_56479() {
      return TYPE;
   }

   @Override
   public void handler(class_1657 player) {
      if (player.method_5854() != null && player.method_5854() instanceof Ship ship && ship instanceof Sailable sailShip) {
         sailShip.toggleSail();
      }
   }

   @Override
   public ModPacket.Side side() {
      return ModPacket.Side.SERVERBOUND;
   }
}
