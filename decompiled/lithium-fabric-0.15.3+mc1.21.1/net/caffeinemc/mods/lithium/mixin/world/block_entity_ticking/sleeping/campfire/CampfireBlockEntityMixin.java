package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.campfire;

import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.WrappedBlockEntityTickInvokerAccessor;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_3924;
import net.minecraft.class_5562;
import net.minecraft.class_7225.class_7874;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3924.class)
public class CampfireBlockEntityMixin extends class_2586 implements SleepingBlockEntity {
   private WrappedBlockEntityTickInvokerAccessor tickWrapper = null;
   private class_5562 sleepingTicker = null;

   public CampfireBlockEntityMixin(class_2591<?> type, class_2338 pos, class_2680 state) {
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
      method = "method_17503(Lnet/minecraft/class_1309;Lnet/minecraft/class_1799;I)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2371;set(ILjava/lang/Object;)Ljava/lang/Object;")
   )
   private void wakeUpOnAddItem(class_1309 user, class_1799 stack, int cookTime, CallbackInfoReturnable<Boolean> cir) {
      this.wakeUpNow();
   }

   @Inject(method = "method_11014(Lnet/minecraft/class_2487;Lnet/minecraft/class_7225$class_7874;)V", at = @At("RETURN"))
   private void wakeUpOnReadNbt(class_2487 nbt, class_7874 registryLookup, CallbackInfo ci) {
      this.wakeUpNow();
   }
}
