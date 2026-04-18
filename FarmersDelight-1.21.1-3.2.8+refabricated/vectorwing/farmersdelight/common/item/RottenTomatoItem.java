package vectorwing.farmersdelight.common.item;

import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1657;
import net.minecraft.class_1676;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_9463;
import net.minecraft.class_1792.class_1793;
import vectorwing.farmersdelight.common.entity.RottenTomatoEntity;
import vectorwing.farmersdelight.common.registry.ModSounds;

public class RottenTomatoItem extends class_1792 implements class_9463 {
   public RottenTomatoItem(class_1793 properties) {
      super(properties);
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      class_1799 heldStack = player.method_5998(hand);
      level.method_43128(
         null,
         player.method_23317(),
         player.method_23318(),
         player.method_23321(),
         ModSounds.ENTITY_ROTTEN_TOMATO_THROW.get(),
         class_3419.field_15254,
         0.5F,
         0.4F / (level.field_9229.method_43057() * 0.4F + 0.8F)
      );
      if (!level.field_9236) {
         RottenTomatoEntity projectile = new RottenTomatoEntity(level, player);
         projectile.method_16940(heldStack);
         projectile.method_24919(player, player.method_36455(), player.method_36454(), 0.0F, 1.5F, 1.0F);
         level.method_8649(projectile);
      }

      player.method_7259(class_3468.field_15372.method_14956(this));
      if (!player.method_31549().field_7477) {
         heldStack.method_7934(1);
      }

      return class_1271.method_29237(heldStack, level.method_8608());
   }

   public class_1676 method_58648(class_1937 level, class_2374 position, class_1799 itemStack, class_2350 direction) {
      RottenTomatoEntity rottenTomato = new RottenTomatoEntity(level, position.method_10216(), position.method_10214(), position.method_10215());
      rottenTomato.method_16940(itemStack);
      return rottenTomato;
   }
}
