package dev.architectury.hooks.level.biome;

import net.minecraft.class_1959.class_5484;

public interface ClimateProperties {
   boolean hasPrecipitation();

   float getTemperature();

   class_5484 getTemperatureModifier();

   float getDownfall();

   public interface Mutable extends ClimateProperties {
      ClimateProperties.Mutable setHasPrecipitation(boolean var1);

      ClimateProperties.Mutable setTemperature(float var1);

      ClimateProperties.Mutable setTemperatureModifier(class_5484 var1);

      ClimateProperties.Mutable setDownfall(float var1);
   }
}
