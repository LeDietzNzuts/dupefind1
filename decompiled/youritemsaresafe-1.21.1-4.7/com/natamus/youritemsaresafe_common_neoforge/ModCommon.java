package com.natamus.youritemsaresafe_common_neoforge;

import com.natamus.collective_common_neoforge.features.PlayerHeadCacheFeature;
import com.natamus.collective_common_neoforge.services.Services;
import com.natamus.youritemsaresafe_common_neoforge.config.ConfigHandler;
import com.natamus.youritemsaresafe_common_neoforge.data.Constants;

public class ModCommon {
   public static void init() {
      ConfigHandler.initConfig();
      load();
   }

   private static void load() {
      PlayerHeadCacheFeature.enableHeadCaching();
      Constants.inventoryTotemModIsLoaded = Services.MODLOADER.isModLoaded("inventory-totem");
   }
}
