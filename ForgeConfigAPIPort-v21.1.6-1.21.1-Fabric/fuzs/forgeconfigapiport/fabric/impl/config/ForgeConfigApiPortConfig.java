package fuzs.forgeconfigapiport.fabric.impl.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.core.io.ParsingException;
import com.electronwill.nightconfig.core.io.WritingMode;
import fuzs.forgeconfigapiport.impl.ForgeConfigAPIPort;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;

public class ForgeConfigApiPortConfig {
   private static final ConfigSpec configSpec = new ConfigSpec();
   private static final CommentedConfig configComments = CommentedConfig.inMemory();
   private static final ForgeConfigApiPortConfig INSTANCE;
   @Nullable
   private CommentedFileConfig configData;

   private ForgeConfigApiPortConfig() {
   }

   private void loadFrom(Path configFile) {
      this.configData = (CommentedFileConfig)CommentedFileConfig.builder(configFile)
         .sync()
         .onFileNotFound(FileNotFoundAction.copyData(Objects.requireNonNull(this.getClass().getResourceAsStream("/forgeconfigapiport.toml"))))
         .writingMode(WritingMode.REPLACE)
         .build();

      try {
         this.configData.load();
      } catch (ParsingException var6) {
         ParsingException exception = var6;

         try {
            Files.delete(this.configData.getNioPath());
            this.configData.load();
            ForgeConfigAPIPort.LOGGER.warn("Configuration file {} could not be parsed. Correcting", this.configData.getNioPath(), exception);
         } catch (ParsingException var4) {
         } catch (Throwable var5) {
            throw new RuntimeException("Failed to load Forge Config API Port config from " + configFile, var5);
         }
      }

      if (!configSpec.isCorrect(this.configData)) {
         ForgeConfigAPIPort.LOGGER.warn("Configuration file {} is not correct. Correcting", configFile);
         configSpec.correct(
            this.configData,
            (action, path, incorrectValue, correctedValue) -> ForgeConfigAPIPort.LOGGER
               .info("Incorrect key {} was corrected from {} to {}", new Object[]{path, incorrectValue, correctedValue})
         );
      }

      this.configData.putAllComments(configComments);
      this.configData.save();
   }

   public static void load() {
      Path configFile = FabricLoader.getInstance().getConfigDir().resolve("forgeconfigapiport.toml");
      INSTANCE.loadFrom(configFile);
      ForgeConfigAPIPort.LOGGER.trace("Loaded FML config from {}", configFile);

      for (ModConfigValues cv : ModConfigValues.values()) {
         ForgeConfigAPIPort.LOGGER.trace("FMLConfig {} is {}", cv.entry, cv.getConfigValue(INSTANCE.configData));
      }

      getOrCreateGameRelativePath(Paths.get(getConfigValue(ModConfigValues.DEFAULT_CONFIGS_PATH)));
   }

   public static String getConfigValue(ModConfigValues v) {
      if (INSTANCE.configData == null) {
         load();
      }

      return v.getConfigValue(INSTANCE.configData);
   }

   public static boolean getBoolConfigValue(ModConfigValues v) {
      if (INSTANCE.configData == null) {
         load();
      }

      return v.<Boolean>getConfigValue(INSTANCE.configData);
   }

   public static Path getDefaultConfigsDirectory() {
      return FabricLoader.getInstance().getGameDir().resolve(getConfigValue(ModConfigValues.DEFAULT_CONFIGS_PATH));
   }

   private static Path getOrCreateGameRelativePath(Path path) {
      Path gameFolderPath = FabricLoader.getInstance().getGameDir().resolve(path);
      if (!Files.isDirectory(gameFolderPath)) {
         try {
            Files.createDirectories(gameFolderPath);
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }

      return gameFolderPath;
   }

   static {
      for (ModConfigValues cv : ModConfigValues.values()) {
         cv.buildConfigEntry(configSpec, configComments);
      }

      INSTANCE = new ForgeConfigApiPortConfig();
   }
}
