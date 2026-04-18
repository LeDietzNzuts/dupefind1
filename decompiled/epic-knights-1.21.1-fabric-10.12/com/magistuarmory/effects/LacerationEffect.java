package com.magistuarmory.effects;

import com.magistuarmory.util.CombatHelper;
import net.minecraft.class_1282;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1309;
import net.minecraft.class_1548;
import net.minecraft.class_2960;
import net.minecraft.class_4081;
import net.minecraft.class_5134;
import net.minecraft.class_1322.class_1323;
import org.jetbrains.annotations.NotNull;

public class LacerationEffect extends class_1291 {
   public LacerationEffect() {
      super(class_4081.field_18272, -10092544);
      this.method_5566(class_5134.field_23716, class_2960.method_60655("magistuarmory", "laceration"), -2.0, class_1323.field_6328);
   }

   @NotNull
   public String method_5567() {
      return "effect.laceration";
   }

   public boolean method_5573() {
      return false;
   }

   public boolean method_5572(class_1309 victim, int i) {
      if (victim.method_6032() > victim.method_6063()) {
         victim.method_6033(victim.method_6063());
         return true;
      } else {
         return false;
      }
   }

   public static void apply(class_1282 source, class_1309 victim, float damage) {
      damage = CombatHelper.getDamageAfterAbsorb(source, victim, damage);
      if (!(victim instanceof class_1548)) {
         int amplifier = Math.min((int)damage, 2);
         int duration = (int)(damage * 50.0F);
         if (victim.method_6059(ModEffects.LACERATION)) {
            class_1293 effect = victim.method_6112(ModEffects.LACERATION);
            amplifier = Math.max(effect.method_5578(), amplifier);
            duration = Math.max(effect.method_5584(), duration);
            victim.method_6016(ModEffects.LACERATION);
         }

         victim.method_6092(new class_1293(ModEffects.LACERATION, duration, amplifier, true, true, true));
      }
   }
}
