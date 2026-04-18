package com.magistuarmory.util;

import com.magistuarmory.item.MedievalWeaponItem;
import com.magistuarmory.item.ModItemTier;
import com.magistuarmory.item.WeaponType;
import net.minecraft.class_1280;
import net.minecraft.class_1282;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1890;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3468;
import net.minecraft.class_5134;
import net.minecraft.class_8103;

public class CombatHelper {
   public static float getAttackReach(class_1657 player, MedievalWeaponItem weapon) {
      return weapon.getAttackReach(getBaseAttackReach(player));
   }

   public static float getBaseAttackReach(class_1657 player) {
      return player.method_7337() ? 5.0F : 4.5F;
   }

   public static float getBaseAttackDamage(ModItemTier material, WeaponType type) {
      return type.getBaseAttackDamage() + 1.6F * material.method_8028() / type.getBaseAttackSpeed();
   }

   public static float getBaseAttackSpeed(ModItemTier material, WeaponType type) {
      return type.getAttackSpeed(material) - 4.0F;
   }

   public static float getDecreasedAttackDamage(ModItemTier material, WeaponType type) {
      return getDecreasedAttackDamage(getBaseAttackDamage(material, type), type);
   }

   public static float getDecreasedAttackSpeed(ModItemTier material, WeaponType type) {
      return getDecreasedAttackSpeed(getBaseAttackSpeed(material, type), type);
   }

   public static float getDecreasedAttackDamage(float baseattackdamage, WeaponType type) {
      return type.getTwoHanded() > 1 ? 14.0F * baseattackdamage / (3.0F * type.getTwoHanded() + 12.0F) : baseattackdamage;
   }

   public static float getDecreasedAttackSpeed(float baseattackspeed, WeaponType type) {
      return 14.0F * (baseattackspeed + 4.0F) / (5.0F * type.getTwoHanded() + 10.0F) - 4.0F;
   }

   public static float getSilverAttackDamage(ModItemTier material, WeaponType type) {
      return Math.round(6.0F / type.getAttackSpeed(material) * 100.0F) / 100.0F;
   }

   public static float getArmorPiercingFactor(class_1297 attacker) {
      float f = 1.0F;
      if (attacker instanceof class_1309 livingentity && livingentity.method_6047().method_7909() instanceof MedievalWeaponItem weapon) {
         float f2 = weapon.type.getArmorPiercing() / 100.0F;
         f = f2 / (1.0F - f2);
      }

      return f;
   }

   public static float getDamageAfterArmorAbsorb(class_1282 source, class_1309 victim, float damage) {
      if (!source.method_48789(class_8103.field_42241)) {
         damage = class_1280.method_5496(victim, damage, source, victim.method_6096(), (float)victim.method_45325(class_5134.field_23725));
      }

      return damage;
   }

   public static float getDamageAfterMagicAbsorb(class_1282 source, class_1309 victim, float damage) {
      if (source.method_48789(class_8103.field_42243)) {
         return damage;
      } else {
         if (victim.method_6059(class_1294.field_5907) && !source.method_48789(class_8103.field_42244)) {
            float k = (victim.method_6112(class_1294.field_5907).method_5578() + 1.0F) * 5.0F;
            float j = 25.0F - k;
            float f = damage * j;
            float f1 = damage;
            damage = Math.max(f / 25.0F, 0.0F);
            float f2 = f1 - damage;
            if (f2 > 0.0F && f2 < 3.4028235E37F) {
               if (victim instanceof class_3222) {
                  ((class_3222)victim).method_7342(class_3468.field_15419.method_14956(class_3468.field_15425), Math.round(f2 * 10.0F));
               } else if (source.method_5529() instanceof class_3222) {
                  ((class_3222)source.method_5529()).method_7342(class_3468.field_15419.method_14956(class_3468.field_15397), Math.round(f2 * 10.0F));
               }
            }
         }

         if (damage <= 0.0F) {
            return 0.0F;
         } else if (source.method_48789(class_8103.field_42245)) {
            return damage;
         } else {
            float k = class_1890.method_8219((class_3218)victim.method_37908(), victim, source);
            if (k > 0.0F) {
               damage = class_1280.method_5497(damage, k);
            }

            return damage;
         }
      }
   }

   public static float getDamageAfterAbsorb(class_1282 source, class_1309 victim, float damage) {
      if (!victim.method_5679(source) && !(damage <= 0.0F)) {
         damage = getDamageAfterArmorAbsorb(source, victim, damage);
         damage = getDamageAfterMagicAbsorb(source, victim, damage);
         float f1 = Math.max(damage - victim.method_6067(), 0.0F);
         victim.method_6073(victim.method_6067() - (damage - f1));
         float f = damage - f1;
         if (f > 0.0F && f < 3.4028235E37F && source.method_5529() instanceof class_3222 serverplayer) {
            serverplayer.method_7339(class_3468.field_15408, Math.round(f * 10.0F));
         }

         return f1;
      } else {
         return 0.0F;
      }
   }
}
