package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_1451;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3730;
import net.minecraft.class_4274;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_4274.class)
public abstract class MixinCatSpawner {
   @Inject(
      method = "spawnCat",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/animal/Cat;finalizeSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;",
         ordinal = 0
      ),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void checkCatSpawn(class_2338 pos, class_3218 level, CallbackInfoReturnable<Integer> cir, class_1451 entity) {
      if (EntityEvent.LIVING_CHECK_SPAWN
            .invoker()
            .canSpawn(entity, level, pos.method_10263(), pos.method_10264(), pos.method_10260(), class_3730.field_16459, null)
            .value()
         == Boolean.FALSE) {
         cir.setReturnValue(0);
         cir.cancel();
      }
   }
}
