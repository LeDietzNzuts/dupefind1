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
public class BascinetModel {
   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 head = partdefinition.method_32117(
         "head",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(-4.0F, -7.75F, -3.2F, 8.0F, 7.0F, 7.0F, new class_5605(0.9F))
            .method_32101(6, 6)
            .method_32098(-4.0F, -7.75F, -3.7F, 8.0F, 7.0F, 1.0F, new class_5605(0.87F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r1 = head.method_32117(
         "cube_r1",
         class_5606.method_32108().method_32101(34, 0).method_32098(-4.0F, 0.6F, -6.0F, 8.0F, 0.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -5.2F, -4.7F, 0.5672F, 0.0F, 0.0F)
      );
      class_5610 cube_r2 = head.method_32117(
         "cube_r2",
         class_5606.method_32108().method_32101(36, 8).method_32098(-4.0F, 0.472F, -1.5337F, 8.0F, 0.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -2.072F, -7.8663F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r3 = head.method_32117(
         "cube_r3",
         class_5606.method_32108().method_32101(0, 14).method_32098(-4.0F, 1.4F, -2.0F, 8.0F, 2.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -7.3F, -5.5F, 0.7854F, 0.0F, 0.0F)
      );
      class_5610 cube_r4 = head.method_32117(
         "cube_r4",
         class_5606.method_32108().method_32101(50, 21).method_32098(-0.4F, -0.9F, -6.3F, 0.0F, 4.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(4.0F, -3.7F, -5.0F, 0.0F, 0.6615F, 0.0F)
      );
      class_5610 cube_r5 = head.method_32117(
         "cube_r5",
         class_5606.method_32108()
            .method_32101(50, 21)
            .method_32096()
            .method_32098(-5.95F, -0.9F, -1.37F, 0.0F, 4.0F, 7.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(4.0F, -3.7F, -5.0F, 0.0F, -0.6615F, 0.0F)
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
