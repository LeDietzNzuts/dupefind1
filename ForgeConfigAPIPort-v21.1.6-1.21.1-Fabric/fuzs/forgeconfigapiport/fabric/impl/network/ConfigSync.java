package fuzs.forgeconfigapiport.fabric.impl.network;

import fuzs.forgeconfigapiport.fabric.impl.network.payload.ConfigFilePayload;
import fuzs.forgeconfigapiport.impl.ForgeConfigAPIPort;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.class_310;
import net.neoforged.fml.config.ConfigTracker;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfigs;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class ConfigSync {
   private static boolean isVanillaConnection = true;

   public static List<ConfigFilePayload> syncConfigs() {
      Map<String, byte[]> configData = ModConfigs.getConfigSet(ModConfig.Type.SERVER).stream().collect(Collectors.toMap(ModConfig::getFileName, mc -> {
         try {
            return Files.readAllBytes(mc.getFullPath());
         } catch (IOException var2) {
            throw new RuntimeException(var2);
         }
      }));
      return configData.entrySet().stream().map(e -> new ConfigFilePayload(e.getKey(), e.getValue())).toList();
   }

   public static void receiveSyncedConfig(byte[] contents, String fileName) {
      onEstablishModdedConnection();
      if (!class_310.method_1551().method_1542()) {
         Optional.ofNullable(ModConfigs.getFileMap().get(fileName)).ifPresent(mc -> ConfigTracker.acceptSyncedConfig(mc, contents));
      }
   }

   private static void onEstablishModdedConnection() {
      isVanillaConnection = false;
      ForgeConfigAPIPort.LOGGER.debug("Received modded connection marker from server");
   }

   public static void handleClientLoginSuccess() {
      if (isVanillaConnection) {
         ForgeConfigAPIPort.LOGGER.debug("Connected to a vanilla server. Catching up missing behaviour.");
         ConfigTracker.INSTANCE.loadDefaultServerConfigs();
      } else {
         isVanillaConnection = true;
         ForgeConfigAPIPort.LOGGER.debug("Connected to a modded server.");
      }
   }
}
