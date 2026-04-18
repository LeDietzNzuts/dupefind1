package com.magistuarmory.client.render.model.decoration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class FlowercrownModel<T extends class_1309> extends ArmorDecorationModel<T> {
   public FlowercrownModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117("body", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("hat", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117(
         "head",
         class_5606.method_32108().method_32101(0, 0).method_32098(-5.0F, -9.0F, -5.0F, 10.0F, 4.0F, 10.0F, new class_5605(0.4F)),
         class_5603.field_27701
      );
      partdefinition.method_32117("right_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_leg", class_5606.method_32108(), class_5603.field_27701);
      return class_5607.method_32110(meshdefinition, 64, 64);
   }
}
