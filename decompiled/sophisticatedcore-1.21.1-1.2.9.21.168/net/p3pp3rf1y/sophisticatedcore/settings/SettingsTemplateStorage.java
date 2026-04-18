package net.p3pp3rf1y.sophisticatedcore.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.class_1657;
import net.minecraft.class_18;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_26;
import net.minecraft.class_3218;
import net.minecraft.class_18.class_8645;
import net.minecraft.class_7225.class_7874;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class SettingsTemplateStorage extends class_18 {
   private static final String SAVED_DATA_NAME = "sophisticatedcore_settings_templates";
   private Map<UUID, Map<Integer, class_2487>> playerTemplates = new HashMap<>();
   private Map<UUID, Map<String, class_2487>> playerNamedTemplates = new HashMap<>();
   private static final SettingsTemplateStorage clientStorageCopy = new SettingsTemplateStorage();

   private SettingsTemplateStorage() {
   }

   private SettingsTemplateStorage(Map<UUID, Map<Integer, class_2487>> playerTemplates, Map<UUID, Map<String, class_2487>> playerNamedTemplates) {
      this.playerTemplates = playerTemplates;
      this.playerNamedTemplates = playerNamedTemplates;
   }

   public void putPlayerTemplate(class_1657 player, int slot, class_2487 settingsTag) {
      this.playerTemplates.computeIfAbsent(player.method_5667(), u -> new HashMap<>()).put(slot, settingsTag);
      this.method_80();
   }

   public void putPlayerNamedTemplate(class_1657 player, String name, class_2487 settingsTag) {
      this.playerNamedTemplates.computeIfAbsent(player.method_5667(), u -> new TreeMap<>()).put(name, settingsTag);
      this.method_80();
   }

   public Map<Integer, class_2487> getPlayerTemplates(class_1657 player) {
      return this.playerTemplates.getOrDefault(player.method_5667(), new HashMap<>());
   }

   public Map<String, class_2487> getPlayerNamedTemplates(class_1657 player) {
      return this.playerNamedTemplates.getOrDefault(player.method_5667(), new TreeMap<>());
   }

   public class_2487 method_75(class_2487 tag, class_7874 registries) {
      NBTHelper.putMap(
         tag,
         "playerTemplates",
         this.playerTemplates,
         UUID::toString,
         slotTemplates -> NBTHelper.putMap(new class_2487(), "slotTemplates", slotTemplates, String::valueOf, settingsTag -> settingsTag)
      );
      NBTHelper.putMap(
         tag,
         "playerNamedTemplates",
         this.playerNamedTemplates,
         UUID::toString,
         namedTemplates -> NBTHelper.putMap(new class_2487(), "namedTemplates", namedTemplates, v -> v, settingsTag -> settingsTag)
      );
      return tag;
   }

   public static SettingsTemplateStorage get() {
      if (SophisticatedCore.isLogicalServerThread()) {
         MinecraftServer server = SophisticatedCore.getCurrentServer();
         if (server != null) {
            class_3218 overworld = server.method_3847(class_1937.field_25179);
            class_26 storage = overworld.method_17983();
            return (SettingsTemplateStorage)storage.method_17924(
               new class_8645(SettingsTemplateStorage::new, SettingsTemplateStorage::load, null), "sophisticatedcore_settings_templates"
            );
         }
      }

      return clientStorageCopy;
   }

   private static SettingsTemplateStorage load(class_2487 tag, class_7874 registries) {
      return new SettingsTemplateStorage(
         NBTHelper.<UUID, Map<Integer, class_2487>>getMap(
               tag,
               "playerTemplates",
               UUID::fromString,
               (key, playerTemplatesTag) -> NBTHelper.getMap(
                  (class_2487)playerTemplatesTag, "slotTemplates", Integer::valueOf, (k, settingsTag) -> Optional.of((class_2487)settingsTag)
               )
            )
            .orElse(new HashMap<>()),
         NBTHelper.<UUID, Map<String, class_2487>>getMap(
               tag,
               "playerNamedTemplates",
               UUID::fromString,
               (key, playerNamedTemplatesTag) -> NBTHelper.getMap(
                  (class_2487)playerNamedTemplatesTag, "namedTemplates", v -> v, (k, settingsTag) -> Optional.of((class_2487)settingsTag), TreeMap::new
               )
            )
            .orElse(new TreeMap<>())
      );
   }

   public void clearPlayerTemplates(class_1657 player) {
      this.playerTemplates.remove(player.method_5667());
      this.playerNamedTemplates.remove(player.method_5667());
      this.method_80();
   }
}
