package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_3414;
import net.minecraft.class_4844;
import net.minecraft.class_6880;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_9793;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;

public record PlayDiscPayload(boolean blockStorage, UUID storageUuid, class_1799 discItemStack, class_6880<class_9793> song, int entityId, class_2338 pos)
   implements class_8710 {
   public static final class_9154<PlayDiscPayload> TYPE = new class_9154(SophisticatedCore.getRL("play_disc"));
   public static final class_9139<class_9129, PlayDiscPayload> STREAM_CODEC = class_9139.method_58025(
      class_9135.field_48547,
      PlayDiscPayload::blockStorage,
      class_4844.field_48453,
      PlayDiscPayload::storageUuid,
      class_1799.field_48349,
      PlayDiscPayload::discItemStack,
      class_9793.field_52030,
      PlayDiscPayload::song,
      class_9135.field_49675,
      PlayDiscPayload::entityId,
      class_2338.field_48404,
      PlayDiscPayload::pos,
      PlayDiscPayload::new
   );

   public PlayDiscPayload(UUID storageUuid, class_1799 discItemStack, class_6880<class_9793> song, class_2338 pos) {
      this(true, storageUuid, discItemStack, song, 0, pos);
   }

   public PlayDiscPayload(UUID storageUuid, class_1799 discItemStack, class_6880<class_9793> song, int entityId) {
      this(false, storageUuid, discItemStack, song, entityId, class_2338.field_10980);
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(PlayDiscPayload payload, Context context) {
      class_3414 soundEvent = (class_3414)((class_9793)payload.song().comp_349()).comp_2835().comp_349();
      if (payload.blockStorage) {
         StorageSoundHandler.playStorageSound(soundEvent, payload.storageUuid, payload.pos);
      } else {
         StorageSoundHandler.playStorageSound(soundEvent, payload.storageUuid, payload.entityId);
      }
   }
}
