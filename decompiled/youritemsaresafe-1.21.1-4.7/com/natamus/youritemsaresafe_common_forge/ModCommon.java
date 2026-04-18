package com.natamus.youritemsaresafe_common_forge;

import com.natamus.collective_common_forge.features.PlayerHeadCacheFeature;
import com.natamus.collective_common_forge.services.Services;
import com.natamus.youritemsaresafe_common_forge.config.ConfigHandler;
import com.natamus.youritemsaresafe_common_forge.data.Constants;

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
