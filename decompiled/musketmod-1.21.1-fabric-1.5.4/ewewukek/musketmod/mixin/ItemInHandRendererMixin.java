package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ClientUtilities;
import ewewukek.musketmod.GunItem;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_759.class)
abstract class ItemInHandRendererMixin {
   @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
   private void renderArmWithItem(
      class_742 player,
      float tickDelta,
      float pitch,
      class_1268 hand,
      float swingProgress,
      class_1799 stack,
      float equipProgress,
      class_4587 matrices,
      class_4597 vertexConsumers,
      int light,
      CallbackInfo ci
   ) {
      if (stack.method_7909() instanceof GunItem) {
         ClientUtilities.renderGunInHand((class_759)this, player, hand, tickDelta, pitch, swingProgress, equipProgress, stack, matrices, vertexConsumers, light);
         ci.cancel();
      }
   }
}
