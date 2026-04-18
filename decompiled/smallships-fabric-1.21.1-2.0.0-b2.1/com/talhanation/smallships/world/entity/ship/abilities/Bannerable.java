package com.talhanation.smallships.world.entity.ship.abilities;

import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1746;
import net.minecraft.class_1799;
import net.minecraft.class_1820;
import net.minecraft.class_2487;
import net.minecraft.class_3417;
import net.minecraft.class_3532;

public interface Bannerable extends Ability {
   Bannerable.BannerPosition getBannerPosition();

   default void tickBannerShip() {
      if (!this.self().<class_1799>getData(Ship.BANNER).method_7960()) {
         this.self().prevBannerWaveAngle = this.self().bannerWaveAngle;
         this.self().bannerWaveAngle = (float)Math.sin(this.getBannerWaveSpeed() * this.self().field_6012) * this.getBannerWaveFactor();
      }
   }

   default void readBannerShipSaveData(class_2487 tag) {
      if (tag.method_10580("Banner") instanceof class_2487 bannerCompound) {
         this.self().setData(Ship.BANNER, class_1799.method_57360(this.self().method_56673(), bannerCompound).orElse(class_1799.field_8037));
      }
   }

   default void addBannerShipSaveData(class_2487 tag) {
      if (!this.self().<class_1799>getData(Ship.BANNER).method_7960()) {
         tag.method_10566("Banner", this.self().<class_1799>getData(Ship.BANNER).method_57358(this.self().method_56673()));
      }
   }

   default boolean interactBanner(class_1657 player, class_1268 interactionHand) {
      class_1799 item = player.method_5998(interactionHand);
      class_1799 shipBanner = this.self().getData(Ship.BANNER);
      shipBanner.method_7939(1);
      if (item.method_7909() instanceof class_1746) {
         if (!shipBanner.method_7960()) {
            this.self().method_5699(shipBanner, 4.0F);
         }

         this.self().setData(Ship.BANNER, item.method_7972());
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
               1.0F
            );
         return true;
      } else if (item.method_7909() instanceof class_1820 && !shipBanner.method_7960()) {
         this.self().method_5699(shipBanner, 4.0F);
         this.self().setData(Ship.BANNER, class_1799.field_8037);
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
               1.0F
            );
         return true;
      } else {
         return false;
      }
   }

   default float getBannerWaveFactor() {
      return this.self().method_37908().method_8419() ? 4.5F : 3.0F;
   }

   default float getBannerWaveSpeed() {
      return this.self().method_37908().method_8419() ? 0.55F : 0.25F;
   }

   default float getBannerWaveAngle(float partialTicks) {
      return class_3532.method_16439(partialTicks, this.self().prevBannerWaveAngle, this.self().bannerWaveAngle);
   }

   public static class BannerPosition {
      public final float yp;
      public final float zp;
      public final double x;
      public final double y;
      public final double z;

      public BannerPosition(float yp, float zp, double x, double y, double z) {
         this.yp = yp;
         this.zp = zp;
         this.x = x;
         this.y = y;
         this.z = z;
      }
   }
}
