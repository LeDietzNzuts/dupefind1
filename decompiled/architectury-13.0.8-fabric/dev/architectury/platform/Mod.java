package dev.architectury.platform;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;

public interface Mod {
   String getModId();

   String getVersion();

   String getName();

   String getDescription();

   Optional<String> getLogoFile(int var1);

   List<Path> getFilePaths();

   @Deprecated(forRemoval = true)
   Path getFilePath();

   Optional<Path> findResource(String... var1);

   Collection<String> getAuthors();

   @Nullable
   Collection<String> getLicense();

   Optional<String> getHomepage();

   Optional<String> getSources();

   Optional<String> getIssueTracker();

   @Environment(EnvType.CLIENT)
   void registerConfigurationScreen(Mod.ConfigurationScreenProvider var1);

   @FunctionalInterface
   @Environment(EnvType.CLIENT)
   public interface ConfigurationScreenProvider {
      class_437 provide(class_437 var1);
   }
}
