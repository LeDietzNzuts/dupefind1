package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.world.block_entity_ticking.support_cache;

import net.caffeinemc.mods.lithium.common.world.blockentity.SupportCache;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2586.class)
public abstract class BlockEntityMixin implements SupportCache {
   @Unique
   private boolean supportTestResult;

   @Shadow
   public abstract class_2591<?> method_11017();

   @Inject(method = "<init>(Lnet/minecraft/class_2591;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)V", at = @At("RETURN"))
   private void initSupportCache(class_2591<?> type, class_2338 pos, class_2680 cachedState, CallbackInfo ci) {
      this.supportTestResult = this.method_11017().method_20526(cachedState);
   }

   @Inject(method = "method_31664(Lnet/minecraft/class_2680;)V", at = @At("RETURN"))
   private void updateSupportCache(class_2680 cachedState, CallbackInfo ci) {
      this.supportTestResult = this.method_11017().method_20526(cachedState);
   }

   @Override
   public boolean lithium$isSupported() {
      return this.supportTestResult;
   }
}
