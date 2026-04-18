package com.magistuarmory.client.render.model.decoration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1496;
import net.minecraft.class_549;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class HorseArmorDecorationModel<T extends class_1496> extends class_549<T> {
   class_630[] parts = new class_630[]{this.field_3305};

   public HorseArmorDecorationModel(class_630 root) {
      super(root);
   }

   public class_630[] parts() {
      return this.parts;
   }
}
