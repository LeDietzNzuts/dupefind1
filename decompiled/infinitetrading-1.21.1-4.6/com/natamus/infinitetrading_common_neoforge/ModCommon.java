package com.natamus.infinitetrading_common_neoforge;

import com.natamus.infinitetrading_common_neoforge.config.ConfigHandler;

public class ModCommon {
   public static void init() {
      ConfigHandler.initConfig();
      load();
   }

   private static void load() {
   }
}
