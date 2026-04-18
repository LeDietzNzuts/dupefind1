package com.natamus.infinitetrading.forge.events;

import com.natamus.infinitetrading_common_forge.events.VillagerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeVillagerEvent {
   @SubscribeEvent
   public static void onVillagerClick(EntityInteract e) {
      VillagerEvent.onVillagerClick(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null);
   }
}
