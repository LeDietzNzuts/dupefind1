package net.caffeinemc.mods.lithium.mixin.entity.inactive_navigations;

import net.caffeinemc.mods.lithium.common.entity.NavigatingEntity;
import net.caffeinemc.mods.lithium.common.world.ServerWorldExtended;
import net.minecraft.class_11;
import net.minecraft.class_1308;
import net.minecraft.class_1408;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1408.class)
public abstract class PathNavigationMixin {
   @Shadow
   @Final
   protected class_1937 field_6677;
   @Shadow
   protected class_11 field_6681;
   @Shadow
   @Final
   protected class_1308 field_6684;

   @Inject(
      method = "method_6356()V",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/class_1408;method_6348(Lnet/minecraft/class_2338;I)Lnet/minecraft/class_11;",
         shift = Shift.AFTER
      )
   )
   private void updateListeningState(CallbackInfo ci) {
      if (((NavigatingEntity)this.field_6684).lithium$isRegisteredToWorld()) {
         if (this.field_6681 == null) {
            ((ServerWorldExtended)this.field_6677).lithium$setNavigationInactive(this.field_6684);
         } else {
            ((ServerWorldExtended)this.field_6677).lithium$setNavigationActive(this.field_6684);
         }
      }
   }

   @Inject(method = "method_6334(Lnet/minecraft/class_11;D)Z", at = @At("RETURN"))
   private void updateListeningState2(class_11 path, double speed, CallbackInfoReturnable<Boolean> cir) {
      if (((NavigatingEntity)this.field_6684).lithium$isRegisteredToWorld()) {
         if (this.field_6681 == null) {
            ((ServerWorldExtended)this.field_6677).lithium$setNavigationInactive(this.field_6684);
         } else {
            ((ServerWorldExtended)this.field_6677).lithium$setNavigationActive(this.field_6684);
         }
      }
   }

   @Inject(method = "method_6340()V", at = @At("RETURN"))
   private void stopListening(CallbackInfo ci) {
      if (((NavigatingEntity)this.field_6684).lithium$isRegisteredToWorld()) {
         ((ServerWorldExtended)this.field_6677).lithium$setNavigationInactive(this.field_6684);
      }
   }
}
