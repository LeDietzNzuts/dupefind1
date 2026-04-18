package com.talhanation.smallships.world.entity.ship.abilities;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.world.entity.projectile.ShipCannon;
import com.talhanation.smallships.world.entity.ship.ContainerShip;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.item.ModItems;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_7265;

public interface Cannonable extends Ability {
   Cannonable.CannonPosition getCannonPosition(int var1);

   byte getMaxCannonPerSide();

   default void tickCannonShip() {
      for (ShipCannon cannon : this.getCannons()) {
         cannon.method_5773();
         if (this.self().isCannonKeyPressed() && this.canShoot()) {
            this.triggerCannon(cannon);
         }
      }
   }

   default void triggerCannon(ShipCannon cannon) {
      if (cannon.canShootDirection()) {
         cannon.trigger();
      }
   }

   default void triggerCannons(class_243 shootVec, double yShootVec, class_1309 driverEntity, double speed, double accuracy) {
      if (this.canShoot()) {
         for (ShipCannon cannon : this.getCannons()) {
            this.triggerCannonAdvanced(cannon, shootVec, yShootVec, driverEntity, speed, accuracy);
         }
      }
   }

   default void triggerCannonAdvanced(ShipCannon cannon, class_243 shootVec, double yShootVec, class_1309 driverEntity, double speed, double accuracy) {
      if (cannon.canShootDirection()) {
         cannon.trigger(shootVec, yShootVec, driverEntity, speed, accuracy);
      }
   }

   default void readCannonShipSaveData(class_2487 tag) {
      if (tag.method_10545("CannonCount")) {
         this.setCannonCount(tag.method_10571("CannonCount"));
         this.updateCannonCount();
      }
   }

   default void addCannonShipSaveData(class_2487 tag) {
      tag.method_10569("CannonCount", this.getCannonCount());
   }

   default float getCannonModifier() {
      return this.getCannonCount() * ((Double)SmallShipsConfig.Common.shipGeneralCannonModifier.get()).floatValue();
   }

   default void updateCannonCount() {
      byte cannons = this.getCannonCount();
      this.getCannons().clear();

      for (int i = 0; i < cannons; i++) {
         Cannonable.CannonPosition cannonPosition = this.getCannonPosition(i);
         if (cannonPosition != null) {
            ShipCannon cannon = new ShipCannon(this.self(), cannonPosition);
            this.getCannons().add(cannon);
         }
      }

      this.setCannonCount(cannons);
   }

   default boolean interactCannon(class_1657 player, class_1268 interactionHand) {
      class_1799 item = player.method_5998(interactionHand);
      byte cannonCount = this.getCannonCount();
      if (item.method_7909() == ModItems.CANNON && this.self() instanceof ContainerShip) {
         if (cannonCount >= this.getMaxCannonPerSide() * 2) {
            return false;
         } else {
            this.setCannonCount((byte)(cannonCount + 1));
            this.self()
               .method_37908()
               .method_43128(
                  player,
                  this.self().method_23317(),
                  this.self().method_23318() + 4.0,
                  this.self().method_23321(),
                  (class_3414)class_3417.field_15191.comp_349(),
                  this.self().method_5634(),
                  15.0F,
                  1.5F
               );
            if (!player.method_7337()) {
               item.method_7934(1);
            }

            this.updateCannonCount();
            return true;
         }
      } else if (item.method_7909() instanceof class_1743 && cannonCount > 0) {
         this.setCannonCount((byte)(cannonCount - 1));
         this.self().method_5706(ModItems.CANNON);
         this.self()
            .method_37908()
            .method_43128(
               player,
               this.self().method_23317(),
               this.self().method_23318() + 4.0,
               this.self().method_23321(),
               (class_3414)class_3417.field_15191.comp_349(),
               this.self().method_5634(),
               15.0F,
               1.0F
            );
         return true;
      } else {
         return false;
      }
   }

   default boolean canShoot() {
      if (this.self() instanceof class_7265 containerEntity) {
         return containerEntity.method_42278().stream().anyMatch(itemStack -> itemStack.method_7909().equals(ModItems.CANNON_BALL));
      } else {
         return this.self().method_5642() instanceof class_1657 player
            ? player.method_31548().field_7547.stream().anyMatch(itemStack -> itemStack.method_7909().equals(ModItems.CANNON_BALL))
            : false;
      }
   }

   default void consumeCannonBall() {
      if (this.self() instanceof class_7265 containerEntity) {
         for (class_1799 itemstack : containerEntity.method_42278()) {
            if (itemstack.method_31574(ModItems.CANNON_BALL)) {
               itemstack.method_7934(1);
               break;
            }
         }
      } else if (this.self().method_5642() instanceof class_1657 player) {
         for (class_1799 itemstackx : player.method_31548().field_7547) {
            if (itemstackx.method_31574(ModItems.CANNON_BALL)) {
               itemstackx.method_7934(1);
               break;
            }
         }
      }
   }

   default class_2960 getTextureLocation() {
      return class_2960.method_60655("smallships", "textures/entity/cannon/ship_cannon.png");
   }

   default void setCannonCount(byte x) {
      this.self().method_5841().method_12778(Ship.CANNON_COUNT, x);
   }

   default byte getCannonCount() {
      return (Byte)this.self().method_5841().method_12789(Ship.CANNON_COUNT);
   }

   default List<ShipCannon> getCannons() {
      return this.self().CANNONS;
   }

   default void cannonShipDestroyed(class_1937 level, Ship ship) {
      for (int i = 0; i < this.getCannonCount(); i++) {
         ship.method_5870(ModItems.CANNON, 4);
      }
   }

   public static class CannonPosition {
      public final double x;
      public final double y;
      public final double z;
      public final boolean isRightSided;

      public CannonPosition(double x, double y, double z, boolean isRightSided) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.isRightSided = isRightSided;
      }
   }
}
