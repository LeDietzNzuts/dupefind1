package dev.architectury.mixin.fabric;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1308;
import net.minecraft.class_1936;
import net.minecraft.class_1948;
import net.minecraft.class_3218;
import net.minecraft.class_3730;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1948.class)
public abstract class MixinNaturalSpawner {
   @Shadow
   private static boolean method_24932(class_3218 serverLevel, class_1308 mob, double d) {
      return false;
   }

   @Redirect(
      method = "spawnCategoryForPosition(Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/NaturalSpawner$SpawnPredicate;Lnet/minecraft/world/level/NaturalSpawner$AfterSpawnCallback;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/level/NaturalSpawner;isValidPositionForMob(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Mob;D)Z",
         ordinal = 0
      )
   )
   private static boolean overrideNaturalSpawnCondition(class_3218 level, class_1308 entity, double f) {
      EventResult result = EntityEvent.LIVING_CHECK_SPAWN
         .invoker()
         .canSpawn(entity, level, entity.field_6038, entity.field_5971, entity.field_5989, class_3730.field_16459, null);
      return result.value() != null ? result.value() : method_24932(level, entity, f);
   }

   @Redirect(
      method = "spawnMobsForChunkGeneration",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/Mob;checkSpawnRules(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;)Z",
         ordinal = 0
      )
   )
   private static boolean overrideChunkGenSpawnCondition(class_1308 mob, class_1936 level, class_3730 type) {
      EventResult result = EntityEvent.LIVING_CHECK_SPAWN
         .invoker()
         .canSpawn(mob, level, mob.field_6038, mob.field_5971, mob.field_5989, class_3730.field_16472, null);
      return result.value() != null ? result.value() : mob.method_5979(level, type);
   }
}
