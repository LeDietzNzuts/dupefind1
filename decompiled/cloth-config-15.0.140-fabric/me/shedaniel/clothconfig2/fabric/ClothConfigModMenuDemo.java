package me.shedaniel.clothconfig2.fabric;

import com.mojang.blaze3d.systems.RenderSystem;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.example.ExampleConfig;
import me.shedaniel.clothconfig2.ClothConfigDemo;
import net.minecraft.class_437;

public class ClothConfigModMenuDemo implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return screen -> RenderSystem.isOnRenderThread() && class_437.method_25442()
         ? AutoConfig.getConfigScreen(ExampleConfig.class, screen).get()
         : ClothConfigDemo.getConfigBuilderWithDemo().setParentScreen(screen).build();
   }
}
