package com.magistuarmory.client.render.model.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5603;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class PaviseModel extends MedievalShieldModel {
   public PaviseModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117(
         "plate",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32097(-15.5F, -17.0F, -2.0F, 31.0F, 31.0F, 1.0F)
            .method_32101(0, 33)
            .method_32097(-9.5F, -12.0F, -2.0F, 19.0F, 22.0F, 1.0F)
            .method_32101(0, 56)
            .method_32097(-8.5F, 10.0F, -2.0F, 17.0F, 1.0F, 1.0F)
            .method_32101(0, 58)
            .method_32097(-7.5F, 11.0F, -2.0F, 15.0F, 1.0F, 1.0F)
            .method_32101(0, 60)
            .method_32097(-8.5F, -13.0F, -2.0F, 17.0F, 1.0F, 1.0F)
            .method_32101(0, 62)
            .method_32097(-7.5F, -14.0F, -2.0F, 15.0F, 1.0F, 1.0F)
            .method_32101(13, 0)
            .method_32097(-2.5F, -17.0F, -3.0F, 5.0F, 32.0F, 1.0F)
            .method_32101(52, 33)
            .method_32097(-2.5F, -14.0F, -3.0F, 5.0F, 1.0F, 1.0F)
            .method_32101(52, 35)
            .method_32097(-2.5F, 11.0F, -3.0F, 5.0F, 1.0F, 1.0F),
         class_5603.field_27701
      );
      partdefinition.method_32117(
         "handle", class_5606.method_32108().method_32101(48, 52).method_32097(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F), class_5603.field_27701
      );
      return class_5607.method_32110(meshdefinition, 64, 64);
   }
}
