package vectorwing.farmersdelight.refabricated.mlconfigs;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_437;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

public abstract class ModConfigHolder {
   private static final Map<class_2960, ModConfigHolder> CONFIG_STORAGE = new ConcurrentHashMap<>();
   private final class_2960 configId;
   private final String fileName;
   private final class_2561 readableName;
   private final Path filePath;
   private final ConfigType type;
   @Nullable
   private final Runnable changeCallback;

   public static void addTrackedSpec(ModConfigHolder spec) {
      ModConfigHolder old = CONFIG_STORAGE.put(spec.getId(), spec);
      if (old != null) {
         throw new IllegalStateException("Duplicate config type for with id " + spec.getId());
      }
   }

   public static Collection<ModConfigHolder> getTrackedSpecs() {
      return CONFIG_STORAGE.values();
   }

   @Nullable
   public static ModConfigHolder getConfigSpec(class_2960 configId) {
      return CONFIG_STORAGE.get(configId);
   }

   protected ModConfigHolder(class_2960 id, String fileExtension, Path configDirectory, ConfigType type, @Nullable Runnable changeCallback) {
      this.configId = id;
      this.fileName = id.method_12836() + "-" + id.method_12832() + "." + fileExtension;
      this.filePath = configDirectory.resolve(this.fileName);
      this.type = type;
      this.changeCallback = changeCallback;
      this.readableName = class_2561.method_43470(getReadableName(id.method_36181() + "_configs"));
      addTrackedSpec(this);
   }

   public static String getReadableName(String name) {
      return Arrays.stream(name.replace(":", "_").split("_")).<CharSequence>map(StringUtils::capitalize).collect(Collectors.joining(" "));
   }

   public class_2561 getReadableName() {
      return this.readableName;
   }

   protected void onRefresh() {
      if (this.changeCallback != null) {
         this.changeCallback.run();
      }
   }

   public boolean isLoaded() {
      return true;
   }

   public abstract void forceLoad();

   public ConfigType getConfigType() {
      return this.type;
   }

   public String getModId() {
      return this.configId.method_12836();
   }

   public class_2960 getId() {
      return this.configId;
   }

   public boolean isSynced() {
      return this.type.isSynced();
   }

   public String getFileName() {
      return this.fileName;
   }

   public Path getFullPath() {
      return this.filePath;
   }

   public abstract void loadFromBytes(InputStream var1);

   @Nullable
   @Environment(EnvType.CLIENT)
   public class_437 makeScreen(class_437 parent) {
      return this.makeScreen(parent, null);
   }

   @Nullable
   @Environment(EnvType.CLIENT)
   public abstract class_437 makeScreen(class_437 var1, @Nullable class_2960 var2);

   public abstract boolean hasConfigScreen();

   public void syncConfigsToPlayer(class_3222 player) {
   }

   public void sendSyncedConfigsToAllPlayers() {
   }

   public static class ConfigLoadingException extends RuntimeException {
      public ConfigLoadingException(ModConfigHolder config, Exception cause) {
         super(
            "Failed to load config file " + config.getFileName() + " of type " + config.getConfigType() + " for mod " + config.getModId() + ". Try deleting it",
            cause
         );
      }
   }
}
