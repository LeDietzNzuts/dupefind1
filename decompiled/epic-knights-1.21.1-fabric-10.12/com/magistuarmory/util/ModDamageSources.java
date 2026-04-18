package com.magistuarmory.util;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_2378;
import net.minecraft.class_5455;
import net.minecraft.class_7924;
import net.minecraft.class_8110;
import net.minecraft.class_8111;

public class ModDamageSources {
   private static class_2378<class_8110> DAMAGE_TYPES;
   private static class_1282 ADDITIONAL;

   public static void setup(class_5455 registryAccess) {
      DAMAGE_TYPES = registryAccess.method_30530(class_7924.field_42534);
      ADDITIONAL = new class_1282(DAMAGE_TYPES.method_40290(ModDamageTypes.ADDITIONAL));
   }

   public static class_1282 additional() {
      return ADDITIONAL;
   }

   public static class_1282 additional(class_1297 attacker) {
      return attacker == null ? additional() : new class_1282(DAMAGE_TYPES.method_40290(ModDamageTypes.ENTITY_ADDITIONAL), attacker);
   }

   public static class_1282 silverAttack(class_1297 attacker) {
      return new class_1282(DAMAGE_TYPES.method_40290(ModDamageTypes.SILVER), attacker);
   }

   public static class_1282 armorPiercing(class_1297 attacker) {
      return new class_1282(DAMAGE_TYPES.method_40290(ModDamageTypes.ARMOR_PIERCING), attacker);
   }

   public static boolean isAdditional(class_1282 source) throws NullPointerException {
      if (source == null) {
         throw new NullPointerException("Got a null damage source");
      } else {
         return source.method_49708(class_8111.field_42348)
            || source.method_49708(class_8111.field_42349)
            || source.method_49708(ModDamageTypes.ADDITIONAL)
            || source.method_49708(ModDamageTypes.ENTITY_ADDITIONAL)
            || source.method_49708(ModDamageTypes.SILVER)
            || source.method_49708(ModDamageTypes.ARMOR_PIERCING);
      }
   }
}
