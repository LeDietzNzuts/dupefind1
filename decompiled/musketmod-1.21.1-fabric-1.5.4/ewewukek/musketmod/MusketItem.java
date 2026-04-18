package ewewukek.musketmod;

import net.minecraft.class_1322;
import net.minecraft.class_1799;
import net.minecraft.class_3414;
import net.minecraft.class_5134;
import net.minecraft.class_9274;
import net.minecraft.class_9285;
import net.minecraft.class_1322.class_1323;
import net.minecraft.class_1792.class_1793;

public class MusketItem extends GunItem {
   public MusketItem(class_1793 properties) {
      super(properties);
   }

   public static class_9285 createBayonetAttributes() {
      return class_9285.method_57480()
         .method_57487(class_5134.field_23721, new class_1322(field_8006, Config.bayonetDamage - 1, class_1323.field_6328), class_9274.field_49217)
         .method_57487(class_5134.field_23723, new class_1322(field_8001, Config.bayonetSpeed - 4.0F, class_1323.field_6328), class_9274.field_49217)
         .method_57486();
   }

   @Override
   public float bulletStdDev() {
      return Config.musketBulletStdDev;
   }

   @Override
   public float bulletSpeed() {
      return Config.musketBulletSpeed;
   }

   @Override
   public float damage() {
      return Config.musketDamage;
   }

   @Override
   public class_3414 fireSound(class_1799 stack) {
      return Sounds.MUSKET_FIRE;
   }
}
