package ewewukek.musketmod;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2315;
import net.minecraft.class_2342;
import net.minecraft.class_2347;
import net.minecraft.class_2350;
import net.minecraft.class_2357;
import net.minecraft.class_2374;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3419;
import net.minecraft.class_1792.class_1793;

public class CartridgeItem extends class_1792 {
   public static final class_2357 DISPENSE_ITEM_BEHAVIOR = new class_2347() {
      public class_1799 method_10135(class_2342 blockSource, class_1799 stack) {
         class_3218 level = blockSource.comp_1967();
         class_2350 blockDirection = (class_2350)blockSource.comp_1969().method_11654(class_2315.field_10918);
         class_243 direction = new class_243(blockDirection.method_10148(), blockDirection.method_10164(), blockDirection.method_10165());
         class_2374 position = class_2315.method_58682(blockSource);
         class_243 origin = new class_243(position.method_10216(), position.method_10214(), position.method_10215());
         direction = GunItem.addSpread(direction, level.method_8409(), Config.dispenserBulletStdDev);
         BulletEntity bullet = new BulletEntity(level);
         bullet.method_33574(origin);
         bullet.setVelocity(Config.dispenserBulletSpeed, direction);
         bullet.damage = Config.dispenserDamage;
         level.method_8649(bullet);
         level.method_43128(
            null, origin.method_10216(), origin.method_10214(), origin.method_10215(), Sounds.DISPENSER_FIRE, class_3419.field_15245, 2.5F, 1.0F
         );
         MusketMod.sendSmokeEffect(level, origin, direction);
         stack.method_7934(1);
         return stack;
      }
   };

   public CartridgeItem(class_1793 properties) {
      super(properties);
      class_2315.method_10009(this, DISPENSE_ITEM_BEHAVIOR);
   }
}
