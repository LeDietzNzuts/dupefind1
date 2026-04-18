package vectorwing.farmersdelight.common.item;

import java.util.Set;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1766;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1832;
import net.minecraft.class_1838;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2272;
import net.minecraft.class_2276;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3965;
import net.minecraft.class_6880;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_2350.class_2351;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.refabricated.ItemAbility;

public class KnifeItem extends class_1766 {
   public static final Set<ItemAbility> KNIFE_ACTIONS = Set.of(ItemAbility.SHEARS_CARVE, ItemAbility.SWORD_DIG);

   public KnifeItem(class_1832 tier, class_1793 properties) {
      super(tier, ModTags.MINEABLE_WITH_KNIFE, properties);
   }

   public static void init() {
      UseBlockCallback.EVENT.register(KnifeItem.KnifeEvents::onCakeInteraction);
   }

   public boolean method_7885(class_2680 state, class_1937 level, class_2338 pos, class_1657 player) {
      return !player.method_7337();
   }

   public boolean method_7873(class_1799 stack, class_1309 target, class_1309 attacker) {
      return true;
   }

   public void method_59978(class_1799 stack, class_1309 target, class_1309 attacker) {
      stack.method_7970(1, attacker, class_1304.field_6173);
   }

   public boolean canBeEnchantedWith(class_1799 stack, class_6880<class_1887> enchantment, EnchantingContext context) {
      return enchantment.method_40225(class_1893.field_9115) ? false : super.canBeEnchantedWith(stack, enchantment, context);
   }

   public class_1269 method_7884(class_1838 context) {
      class_1937 level = context.method_8045();
      class_1799 toolStack = context.method_8041();
      class_2338 pos = context.method_8037();
      class_2680 state = level.method_8320(pos);
      class_2350 facing = context.method_8038();
      if (state.method_26204() == class_2246.field_46282 && toolStack.method_31573(ModTags.KNIVES)) {
         class_1657 player = context.method_8036();
         if (player != null && !level.field_9236) {
            class_2350 direction = facing.method_10166() == class_2351.field_11052 ? player.method_5735().method_10153() : facing;
            level.method_8396(null, pos, class_3417.field_14619, class_3419.field_15245, 1.0F, 1.0F);
            level.method_8652(pos, (class_2680)class_2246.field_10147.method_9564().method_11657(class_2276.field_10748, direction), 11);
            class_1542 itemEntity = new class_1542(
               level,
               pos.method_10263() + 0.5 + direction.method_10148() * 0.65,
               pos.method_10264() + 0.1,
               pos.method_10260() + 0.5 + direction.method_10165() * 0.65,
               new class_1799(class_1802.field_46249, 4)
            );
            itemEntity.method_18800(
               0.05 * direction.method_10148() + level.field_9229.method_43058() * 0.02,
               0.05,
               0.05 * direction.method_10165() + level.field_9229.method_43058() * 0.02
            );
            level.method_8649(itemEntity);
            toolStack.method_7970(1, player, class_1309.method_56079(context.method_20287()));
         }

         return class_1269.method_29236(level.field_9236);
      } else {
         return class_1269.field_5811;
      }
   }

   public static class KnifeEvents {
      public static double onKnifeKnockback(double strength, class_1309 entity) {
         class_1309 attacker = entity.method_6124();
         class_1799 toolStack = attacker != null ? attacker.method_5998(class_1268.field_5808) : class_1799.field_8037;
         if (toolStack.method_7909() instanceof KnifeItem) {
            strength -= 0.1F;
         }

         return strength;
      }

      public static class_1269 onCakeInteraction(class_1657 player, class_1937 level, class_1268 hand, class_3965 hitResult) {
         if (player.method_7325()) {
            return class_1269.field_5811;
         } else {
            class_1799 toolStack = player.method_5998(hand);
            if (!toolStack.method_31573(ModTags.KNIVES)) {
               return class_1269.field_5811;
            } else {
               class_2338 pos = hitResult.method_17777();
               class_2680 state = level.method_8320(pos);
               class_2248 block = state.method_26204();
               if (state.method_26164(ModTags.DROPS_CAKE_SLICE)) {
                  level.method_8652(pos, (class_2680)class_2246.field_10183.method_9564().method_11657(class_2272.field_10739, 1), 3);
                  class_2248.method_9497(state, level, pos);
                  ItemUtils.spawnItemEntity(
                     level,
                     new class_1799((class_1935)ModItems.CAKE_SLICE.get()),
                     pos.method_10263(),
                     pos.method_10264() + 0.2,
                     pos.method_10260() + 0.5,
                     -0.05,
                     0.0,
                     0.0
                  );
                  level.method_8396(null, pos, class_3417.field_14983, class_3419.field_15248, 0.8F, 0.8F);
                  return class_1269.field_5812;
               } else if (block == class_2246.field_10183) {
                  int bites = (Integer)state.method_11654(class_2272.field_10739);
                  if (bites < 6) {
                     level.method_8652(pos, (class_2680)state.method_11657(class_2272.field_10739, bites + 1), 3);
                  } else {
                     level.method_8650(pos, false);
                  }

                  ItemUtils.spawnItemEntity(
                     level,
                     new class_1799((class_1935)ModItems.CAKE_SLICE.get()),
                     pos.method_10263() + bites * 0.1,
                     pos.method_10264() + 0.2,
                     pos.method_10260() + 0.5,
                     -0.05,
                     0.0,
                     0.0
                  );
                  level.method_8396(null, pos, class_3417.field_14983, class_3419.field_15248, 0.8F, 0.8F);
                  return level.field_9236 ? class_1269.field_5812 : class_1269.field_21466;
               } else {
                  return class_1269.field_5811;
               }
            }
         }
      }
   }
}
