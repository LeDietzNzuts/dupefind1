package com.magistuarmory.util;

import com.magistuarmory.EpicKnights;
import java.util.List;
import net.minecraft.class_1267;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_5819;

public class MobEquipmentHelper {
   public static void equip(class_1309 entity) {
      if (!entity.method_37908().method_8608()
         && EpicKnights.GENERAL_CONFIG.equipMonsters
         && (!EpicKnights.GENERAL_CONFIG.equipMonstersOnlyIfHard || entity.method_37908().method_8407().equals(class_1267.field_5807))) {
         class_5819 rand = entity.method_37908().method_8409();
         List<MobEquipment> equipments = MobEquipment.get(entity);
         if (equipments.size() > 0) {
            equipments.get(rand.method_43048(equipments.size())).equip(entity, rand);
         }
      }
   }

   static void setRandomItemSlot(class_1309 entity, class_1304 slot, List<? extends class_1792> items, double chance, class_5819 rand) {
      if (items.size() == 0) {
         if (EpicKnights.GENERAL_CONFIG.overrideEquipment) {
            entity.method_5673(slot, new class_1799(class_1802.field_8162));
         }
      } else {
         int id = rand.method_43048((int)(items.size() / chance));
         if (id < items.size()) {
            entity.method_5673(slot, new class_1799((class_1935)items.get(id)));
         } else if (EpicKnights.GENERAL_CONFIG.overrideEquipment) {
            entity.method_5673(slot, new class_1799(class_1802.field_8162));
         }
      }
   }
}
