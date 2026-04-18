package dev.architectury.hooks.level.biome.fabric;

import dev.architectury.mixin.fabric.BiomeAccessor;
import net.minecraft.class_1959;
import net.minecraft.class_1959.class_5482;

public class BiomeHooksImpl {
   public static class_5482 extractClimateSettings(class_1959 biome) {
      return ((BiomeAccessor)biome).getClimateSettings();
   }
}
