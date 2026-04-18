package com.talhanation.smallships.mixin.entity;

import com.talhanation.smallships.world.entity.IMixinEntity;
import com.talhanation.smallships.world.entity.cannon.Cannon;
import com.talhanation.smallships.world.entity.projectile.ICannonProjectile;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1297.class)
public abstract class EntityMixin implements IMixinEntity, ICannonProjectile {
   private float prevXRot;
   private float prevYRot;
   @Shadow
   private float field_5965;
   @Shadow
   private float field_6031;
   @Shadow
   private class_1937 field_6002;
   private boolean preventNextPassengerSyncTeleport;
   private boolean preventDismountingToCoordinates;

   @Shadow
   public abstract void method_5848();

   @Inject(method = "turn", at = @At("HEAD"))
   public void turn(double x, double y, CallbackInfo ci) {
      this.prevXRot = this.field_5965;
      this.prevYRot = this.field_6031;
   }

   @Override
   public float getPrevXRot() {
      return this.prevXRot;
   }

   @Override
   public float getPrevYRot() {
      return this.prevYRot;
   }

   @Override
   public void shootAndSpawn(Cannon cannon, Vector3d startPos, Vector3f direction, float cannonSpeedMultiplier, float cannonAccuracy, class_1297 shooter) {
      if (!this.field_6002.method_8608()) {
         class_1297 thisEntity = (class_1297)this;
         this.setPreventTeleportOnNextPassengerSync(true);
         this.setPreventDismountToCoordinates(true);
         this.method_5848();
         direction.mul(cannonSpeedMultiplier * 3.0F);
         thisEntity.method_5814(startPos.x, startPos.y, startPos.z);
         thisEntity.method_18800(direction.x, direction.y, direction.z);
         thisEntity.field_6007 = true;
         if (thisEntity instanceof class_1657 player) {
            player.method_40126(40, 8.0F, null);
         }

         thisEntity.field_6037 = true;
      }
   }

   @Override
   public boolean doNotTeleportOnNextPassengerSync() {
      return this.preventNextPassengerSyncTeleport;
   }

   @Override
   public void setPreventTeleportOnNextPassengerSync(boolean prevent) {
      this.preventNextPassengerSyncTeleport = prevent;
   }

   @Override
   public boolean doNotDismountToCoordinates() {
      return this.preventDismountingToCoordinates;
   }

   @Override
   public void setPreventDismountToCoordinates(boolean prevent) {
      this.preventDismountingToCoordinates = prevent;
   }
}
