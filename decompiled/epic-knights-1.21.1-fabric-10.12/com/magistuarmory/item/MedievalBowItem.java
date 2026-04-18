package com.magistuarmory.item;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1753;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_1792.class_1793;

public class MedievalBowItem extends class_1753 implements IHasModelProperty {
   private final int pullTime;
   private final float projectileSpeed;

   public MedievalBowItem(class_1793 properties, float projectileSpeed, int pullTime) {
      super(properties.method_7889(1));
      this.projectileSpeed = projectileSpeed;
      this.pullTime = pullTime;
   }

   public void method_7840(class_1799 itemStack, class_1937 level, class_1309 livingEntity, int i) {
      if (livingEntity instanceof class_1657 player) {
         class_1799 itemStack2 = player.method_18808(itemStack);
         if (!itemStack2.method_7960()) {
            int j = this.method_7881(itemStack, livingEntity) - i;
            float f = this.getPower(j);
            if (!(f < 0.1)) {
               List<class_1799> list = method_57390(itemStack, itemStack2, player);
               if (level instanceof class_3218 serverLevel) {
                  this.method_57393(serverLevel, player, player.method_6058(), itemStack, list, f * this.projectileSpeed, 1.0F, f == 1.0F, null);
               }

               level.method_43128(
                  null,
                  player.method_23317(),
                  player.method_23318(),
                  player.method_23321(),
                  class_3417.field_14600,
                  class_3419.field_15248,
                  1.0F,
                  1.0F / (level.method_8409().method_43057() * 0.4F + 1.2F) + f * 0.5F
               );
               player.method_7259(class_3468.field_15372.method_14956(this));
            }
         }
      }
   }

   public float getPower(int p_185059_0_) {
      float f = (float)p_185059_0_ / this.pullTime;
      f = (f * f + f * 2.0F) / 3.0F;
      if (f > 1.0F) {
         f = 1.0F;
      }

      return f;
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void registerModelProperty() {
      ItemPropertiesRegistry.register(
         this,
         class_2960.method_60656("pulling"),
         (stack, level, entity, i) -> entity != null && entity.method_6115() && entity.method_6030() == stack ? 1.0F : 0.0F
      );
      ItemPropertiesRegistry.register(this, class_2960.method_60656("pull"), (stack, level, entity, i) -> {
         if (entity == null) {
            return 0.0F;
         } else {
            return entity.method_6030() != stack ? 0.0F : (float)(stack.method_7935(entity) - entity.method_6014()) / this.pullTime;
         }
      });
   }
}
