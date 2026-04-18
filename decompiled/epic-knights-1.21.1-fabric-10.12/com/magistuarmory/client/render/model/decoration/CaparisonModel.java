package com.magistuarmory.client.render.model.decoration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1496;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class CaparisonModel<T extends class_1496> extends HorseArmorDecorationModel<T> {
   public CaparisonModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 partdefinition1 = partdefinition.method_32117(
         "body",
         class_5606.method_32108().method_32101(0, 0).method_32098(-5.0F, -8.0F, -17.0F, 10.0F, 18.0F, 22.0F, new class_5605(0.2F)),
         class_5603.method_32090(0.0F, 11.0F, 5.0F)
      );
      partdefinition1.method_32117("tail", class_5606.method_32108(), class_5603.field_27701);
      partdefinition1.method_32117("saddle", class_5606.method_32108(), class_5603.field_27701);
      class_5610 partdefinition2 = partdefinition.method_32117("head_parts", class_5606.method_32108(), class_5603.field_27701);
      partdefinition2.method_32117("left_saddle_mouth", class_5606.method_32108(), class_5603.field_27701);
      partdefinition2.method_32117("right_saddle_mouth", class_5606.method_32108(), class_5603.field_27701);
      partdefinition2.method_32117("left_saddle_line", class_5606.method_32108(), class_5603.field_27701);
      partdefinition2.method_32117("right_saddle_line", class_5606.method_32108(), class_5603.field_27701);
      partdefinition2.method_32117("head_saddle", class_5606.method_32108(), class_5603.field_27701);
      partdefinition2.method_32117("mouth_saddle_wrap", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_hind_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_hind_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_front_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_front_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_hind_baby_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_hind_baby_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_front_baby_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_front_baby_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition1.method_32117("tail", class_5606.method_32108(), class_5603.field_27701);
      return class_5607.method_32110(meshdefinition, 64, 64);
   }
}
