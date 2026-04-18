package net.minecraftforge.fml.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;
import net.minecraft.class_3542;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Deprecated(forRemoval = true)
public class ModConfig {
   private final ModConfig.Type type;
   private final IConfigSpec<?> spec;
   private final String fileName;
   private final String modId;
   @Internal
   @Nullable
   public IConfigSpec.ILoadedConfig loadedConfig;
   @Nullable
   private final ModConfig modConfig;

   @Internal
   public ModConfig(ModConfig.Type type, IConfigSpec<?> spec, String modId, String fileName, net.neoforged.fml.config.ModConfig modConfig) {
      this.type = type;
      this.spec = spec;
      this.fileName = fileName;
      this.modId = modId;
      this.modConfig = modConfig;
   }

   public ModConfig(ModConfig.Type type, IConfigSpec<?> spec, String modId, String fileName) {
      this(type, spec, modId, fileName, null);
   }

   public ModConfig(ModConfig.Type type, IConfigSpec<?> spec, String modId) {
      this(type, spec, modId, defaultConfigName(type, modId));
   }

   static String defaultConfigName(ModConfig.Type type, String modId) {
      return String.format("%s-%s.toml", modId, type.extension());
   }

   public ModConfig.Type getType() {
      return this.type;
   }

   public String getFileName() {
      return this.fileName;
   }

   public <T extends IConfigSpec<T>> IConfigSpec<T> getSpec() {
      return (IConfigSpec<T>)this.spec;
   }

   public String getModId() {
      return this.modId;
   }

   public CommentedConfig getConfigData() {
      return this.loadedConfig != null ? this.loadedConfig.config() : null;
   }

   void setConfigData(CommentedConfig configData) {
      throw new UnsupportedOperationException();
   }

   public void save() {
      Objects.requireNonNull(this.loadedConfig, "loaded config is null");
      this.loadedConfig.save();
   }

   public Path getFullPath() {
      Objects.requireNonNull(this.modConfig, "mod config is null");
      return this.modConfig.getFullPath();
   }

   public void acceptSyncedConfig(byte[] bytes) {
      throw new UnsupportedOperationException();
   }

   @Deprecated(forRemoval = true)
   public static enum Type implements class_3542 {
      COMMON,
      CLIENT,
      SERVER;

      public String extension() {
         return this.name().toLowerCase(Locale.ROOT);
      }

      public String method_15434() {
         return this.extension();
      }
   }
}
