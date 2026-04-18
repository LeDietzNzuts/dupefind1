package ewewukek.musketmod;

import net.minecraft.class_1799;
import net.minecraft.class_3414;
import net.minecraft.class_1792.class_1793;

public class PistolItem extends GunItem {
   public PistolItem(class_1793 properties) {
      super(properties);
   }

   @Override
   public float bulletStdDev() {
      return Config.pistolBulletStdDev;
   }

   @Override
   public float bulletSpeed() {
      return Config.pistolBulletSpeed;
   }

   @Override
   public float damage() {
      return Config.pistolDamage;
   }

   @Override
   public class_3414 fireSound(class_1799 stack) {
      return Sounds.PISTOL_FIRE;
   }

   @Override
   public boolean twoHanded() {
      return false;
   }
}
