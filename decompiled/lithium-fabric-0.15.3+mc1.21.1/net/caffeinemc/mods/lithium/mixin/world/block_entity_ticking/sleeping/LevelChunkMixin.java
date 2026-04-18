package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping;

import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2818;
import net.minecraft.class_5558;
import net.minecraft.class_5562;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_2818.class)
public class LevelChunkMixin {
   @Inject(
      method = {
            "method_31719(Lnet/minecraft/class_2586;Lnet/minecraft/class_5558;Lnet/minecraft/class_2338;Lnet/minecraft/class_2818$class_5564;)Lnet/minecraft/class_2818$class_5564;",
            "lambda$updateBlockEntityTicker$6"
      },
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_31594(Lnet/minecraft/class_5562;)V"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void setBlockEntityTickingOrder(
      class_2586 blockEntity,
      class_5558<?> blockEntityTicker,
      class_2338 pos,
      @Coerce Object wrappedBlockEntityTickInvoker,
      CallbackInfoReturnable<?> cir,
      class_5562 blockEntityTickInvoker,
      @Coerce Object wrappedBlockEntityTickInvoker2
   ) {
      if (blockEntity instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.lithium$setTickWrapper((WrappedBlockEntityTickInvokerAccessor)wrappedBlockEntityTickInvoker2);
      }
   }

   @Inject(
      method = {
            "method_31719(Lnet/minecraft/class_2586;Lnet/minecraft/class_5558;Lnet/minecraft/class_2338;Lnet/minecraft/class_2818$class_5564;)Lnet/minecraft/class_2818$class_5564;",
            "lambda$updateBlockEntityTicker$6"
      },
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2818$class_5564;method_31727(Lnet/minecraft/class_5562;)V"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void setBlockEntityTickingOrder(
      class_2586 blockEntity,
      class_5558<?> blockEntityTicker,
      class_2338 pos,
      @Coerce Object wrappedBlockEntityTickInvoker,
      CallbackInfoReturnable<?> cir,
      class_5562 blockEntityTickInvoker
   ) {
      if (blockEntity instanceof SleepingBlockEntity sleepingBlockEntity) {
         sleepingBlockEntity.lithium$setTickWrapper((WrappedBlockEntityTickInvokerAccessor)wrappedBlockEntityTickInvoker);
      }
   }
}
