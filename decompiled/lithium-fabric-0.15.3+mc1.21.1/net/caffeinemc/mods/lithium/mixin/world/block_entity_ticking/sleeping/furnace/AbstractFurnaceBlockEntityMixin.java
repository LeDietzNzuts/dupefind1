package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.furnace;

import net.caffeinemc.mods.lithium.common.block.entity.SetChangedHandlingBlockEntity;
import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.WrappedBlockEntityTickInvokerAccessor;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2609;
import net.minecraft.class_2680;
import net.minecraft.class_5562;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2609.class)
public abstract class AbstractFurnaceBlockEntityMixin extends class_2586 implements SleepingBlockEntity, SetChangedHandlingBlockEntity {
   @Shadow
   int field_11989;
   private WrappedBlockEntityTickInvokerAccessor tickWrapper = null;
   private class_5562 sleepingTicker = null;

   @Shadow
   protected abstract boolean method_11201();

   public AbstractFurnaceBlockEntityMixin(class_2591<?> type, class_2338 pos, class_2680 state) {
      super(type, pos, state);
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
      method = "method_31651(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2609;)V",
      at = @At("RETURN")
   )
   private static void checkSleep(class_1937 world, class_2338 pos, class_2680 state, class_2609 blockEntity, CallbackInfo ci) {
      ((AbstractFurnaceBlockEntityMixin)blockEntity).checkSleep(state);
   }

   private void checkSleep(class_2680 state) {
      if (!this.method_11201()
         && this.field_11989 == 0
         && (state.method_27852(class_2246.field_10181) || state.method_27852(class_2246.field_16333) || state.method_27852(class_2246.field_16334))
         && this.field_11863 != null) {
         this.lithium$startSleeping();
      }
   }

   @Inject(method = "method_11014(Lnet/minecraft/class_2487;Lnet/minecraft/class_7225$class_7874;)V", at = @At("RETURN"))
   private void wakeUpAfterFromTag(CallbackInfo ci) {
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
