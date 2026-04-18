package io.github.suel_ki.beautify.common.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import io.github.suel_ki.beautify.core.init.SoundInit;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.class_124;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2561;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_5819;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class BookStack extends class_2383 {
   private static final int MODELCOUNT = 7;
   public static final class_2758 BOOKSTACK_MODEL = class_2758.method_11867("bookstack_model", 0, 6);
   private static final Map<Integer, class_265> SHAPES_FOR_MODEL = ImmutableMap.of(
      0,
      class_259.method_17786(class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 4.0, 15.0), new class_265[0]),
      1,
      class_259.method_17786(class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 4.0, 15.0), new class_265[0]),
      2,
      class_259.method_17786(class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 1.5, 16.0), new class_265[0]),
      3,
      class_259.method_17786(class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 9.5, 16.0), new class_265[0]),
      4,
      class_259.method_17786(class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 5.0, 15.0), new class_265[0]),
      5,
      class_259.method_17786(class_2248.method_9541(0.5, 0.0, 0.5, 15.5, 7.25, 15.5), new class_265[0]),
      6,
      class_259.method_17786(class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 12.0, 15.0), new class_265[0])
   );
   public static final MapCodec<BookStack> CODEC = method_54094(BookStack::new);

   public BookStack(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(BOOKSTACK_MODEL, 0)).method_11657(field_11177, class_2350.field_11043));
   }

   protected MapCodec<BookStack> method_53969() {
      return CODEC;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPES_FOR_MODEL.get(state.method_11654(BOOKSTACK_MODEL));
   }

   public class_2680 method_9559(
      class_2680 state, class_2350 direction, class_2680 neighborState, class_1936 level, class_2338 currentPos, class_2338 neighborPos
   ) {
      if (!state.method_26184(level, currentPos)) {
         level.method_39279(currentPos, this, 1);
      }

      return super.method_9559(state, direction, neighborState, level, currentPos, neighborPos);
   }

   public void method_9588(class_2680 state, class_3218 level, class_2338 pos, class_5819 rand) {
      if (!state.method_26184(level, pos)) {
         level.method_22352(pos, true);
      }
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 result) {
      if (level.method_8608()) {
         return class_1269.field_5812;
      } else if (player.method_5715()) {
         int currentModel = (Integer)state.method_11654(BOOKSTACK_MODEL);
         level.method_8396(null, pos, SoundInit.BOOKSTACK_NEXT, class_3419.field_15245, 1.0F, 1.0F);
         if (currentModel + 1 > 6) {
            level.method_8652(pos, (class_2680)state.method_11657(BOOKSTACK_MODEL, 0), 3);
         } else {
            level.method_8652(pos, (class_2680)state.method_11657(BOOKSTACK_MODEL, currentModel + 1), 3);
         }

         return class_1269.field_5812;
      } else {
         return class_1269.field_5812;
      }
   }

   public void method_9554(class_1937 level, class_2680 state, class_2338 pos, class_1297 entity, float fallDistance) {
      level.method_8396(null, pos, SoundInit.BOOKSTACK_FALL, class_3419.field_15245, 1.0F, 1.0F);
      super.method_9554(level, state, pos, entity, fallDistance);
   }

   public class_2680 method_9605(class_1750 context) {
      Random rand = new Random();
      int randomNum = rand.nextInt(7);
      return (class_2680)((class_2680)this.method_9564().method_11657(field_11177, context.method_8042().method_10153()))
         .method_11657(BOOKSTACK_MODEL, randomNum);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{BOOKSTACK_MODEL, field_11177});
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.bookstack.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.bookstack.2").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
