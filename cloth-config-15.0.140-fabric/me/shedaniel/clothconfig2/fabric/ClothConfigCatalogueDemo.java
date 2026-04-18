package me.shedaniel.clothconfig2.fabric;

import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.example.ExampleConfig;
import me.shedaniel.clothconfig2.ClothConfigDemo;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.class_437;

public class ClothConfigCatalogueDemo {
   public static class_437 createConfigScreen(class_437 currentScreen, ModContainer container) {
      return RenderSystem.isOnRenderThread() && class_437.method_25442()
         ? AutoConfig.getConfigScreen(ExampleConfig.class, currentScreen).get()
         : ClothConfigDemo.getConfigBuilderWithDemo().setParentScreen(currentScreen).build();
   }
}
