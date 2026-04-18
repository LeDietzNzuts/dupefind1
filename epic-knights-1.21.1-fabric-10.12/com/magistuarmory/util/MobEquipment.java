package com.magistuarmory.util;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.config.MobEquipmentConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1738;
import net.minecraft.class_1792;
import net.minecraft.class_1819;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_5321;
import net.minecraft.class_5819;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.server.MinecraftServer;

public class MobEquipment {
   public static final MobEquipmentConfig MOBS_EQUIPMENT_CONFIG = EpicKnights.CONFIG.mobEquipments;
   static Map<DualKey<class_1299<? extends class_1309>, class_5321<class_1937>>, List<MobEquipment>> EQUIPMENTS = new HashMap<>();
   public final List<class_1299<? extends class_1309>> entities = new ArrayList<>();
   public final List<class_5321<class_1937>> dimensions;
   public final List<class_1738> helmets = new ArrayList<>();
   public final List<class_1738> chestplates = new ArrayList<>();
   public final List<class_1738> leggings = new ArrayList<>();
   public final List<class_1738> boots = new ArrayList<>();
   public final List<class_1792> weapons = new ArrayList<>();
   public final List<class_1819> shields = new ArrayList<>();
   public double chance;

   MobEquipment(MinecraftServer server, String[] ids) {
      List<class_5321<class_1937>> dimensions = new ArrayList<>();
      this.chance = EpicKnights.GENERAL_CONFIG.equipChance;

      for (String id : ids) {
         class_2960 resloc = class_2960.method_60654(id);
         Optional<class_1299<?>> entityoptional = class_7923.field_41177.method_17966(resloc);
         if (entityoptional.isPresent()) {
            try {
               this.entities.add((class_1299<? extends class_1309>)entityoptional.get());
               continue;
            } catch (ClassCastException var13) {
               System.out.println("[Epic-Knights Mob Equipment] Non-living entity type \"" + id + "\" is not allowed");
            }
         }

         Optional<class_1792> itemoptional = class_7923.field_41178.method_17966(resloc);
         if (itemoptional.isPresent()) {
            if (itemoptional.get() instanceof class_1738 armor) {
               switch (armor.method_48398().method_48399()) {
                  case field_6169:
                     this.helmets.add(armor);
                     break;
                  case field_6174:
                     this.chestplates.add(armor);
                     break;
                  case field_6172:
                     this.leggings.add(armor);
                     break;
                  case field_6166:
                     this.boots.add(armor);
               }
            } else if (itemoptional.get() instanceof class_1819 shield) {
               this.shields.add(shield);
            } else {
               this.weapons.add(itemoptional.get());
            }
         } else {
            class_5321<class_1937> resourcekey = class_5321.method_29179(class_7924.field_41223, resloc);
            class_3218 serverlevel = server.method_3847(resourcekey);
            if (serverlevel != null) {
               dimensions.add(serverlevel.method_27983());
            } else if (id.matches("[-+]?[0-9]*\\.?[0-9]+")) {
               this.chance = Double.parseDouble(id);
            }
         }
      }

      if (dimensions.size() == 0) {
         server.method_3738().forEach(serverlevel -> dimensions.add(serverlevel.method_27983()));
      }

      this.dimensions = dimensions;
   }

   public void equip(class_1309 entity, class_5819 rand) {
      MobEquipmentHelper.setRandomItemSlot(entity, class_1304.field_6169, this.helmets, this.chance, rand);
      MobEquipmentHelper.setRandomItemSlot(entity, class_1304.field_6174, this.chestplates, this.chance, rand);
      MobEquipmentHelper.setRandomItemSlot(entity, class_1304.field_6172, this.leggings, this.chance, rand);
      MobEquipmentHelper.setRandomItemSlot(entity, class_1304.field_6166, this.boots, this.chance, rand);
      MobEquipmentHelper.setRandomItemSlot(entity, class_1304.field_6173, this.weapons, this.chance, rand);
      MobEquipmentHelper.setRandomItemSlot(entity, class_1304.field_6171, this.shields, 0.5 * this.chance, rand);
   }

   public static void setup(MinecraftServer server) {
      for (String ids : MOBS_EQUIPMENT_CONFIG.equipments) {
         MobEquipment equipment = new MobEquipment(server, ids.split(" "));
         equipment.entities.forEach(type -> equipment.dimensions.forEach(dimension -> {
            DualKey<class_1299<? extends class_1309>, class_5321<class_1937>> key = new DualKey<>(type, (class_5321<class_1937>)dimension);
            EQUIPMENTS.putIfAbsent(key, new ArrayList<>());
            EQUIPMENTS.get(key).add(equipment);
         }));
      }
   }

   public static List<MobEquipment> get(class_1309 entity) {
      return EQUIPMENTS.getOrDefault(new DualKey<>(entity.method_5864(), entity.method_37908().method_27983()), new ArrayList<>());
   }
}
