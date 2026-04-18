package io.github.suel_ki.beautify.common.block;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_3619;
import net.minecraft.class_437;
import net.minecraft.class_5172;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_4970.class_2251;

public class Rope extends class_5172 {
   public Rope(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(field_24411, Boolean.FALSE))
            .method_11657(field_11459, class_2351.field_11052)
      );
   }

   public class_3619 getPistonPushReaction(class_2680 blockState) {
      return class_3619.field_15971;
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.rope.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.rope.2").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
