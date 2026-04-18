package com.talhanation.smallships.world.entity.ship;

import com.talhanation.smallships.client.model.sail.SailModel;
import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.math.Kalkuel;
import com.talhanation.smallships.mixin.controlling.BoatAccessor;
import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.network.packet.ServerboundUpdateShipControlPacket;
import com.talhanation.smallships.world.entity.projectile.ShipCannon;
import com.talhanation.smallships.world.entity.ship.abilities.Bannerable;
import com.talhanation.smallships.world.entity.ship.abilities.Cannonable;
import com.talhanation.smallships.world.entity.ship.abilities.IceBreakable;
import com.talhanation.smallships.world.entity.ship.abilities.Paddleable;
import com.talhanation.smallships.world.entity.ship.abilities.Sailable;
import com.talhanation.smallships.world.entity.ship.abilities.Shieldable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1301;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1480;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1928;
import net.minecraft.class_1937;
import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2940;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_310;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3486;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_5498;
import net.minecraft.class_5712;
import net.minecraft.class_9334;
import net.minecraft.class_2945.class_9222;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Ship extends class_1690 {
   public static final class_2940<class_2487> ATTRIBUTES = class_2945.method_12791(Ship.class, class_2943.field_13318);
   public static final class_2940<Float> SPEED = class_2945.method_12791(Ship.class, class_2943.field_13320);
   private static final class_2940<Float> ROT_SPEED = class_2945.method_12791(Ship.class, class_2943.field_13320);
   public static final class_2940<Byte> SAIL_STATE = class_2945.method_12791(Ship.class, class_2943.field_13319);
   public static final class_2940<String> SAIL_COLOR = class_2945.method_12791(Ship.class, class_2943.field_13326);
   public static final class_2940<class_1799> BANNER = class_2945.method_12791(Ship.class, class_2943.field_13322);
   public static final class_2940<Float> CANNON_POWER = class_2945.method_12791(Ship.class, class_2943.field_13320);
   public static final class_2940<Byte> CANNON_COUNT = class_2945.method_12791(Ship.class, class_2943.field_13319);
   private static final class_2940<Boolean> FORWARD = class_2945.method_12791(Ship.class, class_2943.field_13323);
   private static final class_2940<Boolean> BACKWARD = class_2945.method_12791(Ship.class, class_2943.field_13323);
   private static final class_2940<Boolean> LEFT = class_2945.method_12791(Ship.class, class_2943.field_13323);
   private static final class_2940<Boolean> RIGHT = class_2945.method_12791(Ship.class, class_2943.field_13323);
   private static final class_2940<Boolean> SUNKEN = class_2945.method_12791(Ship.class, class_2943.field_13323);
   public static final class_2940<class_2487> SHIELD_DATA = class_2945.method_12791(Ship.class, class_2943.field_13318);
   private boolean isLocked = false;
   private int sunkenTime = 0;
   private float prevWaveAngle;
   private float waveAngle;
   public float prevBannerWaveAngle;
   public float bannerWaveAngle;
   protected boolean cannonKeyPressed;
   public int sailStateCooldown = 0;
   private float setPoint;
   public final List<ShipCannon> CANNONS = new ArrayList<>();
   public final Stack<class_1799> SHIELDS = new Stack<>();
   public float maxSpeed;
   private class_5498 previousCameraType;

   public Ship(class_1299<? extends class_1690> entityType, class_1937 level) {
      super(entityType, level);
      if (this.method_5797() == null) {
         this.method_5665(class_2561.method_43470(StringUtils.capitalize(class_1299.method_5890(this.method_5864()).method_12832())));
      }
   }

   public void method_5773() {
      super.method_5773();
      if (this.method_54294() > 0.0F) {
         this.method_54297(this.method_54294() + 1.0F);
      }

      if (this.isSunken()) {
         if (++this.sunkenTime > (Double)SmallShipsConfig.Common.shipGeneralDespawnTimeSunken.get() * 20.0 * 60.0) {
            this.method_7516(this.method_5770().method_48963().method_48824());
         } else {
            this.method_18800(this.method_18798().field_1352, -0.2, this.method_18798().field_1350);
         }
      } else {
         if (this instanceof Sailable sailShip) {
            sailShip.tickSailShip();
         }

         if (this instanceof Bannerable bannerShip) {
            bannerShip.tickBannerShip();
         }

         if (this instanceof Cannonable cannonShip) {
            cannonShip.tickCannonShip();
         }

         if (this instanceof Paddleable paddleShip) {
            paddleShip.tickPaddleShip();
         }

         if (this instanceof Shieldable shieldShip) {
            shieldShip.tickShieldShip();
         }

         if (this instanceof IceBreakable iceBreakable) {
            iceBreakable.tickIceBreakable();
         }

         boolean isCruising = this.getSpeed() > 0.085F || this.getSpeed() < -0.085F;
         this.updateShipAmbience(isCruising);
         this.updateCollision(isCruising);
         this.updateWaveAngle();
         this.updateWaterMobs();
         this.floatUp();
         if (this.field_7706 > 0.0F) {
            this.field_7706--;
         }
      }
   }

   protected void method_5693(class_9222 builder) {
      super.method_5693(builder);
      builder.method_56912(SPEED, 0.0F);
      builder.method_56912(ROT_SPEED, 0.0F);
      builder.method_56912(ATTRIBUTES, this.createDefaultAttributes());
      builder.method_56912(FORWARD, false);
      builder.method_56912(BACKWARD, false);
      builder.method_56912(LEFT, false);
      builder.method_56912(RIGHT, false);
      builder.method_56912(SUNKEN, false);
      builder.method_56912(SAIL_STATE, (byte)0);
      builder.method_56912(SAIL_COLOR, SailModel.Color.WHITE.toString());
      builder.method_56912(BANNER, class_1799.field_8037);
      builder.method_56912(CANNON_POWER, 4.0F);
      builder.method_56912(CANNON_COUNT, (byte)0);
      builder.method_56912(SHIELD_DATA, new class_2487());
   }

   protected void method_5749(@NotNull class_2487 tag) {
      super.method_5749(tag);
      Attributes attributes = new Attributes();
      attributes.loadSaveData(tag, this);
      this.setData(ATTRIBUTES, attributes.getSaveData());
      if (this instanceof Sailable sailShip) {
         sailShip.readSailShipSaveData(tag);
      }

      if (this instanceof Bannerable bannerShip) {
         bannerShip.readBannerShipSaveData(tag);
      }

      if (this instanceof Cannonable cannonShip) {
         cannonShip.readCannonShipSaveData(tag);
      }

      if (this instanceof Shieldable shieldShip) {
         shieldShip.readShieldShipSaveData(tag);
      }

      this.setSunken(tag.method_10577("Sunken"));
      this.isLocked = tag.method_10577("locked");
   }

   protected void method_5652(@NotNull class_2487 tag) {
      super.method_5652(tag);
      Attributes attributes = new Attributes();
      attributes.loadSaveData(this.getData(ATTRIBUTES));
      attributes.addSaveData(tag);
      if (this instanceof Sailable sailShip) {
         sailShip.addSailShipSaveData(tag);
      }

      if (this instanceof Bannerable bannerShip) {
         bannerShip.addBannerShipSaveData(tag);
      }

      if (this instanceof Cannonable cannonShip) {
         cannonShip.addCannonShipSaveData(tag);
      }

      if (this instanceof Shieldable shieldShip) {
         shieldShip.addShieldShipSaveData(tag);
      }

      tag.method_10556("Sunken", this.isSunken());
      tag.method_10556("locked", this.isLocked);
   }

   public void method_5700(boolean bl) {
      if (!this.method_37908().field_9236) {
         this.field_7689 = true;
         this.field_7703 = bl;
         if (this.method_7539() == 0) {
            this.method_7531(1200);
         }
      }

      this.method_37908()
         .method_8406(
            class_2398.field_11202,
            this.method_23317() + this.field_5974.method_43057(),
            this.method_23318() + 0.7,
            this.method_23321() + this.field_5974.method_43057(),
            0.0,
            0.0,
            0.0
         );
      if (this.field_5974.method_43048(20) == 0) {
         this.method_37908()
            .method_8486(
               this.method_23317(),
               this.method_23318(),
               this.method_23321(),
               this.method_5625(),
               this.method_5634(),
               1.0F,
               0.8F + 0.4F * this.field_5974.method_43057(),
               false
            );
         this.method_32875(class_5712.field_28160, this.method_5642());
      }
   }

   public <T> T getData(class_2940<T> accessor) {
      return (T)this.method_5841().method_12789(accessor);
   }

   public boolean method_5818(class_1297 entity) {
      return super.method_5818(entity)
         && !(entity instanceof Ship)
         && !((List)SmallShipsConfig.Common.mountBlackList.get()).contains(entity.method_5653())
         && !this.isLocked()
         && this.method_5685().size() < this.method_42281()
         && !entity.method_5765()
         && entity.method_17681() < this.method_17681()
         && entity instanceof class_1309
         && !(entity instanceof class_1480);
   }

   public <T> void setData(class_2940<T> accessor, T value) {
      this.method_5841().method_12778(accessor, value);
   }

   protected void method_7549() {
      Attributes attributes = this.getAttributes();
      float speedModifier = (1.0F + this.getBiomeModifier() / 100.0F)
         * (1.0F - (this instanceof Cannonable cannonShip ? cannonShip.getCannonModifier() / 100.0F : 0.0F))
         * (1.0F - (this instanceof ContainerShip containerShip ? containerShip.getContainerModifier() / 100.0F : 0.0F))
         * (1.0F + (this instanceof Paddleable paddleShip ? paddleShip.getPaddlingModifier() / 100.0F : 0.0F));
      this.maxSpeed = attributes.maxSpeed / 69.0F * speedModifier;
      float maxRotSp = attributes.maxRotationSpeed * 0.1F + 1.8F;
      float acceleration = attributes.acceleration;
      float rotAcceleration = attributes.rotationAcceleration;
      if (this.method_37908().method_8608() && !this.isSunken()) {
         class_1657 player = this.getDriver();
         if (player != null) {
            this.updateControls(
               ((BoatAccessor)this).isInputUp(),
               ((BoatAccessor)this).isInputDown(),
               ((BoatAccessor)this).isInputLeft(),
               ((BoatAccessor)this).isInputRight(),
               player
            );
         }
      }

      if (this.method_5799() && !this.isShipLeashed() && !this.isSunken() && !this.isLocked()) {
         if (this instanceof Paddleable && this instanceof Sailable sailShip) {
            if (this.isForward() && this.getDriver() != null) {
               this.setPoint = this.maxSpeed * 12.0F / 16.0F * (1.0F + (1 + sailShip.getSailState()) * 0.1F);
            } else {
               switch (sailShip.getSailState()) {
                  case 0:
                     this.setPoint = 0.0F;
                     break;
                  case 1:
                     this.setPoint = this.maxSpeed * 4.0F / 16.0F;
                     break;
                  case 2:
                     this.setPoint = this.maxSpeed * 8.0F / 16.0F;
                     break;
                  case 3:
                     this.setPoint = this.maxSpeed * 12.0F / 16.0F;
                     break;
                  case 4:
                     this.setPoint = this.maxSpeed * 16.0F / 16.0F;
               }
            }
         } else if (this instanceof Sailable sailShipx) {
            switch (sailShipx.getSailState()) {
               case 0:
                  this.setPoint = 0.0F;
                  break;
               case 1:
                  this.setPoint = this.maxSpeed * 4.0F / 16.0F;
                  break;
               case 2:
                  this.setPoint = this.maxSpeed * 8.0F / 16.0F;
                  break;
               case 3:
                  this.setPoint = this.maxSpeed * 12.0F / 16.0F;
                  break;
               case 4:
                  this.setPoint = this.maxSpeed * 16.0F / 16.0F;
            }
         }

         this.calculateSpeed(acceleration);
         float rotationSpeed = Kalkuel.subtractToZero(this.getRotSpeed(), this.getVelocityResistance() * 2.5F);
         if (this.getDriver() != null) {
            if (this.isRight() && rotationSpeed < maxRotSp) {
               rotationSpeed = Math.min(rotationSpeed + rotAcceleration * 1.0F / 8.0F, maxRotSp);
            }

            if (this.isLeft() && rotationSpeed > -maxRotSp) {
               rotationSpeed = Math.max(rotationSpeed - rotAcceleration * 1.0F / 8.0F, -maxRotSp);
            }
         }

         this.setRotSpeed(rotationSpeed);
         ((BoatAccessor)this).setDeltaRotation(rotationSpeed);
         this.method_36456(this.method_36454() + ((BoatAccessor)this).getDeltaRotation());
         if (this.getDriver() != null) {
            if (this instanceof Sailable sailShipx) {
               sailShipx.controlBoatSailShip();
            }

            if (this instanceof Paddleable paddleShipx) {
               paddleShipx.controlBoatPaddleShip();
            }
         }

         this.method_18800(
            Kalkuel.calculateMotionX(this.getSpeed(), this.method_36454()),
            this.method_18798().field_1351,
            Kalkuel.calculateMotionZ(this.getSpeed(), this.method_36454())
         );
      } else {
         this.setForward(false);
         this.setBackward(false);
         this.setLeft(false);
         this.setRight(false);
      }
   }

   public boolean isLocked() {
      return this.isLocked;
   }

   public boolean isShipLeashed() {
      return this.method_60953();
   }

   private void calculateSpeed(float acceleration) {
      float speed = this.getSpeed();
      if (speed < this.setPoint) {
         speed = Kalkuel.addToSetPoint(speed, acceleration, this.setPoint);
      } else {
         speed = Kalkuel.subtractToZero(speed, this.getVelocityResistance() * 0.8F);
      }

      if (this.isLeft() || this.isRight()) {
         speed *= 1.0F - class_3532.method_15379(this.getRotSpeed()) * 0.02F;
      }

      this.setSpeed(speed);
   }

   public class_2487 getShieldData() {
      return (class_2487)this.field_6011.method_12789(SHIELD_DATA);
   }

   public void setShieldData(class_2487 f) {
      this.field_6011.method_12778(SHIELD_DATA, f);
   }

   public float getSpeed() {
      return (Float)this.field_6011.method_12789(SPEED);
   }

   public float getRotSpeed() {
      return (Float)this.field_6011.method_12789(ROT_SPEED);
   }

   public void setSpeed(float f) {
      this.field_6011.method_12778(SPEED, f);
   }

   public void setRotSpeed(float f) {
      this.field_6011.method_12778(ROT_SPEED, f);
   }

   public void setForward(boolean forward) {
      this.field_6011.method_12778(FORWARD, forward);
   }

   public void setBackward(boolean backward) {
      this.field_6011.method_12778(BACKWARD, backward);
   }

   public void setLeft(boolean left) {
      this.field_6011.method_12778(LEFT, left);
   }

   public void setRight(boolean right) {
      this.field_6011.method_12778(RIGHT, right);
   }

   public boolean isForward() {
      return this.method_5642() == null ? false : (Boolean)this.field_6011.method_12789(FORWARD);
   }

   public boolean isBackward() {
      return this.method_5642() == null ? false : (Boolean)this.field_6011.method_12789(BACKWARD);
   }

   public boolean isLeft() {
      return (Boolean)this.field_6011.method_12789(LEFT);
   }

   public boolean isRight() {
      return (Boolean)this.field_6011.method_12789(RIGHT);
   }

   public float getBiomeModifier() {
      Ship.BiomeModifierType shipBiomeType = this.getBiomeModifierType();
      if (shipBiomeType == Ship.BiomeModifierType.NONE) {
         return 0.0F;
      } else {
         class_2338 pos = new class_2338((int)this.method_23317(), (int)this.method_23318(), (int)this.method_23321());
         int tmp = ((class_1959)this.method_5770().method_23753(pos).comp_349()).method_8687();
         float modifier = ((Double)SmallShipsConfig.Common.shipGeneralBiomeModifier.get()).floatValue();
         boolean coldBiomes = tmp < 4100000;
         boolean warmBiomes = tmp > 4300000;
         boolean neutralBiomes = !coldBiomes && !warmBiomes;
         boolean coldType = shipBiomeType == Ship.BiomeModifierType.COLD;
         boolean neutralType = shipBiomeType == Ship.BiomeModifierType.NEUTRAL;
         boolean warmType = shipBiomeType == Ship.BiomeModifierType.WARM;
         if ((!coldBiomes || !coldType) && (!warmBiomes || !warmType) && (!neutralBiomes || !neutralType)) {
            if ((!coldBiomes || !warmType) && (!warmBiomes || !coldType) && (!coldBiomes && !warmBiomes || !neutralType)) {
               return (!neutralBiomes || !warmType) && (!neutralBiomes || !coldType) ? 0.0F : -modifier / 4.0F;
            } else {
               return -modifier;
            }
         } else {
            return modifier;
         }
      }
   }

   @NotNull
   public class_1269 method_5688(@NotNull class_1657 player, @NotNull class_1268 interactionHand) {
      if (!this.isLocked()) {
         if (this.interactWithNameTag(player)) {
            return class_1269.field_5812;
         } else if (this.interactIronNuggets(player)) {
            return class_1269.field_5812;
         } else if (this instanceof Cannonable cannonShip && cannonShip.interactCannon(player, interactionHand)) {
            return class_1269.field_5812;
         } else if (this instanceof Sailable sailShip && sailShip.interactSail(player, interactionHand)) {
            return class_1269.field_5812;
         } else if (this instanceof Bannerable bannerShip && bannerShip.interactBanner(player, interactionHand)) {
            return class_1269.field_5812;
         } else {
            return this instanceof Shieldable shieldShip && shieldShip.interactShield(player, interactionHand)
               ? class_1269.field_5812
               : super.method_5688(player, interactionHand);
         }
      } else {
         return class_1269.field_5811;
      }
   }

   private boolean interactWithNameTag(@NotNull class_1657 player) {
      if (player.method_6047().method_31574(class_1802.field_8448)
         && player.method_6047().method_57826(class_9334.field_49631)
         && !player.method_5770().field_9236) {
         this.method_5665(player.method_6047().method_7964());
         this.method_5880(false);
         if (!player.method_7337()) {
            player.method_6047().method_7934(1);
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean interactIronNuggets(@NotNull class_1657 player) {
      if (this.method_54294() > 0.0F
         && player.method_6047().method_31574(class_1802.field_8675)
         && player.method_31548().method_43256(stack -> stack.method_31573(class_3489.field_15537))) {
         this.repairShip(5 + this.method_37908().field_9229.method_43048(5));
         if (!player.method_7337()) {
            player.method_6047().method_7934(1);

            for (int i = 0; i < player.method_31548().method_5439(); i++) {
               class_1799 itemStack = player.method_31548().method_5438(i);
               if (itemStack.method_31573(class_3489.field_15537)) {
                  itemStack.method_7934(1);
                  break;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void repairShip(int repairAmount) {
      this.method_5770()
         .method_43128(
            null,
            this.method_23317(),
            this.method_23318() + 1.0,
            this.method_23321(),
            class_3417.field_14808,
            class_3419.field_15245,
            1.0F,
            0.9F + 0.2F * this.method_5770().method_8409().method_43057()
         );
      this.method_5770()
         .method_43128(
            null,
            this.method_23317(),
            this.method_23318() + 2.0,
            this.method_23321(),
            class_3417.field_14718,
            class_3419.field_15245,
            1.0F,
            0.9F + 0.2F * this.method_5770().method_8409().method_43057()
         );
      float newDamage = this.method_54294() - repairAmount;
      if (newDamage < 0.0F) {
         newDamage = 0.0F;
      }

      this.method_54297(newDamage);
   }

   @NotNull
   public class_243 method_24829(@NotNull class_1309 livingEntity) {
      if (this instanceof Sailable sailShip && sailShip.getSailState() != 0) {
         sailShip.toggleSail();
      }

      return super.method_24829(livingEntity);
   }

   protected void method_5627(class_1297 entity) {
      if (this.method_37908().method_8608()
         && (Boolean)SmallShipsConfig.Client.shipGeneralCameraAutoThirdPerson.get()
         && Objects.equals(class_310.method_1551().field_1724, entity)) {
         this.previousCameraType = class_310.method_1551().field_1690.method_31044();
         class_310.method_1551().field_1690.method_31043(class_5498.field_26665);
      }

      super.method_5627(entity);
   }

   protected void method_5793(class_1297 entity) {
      if (this.method_37908().method_8608()
         && (Boolean)SmallShipsConfig.Client.shipGeneralCameraAutoThirdPerson.get()
         && Objects.equals(class_310.method_1551().field_1724, entity)) {
         class_310.method_1551().field_1690.method_31043(this.previousCameraType);
      }

      super.method_5793(entity);
   }

   public void setSunken(boolean sunken) {
      this.field_6011.method_12778(SUNKEN, sunken);
   }

   public boolean isSunken() {
      return (Boolean)this.field_6011.method_12789(SUNKEN);
   }

   private void updateWaveAngle() {
      this.prevWaveAngle = this.waveAngle;
      this.waveAngle = (float)Math.sin(this.getWaveSpeed() * this.field_6012) * this.getWaveFactor();
   }

   private float getWaveFactor() {
      return this.method_37908().method_8419() ? 3.0F : 1.25F;
   }

   private float getWaveSpeed() {
      return this.method_37908().method_8419() ? 0.12F : 0.03F;
   }

   public float getWaveAngle(float partialTicks) {
      return class_3532.method_16439(partialTicks, this.prevWaveAngle, this.waveAngle);
   }

   public Attributes getAttributes() {
      Attributes attributes = new Attributes();
      attributes.loadSaveData(this.getData(ATTRIBUTES));
      return attributes;
   }

   public void setCannonKeyPressed(boolean b) {
      this.cannonKeyPressed = b;
   }

   public boolean isCannonKeyPressed() {
      return this.cannonKeyPressed;
   }

   @NotNull
   public class_238 method_5830() {
      return this.method_5829().method_1014(5.0);
   }

   public abstract int method_42281();

   @NotNull
   public abstract class_1792 method_7557();

   public abstract Ship.BiomeModifierType getBiomeModifierType();

   public abstract class_2487 createDefaultAttributes();

   public float getVelocityResistance() {
      return 0.007F;
   }

   protected void waterSplash() {
   }

   private void updateShipAmbience(boolean isSwimming) {
      if (isSwimming && this.method_5799()) {
         this.waterSplash();
         this.method_37908()
            .method_43128(
               null,
               this.method_23317(),
               this.method_23318(),
               this.method_23321(),
               class_3417.field_14818,
               this.method_5634(),
               0.05F,
               0.8F + 0.4F * this.field_5974.method_43057()
            );
      }
   }

   private void updateWaterMobs() {
      if (!this.method_5770().method_8608()) {
         double radius = (Double)SmallShipsConfig.Common.waterAnimalFleeRadius.get();

         for (class_1480 waterAnimal : this.method_37908()
            .method_18467(
               class_1480.class,
               new class_238(
                  this.method_23317() - radius,
                  this.method_23318() - radius,
                  this.method_23321() - radius,
                  this.method_23317() + radius,
                  this.method_23318() + radius,
                  this.method_23321() + radius
               )
            )) {
            if (this.field_6012 % 20 == 0) {
               this.fleeEntity(waterAnimal);
            }
         }
      }
   }

   private void fleeEntity(class_1308 entity) {
      double fleeDistance = (Double)SmallShipsConfig.Common.waterAnimalFleeDistance.get();
      double fleeSpeed = (Double)SmallShipsConfig.Common.waterAnimalFleeSpeed.get();
      class_243 vecBoat = new class_243(this.method_23317(), this.method_23318(), this.method_23321());
      class_243 vecEntity = new class_243(entity.method_23317(), entity.method_23318(), entity.method_23321());
      class_243 fleeDir = vecEntity.method_1020(vecBoat);
      fleeDir = fleeDir.method_1029();
      class_243 fleePos = new class_243(
         vecEntity.field_1352 + fleeDir.field_1352 * fleeDistance,
         vecEntity.field_1351 + fleeDir.field_1351 * fleeDistance,
         vecEntity.field_1350 + fleeDir.field_1350 * fleeDistance
      );
      entity.method_5942().method_6337(fleePos.field_1352, fleePos.field_1351, fleePos.field_1350, fleeSpeed);
   }

   protected void floatUp() {
      if (this.method_5777(class_3486.field_15517)) {
         this.method_18799(this.method_18798().method_1031(0.0, 0.2, 0.0));
      }
   }

   public boolean method_5643(class_1282 damageSource, float f) {
      if (this.method_5679(damageSource)) {
         return false;
      } else if (!this.method_5770().method_8608() && !this.method_31481()) {
         this.method_54297(this.method_54294() + f * (this instanceof Shieldable shieldShip ? shieldShip.getDamageModifier() : 1.0F));
         this.method_5785();
         this.method_32875(class_5712.field_28736, damageSource.method_5529());
         boolean bl = damageSource.method_5529() instanceof class_1657 player && player.method_31549().field_7477 && player.method_18276();
         if (this.method_54294() > this.getAttributes().maxHealth) {
            if (this.isSunken() && this.sunkenTime > 200) {
               this.method_7516(this.method_5770().method_48963().method_48824());
            } else {
               this.setSunken(true);
            }
         }

         if (bl) {
            this.method_31472();
         }

         return true;
      } else {
         return true;
      }
   }

   private void knockBack(class_1297 entity, double speed, class_238 boundingBox) {
      double d0 = (boundingBox.field_1323 + boundingBox.field_1320) / 2.0;
      double d1 = (boundingBox.field_1321 + boundingBox.field_1324) / 2.0;
      if (entity instanceof class_1309) {
         double d2 = entity.method_23317() - d0;
         double d3 = entity.method_23321() - d1;
         double d4 = Math.max(d2 * d2 + d3 * d3, 0.1);
         entity.method_18799(this.method_18798().method_1031(d2 / d4 * (1.0 + speed * 1.1), 0.0, d3 / d4 * (1.0 + speed * 1.1)));
      }
   }

   private void updateCollision(boolean isCruising) {
      if (isCruising && this.canDoKnockBack() && (Boolean)SmallShipsConfig.Common.shipGeneralCollisionKnockBack.get()) {
         class_238 boundingBox = this.method_5829().method_1009(2.25, 1.25, 2.25).method_989(0.0, -2.0, 0.0);

         for (class_1297 entity : this.method_37908().method_8333(this, boundingBox, class_1301.method_5911(this))) {
            if (entity instanceof class_1309 && !this.method_5685().contains(entity)) {
               this.knockBack(entity, this.getSpeed(), boundingBox);
               this.collisionDamage(entity, this.getSpeed());
            }
         }
      }
   }

   public boolean canDoKnockBack() {
      return true;
   }

   public boolean canDoCollisionDamage() {
      return true;
   }

   private void collisionDamage(class_1297 entity, float speed) {
      if (this.method_5642() != null) {
         if (this.method_5642().method_5781() != null
            && this.method_5642().method_5781().method_1206(entity.method_5781())
            && !this.method_5642().method_5781().method_1205()) {
            return;
         }

         if (this.canDoCollisionDamage() && speed > 0.1F) {
            float damage = speed * ((Double)SmallShipsConfig.Common.shipGeneralCollisionDamage.get()).floatValue();
            if (damage > 0.0F) {
               entity.method_5643(this.method_5770().method_48963().method_48812(this.method_5642()), damage);
            }
         }
      }
   }

   @Nullable
   public class_1657 getDriver() {
      List<class_1297> passengers = this.method_5685();
      if (passengers.isEmpty()) {
         return null;
      } else if (passengers.get(0) instanceof class_1657 player) {
         if (this.method_5770().field_9236) {
            class_310 minecraft = class_310.method_1551();
            class_1657 instancePlayer = minecraft.field_1724;
            return player.equals(instancePlayer) ? player : null;
         } else {
            return (class_1657)passengers.get(0);
         }
      } else {
         return null;
      }
   }

   public void updateControls(boolean forward, boolean backward, boolean left, boolean right, @Nullable class_1657 player) {
      boolean needsUpdate = false;
      if (this.isForward() != forward) {
         this.setForward(forward);
         needsUpdate = true;
      }

      if (this.isBackward() != backward) {
         this.setBackward(backward);
         needsUpdate = true;
      }

      if (this.isLeft() != left) {
         this.setLeft(left);
         needsUpdate = true;
      }

      if (this.isRight() != right) {
         this.setRight(right);
         needsUpdate = true;
      }

      if (this.method_5770().field_9236 && needsUpdate && player != null) {
         ModPackets.clientSendPacket(new ServerboundUpdateShipControlPacket(forward, backward, left, right));
      }
   }

   public void method_7516(@NotNull class_1282 damageSource) {
      super.method_7516(damageSource);
      if (this.method_5770().method_8450().method_8355(class_1928.field_19393)) {
         if (this instanceof ContainerShip containerShip) {
            containerShip.method_42283(damageSource, this.method_5770(), this);
         }

         if (this instanceof Cannonable cannonableShip) {
            cannonableShip.cannonShipDestroyed(this.method_5770(), this);
         }
      }

      this.method_31472();
   }

   public static enum BiomeModifierType {
      NONE,
      COLD,
      NEUTRAL,
      WARM;
   }
}
