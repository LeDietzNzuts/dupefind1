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
public class WingedHussarChestplateModel {
   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117("head", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("hat", class_5606.method_32108(), class_5603.field_27701);
      class_5610 partdefinition1 = partdefinition.method_32117(
         "body",
         class_5606.method_32108().method_32101(0, 0).method_32098(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new class_5605(0.5F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      partdefinition1.method_32117(
         "wing_r1",
         class_5606.method_32108()
            .method_32101(36, -14)
            .method_32096()
            .method_32098(0.0F, -17.0F, -5.0F, 0.0F, 32.0F, 14.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(-4.0524F, -3.7999F, 6.0F, -0.1309F, 0.0F, -0.1309F)
      );
      partdefinition1.method_32117(
         "wing_r2",
         class_5606.method_32108().method_32101(36, -14).method_32098(0.0F, -17.0F, -5.5F, 0.0F, 32.0F, 14.0F, new class_5605(0.0F)),
         class_5603.method_32091(4.0524F, -3.7999F, 6.5F, -0.1309F, 0.0F, 0.1309F)
      );
      partdefinition.method_32117(
         "right_arm",
         class_5606.method_32108().method_32101(0, 16).method_32098(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new class_5605(0.5F)),
         class_5603.method_32090(-5.0F, 2.0F, 0.0F)
      );
      partdefinition.method_32117(
         "left_arm",
         class_5606.method_32108()
            .method_32101(0, 16)
            .method_32096()
            .method_32098(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32090(5.0F, 2.0F, 0.0F)
      );
      partdefinition.method_32117("right_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_leg", class_5606.method_32108(), class_5603.field_27701);
      return class_5607.method_32110(meshdefinition, 64, 32);
   }
}
