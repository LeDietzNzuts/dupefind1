package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_4844;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;

public record SoundFinishedNotificationPayload(UUID storageUuid) implements class_8710 {
   public static final class_9154<SoundFinishedNotificationPayload> TYPE = new class_9154(SophisticatedCore.getRL("sound_finished_notification"));
   public static final class_9139<ByteBuf, SoundFinishedNotificationPayload> STREAM_CODEC = class_9139.method_56434(
      class_4844.field_48453, SoundFinishedNotificationPayload::storageUuid, SoundFinishedNotificationPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(SoundFinishedNotificationPayload payload, Context context) {
      ServerStorageSoundHandler.onSoundFinished(context.player().method_37908(), payload.storageUuid);
   }
}
