package com.magistuarmory.item;

import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1676;
import net.minecraft.class_1799;
import net.minecraft.class_1835;
import net.minecraft.class_1890;
import net.minecraft.class_1937;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_6880;
import net.minecraft.class_9701;
import net.minecraft.class_1665.class_1666;
import net.minecraft.class_1792.class_1793;

public class ThrowingWeaponItem extends class_1835 {
   private final float shootThresholdTime;
   private final float baseDamage;
   private final float projectileSpeed;
   private final boolean spinning;

   public ThrowingWeaponItem(class_1793 properties, float shootThresholdTime, float baseDamage, float projectileSpeed, boolean spinning) {
      super(properties);
      this.shootThresholdTime = shootThresholdTime;
      this.baseDamage = baseDamage;
      this.projectileSpeed = projectileSpeed;
      this.spinning = spinning;
   }

   public void method_7840(class_1799 stack, class_1937 level, class_1309 shooter, int i) {
      int duration = this.method_7881(stack, shooter) - i;
      if (duration >= this.shootThresholdTime && !isTooDamagedToUse(stack)) {
         if (!level.field_9236) {
            stack.method_7970(1, shooter, class_1309.method_56079(shooter.method_6058()));
            ThrownProjectile projectile = new ThrownProjectile(level, shooter, stack);
            projectile.method_24919(shooter, shooter.method_36455(), shooter.method_36454(), 0.0F, this.projectileSpeed, 1.0F);
            if (shooter.method_56992()) {
               projectile.field_7572 = class_1666.field_7594;
            }

            level.method_8649(projectile);
            class_6880<class_3414> holder = class_1890.method_60165(stack, class_9701.field_51654).orElse(class_3417.field_15001);
            level.method_43129(null, projectile, (class_3414)holder.comp_349(), class_3419.field_15248, 1.0F, 1.0F);
            if (shooter instanceof class_1657 player && !player.method_56992()) {
               player.method_31548().method_7378(stack);
            }
         }

         if (shooter instanceof class_1657 player) {
            player.method_7259(class_3468.field_15372.method_14956(this));
         }
      }
   }

   private static boolean isTooDamagedToUse(class_1799 stack) {
      return stack.method_7919() >= stack.method_7936() - 1;
   }

   public class_1676 method_58648(class_1937 level, class_2374 position, class_1799 itemStack, class_2350 direction) {
      ThrownProjectile thrownProjectile = new ThrownProjectile(
         level, position.method_10216(), position.method_10214(), position.method_10215(), itemStack.method_46651(1)
      );
      thrownProjectile.field_7572 = class_1666.field_7593;
      return thrownProjectile;
   }
}
