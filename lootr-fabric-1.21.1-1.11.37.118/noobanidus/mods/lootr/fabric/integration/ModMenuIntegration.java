package noobanidus.mods.lootr.fabric.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_437;
import noobanidus.mods.lootr.fabric.config.ConfigManager;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return parent -> (class_437)AutoConfig.getConfigScreen(ConfigManager.class, parent).get();
   }
}
