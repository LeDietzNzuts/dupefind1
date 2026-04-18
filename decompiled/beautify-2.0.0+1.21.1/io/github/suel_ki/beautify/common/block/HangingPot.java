package io.github.suel_ki.beautify.common.block;

import java.util.Arrays;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1820;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2561;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2746;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_3726;
import net.minecraft.class_3749;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_4538;
import net.minecraft.class_9062;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class HangingPot extends class_3749 {
   public static final List<class_1792> VALID_FLOWERS = Arrays.asList(
      class_1802.field_8162,
      class_1802.field_17527,
      class_1802.field_17526,
      class_1802.field_17499,
      class_1802.field_17523,
      class_1802.field_17525,
      class_1802.field_17529,
      class_1802.field_17501,
      class_1802.field_17502,
      class_1802.field_17509,
      class_1802.field_17510,
      class_1802.field_17511,
      class_1802.field_17500,
      class_1802.field_8491,
      class_1802.field_8880,
      class_1802.field_28409,
      class_1802.field_17512,
      class_1802.field_17514,
      class_1802.field_17513,
      class_1802.field_21992,
      class_1802.field_23070,
      class_1802.field_17515,
      class_1802.field_28659,
      class_1802.field_16998,
      class_1802.field_8602,
      class_1802.field_8471
   );
   public static final class_2758 POTFLOWER = class_2758.method_11867("potflower", 0, VALID_FLOWERS.size() - 1);
   public static final class_2746 GROWN = class_2746.method_11825("grown");
   private static final class_265 HANGING_SHAPE = class_259.method_17786(
      method_9541(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
      new class_265[]{method_9541(3.75, 4.0, 3.75, 12.25, 8.0, 12.25), method_9541(5.0, 8.0, 5.0, 11.0, 16.0, 11.0)}
   );
   private static final class_265 STANDING_SHAPE = class_259.method_1084(
      method_9541(5.0, 0.0, 5.0, 11.0, 5.0, 11.0), method_9541(4.0, 5.0, 4.0, 12.0, 8.0, 12.0)
   );

   public HangingPot(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(POTFLOWER, 0)).method_11657(GROWN, false));
   }

   public List<class_1792> getValidFlowers() {
      return VALID_FLOWERS;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return state.method_11654(field_16545) ? HANGING_SHAPE : STANDING_SHAPE;
   }

   public void method_9612(class_2680 state, class_1937 level, class_2338 pos, class_2248 block, class_2338 blockPos, boolean bool) {
      if ((Boolean)state.method_11654(GROWN)
         && blockPos.equals(pos.method_10074())
         && level.method_8320(pos.method_10074()).method_26206(level, pos.method_10074(), class_2350.field_11036)) {
         level.method_8652(pos, (class_2680)state.method_11657(GROWN, false), 3);
         class_1542 Item = new class_1542(
            level,
            pos.method_10263(),
            pos.method_10264(),
            pos.method_10260(),
            new class_1799((class_1935)VALID_FLOWERS.get((Integer)state.method_11654(POTFLOWER)))
         );
         level.method_8649(Item);
         level.method_8396(null, pos, class_3417.field_28586, class_3419.field_15245, 1.0F, 1.0F);
      }

      super.method_9612(state, level, pos, block, blockPos, bool);
   }

   public class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 result) {
      if (level.method_8608()) {
         return class_9062.field_47729;
      } else {
         class_1799 playerStack = player.method_5998(hand);
         if (playerStack.method_7909().equals(class_1802.field_8324)
            && (Integer)state.method_11654(POTFLOWER) != 0
            && !level.method_8320(pos.method_10074()).method_26206(level, pos.method_10074(), class_2350.field_11036)
            && !(Boolean)state.method_11654(GROWN)) {
            level.method_20290(1505, result.method_17777(), 0);
            if (!player.method_31549().field_7477) {
               playerStack.method_7934(1);
            }

            level.method_8652(pos, (class_2680)state.method_11657(GROWN, true), 3);
            return class_9062.field_47728;
         } else if (playerStack.method_7909() instanceof class_1820 && (Integer)state.method_11654(POTFLOWER) != 0 && (Boolean)state.method_11654(GROWN)) {
            level.method_8396(null, pos, class_3417.field_14975, class_3419.field_15245, 1.0F, 1.0F);
            level.method_8652(pos, (class_2680)state.method_11657(GROWN, false), 3);
            class_1542 Item = new class_1542(
               level,
               pos.method_10263(),
               pos.method_10264(),
               pos.method_10260(),
               new class_1799((class_1935)VALID_FLOWERS.get((Integer)state.method_11654(POTFLOWER)))
            );
            level.method_8649(Item);
            return class_9062.field_47728;
         } else {
            if ((Integer)state.method_11654(POTFLOWER) != 0) {
               if (playerStack.method_7960()) {
                  player.method_6122(
                     hand, new class_1799((class_1935)VALID_FLOWERS.get((Integer)state.method_11654(POTFLOWER)), state.method_11654(GROWN) ? 2 : 1)
                  );
                  level.method_8652(pos, (class_2680)((class_2680)state.method_11657(POTFLOWER, 0)).method_11657(GROWN, false), 3);
                  level.method_8396(null, pos, class_3417.field_17609, class_3419.field_15245, 1.0F, 1.0F);
                  return class_9062.field_47728;
               }

               if (playerStack.method_31574(VALID_FLOWERS.get((Integer)state.method_11654(POTFLOWER))) && playerStack.method_7947() < playerStack.method_7914()
                  )
                {
                  playerStack.method_7933(state.method_11654(GROWN) ? 2 : 1);
                  level.method_8652(pos, (class_2680)((class_2680)state.method_11657(POTFLOWER, 0)).method_11657(GROWN, false), 3);
                  level.method_8396(null, pos, class_3417.field_28565, class_3419.field_15245, 1.0F, 1.0F);
                  return class_9062.field_47728;
               }
            } else {
               for (class_1792 flower : VALID_FLOWERS) {
                  if (playerStack.method_7909().equals(flower)) {
                     level.method_8652(pos, (class_2680)state.method_11657(POTFLOWER, VALID_FLOWERS.indexOf(flower)), 3);
                     player.method_7281(class_3468.field_15412);
                     if (!flower.equals(class_1802.field_8162)) {
                        level.method_8396(null, pos, class_3417.field_28563, class_3419.field_15245, 1.0F, 1.0F);
                     }

                     if (!player.method_31549().field_7477) {
                        playerStack.method_7934(1);
                     }

                     return class_9062.field_47728;
                  }
               }
            }

            return class_9062.field_47729;
         }
      }
   }

   public class_1799 method_9574(class_4538 level, class_2338 pos, class_2680 state) {
      return this.isEmpty(state) ? super.method_9574(level, pos, state) : new class_1799(this.getContent(state));
   }

   private boolean isEmpty(class_2680 state) {
      return (Integer)state.method_11654(POTFLOWER) == 0;
   }

   public class_2248 getContent(class_2680 state) {
      return class_2248.method_9503(VALID_FLOWERS.get((Integer)state.method_11654(POTFLOWER)));
   }

   protected void method_9515(class_2690<class_2248, class_2680> pBuilder) {
      super.method_9515(pBuilder);
      pBuilder.method_11667(new class_2769[]{POTFLOWER, GROWN});
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442() && !class_437.method_25441()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
         component.add(class_2561.method_43471("tooltip.beautify.plantlist").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.hanging_pot.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.hanging_pot.2").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.hanging_pot.3").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
