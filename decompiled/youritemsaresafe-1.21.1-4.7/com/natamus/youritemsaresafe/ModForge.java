package com.natamus.youritemsaresafe;

import com.natamus.collective_common_forge.check.RegisterMod;
import com.natamus.collective_common_forge.check.ShouldLoadCheck;
import com.natamus.youritemsaresafe.forge.config.IntegrateForgeConfig;
import com.natamus.youritemsaresafe.forge.events.ForgeDeathEvent;
import com.natamus.youritemsaresafe_common_forge.ModCommon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("youritemsaresafe")
public class ModForge {
   public ModForge() {
      if (ShouldLoadCheck.shouldLoad("youritemsaresafe")) {
         IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
         modEventBus.addListener(this::loadComplete);
         ModCommon.init();
         IntegrateForgeConfig.registerScreen(ModLoadingContext.get());
         RegisterMod.register("Your Items Are Safe", "youritemsaresafe", "4.7", "[1.21.1]");
      }
   }

   private void loadComplete(FMLLoadCompleteEvent event) {
      MinecraftForge.EVENT_BUS.register(ForgeDeathEvent.class);
   }
}
