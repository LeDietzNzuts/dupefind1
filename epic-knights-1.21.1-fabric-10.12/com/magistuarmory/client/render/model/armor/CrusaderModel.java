package com.magistuarmory.client.render.model.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;

@Environment(EnvType.CLIENT)
public class CrusaderModel {
   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 head = partdefinition.method_32117(
         "head",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new class_5605(0.55F))
            .method_32101(32, 5)
            .method_32098(-3.5F, -8.4F, -3.5F, 7.0F, 8.0F, 3.0F, new class_5605(1.3F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 hat = partdefinition.method_32117("hat", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 body = partdefinition.method_32117("body", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 left_arm = partdefinition.method_32117("left_arm", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 right_arm = partdefinition.method_32117("right_arm", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 left_leg = partdefinition.method_32117("left_leg", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 right_leg = partdefinition.method_32117("right_leg", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      return class_5607.method_32110(meshdefinition, 64, 32);
   }
}
