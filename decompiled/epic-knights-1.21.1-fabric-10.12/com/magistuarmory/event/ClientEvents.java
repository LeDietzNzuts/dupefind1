package com.magistuarmory.event;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.client.HitResultHelper;
import com.magistuarmory.client.render.ModRender;
import com.magistuarmory.item.LanceItem;
import com.magistuarmory.item.MedievalWeaponItem;
import com.magistuarmory.util.CombatHelper;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientPlayerEvent;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3966;
import net.minecraft.class_746;

@Environment(EnvType.CLIENT)
public class ClientEvents {
   public static void init() {
      ClientRawInputEvent.MOUSE_CLICKED_PRE.register(ClientEvents::onMouseInput);
      LifecycleEvent.SETUP.register(ModRender::registerRenderers);
      ClientPlayerEvent.CLIENT_PLAYER_JOIN.register(ClientEvents::onClientPlayerJoin);
   }

   private static void onClientPlayerJoin(class_746 player) {
      if (player.method_37908().method_8608() && player == class_310.method_1551().field_1724) {
         EpicKnights.checkBetterCombatOrEpicFightInstalled();
      }
   }

   private static EventResult onMouseInput(class_310 mc, int button, int action, int mods) {
      if (mc.field_1687 != null && mc.field_1755 == null && !mc.method_1493()) {
         class_304 keyattack = mc.field_1690.field_1886;
         if (button == keyattack.method_1429().method_1444()) {
            class_1657 player = mc.field_1724;
            if (player == null || player.method_6039()) {
               return EventResult.pass();
            }

            class_1799 stack = player.method_6047();
            if (stack.method_7909() instanceof MedievalWeaponItem weapon
               && HitResultHelper.getMouseOver(mc, CombatHelper.getAttackReach(player, weapon)) instanceof class_3966 entityhit
               && !weapon.onAttackClickEntity(stack, player, entityhit.method_17782())) {
               return weapon instanceof LanceItem ? EventResult.interruptFalse() : EventResult.interruptDefault();
            }
         }

         return EventResult.pass();
      } else {
         return EventResult.pass();
      }
   }
}
