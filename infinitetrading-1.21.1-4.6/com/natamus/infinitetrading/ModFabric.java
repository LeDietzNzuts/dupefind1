package com.natamus.infinitetrading;

import com.natamus.collective_common_fabric.check.RegisterMod;
import com.natamus.collective_common_fabric.check.ShouldLoadCheck;
import com.natamus.infinitetrading_common_fabric.ModCommon;
import com.natamus.infinitetrading_common_fabric.events.VillagerEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;

public class ModFabric implements ModInitializer {
   public void onInitialize() {
      if (ShouldLoadCheck.shouldLoad("infinitetrading")) {
         setGlobalConstants();
         ModCommon.init();
         this.loadEvents();
         RegisterMod.register("Infinite Trading", "infinitetrading", "4.6", "[1.21.1]");
      }
   }

   private void loadEvents() {
      UseEntityCallback.EVENT
         .register((UseEntityCallback)(player, world, hand, entity, hitResult) -> VillagerEvent.onVillagerClick(player, world, hand, entity, hitResult));
   }

   private static void setGlobalConstants() {
   }
}
