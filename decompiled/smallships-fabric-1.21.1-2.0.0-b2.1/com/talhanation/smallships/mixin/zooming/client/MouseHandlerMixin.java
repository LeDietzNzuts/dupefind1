package com.talhanation.smallships.mixin.zooming.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.duck.CameraZoomAccess;
import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_1661;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_312.class)
public class MouseHandlerMixin {
   @Shadow
   @Final
   private class_310 field_1779;
   @Unique
   private boolean smallships$shouldCancel;

   @Inject(
      method = "onScroll(JDD)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;swapPaint(D)V", shift = Shift.BEFORE),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void onScrollCaptureScrollDelta(
      long windowPointer, double xOffset, double yOffset, CallbackInfo ci, boolean bl, double scrollSensitivity, double scrollDeltaX, double scrollDeltaY
   ) {
      if ((Boolean)SmallShipsConfig.Client.shipGeneralCameraZoomEnable.get()) {
         assert this.field_1779.field_1724 != null;

         if (!this.field_1779.field_1690.method_31044().method_31034() && this.field_1779.field_1724.method_5854() instanceof Ship) {
            class_4184 camera = this.field_1779.field_1773.method_19418();
            float shipZoom = Math.min(
               ((Double)SmallShipsConfig.Client.shipGeneralCameraZoomMax.get()).floatValue(),
               Math.max(
                  ((Double)SmallShipsConfig.Client.shipGeneralCameraZoomMin.get()).floatValue(),
                  ((CameraZoomAccess)camera).smallships$getShipZoomData() - (float)scrollDeltaY / 5.0F
               )
            );
            ((CameraZoomAccess)camera).smallships$setShipZoomData(shipZoom);
            this.smallships$shouldCancel = true;
         }
      }
   }

   @WrapWithCondition(method = "onScroll(JDD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;swapPaint(D)V"))
   private boolean cancelScrollApplyInventoryPaint(class_1661 instance, double direction) {
      boolean shouldContinue = !this.smallships$shouldCancel;
      this.smallships$shouldCancel = false;
      return shouldContinue;
   }
}
