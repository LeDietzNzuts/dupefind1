package com.talhanation.smallships.network.packet;

import com.talhanation.smallships.network.ModPacket;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.world.entity.cannon.GroundCannonEntity;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public record ServerboundEnterCannonBarrelPacket(int cannonID, int entityID) implements ModPacket {
   public static final class_9154<ServerboundEnterCannonBarrelPacket> TYPE = new class_9154(ModPackets.id("server_enter_cannon_barrel"));
   public static final class_9139<class_9129, ServerboundEnterCannonBarrelPacket> CODEC = class_9139.method_56435(
      class_9135.field_49675,
      ServerboundEnterCannonBarrelPacket::cannonID,
      class_9135.field_49675,
      ServerboundEnterCannonBarrelPacket::entityID,
      ServerboundEnterCannonBarrelPacket::new
   );

   @Override
   public void handler(class_1657 player) {
      class_1297 entity = player.method_37908().method_8469(this.entityID);
      class_1297 cannon = player.method_37908().method_8469(this.cannonID);
      if (entity != null && cannon != null && cannon instanceof GroundCannonEntity cannonEntity) {
         cannonEntity.putEntityIntoBarrel(entity);
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
