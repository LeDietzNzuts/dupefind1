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
public class TorseAndMantleModel<T extends class_1309> extends ArmorDecorationModel<T> {
   public TorseAndMantleModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117(
         "head",
         class_5606.method_32108()
            .method_32101(40, 0)
            .method_32098(-4.0F, -7.9F, -3.0F, 8.0F, 7.0F, 7.0F, new class_5605(1.2F))
            .method_32101(0, 0)
            .method_32098(-5.0F, -9.5F, -5.0F, 10.0F, 1.0F, 10.0F, new class_5605(0.3F))
            .method_32101(32, 16)
            .method_32098(-4.0F, -7.5F, -3.0F, 8.0F, 7.0F, 7.0F, new class_5605(1.4F))
            .method_32101(1, 32)
            .method_32098(-4.0F, -7.5F, -3.0F, 8.0F, 7.0F, 7.0F, new class_5605(1.6F))
            .method_32101(1, 15)
            .method_32098(-4.0F, -7.5F, -3.0F, 8.0F, 7.0F, 7.0F, new class_5605(1.8F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      partdefinition.method_32117("body", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("hat", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_leg", class_5606.method_32108(), class_5603.field_27701);
      return class_5607.method_32110(meshdefinition, 128, 64);
   }
}
