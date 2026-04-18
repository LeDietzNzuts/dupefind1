package com.talhanation.smallships.world.entity.cannon;

import com.mojang.datafixers.util.Pair;
import com.talhanation.smallships.utils.ServerParticleUtils;
import com.talhanation.smallships.utils.VectorMath;
import com.talhanation.smallships.world.entity.projectile.ICannonProjectile;
import com.talhanation.smallships.world.sound.ModSoundTypes;
import java.util.function.Supplier;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_5819;
import org.joml.Vector3d;

public class Cannon {
   private final class_5819 random;
   private int shootDelayTimer;
   private Pair<class_1297, Supplier<ICannonProjectile>> cachedShoot = null;
   private int coolDown;
   private final class_1937 level;
   private final ICannon owner;
   private float yaw = 0.0F;
   private float prevYaw = 0.0F;
   private float pitch = 0.0F;
   private float prevPitch = 0.0F;
   private Vector3d pos = new Vector3d();
   private final float barrelHeight = 0.3F;
   private final float speed = 2.6F;

   public Cannon(ICannon owner) {
      this.owner = owner;
      this.level = owner.getLevel();
      this.random = this.level.method_8409();
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPrevYaw() {
      return this.prevYaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public float getPrevPitch() {
      return this.prevPitch;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public void setPitch(float pitch) {
      this.pitch = Math.clamp(pitch, -90.0F, 20.0F);
   }

   public Vector3d getForward() {
      Vector3d dir = new Vector3d(0.0, 0.0, 1.0);
      dir.rotateX((float)Math.toRadians(this.pitch));
      dir.rotateY((float)Math.toRadians(this.yaw));
      return dir;
   }

   public Vector3d getBarrelEndPoint() {
      return this.getBarrelEndPointLocal().add(this.pos);
   }

   public Vector3d getBarrelEndPointLocal() {
      Vector3d barrelMiddle = new Vector3d();
      barrelMiddle.y += 0.3F;
      return barrelMiddle.add(this.getForward().normalize().mul(1.2F));
   }

   public Vector3d getPos() {
      return new Vector3d(this.pos);
   }

   public void tick(double x, double y, double z, double yaw, double pitch) {
      this.prevPitch = this.pitch;
      this.prevYaw = this.yaw;
      this.setYaw((float)yaw);
      this.setPitch((float)pitch);
      this.pos.set(x, y, z);
      if (this.coolDown > 0) {
         this.coolDown--;
      }

      if (this.shootDelayTimer > 0) {
         this.shootDelayTimer--;
         if (!this.level.method_8608() && this.shootDelayTimer == 0 && this.cachedShoot != null) {
            ICannonProjectile projectile = (ICannonProjectile)((Supplier)this.cachedShoot.getSecond()).get();
            if (projectile != null) {
               this.shoot((class_1297)this.cachedShoot.getFirst(), projectile);
            }

            this.cachedShoot = null;
         }
      }

      if (this.coolDown == 3) {
         this.playReloadedSound();
      }
   }

   private void resetTimer() {
      this.shootDelayTimer = 10 + this.random.method_43048(10);
   }

   private void setCoolDown() {
      this.coolDown = 50;
   }

   public boolean isCooldown() {
      return this.coolDown > 0;
   }

   public boolean isFuzing() {
      return this.shootDelayTimer > 0;
   }

   public void triggerFuze(class_1297 shooter, Supplier<ICannonProjectile> projectileSupplier) {
      if (!this.level.method_8608() && !this.isCooldown() && !this.isFuzing()) {
         this.resetTimer();
         this.playFuzeSound();
         this.cachedShoot = new Pair(shooter, projectileSupplier);
      }
   }

   protected void shoot(class_1297 shooter, ICannonProjectile projectile) {
      if (this.level instanceof class_3218 serverLevel && !this.isCooldown() && !this.isFuzing()) {
         this.setCoolDown();
         Vector3d forward = this.getForward();
         projectile.shootAndSpawn(this, this.getBarrelEndPoint(), VectorMath.castToVector3f(forward), 2.6F, 1.0F, shooter);
         this.playCannonShotSound();
         Vector3d particlePos = this.getBarrelEndPoint();
         particlePos.add(this.getForward().mul(0.25));
         ServerParticleUtils.sendParticle(serverLevel, this.owner.provideShootParticles(), particlePos, forward);
         class_2394 particles = projectile.getAdditionalCannonShootParticles();
         if (particles != null) {
            ServerParticleUtils.sendParticle(serverLevel, particles, particlePos, forward);
         }
      }
   }

   public void shootAdvanced(class_243 shootVec, double yShootVec, class_1309 driverEntity, double speed, double accuracy) {
   }

   private void playReloadedSound() {
      if (!this.level.method_8608()) {
         this.owner.playSoundAt((class_3414)class_3417.field_21866.comp_349(), 2.0F, 1.0F);
      }
   }

   private void playCannonShotSound() {
      if (!this.level.method_8608()) {
         this.owner.playSoundAt(ModSoundTypes.CANNON_SHOT, 10.0F, 1.0F);
      }
   }

   private void playFuzeSound() {
      if (!this.level.method_8608()) {
         this.owner.playSoundAt(class_3417.field_15079, 1.0F, 1.5F);
      }
   }
}
