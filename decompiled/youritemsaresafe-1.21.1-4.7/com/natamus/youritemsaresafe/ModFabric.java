package com.natamus.youritemsaresafe;

import com.natamus.collective_common_fabric.check.RegisterMod;
import com.natamus.collective_common_fabric.check.ShouldLoadCheck;
import com.natamus.youritemsaresafe_common_fabric.ModCommon;
import com.natamus.youritemsaresafe_common_fabric.events.DeathEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents.AllowDeath;
import net.minecraft.class_3222;

public class ModFabric implements ModInitializer {
   public void onInitialize() {
      if (ShouldLoadCheck.shouldLoad("youritemsaresafe")) {
         ModCommon.init();
         this.loadEvents();
         RegisterMod.register("Your Items Are Safe", "youritemsaresafe", "4.7", "[1.21.1]");
      }
   }

   private void loadEvents() {
      ServerLivingEntityEvents.ALLOW_DEATH.register((AllowDeath)(livingEntity, damageSource, damageAmount) -> {
         if (livingEntity instanceof class_3222) {
            DeathEvent.onPlayerDeath((class_3222)livingEntity, damageSource, damageAmount);
         }

         return true;
      });
   }
}
