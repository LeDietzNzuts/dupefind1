package com.talhanation.smallships.network.packet;

import com.talhanation.smallships.network.ModPacket;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.ship.ContainerShip;
import com.talhanation.smallships.world.inventory.ContainerUtility;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_3532;
import net.minecraft.class_4844;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.NotNull;

public record ServerboundOpenShipScreenPacket(UUID ship, int pageIndex) implements ModPacket {
   public static final class_9154<ServerboundOpenShipScreenPacket> TYPE = new class_9154(ModPackets.id("server_open_ship_screen"));
   public static final class_9139<class_9129, ServerboundOpenShipScreenPacket> CODEC = class_9139.method_56435(
      class_4844.field_48453,
      ServerboundOpenShipScreenPacket::ship,
      class_9135.field_49675,
      ServerboundOpenShipScreenPacket::pageIndex,
      ServerboundOpenShipScreenPacket::new
   );

   @NotNull
   public class_9154<ServerboundOpenShipScreenPacket> method_56479() {
      return TYPE;
   }

   @Override
   public void handler(class_1657 player) {
      player.method_37908()
         .method_8390(ContainerShip.class, player.method_5830().method_1014(16.0), containerShip -> containerShip.method_5667().equals(this.ship))
         .stream()
         .filter(class_1297::method_5805)
         .findAny()
         .ifPresent(containerShip -> {
            int pageIndex = class_3532.method_15340(this.pageIndex, 0, containerShip.containerData.method_17390(1) - 1);
            containerShip.containerData.method_17391(2, pageIndex);
            ContainerUtility.openShipMenu(player, containerShip);
         });
   }

   @Override
   public ModPacket.Side side() {
      return ModPacket.Side.SERVERBOUND;
   }
}
