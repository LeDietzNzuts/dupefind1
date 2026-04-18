package com.magistuarmory.item.armor;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;

public class JoustingItem extends DyeableMedievalArmorItem implements ISurcoat {
   public JoustingItem(ArmorType material, class_8051 type, class_1793 properties) {
      super(material, type, properties, 16119285);
   }

   @Override
   public int getColor(class_1799 stack) {
      return this.method_48398() == class_8051.field_41934 ? super.getColor(stack) : this.getDefaultColor();
   }

   public void method_7888(class_1799 stack, class_1937 level, class_1297 entity, int i, boolean selected) {
      if (entity instanceof class_1309 livingentity && livingentity.method_6118(this.method_48398().method_48399()) == stack) {
         livingentity.method_6092(new class_1293(class_1294.field_5909, 40, 1, false, false, false));
      }

      super.method_7888(stack, level, entity, i, selected);
   }

   @Override
   public void method_7851(class_1799 stack, class_9635 tooltipContext, List<class_2561> tooltip, class_1836 flag) {
      tooltip.add(class_2561.method_43471("slowmovementspeed").method_27692(class_124.field_1061));
   }
}
