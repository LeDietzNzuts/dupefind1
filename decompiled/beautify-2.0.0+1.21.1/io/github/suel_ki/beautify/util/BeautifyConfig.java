package io.github.suel_ki.beautify.util;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "beautify")
public class BeautifyConfig implements ConfigData {
   @CollapsibleObject
   public BeautifyConfig.Blinds blinds = new BeautifyConfig.Blinds();
   @CollapsibleObject
   public BeautifyConfig.Houses houses = new BeautifyConfig.Houses();

   public static class Blinds {
      @Tooltip
      @BoundedDiscrete(min = 0L, max = 100L)
      @Comment("Searches X-Blocks below and X/2 to the sides of the clicked blind for others and opens/closes them too")
      public int searchRadius = 6;
      @Tooltip
      @Comment("Opens blinds from the topmost blind on if true")
      public boolean opensFromRoot = true;
   }

   public static class Houses {
      @Tooltip
      @Comment("Defines the chance of a Botanist Villager House inside a village")
      public int botanistSpawnWeight = 2;
   }
}
