package com.magistuarmory.network;

import com.magistuarmory.item.LanceItem;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.NetworkManager.PacketContext;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.NotNull;

public class PacketLanceCollision implements class_8710 {
   public static final class_9154<PacketLanceCollision> TYPE = new class_9154(class_2960.method_60655("magistuarmory", "packet_lance_collision"));
   public static final class_9139<class_9129, PacketLanceCollision> STREAM_CODEC = class_9139.method_56435(
      class_9135.field_49675, p -> p.attackerid, class_9135.field_49675, p -> p.victimid, PacketLanceCollision::new
   );
   public int attackerid;
   public int victimid;

   public PacketLanceCollision(class_1297 attacker, class_1297 victim) {
      this(attacker.method_5628(), victim.method_5628());
   }

   public PacketLanceCollision(int attackerid, int victimid) {
      this.attackerid = attackerid;
      this.victimid = victimid;
   }

   public static void sendToServer(class_1297 attacker, class_1297 victim) {
      NetworkManager.sendToServer(new PacketLanceCollision(attacker, victim));
   }

   public static void apply(PacketLanceCollision packet, PacketContext context) {
      if (context.getPlayer() instanceof class_3222 player) {
         class_1297 var5 = player.method_37908().method_8469(packet.attackerid);
         class_1297 victim = player.method_37908().method_8469(packet.victimid);
         if (var5 != null && victim != null) {
            context.queue(() -> execute(var5, victim, player));
         }
      }
   }

   static void execute(class_1297 attacker, class_1297 victim, class_3222 player) {
      class_1799 stack = player.method_6047();
      if (attacker == player && stack.method_7909() instanceof LanceItem lance) {
         float speed = lance.getVelocityProjection(player);
         boolean dismount = victim.method_37908().method_8409().method_43058() > 1.0 - lance.getClickedScale();
         lance.setRideSpeed(stack, speed);
         lance.setDismount(stack, dismount);
         player.method_7324(victim);
         player.method_7350();
      }
   }

   @NotNull
   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
