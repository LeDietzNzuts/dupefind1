package dev.architectury.platform.fabric;

import dev.architectury.platform.Mod;
import dev.architectury.utils.Env;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import org.jetbrains.annotations.Nullable;

public class PlatformImpl {
   public static final Map<String, Mod.ConfigurationScreenProvider> CONFIG_SCREENS = new ConcurrentHashMap<>();
   private static final Map<String, Mod> mods = new ConcurrentHashMap<>();

   public static Path getGameFolder() {
      return FabricLoader.getInstance().getGameDir().toAbsolutePath().normalize();
   }

   public static Path getConfigFolder() {
      return FabricLoader.getInstance().getConfigDir().toAbsolutePath().normalize();
   }

   public static Path getModsFolder() {
      return getGameFolder().resolve("mods");
   }

   public static Env getEnvironment() {
      return Env.fromPlatform(getEnv());
   }

   public static EnvType getEnv() {
      return FabricLoader.getInstance().getEnvironmentType();
   }

   public static boolean isModLoaded(String id) {
      return FabricLoader.getInstance().isModLoaded(id);
   }

   public static Mod getMod(String id) {
      return mods.computeIfAbsent(id, PlatformImpl.ModImpl::new);
   }

   public static Collection<Mod> getMods() {
      for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
         getMod(mod.getMetadata().getId());
      }

      return mods.values();
   }

   public static Collection<String> getModIds() {
      return FabricLoader.getInstance().getAllMods().stream().map(ModContainer::getMetadata).<String>map(ModMetadata::getId).collect(Collectors.toList());
   }

   public static boolean isDevelopmentEnvironment() {
      return FabricLoader.getInstance().isDevelopmentEnvironment();
   }

   private static class ModImpl implements Mod {
      private final ModContainer container;
      private final ModMetadata metadata;

      public ModImpl(String id) {
         this.container = (ModContainer)FabricLoader.getInstance().getModContainer(id).orElseThrow();
         this.metadata = this.container.getMetadata();
      }

      @Override
      public String getModId() {
         return this.metadata.getId();
      }

      @Override
      public String getVersion() {
         return this.metadata.getVersion().getFriendlyString();
      }

      @Override
      public String getName() {
         return this.metadata.getName();
      }

      @Override
      public String getDescription() {
         return this.metadata.getDescription();
      }

      @Override
      public Optional<String> getLogoFile(int preferredSize) {
         return this.metadata.getIconPath(preferredSize);
      }

      @Override
      public List<Path> getFilePaths() {
         return this.container.getRootPaths();
      }

      @Override
      public Path getFilePath() {
         return this.container.getRootPath();
      }

      @Override
      public Optional<Path> findResource(String... path) {
         return this.container.findPath(String.join("/", path));
      }

      @Override
      public Collection<String> getAuthors() {
         return this.metadata.getAuthors().stream().<String>map(Person::getName).collect(Collectors.toList());
      }

      @Nullable
      @Override
      public Collection<String> getLicense() {
         return this.metadata.getLicense();
      }

      @Override
      public Optional<String> getHomepage() {
         return this.metadata.getContact().get("homepage");
      }

      @Override
      public Optional<String> getSources() {
         return this.metadata.getContact().get("issues");
      }

      @Override
      public Optional<String> getIssueTracker() {
         return this.metadata.getContact().get("sources");
      }

      @Override
      public void registerConfigurationScreen(Mod.ConfigurationScreenProvider provider) {
         if (PlatformImpl.CONFIG_SCREENS.containsKey(this.getModId())) {
            throw new IllegalStateException("Can not register configuration screen for mod '" + this.getModId() + "' because it was already registered!");
         } else {
            PlatformImpl.CONFIG_SCREENS.put(this.getModId(), provider);
         }
      }
   }
}
