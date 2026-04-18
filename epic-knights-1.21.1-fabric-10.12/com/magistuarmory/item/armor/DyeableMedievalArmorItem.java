package com.magistuarmory.item.armor;

import com.magistuarmory.item.DyeableItemLike;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_9282;
import net.minecraft.class_9334;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_5253.class_5254;

public class DyeableMedievalArmorItem extends MedievalArmorItem implements DyeableItemLike {
   private final int defaultcolor;

   public DyeableMedievalArmorItem(ArmorType material, class_8051 type, class_1793 properties, int defaultcolor) {
      super(material, type, properties);
      this.defaultcolor = class_5254.method_57174(defaultcolor);
   }

   public int getDefaultColor() {
      return this.defaultcolor;
   }

   @Override
   public int getColor(class_1799 stack) {
      class_9282 color = (class_9282)stack.method_57824(class_9334.field_49644);
      return class_5254.method_57174(color != null ? color.comp_2384() : this.getDefaultColor());
   }

   public void method_7851(class_1799 stack, class_9635 tooltipContext, List<class_2561> tooltip, class_1836 flag) {
      super.method_7851(stack, tooltipContext, tooltip, flag);
      tooltip.add(
         class_2561.method_43471("magistuarmory.dyeable_armor.description")
            .method_27696(class_2583.field_24360.method_10977(class_124.field_1078).method_10978(true))
      );
   }
}
