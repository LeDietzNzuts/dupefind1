package net.neoforged.fml.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ModConfigs {
   public static List<ModConfig> getModConfigs(String modId) {
      return List.copyOf(ConfigTracker.INSTANCE.configsByMod.getOrDefault(modId, List.of()));
   }

   public static List<String> getConfigFileNames(String modId, ModConfig.Type type) {
      List<ModConfig> config = ConfigTracker.INSTANCE.configsByMod.getOrDefault(modId, List.of());
      synchronized (config) {
         return config.stream().filter(c -> c.getType() == type).map(ModConfig::getFileName).toList();
      }
   }

   public static Set<ModConfig> getConfigSet(ModConfig.Type type) {
      return Collections.unmodifiableSet(ConfigTracker.INSTANCE.configSets.get(type));
   }

   public static Map<String, ModConfig> getFileMap() {
      return Collections.unmodifiableMap(ConfigTracker.INSTANCE.fileMap);
   }
}
