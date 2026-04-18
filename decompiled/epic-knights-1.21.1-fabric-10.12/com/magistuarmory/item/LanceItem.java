package com.magistuarmory.item;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.client.HitResultHelper;
import com.magistuarmory.component.ModDataComponents;
import com.magistuarmory.network.PacketLanceCollision;
import com.magistuarmory.util.CombatHelper;
import com.magistuarmory.util.ModDamageSources;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import java.util.ArrayList;
import java.util.List;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1271;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1498;
import net.minecraft.class_1657;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1836;
import net.minecraft.class_1838;
import net.minecraft.class_1839;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_3966;
import net.minecraft.class_5134;
import net.minecraft.class_9331;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;

public class LanceItem extends MedievalWeaponItem {
   private final ModItemTier material;
   private List<class_1799> dropItems = new ArrayList<>();
   protected int clickedticks = 0;
   static int CLICKED_TICKS_COOLDOWN = 5;

   public LanceItem(class_1793 properties, ModItemTier material, WeaponType type) {
      super(properties, material, type);
      this.material = material;
   }

   public void resetClickedTicks() {
      this.clickedticks = CLICKED_TICKS_COOLDOWN;
   }

   public float getClickedScale() {
      return class_3532.method_15363((float)this.clickedticks / CLICKED_TICKS_COOLDOWN, 0.0F, 1.0F);
   }

   public void setupDropItems() {
      this.dropItems.add(new class_1799(class_1802.field_8600, 2));
      String materialname = this.material.getMaterialName();
      switch (materialname) {
         case "iron":
            this.dropItems.add(new class_1799(class_1802.field_8620));
            break;
         case "gold":
            this.dropItems.add(new class_1799(class_1802.field_8695));
            break;
         case "diamond":
            this.dropItems.add(new class_1799(class_1802.field_8477));
            break;
         case "netherite":
            this.dropItems.add(new class_1799(class_1802.field_22020));
            this.dropItems.add(new class_1799(class_1802.field_8477));
            break;
         case "steel":
            this.dropItems.add(new class_1799((class_1935)ModItems.STEEL_INGOT.get()));
      }
   }

   @Override
   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      this.setRaised(player, !this.isRaised(player));
      return super.method_7836(level, player, hand);
   }

   @Override
   public boolean onAttackClickEntity(class_1799 stack, class_1657 player, class_1297 entity) {
      if (EpicKnights.GENERAL_CONFIG.disableLanceCollision) {
         return super.onAttackClickEntity(stack, player, entity);
      } else {
         if (player.method_5765() && !this.isRaised(player) && !player.method_7357().method_7904(this)) {
            this.resetClickedTicks();
         }

         player.method_6104(class_1268.field_5808);
         return false;
      }
   }

   public void collide(class_1657 player, class_1309 victim, class_1937 level) {
      if (player != null && level != null) {
         if (!this.isRaised(player) && !player.method_7357().method_7904(this) && player.method_5765()) {
            float speed = this.getVelocityProjection(player);
            if (player.method_5668() instanceof class_1498 && speed >= ((class_1498)player.method_5668()).method_5996(class_5134.field_23719).method_6194()
               || !(player.method_5668() instanceof class_1498) && speed >= 0.233) {
               PacketLanceCollision.sendToServer(player, victim);
               player.method_7350();
            }
         }
      }
   }

   @Override
   public boolean onHurtEntity(class_1282 source, class_1309 victim, float damage) {
      if (EpicKnights.GENERAL_CONFIG.disableLanceCollision) {
         return super.onHurtEntity(source, victim, damage);
      } else if (!victim.method_37908().method_8608() && !ModDamageSources.isAdditional(source) && source.method_5529() instanceof class_1309 attacker) {
         float var13 = 0.0F;
         float bonusdamage = 0.0F;
         boolean dismount = false;
         if (attacker instanceof class_1308 mob) {
            class_1799 stack = mob.method_6047();
            var13 = this.getRideSpeed(stack);
            this.setRideSpeed(stack, 0.0F);
            bonusdamage = this.calcBonusDamage(attacker, var13);
            dismount = this.getDismount(stack);
            this.setDismount(stack, false);
         } else if (attacker instanceof class_1657 player) {
            class_1799 stack = player.method_6047();
            var13 = this.getRideSpeed(stack);
            this.setRideSpeed(stack, 0.0F);
            bonusdamage = this.calcBonusDamage(attacker, var13);
            dismount = this.getDismount(stack);
            this.setDismount(stack, false);
            if (stack.method_7919() >= stack.method_7936() - 1) {
               this.onBroken(player);
            }

            if (stack.method_7919() >= stack.method_7936() - 1) {
               this.onBroken(player);
            } else if (!player.method_7337()) {
               if (!(victim.method_6096() >= 9.0F * (this.material.method_8028() + 1.0F)) && !victim.method_6039()) {
                  stack.method_7974(stack.method_7919() + 1);
               } else {
                  stack.method_7974(
                     stack.method_7919() + (int)((0.6 + bonusdamage / 20.0F) * victim.method_37908().method_8409().method_43058() * stack.method_7936())
                  );
               }
            }

            for (class_1799 stack0 : player.method_31548().field_7547) {
               this.setRaised(player, true);
               player.method_7357().method_7906(stack0.method_7909(), (int)player.method_7279());
            }

            if (stack.method_7919() >= stack.method_7936()) {
               this.onBroken(player);
               stack.method_7939(0);
            }
         }

         class_243 vec = attacker.method_5828(1.0F);
         double magnitude = Math.min(1.0F, var13 * this.getTotalMass(attacker) / this.getTotalMass(victim));
         class_243 vel = vec.method_18805(magnitude, magnitude, magnitude);
         if (victim.method_5765()) {
            victim.method_5668().method_18799(victim.method_5668().method_18798().method_1019(vel));
            victim.method_5668().field_6007 = true;
            if (dismount) {
               victim.method_5848();
            }
         } else {
            victim.method_18799(victim.method_18798().method_1019(vel));
            victim.field_6007 = true;
         }

         if (!super.onHurtEntity(source, victim, bonusdamage + damage) && bonusdamage != 0.0F) {
            victim.method_5643(ModDamageSources.additional(attacker), bonusdamage + damage);
            return true;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   @Override
   public class_1839 method_7853(class_1799 stack) {
      return class_1839.field_8953;
   }

   @Override
   public void method_7888(class_1799 stack, class_1937 level, class_1297 entity, int i, boolean selected) {
      if (EpicKnights.GENERAL_CONFIG.disableLanceCollision) {
         super.method_7888(stack, level, entity, i, selected);
      } else {
         if (entity instanceof class_1657 player) {
            if (level.field_9236 && player.method_6047().method_7909() instanceof LanceItem) {
               if (HitResultHelper.getMouseOver(class_310.method_1551(), CombatHelper.getAttackReach(player, this)) instanceof class_3966 entityhit) {
                  class_1297 victim = entityhit.method_17782();
                  if (player.method_5765()
                     && victim instanceof class_1309
                     && victim.method_5805()
                     && victim.method_5628() != player.method_5854().method_5628()) {
                     this.collide(player, (class_1309)victim, level);
                  }
               }

               if (this.clickedticks > 0) {
                  this.clickedticks--;
               }
            }

            if (!this.isRaised(player) && player.method_7357().method_7904(this)) {
               this.setRaised(player, true);
            }
         }

         super.method_7888(stack, level, entity, i, selected);
      }
   }

   public class_1269 method_7884(class_1838 context) {
      return class_1269.field_5811;
   }

   public float calcBonusDamage(class_1297 entity, float speed) {
      float bonusdamage = 3.0F * this.getTotalMass(entity) * speed;
      return Math.min(Math.max(0.0F, bonusdamage), 0.7F * this.attackDamage);
   }

   @Override
   public void method_7851(class_1799 stack, class_9635 tooltipContext, List<class_2561> tooltip, class_1836 flag) {
      tooltip.add(class_2561.method_43471("lance.rideronly").method_27692(class_124.field_1078));
      tooltip.add(class_2561.method_43471("lance.leftclick").method_27692(class_124.field_1078));
      tooltip.add(class_2561.method_43471("lance.bonusdamage").method_27692(class_124.field_1078));
      super.method_7851(stack, tooltipContext, tooltip, flag);
   }

   public float getVelocityProjection(class_1297 entity) {
      class_243 velocity;
      if (entity.method_5765()) {
         velocity = entity.method_5668().method_18798();
      } else {
         velocity = entity.method_18798();
      }

      class_243 vec = entity.method_5828(1.0F);
      return vec.method_1027() == 0.0 ? 0.0F : (float)(velocity.method_1026(vec) / vec.method_1033());
   }

   public float getTotalMass(class_1297 entity) {
      float totalmass = this.getMass(entity);

      for (class_1297 entity2 = entity; entity2.method_5765(); totalmass += this.getMass(entity2)) {
         entity2 = entity2.method_5854();
      }

      for (class_1297 passenger : entity.method_5685()) {
         totalmass += this.getMass(passenger);
      }

      return totalmass;
   }

   public float getMass(class_1297 entity) {
      float mass = 0.0F;
      class_238 box = entity.method_5829();
      mass = (float)(mass + box.method_17939() * box.method_17940() * box.method_17941());
      if (entity instanceof class_1309 livingentity) {
         for (class_1799 armorpiece : livingentity.method_5661()) {
            if (!armorpiece.method_7960() && armorpiece.method_7909() instanceof class_1738) {
               mass = (float)(mass + (((class_1738)armorpiece.method_7909()).method_7687() + ((class_1738)armorpiece.method_7909()).method_26353()) / 20.0);
            }
         }
      }

      return mass;
   }

   public void onBroken(class_1657 player) {
      if (player.method_6047().method_7909() == this) {
         for (class_1799 stack : this.dropItems) {
            player.method_7328(stack, true);
         }

         player.method_5783(class_3417.field_15075, 1.0F, 1.0F);
      }
   }

   public boolean isRaised(@Nullable class_1309 entity) {
      if (entity == null) {
         return false;
      } else {
         class_1799 stack = entity.method_6047();
         Integer raised = (Integer)stack.method_57824((class_9331)ModDataComponents.RAISED.get());
         return raised != null && raised == 1;
      }
   }

   public void setRaised(class_1309 entity, boolean raised) {
      class_1799 stack = entity.method_6047();
      stack.method_57379((class_9331)ModDataComponents.RAISED.get(), raised ? 1 : 0);
   }

   public boolean getDismount(class_1799 stack) {
      Boolean dismount = (Boolean)stack.method_57824((class_9331)ModDataComponents.DISMOUNT.get());
      return dismount != null ? dismount : false;
   }

   public void setDismount(class_1799 stack, boolean dismount) {
      stack.method_57379((class_9331)ModDataComponents.DISMOUNT.get(), dismount);
   }

   public float getRideSpeed(class_1799 stack) {
      Float speed = (Float)stack.method_57824((class_9331)ModDataComponents.RIDE_SPEED.get());
      return speed != null ? speed : 0.0F;
   }

   public void setRideSpeed(class_1799 stack, float speed) {
      stack.method_57379((class_9331)ModDataComponents.RIDE_SPEED.get(), speed);
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void registerModelProperty() {
      ItemPropertiesRegistry.register(
         this, class_2960.method_60655("magistuarmory", "raised"), (stack, level, entity, i) -> this.isRaised(entity) ? 1.0F : 0.0F
      );
   }
}
