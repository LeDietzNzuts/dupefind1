package io.github.suel_ki.beautify.common.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2561;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2746;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_4538;
import net.minecraft.class_9062;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class Trellis extends class_2383 {
   public static final List<class_1792> VALID_FLOWERS = Arrays.asList(
      class_1802.field_8162,
      class_1802.field_17527,
      class_1802.field_17525,
      class_1802.field_17529,
      class_1802.field_17526,
      class_1802.field_17523,
      class_1802.field_21992,
      class_1802.field_23070,
      class_1802.field_28409
   );
   public static final class_2758 FLOWERS = class_2758.method_11867("flowers", 0, VALID_FLOWERS.size() - 1);
   public static final class_2746 CEILLING = class_2746.method_11825("ceilling");
   private static final class_265 SHAPE_CEILLING = class_2248.method_9541(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
   private static final Map<class_2350, class_265> SHAPES_FOR_MODEL = ImmutableMap.of(
      class_2350.field_11043,
      class_2248.method_9541(0.0, 0.0, 14.0, 16.0, 16.0, 16.0),
      class_2350.field_11035,
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 16.0, 2.0),
      class_2350.field_11039,
      class_2248.method_9541(14.0, 0.0, 0.0, 16.0, 16.0, 16.0),
      class_2350.field_11034,
      class_2248.method_9541(0.0, 0.0, 0.0, 2.0, 16.0, 16.0)
   );
   public static final MapCodec<Trellis> CODEC = method_54094(Trellis::new);

   public Trellis(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(CEILLING, false)).method_11657(field_11177, class_2350.field_11043));
   }

   protected MapCodec<Trellis> method_53969() {
      return CODEC;
   }

   public List<class_1792> getValidFlowers() {
      return VALID_FLOWERS;
   }

   public class_2680 method_9605(class_1750 context) {
      return context.method_8038() == class_2350.field_11033
         ? (class_2680)((class_2680)this.method_9564().method_11657(field_11177, context.method_8042().method_10153())).method_11657(CEILLING, true)
         : (class_2680)((class_2680)this.method_9564().method_11657(field_11177, context.method_8042().method_10153())).method_11657(CEILLING, false);
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return state.method_11654(CEILLING) ? SHAPE_CEILLING : SHAPES_FOR_MODEL.get(state.method_11654(field_11177));
   }

   public class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 result) {
      if (!level.method_8608() && hand == class_1268.field_5808) {
         class_1799 playerStack = player.method_5998(hand);
         if ((Integer)state.method_11654(FLOWERS) != 0) {
            if (playerStack.method_7960()) {
               player.method_6122(hand, new class_1799((class_1935)VALID_FLOWERS.get((Integer)state.method_11654(FLOWERS))));
               level.method_8652(pos, (class_2680)state.method_11657(FLOWERS, 0), 3);
               level.method_8396(null, pos, class_3417.field_28565, class_3419.field_15245, 1.0F, 1.0F);
               return class_9062.field_47728;
            } else if (playerStack.method_31574(VALID_FLOWERS.get((Integer)state.method_11654(FLOWERS)))
               && playerStack.method_7947() < playerStack.method_7914()) {
               playerStack.method_7933(1);
               level.method_8652(pos, (class_2680)state.method_11657(FLOWERS, 0), 3);
               level.method_8396(null, pos, class_3417.field_28565, class_3419.field_15245, 1.0F, 1.0F);
               return class_9062.field_47728;
            } else {
               return class_9062.field_47729;
            }
         } else {
            for (class_1792 flower : VALID_FLOWERS) {
               if (playerStack.method_7909().equals(flower)) {
                  level.method_8652(pos, (class_2680)state.method_11657(FLOWERS, VALID_FLOWERS.indexOf(flower)), 3);
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

            return class_9062.field_47729;
         }
      } else {
         return class_9062.field_47729;
      }
   }

   public class_1799 method_9574(class_4538 level, class_2338 pos, class_2680 state) {
      return this.isEmpty(state) ? super.method_9574(level, pos, state) : new class_1799(this.getContent(state));
   }

   private boolean isEmpty(class_2680 state) {
      return (Integer)state.method_11654(FLOWERS) == 0;
   }

   public class_2248 getContent(class_2680 state) {
      return class_2248.method_9503(VALID_FLOWERS.get((Integer)state.method_11654(FLOWERS)));
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{field_11177, CEILLING, FLOWERS});
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442() && !class_437.method_25441()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
         component.add(class_2561.method_43471("tooltip.beautify.plantlist").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.trellis.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.trellis.2").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
