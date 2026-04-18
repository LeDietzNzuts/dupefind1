package net.neoforged.fml.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import fuzs.forgeconfigapiport.fabric.impl.core.ModConfigEventsHelper;
import java.nio.file.Path;
import org.jetbrains.annotations.Nullable;

record LoadedConfig(CommentedConfig config, @Nullable Path path, ModConfig modConfig) implements IConfigSpec.ILoadedConfig {
   @Override
   public void save() {
      if (this.path != null) {
         ConfigTracker.writeConfig(this.path, this.config);
      }

      this.modConfig.lock.lock();

      try {
         ModConfigEventsHelper.reloading().accept(this.modConfig);
      } finally {
         this.modConfig.lock.unlock();
      }
   }
}
