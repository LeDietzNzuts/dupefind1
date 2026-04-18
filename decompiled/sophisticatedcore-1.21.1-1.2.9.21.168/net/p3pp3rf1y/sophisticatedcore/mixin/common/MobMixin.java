package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import net.minecraft.class_1266;
import net.minecraft.class_1308;
import net.minecraft.class_1315;
import net.minecraft.class_3730;
import net.minecraft.class_5425;
import net.p3pp3rf1y.sophisticatedcore.event.common.MobSpawnEvents;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1308.class)
public class MobMixin {
   @Inject(method = "finalizeSpawn", at = @At("RETURN"))
   private void sophosticatedcore$afterFinalizeSpawn(
      class_5425 level, class_1266 difficulty, class_3730 spawnType, class_1315 spawnGroupData, CallbackInfoReturnable<class_1315> cir
   ) {
      ((MobSpawnEvents.After)MobSpawnEvents.AFTER_FINALIZE_SPAWN.invoker())
         .onAfterFinalizeSpawn(new MobSpawnEvents.FinalizeSpawn(MixinHelper.cast(this), level, difficulty, spawnType, spawnGroupData));
   }
}
