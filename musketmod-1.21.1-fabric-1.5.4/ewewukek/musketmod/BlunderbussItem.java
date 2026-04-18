package ewewukek.musketmod;

import net.minecraft.class_1799;
import net.minecraft.class_3414;
import net.minecraft.class_1792.class_1793;

public class BlunderbussItem extends GunItem {
   public BlunderbussItem(class_1793 properties) {
      super(properties);
   }

   @Override
   public float bulletStdDev() {
      return Config.blunderbussBulletStdDev;
   }

   @Override
   public float bulletSpeed() {
      return Config.blunderbussBulletSpeed;
   }

   @Override
   public int pelletCount() {
      return Config.blunderbussPelletCount;
   }

   @Override
   public float damage() {
      return Config.blunderbussDamage;
   }

   @Override
   public class_3414 fireSound(class_1799 stack) {
      return hasFlame(stack) ? Sounds.BLUNDERBUSS_FIRE_FLAME : Sounds.BLUNDERBUSS_FIRE;
   }
}
