package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_4844;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;

public record StopDiscPlaybackPayload(UUID storageUuid) implements class_8710 {
   public static final class_9154<StopDiscPlaybackPayload> TYPE = new class_9154(SophisticatedCore.getRL("stop_disc_playback"));
   public static final class_9139<ByteBuf, StopDiscPlaybackPayload> STREAM_CODEC = class_9139.method_56434(
      class_4844.field_48453, StopDiscPlaybackPayload::storageUuid, StopDiscPlaybackPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(StopDiscPlaybackPayload payload, Context context) {
      StorageSoundHandler.stopStorageSound(payload.storageUuid);
   }
}
