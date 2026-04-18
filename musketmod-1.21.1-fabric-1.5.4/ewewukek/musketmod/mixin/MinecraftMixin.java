package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ClientUtilities;
import ewewukek.musketmod.GunItem;
import ewewukek.musketmod.Items;
import ewewukek.musketmod.ScopedMusketItem;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_310.class)
abstract class MinecraftMixin {
   @Shadow
   protected abstract void method_1583();

   @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
   private void startAttack(CallbackInfoReturnable<Boolean> ci) {
      if (ScopedMusketItem.isScoping) {
         this.method_1583();
         ci.cancel();
      }
   }

   @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
   private void continueAttack(CallbackInfo ci) {
      if (ScopedMusketItem.isScoping) {
         ci.cancel();
      }
   }

   @Inject(method = "handleKeybinds", at = @At("HEAD"))
   private void handleKeybinds(CallbackInfo ci) {
      class_310 client = (class_310)this;
      class_1657 player = client.field_1724;
      class_1799 stack = player.method_6047();
      boolean canUseScope = stack.method_7909() == Items.MUSKET_WITH_SCOPE && GunItem.canUse(player) && client.field_1690.method_31044().method_31034();
      if (player.method_6115()) {
         class_1799 usedStack = player.method_6030();
         int delay = canUseScope ? 10 : 5;
         if (usedStack.method_7909() instanceof GunItem && GunItem.isLoaded(usedStack) && player.method_6048() >= GunItem.reloadDuration(usedStack) + delay) {
            ClientUtilities.preventFiring = true;
            client.field_1761.method_2897(player);
         }
      }

      boolean canContinueScoping = client.field_1690.field_1904.method_1434() && (GunItem.isReady(stack) || client.field_1690.field_1886.method_1434());
      if (!canUseScope || !canContinueScoping) {
         ClientUtilities.setScoping(player, false);
      }

      ClientUtilities.canUseScope = canUseScope;
      ClientUtilities.attackKeyDown = client.field_1690.field_1886.method_1434();
      if (!client.field_1690.field_1904.method_1434()) {
         ClientUtilities.preventFiring = false;
      }
   }
}
