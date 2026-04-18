package dev.architectury.compat.fabric;

import com.google.common.collect.Maps;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.architectury.platform.Mod;
import dev.architectury.platform.fabric.PlatformImpl;
import java.util.Map;
import java.util.Map.Entry;

public class ModMenuCompatibility implements ModMenuApi {
   private static final Map<String, ConfigScreenFactory<?>> FACTORIES = Maps.newHashMap();

   public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
      this.validateMap();
      return FACTORIES;
   }

   private void validateMap() {
      for (Entry<String, Mod.ConfigurationScreenProvider> entry : PlatformImpl.CONFIG_SCREENS.entrySet()) {
         if (!FACTORIES.containsKey(entry.getKey())) {
            FACTORIES.put(entry.getKey(), entry.getValue()::provide);
         }
      }
   }
}
