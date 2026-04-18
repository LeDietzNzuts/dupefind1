package net.p3pp3rf1y.sophisticatedcore.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeSlotChangeResult;

public record SyncSlotChangeErrorPayload(UpgradeSlotChangeResult slotChangeError) implements class_8710 {
   public static final class_9154<SyncSlotChangeErrorPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_slot_change_error"));
   public static final class_9139<class_9129, SyncSlotChangeErrorPayload> STREAM_CODEC = class_9139.method_56434(
      UpgradeSlotChangeResult.STREAM_CODEC, SyncSlotChangeErrorPayload::slotChangeError, SyncSlotChangeErrorPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncSlotChangeErrorPayload payload, Context context) {
      if (context.player().field_7512 instanceof StorageContainerMenuBase<?> menu) {
         menu.updateSlotChangeError(payload.slotChangeError);
      }
   }
}
