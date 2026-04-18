package com.magistuarmory.item;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.component.ModDataComponents;
import com.magistuarmory.effects.LacerationEffect;
import com.magistuarmory.util.CombatHelper;
import com.magistuarmory.util.ModDamageSources;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1322;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1819;
import net.minecraft.class_1829;
import net.minecraft.class_1836;
import net.minecraft.class_1839;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3483;
import net.minecraft.class_5134;
import net.minecraft.class_5819;
import net.minecraft.class_8111;
import net.minecraft.class_9274;
import net.minecraft.class_9285;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.minecraft.class_1322.class_1323;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_9285.class_9286;

public class MedievalWeaponItem extends class_1829 implements IHasModelProperty {
   public static final class_2960 BASE_ENTITY_INTERACTION_RANGE_ID = class_2960.method_60655("magistuarmory", "base_entity_interaction_range_id");
   private final class_9285 defaultModifiers;
   private final class_9285 decreasedModifiers;
   public final WeaponType type;
   protected final float attackDamage;
   private boolean isSilver = false;
   private float silverAttackDamage = 0.0F;
   private boolean blockingPriority = false;

   public MedievalWeaponItem(class_1793 properties, ModItemTier material, WeaponType type) {
      super(
         material,
         properties.method_7889(1)
            .method_7895(type.getDurability(material))
            .method_57348(createDefaultAttributeModifiersBuilder(material, type).method_57486())
      );
      this.type = type;
      this.attackDamage = CombatHelper.getBaseAttackDamage(material, type);
      if (material.equals(ModItemTier.SILVER)) {
         this.isSilver = true;
         this.silverAttackDamage = CombatHelper.getSilverAttackDamage(material, type);
      }

      this.defaultModifiers = createDefaultAttributeModifiersBuilder(material, type).method_57486();
      this.decreasedModifiers = createDecreasedAttributeModifiersBuilder(material, type).method_57486();
   }

   public static class_9286 createDefaultAttributeModifiersBuilder(ModItemTier material, WeaponType type) {
      return createAttributeModifiersBuilder(
         CombatHelper.getBaseAttackDamage(material, type), CombatHelper.getBaseAttackSpeed(material, type), type.getBonusAttackReach()
      );
   }

   public static class_9286 createDecreasedAttributeModifiersBuilder(ModItemTier material, WeaponType type) {
      return createAttributeModifiersBuilder(
         CombatHelper.getDecreasedAttackDamage(material, type), CombatHelper.getDecreasedAttackSpeed(material, type), type.getBonusAttackReach()
      );
   }

   public static class_9286 createAttributeModifiersBuilder(float damage, float speed, float reach) {
      class_9286 builder = class_9285.method_57480();
      builder.method_57487(class_5134.field_23721, new class_1322(field_8006, damage, class_1323.field_6328), class_9274.field_49217);
      builder.method_57487(class_5134.field_23723, new class_1322(field_8001, speed, class_1323.field_6328), class_9274.field_49217);
      builder.method_57487(class_5134.field_47759, new class_1322(BASE_ENTITY_INTERACTION_RANGE_ID, reach, class_1323.field_6328), class_9274.field_49217);
      return builder;
   }

   public boolean onAttackClickEntity(class_1799 stack, class_1657 player, class_1297 entity) {
      return true;
   }

   public class_9285 getAttributeModifiers(class_1799 stack) {
      return this.hasTwoHandedPenalty(stack) ? this.decreasedModifiers : this.defaultModifiers;
   }

   public void method_7888(class_1799 stack, class_1937 level, class_1297 entity, int i, boolean selected) {
      if (entity instanceof class_1309 livingentity) {
         boolean penalty = this.type.getTwoHanded() > 0 && !livingentity.method_6079().method_7909().equals(class_1802.field_8162);
         if (this.hasTwoHandedPenalty(stack) != penalty) {
            stack.method_57379((class_9331)ModDataComponents.TWO_HANDED_PENALTY.get(), penalty ? this.type.getTwoHanded() : 0);
            stack.method_57379(class_9334.field_49636, this.getAttributeModifiers(stack));
         }

         if (this.canBlock()) {
            this.blockingPriority = !(livingentity.method_6047().method_7909() instanceof class_1819)
               && !(livingentity.method_6079().method_7909() instanceof class_1819);
         }
      }

      super.method_7888(stack, level, entity, i, selected);
   }

   public boolean onHurtEntity(class_1282 source, class_1309 victim, float damage) {
      if (!victim.method_37908().method_8608() && !ModDamageSources.isAdditional(source) && source.method_5529() instanceof class_1309 attacker) {
         float attackscale = source.method_5529() instanceof class_1309 livingentity ? damage / this.getAttackDamage(livingentity.method_6047()) : 1.0F;
         if (this.type.isHalberd() && victim.method_5765() && victim.method_37908().method_8409().method_43048(20) * attackscale >= 14.0F) {
            victim.method_5848();
         }

         boolean flag = false;
         if (this.isSilver()) {
            flag = this.dealSilverDamage(source, attacker, victim, damage, attackscale);
         }

         if (!flag && this.type.getArmorPiercing() != 0 && victim.method_6096() > 0) {
            flag = this.dealArmorPiercingDamage(source, attacker, victim, damage);
         }

         if (this.type.isFlamebladed()) {
            LacerationEffect.apply(source, victim, damage * attackscale);
         }

         this.method_59978(attacker.method_59958(), attacker, victim);
         return flag;
      } else {
         return true;
      }
   }

   public void method_7851(class_1799 stack, class_9635 tooltipContext, List<class_2561> tooltip, class_1836 flag) {
      if (this.isSilver) {
         tooltip.add(class_2561.method_43469("silvertools.hurt", new Object[]{this.silverAttackDamage}).method_27692(class_124.field_1060));
      }

      if (this.type.isFlamebladed()) {
         tooltip.add(class_2561.method_43471("flamebladed.hurt").method_27692(class_124.field_1078));
      }

      if (this.type.isHalberd()) {
         tooltip.add(class_2561.method_43471("halberd.hurt").method_27692(class_124.field_1078));
      }

      if (this.type.getArmorPiercing() != 0) {
         tooltip.add(class_2561.method_43469("armorpiercing", new Object[]{this.type.getArmorPiercing()}).method_27692(class_124.field_1078));
      }

      if (this.isLong()) {
         tooltip.add(class_2561.method_43469("bonusattackreach", new Object[]{this.type.getBonusAttackReach()}).method_27692(class_124.field_1078));
      }

      if (this.type.getTwoHanded() == 1) {
         tooltip.add(class_2561.method_43471("twohandedi").method_27692(class_124.field_1078));
      } else if (this.type.getTwoHanded() > 1) {
         tooltip.add(class_2561.method_43471("twohandedii").method_27692(class_124.field_1078));
      }

      if (this.canBlock()) {
         tooltip.add(class_2561.method_43469("maxdamageblock", new Object[]{this.getMaxBlockDamage()}).method_27692(class_124.field_1078));
      }

      tooltip.add(class_2561.method_43469("kgweight", new Object[]{this.getWeight()}).method_27692(class_124.field_1078));
      if (this.hasTwoHandedPenalty(stack)) {
         tooltip.add(class_2561.method_43471("twohandedpenalty_1").method_27692(class_124.field_1061));
         tooltip.add(class_2561.method_43471("twohandedpenalty_2").method_27692(class_124.field_1061));
      }

      super.method_7851(stack, tooltipContext, tooltip, flag);
   }

   public boolean hasTwoHandedPenalty(class_1799 stack) {
      Integer value = (Integer)stack.method_57824((class_9331)ModDataComponents.TWO_HANDED_PENALTY.get());
      return value != null && value > 0;
   }

   public float getAttackDamage(class_1799 stack) {
      return (float)this.getAttributeModifiers(stack)
         .comp_2393()
         .stream()
         .filter(m -> m.comp_2396().comp_2447().equals(field_8006))
         .findFirst()
         .orElseThrow()
         .comp_2396()
         .comp_2449();
   }

   public float getAttackReach(float baseReach) {
      return baseReach + this.getBonusAttackReach();
   }

   public float getBonusAttackReach() {
      return EpicKnights.BC_or_EF_installed ? 0.0F : this.type.getBonusAttackReach();
   }

   public boolean isLong() {
      return this.getBonusAttackReach() > 0.0;
   }

   @Deprecated(forRemoval = true)
   public float getSilverDamage(class_1799 stack, float damage) {
      return this.silverAttackDamage * damage / this.getAttackDamage(stack);
   }

   public float getMaxBlockDamage() {
      return this.type.getMaxBlockDamage();
   }

   public float getWeight() {
      return this.type.getWeight();
   }

   public boolean isSilver() {
      return this.isSilver;
   }

   public boolean canBlock(class_1657 player) {
      return player.method_7261(0.0F) == 1.0F && this.canBlock();
   }

   public boolean canBlock() {
      return this.type.canBlock();
   }

   boolean haveBlocked(class_5819 rand, class_1282 source) {
      return source.method_60489() && rand.method_43048(18) > this.getWeight();
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      if (this.canBlock(player) && this.blockingPriority) {
         class_1799 stack = player.method_5998(hand);
         player.method_6019(hand);
         return class_1271.method_22428(stack);
      } else {
         return super.method_7836(level, player, hand);
      }
   }

   public int method_7881(class_1799 stack, class_1309 entity) {
      return this.canBlock() ? (int)(500.0F / this.getWeight()) : 0;
   }

   public class_1839 method_7853(class_1799 stack) {
      return this.canBlock() && this.blockingPriority ? class_1839.field_8949 : super.method_7853(stack);
   }

   public void onBlocked(class_1799 stack, float damage, class_1309 victim, class_1282 source) {
      if (this.canBlock() && !ModDamageSources.isAdditional(source)) {
         class_1297 attacker = source.method_5529();
         float f = CombatHelper.getArmorPiercingFactor(attacker);
         if (!source.method_49708(class_8111.field_42332) && !source.method_49708(class_8111.field_42331)) {
            if (!this.haveBlocked(victim.method_37908().method_8409(), source)) {
               victim.method_5643(ModDamageSources.additional(), damage);
            } else if (damage > this.getMaxBlockDamage()) {
               f *= 1.5F;
               float damage1 = damage - this.getMaxBlockDamage();
               victim.method_5643(ModDamageSources.additional(), damage1);
            }
         } else {
            victim.method_5643(ModDamageSources.additional(), damage);
         }

         stack.method_7970((int)(f * damage), victim, class_1304.field_6173);
      }
   }

   public boolean dealSilverDamage(class_1282 source, class_1309 attacker, class_1309 victim, float damage, float attackscale) {
      if (victim.method_5864().method_20210(class_3483.field_46232)) {
         victim.method_5643(
            ModDamageSources.silverAttack(attacker), CombatHelper.getDamageAfterAbsorb(source, victim, this.silverAttackDamage) * attackscale + damage
         );
         return true;
      } else {
         return false;
      }
   }

   public boolean dealArmorPiercingDamage(class_1282 source, class_1309 attacker, class_1309 victim, float damage) {
      float afterabsorb = CombatHelper.getDamageAfterAbsorb(source, victim, damage);
      afterabsorb = Math.max(afterabsorb - victim.method_6067(), 0.0F);
      float pierced = Math.max(this.type.getArmorPiercing() / 100.0F * (damage - afterabsorb), 0.0F);
      victim.method_5643(ModDamageSources.armorPiercing(attacker), damage + pierced);
      return true;
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void registerModelProperty() {
      if (this.canBlock()) {
         ItemPropertiesRegistry.register(
            this,
            class_2960.method_60656("blocking"),
            (stack, level, entity, i) -> entity != null && entity.method_6115() && entity.method_6030() == stack ? 1.0F : 0.0F
         );
      }
   }
}
