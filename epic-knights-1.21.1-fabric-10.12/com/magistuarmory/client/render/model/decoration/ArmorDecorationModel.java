package com.magistuarmory.client.render.model.decoration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_572;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class ArmorDecorationModel<T extends class_1309> extends class_572<T> {
   class_630[] parts = new class_630[]{this.field_3398, this.field_3391, this.field_3401, this.field_27433};

   public ArmorDecorationModel(class_630 root) {
      super(root);
   }

   public class_630[] parts() {
      return this.parts;
   }
}
