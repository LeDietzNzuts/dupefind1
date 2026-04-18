package com.magistuarmory.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer.GlobalData;

@Config(name = "epicknights")
public class ModConfig extends GlobalData {
   @CollapsibleObject
   public GeneralConfig general = new GeneralConfig();
   @CollapsibleObject
   public WeaponsConfig weapons = new WeaponsConfig();
   @CollapsibleObject
   public ShieldsConfig shields = new ShieldsConfig();
   @CollapsibleObject
   public ArmorConfig armor = new ArmorConfig();
   @CollapsibleObject
   public MobEquipmentConfig mobEquipments = new MobEquipmentConfig();
}
