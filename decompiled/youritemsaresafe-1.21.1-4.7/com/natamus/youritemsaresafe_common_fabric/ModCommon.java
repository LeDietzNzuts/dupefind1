package com.natamus.youritemsaresafe_common_fabric;

import com.natamus.collective_common_fabric.features.PlayerHeadCacheFeature;
import com.natamus.collective_common_fabric.services.Services;
import com.natamus.youritemsaresafe_common_fabric.config.ConfigHandler;
import com.natamus.youritemsaresafe_common_fabric.data.Constants;

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
