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
public class StechhelmModel {
   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 head = partdefinition.method_32117(
         "head",
         class_5606.method_32108()
            .method_32101(34, 0)
            .method_32098(-4.0F, -7.7F, -3.2F, 8.0F, 7.0F, 7.0F, new class_5605(0.9F))
            .method_32101(40, 6)
            .method_32098(-4.0F, -7.65F, -3.7F, 8.0F, 7.0F, 1.0F, new class_5605(0.87F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 Helmet_r1 = head.method_32117("Helmet_r1", class_5606.method_32108(), class_5603.method_32091(0.0F, 0.0F, 0.0F, 1.0472F, -0.4363F, 1.5708F));
      class_5610 Helmet_r1_r1 = Helmet_r1.method_32117(
         "Helmet_r1_r1",
         class_5606.method_32108().method_32101(0, 0).method_32098(-3.7779F, -2.9347F, -1.8449F, 8.0F, 4.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(-5.1487F, -1.282F, -0.7257F, -0.0278F, -0.0436F, 0.0756F)
      );
      class_5610 Helmet_r2 = head.method_32117("Helmet_r2", class_5606.method_32108(), class_5603.method_32091(0.0F, 0.0F, 0.0F, 2.0944F, -0.4363F, 1.5708F));
      class_5610 Helmet_r2_r1 = Helmet_r2.method_32117(
         "Helmet_r2_r1",
         class_5606.method_32108().method_32101(0, 12).method_32098(-3.7769F, -2.9332F, -5.1559F, 8.0F, 4.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(-5.1487F, -1.282F, 0.7507F, 0.0453F, 0.0436F, 0.0756F)
      );
      class_5610 Helmet_r3 = head.method_32117(
         "Helmet_r3",
         class_5606.method_32108().method_32101(0, 23).method_32098(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new class_5605(0.3F)),
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
