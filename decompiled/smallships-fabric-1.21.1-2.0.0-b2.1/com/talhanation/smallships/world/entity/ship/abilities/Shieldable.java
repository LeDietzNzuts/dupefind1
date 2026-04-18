package com.talhanation.smallships.world.entity.ship.abilities;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.world.entity.ship.Ship;
import java.util.Stack;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3417;
import net.minecraft.class_2945.class_9222;

public interface Shieldable extends Ability {
   Shieldable.ShieldPosition getShieldPosition(int var1);

   byte getMaxShieldsPerSide();

   default void tickShieldShip() {
   }

   default void defineShieldShipSynchedData(class_9222 builder) {
      builder.method_56912(Ship.SHIELD_DATA, new class_2487());
   }

   default void readShieldShipSaveData(class_2487 tag) {
      class_2499 shieldItems = tag.method_10554("Shields", 10);

      for (int i = 0; i < shieldItems.size(); i++) {
         class_2487 compoundTag = shieldItems.method_10602(i);
         class_1799 itemStack = class_1799.method_57360(this.self().method_56673(), compoundTag).orElse(class_1799.field_8037);
         if (!itemStack.method_7960()) {
            this.self().SHIELDS.push(itemStack);
         }
      }

      this.self().setShieldData(tag);
   }

   default void addShieldShipSaveData(class_2487 tag) {
      class_2499 listTag = new class_2499();

      for (int i = 0; i < this.self().SHIELDS.size(); i++) {
         class_1799 itemstack = this.self().SHIELDS.get(i);
         if (!itemstack.method_7960()) {
            class_2487 inTag = new class_2487();
            inTag.method_10567("Shields", (byte)i);
            class_2520 itemTag = itemstack.method_57376(this.self().method_56673(), inTag);
            listTag.add(itemTag);
         }
      }

      tag.method_10566("Shields", listTag);
      this.self().setShieldData(tag);
   }

   default Stack<class_1799> getShields() {
      if (this.self().SHIELDS.isEmpty() && !this.self().getShieldData().method_33133() && this.self().method_5770().method_8608()) {
         this.readShieldShipSaveData(this.self().getShieldData());
      }

      return this.self().SHIELDS;
   }

   default float getDamageModifier() {
      return (float)(1.0 - this.getShields().size() * (Double)SmallShipsConfig.Common.shipGeneralShieldDamageReduction.get() / 100.0);
   }

   default boolean interactShield(class_1657 player, class_1268 interactionHand) {
      class_1799 itemStack = player.method_5998(interactionHand);
      int shieldCount = this.getShields().size();
      if (itemStack.method_31574(class_1802.field_8255)) {
         if (shieldCount >= this.getMaxShieldsPerSide() * 2) {
            return false;
         } else {
            this.getShields().push(itemStack.method_7972());
            if (!player.method_7337()) {
               itemStack.method_7934(1);
            }

            this.self()
               .method_5770()
               .method_43128(
                  player,
                  this.self().method_23317(),
                  this.self().method_23318() + 4.0,
                  this.self().method_23321(),
                  class_3417.field_14808,
                  this.self().method_5634(),
                  15.0F,
                  1.5F
               );
            return true;
         }
      } else if (itemStack.method_7909() instanceof class_1743 && shieldCount > 0) {
         class_1799 removedShield = this.getShields().pop();
         this.self().method_5699(removedShield, 2.0F);
         this.self()
            .method_5770()
            .method_43128(
               player,
               this.self().method_23317(),
               this.self().method_23318() + 4.0,
               this.self().method_23321(),
               class_3417.field_14808,
               this.self().method_5634(),
               15.0F,
               1.0F
            );
         return true;
      } else {
         return false;
      }
   }

   public static class ShieldPosition {
      public final double x;
      public final double y;
      public final double z;
      public final boolean isRightSided;

      public ShieldPosition(double x, double y, double z, boolean isRightSided) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.isRightSided = isRightSided;
      }
   }
}
