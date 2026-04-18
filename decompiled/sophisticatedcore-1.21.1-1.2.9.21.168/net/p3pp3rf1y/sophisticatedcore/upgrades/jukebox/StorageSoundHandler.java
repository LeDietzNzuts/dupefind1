package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1106;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3218;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_638;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class StorageSoundHandler {
   private static final int SOUND_STOP_CHECK_INTERVAL = 10;
   private static final Map<UUID, class_1113> storageSounds = new ConcurrentHashMap<>();
   private static long lastPlaybackChecked = 0L;

   private StorageSoundHandler() {
   }

   public static void playStorageSound(UUID storageUuid, class_1113 sound) {
      stopStorageSound(storageUuid);
      storageSounds.put(storageUuid, sound);
      class_310.method_1551().method_1483().method_4873(sound);
   }

   public static void stopStorageSound(UUID storageUuid) {
      if (storageSounds.containsKey(storageUuid)) {
         class_310.method_1551().method_1483().method_4870(storageSounds.remove(storageUuid));
      }
   }

   public static void tick(class_638 level) {
      if (!storageSounds.isEmpty() && lastPlaybackChecked < level.method_8510() - 10L) {
         lastPlaybackChecked = level.method_8510();
         storageSounds.entrySet().removeIf(entry -> {
            if (!class_310.method_1551().method_1483().method_4877(entry.getValue())) {
               PacketDistributor.sendToServer(new SoundFinishedNotificationPayload(entry.getKey()));
               return true;
            } else {
               return false;
            }
         });
      }
   }

   public static void playStorageSound(class_3414 soundEvent, UUID storageUuid, class_2338 pos) {
      playStorageSound(storageUuid, class_1109.method_4760(soundEvent, class_243.method_24953(pos)));
   }

   public static void playStorageSound(class_3414 soundEvent, UUID storageUuid, int entityId) {
      class_638 level = class_310.method_1551().field_1687;
      if (level != null) {
         final class_1297 entity = level.method_8469(entityId);
         if (!(entity instanceof class_1297)) {
            stopStorageSound(storageUuid);
         } else {
            playStorageSound(storageUuid, new class_1106(soundEvent, class_3419.field_15247, 2.0F, 1.0F, entity, level.field_9229.method_43055()) {
               public void method_16896() {
                  super.method_16896();
                  if (entity instanceof class_1657 player) {
                     class_243 lookAngle = player.method_5720();
                     this.field_5439 = player.method_23317() + lookAngle.field_1352;
                     this.field_5450 = player.method_23320() + lookAngle.field_1351;
                     this.field_5449 = player.method_23321() + lookAngle.field_1350;
                  }
               }
            });
         }
      }
   }

   public static void onWorldUnload(MinecraftServer minecraftServer, class_3218 serverLevel) {
      storageSounds.clear();
      lastPlaybackChecked = 0L;
   }
}
