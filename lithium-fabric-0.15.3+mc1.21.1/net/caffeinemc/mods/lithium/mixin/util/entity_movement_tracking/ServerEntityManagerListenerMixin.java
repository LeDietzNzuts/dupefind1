package net.caffeinemc.mods.lithium.mixin.util.entity_movement_tracking;

import net.caffeinemc.mods.lithium.common.tracking.entity.EntityMovementTrackerSection;
import net.caffeinemc.mods.lithium.common.tracking.entity.MovementTrackerHelper;
import net.caffeinemc.mods.lithium.common.tracking.entity.ToggleableMovementTracker;
import net.minecraft.class_1297;
import net.minecraft.class_5568;
import net.minecraft.class_5572;
import net.minecraft.class_5579;
import net.minecraft.class_1297.class_5529;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/class_5579$class_5580")
public class ServerEntityManagerListenerMixin<T extends class_5568> implements ToggleableMovementTracker {
   @Shadow
   private class_5572<T> field_27274;
   @Shadow
   @Final
   private T field_27272;
   @Unique
   private int notificationMask;

   @Inject(method = "<init>(Lnet/minecraft/class_5579;Lnet/minecraft/class_5568;JLnet/minecraft/class_5572;)V", at = @At("RETURN"))
   private void init(class_5579<?> outer, T entityLike, long l, class_5572<T> entityTrackingSection, CallbackInfo ci) {
      this.notificationMask = MovementTrackerHelper.getNotificationMask((class_1297)this.field_27272);
      this.notifyMovementListeners();
   }

   @Inject(method = "method_31749()V", at = @At("RETURN"))
   private void updateEntityTrackerEngine(CallbackInfo ci) {
      this.notifyMovementListeners();
   }

   @Inject(
      method = "method_31749()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5572;method_31764(Lnet/minecraft/class_5568;)V", shift = Shift.AFTER)
   )
   private void onAddEntity(CallbackInfo ci) {
      this.notifyMovementListeners();
   }

   @Inject(method = "method_31750(Lnet/minecraft/class_1297$class_5529;)V", at = @At("HEAD"))
   private void onRemoveEntity(class_5529 reason, CallbackInfo ci) {
      this.notifyMovementListeners();
   }

   @Unique
   private void notifyMovementListeners() {
      if (this.notificationMask != 0) {
         ((EntityMovementTrackerSection)this.field_27274)
            .lithium$trackEntityMovement(this.notificationMask, ((class_1297)this.field_27272).method_5770().method_8510());
      }
   }

   @Override
   public int lithium$setNotificationMask(int notificationMask) {
      int oldNotificationMask = this.notificationMask;
      this.notificationMask = notificationMask;
      return oldNotificationMask;
   }
}
