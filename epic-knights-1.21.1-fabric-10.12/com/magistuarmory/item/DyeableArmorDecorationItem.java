package com.magistuarmory.item;

import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_9282;
import net.minecraft.class_9334;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_5253.class_5254;

public class DyeableArmorDecorationItem extends ArmorDecorationItem implements DyeableItemLike {
   int defaultcolor;

   public DyeableArmorDecorationItem(class_2960 location, class_1793 properties, class_8051 armorType) {
      this(location, properties, armorType, -1);
      this.armorType = armorType;
   }

   public DyeableArmorDecorationItem(class_2960 location, class_1793 properties, class_8051 armorType, int defaultcolor) {
      super(location, properties, armorType);
      this.defaultcolor = class_5254.method_57174(defaultcolor);
   }

   public void setColor(class_1799 stack, int color) {
      stack.method_57379(class_9334.field_49644, new class_9282(color, true));
   }

   @Override
   public int getColor(class_1799 stack) {
      class_9282 color = (class_9282)stack.method_57824(class_9334.field_49644);
      return class_5254.method_57174(color != null ? color.comp_2384() : this.defaultcolor);
   }

   @Override
   public class_2487 getCompoundTag(class_1799 stack) {
      class_2487 compoundnbt = new class_2487();
      compoundnbt.method_10582("name", this.location.toString());
      compoundnbt.method_10556("dyeable", true);
      compoundnbt.method_10569("color", this.getColor(stack));
      return compoundnbt;
   }
}
