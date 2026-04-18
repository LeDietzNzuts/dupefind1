package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_3222;
import net.minecraft.class_4844;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public record RequestBackpackInventoryContentsPayload(UUID backpackUuid) implements class_8710 {
   public static final class_9154<RequestBackpackInventoryContentsPayload> TYPE = new class_9154(
      SophisticatedBackpacks.getRL("request_backpack_inventory_contents")
   );
   public static final class_9139<ByteBuf, RequestBackpackInventoryContentsPayload> STREAM_CODEC = class_9139.method_56434(
      class_4844.field_48453, RequestBackpackInventoryContentsPayload::backpackUuid, RequestBackpackInventoryContentsPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(RequestBackpackInventoryContentsPayload payload, Context context) {
      class_2487 backpackContents = BackpackStorage.get().getOrCreateBackpackContents(payload.backpackUuid);
      class_2487 inventoryContents = new class_2487();
      class_2520 inventoryNbt = backpackContents.method_10580("inventory");
      if (inventoryNbt != null) {
         inventoryContents.method_10566("inventory", inventoryNbt);
      }

      class_2520 upgradeNbt = backpackContents.method_10580("upgradeInventory");
      if (upgradeNbt != null) {
         inventoryContents.method_10566("upgradeInventory", upgradeNbt);
      }

      class_3222 var7 = context.player();
      if (var7 instanceof class_3222) {
         PacketDistributor.sendToPlayer(var7, new BackpackContentsPayload(payload.backpackUuid, inventoryContents));
      }
   }
}
