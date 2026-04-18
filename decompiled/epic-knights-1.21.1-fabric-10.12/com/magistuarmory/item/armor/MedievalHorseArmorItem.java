package com.magistuarmory.item.armor;

import net.minecraft.class_1741;
import net.minecraft.class_2960;
import net.minecraft.class_4059;
import net.minecraft.class_6880;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_4059.class_9076;

public class MedievalHorseArmorItem extends class_4059 {
   private final class_2960 texture;

   public MedievalHorseArmorItem(class_6880<class_1741> material, class_2960 texture, boolean dyeable, class_1793 properties) {
      super(material, class_9076.field_47825, dyeable, properties.method_7889(1));
      this.texture = texture;
   }

   public class_2960 method_18454() {
      return this.texture;
   }
}
