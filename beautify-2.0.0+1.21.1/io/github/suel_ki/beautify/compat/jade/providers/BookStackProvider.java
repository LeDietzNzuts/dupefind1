package io.github.suel_ki.beautify.compat.jade.providers;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import snownee.jade.api.platform.CustomEnchantPower;

public enum BookStackProvider implements CustomEnchantPower {
   INSTANCE;

   public float getEnchantPowerBonus(class_2680 state, class_1937 level, class_2338 pos) {
      return 1.0F;
   }
}
