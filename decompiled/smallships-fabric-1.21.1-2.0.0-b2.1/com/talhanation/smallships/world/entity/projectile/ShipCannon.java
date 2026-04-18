package com.talhanation.smallships.world.entity.projectile;

import com.mojang.datafixers.util.Pair;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import com.talhanation.smallships.world.sound.ModSoundTypes;
import java.util.Objects;
import java.util.function.BiConsumer;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_3231;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_5819;
import net.minecraft.class_2945.class_9222;

public class ShipCannon extends class_1297 {
   private final class_5819 random;
   private final double offsetX;
   private final double offsetY;
   private final double offsetZ;
   private int time;
   private int coolDown;
   private final Ship ship;
   private final class_1937 level;
   private double angle;
   private boolean isRightSided;
   private boolean isLeftSided;

   public ShipCannon(Ship ship, Cannonable.CannonPosition cannonPosition) {
      this(ship, cannonPosition.x, cannonPosition.y, cannonPosition.z, cannonPosition.isRightSided, !cannonPosition.isRightSided);
   }

   public ShipCannon(Ship ship, double offsetX, double offsetY, double offsetZ, boolean isRightSided, boolean isLeftSided) {
      super(class_1299.field_6131, ship.method_37908());
      this.ship = ship;
      this.level = ship.method_37908();
      this.random = this.level.method_8409();
      this.offsetX = offsetX;
      this.offsetY = offsetY;
      this.offsetZ = offsetZ;
      this.resetTimer();
      this.coolDown = 0;
      if (isRightSided) {
         this.setRightSided();
      }

      if (isLeftSided) {
         this.setLeftSided();
      }
   }

   protected void method_5693(class_9222 builder) {
   }

   public void method_5773() {
      if (this.coolDown > 0) {
         this.coolDown--;
      }

      this.updatePosition();
   }

   protected void method_5749(class_2487 compoundTag) {
   }

   protected void method_5652(class_2487 compoundTag) {
   }

   public class_2596<class_2602> method_18002(class_3231 serverEntity) {
      return null;
   }

   public void trigger() {
      if (this.coolDown == 0) {
         if (this.time > 0) {
            this.time--;
         }

         if (this.time == 0) {
            this.shoot();
            this.resetTimer();
            this.setCoolDown();
         }
      }
   }

   public void trigger(class_243 shootVec, double yShootVec, class_1309 driverEntity, double speed, double accuracy) {
      if (this.coolDown == 0) {
         if (this.time > 0) {
            this.time--;
         }

         if (this.time == 0) {
            this.shoot(shootVec, yShootVec, driverEntity, speed, accuracy);
            this.resetTimer();
            this.setCoolDown();
         }
      }
   }

   public void updatePosition() {
      class_243 forward = this.ship.method_5663();
      float x0 = 0.0F;
      double f0 = Math.cos(this.ship.method_36454() * (float) (Math.PI / 180.0)) * x0;
      double f1 = Math.sin(this.ship.method_36454() * (float) (Math.PI / 180.0)) * x0;
      double f2 = this.getOffsetX();
      double d1 = this.ship.method_23317() - forward.field_1352 * f2 + f0;
      double d2 = this.ship.method_23318() - forward.field_1351 + this.getOffsetY();
      double d3 = this.ship.method_23321() - forward.field_1350 * f2 + f1;
      this.method_24203(d1, d2, d3);
   }

   private void resetTimer() {
      this.time = 10 + this.random.method_43048(10);
   }

   private void setCoolDown() {
      this.coolDown = 50;
   }

   public void shoot() {
      class_1309 driverEntity = this.ship.method_5642();
      if (driverEntity != null) {
         class_243 forward = this.ship.method_5663().method_1029();
         class_243 shootVec = this.getShootVector(forward, driverEntity);
         double speed = 2.6F;
         double accuracy = 3.0;
         boolean playerView = driverEntity.method_5720().field_1351 >= 0.0;
         double yShootVec = playerView ? shootVec.method_10214() + driverEntity.method_5720().field_1351 * 0.95F : shootVec.method_10214() + 0.15F;
         this.shoot(shootVec, yShootVec, driverEntity, speed, accuracy);
      }
   }

   public void shoot(class_243 shootVec, double yShootVec, class_1309 driverEntity, double speed, double accuracy) {
      if (shootVec != null) {
         CannonBallEntity cannonBallEntity = new CannonBallEntity(this.level, driverEntity, this.method_23317(), this.method_23318() + 1.0, this.method_23321());
         cannonBallEntity.method_7485(shootVec.method_10216(), yShootVec, shootVec.method_10215(), (float)speed, (float)accuracy);
         this.level.method_8649(cannonBallEntity);
         this.ship.method_5783(class_3417.field_15079, 1.0F, 1.125F);
         this.playCannonShotSound();
         if (this.ship instanceof Cannonable cannonable) {
            cannonable.consumeCannonBall();
         }
      }
   }

   public class_243 getShootVector(class_243 forward, class_1309 driver) {
      class_243 VecRight = forward.method_1024(-1.57F).method_1029();
      class_243 VecLeft = forward.method_1024(1.57F).method_1029();
      class_243 playerVec = driver.method_5720().method_1029();
      if (playerVec.method_1022(VecLeft) > playerVec.method_1022(VecRight)) {
         return VecRight;
      } else {
         return playerVec.method_1022(VecLeft) < playerVec.method_1022(VecRight) ? VecLeft : null;
      }
   }

   public double getOffsetX() {
      return this.offsetX;
   }

   public double getOffsetY() {
      return this.offsetY;
   }

   public double getOffsetZ() {
      return this.offsetZ;
   }

   public float getAngle() {
      return (float)this.angle;
   }

   public void setAngle(double angle) {
      this.angle = angle;
   }

   public void setLeftSided() {
      this.isLeftSided = true;
      this.setAngle(0.0);
   }

   public void setRightSided() {
      this.isRightSided = true;
      this.setAngle(180.0);
   }

   public boolean isRightSided() {
      return this.isRightSided;
   }

   public boolean canShootDirection() {
      class_1309 driver = this.ship.method_5642();
      if (driver == null) {
         return false;
      } else {
         class_243 forward = this.ship.method_5663().method_1029();
         class_243 shootVec = this.getShootVector(forward, driver);
         class_243 VecRight = forward.method_1024(-1.57F).method_1029();
         class_243 VecLeft = forward.method_1024(1.57F).method_1029();
         return this.isRightSided && Objects.equals(shootVec, VecRight) ? true : this.isLeftSided && Objects.equals(shootVec, VecLeft);
      }
   }

   public class_2487 getData() {
      class_2487 compoundtag = new class_2487();
      compoundtag.method_10549("x", this.getOffsetX());
      compoundtag.method_10549("y", this.getOffsetY());
      compoundtag.method_10549("z", this.getOffsetZ());
      compoundtag.method_10556("isRightSided", this.isRightSided());
      return compoundtag;
   }

   private void playCannonShotSound() {
      BiConsumer<class_3414, Pair<Float, Float>> play = (sound, modifier) -> {
         if (!this.ship.method_37908().method_8608()) {
            this.ship.method_5783(sound, (Float)modifier.getFirst(), (Float)modifier.getSecond());
         } else {
            this.ship
               .method_37908()
               .method_8486(
                  this.ship.method_23317(),
                  this.ship.method_23318() + 4.0,
                  this.ship.method_23321(),
                  sound,
                  this.ship.method_5634(),
                  (Float)modifier.getFirst(),
                  (Float)modifier.getSecond(),
                  false
               );
         }
      };
      play.accept(ModSoundTypes.CANNON_SHOT, Pair.of(10.0F, 1.0F));
   }
}
