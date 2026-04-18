package me.toastymop.combatlog;

import me.toastymop.combatlog.util.IEntityDataSaver;
import me.toastymop.combatlog.util.TagData;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_5250;
import net.minecraft.server.MinecraftServer;

public class CombatTicks {
   public static void CombatTick(MinecraftServer server) {
      for (class_1657 player : server.method_3760().method_14571()) {
         IEntityDataSaver data = (IEntityDataSaver)player;
         if (TagData.getCombat(data)) {
            int tagTime = TagData.getTagTime(data);
            boolean combatNotice = CombatConfig.Config.combatNotice;
            if (tagTime > 0) {
               TagData.decreaseTagTime(data);
               if (combatNotice) {
                  String message = CombatConfig.Config.inCombat.replace("{timeLeft}", String.valueOf(tagTime / 20));
                  class_5250 inCombat = class_2561.method_43470(message);
                  player.method_7353(inCombat.method_27696(class_2583.field_24360.method_10977(class_124.field_1061)), true);
               }
            } else {
               TagData.endCombat(data);
               if (combatNotice) {
                  class_5250 outCombat = class_2561.method_43470(CombatConfig.Config.outCombat);
                  player.method_7353(outCombat.method_27696(class_2583.field_24360.method_10977(class_124.field_1060)), true);
               }
            }
         }
      }
   }
}
