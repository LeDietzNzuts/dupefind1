package com.magistuarmory.item;

import com.magistuarmory.EpicKnights;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;

public class WeaponType {
   @RequiresRestart
   private float baseAttackDamage;
   @RequiresRestart
   private float baseAttackSpeed;
   @RequiresRestart
   private float bonusAttackReach;
   @RequiresRestart
   private float sizeFactor;
   @RequiresRestart
   private float weight;
   @RequiresRestart
   private int armorPiercing;
   @RequiresRestart
   private int twoHanded;
   @RequiresRestart
   private float maxBlockDamage;
   @RequiresRestart
   private boolean canBlock;
   @RequiresRestart
   private boolean isFlamebladed;
   @RequiresRestart
   private boolean isHalberd;
   @CollapsibleObject
   private boolean enabled;

   public WeaponType() {
   }

   public static WeaponType of(
      float baseAttackDamage,
      float baseAttackSpeed,
      float bonusAttackReach,
      float sizeFactor,
      float weight,
      int armorPiercing,
      boolean enabled,
      int twoHanded,
      float maxBlockDamage,
      boolean canBlock,
      boolean isFlamebladed,
      boolean isHalberd
   ) {
      WeaponType type = new WeaponType();
      type.baseAttackDamage = baseAttackDamage;
      type.baseAttackSpeed = baseAttackSpeed;
      type.bonusAttackReach = bonusAttackReach;
      type.sizeFactor = sizeFactor;
      type.weight = weight;
      type.armorPiercing = armorPiercing;
      type.enabled = enabled;
      type.twoHanded = twoHanded;
      type.maxBlockDamage = maxBlockDamage;
      type.canBlock = canBlock;
      type.isFlamebladed = isFlamebladed;
      type.isHalberd = isHalberd;
      return type;
   }

   public static WeaponType of(
      float baseAttackDamage, float baseAttackSpeed, float bonusAttackReach, float sizeFactor, float weight, int armorPiercing, boolean enabled
   ) {
      return of(baseAttackDamage, baseAttackSpeed, bonusAttackReach, sizeFactor, weight, armorPiercing, enabled, 0, 0.0F, false, false, false);
   }

   @Deprecated(forRemoval = true)
   public WeaponType(float baseAttackDamage, float baseAttackSpeed, float bonusAttackReach, float sizeFactor, float weight, int armorPiercing, boolean enabled) {
      this.baseAttackDamage = baseAttackDamage;
      this.baseAttackSpeed = baseAttackSpeed;
      this.bonusAttackReach = bonusAttackReach;
      this.sizeFactor = sizeFactor;
      this.weight = weight;
      this.armorPiercing = armorPiercing;
      this.enabled = enabled;
      this.twoHanded = 0;
      this.maxBlockDamage = 0.0F;
      this.canBlock = false;
      this.isFlamebladed = false;
      this.isHalberd = false;
   }

   @Deprecated(forRemoval = true)
   public WeaponType setFlamebladed() {
      this.isFlamebladed = true;
      return this;
   }

   @Deprecated(forRemoval = true)
   public WeaponType setTwoHanded(int level) {
      this.twoHanded = level;
      return this;
   }

   @Deprecated(forRemoval = true)
   public WeaponType setMaxBlockDamage(float maxBlockDamage) {
      this.maxBlockDamage = maxBlockDamage;
      this.canBlock = true;
      return this;
   }

   @Deprecated(forRemoval = true)
   public WeaponType setHalberd() {
      this.isHalberd = true;
      return this;
   }

   public float getAttackSpeed(ModItemTier material) {
      return -material.getDensity() * this.getSizeFactor() + this.getBaseAttackSpeed();
   }

   public float getBaseAttackDamage() {
      return this.baseAttackDamage;
   }

   public float getBaseAttackSpeed() {
      return this.baseAttackSpeed;
   }

   public float getSizeFactor() {
      return this.sizeFactor;
   }

   public float getWeight() {
      return this.weight;
   }

   public int getArmorPiercing() {
      return !EpicKnights.GENERAL_CONFIG.disableArmorPiercing ? this.armorPiercing : 0;
   }

   public float getBonusAttackReach() {
      return !EpicKnights.GENERAL_CONFIG.disableAttackReach ? this.bonusAttackReach : 0.0F;
   }

   public int getTwoHanded() {
      return !EpicKnights.GENERAL_CONFIG.disableTwoHanded ? this.twoHanded : 0;
   }

   public float getMaxBlockDamage() {
      return !EpicKnights.GENERAL_CONFIG.disableWeaponBlocking ? this.maxBlockDamage : 0.0F;
   }

   public boolean canBlock() {
      return !EpicKnights.GENERAL_CONFIG.disableWeaponBlocking && this.canBlock;
   }

   public boolean isFlamebladed() {
      return !EpicKnights.GENERAL_CONFIG.disableLaceration && this.isFlamebladed;
   }

   public boolean isHalberd() {
      return this.isHalberd;
   }

   public boolean isDisabled() {
      return !this.enabled;
   }

   public int getDurability(ModItemTier material) {
      return (int)(material.method_8025() * (1.0F + this.getSizeFactor() * 5.0F));
   }
}
