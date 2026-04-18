package io.github.suel_ki.beautify.util;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.class_437;

public class ModMenuConfig implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return parent -> (class_437)AutoConfig.getConfigScreen(BeautifyConfig.class, parent).get();
   }
}
