package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.crafter;

import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.lithium.common.block.entity.SetChangedHandlingBlockEntity;
import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.WrappedBlockEntityTickInvokerAccessor;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_5562;
import net.minecraft.class_8887;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_8887.class)
public class CrafterBlockEntityMixin extends class_2586 implements SleepingBlockEntity, SetChangedHandlingBlockEntity {
   @Shadow
   private int field_46818;
   @Unique
   private WrappedBlockEntityTickInvokerAccessor tickWrapper = null;
   @Unique
   private class_5562 sleepingTicker = null;

   public CrafterBlockEntityMixin(class_2591<?> blockEntityType, class_2338 blockPos, class_2680 blockState) {
      super(blockEntityType, blockPos, blockState);
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

   @Override
   public void lithium$setSleepingTicker(class_5562 sleepingTicker) {
      this.sleepingTicker = sleepingTicker;
   }

   @Inject(
      method = "method_54481(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_8887;)V",
      at = @At("RETURN")
   )
   private static void checkSleep(CallbackInfo ci, @Local(argsOnly = true) class_8887 crafterBlockEntity, @Local int remainingTicks) {
      if (remainingTicks < 0) {
         ((CrafterBlockEntityMixin)crafterBlockEntity).checkSleep();
      }
   }

   @Unique
   private void checkSleep() {
      if (this.field_46818 == 0) {
         this.lithium$startSleeping();
      }
   }

   @Inject(method = {"method_11014(Lnet/minecraft/class_2487;Lnet/minecraft/class_7225$class_7874;)V", "method_54484(I)V"}, at = @At("RETURN"))
   private void wakeUpAfterRemainingTicksChanged(CallbackInfo ci) {
      if (this.isSleeping() && this.field_11863 != null && !this.field_11863.field_9236) {
         this.wakeUpNow();
      }
   }

   @Override
   public void lithium$handleSetChanged() {
      if (this.isSleeping() && this.field_11863 != null && !this.field_11863.field_9236) {
         this.wakeUpNow();
      }
   }
}
