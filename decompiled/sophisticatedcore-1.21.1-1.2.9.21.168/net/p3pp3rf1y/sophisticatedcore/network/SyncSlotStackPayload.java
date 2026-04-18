package net.p3pp3rf1y.sophisticatedcore.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public record SyncSlotStackPayload(int windowId, int stateId, int slotNumber, class_1799 stack) implements class_8710 {
   public static final class_9154<SyncSlotStackPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_slot_stack"));
   public static final class_9139<class_9129, SyncSlotStackPayload> STREAM_CODEC = class_9139.method_56905(
      class_9135.field_49675,
      SyncSlotStackPayload::windowId,
      class_9135.field_49675,
      SyncSlotStackPayload::stateId,
      class_9135.field_49675,
      SyncSlotStackPayload::slotNumber,
      class_1799.field_49268,
      SyncSlotStackPayload::stack,
      SyncSlotStackPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncSlotStackPayload payload, Context context) {
      class_1657 player = context.player();
      if ((player.field_7512 instanceof StorageContainerMenuBase || player.field_7512 instanceof SettingsContainerMenu)
         && player.field_7512.field_7763 == payload.windowId) {
         player.field_7512.method_7619(payload.slotNumber, payload.stateId, payload.stack);
      }
   }
}
