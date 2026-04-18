package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.common.tracking.entity.ToggleableMovementTracker;
import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1688;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_5569;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1688.class)
public abstract class AbstractMinecartMixin extends class_1297 {
   private class_243 beforeMoveOnRailPos;
   private int beforeMoveOnRailNotificationMask;

   public AbstractMinecartMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Inject(method = "method_7513(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)V", at = @At("HEAD"))
   private void avoidNotifyingMovementListeners(class_2338 pos, class_2680 state, CallbackInfo ci) {
      if (this instanceof class_1263) {
         this.beforeMoveOnRailPos = this.method_19538();
         if (((EntityAccessor)this).getChangeListener() instanceof ToggleableMovementTracker toggleableMovementTracker) {
            this.beforeMoveOnRailNotificationMask = toggleableMovementTracker.lithium$setNotificationMask(0);
         }
      }
   }

   @Inject(method = "method_7513(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)V", at = @At("RETURN"))
   private void notifyMovementListeners(class_2338 pos, class_2680 state, CallbackInfo ci) {
      if (this instanceof class_1263) {
         class_5569 changeListener = ((EntityAccessor)this).getChangeListener();
         if (changeListener instanceof ToggleableMovementTracker toggleableMovementTracker) {
            this.beforeMoveOnRailNotificationMask = toggleableMovementTracker.lithium$setNotificationMask(this.beforeMoveOnRailNotificationMask);
            if (!this.beforeMoveOnRailPos.equals(this.method_19538())) {
               changeListener.method_31749();
            }
         }

         this.beforeMoveOnRailPos = null;
      }
   }
}
