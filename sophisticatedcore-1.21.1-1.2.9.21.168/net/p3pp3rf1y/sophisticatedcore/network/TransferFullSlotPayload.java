package net.p3pp3rf1y.sophisticatedcore.network;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public record TransferFullSlotPayload(int slotId) implements class_8710 {
   public static final class_9154<TransferFullSlotPayload> TYPE = new class_9154(SophisticatedCore.getRL("transfer_full_slot"));
   public static final class_9139<ByteBuf, TransferFullSlotPayload> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_49675, TransferFullSlotPayload::slotId, TransferFullSlotPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(TransferFullSlotPayload payload, Context context) {
      class_1657 player = context.player();
      if (player.field_7512 instanceof StorageContainerMenuBase<?> storageContainer) {
         class_1735 var6 = storageContainer.method_7611(payload.slotId);

         class_1799 transferResult;
         do {
            transferResult = storageContainer.method_7601(player, payload.slotId);
         } while (!transferResult.method_7960() && class_1799.method_7984(var6.method_7677(), transferResult));
      }
   }
}
