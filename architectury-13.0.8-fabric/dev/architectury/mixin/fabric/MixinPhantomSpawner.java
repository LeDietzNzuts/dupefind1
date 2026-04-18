package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import java.util.Iterator;
import net.minecraft.class_1266;
import net.minecraft.class_1315;
import net.minecraft.class_1593;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2910;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3442;
import net.minecraft.class_3610;
import net.minecraft.class_3730;
import net.minecraft.class_5819;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_2910.class)
public abstract class MixinPhantomSpawner {
   @Inject(
      method = "tick",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/monster/Phantom;finalizeSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;",
         ordinal = 0,
         shift = Shift.BEFORE
      ),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILSOFT
   )
   private void checkPhantomSpawn(
      class_3218 level,
      boolean bl,
      boolean bl2,
      CallbackInfoReturnable<Integer> cir,
      class_5819 random,
      int i,
      Iterator<class_3222> it,
      class_3222 player,
      class_2338 pos,
      class_1266 diff,
      class_3442 serverStatsCounter,
      int j,
      int k,
      class_2338 pos2,
      class_2680 blockState,
      class_3610 fluidState,
      class_1315 sgd,
      int l,
      int m,
      class_1593 entity
   ) {
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
