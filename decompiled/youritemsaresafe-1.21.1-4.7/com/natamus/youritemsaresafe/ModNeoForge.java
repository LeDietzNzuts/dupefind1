package com.natamus.youritemsaresafe;

import com.natamus.collective_common_neoforge.check.RegisterMod;
import com.natamus.collective_common_neoforge.check.ShouldLoadCheck;
import com.natamus.youritemsaresafe.neoforge.config.IntegrateNeoForgeConfig;
import com.natamus.youritemsaresafe.neoforge.events.NeoForgeDeathEvent;
import com.natamus.youritemsaresafe_common_neoforge.ModCommon;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod("youritemsaresafe")
public class ModNeoForge {
   public ModNeoForge(IEventBus modEventBus) {
      if (ShouldLoadCheck.shouldLoad("youritemsaresafe")) {
         modEventBus.addListener(this::loadComplete);
         ModCommon.init();
         IntegrateNeoForgeConfig.registerScreen(ModLoadingContext.get());
         RegisterMod.register("Your Items Are Safe", "youritemsaresafe", "4.7", "[1.21.1]");
      }
   }

   private void loadComplete(FMLLoadCompleteEvent event) {
      NeoForge.EVENT_BUS.register(NeoForgeDeathEvent.class);
   }
}
