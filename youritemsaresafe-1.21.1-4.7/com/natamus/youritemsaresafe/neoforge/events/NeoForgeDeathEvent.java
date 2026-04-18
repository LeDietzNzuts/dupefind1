package com.natamus.youritemsaresafe.neoforge.events;

import com.natamus.youritemsaresafe_common_neoforge.events.DeathEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

public class NeoForgeDeathEvent {
   @SubscribeEvent
   public static void onPlayerDeath(LivingDeathEvent e) {
      Entity entity = e.getEntity();
      if (!entity.level().isClientSide) {
         if (entity instanceof Player) {
            DeathEvent.onPlayerDeath((ServerPlayer)entity, e.getSource(), 0.0F);
         }
      }
   }
}
