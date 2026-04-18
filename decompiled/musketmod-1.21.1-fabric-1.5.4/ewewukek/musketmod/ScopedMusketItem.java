package ewewukek.musketmod;

import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_1792.class_1793;

public class ScopedMusketItem extends MusketItem {
   public static final int RECOIL_TICKS = 12;
   public static final float RECOIL_AMOUNT = 0.25F;
   public static boolean isScoping;
   public static int recoilTicks;

   public ScopedMusketItem(class_1793 properties) {
      super(properties);
   }

   @Override
   public float bulletStdDev() {
      return Config.scopedMusketBulletStdDev;
   }

   @Override
   public float bulletDropReduction() {
      return 1.0F - Config.bulletGravityMultiplier;
   }

   @Override
   public int hitDurabilityDamage() {
      return 2;
   }

   @Override
   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      boolean wasLoaded = GunItem.isLoaded(player.method_5998(hand));
      class_1271<class_1799> result = super.method_7836(level, player, hand);
      if (level.field_9236 && wasLoaded && result.method_5467().method_23665()) {
         recoilTicks = 12;
      }

      return result;
   }
}
