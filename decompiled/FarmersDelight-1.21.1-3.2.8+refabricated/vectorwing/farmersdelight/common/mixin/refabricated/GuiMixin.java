package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.class_1657;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.gui.HUDOverlays;

@Mixin(value = class_329.class, priority = 999)
public class GuiMixin {
   @Unique
   private class_9779 farmersdelightrefabricated$deltaTracker;

   @Inject(method = "method_55805(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V", at = @At("HEAD"))
   private void farmersdelightrefabricated$captureDeltaTracker(class_332 guiGraphics, class_9779 deltaTracker, CallbackInfo ci) {
      this.farmersdelightrefabricated$deltaTracker = deltaTracker;
   }

   @Inject(method = "method_55805(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V", at = @At("TAIL"))
   private void farmersdelightrefabricated$clearDeltaTracker(class_332 guiGraphics, class_9779 deltaTracker, CallbackInfo ci) {
      this.farmersdelightrefabricated$deltaTracker = null;
   }

   @Inject(method = "method_37298(Lnet/minecraft/class_332;Lnet/minecraft/class_1657;IIIIFIIIZ)V", at = @At("TAIL"))
   private void farmersdelightrefabricated$renderHearts(
      class_332 guiGraphics,
      class_1657 player,
      int x,
      int y,
      int height,
      int offsetHeartIndex,
      float maxHealth,
      int currentHealth,
      int displayHealth,
      int absorptionAmount,
      boolean renderHighlight,
      CallbackInfo ci
   ) {
      HUDOverlays.ComfortOverlay.INSTANCE.render(guiGraphics, this.farmersdelightrefabricated$deltaTracker);
   }

   @Inject(method = "method_58477(Lnet/minecraft/class_332;Lnet/minecraft/class_1657;II)V", at = @At("TAIL"))
   private void farmersdelightrefabricated$renderNourishment(class_332 guiGraphics, class_1657 player, int y, int x, CallbackInfo ci) {
      HUDOverlays.NourishmentOverlay.INSTANCE.render(guiGraphics, this.farmersdelightrefabricated$deltaTracker);
   }
}
