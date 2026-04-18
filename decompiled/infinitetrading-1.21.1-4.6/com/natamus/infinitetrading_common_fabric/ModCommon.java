package com.natamus.infinitetrading_common_fabric;

import com.natamus.infinitetrading_common_fabric.config.ConfigHandler;

public class ModCommon {
   public static void init() {
      ConfigHandler.initConfig();
      load();
   }

   private static void load() {
   }
}
