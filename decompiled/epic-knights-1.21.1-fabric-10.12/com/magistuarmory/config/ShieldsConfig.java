package com.magistuarmory.config;

import com.magistuarmory.item.ShieldType;
import java.util.LinkedHashMap;
import java.util.Map;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;

@Config(name = "shields")
public class ShieldsConfig implements ConfigData {
   @CollapsibleObject
   public Map<String, ShieldType> shields = new LinkedHashMap<String, ShieldType>() {
      {
         this.put("heaterShield", ShieldType.of(350, 0.8F, 4.0F, 10.0F, true, true));
         this.put("target", ShieldType.of(350, 0.8F, 1.0F, 6.0F, true, true));
         this.put("buckler", ShieldType.of(350, 0.8F, 1.0F, 6.0F, true, true));
         this.put("rondache", ShieldType.of(420, 1.2F, 6.0F, 13.0F, true, true));
         this.put("tartsche", ShieldType.of(350, 0.8F, 4.0F, 10.0F, true, true));
         this.put("ellipticalShield", ShieldType.of(370, 0.8F, 5.0F, 10.0F, true, true));
         this.put("roundShield", ShieldType.of(350, 0.8F, 3.0F, 7.0F, true, true));
         this.put("pavise", ShieldType.of(450, 0.7F, 10.0F, 17.0F, true, true));
         this.put("kiteShield", ShieldType.of(370, 0.8F, 5.0F, 10.0F, true, true));
         this.put("corruptedRoundShield", ShieldType.of(100, 0.8F, 2.0F, 4.0F, true, true));
      }
   };

   public ShieldType get(String name) {
      return this.shields.get(name);
   }
}
