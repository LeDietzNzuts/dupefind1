package org.embeddedt.modernfix;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;
import net.minecraft.class_155;
import net.minecraft.class_156;
import net.minecraft.class_3218;
import net.minecraft.class_3898;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.embeddedt.modernfix.command.ModernFixCommands;
import org.embeddedt.modernfix.core.ModernFixMixinPlugin;
import org.embeddedt.modernfix.platform.ModernFixPlatformHooks;
import org.embeddedt.modernfix.resources.ReloadExecutor;
import org.embeddedt.modernfix.util.ClassInfoManager;

public class ModernFix {
   public static final Logger LOGGER = LogManager.getLogger("ModernFix");
   public static final String MODID = "modernfix";
   public static String NAME = "ModernFix";
   public static ModernFix INSTANCE;
   public static boolean runningFirstInjection = false;
   private static ExecutorService resourceReloadService = null;

   public static ExecutorService resourceReloadExecutor() {
      return resourceReloadService;
   }

   public ModernFix() {
      INSTANCE = this;
      if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.snapshot_easter_egg.NameChange") && !class_155.method_16673().method_48022()) {
         NAME = "PreemptiveFix";
      }

      ModernFixPlatformHooks.INSTANCE.onServerCommandRegister(ModernFixCommands::register);
   }

   public void onServerStarted() {
      if (ModernFixPlatformHooks.INSTANCE.isDedicatedServer()) {
         float gameStartTime = (float)ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0F;
         if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.measure_time.ServerLoad")) {
            LOGGER.warn("Dedicated server took " + gameStartTime + " seconds to load");
         }

         ModernFixPlatformHooks.INSTANCE.onLaunchComplete();
      }

      ClassInfoManager.clear();
   }

   public void onServerDead(MinecraftServer server) {
      try {
         for (class_3218 level : server.method_3738()) {
            class_3898 chunkMap = level.method_14178().field_17254;
            if (chunkMap.field_17213 != null) {
               chunkMap.field_17213.clear();
            }

            if (chunkMap.field_17220 != null) {
               chunkMap.field_17220.clear();
            }

            if (chunkMap.field_18807 != null) {
               chunkMap.field_18807.clear();
            }
         }
      } catch (RuntimeException var5) {
         LOGGER.error("Couldn't clear chunk data", var5);
      }
   }

   static {
      if (ModernFixMixinPlugin.instance.isOptionEnabled("perf.dedicated_reload_executor.ReloadExecutor")) {
         resourceReloadService = ReloadExecutor.createCustomResourceReloadExecutor();
      } else {
         resourceReloadService = class_156.method_18349();
      }
   }
}
