package me.shedaniel.autoconfig.util.fabric;

import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

public class UtilsImpl {
   public static Path getConfigFolder() {
      return FabricLoader.getInstance().getConfigDir();
   }
}
