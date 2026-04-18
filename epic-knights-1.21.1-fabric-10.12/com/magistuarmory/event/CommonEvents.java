package com.magistuarmory.event;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.item.MedievalShieldItem;
import com.magistuarmory.item.MedievalWeaponItem;
import com.magistuarmory.misc.ModLoot;
import com.magistuarmory.misc.ModMerchOffers;
import com.magistuarmory.util.MobEquipment;
import com.magistuarmory.util.MobEquipmentHelper;
import com.magistuarmory.util.ModDamageSources;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.LootEvent;
import dev.architectury.event.events.common.LootEvent.LootTableModificationContext;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.server.MinecraftServer;

public class CommonEvents {
   public static void init() {
      LootEvent.MODIFY_LOOT_TABLE.register(CommonEvents::onModifyLootTable);
      LifecycleEvent.SETUP.register(CommonEvents::onSetup);
      LifecycleEvent.SERVER_STARTING.register(CommonEvents::onServerStarting);
      LifecycleEvent.SERVER_LEVEL_LOAD.register(CommonEvents::onServerLevelLoad);
      EntityEvent.ADD.register(CommonEvents::onEntityJoinLevel);
      EntityEvent.LIVING_HURT.register(CommonEvents::onLivingHurt);
   }

   private static void onModifyLootTable(class_5321<class_52> key, LootTableModificationContext context, boolean builtin) {
      ModLoot.modifyLootTable(key, context, builtin);
   }

   private static void onSetup() {
      ModMerchOffers.setup();
   }

   private static void onServerStarting(MinecraftServer server) {
      MobEquipment.setup(server);
      EpicKnights.checkBetterCombatOrEpicFightInstalled();
   }

   private static void onServerLevelLoad(class_1937 level) {
      ModDamageSources.setup(level.method_30349());
   }

   private static EventResult onEntityJoinLevel(class_1297 entity, class_1937 level) {
      if (entity instanceof class_1309 livingentity) {
         MobEquipmentHelper.equip(livingentity);
      }

      return EventResult.pass();
   }

   private static EventResult onLivingHurt(class_1309 victim, class_1282 source, float damage) {
      if (victim.method_37908().method_8608()) {
         return EventResult.pass();
      } else {
         class_1799 stack = victim.method_6030();
         if (!victim.method_5655() && !(victim instanceof class_1657 player && player.method_7337())) {
            if (victim.method_6039()) {
               if (stack.method_7909() instanceof MedievalShieldItem shield) {
                  shield.onBlocked(stack, damage, victim, source);
                  return EventResult.pass();
               }

               if (stack.method_7909() instanceof MedievalWeaponItem weapon && weapon.canBlock()) {
                  weapon.onBlocked(stack, damage, victim, source);
                  return EventResult.pass();
               }
            }

            if (source.method_5529() instanceof class_1309 attacker && attacker.method_6047().method_7909() instanceof MedievalWeaponItem weapon2) {
               weapon2.onHurtEntity(source, victim, damage);
            }

            return EventResult.pass();
         } else {
            return EventResult.pass();
         }
      }
   }
}
