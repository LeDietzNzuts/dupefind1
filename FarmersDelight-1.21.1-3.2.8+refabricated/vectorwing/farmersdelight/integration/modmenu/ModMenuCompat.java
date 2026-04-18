package vectorwing.farmersdelight.integration.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.class_2561;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.FabricConfigListScreen;

public class ModMenuCompat implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return parent -> new FabricConfigListScreen(
         "farmersdelight",
         ModItems.STOVE.get().method_7854(),
         class_2561.method_43471("farmersdelight"),
         null,
         parent,
         Configuration.CLIENT_CONFIG,
         Configuration.COMMON_CONFIG
      );
   }
}
