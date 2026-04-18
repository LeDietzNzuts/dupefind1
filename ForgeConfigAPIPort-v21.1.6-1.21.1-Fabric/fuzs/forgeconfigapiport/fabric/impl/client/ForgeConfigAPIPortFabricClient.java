package fuzs.forgeconfigapiport.fabric.impl.client;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import fuzs.forgeconfigapiport.fabric.impl.client.commands.FabricConfigCommand;
import fuzs.forgeconfigapiport.fabric.impl.network.ConfigSync;
import fuzs.forgeconfigapiport.fabric.impl.network.payload.ConfigFilePayload;
import fuzs.forgeconfigapiport.impl.services.CommonAbstractions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationConnectionEvents.Complete;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.Disconnect;
import net.neoforged.fml.config.ModConfigs;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ForgeConfigAPIPortFabricClient implements ClientModInitializer {
   public void onInitializeClient() {
      registerMessages();
      registerEventHandlers();
      setupDevelopmentEnvironment();
   }

   private static void registerMessages() {
      ClientConfigurationNetworking.registerGlobalReceiver(
         ConfigFilePayload.TYPE, (payload, context) -> ConfigSync.receiveSyncedConfig(payload.contents(), payload.fileName())
      );
   }

   private static void registerEventHandlers() {
      ClientCommandRegistrationCallback.EVENT
         .register(
            (ClientCommandRegistrationCallback)(dispatcher, registryAccess) -> FabricConfigCommand.register(dispatcher, FabricClientCommandSource::sendFeedback)
         );
      ClientConfigurationConnectionEvents.COMPLETE.register((Complete)(handler, client) -> ConfigSync.handleClientLoginSuccess());
      ClientPlayConnectionEvents.DISCONNECT.register((Disconnect)(handler, client) -> ModConfigs.getFileMap().values().forEach(config -> {
         if (config.getSpec() instanceof ModConfigSpec spec) {
            spec.resetCaches(ModConfigSpec.RestartType.WORLD);
         }
      }));
   }

   private static void setupDevelopmentEnvironment() {
      if (CommonAbstractions.INSTANCE.includeTestConfigs()) {
         ConfigScreenFactoryRegistry.INSTANCE.register("forgeconfigapiport", ConfigurationScreen::new);
      }
   }
}
