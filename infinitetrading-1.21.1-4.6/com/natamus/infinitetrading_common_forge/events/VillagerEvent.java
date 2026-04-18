package com.natamus.infinitetrading_common_forge.events;

import com.natamus.collective_common_forge.functions.EntityFunctions;
import com.natamus.infinitetrading_common_forge.config.ConfigHandler;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class VillagerEvent {
   public static InteractionResult onVillagerClick(Player player, Level level, InteractionHand hand, Entity target, EntityHitResult hitResult) {
      if (level.isClientSide) {
         return InteractionResult.PASS;
      } else {
         if (target instanceof Villager) {
            if (ConfigHandler.villagerInfiniteTrades) {
               Villager villager = (Villager)target;
               EntityFunctions.resetMerchantOffers(villager);
            }
         } else if (target instanceof WanderingTrader && ConfigHandler.wanderingTraderInfiniteTrades) {
            WanderingTrader wanderer = (WanderingTrader)target;
            EntityFunctions.resetMerchantOffers(wanderer);
         }

         return InteractionResult.PASS;
      }
   }
}
