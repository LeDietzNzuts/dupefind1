package com.natamus.infinitetrading_common_fabric.events;

import com.natamus.collective_common_fabric.functions.EntityFunctions;
import com.natamus.infinitetrading_common_fabric.config.ConfigHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_3966;
import net.minecraft.class_3989;

public class VillagerEvent {
   public static class_1269 onVillagerClick(class_1657 player, class_1937 level, class_1268 hand, class_1297 target, class_3966 hitResult) {
      if (level.field_9236) {
         return class_1269.field_5811;
      } else {
         if (target instanceof class_1646) {
            if (ConfigHandler.villagerInfiniteTrades) {
               class_1646 villager = (class_1646)target;
               EntityFunctions.resetMerchantOffers(villager);
            }
         } else if (target instanceof class_3989 && ConfigHandler.wanderingTraderInfiniteTrades) {
            class_3989 wanderer = (class_3989)target;
            EntityFunctions.resetMerchantOffers(wanderer);
         }

         return class_1269.field_5811;
      }
   }
}
