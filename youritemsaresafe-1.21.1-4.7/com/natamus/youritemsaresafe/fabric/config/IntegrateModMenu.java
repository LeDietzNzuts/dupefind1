package com.natamus.youritemsaresafe.fabric.config;

import com.natamus.collective_common_fabric.config.DuskConfig.DuskConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class IntegrateModMenu implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return parent -> DuskConfigScreen.getScreen(parent, "youritemsaresafe");
   }
}
