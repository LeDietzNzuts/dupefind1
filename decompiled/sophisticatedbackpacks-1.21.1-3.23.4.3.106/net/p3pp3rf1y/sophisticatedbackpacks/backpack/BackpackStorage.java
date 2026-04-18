package net.p3pp3rf1y.sophisticatedbackpacks.backpack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.class_18;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2512;
import net.minecraft.class_2520;
import net.minecraft.class_26;
import net.minecraft.class_310;
import net.minecraft.class_3218;
import net.minecraft.class_638;
import net.minecraft.class_18.class_8645;
import net.minecraft.class_7225.class_7874;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;

public class BackpackStorage extends class_18 {
   private static final String SAVED_DATA_NAME = "sophisticatedbackpacks";
   private final Map<UUID, class_2487> backpackContents = new HashMap<>();
   private static final BackpackStorage clientStorageCopy = new BackpackStorage();
   private final Map<UUID, AccessLogRecord> accessLogRecords = new HashMap<>();
   private final Set<UUID> updatedBackpackSettingsFlags = new HashSet<>();

   private BackpackStorage() {
   }

   public static BackpackStorage get() {
      if (SophisticatedCore.isLogicalServerThread()) {
         MinecraftServer server = SophisticatedCore.getCurrentServer();
         if (server != null) {
            class_3218 overworld = server.method_3847(class_1937.field_25179);
            class_26 storage = overworld.method_17983();
            return (BackpackStorage)storage.method_17924(new class_8645(BackpackStorage::new, BackpackStorage::load, null), "sophisticatedbackpacks");
         }
      }

      return clientStorageCopy;
   }

   public static BackpackStorage load(class_2487 nbt, class_7874 registries) {
      BackpackStorage storage = new BackpackStorage();
      readBackpackContents(nbt, storage);
      readAccessLogs(nbt, storage);
      return storage;
   }

   private static void readAccessLogs(class_2487 nbt, BackpackStorage storage) {
      for (class_2520 n : nbt.method_10554("accessLogRecords", 10)) {
         AccessLogRecord alr = AccessLogRecord.deserializeFromNBT((class_2487)n);
         storage.accessLogRecords.put(alr.getBackpackUuid(), alr);
      }
   }

   private static void readBackpackContents(class_2487 nbt, BackpackStorage storage) {
      for (class_2520 n : nbt.method_10554("backpackContents", 10)) {
         class_2487 uuidContentsPair = (class_2487)n;
         UUID uuid = class_2512.method_25930(Objects.requireNonNull(uuidContentsPair.method_10580("uuid")));
         class_2487 contents = uuidContentsPair.method_10562("contents");
         storage.backpackContents.put(uuid, contents);
      }
   }

   public class_2487 method_75(class_2487 compound, class_7874 registries) {
      class_2487 ret = new class_2487();
      this.writeBackpackContents(ret);
      this.writeAccessLogs(ret);
      return ret;
   }

   private void writeBackpackContents(class_2487 ret) {
      class_2499 backpackContentsNbt = new class_2499();

      for (Entry<UUID, class_2487> entry : this.backpackContents.entrySet()) {
         class_2487 uuidContentsPair = new class_2487();
         uuidContentsPair.method_10566("uuid", class_2512.method_25929(entry.getKey()));
         uuidContentsPair.method_10566("contents", (class_2520)entry.getValue());
         backpackContentsNbt.add(uuidContentsPair);
      }

      ret.method_10566("backpackContents", backpackContentsNbt);
   }

   private void writeAccessLogs(class_2487 ret) {
      class_2499 accessLogsNbt = new class_2499();

      for (AccessLogRecord alr : this.accessLogRecords.values()) {
         accessLogsNbt.add(alr.serializeToNBT());
      }

      ret.method_10566("accessLogRecords", accessLogsNbt);
   }

   public class_2487 getOrCreateBackpackContents(UUID backpackUuid) {
      return this.backpackContents.computeIfAbsent(backpackUuid, uuid -> {
         this.method_80();
         return new class_2487();
      });
   }

   public void putAccessLog(AccessLogRecord alr) {
      this.accessLogRecords.put(alr.getBackpackUuid(), alr);
      this.method_80();
   }

   public void removeBackpackContents(UUID backpackUuid) {
      this.backpackContents.remove(backpackUuid);
      this.method_80();
   }

   public void setBackpackContents(UUID backpackUuid, class_2487 contents) {
      if (!this.backpackContents.containsKey(backpackUuid)) {
         this.backpackContents.put(backpackUuid, contents);
         this.updatedBackpackSettingsFlags.add(backpackUuid);
      } else {
         class_2487 currentContents = this.backpackContents.get(backpackUuid);

         for (String key : contents.method_10541()) {
            currentContents.method_10566(key, contents.method_10580(key));
            if (key.equals("settings")) {
               this.updatedBackpackSettingsFlags.add(backpackUuid);
            }
         }

         this.method_80();
      }
   }

   public Map<UUID, AccessLogRecord> getAccessLogs() {
      return this.accessLogRecords;
   }

   public int removeNonPlayerBackpackContents(boolean onlyWithEmptyInventory) {
      AtomicInteger numberRemoved = new AtomicInteger(0);
      this.backpackContents.entrySet().removeIf(entry -> {
         if (this.accessLogRecords.containsKey(entry.getKey()) || onlyWithEmptyInventory && entry.getValue().method_10545("inventory")) {
            return false;
         } else {
            numberRemoved.incrementAndGet();
            return true;
         }
      });
      if (numberRemoved.get() > 0) {
         this.method_80();
      }

      return numberRemoved.get();
   }

   public boolean removeUpdatedBackpackSettingsFlag(UUID backpackUuid) {
      return this.updatedBackpackSettingsFlags.remove(backpackUuid);
   }

   public static void onClientWorldLoad(class_310 mc, class_638 level) {
      clientStorageCopy.backpackContents.clear();
      clientStorageCopy.accessLogRecords.clear();
   }
}
