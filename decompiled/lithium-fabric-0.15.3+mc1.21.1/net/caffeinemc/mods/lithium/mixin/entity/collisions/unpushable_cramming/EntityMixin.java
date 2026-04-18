package net.caffeinemc.mods.lithium.mixin.entity.collisions.unpushable_cramming;

import net.caffeinemc.mods.lithium.common.entity.pushable.BlockCachingEntity;
import net.minecraft.class_1297;
import net.minecraft.class_2680;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1297.class)
public class EntityMixin implements BlockCachingEntity {
   @Shadow
   @Nullable
   private class_2680 field_47742;

   @Inject(method = "method_23327(DDD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4076;method_18675(I)I", ordinal = 0, shift = Shift.BEFORE))
   private void onPositionChanged(double x, double y, double z, CallbackInfo ci) {
      this.lithium$OnBlockCacheDeleted();
   }

   @Inject(method = "method_5670()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_5765()Z", ordinal = 0, shift = Shift.BEFORE))
   private void onBaseTick(CallbackInfo ci) {
      this.lithium$OnBlockCacheDeleted();
   }

   @Inject(
      method = "method_55667()Lnet/minecraft/class_2680;",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/class_1937;method_8320(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;",
         shift = Shift.AFTER
      )
   )
   private void onBlockCached(CallbackInfoReturnable<class_2680> cir) {
      this.lithium$OnBlockCacheSet(this.field_47742);
   }

   @Override
   public class_2680 lithium$getCachedFeetBlockState() {
      return this.field_47742;
   }
}
