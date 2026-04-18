package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2344;
import net.minecraft.class_2680;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2344.class)
public abstract class MixinFarmBlock {
   @Unique
   private static ThreadLocal<Triple<Long, Float, class_1297>> turnToDirtLocal = new ThreadLocal<>();

   @Inject(
      method = "fallOn",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/level/block/FarmBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
      )
   )
   private void fallOn(class_1937 level, class_2680 blockState, class_2338 blockPos, class_1297 entity, float f, CallbackInfo ci) {
      turnToDirtLocal.set(Triple.of(blockPos.method_10063(), f, entity));
   }

   @Inject(method = "turnToDirt", at = @At("HEAD"), cancellable = true)
   private static void turnToDirt(@Nullable class_1297 entity, class_2680 state, class_1937 level, class_2338 pos, CallbackInfo ci) {
      Triple<Long, Float, class_1297> triple = turnToDirtLocal.get();
      turnToDirtLocal.remove();
      if (triple != null
         && (Long)triple.getLeft() == pos.method_10063()
         && triple.getRight() == entity
         && InteractionEvent.FARMLAND_TRAMPLE.invoker().trample(level, pos, state, (Float)triple.getMiddle(), entity).value() != null) {
         ci.cancel();
      }
   }
}
