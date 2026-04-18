package vectorwing.farmersdelight.common.item.enchantment;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import org.apache.commons.lang3.mutable.MutableFloat;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class BackstabbingEnchantment {
   public static boolean isLookingBehindTarget(class_1309 target, class_243 attackerLocation) {
      if (attackerLocation != null) {
         class_243 lookingVector = target.method_5828(1.0F);
         class_243 attackAngleVector = attackerLocation.method_1020(target.method_19538()).method_1029();
         attackAngleVector = new class_243(attackAngleVector.field_1352, 0.0, attackAngleVector.field_1350);
         return attackAngleVector.method_1026(lookingVector) < -0.5;
      } else {
         return false;
      }
   }

   public static float getBackstabbingDamagePerLevel(float amount, int level) {
      float multiplier = level * 0.2F + 1.2F;
      return amount * multiplier;
   }

   public static class BackstabbingEvent {
      public static float onKnifeBackstab(class_1309 entity, class_1282 source, float amount) {
         class_1297 attacker = source.method_5529();
         if (attacker instanceof class_1309 living
            && BackstabbingEnchantment.isLookingBehindTarget(entity, source.method_5510())
            && attacker.method_37908() instanceof class_3218 serverLevel) {
            class_1799 weapon = living.method_59958();
            MutableFloat dmg = new MutableFloat(amount);
            class_1890.method_8220(
               weapon,
               (enchantment, powerLevel) -> ((class_1887)enchantment.comp_349())
                  .method_60035(ModDataComponents.BACKSTABBING.get(), serverLevel, powerLevel, weapon, attacker, source, dmg)
            );
            if (amount != dmg.getValue()) {
               amount = dmg.getValue();
               serverLevel.method_43128(
                  null, attacker.method_23317(), attacker.method_23318(), attacker.method_23321(), class_3417.field_15016, class_3419.field_15245, 1.0F, 1.0F
               );
            }
         }

         return amount;
      }
   }
}
