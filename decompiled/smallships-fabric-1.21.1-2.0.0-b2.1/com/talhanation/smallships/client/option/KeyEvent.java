package com.talhanation.smallships.client.option;

import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.network.packet.ServerboundShootShipCannonPacket;
import com.talhanation.smallships.network.packet.ServerboundToggleShipSailPacket;
import com.talhanation.smallships.world.entity.cannon.GroundCannonEntity;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import com.talhanation.smallships.world.entity.ship.abilities.Sailable;
import net.minecraft.class_1657;
import net.minecraft.class_310;

public class KeyEvent {
   static boolean wasPressedSailKey = false;

   public static void onKeyInput(int key, int scanCode, int action, int modifiers) {
      class_1657 player;
      if ((player = class_310.method_1551().field_1724) != null) {
         if (player.method_5854() instanceof GroundCannonEntity cannon) {
            if (class_310.method_1551().field_1690.field_1903.method_1434()) {
               cannon.trigger(player);
            } else if (ModGameOptions.ENTER_CANNON_BARREL_KEY.method_1417(key, scanCode) && action == 1) {
               cannon.putEntityIntoBarrel(player);
            }
         }
      }
   }

   public static void onKeyInput(class_310 client) {
      class_1657 player = client.field_1724;
      if (player != null) {
         boolean pressedSailKey = ModGameOptions.SAIL_KEY.method_1436();
         boolean pressedEnterKey = ModGameOptions.ENTER_CANNON_BARREL_KEY.method_1436();
         boolean pressedJumpKey = client.field_1690.field_1903.method_1434();
         if (player.method_5854() instanceof Ship ship && player.equals(ship.getDriver())) {
            if (ship instanceof Sailable) {
               if (pressedSailKey && !wasPressedSailKey) {
                  ModPackets.clientSendPacket(new ServerboundToggleShipSailPacket());
                  wasPressedSailKey = true;
               } else {
                  wasPressedSailKey = false;
               }
            }

            if (ship instanceof Cannonable cannonable) {
               if (pressedJumpKey) {
                  ModPackets.clientSendPacket(new ServerboundShootShipCannonPacket(true));
               } else if (!cannonable.self().isCannonKeyPressed()) {
                  ModPackets.clientSendPacket(new ServerboundShootShipCannonPacket(false));
               }
            }
         }
      }
   }
}
