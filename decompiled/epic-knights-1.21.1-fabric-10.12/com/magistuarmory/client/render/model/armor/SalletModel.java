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
public class SalletModel {
   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 head = partdefinition.method_32117(
         "head",
         class_5606.method_32108().method_32101(32, 0).method_32098(-4.0F, -7.7F, -4.2F, 8.0F, 7.0F, 8.0F, new class_5605(0.9F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 Helmet_r1 = head.method_32117(
         "Helmet_r1",
         class_5606.method_32108().method_32101(52, 26).method_32098(-4.7F, -2.6F, -4.7F, 3.0F, 3.0F, 3.0F, new class_5605(-0.2F)),
         class_5603.method_32091(0.0F, -0.2F, 0.0F, 0.0F, -0.7854F, 0.0F)
      );
      class_5610 Helmet_r2 = head.method_32117(
         "Helmet_r2",
         class_5606.method_32108().method_32101(0, 10).method_32098(-9.3F, 5.6F, 1.67F, 8.0F, 0.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(6.25F, 0.4F, 0.0F, -1.789F, 0.1309F, 1.5708F)
      );
      class_5610 Helmet_r3 = head.method_32117(
         "Helmet_r3",
         class_5606.method_32108().method_32101(0, 0).method_32098(-9.3F, 6.9F, -1.806F, 8.0F, 0.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.275F, 0.4F, 0.0F, -1.3526F, 0.1309F, 1.5708F)
      );
      class_5610 bone = head.method_32117("bone", class_5606.method_32108(), class_5603.method_32091(-4.0F, 0.6F, -2.9F, 0.1309F, 0.0F, 0.0F));
      class_5610 Helmet_r4 = bone.method_32117(
         "Helmet_r4",
         class_5606.method_32108()
            .method_32101(12, 6)
            .method_32096()
            .method_32098(-4.3966F, -2.0522F, 0.0F, 8.0F, 4.0F, 0.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(9.3301F, -6.3606F, 0.4989F, -1.5708F, -0.2618F, -1.5708F)
      );
      class_5610 Helmet_r5 = bone.method_32117(
         "Helmet_r5",
         class_5606.method_32108().method_32101(12, 6).method_32098(-3.6034F, -2.0522F, 0.0F, 8.0F, 4.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32091(-1.3301F, -6.3606F, 0.4989F, -1.5708F, 0.2618F, 1.5708F)
      );
      class_5610 Helmet_r6 = bone.method_32117(
         "Helmet_r6",
         class_5606.method_32108().method_32101(20, 11).method_32098(-2.9034F, -9.5F, -2.5478F, 1.0F, 11.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 1.5708F)
      );
      class_5610 Helmet_r7 = bone.method_32117(
         "Helmet_r7",
         class_5606.method_32108().method_32101(0, 22).method_32098(-2.1044F, -4.7473F, -6.6221F, 1.0F, 4.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 3.0F, -2.0071F, 0.2618F, 1.5708F)
      );
      class_5610 Helmet_r8 = bone.method_32117(
         "Helmet_r8",
         class_5606.method_32108().method_32101(0, 22).method_32098(-2.1034F, -6.4473F, -2.9879F, 1.0F, 4.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(4.0F, 0.0F, 3.0F, -1.1345F, 0.2618F, 1.5708F)
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
