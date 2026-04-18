package com.magistuarmory.item;

import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
import net.minecraft.class_1832;

public class ShieldType {
   @RequiresRestart
   private int baseDurability;
   @RequiresRestart
   private float materialFactor;
   @RequiresRestart
   private float weight;
   @RequiresRestart
   private float maxBlockDamage;
   @RequiresRestart
   private boolean repairable = false;
   @CollapsibleObject
   private boolean enabled = true;

   public ShieldType() {
   }

   public static ShieldType of(int baseDurability, float materialFactor, float weight, float maxBlockDamage, boolean enabled, boolean repairable) {
      ShieldType shield = new ShieldType();
      shield.baseDurability = baseDurability;
      shield.materialFactor = materialFactor;
      shield.weight = weight;
      shield.maxBlockDamage = maxBlockDamage;
      shield.enabled = enabled;
      shield.repairable = repairable;
      return shield;
   }

   public static ShieldType of(int baseDurability, float materialFactor, float weight, float maxBlockDamage) {
      return of(baseDurability, materialFactor, weight, maxBlockDamage, true, false);
   }

   @Deprecated(forRemoval = true)
   public ShieldType(int baseDurability, float materialFactor, float weight, float maxBlockDamage, boolean enabled) {
      this.baseDurability = baseDurability;
      this.materialFactor = materialFactor;
      this.weight = weight;
      this.maxBlockDamage = maxBlockDamage;
      this.enabled = enabled;
   }

   @Deprecated(forRemoval = true)
   public ShieldType setRepairable() {
      this.repairable = true;
      return this;
   }

   public int getDurability(class_1832 material) {
      return (int)(this.baseDurability + this.materialFactor * material.method_8025());
   }

   public int getBaseDurability() {
      return this.baseDurability;
   }

   public float getMaterialFactor() {
      return this.materialFactor;
   }

   public float getWeight() {
      return this.weight;
   }

   public float getMaxBlockDamage() {
      return this.maxBlockDamage;
   }

   public boolean isRepairable() {
      return this.repairable;
   }

   public boolean isDisabled() {
      return !this.enabled;
   }
}
