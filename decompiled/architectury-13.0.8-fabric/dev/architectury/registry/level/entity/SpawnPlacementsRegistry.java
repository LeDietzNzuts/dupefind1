package dev.architectury.registry.level.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.level.entity.fabric.SpawnPlacementsRegistryImpl;
import java.util.function.Supplier;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_9168;
import net.minecraft.class_1317.class_4306;
import net.minecraft.class_2902.class_2903;

public final class SpawnPlacementsRegistry {
   @ExpectPlatform
   @Transformed
   public static <T extends class_1308> void register(
      Supplier<? extends class_1299<T>> type, class_9168 spawnPlacement, class_2903 heightmapType, class_4306<T> spawnPredicate
   ) {
      SpawnPlacementsRegistryImpl.register(type, spawnPlacement, heightmapType, spawnPredicate);
   }
}
