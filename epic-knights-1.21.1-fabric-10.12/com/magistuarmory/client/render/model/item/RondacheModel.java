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
public class RondacheModel extends MedievalShieldModel {
   public RondacheModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117(
         "plate",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32097(-17.0F, -17.0F, -2.0F, 31.0F, 31.0F, 1.0F)
            .method_32101(0, 33)
            .method_32097(-9.0F, -5.0F, -2.0F, 16.0F, 6.0F, 1.0F)
            .method_32101(34, 33)
            .method_32097(-8.0F, 1.0F, -2.0F, 14.0F, 2.0F, 1.0F)
            .method_32101(38, 36)
            .method_32097(-7.0F, 3.0F, -2.0F, 12.0F, 1.0F, 1.0F)
            .method_32101(42, 38)
            .method_32097(-6.0F, 4.0F, -2.0F, 10.0F, 1.0F, 1.0F)
            .method_32101(50, 40)
            .method_32097(-4.0F, 5.0F, -2.0F, 6.0F, 1.0F, 1.0F)
            .method_32101(34, 42)
            .method_32097(-8.0F, -7.0F, -2.0F, 14.0F, 2.0F, 1.0F)
            .method_32101(38, 45)
            .method_32097(-7.0F, -8.0F, -2.0F, 12.0F, 1.0F, 1.0F)
            .method_32101(42, 49)
            .method_32097(-6.0F, -9.0F, -2.0F, 10.0F, 1.0F, 1.0F)
            .method_32101(50, 47)
            .method_32097(-4.0F, -10.0F, -2.0F, 6.0F, 1.0F, 1.0F),
         class_5603.field_27701
      );
      partdefinition.method_32117(
         "handle", class_5606.method_32108().method_32101(48, 52).method_32097(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F), class_5603.field_27701
      );
      return class_5607.method_32110(meshdefinition, 64, 64);
   }
}
