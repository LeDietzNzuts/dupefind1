package io.github.suel_ki.beautify.common.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2561;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_437;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class BotanistWorkbench extends class_2383 {
   private static final Map<class_2350, class_265> SHAPES_FOR_MODEL = ImmutableMap.of(
      class_2350.field_11043,
      class_259.method_1084(method_9541(2.0, 0.0, 0.0, 16.0, 12.0, 14.25), method_9541(9.5, 12.0, 8.5, 13.5, 16.0, 12.5)),
      class_2350.field_11035,
      class_259.method_1084(method_9541(0.0, 0.0, 1.75, 14.0, 12.0, 16.0), method_9541(2.5, 12.0, 3.5, 6.5, 16.0, 7.5)),
      class_2350.field_11039,
      class_259.method_1084(method_9541(0.0, 0.0, 0.0, 14.25, 12.0, 14.0), method_9541(8.5, 12.0, 2.5, 12.5, 16.0, 6.5)),
      class_2350.field_11034,
      class_259.method_1084(method_9541(1.75, 0.0, 2.0, 16.0, 12.0, 16.0), method_9541(3.5, 12.0, 9.5, 7.5, 16.0, 13.5))
   );
   public static final MapCodec<BotanistWorkbench> CODEC = method_54094(BotanistWorkbench::new);

   public BotanistWorkbench(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)this.method_9564().method_11657(field_11177, class_2350.field_11043));
   }

   protected MapCodec<BotanistWorkbench> method_53969() {
      return CODEC;
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(field_11177, context.method_8042().method_10153());
   }

   public class_265 method_9530(class_2680 state, class_1922 getter, class_2338 pos, class_3726 context) {
      return SHAPES_FOR_MODEL.get(state.method_11654(field_11177));
   }

   protected void method_9515(class_2690<class_2248, class_2680> pBuilder) {
      super.method_9515(pBuilder);
      pBuilder.method_11667(new class_2769[]{field_11177});
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.botanist_workbench.1").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
