package com.magistuarmory.config;

import com.magistuarmory.item.RangedWeaponType;
import com.magistuarmory.item.WeaponType;
import java.util.LinkedHashMap;
import java.util.Map;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;

@Config(name = "weapons")
public class WeaponsConfig implements ConfigData {
   @CollapsibleObject
   private Map<String, RangedWeaponType> rangedWeapons = new LinkedHashMap<String, RangedWeaponType>() {
      {
         this.put("longbow", RangedWeaponType.of(420, 36, 4.3F, true));
         this.put("heavyCrossbow", RangedWeaponType.of(500, 50, 4.3F, true));
      }
   };
   @CollapsibleObject
   private Map<String, WeaponType> meleeWeapons = new LinkedHashMap<String, WeaponType>() {
      {
         this.put("stiletto", WeaponType.of(2.0F, 1.6F, 0.0F, 0.0F, 0.6F, 15, true));
         this.put("shortSword", WeaponType.of(2.5F, 1.7F, 0.0F, 0.0F, 0.9F, 0, true));
         this.put("katzbalger", WeaponType.of(3.2F, 1.65F, 0.0F, 0.0F, 1.1F, 0, true));
         this.put("pike", WeaponType.of(3.0F, 1.17F, 3.0F, 0.0F, 3.0F, 0, true, 2, 0.0F, false, false, false));
         this.put("ranseur", WeaponType.of(3.3F, 1.17F, 2.0F, 0.0F, 2.5F, 0, true, 1, 3.0F, true, false, false));
         this.put("ahlspiess", WeaponType.of(2.8F, 1.28F, 1.0F, 0.02F, 4.0F, 12, true, 2, 3.0F, true, false, false));
         this.put("giantLance", WeaponType.of(2.5F, 0.84F, 2.0F, 0.0F, 4.5F, 10, true));
         this.put("bastardSword", WeaponType.of(4.25F, 1.4F, 0.0F, 0.02F, 1.8F, 0, true, 1, 5.0F, true, false, false));
         this.put("estoc", WeaponType.of(4.1F, 1.4F, 0.5F, 0.02F, 2.0F, 3, true, 1, 5.0F, true, false, false));
         this.put("claymore", WeaponType.of(5.3F, 1.22F, 0.0F, 0.1F, 2.6F, 0, true, 2, 6.0F, true, false, false));
         this.put("zweihander", WeaponType.of(6.0F, 1.12F, 0.5F, 0.05F, 4.0F, 4, true, 2, 6.0F, true, false, false));
         this.put("flameBladedSword", WeaponType.of(6.0F, 1.12F, 0.5F, 0.05F, 4.0F, 4, true, 2, 6.0F, true, true, false));
         this.put("lochaberAxe", WeaponType.of(7.0F, 1.0F, 0.9F, 0.05F, 3.5F, 0, true, 1, 3.0F, true, false, false));
         this.put("concaveEdgedHalberd", WeaponType.of(7.3F, 0.9F, 1.0F, 0.05F, 4.1F, 2, true, 2, 3.0F, true, false, true));
         this.put("heavyMace", WeaponType.of(4.8F, 1.15F, 0.0F, 0.05F, 2.6F, 0, true));
         this.put("heavyWarHammer", WeaponType.of(5.0F, 1.05F, 0.0F, 0.05F, 2.5F, 15, true));
         this.put("lucerneHammer", WeaponType.of(4.4F, 1.1F, 0.7F, 0.05F, 2.5F, 20, true, 1, 0.0F, false, false, false));
         this.put("morningstar", WeaponType.of(4.7F, 1.25F, 0.0F, 0.05F, 2.5F, 0, true));
         this.put("flail", WeaponType.of(6.2F, 1.0F, 0.0F, 0.06F, 3.0F, 0, true));
         this.put("guisarme", WeaponType.of(3.15F, 1.12F, 1.9F, 0.0F, 2.5F, 0, true, 1, 3.3F, true, false, false));
         this.put("blacksmithHammer", WeaponType.of(5.0F, 1.0F, 0.0F, 0.05F, 1.4F, 4, true));
         this.put("barbedClub", WeaponType.of(5.2F, 1.0F, 0.0F, 0.0F, 1.0F, 0, true));
         this.put("pitchfork", WeaponType.of(2.8F, 1.1F, 2.0F, 0.05F, 1.2F, 0, true));
         this.put("nobleSword", WeaponType.of(4.25F, 1.51F, 0.0F, 0.02F, 2.7F, 0, true, 1, 5.0F, true, false, false));
         this.put("rustedBastardSword", WeaponType.of(2.0F, 1.51F, 0.0F, 0.02F, 2.7F, 0, true, 1, 5.0F, true, false, false));
         this.put("rustedHeavyMace", WeaponType.of(2.0F, 1.51F, 0.0F, 0.02F, 2.6F, 0, true));
         this.put("club", WeaponType.of(7.0F, 0.8F, 0.0F, 0.0F, 5.0F, 0, true, 1, 0.0F, false, false, false));
         this.put("messerSword", WeaponType.of(3.4F, 1.5F, 0.0F, 0.02F, 1.2F, 0, true, 0, 5.0F, true, false, false));
      }
   };

   public RangedWeaponType getRanged(String name) {
      return this.rangedWeapons.get(name);
   }

   public WeaponType getMelee(String name) {
      return this.meleeWeapons.get(name);
   }
}
