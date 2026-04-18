package fuzs.forgeconfigapiport.fabric.impl.handler;

import com.electronwill.nightconfig.core.file.FileWatcher;
import fuzs.forgeconfigapiport.impl.ForgeConfigAPIPort;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_2960;
import net.minecraft.class_5218;
import net.minecraft.server.MinecraftServer;
import net.neoforged.fml.config.ConfigTracker;
import net.neoforged.fml.config.ModConfig;

public class ServerLifecycleHandler {
   public static final class_2960 BEFORE_PHASE = ForgeConfigAPIPort.id("before");
   public static final class_2960 AFTER_PHASE = ForgeConfigAPIPort.id("after");
   private static final class_5218 SERVER_CONFIG_LEVEL_RESOURCE = new class_5218("serverconfig");

   public static void onServerStarting(MinecraftServer minecraftServer) {
      ConfigTracker.INSTANCE.loadConfigs(ModConfig.Type.SERVER, FabricLoader.getInstance().getConfigDir(), getServerConfigPath(minecraftServer));
   }

   public static void onServerStopped(MinecraftServer minecraftServer) {
      ConfigTracker.INSTANCE.unloadConfigs(ModConfig.Type.SERVER);
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
         FileWatcher.defaultInstance().stop();
      }
   }

   private static Path getServerConfigPath(MinecraftServer server) {
      Path serverConfig = server.method_27050(SERVER_CONFIG_LEVEL_RESOURCE);
      if (!Files.isDirectory(serverConfig)) {
         try {
            Files.createDirectories(serverConfig);
         } catch (IOException var5) {
            throw new RuntimeException(var5);
         }
      }

      Path explanation = serverConfig.resolve("readme.txt");
      if (!Files.exists(explanation)) {
         try {
            Files.writeString(
               explanation,
               "Any server configs put in this folder will override the corresponding server config from <instance path>/config/<config path>.\nIf the config being transferred is in a subfolder of the base config folder make sure to include that folder here in the path to the file you are overwriting.\nFor example if you are overwriting a config with the path <instance path>/config/ExampleMod/config-server.toml, you would need to put it in serverconfig/ExampleMod/config-server.toml\n",
               StandardCharsets.UTF_8
            );
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }
      }

      return serverConfig;
   }
}
