package com.talhanation.smallships.world.entity.ship.abilities;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.world.entity.ship.Ship;
import com.talhanation.smallships.world.sound.ModSoundTypes;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1769;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_3417;

public interface Sailable extends Ability {
   default void tickSailShip() {
      if (this.self().sailStateCooldown > 0) {
         this.self().sailStateCooldown--;
      }
   }

   default void readSailShipSaveData(class_2487 tag) {
      class_2487 compoundTag = tag.method_10562("Sail");
      this.self().setData(Ship.SAIL_STATE, compoundTag.method_10571("State"));
      this.self().setData(Ship.SAIL_COLOR, compoundTag.method_10558("Color"));
   }

   default void addSailShipSaveData(class_2487 tag) {
      class_2487 compoundTag = new class_2487();
      compoundTag.method_10569("State", this.self().<Byte>getData(Ship.SAIL_STATE));
      compoundTag.method_10582("Color", this.self().getData(Ship.SAIL_COLOR));
      tag.method_10566("Sail", compoundTag);
   }

   default void controlBoatSailShip() {
      byte sailState = this.getSailState();
      if (sailState != 0) {
         if (this.self().isForward() && sailState != 4 && this.self().sailStateCooldown == 0) {
            sailState++;
            if (!this.self().method_37908().method_8608()) {
               this.playSailSound(sailState);
            }

            this.self().sailStateCooldown = this.getSailStateCooldown();
            this.setSailState(sailState);
         }

         if (this.self().isBackward() && sailState != 1 && this.self().sailStateCooldown == 0) {
            sailState--;
            if (!this.self().method_37908().method_8608()) {
               this.playSailSound(sailState);
            }

            this.self().sailStateCooldown = this.getSailStateCooldown();
            this.setSailState(sailState);
         }
      }
   }

   default boolean interactSail(class_1657 player, class_1268 interactionHand) {
      class_1799 item = player.method_5998(interactionHand);
      if (item.method_7909() instanceof class_1769 dyeItem) {
         String color = dyeItem.method_7802().method_7792();
         if (color.equals(this.self().getData(Ship.SAIL_COLOR))) {
            return false;
         } else {
            this.self().setData(Ship.SAIL_COLOR, color);
            if (!player.method_7337()) {
               item.method_7934(1);
            }

            this.self()
               .method_37908()
               .method_43128(
                  player,
                  this.self().method_23317(),
                  this.self().method_23318() + 4.0,
                  this.self().method_23321(),
                  class_3417.field_14628,
                  this.self().method_5634(),
                  15.0F,
                  1.5F
               );
            return true;
         }
      } else {
         return false;
      }
   }

   default void toggleSail() {
      if (!this.self().isShipLeashed()) {
         byte state = this.getSailState();
         if (state > 0) {
            state = 0;
         } else {
            state = 1;
         }

         this.setSailState(state);
         this.playSailSound(state);
      }
   }

   default void playSailSound(int state) {
      if (state != 0) {
         this.self().method_5783(ModSoundTypes.SAIL_MOVE, 15.0F, Math.max(0.5F, 1.4F - state / 5.0F));
      } else {
         this.self().method_5783(ModSoundTypes.SAIL_PULL, 10.0F, 1.0F);
      }
   }

   default int getSailStateCooldown() {
      return (Integer)SmallShipsConfig.Common.shipGeneralSailCooldown.get();
   }

   default void setSailState(byte state) {
      this.self().setData(Ship.SAIL_STATE, state);
   }

   default byte getSailState() {
      return this.self().<Byte>getData(Ship.SAIL_STATE);
   }
}
