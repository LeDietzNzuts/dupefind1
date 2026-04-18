package ewewukek.musketmod;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1311;
import net.minecraft.class_1657;
import net.minecraft.class_1668;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2388;
import net.minecraft.class_239;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2530;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2604;
import net.minecraft.class_2680;
import net.minecraft.class_2940;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3231;
import net.minecraft.class_3414;
import net.minecraft.class_3486;
import net.minecraft.class_3610;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import net.minecraft.class_8110;
import net.minecraft.class_1299.class_1300;
import net.minecraft.class_239.class_240;
import net.minecraft.class_2945.class_9222;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public class BulletEntity extends class_1668 {
   public static final class_2940<Float> INITIAL_SPEED = class_2945.method_12791(BulletEntity.class, class_2943.field_13320);
   public static final class_2940<Float> DROP_REDUCTION = class_2945.method_12791(BulletEntity.class, class_2943.field_13320);
   public static final class_2940<Byte> PELLET_COUNT = class_2945.method_12791(BulletEntity.class, class_2943.field_13319);
   public static final class_6862<class_2248> DESTROYED_BY_BULLETS = class_6862.method_40092(class_7924.field_41254, MusketMod.resource("destroyed_by_bullets"));
   public static final class_6862<class_2248> DROPPED_BY_BULLETS = class_6862.method_40092(class_7924.field_41254, MusketMod.resource("dropped_by_bullets"));
   public static final class_6862<class_1299<?>> HEADSHOTABLE = class_6862.method_40092(class_7924.field_41266, MusketMod.resource("headshotable"));
   public static final class_5321<class_8110> BULLET_DAMAGE = class_5321.method_29179(class_7924.field_42534, MusketMod.resource("bullet"));
   public static class_1299<BulletEntity> ENTITY_TYPE;
   public static final double GRAVITY = 0.05;
   public static final double AIR_FRICTION = 0.99;
   public static final double WATER_FRICTION = 0.6;
   public static final short LIFETIME = 100;
   public static final int HIT_PARTICLE_COUNT = 5;
   public static final float IGNITE_SECONDS = 5.0F;
   public float damage;
   public boolean touchedWater;
   public boolean headshot;
   public float distanceTravelled;
   public short tickCounter;
   private boolean packetSpeedReceived;

   public static void register(BiConsumer<String, class_1299<?>> helper) {
      class_1300<BulletEntity> builder = class_1300.method_5903(BulletEntity::new, class_1311.field_17715)
         .method_17687(0.5F, 0.5F)
         .method_27299(64)
         .method_27300(20);
      MusketMod.disableVelocityUpdate(builder);
      ENTITY_TYPE = builder.method_5905("bullet");
      helper.accept("bullet", ENTITY_TYPE);
   }

   public BulletEntity(class_1299<BulletEntity> entityType, class_1937 level) {
      super(entityType, level);
   }

   public BulletEntity(class_1937 level) {
      this(ENTITY_TYPE, level);
   }

   public boolean isFirstTick() {
      return this.tickCounter == 0;
   }

   public void discardOnNextTick() {
      this.tickCounter = 100;
   }

   public float calculateEnergyFraction() {
      double maxEnergy = Math.pow(((Float)this.field_6011.method_12789(INITIAL_SPEED)).floatValue(), 2.0);
      double energy = this.method_18798().method_1027();
      if (maxEnergy < energy) {
         maxEnergy = energy;
      }

      return (float)(energy / maxEnergy);
   }

   public int pelletCount() {
      int count = (Byte)this.field_6011.method_12789(PELLET_COUNT);
      return count > 1 ? count : 1;
   }

   public boolean soundEffectRoll() {
      int count = this.pelletCount();
      return count == 1 ? true : this.field_5974.method_43057() < 1.5F / count;
   }

   public class_1282 getDamageSource() {
      class_1297 attacker = (class_1297)(this.method_24921() != null ? this.method_24921() : this);
      return this.method_37908().method_48963().method_48797(BULLET_DAMAGE, this, attacker);
   }

   public void setVelocity(float bulletSpeed, class_243 direction) {
      float tickSpeed = bulletSpeed / 20.0F;
      this.setInitialSpeed(tickSpeed);
      this.method_18799(direction.method_1021(tickSpeed));
   }

   public boolean method_5862() {
      return false;
   }

   public void method_5773() {
      if (++this.tickCounter <= 100 && !(this.distanceTravelled > Config.bulletMaxDistance)) {
         class_1937 level = this.method_37908();
         class_243 velocity = this.method_18798();
         class_243 from = this.method_19538();
         class_243 to = from.method_1019(velocity);
         class_243 waterPos = class_243.field_1353;
         this.field_5957 = this.method_5692(class_3486.field_15517, 0.0);
         if (this.field_5957) {
            waterPos = from;
            velocity = velocity.method_1021(0.6);
            to = from.method_1019(velocity);
            this.method_18799(velocity);
         }

         class_239 hitResult = level.method_17742(new class_3959(from, to, class_3960.field_17558, class_242.field_1348, this));
         if (hitResult.method_17783() != class_240.field_1333) {
            to = hitResult.method_17784();
         }

         class_3966 entityHitResult = this.findHitEntity(from, to);
         if (entityHitResult != null) {
            hitResult = entityHitResult;
            to = entityHitResult.method_17784();
         }

         if (!this.field_5957) {
            class_3965 fluidHitResult = level.method_17742(new class_3959(from, to, class_3960.field_17558, class_242.field_1347, this));
            if (fluidHitResult.method_17783() == class_240.field_1332) {
               class_3610 fluid = level.method_8316(fluidHitResult.method_17777());
               double distanceToFluid = fluidHitResult.method_17784().method_1020(from).method_1033();
               double distanceToHit = to.method_1020(from).method_1033();
               if (fluid.method_15767(class_3486.field_15517)) {
                  this.field_5957 = true;
                  waterPos = fluidHitResult.method_17784();
                  double speed = velocity.method_1033();
                  double timeInWater = 1.0 - distanceToFluid / speed;
                  double newSpeed = speed * (1.0 - timeInWater + timeInWater * Math.pow(0.6, timeInWater));
                  if (hitResult.method_17783() != class_240.field_1333) {
                     if (distanceToFluid < distanceToHit) {
                        if (distanceToHit < newSpeed) {
                           timeInWater = (distanceToHit - distanceToFluid) / speed;
                           newSpeed = speed * (1.0 - timeInWater + timeInWater * Math.pow(0.6, timeInWater));
                        } else {
                           hitResult = class_3965.method_17778(null, null, null);
                        }
                     } else {
                        fluidHitResult = class_3965.method_17778(null, null, null);
                     }
                  }

                  velocity = velocity.method_1021(newSpeed / speed);
                  to = from.method_1019(velocity);
                  this.method_18799(velocity);
                  if (level.field_9236 && fluidHitResult.method_17783() != class_240.field_1333) {
                     double yv = fluidHitResult.method_17780() == class_2350.field_11036 ? 0.02 : 0.0;
                     this.createHitParticles(class_2398.field_11202, waterPos, new class_243(0.0, yv, 0.0));
                     this.playHitSound(Sounds.BULLET_WATER_HIT, waterPos);
                  }
               } else if (fluid.method_15767(class_3486.field_15518) && (hitResult.method_17783() == class_240.field_1333 || distanceToFluid < distanceToHit)) {
                  hitResult = fluidHitResult;
                  to = fluidHitResult.method_17784();
               }
            }
         }

         if (this.field_5957) {
            this.touchedWater = true;
            this.method_46395();
         }

         if (!level.field_9236) {
            this.method_33572(this.method_20802() > 0);
         }

         if (hitResult.method_17783() != class_240.field_1333) {
            if (this.touchedWater) {
               this.damage = this.damage * this.calculateEnergyFraction();
            }

            if (!level.field_9236) {
               this.method_7488(hitResult);
               if (hitResult.method_17783() == class_240.field_1332) {
                  float destroyProbability = this.calculateEnergyFraction();
                  if (this.pelletCount() > 1) {
                     destroyProbability = 1.5F * destroyProbability / this.pelletCount();
                  }

                  if (this.field_5974.method_43057() < destroyProbability) {
                     class_2338 blockPos = ((class_3965)hitResult).method_17777();
                     class_2680 blockState = this.method_37908().method_8320(blockPos);
                     if (blockState.method_27852(class_2246.field_10375)) {
                        class_2530.method_10738(this.method_37908(), blockPos);
                        level.method_8650(blockPos, false);
                     } else if (blockState.method_26164(DESTROYED_BY_BULLETS)) {
                        if (level.method_8650(blockPos, false)) {
                           blockState.method_26204().method_9585(level, blockPos, blockState);
                        }
                     } else if (blockState.method_26164(DROPPED_BY_BULLETS) && level.method_8650(blockPos, false)) {
                        class_2248.method_9497(blockState, level, blockPos);
                     }
                  }
               }

               this.discardOnNextTick();
            } else if (hitResult.method_17783() == class_240.field_1332) {
               class_243 pos = hitResult.method_17784();
               class_2680 blockState = level.method_8320(((class_3965)hitResult).method_17777());
               class_2388 particle = new class_2388(class_2398.field_11217, blockState);
               this.createHitParticles(particle, pos, class_243.field_1353);
               if (this.method_5809()) {
                  level.method_8406(class_2398.field_11223, pos.field_1352, pos.field_1351, pos.field_1350, 0.0, 0.01, 0.0);
               }

               this.playHitSound(blockState.method_26231().method_10595(), pos);
               this.method_31472();
            }
         } else if (level.field_9236 && !this.field_5957 && this.soundEffectRoll()) {
            double length = velocity.method_1033();
            class_243 dir = velocity.method_1021(1.0 / length);
            float volume = this.calculateEnergyFraction();
            class_238 aabbSelection = this.method_5829().method_18804(velocity).method_1014(8.0);
            Predicate<class_1297> predicate = entityx -> entityx instanceof class_1657 && !entityx.equals(this.method_24921());

            for (class_1297 entity : this.method_37908().method_8333(this, aabbSelection, predicate)) {
               class_243 pos = new class_243(entity.method_23317(), entity.method_23320(), entity.method_23321());
               class_243 diff = pos.method_1020(from);
               double proj = dir.method_1026(diff);
               if (proj > 0.0 && proj < length) {
                  class_243 projPos = from.method_1019(dir.method_1021(proj));
                  this.method_37908()
                     .method_8486(
                        projPos.field_1352,
                        projPos.field_1351,
                        projPos.field_1350,
                        Sounds.BULLET_FLY_BY,
                        this.method_5634(),
                        volume,
                        0.92F + 0.16F * this.field_5974.method_43057(),
                        false
                     );
               }
            }
         }

         if (level.field_9236 && this.field_5957) {
            double length = velocity.method_1033();
            class_243 step = velocity.method_1021(1.0 / length);
            class_243 pos = waterPos.method_1019(step.method_1021(0.5));
            float prob = 1.5F * this.calculateEnergyFraction() / this.pelletCount();

            while (length > 0.5) {
               pos = pos.method_1019(step);
               length--;
               if (this.field_5974.method_43057() < prob) {
                  level.method_8406(class_2398.field_11247, pos.field_1352, pos.field_1351, pos.field_1350, 0.0, 0.0, 0.0);
               }
            }
         }

         if (!this.field_5957) {
            velocity = velocity.method_1021(0.99);
         }

         double gravity = 0.05 * (1.0F - (Float)this.field_6011.method_12789(DROP_REDUCTION));
         this.method_18799(velocity.method_1023(0.0, gravity, 0.0));
         this.method_33574(to);
         this.distanceTravelled = (float)(this.distanceTravelled + to.method_1020(from).method_1033());
         this.method_5852();
      } else {
         this.method_31472();
      }
   }

   public void method_7454(class_3966 hitResult) {
      if (!(this.calculateEnergyFraction() < 0.05)) {
         float damageMult = 1.0F;
         class_1297 target = hitResult.method_17782();
         class_1297 shooter = this.method_24921();
         if (shooter == null
            || shooter.method_5781() == null
            || !shooter.method_5781().method_1206(target.method_5781())
            || shooter.method_5781().method_1205()) {
            if (shooter instanceof class_1657 playerShooter) {
               if (target instanceof class_1657 playerTarget) {
                  damageMult = Config.pvpDamageMultiplier;
                  if (!playerShooter.method_7256(playerTarget)) {
                     return;
                  }
               }
            } else {
               damageMult = Config.mobDamageMultiplier;
            }

            class_1282 source = this.getDamageSource();
            boolean ignite = this.method_5809() && target.method_5864() != class_1299.field_6091;
            if (this.pelletCount() == 1) {
               if (this.headshot) {
                  damageMult *= Config.headshotDamageMultiplier;
               }

               target.field_6008 = 0;
               target.method_5643(source, this.damage * damageMult);
               if (ignite) {
                  target.method_5639(5.0F);
               }
            } else {
               damageMult /= this.pelletCount();
               DeferredDamage.hurt(target, source, this.damage * damageMult, ignite ? 1.0F : 0.0F);
            }
         }
      }
   }

   public boolean checkHeadshot(class_1297 entity, class_238 aabb, class_243 start, class_243 end) {
      if (this.pelletCount() <= 1 && entity.method_5864().method_20210(HEADSHOTABLE)) {
         double width = (aabb.field_1320 - aabb.field_1323 + aabb.field_1324 - aabb.field_1321) / 2.0;
         aabb = aabb.method_35575(aabb.field_1325 - width);
         return aabb.method_992(start, end).isPresent();
      } else {
         return false;
      }
   }

   public class_3966 findHitEntity(class_243 start, class_243 end) {
      class_1297 resultEntity = null;
      class_243 resultPos = null;
      double resultDist = 0.0;
      class_238 aabbSelection = this.method_5829().method_18804(this.method_18798()).method_1014(0.5);

      for (class_1297 entity : this.method_37908().method_8333(this, aabbSelection, this::method_26958)) {
         class_238 aabb = entity.method_5829();
         Optional<class_243> clipResult = aabb.method_992(start, end);
         if (!clipResult.isPresent()) {
            aabb = aabb.method_989(
               entity.field_6038 - entity.method_23317(), entity.field_5971 - entity.method_23318(), entity.field_5989 - entity.method_23321()
            );
            clipResult = aabb.method_992(start, end);
         }

         if (clipResult.isPresent()) {
            double dist = start.method_1025(clipResult.get());
            if (dist < resultDist || resultEntity == null) {
               resultEntity = entity;
               resultPos = clipResult.get();
               resultDist = dist;
               this.headshot = this.checkHeadshot(entity, aabb, start, end);
            }
         }
      }

      return resultEntity != null ? new class_3966(resultEntity, resultPos) : null;
   }

   public boolean method_26958(class_1297 entity) {
      if (super.method_26958(entity)) {
         return true;
      } else {
         class_1937 level = this.method_37908();
         return level.field_9236 && entity instanceof class_1309;
      }
   }

   public void createHitParticles(class_2394 particle, class_243 position, class_243 velocity) {
      float amount = 5.0F * this.calculateEnergyFraction() / this.pelletCount();
      int count = (int)amount + (this.field_5974.method_43057() < amount % 1.0F ? 1 : 0);

      for (int i = 0; i < count; i++) {
         this.method_37908()
            .method_8406(
               particle,
               position.field_1352,
               position.field_1351,
               position.field_1350,
               velocity.field_1352 + 0.01 * this.field_5974.method_43059(),
               velocity.field_1351 + 0.01 * this.field_5974.method_43059(),
               velocity.field_1350 + 0.01 * this.field_5974.method_43059()
            );
      }
   }

   public void playHitSound(class_3414 sound, class_243 position) {
      if (this.soundEffectRoll()) {
         this.method_37908()
            .method_8486(
               position.field_1352,
               position.field_1351,
               position.field_1350,
               sound,
               this.method_5634(),
               this.calculateEnergyFraction(),
               0.95F + 0.1F * this.field_5974.method_43057(),
               false
            );
      }
   }

   public void setInitialSpeed(float speed) {
      this.field_6011.method_12778(INITIAL_SPEED, speed);
   }

   public void setDropReduction(float reduction) {
      this.field_6011.method_12778(DROP_REDUCTION, reduction);
   }

   public void setPelletCount(int count) {
      this.field_6011.method_12778(PELLET_COUNT, (byte)count);
   }

   protected void method_5693(class_9222 builder) {
      super.method_5693(builder);
      builder.method_56912(INITIAL_SPEED, 0.0F);
      builder.method_56912(DROP_REDUCTION, 0.0F);
      builder.method_56912(PELLET_COUNT, (byte)1);
   }

   public void method_5749(class_2487 compound) {
      super.method_5749(compound);
      this.damage = compound.method_10583("damage");
      this.distanceTravelled = compound.method_10583("distanceTravelled");
      this.field_6011.method_12778(DROP_REDUCTION, compound.method_10583("dropReduction"));
      this.field_6011.method_12778(PELLET_COUNT, compound.method_10571("pelletCount"));
   }

   public void method_5652(class_2487 compound) {
      super.method_5652(compound);
      compound.method_10548("damage", this.damage);
      compound.method_10548("distanceTravelled", this.distanceTravelled);
      compound.method_10548("dropReduction", (Float)this.field_6011.method_12789(DROP_REDUCTION));
      compound.method_10567("pelletCount", (Byte)this.field_6011.method_12789(PELLET_COUNT));
   }

   public class_2596<class_2602> method_18002(class_3231 entity) {
      class_1297 owner = this.method_24921();
      class_243 position = entity.method_60942();
      return new class_2604(
         this.method_5628(),
         this.method_5667(),
         position.method_10216(),
         position.method_10214(),
         position.method_10215(),
         entity.method_60944(),
         entity.method_60945(),
         this.method_5864(),
         owner != null ? owner.method_5628() : 0,
         entity.method_60943().method_1021(3.9 / ((Float)this.field_6011.method_12789(INITIAL_SPEED)).floatValue()),
         0.0
      );
   }

   public void method_31471(class_2604 packet) {
      super.method_31471(packet);
      class_243 packetVelocity = new class_243(packet.method_11170(), packet.method_11172(), packet.method_11173());
      this.method_18799(packetVelocity.method_1021(0.25641025641025644));
   }

   public void method_5674(class_2940<?> accessor) {
      super.method_5674(accessor);
      if (INITIAL_SPEED.equals(accessor) && this.method_37908().field_9236 && !this.packetSpeedReceived) {
         this.method_18799(this.method_18798().method_1021(((Float)this.field_6011.method_12789(INITIAL_SPEED)).floatValue()));
         this.packetSpeedReceived = true;
      }
   }
}
