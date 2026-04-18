package com.magistuarmory.misc;

import dev.architectury.event.events.common.LootEvent.LootTableModificationContext;
import net.minecraft.class_2960;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_55;
import net.minecraft.class_67;
import net.minecraft.class_55.class_56;

public class ModLoot {
   public static void modifyLootTable(class_5321<class_52> key, LootTableModificationContext context, boolean builtin) {
      String prefix = "minecraft:chests/";
      String name = key.method_29177().toString();
      if (name.startsWith(prefix)) {
         String file = name.substring(name.indexOf(prefix) + prefix.length());
         switch (file) {
            case "desert_pyramid":
            case "end_city_treasure":
            case "jungle_temple":
            case "nether_bridge":
            case "ruined_portal":
            case "simple_dungeon":
            case "stronghold_corridor":
            case "village/village_weaponsmith":
               context.addPool(getPoolEntry(key));
         }
      }
   }

   private static class_56 getPoolEntry(class_5321<class_52> key) {
      class_2960 table = class_2960.method_60655("magistuarmory", key.method_29177().method_12832());
      return class_55.method_347().method_351(class_67.method_390(table));
   }
}
