package com.magistuarmory.item;

import java.util.function.Supplier;
import net.minecraft.class_1832;
import net.minecraft.class_1834;
import net.minecraft.class_1856;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_3481;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import org.jetbrains.annotations.NotNull;

public class ModItemTier implements class_1832 {
   private final String name;
   private final float attackDamageBonus;
   private final int enchantmentValue;
   private final Supplier<class_1856> repairIngredient;
   private final float speed;
   private final int uses;
   private final float density;
   public static ModItemTier WOOD = new ModItemTier("wood", class_1834.field_8922, 0.0F);
   public static ModItemTier STONE = new ModItemTier("stone", class_1834.field_8927, 1.0F);
   public static ModItemTier IRON = new ModItemTier("iron", class_1834.field_8923, 2.0F);
   public static ModItemTier DIAMOND = new ModItemTier("diamond", class_1834.field_8930, 3.0F);
   public static ModItemTier GOLD = new ModItemTier("gold", class_1834.field_8929, 4.0F);
   public static ModItemTier NETHERITE = new ModItemTier("netherite", class_1834.field_22033, 5.0F);
   public static ModItemTier COPPER = new ModItemTier("copper", class_3481.field_49928, 150, 0.7F, 0.0F, 10, "c:ingots/copper", 1.0F);
   public static ModItemTier SILVER = new ModItemTier("silver", class_3481.field_49928, 230, 5.5F, 1.0F, 18, "c:ingots/silver", 2.0F);
   public static ModItemTier STEEL = new ModItemTier("steel", class_3481.field_49927, 400, 6.0F, 2.5F, 14, "c:ingots/steel", 2.0F);
   public static ModItemTier TIN = new ModItemTier("tin", class_3481.field_49928, 130, 6.0F, 0.0F, 20, "c:ingots/tin", 2.0F);
   public static ModItemTier BRONZE = new ModItemTier("bronze", class_3481.field_49927, 200, 6.0F, 2.0F, 15, "c:ingots/bronze", 2.0F);
   private final class_6862<class_2248> incorrectBlocks;

   public ModItemTier(String name, class_1832 tier, float density) {
      this.name = name;
      this.incorrectBlocks = tier.method_58419();
      this.uses = tier.method_8025();
      this.speed = tier.method_8027();
      this.attackDamageBonus = tier.method_8028();
      this.enchantmentValue = tier.method_8026();
      this.repairIngredient = tier::method_8023;
      this.density = density;
   }

   public ModItemTier(
      String name, class_6862<class_2248> incorrectBlocks, int uses, float speed, float attack, int enchantment, String repairitemtag, float density
   ) {
      this.name = name;
      this.incorrectBlocks = incorrectBlocks;
      this.uses = uses;
      this.speed = speed;
      this.attackDamageBonus = attack;
      this.enchantmentValue = enchantment;
      this.repairIngredient = () -> class_1856.method_8106(class_6862.method_40092(class_7924.field_41197, class_2960.method_60654(repairitemtag)));
      this.density = density;
   }

   public float method_8028() {
      return this.attackDamageBonus;
   }

   @NotNull
   public class_6862<class_2248> method_58419() {
      return this.incorrectBlocks;
   }

   public int method_8026() {
      return this.enchantmentValue;
   }

   public class_1856 method_8023() {
      return (T)((class_1856)this.repairIngredient.get());
   }

   public float method_8027() {
      return this.speed;
   }

   public int method_8025() {
      return this.uses;
   }

   public String getMaterialName() {
      return this.name;
   }

   public float getDensity() {
      return this.density;
   }
}
