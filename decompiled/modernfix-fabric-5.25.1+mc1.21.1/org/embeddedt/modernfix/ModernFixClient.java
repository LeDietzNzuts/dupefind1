package org.embeddedt.modernfix;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_2940;
import net.minecraft.class_310;
import net.minecraft.class_6416;
import net.minecraft.server.MinecraftServer;
import org.embeddedt.modernfix.api.entrypoint.ModernFixClientIntegration;
import org.embeddedt.modernfix.core.ModernFixMixinPlugin;
import org.embeddedt.modernfix.platform.ModernFixPlatformHooks;
import org.embeddedt.modernfix.spark.SparkLaunchProfiler;
import org.embeddedt.modernfix.util.ClassInfoManager;
import org.embeddedt.modernfix.world.IntegratedWatchdog;

public class ModernFixClient {
   public static ModernFixClient INSTANCE;
   public static long worldLoadStartTime = -1L;
   private static int numRenderTicks;
   public static float gameStartTimeSeconds = -1.0F;
   public static boolean recipesUpdated;
   public static boolean tagsUpdated = false;
   public String brandingString = null;
   public static List<ModernFixClientIntegration> CLIENT_INTEGRATIONS = new CopyOnWriteArrayList<>();

   public ModernFixClient() {
      INSTANCE = this;
      class_6416.method_37415();
      if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.branding.F3Screen")) {
         this.brandingString = ModernFix.NAME + " " + ModernFixPlatformHooks.INSTANCE.getVersionString();
      }

      for (String className : ModernFixPlatformHooks.INSTANCE.getCustomModOptions().get("client_entrypoint")) {
         try {
            CLIENT_INTEGRATIONS.add((ModernFixClientIntegration)Class.forName(className).getDeclaredConstructor().newInstance());
         } catch (ClassCastException | ReflectiveOperationException var4) {
            ModernFix.LOGGER.error("Could not instantiate integration {}", className, var4);
         }
      }

      if (ModernFixMixinPlugin.instance.isOptionEnabled("perf.dynamic_resources.FireIntegrationHook")) {
         for (ModernFixClientIntegration integration : CLIENT_INTEGRATIONS) {
            integration.onDynamicResourcesStatusChange(true);
         }
      }
   }

   public void resetWorldLoadStateMachine() {
      numRenderTicks = 0;
      worldLoadStartTime = -1L;
      recipesUpdated = false;
      tagsUpdated = false;
   }

   public void onGameLaunchFinish() {
      if (!(gameStartTimeSeconds >= 0.0F)) {
         gameStartTimeSeconds = (float)ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0F;
         if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.measure_time.GameLoad")) {
            ModernFix.LOGGER.warn("Game took " + gameStartTimeSeconds + " seconds to start");
         }

         ModernFixPlatformHooks.INSTANCE.onLaunchComplete();
         ClassInfoManager.clear();
      }
   }

   public void onRecipesUpdated() {
      recipesUpdated = true;
   }

   public void onTagsUpdated() {
      tagsUpdated = true;
   }

   public void onRenderTickEnd() {
      if (recipesUpdated && tagsUpdated && worldLoadStartTime != -1L && class_310.method_1551().field_1724 != null && numRenderTicks++ >= 10) {
         float timeSpentLoading = (float)(System.nanoTime() - worldLoadStartTime) / 1.0E9F;
         if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.measure_time.WorldLoad")) {
            ModernFix.LOGGER.warn("Time from main menu to in-game was " + timeSpentLoading + " seconds");
            ModernFix.LOGGER.warn("Total time to load game and open world was " + (timeSpentLoading + gameStartTimeSeconds) + " seconds");
         }

         if (ModernFixPlatformHooks.INSTANCE.modPresent("spark") && ModernFixMixinPlugin.instance.isOptionEnabled("feature.spark_profile_world_join.WorldJoin")
            )
          {
            SparkLaunchProfiler.stop("world_join");
         }

         this.resetWorldLoadStateMachine();
      }
   }

   private static boolean compareAndSwitchIds(Class<? extends class_1297> eClass, String fieldName, class_2940<?> accessor, int newId) {
      if (accessor.comp_2327 != newId) {
         ModernFix.LOGGER.warn("Corrected ID mismatch on {} field {}. Client had {} but server wants {}.", eClass, fieldName, accessor.comp_2327, newId);
         accessor.comp_2327 = newId;
         return true;
      } else {
         ModernFix.LOGGER.debug("{} {} ID fine: {}", eClass, fieldName, newId);
         return false;
      }
   }

   public void onServerStarted(MinecraftServer server) {
      if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.integrated_server_watchdog.IntegratedWatchdog")) {
         IntegratedWatchdog watchdog = new IntegratedWatchdog(server);
         watchdog.start();
      }
   }
}
