package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_156;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_7923;

public class BackpackAccessLogger {
   private static final int REFRESH_INTERVAL_SECONDS = 30;
   private static long lastCacheRefresh = 0L;
   private static final Map<String, Set<AccessLogRecord>> playerLogCache = new HashMap<>();

   private BackpackAccessLogger() {
   }

   public static void logPlayerAccess(
      class_1657 player, class_1792 backpackItem, UUID backpackUuid, String backpackName, int clothColor, int trimColor, int columnsTaken
   ) {
      if (!player.method_37908().field_9236) {
         BackpackStorage.get()
            .putAccessLog(
               new AccessLogRecord(
                  class_7923.field_41178.method_10221(backpackItem),
                  backpackUuid,
                  player.method_7334().getName(),
                  backpackName,
                  clothColor,
                  trimColor,
                  class_156.method_659(),
                  columnsTaken
               )
            );
      }
   }

   public static Set<String> getPlayerNames() {
      initPlayerBackpackCache();
      return playerLogCache.keySet();
   }

   public static Collection<AccessLogRecord> getBackpackLogsForPlayer(String playerName) {
      initPlayerBackpackCache();
      return playerLogCache.getOrDefault(playerName, new HashSet<>());
   }

   private static void initPlayerBackpackCache() {
      if (lastCacheRefresh + 30000L < class_156.method_659()) {
         lastCacheRefresh = class_156.method_659();
         playerLogCache.clear();
         BackpackStorage.get().getAccessLogs().values().forEach(alr -> playerLogCache.computeIfAbsent(alr.getPlayerName(), name -> new HashSet<>()).add(alr));
      }
   }

   public static Collection<AccessLogRecord> getAllBackpackLogs() {
      return BackpackStorage.get().getAccessLogs().values();
   }

   public static Set<UUID> getBackpackUuids() {
      return BackpackStorage.get().getAccessLogs().keySet();
   }

   public static Optional<AccessLogRecord> getBackpackLog(UUID backpackUuid) {
      return Optional.ofNullable(BackpackStorage.get().getAccessLogs().get(backpackUuid));
   }
}
