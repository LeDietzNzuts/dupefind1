package dev.architectury.mixin.fabric;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1308;
import net.minecraft.class_1917;
import net.minecraft.class_1936;
import net.minecraft.class_3730;
import net.minecraft.class_4538;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1917.class)
public abstract class MixinBaseSpawner {
   @Redirect(
      method = "serverTick",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/Mob;checkSpawnRules(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;)Z",
         ordinal = 0
      )
   )
   private boolean checkSpawnerSpawn(class_1308 mob, class_1936 level, class_3730 type) {
      EventResult result = EntityEvent.LIVING_CHECK_SPAWN
         .invoker()
         .canSpawn(mob, level, mob.method_23317(), mob.method_23318(), mob.method_23321(), type, (class_1917)this);
      return result.value() != null ? result.value() : mob.method_5979(level, type) && mob.method_5957(level);
   }

   @Redirect(
      method = "serverTick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;checkSpawnObstruction(Lnet/minecraft/world/level/LevelReader;)Z", ordinal = 0)
   )
   private boolean skipDoubleObstruction(class_1308 mob, class_4538 levelReader) {
      return true;
   }
}
