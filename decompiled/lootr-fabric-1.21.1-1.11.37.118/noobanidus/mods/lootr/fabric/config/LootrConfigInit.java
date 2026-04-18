package noobanidus.mods.lootr.fabric.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.class_1269;

public class LootrConfigInit {
   public static void registerConfig() {
      AutoConfig.register(ConfigManager.class, GsonConfigSerializer::new);
      ConfigHolder<ConfigManager> config = AutoConfig.getConfigHolder(ConfigManager.class);
      config.registerLoadListener((manager, configData) -> {
         ConfigManager.reset();
         return class_1269.field_5811;
      });
      config.registerSaveListener((manager, configData) -> {
         ConfigManager.reset();
         return class_1269.field_5811;
      });
   }
}
