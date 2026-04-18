package com.magistuarmory.config;

import java.util.LinkedHashMap;
import java.util.Map;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
import net.minecraft.class_1741;
import net.minecraft.class_1738.class_8051;

@Config(name = "armor")
public class ArmorConfig implements ConfigData {
   @CollapsibleObject
   public Map<String, ArmorConfig.ArmorTypeConfig> armor = new LinkedHashMap<String, ArmorConfig.ArmorTypeConfig>() {
      {
         this.put("minicrown", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{0, 0, 0, 77}, new Integer[]{0, 0, 0, 2}, 25, true));
         this.put("crown", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{0, 0, 0, 77}, new Integer[]{0, 0, 0, 2}, 25, true));
         this.put("flowercrown", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{0, 0, 0, 77}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("knight", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{230, 315, 335, 0}, new Integer[]{2, 5, 8, 0}, 9, true));
         this.put("armet", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{0, 0, 0, 275}, new Integer[]{0, 0, 0, 3}, 9, true));
         this.put("stechhelm", ArmorConfig.ArmorTypeConfig.of(2.0F, 1.5F, new Integer[]{0, 0, 0, 385}, new Integer[]{0, 0, 0, 4}, 9, true));
         this.put("jousting", ArmorConfig.ArmorTypeConfig.of(2.0F, 1.5F, new Integer[]{345, 440, 500, 0}, new Integer[]{3, 6, 9, 0}, 9, true));
         this.put("sallet", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{0, 0, 0, 275}, new Integer[]{0, 0, 0, 3}, 9, true));
         this.put("gothic", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{230, 315, 335, 0}, new Integer[]{2, 5, 8, 0}, 9, true));
         this.put("maximilianHelmet", ArmorConfig.ArmorTypeConfig.of(1.8F, 0.5F, new Integer[]{0, 0, 0, 385}, new Integer[]{0, 0, 0, 4}, 12, true));
         this.put("maximilian", ArmorConfig.ArmorTypeConfig.of(1.8F, 0.5F, new Integer[]{345, 440, 500, 0}, new Integer[]{3, 6, 9, 0}, 12, true));
         this.put("chainmail", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{170, 235, 250, 205}, new Integer[]{1, 4, 5, 2}, 9, true));
         this.put("kettlehat", ArmorConfig.ArmorTypeConfig.of(0.5F, 0.0F, new Integer[]{0, 0, 0, 240}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("platemail", ArmorConfig.ArmorTypeConfig.of(0.5F, 0.0F, new Integer[]{200, 230, 290, 0}, new Integer[]{3, 4, 6, 0}, 9, true));
         this.put("barbute", ArmorConfig.ArmorTypeConfig.of(0.5F, 0.0F, new Integer[]{0, 0, 0, 170}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("halfarmor", ArmorConfig.ArmorTypeConfig.of(0.5F, 0.0F, new Integer[]{0, 0, 315, 0}, new Integer[]{0, 0, 6, 0}, 9, true));
         this.put("greathelm", ArmorConfig.ArmorTypeConfig.of(0.6F, 0.0F, new Integer[]{150, 280, 295, 220}, new Integer[]{1, 5, 6, 3}, 9, true));
         this.put("crusader", ArmorConfig.ArmorTypeConfig.of(0.6F, 0.0F, new Integer[]{150, 280, 295, 220}, new Integer[]{1, 5, 6, 3}, 9, true));
         this.put("brigandine", ArmorConfig.ArmorTypeConfig.of(0.25F, 0.0F, new Integer[]{0, 0, 265, 0}, new Integer[]{0, 0, 5, 0}, 9, true));
         this.put("gambeson", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{100, 100, 128, 88}, new Integer[]{1, 1, 3, 1}, 9, true));
         this.put("ceremonialArmet", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{0, 0, 0, 275}, new Integer[]{0, 0, 0, 3}, 9, true));
         this.put("ceremonial", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{230, 0, 335, 0}, new Integer[]{2, 0, 8, 0}, 9, true));
         this.put("shishak", ArmorConfig.ArmorTypeConfig.of(0.3F, 0.0F, new Integer[]{0, 0, 0, 210}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("norman", ArmorConfig.ArmorTypeConfig.of(0.2F, 0.0F, new Integer[]{0, 0, 0, 190}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("rustedBarbute", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{0, 0, 0, 90}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("rustedHalfarmor", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{0, 0, 170, 0}, new Integer[]{0, 0, 6, 0}, 9, true));
         this.put("rustedChainmail", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{85, 115, 125, 100}, new Integer[]{1, 4, 5, 2}, 9, true));
         this.put("rustedKettlehat", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{0, 0, 0, 120}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("rustedNorman", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{0, 0, 0, 85}, new Integer[]{0, 0, 0, 2}, 9, true));
         this.put("rustedGreathelm", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{75, 0, 150, 110}, new Integer[]{1, 0, 6, 3}, 9, true));
         this.put("rustedCrusader", ArmorConfig.ArmorTypeConfig.of(0.0F, 0.0F, new Integer[]{75, 0, 150, 110}, new Integer[]{1, 0, 6, 3}, 9, true));
         this.put("bascinet", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.0F, new Integer[]{0, 0, 0, 250}, new Integer[]{0, 0, 0, 3}, 9, true));
         this.put("xivCenturyKnight", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.0F, new Integer[]{210, 300, 320, 0}, new Integer[]{2, 5, 7, 0}, 9, true));
         this.put("wingedHussarChestplate", ArmorConfig.ArmorTypeConfig.of(0.5F, 0.0F, new Integer[]{0, 0, 360, 0}, new Integer[]{0, 0, 6, 0}, 9, true));
         this.put("cuirassier", ArmorConfig.ArmorTypeConfig.of(0.5F, 0.0F, new Integer[]{150, 100, 315, 170}, new Integer[]{1, 1, 6, 2}, 9, true));
         this.put("kastenbrust", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{230, 315, 335, 0}, new Integer[]{2, 5, 8, 0}, 9, true));
         this.put("grandBascinet", ArmorConfig.ArmorTypeConfig.of(1.25F, 0.5F, new Integer[]{0, 0, 0, 275}, new Integer[]{0, 0, 0, 3}, 9, true));
         this.put("lamellar", ArmorConfig.ArmorTypeConfig.of(0.3F, 0.0F, new Integer[]{200, 0, 250, 240}, new Integer[]{2, 0, 6, 2}, 9, true));
      }
   };

   public ArmorConfig.ArmorTypeConfig get(String name) {
      return this.armor.get(name);
   }

   public static class ArmorTypeConfig {
      @RequiresRestart
      public float toughness;
      @RequiresRestart
      public float knockbackResistance;
      @RequiresRestart
      public int bootsDurability;
      @RequiresRestart
      public int leggingsDurability;
      @RequiresRestart
      public int chestplateDurability;
      @RequiresRestart
      public int helmetDurability;
      @RequiresRestart
      public int bootsDefense;
      @RequiresRestart
      public int leggingsDefense;
      @RequiresRestart
      public int chestplateDefense;
      @RequiresRestart
      public int helmetDefense;
      @RequiresRestart
      public int enchantmentValue;
      @RequiresRestart
      public boolean enabled;

      private ArmorTypeConfig() {
      }

      public static ArmorConfig.ArmorTypeConfig of(
         float toughness, float knockbackResistance, Integer[] durability, Integer[] defenseForSlot, int enchantmentValue, boolean enabled
      ) {
         ArmorConfig.ArmorTypeConfig cfg = new ArmorConfig.ArmorTypeConfig();
         cfg.toughness = toughness;
         cfg.knockbackResistance = knockbackResistance;
         cfg.bootsDurability = durability[0];
         cfg.leggingsDurability = durability[1];
         cfg.chestplateDurability = durability[2];
         cfg.helmetDurability = durability[3];
         cfg.bootsDefense = defenseForSlot[0];
         cfg.leggingsDefense = defenseForSlot[1];
         cfg.chestplateDefense = defenseForSlot[2];
         cfg.helmetDefense = defenseForSlot[3];
         cfg.enchantmentValue = enchantmentValue;
         cfg.enabled = enabled;
         return cfg;
      }

      public static ArmorConfig.ArmorTypeConfig of(class_1741 material, Integer[] durability, boolean enabled) {
         return of(
            material.comp_2303(),
            material.comp_2304(),
            durability,
            new Integer[]{
               material.method_48403(class_8051.field_41937),
               material.method_48403(class_8051.field_41936),
               material.method_48403(class_8051.field_41935),
               material.method_48403(class_8051.field_41934)
            },
            material.comp_2299(),
            enabled
         );
      }
   }
}
