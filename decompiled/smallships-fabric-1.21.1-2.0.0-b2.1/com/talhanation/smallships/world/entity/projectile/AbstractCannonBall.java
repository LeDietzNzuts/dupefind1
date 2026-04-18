package com.talhanation.smallships.world.entity.projectile;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.world.entity.cannon.Cannon;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.particles.ModParticleTypes;
import com.talhanation.smallships.world.sound.ModSoundTypes;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1668;
import net.minecraft.class_1675;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_1297.class_5529;
import net.minecraft.class_1937.class_7867;
import net.minecraft.class_239.class_240;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.joml.Vector3f;

public abstract class AbstractCannonBall extends class_1668 implements ICannonProjectile {
   public boolean inWater = false;
   public boolean wasShot = false;
   public int counter = 0;

   public AbstractCannonBall(class_1299<? extends AbstractCannonBall> type, class_1937 world) {
      super(type, world);
   }

   public AbstractCannonBall(class_1299<? extends AbstractCannonBall> type, class_1309 owner, double d1, double d2, double d3, class_1937 world) {
      super(type, owner, new class_243(d1, d2, d3), world);
      this.method_5808(d1, d2, d3, this.method_36454(), this.method_36455());
   }

   @Override
   public void shootAndSpawn(Cannon cannon, Vector3d startPos, Vector3f direction, float cannonSpeedMultiplier, float cannonAccuracy, class_1297 shooter) {
      Vector3f deltaMovement = direction.normalize().mul((float)this.field_51893);
      this.method_7432(shooter);
      this.method_5808(startPos.x, startPos.y, startPos.z, this.method_36454(), this.method_36455());
      this.method_23311();
      this.method_18800(deltaMovement.x, deltaMovement.y, deltaMovement.z);
      this.field_6007 = true;
      this.method_7485(direction.x(), direction.y(), direction.z(), cannonSpeedMultiplier, cannonAccuracy);
      this.method_37908().method_8649(this);
   }

   @Override
   public class_2394 getAdditionalCannonShootParticles() {
      return (class_2394)ModParticleTypes.CANNON_BALL_SHOOT.get();
   }

   public void method_5773() {
      this.method_5670();
      class_243 vector3d = this.method_18798();
      class_239 raytraceresult = class_1675.method_49997(this, x$0 -> this.method_26958(x$0));
      if (raytraceresult.method_17783() != class_240.field_1333) {
         this.method_7488(raytraceresult);
      }

      double d0 = this.method_23317() + vector3d.field_1352;
      double d1 = this.method_23318() + vector3d.field_1351;
      double d2 = this.method_23321() + vector3d.field_1350;
      this.method_26962();
      float f = 0.99F;
      float f1 = 0.06F;
      float f2 = -0.05F;
      this.method_18799(vector3d.method_1021(f));
      if (!this.method_5740()) {
         this.method_18799(this.method_18798().method_1031(0.0, -f1, 0.0));
      }

      this.method_5814(d0, d1, d2);
      if (this.method_5805()) {
         this.setWasShot(true);
      }

      if (this.method_5799()) {
         if (this.method_37908().method_8608() && !this.method_5869()) {
            this.waterParticles();
         }

         this.method_18799(this.method_18798().method_1031(0.0, -f2, 0.0));
         this.setInWater(true);
      }

      if (this.method_37908().method_8608()) {
         this.tailParticles();
      }

      if (this.method_5799() && this.counter > 200) {
         this.method_31472();
      }

      if (this.wasShot) {
         this.counter++;
      }
   }

   public void setWasShot(boolean bool) {
      if (bool != this.wasShot) {
         this.wasShot = true;
         boolean spawnedFromShip = this.method_24921() != null && this.method_24921().method_5854() instanceof Ship;
         if (this.method_37908().method_8608() && spawnedFromShip) {
            this.shootParticles();
         }
      }
   }

   public void setInWater(boolean bool) {
      if (bool != this.inWater) {
         this.method_37908()
            .method_43128(
               null,
               this.method_23317(),
               this.method_23318(),
               this.method_23321(),
               class_3417.field_14737,
               class_3419.field_15245,
               3.3F,
               0.8F + 0.4F * this.field_5974.method_43057()
            );
         this.inWater = true;
      }
   }

   protected void method_24920(class_3965 blockHitResult) {
      super.method_24920(blockHitResult);
      if (!this.method_37908().method_8608()) {
         boolean doesSpreadFire = false;
         if (!this.method_5799()) {
            this.method_37908()
               .method_8537(
                  this.method_24921(),
                  this.method_23317(),
                  this.method_23318(),
                  this.method_23321(),
                  ((Double)SmallShipsConfig.Common.shipGeneralCannonDestruction.get()).floatValue(),
                  doesSpreadFire,
                  class_7867.field_40890
               );
         }

         this.method_5650(class_5529.field_26998);
      }
   }

   protected void method_7488(class_239 hitResult) {
      super.method_7488(hitResult);
      this.hitParticles();
   }

   protected void method_7454(class_3966 hitResult) {
      super.method_7454(hitResult);
      if (!this.method_37908().method_8608()) {
         class_1297 hitEntity = hitResult.method_17782();
         class_1297 ownerEntity = this.method_24921();
         if (hitEntity instanceof Ship shipHitEntity) {
            shipHitEntity.method_5643(this.method_48923().method_48811(this, ownerEntity), this.field_5974.method_43048(7) + 7);
            this.method_37908()
               .method_43128(
                  null,
                  this.method_23317(),
                  this.method_23318() + 4.0,
                  this.method_23321(),
                  ModSoundTypes.SHIP_HIT,
                  this.method_5634(),
                  3.3F,
                  0.8F + 0.4F * this.field_5974.method_43057()
               );
         } else if (ownerEntity instanceof class_1309 livingOwnerEntity) {
            if (ownerEntity.method_5781() != null && ownerEntity.method_5781().method_1206(hitEntity.method_5781()) && !ownerEntity.method_5781().method_1205()
               )
             {
               return;
            }

            this.method_37908()
               .method_43128(
                  null,
                  this.method_23317(),
                  this.method_23318() + 4.0,
                  this.method_23321(),
                  (class_3414)class_3417.field_15152.comp_349(),
                  this.method_5634(),
                  3.3F,
                  0.8F + 0.4F * this.field_5974.method_43057()
               );
         }

         hitEntity.method_5643(
            this.method_48923().method_48811(this, ownerEntity), ((Double)SmallShipsConfig.Common.shipGeneralCannonDamage.get()).floatValue()
         );
      }
   }

   public void hitParticles() {
      for (int i = 0; i < 300; i++) {
         double d0 = this.field_5974.method_43059() * 0.03;
         double d1 = this.field_5974.method_43059() * 0.03;
         double d2 = this.field_5974.method_43059() * 0.03;
         double d3 = 20.0;
         this.method_37908()
            .method_8406(class_2398.field_11203, this.method_23316(1.0) - d0 * d3, this.method_23319() - d1 * d3, this.method_23325(2.0) - d2 * d3, d0, d1, d2);
         this.method_37908()
            .method_8406(class_2398.field_11237, this.method_23316(1.0) - d0 * d3, this.method_23319() - d1 * d3, this.method_23325(2.0) - d2 * d3, d0, d1, d2);
      }
   }

   public void waterParticles() {
      for (int i = 0; i < 200; i++) {
         double d0 = this.field_5974.method_43059() * 0.03;
         double d1 = this.field_5974.method_43059() * 0.03;
         double d2 = this.field_5974.method_43059() * 0.03;
         double d3 = 20.0;
         this.method_37908()
            .method_8406(
               class_2398.field_11203,
               this.method_23316(1.0) - d0 * d3,
               this.method_23319() - d1 * d3 + i * 0.012,
               this.method_23325(2.0) - d2 * d3,
               d0,
               d1,
               d2
            );
      }
   }

   public void shootParticles() {
      boolean spawnedFromShip = this.method_24921() != null && this.method_24921().method_5854() instanceof Ship;
      Vector3d prevPos = new Vector3d(this.field_6038, this.field_5971, this.field_5989);
      Vector3d movement = new Vector3d(this.method_23317(), this.method_23318(), this.method_23321()).sub(prevPos);

      for (int i = 0; i < 100; i++) {
         double d0 = this.field_5974.method_43059() * 0.03;
         double d1 = this.field_5974.method_43059() * 0.03;
         double d2 = this.field_5974.method_43059() * 0.03;
         double d3 = 20.0;
         Vector3d particlePos = new Vector3d(this.method_23316(1.0) - d0 * d3, this.method_23319() - d1 * d3, this.method_23325(2.0) - d2 * d3);
         if (!spawnedFromShip) {
            particlePos.sub(movement);
         }

         this.method_37908().method_8406(class_2398.field_11203, particlePos.x, particlePos.y, particlePos.z, d0, d1, d2);
      }

      for (int i = 0; i < 50; i++) {
         double d00 = this.field_5974.method_43059() * 0.03;
         double d11 = this.field_5974.method_43059() * 0.03;
         double d22 = this.field_5974.method_43059() * 0.03;
         double d44 = 10.0;
         Vector3d particlePos = new Vector3d(this.method_23316(1.0) - d00 * d44, this.method_23319() - d11 * d44, this.method_23325(2.0) - d22 * d44);
         if (!spawnedFromShip) {
            particlePos.sub(movement);
         }

         this.method_37908().method_8406(class_2398.field_11237, particlePos.x, particlePos.y, particlePos.z, d00, d11, d22);
         this.method_37908().method_8406(class_2398.field_11240, particlePos.x, particlePos.y, particlePos.z, 0.0, 0.0, 0.0);
      }
   }

   public void tailParticles() {
      boolean spawnedFromShip = this.method_24921() != null && this.method_24921().method_5854() instanceof Ship;
      int maxShotCounter = spawnedFromShip ? 2 : 3;
      int shipOffsetCounter = 2;
      if (!spawnedFromShip || this.counter >= 2) {
         int counter = this.counter - (spawnedFromShip ? 2 : 0);
         Vector3d prevPos = new Vector3d(this.field_6038, this.field_5971, this.field_5989);
         Vector3d pos = new Vector3d(this.method_23317(), this.method_23318(), this.method_23321());
         Vector3d speed = new Vector3d(pos).sub(prevPos).mul(0.025F);
         int totalSteps = 6;
         int numPoofParticlesPerStep = 25;
         int numFlameParticlesPerStep = 16;

         for (int step = 1; step <= 6; step++) {
            float partialStep = step / 6.0F;
            Vector3d lerp = new Vector3d(prevPos).lerp(pos, partialStep);
            if (counter < maxShotCounter && spawnedFromShip) {
               for (int i = 0; i < 25; i++) {
                  float counterStep = counter == 0 ? 0.0F : (counter + partialStep) / maxShotCounter;
                  double xRand = this.field_5974.method_43059() * counterStep * 0.08F;
                  double yRand = this.field_5974.method_43059() * counterStep * 0.08F;
                  double zRand = this.field_5974.method_43059() * counterStep * 0.08F;
                  this.method_37908().method_8406(class_2398.field_11203, lerp.x, lerp.y, lerp.z, xRand, yRand, zRand);
               }

               for (int i = 0; i < 16; i++) {
                  double radius = this.method_17681() / 2.0F;
                  double xRand = this.field_5974.method_43059() * radius;
                  double yRand = this.field_5974.method_43059() * radius;
                  double zRand = this.field_5974.method_43059() * radius;
                  this.method_37908().method_8406(class_2398.field_11240, lerp.x + xRand, lerp.y + yRand, lerp.z + zRand, speed.x, speed.y, speed.z);
               }
            }

            this.method_37908().method_8406(class_2398.field_11203, lerp.x, lerp.y, lerp.z, 0.0, 0.0, 0.0);
         }
      }
   }

   public boolean method_5863() {
      return false;
   }

   public boolean method_5643(class_1282 p_70097_1_, float p_70097_2_) {
      return false;
   }

   protected boolean method_7468() {
      return false;
   }

   @NotNull
   protected class_2394 method_7467() {
      return class_2398.field_11251;
   }
}
