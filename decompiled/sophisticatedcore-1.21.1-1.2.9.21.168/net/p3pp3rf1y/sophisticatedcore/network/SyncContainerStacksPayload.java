package net.p3pp3rf1y.sophisticatedcore.network;

import java.util.List;
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
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public record SyncContainerStacksPayload(int windowId, int stateId, List<class_1799> itemStacks, class_1799 carriedStack) implements class_8710 {
   public static final class_9154<SyncContainerStacksPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_container_stacks"));
   public static final class_9139<class_9129, SyncContainerStacksPayload> STREAM_CODEC = class_9139.method_56905(
      class_9135.field_49675,
      SyncContainerStacksPayload::windowId,
      class_9135.field_49675,
      SyncContainerStacksPayload::stateId,
      class_1799.field_49269,
      SyncContainerStacksPayload::itemStacks,
      class_1799.field_49268,
      SyncContainerStacksPayload::carriedStack,
      SyncContainerStacksPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncContainerStacksPayload payload, Context context) {
      class_1657 player = context.player();
      if (player.field_7512 instanceof StorageContainerMenuBase && player.field_7512.field_7763 == payload.windowId) {
         player.field_7512.method_7610(payload.stateId, payload.itemStacks, payload.carriedStack);
      }
   }
}
