package fuzs.forgeconfigapiport.fabric.impl.core;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import java.lang.reflect.Field;
import java.nio.file.Path;
import net.minecraftforge.fml.config.IConfigSpec;
import org.jetbrains.annotations.Nullable;

public record ForgeConfigSpecAdapter(IConfigSpec<?> spec) implements net.neoforged.fml.config.IConfigSpec {
   @Override
   public boolean isEmpty() {
      return this.spec.isEmpty();
   }

   @Override
   public boolean isCorrect(UnmodifiableCommentedConfig config) {
      return this.spec.isCorrect((CommentedConfig)config);
   }

   @Override
   public void correct(CommentedConfig config) {
      this.spec.correct(config);
   }

   @Override
   public void acceptConfig(@Nullable IConfigSpec.ILoadedConfig loadedConfig) {
      CommentedConfig config;
      if (loadedConfig != null) {
         Path path = getConfigPath(loadedConfig);
         if (path != null) {
            config = new FileConfigWrapper(loadedConfig.config(), path, loadedConfig::save);
         } else {
            config = loadedConfig.config();
         }
      } else {
         config = null;
      }

      this.spec.acceptConfig(config);
   }

   @Nullable
   static Path getConfigPath(net.neoforged.fml.config.IConfigSpec.ILoadedConfig loadedConfig) {
      for (Field field : loadedConfig.getClass().getDeclaredFields()) {
         if (field.getType() == Path.class) {
            field.setAccessible(true);

            try {
               return (Path)field.get(loadedConfig);
            } catch (IllegalAccessException var6) {
            }
         }
      }

      return null;
   }
}
