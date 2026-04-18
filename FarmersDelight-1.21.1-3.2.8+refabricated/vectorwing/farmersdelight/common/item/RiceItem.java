package vectorwing.farmersdelight.common.item;

import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1798;
import net.minecraft.class_1838;
import net.minecraft.class_2248;
import net.minecraft.class_2344;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_1792.class_1793;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class RiceItem extends class_1798 {
   public RiceItem(class_2248 block, class_1793 properties) {
      super(block, properties);
   }

   public class_1269 method_7884(class_1838 context) {
      class_1269 result = this.method_7712(new class_1750(context));
      if (result.equals(class_1269.field_5814)) {
         class_1657 player = context.method_8036();
         class_2680 targetState = context.method_8045().method_8320(context.method_8037());
         if (player != null
            && context.method_8038().equals(class_2350.field_11036)
            && (targetState.method_26164(class_3481.field_29822) || targetState.method_26204() instanceof class_2344)) {
            player.method_7353(TextUtils.getTranslation("block.rice.invalid_placement"), true);
         }
      }

      return !result.method_23665() ? this.method_7836(context.method_8045(), context.method_8036(), context.method_20287()).method_5467() : result;
   }
}
