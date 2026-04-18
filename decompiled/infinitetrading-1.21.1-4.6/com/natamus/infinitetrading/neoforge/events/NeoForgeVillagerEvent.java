package com.natamus.infinitetrading.neoforge.events;

import com.natamus.infinitetrading_common_neoforge.events.VillagerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteract;

public class NeoForgeVillagerEvent {
   @SubscribeEvent
   public static void onVillagerClick(EntityInteract e) {
      VillagerEvent.onVillagerClick(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null);
   }
}
