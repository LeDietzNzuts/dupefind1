package io.github.suel_ki.beautify.common.block;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2398;
import net.minecraft.class_2561;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_3749;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_5819;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class LampLightBulb extends class_3749 {
   public static final class_2746 ON = class_2746.method_11825("on");
   private static final class_265 SHAPE_HANGING = class_2248.method_9541(5.0, 3.0, 5.0, 11.0, 16.0, 11.0);
   private static final class_265 SHAPE_STANDING = class_2248.method_9541(4.0, 0.0, 4.0, 12.0, 13.0, 12.0);

   public LampLightBulb(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)this.method_9564().method_11657(ON, true));
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return state.method_11654(field_16545) ? SHAPE_HANGING : SHAPE_STANDING;
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 result) {
      if (!level.method_8608()) {
         level.method_8652(pos, (class_2680)state.method_11657(ON, !(Boolean)state.method_11654(ON)), 3);
         float f = state.method_11654(ON) ? 0.5F : 0.6F;
         level.method_8396(null, pos, class_3417.field_14962, class_3419.field_15245, 0.25F, f);
      }

      return class_1269.field_5812;
   }

   @Environment(EnvType.CLIENT)
   public void method_9496(class_2680 state, class_1937 level, class_2338 pos, class_5819 rand) {
      double d0 = pos.method_10263() + 0.5;
      double d1 = pos.method_10264() + 0.7;
      double d2 = pos.method_10260() + 0.5;
      if (rand.method_43056() && (Boolean)state.method_11654(ON)) {
         if ((Boolean)state.method_11654(field_16545)) {
            level.method_8406(class_2398.field_11251, d0, d1 - 0.3, d2, 0.0, 0.0, 0.0);
         } else {
            level.method_8406(class_2398.field_11251, d0, d1, d2, 0.0, 0.0, 0.0);
         }
      }
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{ON});
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.lamp.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.lamp.2").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
