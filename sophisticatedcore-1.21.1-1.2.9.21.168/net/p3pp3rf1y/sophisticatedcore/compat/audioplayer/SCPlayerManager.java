package net.p3pp3rf1y.sophisticatedcore.compat.audioplayer;

import de.maxhenkel.audioplayer.AudioManager;
import de.maxhenkel.audioplayer.StaticAudioPlayer;
import de.maxhenkel.voicechat.api.Player;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.audiochannel.AudioChannel;
import de.maxhenkel.voicechat.api.audiochannel.AudioPlayer;
import de.maxhenkel.voicechat.api.audiochannel.LocationalAudioChannel;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import net.minecraft.class_124;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;

public class SCPlayerManager {
   private final Map<UUID, SCPlayerManager.PlayerReference> players = new ConcurrentHashMap<>();
   private final ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
      Thread thread = new Thread(r, "SC-AudioPlayerThread");
      thread.setDaemon(true);
      return thread;
   });
   private static SCPlayerManager instance;

   @Nullable
   public UUID playLocational(
      VoicechatServerApi api,
      class_3218 level,
      class_243 pos,
      UUID sound,
      @Nullable class_3222 p,
      float distance,
      @Nullable String category,
      int maxLengthSeconds
   ) {
      return this.playLocational(api, level, pos, sound, p, distance, category, maxLengthSeconds, false);
   }

   @Nullable
   public UUID playLocational(
      VoicechatServerApi api,
      class_3218 level,
      class_243 pos,
      UUID sound,
      @Nullable class_3222 p,
      float distance,
      @Nullable String category,
      int maxLengthSeconds,
      boolean byCommand
   ) {
      UUID channelID = UUID.randomUUID();
      LocationalAudioChannel channel = api.createLocationalAudioChannel(
         channelID, api.fromServerLevel(level), api.createPosition(pos.field_1352, pos.field_1351, pos.field_1350)
      );
      if (channel == null) {
         return null;
      } else {
         if (category != null) {
            channel.setCategory(category);
         }

         channel.setDistance(distance);
         api.getPlayersInRange(api.fromServerLevel(level), channel.getLocation(), distance + 1.0F, serverPlayer -> {
               VoicechatConnection connection = api.getConnectionOf(serverPlayer);
               return connection != null ? connection.isDisabled() : true;
            })
            .stream()
            .<Object>map(Player::getPlayer)
            .map(class_3222.class::cast)
            .forEach(playerx -> playerx.method_7353(class_2561.method_43470("You need to enable voice chat to hear custom audio"), true));
         AtomicBoolean stopped = new AtomicBoolean();
         AtomicReference<AudioPlayer> player = new AtomicReference<>();
         this.players.put(channelID, new SCPlayerManager.PlayerReference(() -> {
            synchronized (stopped) {
               stopped.set(true);
               AudioPlayer audioPlayer = player.get();
               if (audioPlayer != null) {
                  audioPlayer.stopPlaying();
               }
            }
         }, player, sound, channel));
         this.executor.execute(() -> {
            AudioPlayer audioPlayer = this.playChannel(api, channel, level, sound, p, maxLengthSeconds);
            if (audioPlayer == null) {
               this.players.remove(channelID);
            } else {
               audioPlayer.setOnStopped(() -> this.players.remove(channelID));
               synchronized (stopped) {
                  if (!stopped.get()) {
                     player.set(audioPlayer);
                  } else {
                     audioPlayer.stopPlaying();
                  }
               }
            }
         });
         return channelID;
      }
   }

   @Nullable
   public UUID playStatic(
      VoicechatServerApi api,
      class_3218 level,
      class_243 pos,
      UUID sound,
      @Nullable class_3222 p,
      float distance,
      @Nullable String category,
      int maxLengthSeconds
   ) {
      return this.playStatic(api, level, pos, sound, p, distance, category, maxLengthSeconds, false);
   }

   @Nullable
   public UUID playStatic(
      VoicechatServerApi api,
      class_3218 level,
      class_243 pos,
      UUID sound,
      @Nullable class_3222 p,
      float distance,
      @Nullable String category,
      int maxLengthSeconds,
      boolean byCommand
   ) {
      UUID channelID = UUID.randomUUID();
      api.getPlayersInRange(api.fromServerLevel(level), api.createPosition(pos.field_1352, pos.field_1351, pos.field_1350), distance + 1.0F, serverPlayer -> {
            VoicechatConnection connection = api.getConnectionOf(serverPlayer);
            return connection != null ? connection.isDisabled() : true;
         })
         .stream()
         .<Object>map(Player::getPlayer)
         .map(class_3222.class::cast)
         .forEach(playerx -> playerx.method_7353(class_2561.method_43470("You need to enable voice chat to hear custom audio"), true));
      StaticAudioPlayer staticAudioPlayer = StaticAudioPlayer.create(api, level, sound, p, maxLengthSeconds, category, pos, channelID, distance);
      AtomicBoolean stopped = new AtomicBoolean();
      AtomicReference<AudioPlayer> player = new AtomicReference<>();
      this.players.put(channelID, new SCPlayerManager.PlayerReference(() -> {
         synchronized (stopped) {
            stopped.set(true);
            AudioPlayer audioPlayer = player.get();
            if (audioPlayer != null) {
               audioPlayer.stopPlaying();
            }
         }
      }, player, sound, null));
      this.executor.execute(() -> {
         if (staticAudioPlayer == null) {
            this.players.remove(channelID);
         } else {
            staticAudioPlayer.setOnStopped(() -> this.players.remove(channelID));
            synchronized (stopped) {
               if (!stopped.get()) {
                  player.set(staticAudioPlayer);
               } else {
                  staticAudioPlayer.stopPlaying();
               }
            }
         }
      });
      return channelID;
   }

   @Nullable
   private AudioPlayer playChannel(VoicechatServerApi api, AudioChannel channel, class_3218 level, UUID sound, class_3222 p, int maxLengthSeconds) {
      try {
         short[] audio = AudioManager.getSound(level.method_8503(), sound);
         if (AudioManager.getLengthSeconds(audio) > maxLengthSeconds) {
            if (p != null) {
               p.method_7353(class_2561.method_43470("Audio is too long to play").method_27692(class_124.field_1079), true);
            } else {
               SophisticatedCore.LOGGER.error("Audio {} was too long to play", sound);
            }

            return null;
         } else {
            AudioPlayer player = api.createAudioPlayer(channel, api.createEncoder(), audio);
            player.startPlaying();
            return player;
         }
      } catch (Exception var9) {
         SophisticatedCore.LOGGER.error("Failed to play audio", var9);
         if (p != null) {
            p.method_7353(class_2561.method_43470("Failed to play audio: %s".formatted(var9.getMessage())).method_27692(class_124.field_1079), true);
         }

         return null;
      }
   }

   public void stop(UUID channelID) {
      SCPlayerManager.PlayerReference player = this.players.get(channelID);
      if (player != null) {
         player.onStop.stop();
      }

      this.players.remove(channelID);
   }

   public boolean isPlaying(UUID channelID) {
      SCPlayerManager.PlayerReference player = this.players.get(channelID);
      if (player == null) {
         return false;
      } else {
         AudioPlayer p = player.player.get();
         return p == null ? true : p.isPlaying();
      }
   }

   @Nullable
   public LocationalAudioChannel getAudioChannel(UUID channelID) {
      SCPlayerManager.PlayerReference player = this.players.get(channelID);
      return player == null ? null : player.channel();
   }

   public static SCPlayerManager instance() {
      if (instance == null) {
         instance = new SCPlayerManager();
      }

      return instance;
   }

   private record PlayerReference(SCPlayerManager.Stoppable onStop, AtomicReference<AudioPlayer> player, UUID sound, LocationalAudioChannel channel) {
   }

   private interface Stoppable {
      void stop();
   }
}
