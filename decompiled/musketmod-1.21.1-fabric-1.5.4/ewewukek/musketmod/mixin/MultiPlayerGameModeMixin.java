package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ClientUtilities;
import ewewukek.musketmod.GunItem;
import ewewukek.musketmod.ScopedMusketItem;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_636;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_636.class)
abstract class MultiPlayerGameModeMixin {
   @Inject(method = "interactAt", at = @At("HEAD"), cancellable = true)
   private void interactAtHead(class_1657 player, class_1297 entity, class_3966 hitResult, class_1268 hand, CallbackInfoReturnable<class_1269> ci) {
      if (ScopedMusketItem.isScoping) {
         ci.setReturnValue(class_1269.field_5814);
         ci.cancel();
      }
   }

   @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
   private void interactHead(class_1657 player, class_1297 entity, class_1268 hand, CallbackInfoReturnable<class_1269> ci) {
      if (ScopedMusketItem.isScoping) {
         ci.setReturnValue(class_1269.field_5814);
         ci.cancel();
      }
   }

   @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
   private void useItemOnHead(class_746 player, class_1268 hand, class_3965 hitResult, CallbackInfoReturnable<class_1269> ci) {
      if (ScopedMusketItem.isScoping) {
         ci.setReturnValue(class_1269.field_5814);
         ci.cancel();
      }
   }

   @Inject(method = "useItem", at = @At("HEAD"), cancellable = true)
   private void useItemHead(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> ci) {
      class_1799 stack = player.method_5998(hand);
      if (ClientUtilities.canUseScope && hand == class_1268.field_5808 && GunItem.isReady(stack)) {
         ClientUtilities.setScoping(player, true);
         if (ClientUtilities.attackKeyDown) {
            return;
         }
      }

      if (ScopedMusketItem.isScoping) {
         ci.setReturnValue(class_1269.field_5814);
         ci.cancel();
      }

      if (stack.method_7909() instanceof GunItem gun && GunItem.isReady(stack) && gun.canUseFrom(player, hand) && ClientUtilities.preventFiring) {
         ci.setReturnValue(class_1269.field_5814);
         ci.cancel();
      }
   }
}
