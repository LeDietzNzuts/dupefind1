package com.natamus.infinitetrading;

import com.natamus.collective_common_neoforge.check.RegisterMod;
import com.natamus.collective_common_neoforge.check.ShouldLoadCheck;
import com.natamus.infinitetrading.neoforge.config.IntegrateNeoForgeConfig;
import com.natamus.infinitetrading.neoforge.events.NeoForgeVillagerEvent;
import com.natamus.infinitetrading_common_neoforge.ModCommon;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod("infinitetrading")
public class ModNeoForge {
   public ModNeoForge(IEventBus modEventBus) {
      if (ShouldLoadCheck.shouldLoad("infinitetrading")) {
         modEventBus.addListener(this::loadComplete);
         setGlobalConstants();
         ModCommon.init();
         IntegrateNeoForgeConfig.registerScreen(ModLoadingContext.get());
         RegisterMod.register("Infinite Trading", "infinitetrading", "4.6", "[1.21.1]");
      }
   }

   private void loadComplete(FMLLoadCompleteEvent event) {
      NeoForge.EVENT_BUS.register(NeoForgeVillagerEvent.class);
   }

   private static void setGlobalConstants() {
   }
}
