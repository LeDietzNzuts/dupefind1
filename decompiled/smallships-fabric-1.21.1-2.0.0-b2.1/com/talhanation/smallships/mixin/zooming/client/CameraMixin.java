package com.talhanation.smallships.mixin.zooming.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.duck.CameraZoomAccess;
import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_1297;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_4184.class)
public abstract class CameraMixin implements CameraZoomAccess {
   @Unique
   private float smallships$shipZoom = 6.0F;

   @Shadow
   public abstract class_1297 method_19331();

   @ModifyExpressionValue(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getMaxZoom(F)F"))
   private float setupShipZoom(float original) {
      if (!(Boolean)SmallShipsConfig.Client.shipGeneralCameraZoomEnable.get()) {
         return original;
      } else {
         return this.method_19331().method_5854() instanceof Ship && !class_310.method_1551().field_1690.method_31044().method_31034()
            ? original * (this.smallships$getShipZoomData() - 4.0F)
            : original;
      }
   }

   @Unique
   @Override
   public float smallships$getShipZoomData() {
      return this.smallships$shipZoom;
   }

   @Unique
   @Override
   public void smallships$setShipZoomData(float d) {
      this.smallships$shipZoom = d;
   }
}
