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
public class KiteShieldModel extends MedievalShieldModel {
   public KiteShieldModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117(
         "plate",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(-15.5F, -17.0F, -2.0F, 31.0F, 32.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 33)
            .method_32098(-3.5F, -9.0F, -2.0F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 35)
            .method_32098(-5.5F, -8.0F, -2.0F, 11.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 38)
            .method_32098(-6.5F, -6.0F, -2.0F, 13.0F, 7.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 46)
            .method_32098(-5.5F, 1.0F, -2.0F, 11.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 49)
            .method_32098(-4.5F, 3.0F, -2.0F, 9.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 52)
            .method_32098(-3.5F, 5.0F, -2.0F, 7.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 55)
            .method_32098(-2.5F, 7.0F, -2.0F, 5.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 58)
            .method_32098(-1.5F, 9.0F, -2.0F, 3.0F, 2.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 2.0F, 0.0F)
      );
      partdefinition.method_32117(
         "handle",
         class_5606.method_32108().method_32101(48, 52).method_32098(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 64, 64);
   }
}
