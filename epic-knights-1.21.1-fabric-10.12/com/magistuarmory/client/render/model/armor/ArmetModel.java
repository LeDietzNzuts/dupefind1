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
public class ArmetModel {
   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 head = partdefinition.method_32117(
         "head",
         class_5606.method_32108().method_32101(0, 0).method_32098(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r1 = head.method_32117(
         "cube_r1",
         class_5606.method_32108().method_32101(54, 7).method_32098(-0.5F, 0.0F, -6.0F, 1.0F, 0.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.025F, -4.725F, -8.85F, 2.3518F, 0.0F, 0.0F)
      );
      class_5610 cube_r2 = head.method_32117(
         "cube_r2",
         class_5606.method_32108().method_32101(56, 7).method_32098(-0.5F, 0.0F, -6.0F, 1.0F, 0.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.025F, -8.675F, -4.45F, 0.7374F, 0.0F, 0.0F)
      );
      class_5610 Armet = head.method_32117(
         "Armet",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(0.0F, 0.15F, 0.8F, 8.0F, 7.0F, 7.0F, new class_5605(0.9F))
            .method_32101(6, 6)
            .method_32098(0.0F, 0.15F, 0.3F, 8.0F, 7.0F, 1.0F, new class_5605(0.87F)),
         class_5603.method_32090(-4.0F, -8.0F, -4.0F)
      );
      class_5610 VisorTopLeft = head.method_32117(
         "VisorTopLeft",
         class_5606.method_32108().method_32101(47, 0).method_32098(0.4F, 0.7992F, -2.4668F, 7.0F, 5.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -0.7285F, -0.6829F, 0.0F)
      );
      class_5610 VisorTopRight = head.method_32117(
         "VisorTopRight", class_5606.method_32108(), class_5603.method_32091(-4.0F, -8.0F, -4.0F, -0.7285F, 0.6374F, 0.0F)
      );
      class_5610 VisorBottomLeft = head.method_32117(
         "VisorBottomLeft", class_5606.method_32108(), class_5603.method_32091(-4.0F, -8.0F, -4.0F, 0.6829F, -0.6829F, 0.0F)
      );
      class_5610 VisorBottomRight = head.method_32117(
         "VisorBottomRight",
         class_5606.method_32108().method_32101(30, 0).method_32098(-0.925F, 1.8276F, -2.9631F, 7.0F, 6.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, 0.6829F, 0.6829F, 0.0F)
      );
      class_5610 VisorTopRight2 = head.method_32117(
         "VisorTopRight2",
         class_5606.method_32108()
            .method_32101(47, 0)
            .method_32096()
            .method_32098(-7.4F, 0.7992F, -2.4668F, 7.0F, 5.0F, 0.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(4.0F, -8.0F, -4.0F, -0.7285F, 0.6829F, 0.0F)
      );
      class_5610 VisorBottomLeft2 = head.method_32117(
         "VisorBottomLeft2",
         class_5606.method_32108()
            .method_32101(30, 0)
            .method_32096()
            .method_32098(-6.075F, 1.8276F, -2.9631F, 7.0F, 6.0F, 0.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(4.0F, -8.0F, -4.0F, 0.6829F, -0.6829F, 0.0F)
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
