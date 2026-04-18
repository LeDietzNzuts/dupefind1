package com.magistuarmory.fabric.config;

import com.magistuarmory.config.ModConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_437;

@Environment(EnvType.CLIENT)
public class ModMenuFabric implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return parent -> (class_437)AutoConfig.getConfigScreen(ModConfig.class, parent).get();
   }
}
