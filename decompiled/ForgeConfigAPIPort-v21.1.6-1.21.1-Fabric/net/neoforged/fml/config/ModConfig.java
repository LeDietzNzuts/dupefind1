package net.neoforged.fml.config;

import fuzs.forgeconfigapiport.fabric.impl.core.ForgeConfigRegistryImpl;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_3542;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public final class ModConfig {
   private final ModConfig.Type type;
   private final IConfigSpec spec;
   private final String fileName;
   private final String modId;
   @Nullable
   LoadedConfig loadedConfig;
   final Lock lock;
   @Internal
   @Nullable
   public final ModConfig modConfig;

   ModConfig(ModConfig.Type type, IConfigSpec spec, String modId, String fileName, ReentrantLock lock) {
      this.type = type;
      this.spec = spec;
      this.fileName = fileName;
      if (!FabricLoader.getInstance().isModLoaded(modId)) {
         throw new IllegalArgumentException("No mod with id '%s'".formatted(modId));
      } else {
         this.modId = modId;
         this.lock = lock;
         this.modConfig = ForgeConfigRegistryImpl.adapt(this);
      }
   }

   public ModConfig.Type getType() {
      return this.type;
   }

   public String getFileName() {
      return this.fileName;
   }

   public IConfigSpec getSpec() {
      return this.spec;
   }

   public String getModId() {
      return this.modId;
   }

   @Nullable
   public IConfigSpec.ILoadedConfig getLoadedConfig() {
      return this.loadedConfig;
   }

   public Path getFullPath() {
      if (this.loadedConfig != null && this.loadedConfig.path() != null) {
         return this.loadedConfig.path();
      } else {
         throw new IllegalStateException("Cannot call getFullPath() on non-file config " + this.loadedConfig + " at path " + this.getFileName());
      }
   }

   void setConfig(@Nullable LoadedConfig loadedConfig, Consumer<ModConfig> eventConstructor) {
      this.lock.lock();

      try {
         this.loadedConfig = loadedConfig;
         this.spec.acceptConfig(loadedConfig);
         if (this.modConfig != null) {
            this.modConfig.loadedConfig = loadedConfig;
         }

         eventConstructor.accept(this);
      } finally {
         this.lock.unlock();
      }
   }

   public static enum Type implements class_3542 {
      COMMON,
      CLIENT,
      SERVER,
      STARTUP;

      public String extension() {
         return this.name().toLowerCase(Locale.ROOT);
      }

      public String method_15434() {
         return this.extension();
      }
   }
}
