package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.shulker_box;

import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.WrappedBlockEntityTickInvokerAccessor;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2627;
import net.minecraft.class_2680;
import net.minecraft.class_5562;
import net.minecraft.class_2627.class_2628;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2627.class)
public class ShulkerBoxBlockEntityMixin implements SleepingBlockEntity {
   @Shadow
   private class_2628 field_12057;
   @Shadow
   private float field_12056;
   @Shadow
   private float field_12055;
   private WrappedBlockEntityTickInvokerAccessor tickWrapper = null;
   private class_5562 sleepingTicker = null;

   @Override
   public WrappedBlockEntityTickInvokerAccessor lithium$getTickWrapper() {
      return this.tickWrapper;
   }

   @Override
   public void lithium$setTickWrapper(WrappedBlockEntityTickInvokerAccessor tickWrapper) {
      this.tickWrapper = tickWrapper;
   }

   @Override
   public class_5562 lithium$getSleepingTicker() {
      return this.sleepingTicker;
   }

   @Override
   public void lithium$setSleepingTicker(class_5562 sleepingTicker) {
      this.sleepingTicker = sleepingTicker;
   }

   @Inject(method = "method_11004(II)Z", at = @At("HEAD"))
   private void wakeUpOnSyncedBlockEvent(int type, int data, CallbackInfoReturnable<Boolean> cir) {
      if (this.sleepingTicker != null) {
         this.wakeUpNow();
      }
   }

   @Inject(method = "method_11318(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)V", at = @At("RETURN"))
   private void sleepOnAnimationEnd(class_1937 world, class_2338 pos, class_2680 state, CallbackInfo ci) {
      if (this.field_12057 == class_2628.field_12065 && this.field_12055 == 0.0F && this.field_12056 == 0.0F) {
         this.lithium$startSleeping();
      }
   }
}
