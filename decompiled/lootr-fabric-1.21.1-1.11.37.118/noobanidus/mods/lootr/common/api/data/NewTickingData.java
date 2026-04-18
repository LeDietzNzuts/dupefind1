package noobanidus.mods.lootr.common.api.data;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.io.File;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_26;
import net.minecraft.class_3218;
import net.minecraft.class_18.class_8645;
import net.minecraft.class_7225.class_7874;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.LootrAPI;

public class NewTickingData {
   private static final Function<String, class_8645<NewTickingData.Section>> STRING_FACTORY = name -> new class_8645(
      () -> new NewTickingData.Section(name), NewTickingData.Section::load, null
   );
   private static final Object2ObjectMap<UUID, String> CACHED_NAMES = new Object2ObjectOpenHashMap();
   private static final Object2ObjectMap<UUID, String> CACHED_FILE_NAMES = new Object2ObjectOpenHashMap();
   private static final NewTickingData REFRESH_DATA = new NewTickingData(NewTickingData.TickingType.REFRESH);
   private static final NewTickingData DECAY_DATA = new NewTickingData(NewTickingData.TickingType.DECAY);
   private final String prefix;
   private final NewTickingData.TickingType type;

   public static NewTickingData getRefreshData() {
      return REFRESH_DATA;
   }

   public static NewTickingData getDecayData() {
      return DECAY_DATA;
   }

   public void migrateOldData(MinecraftServer server, TickingData oldData) {
      long currentGameTime = server.method_27728().method_27859().method_188();
      ObjectIterator var5 = oldData.getTickMap().object2IntEntrySet().iterator();

      while (var5.hasNext()) {
         Entry<UUID> entry = (Entry<UUID>)var5.next();
         UUID id = (UUID)entry.getKey();
         long completesAt = currentGameTime;
         int oldValue = entry.getIntValue();
         if (oldValue > 0) {
            completesAt = currentGameTime + oldValue;
         }

         NewTickingData.Section section = this.getSection(server, id);

         try {
            section.setCompletesAt(id, completesAt);
         } catch (NewTickingData.SectionException var13) {
            LootrAPI.LOG.error("Unable to migrate {} ticking data for id {}: section mismatch, expected {}", this.type.getPrefix(), id, section.cachedName);
         }
      }

      oldData.clear();
   }

   protected NewTickingData(NewTickingData.TickingType type) {
      this.type = type;
      this.prefix = "lootr/ticking/" + type.getPrefix() + "/";
   }

   public void setCompletesIn(MinecraftServer server, UUID id, long tickTime) {
      NewTickingData.Section section = this.getSection(server, id);

      try {
         section.setCompletesAt(id, server.method_27728().method_27859().method_188() + tickTime);
      } catch (NewTickingData.SectionException var7) {
         LootrAPI.LOG.error("Unable to set {} ticking data for id {}: section mismatch, expected {}", this.type.getPrefix(), id, section.cachedName);
      }
   }

   public void clearTicking(MinecraftServer server, UUID id) {
      NewTickingData.Section section = this.getSection(server, id);

      try {
         section.setCompletesAt(id, -1L);
      } catch (NewTickingData.SectionException var5) {
         LootrAPI.LOG.error("Unable to clear {} ticking data for id {}: section mismatch, expected {}", this.type.getPrefix(), id, section.cachedName);
      }
   }

   public long howLongUntilComplete(MinecraftServer server, UUID id) {
      NewTickingData.Section section = this.getSection(server, id);

      try {
         long completesAt = section.completesAt(id);
         if (completesAt == -1L) {
            return -1L;
         } else {
            long currentTime = server.method_27728().method_27859().method_188();
            return Math.max(0L, completesAt - currentTime);
         }
      } catch (NewTickingData.SectionException var8) {
         LootrAPI.LOG.error("Unable to get {} ticking data for id {}: section mismatch, expected {}", this.type.getPrefix(), id, section.cachedName);
         return -1L;
      }
   }

   private static String getCached(UUID id) {
      return (String)CACHED_NAMES.computeIfAbsent(id, UUID::toString);
   }

   private static String getBaseFileName(UUID id) {
      return (String)CACHED_FILE_NAMES.computeIfAbsent(id, uuid -> {
         String name = getCached(uuid);
         return name.substring(0, 2);
      });
   }

   private String getFileName(UUID id) {
      return this.prefix + getBaseFileName(id);
   }

   private NewTickingData.Section getSection(MinecraftServer server, UUID id) {
      class_3218 level = server.method_30002();
      class_26 dataStorage = level.method_17983();
      return (NewTickingData.Section)dataStorage.method_17924(STRING_FACTORY.apply(getBaseFileName(id)), this.getFileName(id));
   }

   protected static class Section extends class_18 {
      private final Object2LongMap<UUID> tickMap = new Object2LongOpenHashMap();
      private final String cachedName;

      public Section(String cachedName) {
         this.tickMap.defaultReturnValue(-1L);
         this.cachedName = cachedName;
      }

      private boolean excludes(UUID id) {
         return !this.cachedName.equals(NewTickingData.getBaseFileName(id));
      }

      public boolean completed(MinecraftServer server, UUID id) throws NewTickingData.SectionException {
         if (this.excludes(id)) {
            throw new NewTickingData.SectionException();
         } else {
            long completesAt = this.completesAt(id);
            return completesAt == -1L ? false : server.method_27728().method_27859().method_188() >= completesAt;
         }
      }

      public long completesAt(UUID id) throws NewTickingData.SectionException {
         if (this.excludes(id)) {
            throw new NewTickingData.SectionException();
         } else {
            return this.tickMap.getLong(id);
         }
      }

      public void setCompletesAt(UUID id, long tickTime) throws NewTickingData.SectionException {
         if (this.excludes(id)) {
            throw new NewTickingData.SectionException();
         } else {
            this.tickMap.put(id, tickTime);
            this.method_80();
         }
      }

      public class_2487 method_75(class_2487 tag, class_7874 registries) {
         class_2499 decayList = new class_2499();
         ObjectIterator var4 = this.tickMap.object2LongEntrySet().iterator();

         while (var4.hasNext()) {
            it.unimi.dsi.fastutil.objects.Object2LongMap.Entry<UUID> entry = (it.unimi.dsi.fastutil.objects.Object2LongMap.Entry<UUID>)var4.next();
            class_2487 thisTag = new class_2487();
            thisTag.method_25927("id", (UUID)entry.getKey());
            thisTag.method_10544("value", entry.getLongValue());
            decayList.add(thisTag);
         }

         tag.method_10566("result", decayList);
         tag.method_10582("cachedName", this.cachedName);
         return tag;
      }

      public static NewTickingData.Section load(class_2487 pCompound, class_7874 provider) {
         String cachedName = pCompound.method_10558("cachedName");
         NewTickingData.Section data = new NewTickingData.Section(cachedName);
         data.tickMap.clear();
         data.tickMap.defaultReturnValue(-1L);
         class_2499 decayList = pCompound.method_10554("result", 10);

         for (int i = 0; i < decayList.size(); i++) {
            class_2487 thisTag = decayList.method_10602(i);
            data.tickMap.put(thisTag.method_25926("id"), thisTag.method_10537("value"));
         }

         return data;
      }

      public void method_17919(File file, class_7874 registries) {
         if (this.method_79()) {
            file.getParentFile().mkdirs();
         }

         super.method_17919(file, registries);
      }
   }

   public static class SectionException extends Exception {
   }

   public static enum TickingType {
      DECAY("decay"),
      REFRESH("refresh");

      private final String prefix;

      private TickingType(String prefix) {
         this.prefix = prefix;
      }

      public String getPrefix() {
         return this.prefix;
      }
   }
}
