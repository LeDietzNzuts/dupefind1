package me.toastymop.combatlog.util;

import me.toastymop.combatlog.CombatConfig;
import net.minecraft.class_2487;

public class TagData {
   public static void decreaseTagTime(IEntityDataSaver player) {
      class_2487 nbt = player.getPersistentData();
      int tagTime = nbt.method_10550("combatTime");
      if (tagTime > 0) {
         nbt.method_10569("combatTime", --tagTime);
      }
   }

   public static void setTagTime(IEntityDataSaver player) {
      class_2487 nbt = player.getPersistentData();
      nbt.method_10569("combatTime", CombatConfig.Config.combatTime * 20);
      nbt.method_10556("inCombat", true);
   }

   public static void endCombat(IEntityDataSaver player) {
      class_2487 nbt = player.getPersistentData();
      nbt.method_10569("combatTime", 0);
      nbt.method_10556("inCombat", false);
   }

   public static int getTagTime(IEntityDataSaver player) {
      class_2487 nbt = player.getPersistentData();
      return nbt.method_10550("combatTime");
   }

   public static boolean getCombat(IEntityDataSaver player) {
      class_2487 nbt = player.getPersistentData();
      return nbt.method_10577("inCombat");
   }
}
