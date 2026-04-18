package com.magistuarmory.client.render.model.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class BucklerModel extends MedievalShieldModel {
   public BucklerModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117(
         "plate",
         class_5606.method_32108()
            .method_32101(14, 32)
            .method_32098(-3.0F, -8.0F, -2.0F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(12, 34)
            .method_32098(-5.0F, -7.0F, -2.0F, 8.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(11, 36)
            .method_32098(-6.0F, -6.0F, -2.0F, 10.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(10, 39)
            .method_32098(-7.0F, -4.0F, -2.0F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(11, 44)
            .method_32098(-6.0F, 0.0F, -2.0F, 10.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(12, 47)
            .method_32098(-5.0F, 2.0F, -2.0F, 8.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(14, 49)
            .method_32098(-3.0F, 3.0F, -2.0F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(47, 37)
            .method_32098(-3.0F, -5.0F, -3.0F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(46, 39)
            .method_32098(-4.0F, -4.0F, -3.0F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(47, 44)
            .method_32098(-3.0F, 0.0F, -3.0F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(14, 54)
            .method_32098(-3.0F, -4.0F, -4.0F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(1.0F, 2.0F, 0.0F)
      );
      partdefinition.method_32117(
         "handle",
         class_5606.method_32108().method_32101(48, 52).method_32098(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 64, 64);
   }
}
