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
public class MaximilianHelmetModel {
   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 head = partdefinition.method_32117("head", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 Helmet_r1 = head.method_32117(
         "Helmet_r1",
         class_5606.method_32108().method_32101(0, 5).method_32098(-3.6F, -7.2F, -3.75F, 7.0F, 5.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -0.1F, 0.2F, 0.552F, -0.4648F, 0.0F)
      );
      class_5610 Helmet_r2 = head.method_32117(
         "Helmet_r2",
         class_5606.method_32108()
            .method_32101(0, 5)
            .method_32096()
            .method_32098(-3.4F, -7.2F, -3.75F, 7.0F, 5.0F, 0.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(0.0F, -0.1F, 0.2F, 0.552F, 0.4648F, 0.0F)
      );
      class_5610 Helmet_r3 = head.method_32117(
         "Helmet_r3",
         class_5606.method_32108().method_32101(0, 0).method_32098(-3.6F, -4.9F, -8.1F, 7.0F, 5.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -0.1F, 0.2F, -0.552F, -0.4648F, 0.0F)
      );
      class_5610 Helmet_r4 = head.method_32117(
         "Helmet_r4",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32096()
            .method_32098(-3.4F, -4.9F, -8.1F, 7.0F, 5.0F, 0.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(0.0F, -0.1F, 0.2F, -0.552F, 0.4648F, 0.0F)
      );
      class_5610 Armet = head.method_32117(
         "Armet",
         class_5606.method_32108()
            .method_32101(32, 0)
            .method_32098(0.0F, 0.15F, 0.8F, 8.0F, 7.0F, 7.0F, new class_5605(0.9F))
            .method_32101(38, 6)
            .method_32098(0.0F, 0.15F, 0.3F, 8.0F, 7.0F, 1.0F, new class_5605(0.87F)),
         class_5603.method_32090(-4.0F, -8.0F, -4.0F)
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
