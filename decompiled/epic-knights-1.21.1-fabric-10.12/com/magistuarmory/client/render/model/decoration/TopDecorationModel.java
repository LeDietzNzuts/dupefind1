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
public class TopDecorationModel<T extends class_1309> extends ArmorDecorationModel<T> {
   public TopDecorationModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117("body", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("hat", class_5606.method_32108(), class_5603.field_27701);
      class_5610 head = partdefinition.method_32117(
         "head",
         class_5606.method_32108().method_32101(35, -14).method_32098(0.0F, -24.05F, -13.0F, 0.0F, 24.0F, 24.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 decoration_r2_r1 = head.method_32117(
         "decoration_r2_r1",
         class_5606.method_32108().method_32101(39, 37).method_32098(-12.5F, -24.55F, 0.0F, 25.0F, 24.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F)
      );
      partdefinition.method_32117("right_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_leg", class_5606.method_32108(), class_5603.field_27701);
      return class_5607.method_32110(meshdefinition, 128, 128);
   }
}
