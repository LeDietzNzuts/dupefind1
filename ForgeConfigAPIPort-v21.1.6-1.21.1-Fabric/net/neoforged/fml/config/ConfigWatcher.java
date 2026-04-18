package net.neoforged.fml.config;

import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.fabric.impl.core.ModConfigEventsHelper;
import java.nio.file.Path;
import org.slf4j.Logger;

class ConfigWatcher implements Runnable {
   private static final Logger LOGGER = LogUtils.getLogger();
   private final ModConfig modConfig;
   private final Path path;
   private final ClassLoader realClassLoader;

   ConfigWatcher(ModConfig modConfig, Path path, ClassLoader classLoader) {
      this.modConfig = modConfig;
      this.path = path;
      this.realClassLoader = classLoader;
   }

   @Override
   public void run() {
      ClassLoader previousLoader = Thread.currentThread().getContextClassLoader();
      Thread.currentThread().setContextClassLoader(this.realClassLoader);

      try {
         this.modConfig.lock.lock();

         try {
            LOGGER.debug(ConfigTracker.CONFIG, "Config file {} changed, re-loading", this.modConfig.getFileName());
            ConfigTracker.loadConfig(this.modConfig, this.path, ModConfigEventsHelper.reloading());
         } finally {
            this.modConfig.lock.unlock();
         }
      } finally {
         Thread.currentThread().setContextClassLoader(previousLoader);
      }
   }
}
