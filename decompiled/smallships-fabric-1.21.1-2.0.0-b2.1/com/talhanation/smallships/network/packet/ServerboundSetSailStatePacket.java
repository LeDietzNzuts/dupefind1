package com.talhanation.smallships.network.packet;

import com.talhanation.smallships.network.ModPacket;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.ship.abilities.Sailable;
import net.minecraft.class_1657;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.NotNull;

public record ServerboundSetSailStatePacket(byte state) implements ModPacket {
   public static final class_9154<ServerboundSetSailStatePacket> TYPE = new class_9154(ModPackets.id("server_set_sail_state"));
   public static final class_9139<class_9129, ServerboundSetSailStatePacket> CODEC = class_9139.method_56434(
      class_9135.field_48548, ServerboundSetSailStatePacket::state, ServerboundSetSailStatePacket::new
   );

   @NotNull
   public class_9154<ServerboundSetSailStatePacket> method_56479() {
      return TYPE;
   }

   @Override
   public void handler(class_1657 player) {
      if (player.method_5854() != null && player.method_5854() instanceof Sailable sailShip) {
         sailShip.setSailState(this.state);
      }
   }

   @Override
   public ModPacket.Side side() {
      return ModPacket.Side.SERVERBOUND;
   }
}
