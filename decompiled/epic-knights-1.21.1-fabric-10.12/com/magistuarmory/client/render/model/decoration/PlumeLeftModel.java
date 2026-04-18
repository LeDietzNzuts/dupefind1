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
public class PlumeLeftModel<T extends class_1309> extends ArmorDecorationModel<T> {
   public PlumeLeftModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117("body", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("hat", class_5606.method_32108(), class_5603.field_27701);
      class_5610 head = partdefinition.method_32117("head", class_5606.method_32108(), class_5603.field_27701);
      head.method_32117(
         "plume",
         class_5606.method_32108().method_32101(0, 0).method_32098(-1.0F, -14.5F, 0.0F, 16.0F, 16.0F, 0.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -5.5F, 5.0F, -0.2618F, -0.48F, -0.2618F)
      );
      partdefinition.method_32117("right_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_leg", class_5606.method_32108(), class_5603.field_27701);
      return class_5607.method_32110(meshdefinition, 32, 16);
   }
}
