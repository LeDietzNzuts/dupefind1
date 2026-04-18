package dev.architectury.hooks.level.biome;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_5483.class_1964;
import net.minecraft.class_5483.class_5265;

public interface SpawnProperties {
   float getCreatureProbability();

   Map<class_1311, List<class_1964>> getSpawners();

   Map<class_1299<?>, class_5265> getMobSpawnCosts();

   public interface Mutable extends SpawnProperties {
      SpawnProperties.Mutable setCreatureProbability(float var1);

      SpawnProperties.Mutable addSpawn(class_1311 var1, class_1964 var2);

      boolean removeSpawns(BiPredicate<class_1311, class_1964> var1);

      SpawnProperties.Mutable setSpawnCost(class_1299<?> var1, class_5265 var2);

      SpawnProperties.Mutable setSpawnCost(class_1299<?> var1, double var2, double var4);

      SpawnProperties.Mutable clearSpawnCost(class_1299<?> var1);
   }
}
