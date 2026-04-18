package net.raphimc.immediatelyfast.fabric;

import java.nio.file.Path;
import java.util.Optional;
import net.fabricmc.loader.api.FabricLoader;

public class PlatformCodeImpl {
   public static Path getConfigDirectory() {
      return FabricLoader.getInstance().getConfigDir();
   }

   public static Optional<String> getModVersion(String mod) {
      return FabricLoader.getInstance().getModContainer(mod).map(m -> m.getMetadata().getVersion().getFriendlyString());
   }

   public static void checkModCompatibility() {
   }
}
