package com.natamus.infinitetrading_common_forge;

import com.natamus.infinitetrading_common_forge.config.ConfigHandler;

public class ModCommon {
   public static void init() {
      ConfigHandler.initConfig();
      load();
   }

   private static void load() {
   }
}
