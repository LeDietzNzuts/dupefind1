package net.p3pp3rf1y.sophisticatedcore.compat.audioplayer;

import de.maxhenkel.audioplayer.AudioPlayer;
import de.maxhenkel.audioplayer.CustomSound;
import de.maxhenkel.audioplayer.PlayerType;
import de.maxhenkel.audioplayer.Plugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.audiochannel.LocationalAudioChannel;
import de.maxhenkel.voicechat.plugins.impl.PositionImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_6880;
import net.minecraft.class_9793;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.ServerStorageSoundHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.SoundHandler;

public class AudioPlayerSoundHandler implements SoundHandler {
   private static final int SOUND_STOP_CHECK_INTERVAL = 10;
   private static long lastPlaybackChecked = 0L;
   private static final Map<UUID, UUID> storageUUIDToChannelUUID = new HashMap<>();

   @Nullable
   public static UUID play(class_1937 level, class_243 pos, PlayerType type, CustomSound sound) {
      if (level instanceof class_3218 serverLevel) {
         float range = sound.getRange(type);
         VoicechatServerApi api = Plugin.voicechatServerApi;
         if (api == null) {
            return null;
         } else {
            UUID channelID;
            if (sound.isStaticSound() && (Boolean)AudioPlayer.SERVER_CONFIG.allowStaticAudio.get()) {
               channelID = SCPlayerManager.instance()
                  .playStatic(api, serverLevel, pos, sound.getSoundId(), null, range, type.getCategory(), (Integer)type.getMaxDuration().get());
            } else {
               channelID = SCPlayerManager.instance()
                  .playLocational(api, serverLevel, pos, sound.getSoundId(), null, range, type.getCategory(), (Integer)type.getMaxDuration().get());
            }

            return channelID;
         }
      } else {
         return null;
      }
   }

   @Override
   public boolean play(class_1937 level, class_2338 position, UUID storageUuid, class_1799 discItemStack, class_6880<class_9793> song) {
      CustomSound customSound = CustomSound.of(discItemStack);
      if (customSound != null) {
         class_243 pos = class_243.method_24953(position);
         UUID channel = play(level, pos, PlayerType.MUSIC_DISC, customSound);
         if (channel != null) {
            storageUUIDToChannelUUID.put(storageUuid, channel);
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean play(class_1937 level, class_243 position, UUID storageUuid, int entityId, class_1799 discItemStack, class_6880<class_9793> song) {
      CustomSound customSound = CustomSound.of(discItemStack);
      if (customSound != null) {
         UUID channel = play(level, position, PlayerType.MUSIC_DISC, customSound);
         if (channel != null) {
            storageUUIDToChannelUUID.put(storageUuid, channel);
            return true;
         }
      }

      return false;
   }

   @Override
   public void stop(class_1937 level, class_243 position, UUID storageUuid) {
      if (storageUUIDToChannelUUID.containsKey(storageUuid)) {
         UUID channelID = storageUUIDToChannelUUID.remove(storageUuid);
         SCPlayerManager.instance().stop(channelID);
      }
   }

   @Override
   public void update(UUID storageUuid, class_243 position) {
      if (storageUUIDToChannelUUID.containsKey(storageUuid)) {
         UUID channelID = storageUUIDToChannelUUID.get(storageUuid);
         LocationalAudioChannel channel = SCPlayerManager.instance().getAudioChannel(channelID);
         if (channel != null) {
            channel.updateLocation(new PositionImpl(position.field_1352, position.field_1351, position.field_1350));
         }
      }
   }

   public static void tick(class_3218 level) {
      if (!storageUUIDToChannelUUID.isEmpty() && lastPlaybackChecked < level.method_8510() - 10L) {
         lastPlaybackChecked = level.method_8510();
         storageUUIDToChannelUUID.entrySet().removeIf(entry -> {
            if (!SCPlayerManager.instance().isPlaying(entry.getValue())) {
               ServerStorageSoundHandler.onSoundFinished(level, entry.getKey());
               return true;
            } else {
               return false;
            }
         });
      }
   }
}
