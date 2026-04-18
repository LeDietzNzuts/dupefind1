package dev.architectury.mixin.fabric;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3730;
import net.minecraft.class_3732;
import net.minecraft.class_3769;
import net.minecraft.class_5819;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_3769.class)
public abstract class MixinPatrolSpawner {
   @Inject(
      method = "spawnPatrolMember",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;",
         ordinal = 0,
         shift = Shift.BY,
         by = 2
      ),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void checkPatrolSpawn(
      class_3218 level, class_2338 pos, class_5819 r, boolean b, CallbackInfoReturnable<Boolean> cir, class_2680 blockState, class_3732 entity
   ) {
      EventResult result = EntityEvent.LIVING_CHECK_SPAWN
         .invoker()
         .canSpawn(entity, level, pos.method_10263(), pos.method_10264(), pos.method_10260(), class_3730.field_16527, null);
      if (result.value() != null) {
         cir.setReturnValue(result.value());
      }
   }
}
