package fuzs.forgeconfigapiport.fabric.impl;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.impl.handler.ServerLifecycleHandler;
import fuzs.forgeconfigapiport.fabric.impl.network.configuration.SyncConfig;
import fuzs.forgeconfigapiport.fabric.impl.network.payload.ConfigFilePayload;
import fuzs.forgeconfigapiport.impl.services.CommonAbstractions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStopping;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents.Configure;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfigs;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ForgeConfigAPIPortFabric implements ModInitializer {
   public void onInitialize() {
      registerMessages();
      registerEventHandlers();
      setupDevelopmentEnvironment();
   }

   private static void registerMessages() {
      ServerConfigurationConnectionEvents.CONFIGURE.register((Configure)(handler, server) -> {
         if (ServerConfigurationNetworking.canSend(handler, ConfigFilePayload.TYPE)) {
            handler.addTask(new SyncConfig(handler));
         }
      });
      PayloadTypeRegistry.configurationS2C().register(ConfigFilePayload.TYPE, ConfigFilePayload.STREAM_CODEC);
   }

   private static void registerEventHandlers() {
      ServerLifecycleEvents.SERVER_STARTING.addPhaseOrdering(ServerLifecycleHandler.BEFORE_PHASE, Event.DEFAULT_PHASE);
      ServerLifecycleEvents.SERVER_STARTING.register(ServerLifecycleHandler.BEFORE_PHASE, ServerLifecycleHandler::onServerStarting);
      ServerLifecycleEvents.SERVER_STOPPED.addPhaseOrdering(Event.DEFAULT_PHASE, ServerLifecycleHandler.AFTER_PHASE);
      ServerLifecycleEvents.SERVER_STOPPED.register(ServerLifecycleHandler.AFTER_PHASE, ServerLifecycleHandler::onServerStopped);
      ServerLifecycleEvents.SERVER_STOPPING.register((ServerStopping)server -> ModConfigs.getFileMap().values().forEach(config -> {
         if (config.getSpec() instanceof ModConfigSpec spec) {
            spec.resetCaches(ModConfigSpec.RestartType.WORLD);
         }
      }));
   }

   private static void setupDevelopmentEnvironment() {
      if (CommonAbstractions.INSTANCE.includeTestConfigs()) {
         NeoForgeConfigRegistry.INSTANCE
            .register(
               "forgeconfigapiport",
               ModConfig.Type.SERVER,
               new ModConfigSpec.Builder().comment("hello world").define("dummy_entry", true).next().build(),
               "forgeconfigapiport-server-neoforge.toml"
            );
      }
   }
}
