package fuzs.forgeconfigapiport.fabric.impl.core;

import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeModConfigEvents;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents;
import java.util.function.Consumer;
import net.neoforged.fml.config.ModConfig;

public final class ModConfigEventsHelper {
   private ModConfigEventsHelper() {
   }

   public static Consumer<ModConfig> loading() {
      return modConfig -> {
         ((NeoForgeModConfigEvents.Loading)NeoForgeModConfigEvents.loading(modConfig.getModId()).invoker()).onModConfigLoading(modConfig);
         if (modConfig.modConfig != null) {
            ((ForgeModConfigEvents.Loading)ForgeModConfigEvents.loading(modConfig.getModId()).invoker()).onModConfigLoading(modConfig.modConfig);
         }
      };
   }

   public static Consumer<ModConfig> reloading() {
      return modConfig -> {
         ((NeoForgeModConfigEvents.Reloading)NeoForgeModConfigEvents.reloading(modConfig.getModId()).invoker()).onModConfigReloading(modConfig);
         if (modConfig.modConfig != null) {
            ((ForgeModConfigEvents.Reloading)ForgeModConfigEvents.reloading(modConfig.getModId()).invoker()).onModConfigReloading(modConfig.modConfig);
         }
      };
   }

   public static Consumer<ModConfig> unloading() {
      return modConfig -> {
         ((NeoForgeModConfigEvents.Unloading)NeoForgeModConfigEvents.unloading(modConfig.getModId()).invoker()).onModConfigUnloading(modConfig);
         if (modConfig.modConfig != null) {
            ((ForgeModConfigEvents.Unloading)ForgeModConfigEvents.unloading(modConfig.getModId()).invoker()).onModConfigUnloading(modConfig.modConfig);
         }
      };
   }
}
