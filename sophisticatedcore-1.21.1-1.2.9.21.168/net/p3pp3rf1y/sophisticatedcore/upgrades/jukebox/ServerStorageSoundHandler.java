package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import com.google.common.collect.Lists;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_9793;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class ServerStorageSoundHandler {
   private static final int KEEP_ALIVE_CHECK_INTERVAL = 10;
   private static final Map<class_5321<class_1937>, Long> lastWorldCheck = new HashMap<>();
   private static final Map<class_5321<class_1937>, Map<UUID, ServerStorageSoundHandler.KeepAliveInfo>> worldStorageSoundKeepAlive = new HashMap<>();
   private static final List<SoundHandler> soundHandlers = Lists.newArrayList();

   private ServerStorageSoundHandler() {
   }

   public static void registerSoundHandler(SoundHandler handler) {
      soundHandlers.add(handler);
   }

   public static void tick(class_3218 serverLevel) {
      if (!serverLevel.method_8608()) {
         class_5321<class_1937> dim = serverLevel.method_27983();
         if (lastWorldCheck.computeIfAbsent(dim, key -> serverLevel.method_8510()) <= serverLevel.method_8510() - 10L
            && worldStorageSoundKeepAlive.containsKey(dim)) {
            lastWorldCheck.put(dim, serverLevel.method_8510());
            worldStorageSoundKeepAlive.get(dim).entrySet().removeIf(entry -> {
               if (entry.getValue().getLastKeepAliveTime() < serverLevel.method_8510() - 10L) {
                  entry.getValue().getSoundHandler().stop(serverLevel, entry.getValue().getLastPosition(), entry.getKey());
                  return true;
               } else {
                  return false;
               }
            });
         }
      }
   }

   public static void updateKeepAlive(UUID storageUuid, class_1937 level, class_243 position, Runnable onNoLongerRunning) {
      class_5321<class_1937> dim = level.method_27983();
      if (worldStorageSoundKeepAlive.containsKey(dim) && worldStorageSoundKeepAlive.get(dim).containsKey(storageUuid)) {
         if (worldStorageSoundKeepAlive.get(dim).containsKey(storageUuid)) {
            worldStorageSoundKeepAlive.get(dim).get(storageUuid).update(level.method_8510(), position, storageUuid);
         }
      } else {
         onNoLongerRunning.run();
      }
   }

   public static void onSoundFinished(class_1937 level, UUID storageUuid) {
      removeKeepAliveInfo(level, storageUuid, true);
   }

   private static void runSoundHandler(
      class_3218 serverWorld, class_243 position, UUID storageUuid, Runnable onStopHandler, Function<SoundHandler, Boolean> onHandler
   ) {
      for (SoundHandler handler : Lists.reverse(soundHandlers)) {
         if (onHandler.apply(handler)) {
            putKeepAliveInfo(serverWorld, storageUuid, onStopHandler, position, handler);
            return;
         }
      }
   }

   public static void startPlayingDisc(
      class_3218 serverLevel, class_2338 position, UUID storageUuid, class_1799 discItemStack, class_6880<class_9793> song, Runnable onFinishedHandler
   ) {
      class_243 pos = class_243.method_24953(position);
      runSoundHandler(serverLevel, pos, storageUuid, onFinishedHandler, handler -> handler.play(serverLevel, position, storageUuid, discItemStack, song));
   }

   public static void startPlayingDisc(
      class_3218 serverLevel, class_243 position, UUID storageUuid, int entityId, class_1799 discItemStack, class_6880<class_9793> song, Runnable onStopHandler
   ) {
      runSoundHandler(
         serverLevel, position, storageUuid, onStopHandler, handler -> handler.play(serverLevel, position, storageUuid, entityId, discItemStack, song)
      );
   }

   private static void putKeepAliveInfo(class_3218 serverLevel, UUID storageUuid, Runnable onFinishedHandler, class_243 pos, SoundHandler handler) {
      worldStorageSoundKeepAlive.computeIfAbsent(serverLevel.method_27983(), dim -> new HashMap<>())
         .put(storageUuid, new ServerStorageSoundHandler.KeepAliveInfo(onFinishedHandler, serverLevel.method_8510(), pos, handler));
   }

   public static void stopPlayingDisc(class_1937 level, class_243 position, UUID storageUuid) {
      class_5321<class_1937> dim = level.method_27983();
      if (worldStorageSoundKeepAlive.containsKey(dim) && worldStorageSoundKeepAlive.get(dim).containsKey(storageUuid)) {
         worldStorageSoundKeepAlive.get(dim).get(storageUuid).getSoundHandler().stop(level, position, storageUuid);
      }

      removeKeepAliveInfo(level, storageUuid, false);
      sendStopMessage(level, position, storageUuid);
   }

   private static void removeKeepAliveInfo(class_1937 level, UUID storageUuid, boolean finished) {
      class_5321<class_1937> dim = level.method_27983();
      if (worldStorageSoundKeepAlive.containsKey(dim) && worldStorageSoundKeepAlive.get(dim).containsKey(storageUuid)) {
         ServerStorageSoundHandler.KeepAliveInfo keepAliveInfo = worldStorageSoundKeepAlive.get(dim).remove(storageUuid);
         if (finished) {
            keepAliveInfo.runOnFinished();
         }
      }
   }

   private static void sendStopMessage(class_1937 level, class_243 position, UUID storageUuid) {
      if (level instanceof class_3218 serverLevel) {
         PacketDistributor.sendToAllNear(new StopDiscPlaybackPayload(storageUuid), level, position, 128);
      }
   }

   public static void onWorldUnload(MinecraftServer server, class_3218 serverLevel) {
      worldStorageSoundKeepAlive.remove(serverLevel.method_27983());
      lastWorldCheck.remove(serverLevel.method_27983());
   }

   static {
      registerSoundHandler(new SoundHandler() {
         @Override
         public boolean play(class_1937 level, class_2338 position, UUID storageUuid, class_1799 discItemStack, class_6880<class_9793> song) {
            PacketDistributor.sendToAllNear(new PlayDiscPayload(storageUuid, discItemStack, song, position), level, class_243.method_24953(position), 128);
            return true;
         }

         @Override
         public boolean play(class_1937 level, class_243 position, UUID storageUuid, int entityId, class_1799 discItemStack, class_6880<class_9793> song) {
            PacketDistributor.sendToAllNear(new PlayDiscPayload(storageUuid, discItemStack, song, entityId), level, position, 128);
            return true;
         }

         @Override
         public void stop(class_1937 level, class_243 position, UUID storageUuid) {
            ServerStorageSoundHandler.sendStopMessage(level, position, storageUuid);
         }

         @Override
         public void update(UUID storageUuid, class_243 position) {
         }
      });
   }

   private static class KeepAliveInfo {
      private final WeakReference<Runnable> onFinishedHandler;
      private long lastKeepAliveTime;
      private class_243 lastPosition;
      private final SoundHandler handler;

      private KeepAliveInfo(Runnable onFinishedHandler, long lastKeepAliveTime, class_243 lastPosition, SoundHandler handler) {
         this.onFinishedHandler = new WeakReference<>(onFinishedHandler);
         this.lastKeepAliveTime = lastKeepAliveTime;
         this.lastPosition = lastPosition;
         this.handler = handler;
      }

      public long getLastKeepAliveTime() {
         return this.lastKeepAliveTime;
      }

      public class_243 getLastPosition() {
         return this.lastPosition;
      }

      public SoundHandler getSoundHandler() {
         return this.handler;
      }

      public void update(long gameTime, class_243 position, UUID storageUuid) {
         this.lastKeepAliveTime = gameTime;
         this.lastPosition = position;
         this.handler.update(storageUuid, position);
      }

      public void runOnFinished() {
         Runnable handler = this.onFinishedHandler.get();
         if (handler != null) {
            handler.run();
         }
      }
   }
}
