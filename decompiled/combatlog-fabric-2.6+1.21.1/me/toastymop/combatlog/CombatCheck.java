package me.toastymop.combatlog;

import me.toastymop.combatlog.util.IEntityDataSaver;
import me.toastymop.combatlog.util.TagData;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_3222;

public class CombatCheck {
   static class_1792 enderPearl = class_1802.field_8634.method_7854().method_7909();

   public static void CheckCombat(class_1297 entity) {
      class_1309 target = (class_1309)entity;
      if (target instanceof class_1657) {
         class_1309 attacker = target.method_6065();
         if (attacker instanceof class_1657
            && ((class_3222)target).field_13974.method_14257().method_8388()
            && ((class_3222)attacker).field_13974.method_14257().method_8388()) {
            setCombat((class_1657)target, (class_1657)attacker);
         } else if (CombatConfig.Config.allDamage && ((class_3222)target).field_13974.method_14257().method_8388()) {
            setCombat((class_1657)target);
         } else if (CombatConfig.Config.mobDamage && attacker instanceof class_1309 && ((class_3222)target).field_13974.method_14257().method_8388()) {
            setCombat((class_1657)target);
         }
      }
   }

   public static void setCombat(class_1657 target, class_1657 attacker) {
      TagData.setTagTime((IEntityDataSaver)target);
      TagData.setTagTime((IEntityDataSaver)attacker);
      if (CombatConfig.Config.disablePearl) {
         target.method_7357().method_7906(enderPearl, CombatConfig.Config.combatTime * 20);
         attacker.method_7357().method_7906(enderPearl, CombatConfig.Config.combatTime * 20);
      }
   }

   public static void setCombat(class_1657 target) {
      TagData.setTagTime((IEntityDataSaver)target);
      if (CombatConfig.Config.disablePearl) {
         target.method_7357().method_7906(enderPearl, CombatConfig.Config.combatTime * 20);
      }
   }
}
