package fuzs.forgeconfigapiport.fabric.impl.core;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Function;

public class FileConfigWrapper extends CommentedConfigWrapper<CommentedConfig> implements FileConfig {
   private final Path path;
   private final Runnable saveConfig;

   public FileConfigWrapper(CommentedConfig config, Path path, Runnable saveConfig) {
      super(config);
      Objects.requireNonNull(path, "path is null");
      this.path = path;
      this.saveConfig = saveConfig;
   }

   public File getFile() {
      throw new UnsupportedOperationException();
   }

   public Path getNioPath() {
      return this.path;
   }

   public void save() {
      this.saveConfig.run();
   }

   public void load() {
   }

   public void close() {
   }

   public <R> R bulkRead(Function<? super UnmodifiableConfig, R> action) {
      throw new UnsupportedOperationException();
   }

   public <R> R bulkUpdate(Function<? super Config, R> action) {
      throw new UnsupportedOperationException();
   }

   public FileConfigWrapper checked() {
      return new FileConfigWrapper(super.checked(), this.path, this.saveConfig);
   }

   public FileConfigWrapper createSubConfig() {
      return new FileConfigWrapper(super.createSubConfig(), this.path, this.saveConfig);
   }
}
