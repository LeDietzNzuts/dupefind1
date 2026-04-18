package net.p3pp3rf1y.sophisticatedcore.network;

import io.netty.buffer.ByteBuf;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_2487;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ISyncedContainer;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record SyncContainerClientDataPayload(@Nullable class_2487 data) implements class_8710 {
   public static final class_9154<SyncContainerClientDataPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_container_client_data"));
   public static final class_9139<ByteBuf, SyncContainerClientDataPayload> STREAM_CODEC = class_9139.method_56434(
      StreamCodecHelper.ofNullable(class_9135.field_48556), SyncContainerClientDataPayload::data, SyncContainerClientDataPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(SyncContainerClientDataPayload payload, Context context) {
      if (payload.data != null) {
         if (context.player().field_7512 instanceof ISyncedContainer container) {
            container.handlePacket(payload.data);
         }
      }
   }
}
