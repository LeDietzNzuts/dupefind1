package com.magistuarmory.item;

import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1685;
import net.minecraft.class_1799;
import net.minecraft.class_1937;

public class ThrownProjectile extends class_1685 {
   public ThrownProjectile(class_1299<? extends class_1685> entityType, class_1937 level) {
      super(entityType, level);
   }

   public ThrownProjectile(class_1937 level, class_1309 livingEntity, class_1799 itemStack) {
      super(level, livingEntity, itemStack);
   }

   public ThrownProjectile(class_1937 level, double x, double y, double z, class_1799 itemStack) {
      super(level, x, y, z, itemStack);
   }
}
