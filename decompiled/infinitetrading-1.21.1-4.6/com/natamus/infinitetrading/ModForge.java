package com.natamus.infinitetrading;

import com.natamus.collective_common_forge.check.RegisterMod;
import com.natamus.collective_common_forge.check.ShouldLoadCheck;
import com.natamus.infinitetrading.forge.config.IntegrateForgeConfig;
import com.natamus.infinitetrading.forge.events.ForgeVillagerEvent;
import com.natamus.infinitetrading_common_forge.ModCommon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("infinitetrading")
public class ModForge {
   public ModForge() {
      if (ShouldLoadCheck.shouldLoad("infinitetrading")) {
         IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
         modEventBus.addListener(this::loadComplete);
         setGlobalConstants();
         ModCommon.init();
         IntegrateForgeConfig.registerScreen(ModLoadingContext.get());
         RegisterMod.register("Infinite Trading", "infinitetrading", "4.6", "[1.21.1]");
      }
   }

   private void loadComplete(FMLLoadCompleteEvent event) {
      MinecraftForge.EVENT_BUS.register(ForgeVillagerEvent.class);
   }

   private static void setGlobalConstants() {
   }
}
