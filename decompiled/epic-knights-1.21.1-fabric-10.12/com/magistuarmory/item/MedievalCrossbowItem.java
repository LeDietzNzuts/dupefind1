package com.magistuarmory.item;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import java.util.List;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1890;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_9278;
import net.minecraft.class_9334;
import net.minecraft.class_9701;
import net.minecraft.class_1764.class_9693;
import net.minecraft.class_1792.class_1793;

public class MedievalCrossbowItem extends class_1764 implements IHasModelProperty {
   static class_9693 DEFAULT_SOUNDS = new class_9693(
      Optional.of(class_3417.field_14765), Optional.of(class_3417.field_14860), Optional.of(class_3417.field_14626)
   );
   private final int pullTime;
   private final float projectileSpeed;
   protected boolean startSoundPlayed = false;
   protected boolean midLoadSoundPlayed = false;

   public MedievalCrossbowItem(class_1793 properties, float projectileSpeed, int pullTime) {
      super(properties.method_7889(1));
      this.projectileSpeed = projectileSpeed;
      this.pullTime = pullTime;
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 interactionHand) {
      class_1799 itemStack = player.method_5998(interactionHand);
      class_9278 chargedProjectiles = (class_9278)itemStack.method_57824(class_9334.field_49649);
      if (chargedProjectiles != null && !chargedProjectiles.method_57442()) {
         this.method_7777(level, player, interactionHand, itemStack, this.getProjectileSpeed(chargedProjectiles), 1.0F, null);
         return class_1271.method_22428(itemStack);
      } else if (!player.method_18808(itemStack).method_7960()) {
         this.startSoundPlayed = false;
         this.midLoadSoundPlayed = false;
         player.method_6019(interactionHand);
         return class_1271.method_22428(itemStack);
      } else {
         return class_1271.method_22431(itemStack);
      }
   }

   public void method_7840(class_1799 itemStack, class_1937 level, class_1309 livingEntity, int i) {
      int j = this.method_7881(itemStack, livingEntity) - i;
      float f = this.getPower(j, itemStack, livingEntity);
      if (f >= 1.0F && !method_7781(itemStack) && tryLoadProjectiles(livingEntity, itemStack)) {
         class_9693 chargingSounds = this.method_59976(itemStack);
         chargingSounds.comp_2675()
            .ifPresent(
               holder -> level.method_43128(
                  null,
                  livingEntity.method_23317(),
                  livingEntity.method_23318(),
                  livingEntity.method_23321(),
                  (class_3414)holder.comp_349(),
                  livingEntity.method_5634(),
                  1.0F,
                  1.0F / (level.method_8409().method_43057() * 0.5F + 1.0F) + 0.2F
               )
            );
      }
   }

   private static boolean tryLoadProjectiles(class_1309 livingEntity, class_1799 itemStack) {
      List<class_1799> list = method_57390(itemStack, livingEntity.method_18808(itemStack), livingEntity);
      if (!list.isEmpty()) {
         itemStack.method_57379(class_9334.field_49649, class_9278.method_57441(list));
         return true;
      } else {
         return false;
      }
   }

   class_9693 method_59976(class_1799 itemStack) {
      return class_1890.method_60165(itemStack, class_9701.field_51653).orElse(DEFAULT_SOUNDS);
   }

   private float getProjectileSpeed(class_9278 chargedProjectiles) {
      return chargedProjectiles.method_57438(class_1802.field_8639) ? this.projectileSpeed / 2.0F : this.projectileSpeed;
   }

   public int getPullTime(class_1799 itemStack, class_1309 shooter) {
      float f = class_1890.method_60159(itemStack, shooter, this.pullTime / 20.0F);
      return class_3532.method_15375(f * 20.0F);
   }

   private float getPower(int p_40854_, class_1799 stack, class_1309 shooter) {
      float f = (float)p_40854_ / this.getPullTime(stack, shooter);
      if (f > 1.0F) {
         f = 1.0F;
      }

      return f;
   }

   public int method_7881(class_1799 stack, class_1309 entity) {
      return this.getPullTime(stack, entity) + 3;
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void registerModelProperty() {
      ItemPropertiesRegistry.register(this, class_2960.method_60656("pull"), (stack, level, entity, i) -> {
         if (entity == null) {
            return 0.0F;
         } else {
            return class_1764.method_7781(stack) ? 0.0F : (float)(stack.method_7935(entity) - entity.method_6014()) / this.getPullTime(stack, entity);
         }
      });
      ItemPropertiesRegistry.register(
         this,
         class_2960.method_60656("pulling"),
         (stack, level, entity, i) -> entity != null && entity.method_6115() && entity.method_6030() == stack && !class_1764.method_7781(stack) ? 1.0F : 0.0F
      );
      ItemPropertiesRegistry.register(
         this, class_2960.method_60656("charged"), (stack, level, entity, i) -> entity != null && class_1764.method_7781(stack) ? 1.0F : 0.0F
      );
      ItemPropertiesRegistry.register(
         this,
         class_2960.method_60656("firework"),
         (stack, level, entity, i) -> entity != null
               && class_1764.method_7781(stack)
               && stack.method_57826(class_9334.field_49649)
               && ((class_9278)stack.method_57824(class_9334.field_49649)).method_57438(class_1802.field_8639)
            ? 1.0F
            : 0.0F
      );
   }
}
