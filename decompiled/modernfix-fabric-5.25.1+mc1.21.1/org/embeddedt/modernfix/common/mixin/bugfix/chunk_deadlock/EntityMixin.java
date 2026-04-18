package org.embeddedt.modernfix.common.mixin.bugfix.chunk_deadlock;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.class_1297;
import net.minecraft.class_1923;
import net.minecraft.class_3218;
import net.minecraft.class_5712;
import net.minecraft.class_6880;
import org.embeddedt.modernfix.ModernFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1297.class)
public class EntityMixin {
   @WrapWithCondition(
      method = "addPassenger",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;gameEvent(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/Entity;)V")
   )
   private boolean onlyAddIfSelfChunkLoaded(class_1297 instance, class_6880<class_5712> gameEvent, class_1297 entity) {
      class_1923 chunkPos = instance.method_31476();
      if (instance.method_37908() instanceof class_3218 serverLevel
         && serverLevel.method_14178().method_21730(chunkPos.field_9181, chunkPos.field_9180) == null) {
         ModernFix.LOGGER.warn("Skipped emitting ENTITY_MOUNT game event for entity {} as it would cause deadlock", instance.toString());
         return false;
      } else {
         return true;
      }
   }
}
