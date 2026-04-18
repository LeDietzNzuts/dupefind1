package io.github.suel_ki.beautify.common.block;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Random;
import net.minecraft.class_124;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
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
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class PictureFrame extends class_2383 {
   private static final int MODELCOUNT = 13;
   public static final class_2758 FRAME_MOTIVE = class_2758.method_11867("frame_motive", 0, 12);
   protected static final class_265 SHAPE = class_2248.method_9541(5.0, 0.0, 5.0, 11.0, 8.0, 11.0);
   public static final MapCodec<PictureFrame> CODEC = method_54094(PictureFrame::new);

   public PictureFrame(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(FRAME_MOTIVE, 0)).method_11657(field_11177, class_2350.field_11043));
   }

   protected MapCodec<PictureFrame> method_53969() {
      return CODEC;
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 result) {
      if (!level.method_8608() && player.method_5715()) {
         int currentModel = (Integer)state.method_11654(FRAME_MOTIVE);
         if (currentModel + 1 > 12) {
            level.method_8652(pos, (class_2680)state.method_11657(FRAME_MOTIVE, 0), 3);
         } else {
            level.method_8652(pos, (class_2680)state.method_11657(FRAME_MOTIVE, currentModel + 1), 3);
         }

         level.method_8396(null, pos, class_3417.field_14875, class_3419.field_15245, 1.0F, 1.0F);
         return class_1269.field_5812;
      } else {
         return class_1269.field_5812;
      }
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public class_265 method_9571(class_2680 state, class_1922 level, class_2338 pos) {
      return class_259.method_1073();
   }

   public class_2680 method_9605(class_1750 context) {
      Random rand = new Random();
      int randomNum = rand.nextInt(13);
      return (class_2680)((class_2680)this.method_9564().method_11657(field_11177, context.method_8042().method_10153())).method_11657(FRAME_MOTIVE, randomNum);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{FRAME_MOTIVE, field_11177});
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.picture_frame.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.picture_frame.2").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
