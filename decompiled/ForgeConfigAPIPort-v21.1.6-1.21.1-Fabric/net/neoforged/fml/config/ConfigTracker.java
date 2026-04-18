package net.neoforged.fml.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.InMemoryCommentedFormat;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.concurrent.SynchronizedConfig;
import com.electronwill.nightconfig.core.file.FileWatcher;
import com.electronwill.nightconfig.core.io.ParsingException;
import com.electronwill.nightconfig.core.io.ParsingMode;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlParser;
import com.electronwill.nightconfig.toml.TomlWriter;
import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.fabric.impl.config.ForgeConfigApiPortConfig;
import fuzs.forgeconfigapiport.fabric.impl.config.ModConfigValues;
import fuzs.forgeconfigapiport.fabric.impl.core.ModConfigEventsHelper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import net.fabricmc.loader.api.FabricLoader;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Internal
public class ConfigTracker {
   public static final ConfigTracker INSTANCE = new ConfigTracker();
   static final Marker CONFIG = MarkerFactory.getMarker("CONFIG");
   private static final Logger LOGGER = LogUtils.getLogger();
   private static final Path defaultConfigPath = ForgeConfigApiPortConfig.getDefaultConfigsDirectory();
   final ConcurrentHashMap<String, ModConfig> fileMap = new ConcurrentHashMap<>();
   final EnumMap<ModConfig.Type, Set<ModConfig>> configSets = new EnumMap<>(ModConfig.Type.class);
   final ConcurrentHashMap<String, List<ModConfig>> configsByMod = new ConcurrentHashMap<>();
   private final Map<String, ReentrantLock> locksByMod = new ConcurrentHashMap<>();

   @VisibleForTesting
   public ConfigTracker() {
      for (ModConfig.Type type : ModConfig.Type.values()) {
         this.configSets.put(type, Collections.synchronizedSet(new LinkedHashSet<>()));
      }
   }

   public ModConfig registerConfig(ModConfig.Type type, IConfigSpec spec, String modId) {
      return this.registerConfig(type, spec, modId, defaultConfigName(type, modId));
   }

   public ModConfig registerConfig(ModConfig.Type type, IConfigSpec spec, String modId, String fileName) {
      ReentrantLock lock = this.locksByMod.computeIfAbsent(modId, m -> new ReentrantLock());
      ModConfig modConfig = new ModConfig(type, spec, modId, fileName, lock);
      this.validateSpec(spec, modConfig);
      this.trackConfig(modConfig);
      if (modConfig.getType() != ModConfig.Type.SERVER) {
         openConfig(modConfig, FabricLoader.getInstance().getConfigDir(), null);
      }

      return modConfig;
   }

   private void validateSpec(IConfigSpec spec, ModConfig config) {
      if (spec instanceof ModConfigSpec) {
         this.forEachValue(
            ((ModConfigSpec)spec).getValues().valueMap().values(),
            configValue -> {
               if (configValue.getSpec().restartType() == ModConfigSpec.RestartType.GAME && config.getType() == ModConfig.Type.SERVER) {
                  throw new IllegalArgumentException(
                     "Configuration value "
                        + String.join(".", configValue.getPath())
                        + " defined in config "
                        + config.getFileName()
                        + " has restart of type "
                        + configValue.getSpec().restartType()
                        + " which cannot be used for configs of type "
                        + config.getType()
                  );
               }
            }
         );
      }
   }

   private void forEachValue(Iterable<Object> configValues, Consumer<ModConfigSpec.ConfigValue<?>> consumer) {
      configValues.forEach(value -> {
         if (value instanceof ModConfigSpec.ConfigValue<?> configValue) {
            consumer.accept(configValue);
         } else if (value instanceof Config innerConfig) {
            this.forEachValue(innerConfig.valueMap().values(), consumer);
         }
      });
   }

   private static String defaultConfigName(ModConfig.Type type, String modId) {
      return String.format(Locale.ROOT, "%s-%s.toml", modId, type.extension());
   }

   void trackConfig(ModConfig config) {
      ModConfig previousValue = this.fileMap.putIfAbsent(config.getFileName(), config);
      if (previousValue != null) {
         String errorMessage = String.format(
            Locale.ROOT,
            "Detected config file conflict on %s from %s (already registered by %s)",
            config.getFileName(),
            config.getModId(),
            previousValue.getModId()
         );
         LOGGER.error(CONFIG, "{}", errorMessage);
         throw new RuntimeException(errorMessage);
      } else {
         this.configSets.get(config.getType()).add(config);
         this.configsByMod.computeIfAbsent(config.getModId(), k -> Collections.synchronizedList(new ArrayList<>())).add(config);
         LOGGER.debug(CONFIG, "Config file {} for {} tracking", config.getFileName(), config.getModId());
      }
   }

   public void loadConfigs(ModConfig.Type type, Path configBasePath) {
      this.loadConfigs(type, configBasePath, null);
   }

   public void loadConfigs(ModConfig.Type type, Path configBasePath, @Nullable Path configOverrideBasePath) {
      LOGGER.debug(CONFIG, "Loading configs type {}", type);
      this.configSets.get(type).forEach(config -> openConfig(config, configBasePath, configOverrideBasePath));
   }

   public void unloadConfigs(ModConfig.Type type) {
      LOGGER.debug(CONFIG, "Unloading configs type {}", type);
      this.configSets.get(type).forEach(ConfigTracker::closeConfig);
   }

   static void openConfig(ModConfig config, Path configBasePath, @Nullable Path configOverrideBasePath) {
      LOGGER.trace(CONFIG, "Loading config file type {} at {} for {}", new Object[]{config.getType(), config.getFileName(), config.getModId()});
      if (config.loadedConfig != null) {
         LOGGER.warn("Opening a config that was already loaded with value {} at path {}", config.loadedConfig, config.getFileName());
      }

      Path basePath = resolveBasePath(config, configBasePath, configOverrideBasePath);
      Path configPath = basePath.resolve(config.getFileName());
      loadConfig(config, configPath, ModConfigEventsHelper.loading());
      LOGGER.debug(CONFIG, "Loaded TOML config file {}", configPath);
      if (!ForgeConfigApiPortConfig.getBoolConfigValue(ModConfigValues.DISABLE_CONFIG_WATCHER)) {
         FileWatcher.defaultInstance().addWatch(configPath, new ConfigWatcher(config, configPath, Thread.currentThread().getContextClassLoader()));
         LOGGER.debug(CONFIG, "Watching TOML config file {} for changes", configPath);
      }
   }

   private static Path resolveBasePath(ModConfig config, Path configBasePath, @Nullable Path configOverrideBasePath) {
      if (configOverrideBasePath != null) {
         Path overrideFilePath = configOverrideBasePath.resolve(config.getFileName());
         if (Files.exists(overrideFilePath)) {
            LOGGER.info(CONFIG, "Found config file override in path {}", overrideFilePath);
            return configOverrideBasePath;
         }
      }

      return configBasePath;
   }

   static void loadConfig(ModConfig modConfig, Path path, Consumer<ModConfig> eventConstructor) {
      CommentedConfig config;
      try {
         config = readConfig(path);
         if (!modConfig.getSpec().isCorrect(config)) {
            LOGGER.warn(CONFIG, "Configuration file {} is not correct. Correcting", path);
            backUpConfig(path);
            modConfig.getSpec().correct(config);
            writeConfig(path, config);
         }
      } catch (NoSuchFileException var8) {
         try {
            setupConfigFile(modConfig, path);
            config = readConfig(path);
         } catch (ParsingException | IOException var7) {
            throw new RuntimeException(
               "Failed to create default config file " + modConfig.getFileName() + " of type " + modConfig.getType() + " for modid " + modConfig.getModId(),
               var7
            );
         }
      } catch (ParsingException | IOException var9) {
         LOGGER.warn(CONFIG, "Failed to load config {}: {}. Attempting to recreate", modConfig.getFileName(), var9);

         try {
            backUpConfig(path);
            Files.delete(path);
            setupConfigFile(modConfig, path);
            config = readConfig(path);
         } catch (Throwable var6) {
            var9.addSuppressed(var6);
            throw new RuntimeException(
               "Failed to recreate config file " + modConfig.getFileName() + " of type " + modConfig.getType() + " for modid " + modConfig.getModId(), var9
            );
         }
      }

      modConfig.setConfig(new LoadedConfig(config, path, modConfig), eventConstructor);
   }

   public static void acceptSyncedConfig(ModConfig modConfig, byte[] bytes) {
      if (modConfig.loadedConfig != null) {
         LOGGER.warn("Overwriting non-null config {} at path {} with synced config", modConfig.loadedConfig, modConfig.getFileName());
      }

      SynchronizedConfig newConfig = new SynchronizedConfig(InMemoryCommentedFormat.defaultInstance(), LinkedHashMap::new);
      newConfig.bulkCommentedUpdate(view -> TomlFormat.instance().createParser().parse(new ByteArrayInputStream(bytes), view, ParsingMode.REPLACE));
      modConfig.setConfig(new LoadedConfig(newConfig, null, modConfig), ModConfigEventsHelper.reloading());
   }

   public void loadDefaultServerConfigs() {
      this.configSets.get(ModConfig.Type.SERVER).forEach(modConfig -> {
         if (modConfig.loadedConfig != null) {
            LOGGER.warn("Overwriting non-null config {} at path {} with default server config", modConfig.loadedConfig, modConfig.getFileName());
         }

         modConfig.setConfig(new LoadedConfig(createDefaultConfig(modConfig.getSpec()), null, modConfig), ModConfigEventsHelper.loading());
      });
   }

   private static CommentedConfig createDefaultConfig(IConfigSpec spec) {
      SynchronizedConfig commentedConfig = new SynchronizedConfig(InMemoryCommentedFormat.defaultInstance(), LinkedHashMap::new);
      commentedConfig.bulkCommentedUpdate(spec::correct);
      return commentedConfig;
   }

   private static void closeConfig(ModConfig config) {
      if (config.loadedConfig != null) {
         if (config.loadedConfig.path() != null) {
            LOGGER.trace(CONFIG, "Closing config file type {} at {} for {}", new Object[]{config.getType(), config.getFileName(), config.getModId()});
            unload(config.loadedConfig.path());
            config.setConfig(null, ModConfigEventsHelper.unloading());
         } else {
            LOGGER.warn(CONFIG, "Closing non-file config {} at path {}", config.loadedConfig, config.getFileName());
         }
      }
   }

   private static void unload(Path path) {
      if (!ForgeConfigApiPortConfig.getBoolConfigValue(ModConfigValues.DISABLE_CONFIG_WATCHER)) {
         try {
            FileWatcher.defaultInstance().removeWatch(path);
         } catch (RuntimeException var2) {
            LOGGER.error("Failed to remove config {} from tracker!", path, var2);
         }
      }
   }

   private static void setupConfigFile(ModConfig modConfig, Path file) throws IOException {
      Files.createDirectories(file.getParent());
      Path p = defaultConfigPath.resolve(modConfig.getFileName());
      if (Files.exists(p)) {
         LOGGER.info(CONFIG, "Loading default config file from path {}", p);
         Files.copy(p, file);
      } else {
         writeConfig(file, createDefaultConfig(modConfig.getSpec()));
      }
   }

   private static ConcurrentCommentedConfig readConfig(Path path) throws IOException, ParsingException {
      SynchronizedConfig var3;
      try (BufferedReader reader = Files.newBufferedReader(path)) {
         SynchronizedConfig config = new SynchronizedConfig(TomlFormat.instance(), LinkedHashMap::new);
         config.bulkCommentedUpdate(view -> new TomlParser().parse(reader, view, ParsingMode.REPLACE));
         var3 = config;
      }

      return var3;
   }

   static void writeConfig(Path file, UnmodifiableCommentedConfig config) {
      new TomlWriter().write(config, file, WritingMode.REPLACE_ATOMIC);
   }

   private static void backUpConfig(Path commentedFileConfig) {
      backUpConfig(commentedFileConfig, 5);
   }

   private static void backUpConfig(Path commentedFileConfig, int maxBackups) {
      Path bakFileLocation = commentedFileConfig.getParent();
      String bakFileName = FilenameUtils.removeExtension(commentedFileConfig.getFileName().toString());
      String bakFileExtension = FilenameUtils.getExtension(commentedFileConfig.getFileName().toString()) + ".bak";
      Path bakFile = bakFileLocation.resolve(bakFileName + "-1." + bakFileExtension);

      try {
         for (int i = maxBackups; i > 0; i--) {
            Path oldBak = bakFileLocation.resolve(bakFileName + "-" + i + "." + bakFileExtension);
            if (Files.exists(oldBak)) {
               if (i >= maxBackups) {
                  Files.delete(oldBak);
               } else {
                  Files.move(oldBak, bakFileLocation.resolve(bakFileName + "-" + (i + 1) + "." + bakFileExtension));
               }
            }
         }

         Files.copy(commentedFileConfig, bakFile);
      } catch (IOException var8) {
         LOGGER.warn(CONFIG, "Failed to back up config file {}", commentedFileConfig, var8);
      }
   }
}
