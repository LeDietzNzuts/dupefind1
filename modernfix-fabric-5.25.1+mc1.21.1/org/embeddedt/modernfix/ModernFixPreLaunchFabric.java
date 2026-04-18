package org.embeddedt.modernfix;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.embeddedt.modernfix.core.ModernFixMixinPlugin;
import org.embeddedt.modernfix.spark.SparkLaunchProfiler;
import org.embeddedt.modernfix.util.CommonModUtil;

public class ModernFixPreLaunchFabric implements PreLaunchEntrypoint {
   public void onPreLaunch() {
      if (ModernFixMixinPlugin.instance == null) {
         System.err.println("Mixin plugin not loaded yet");
      } else {
         if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.spark_profile_launch.OnFabric")) {
            CommonModUtil.runWithoutCrash(() -> SparkLaunchProfiler.start("launch"), "Failed to start profiler");
         }
      }
   }
}
