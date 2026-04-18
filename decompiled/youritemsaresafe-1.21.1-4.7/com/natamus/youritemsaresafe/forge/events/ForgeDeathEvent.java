package com.natamus.youritemsaresafe.forge.events;

import com.natamus.youritemsaresafe_common_forge.events.DeathEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeDeathEvent {
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
