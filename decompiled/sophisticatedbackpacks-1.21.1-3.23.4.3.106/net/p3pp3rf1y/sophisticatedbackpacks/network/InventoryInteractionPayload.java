package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.util.InventoryInteractionHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;

public record InventoryInteractionPayload(class_2338 pos, class_2350 face) implements class_8710 {
   public static final class_9154<InventoryInteractionPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("inventory_interaction"));
   public static final class_9139<ByteBuf, InventoryInteractionPayload> STREAM_CODEC = class_9139.method_56435(
      class_2338.field_48404, InventoryInteractionPayload::pos, class_2350.field_48450, InventoryInteractionPayload::face, InventoryInteractionPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(InventoryInteractionPayload payload, Context context) {
      class_1657 player = context.player();
      PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, inventoryName, identifier, slot) -> {
         InventoryInteractionHelper.tryInventoryInteraction(payload.pos, player.method_37908(), backpack, payload.face, player);
         player.method_23667(class_1268.field_5808, true);
         return true;
      });
   }
}
