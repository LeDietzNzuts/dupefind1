package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.hopper;

import java.util.function.BooleanSupplier;
import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.WrappedBlockEntityTickInvokerAccessor;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2377;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2614;
import net.minecraft.class_2680;
import net.minecraft.class_5562;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2614.class)
public class HopperBlockEntityMixin extends class_2586 implements SleepingBlockEntity {
   @Shadow
   private long field_12022;
   private WrappedBlockEntityTickInvokerAccessor tickWrapper = null;
   private class_5562 sleepingTicker = null;

   @Shadow
   private native boolean method_11239();

   @Inject(
      method = "method_11243(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2614;Ljava/util/function/BooleanSupplier;)Z",
      at = @At(value = "RETURN", ordinal = 2)
   )
   private static void sleepIfNoCooldownAndLocked(
      class_1937 world, class_2338 pos, class_2680 state, class_2614 blockEntity, BooleanSupplier booleanSupplier, CallbackInfoReturnable<Boolean> cir
   ) {
      if (!((HopperBlockEntityMixin)blockEntity).method_11239()
         && !((HopperBlockEntityMixin)blockEntity).isSleeping()
         && !(Boolean)state.method_11654(class_2377.field_11126)) {
         ((HopperBlockEntityMixin)blockEntity).lithium$startSleeping();
      }
   }

   @Override
   public WrappedBlockEntityTickInvokerAccessor lithium$getTickWrapper() {
      return this.tickWrapper;
   }

   @Override
   public void lithium$setTickWrapper(WrappedBlockEntityTickInvokerAccessor tickWrapper) {
      this.tickWrapper = tickWrapper;
      this.lithium$setSleepingTicker(null);
   }

   @Override
   public class_5562 lithium$getSleepingTicker() {
      return this.sleepingTicker;
   }

   public HopperBlockEntityMixin(class_2591<?> type, class_2338 pos, class_2680 state) {
      super(type, pos, state);
   }

   @Override
   public void lithium$setSleepingTicker(class_5562 sleepingTicker) {
      this.sleepingTicker = sleepingTicker;
   }

   @Override
   public boolean lithium$startSleeping() {
      if (this.isSleeping()) {
         return false;
      } else {
         WrappedBlockEntityTickInvokerAccessor tickWrapper = this.lithium$getTickWrapper();
         if (tickWrapper != null) {
            this.lithium$setSleepingTicker(tickWrapper.getWrapped());
            tickWrapper.callSetWrapped(SleepingBlockEntity.SLEEPING_BLOCK_ENTITY_TICKER);
            this.field_12022 = Long.MAX_VALUE;
            return true;
         } else {
            return false;
         }
      }
   }

   @Inject(method = "method_11238(I)V", at = @At("HEAD"))
   private void wakeUpOnCooldownSet(int transferCooldown, CallbackInfo ci) {
      if (transferCooldown == 7) {
         if (this.field_12022 == Long.MAX_VALUE) {
            this.sleepOnlyCurrentTick();
         } else {
            this.wakeUpNow();
         }
      } else if (transferCooldown > 0 && this.sleepingTicker != null) {
         this.wakeUpNow();
      }
   }
}
