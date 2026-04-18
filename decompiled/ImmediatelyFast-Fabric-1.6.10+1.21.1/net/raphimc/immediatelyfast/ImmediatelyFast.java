package net.raphimc.immediatelyfast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Objects;
import net.minecraft.class_310;
import net.minecraft.class_3304;
import net.raphimc.immediatelyfast.apiimpl.ApiAccessImpl;
import net.raphimc.immediatelyfast.compat.IrisCompat;
import net.raphimc.immediatelyfast.feature.core.ImmediatelyFastConfig;
import net.raphimc.immediatelyfast.feature.core.ImmediatelyFastRuntimeConfig;
import net.raphimc.immediatelyfast.feature.sign_text_buffering.SignTextCache;
import net.raphimc.immediatelyfastapi.ImmediatelyFastApi;
import org.lwjgl.opengl.GL11C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImmediatelyFast {
   public static final Logger LOGGER = LoggerFactory.getLogger("ImmediatelyFast");
   public static String VERSION;
   public static ImmediatelyFastConfig config;
   public static ImmediatelyFastRuntimeConfig runtimeConfig;
   public static SignTextCache signTextCache;

   public static void earlyInit() {
      if (config == null) {
         loadConfig();
         if (config.experimental_screen_batching && !config.hud_batching) {
            LOGGER.warn("Screen Batching is enabled but HUD Batching is disabled. Disabling Screen Batching.");
            config.experimental_screen_batching = false;
         }

         if (!config.debug_only_and_not_recommended_disable_mod_conflict_handling
            && config.experimental_sign_text_buffering
            && PlatformCode.getModVersion("effective").isPresent()) {
            LOGGER.warn("Effective mod detected. Force disabling sign text buffering optimization.");
            config.experimental_sign_text_buffering = false;
         }

         createRuntimeConfig();
         ImmediatelyFastApi.setApiImpl(new ApiAccessImpl());
         VERSION = PlatformCode.getModVersion("immediatelyfast").orElseThrow(NullPointerException::new);
         PlatformCode.checkModCompatibility();
      }
   }

   public static void windowInit() {
      String gpuVendor = GL11C.glGetString(7936);
      String gpuModel = GL11C.glGetString(7937);
      String glVersion = GL11C.glGetString(7938);
      LOGGER.info("Initializing ImmediatelyFast " + VERSION + " on " + gpuModel + " (" + gpuVendor + ") with OpenGL " + glVersion);
      boolean isNvidia = false;
      boolean isAmd = false;
      boolean isIntel = false;
      boolean isApple = false;
      if (gpuVendor != null) {
         String gpuVendorLower = gpuVendor.toLowerCase();
         isNvidia = gpuVendorLower.startsWith("nvidia");
         if (!gpuVendorLower.startsWith("ati") && !gpuVendorLower.startsWith("amd")) {
            boolean var10 = false;
         } else {
            boolean var10000 = true;
         }

         isIntel = gpuVendorLower.startsWith("intel");
         isApple = gpuVendorLower.startsWith("apple");
      }

      Objects.requireNonNull(config, "Config not loaded yet");
      Objects.requireNonNull(runtimeConfig, "Runtime config not created yet");
      if (config.fast_buffer_upload && isApple && !config.debug_only_and_not_recommended_disable_hardware_conflict_handling) {
         LOGGER.warn("Apple GPU detected. Disabling fast buffer upload.");
         runtimeConfig.fast_buffer_upload = false;
      }

      if (!config.debug_only_and_not_recommended_disable_mod_conflict_handling) {
         PlatformCode.getModVersion("iris").ifPresent(version -> {
            LOGGER.info("Found Iris " + version + ". Enabling compatibility.");
            IrisCompat.init();
         });
      }
   }

   public static void lateInit() {
      if (config.experimental_sign_text_buffering) {
         signTextCache = new SignTextCache();
         ((class_3304)class_310.method_1551().method_1478()).method_14477(signTextCache);
      }
   }

   public static void onWorldJoin() {
      if (signTextCache != null) {
         signTextCache.clearCache();
      }
   }

   public static void loadConfig() {
      File configFile = PlatformCode.getConfigDirectory().resolve("immediatelyfast.json").toFile();
      if (configFile.exists()) {
         try {
            config = (ImmediatelyFastConfig)new Gson().fromJson(new FileReader(configFile), ImmediatelyFastConfig.class);
         } catch (Throwable var3) {
            LOGGER.error("Failed to load ImmediatelyFast config. Resetting it.", var3);
         }
      }

      if (config == null) {
         config = new ImmediatelyFastConfig();
      }

      try {
         Files.writeString(configFile.toPath(), new GsonBuilder().setPrettyPrinting().create().toJson(config));
      } catch (Throwable var2) {
         LOGGER.error("Failed to save ImmediatelyFast config.", var2);
      }
   }

   public static void createRuntimeConfig() {
      runtimeConfig = new ImmediatelyFastRuntimeConfig(config);
   }
}
