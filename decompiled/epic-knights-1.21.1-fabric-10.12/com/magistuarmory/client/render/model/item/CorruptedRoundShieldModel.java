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
public class CorruptedRoundShieldModel extends MedievalShieldModel {
   public CorruptedRoundShieldModel(class_630 root) {
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
            .method_32101(12, 60)
            .method_32098(-3.5F, 4.0F, -2.0F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(10, 58)
            .method_32098(-5.5F, 3.0F, -2.0F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(9, 55)
            .method_32098(-6.5F, 1.0F, -2.0F, 13.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(3, 52)
            .method_32098(-7.5F, -1.0F, -2.0F, 4.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(3, 47)
            .method_32098(-7.5F, -5.0F, -2.0F, 3.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(3, 45)
            .method_32098(-7.5F, -6.0F, -2.0F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(13, 51)
            .method_32098(-2.5F, -2.0F, -2.0F, 10.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(19, 49)
            .method_32098(-3.5F, -3.0F, -2.0F, 3.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(19, 47)
            .method_32098(-3.5F, -4.0F, -2.0F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(20, 44)
            .method_32098(-2.5F, -6.0F, -2.0F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(29, 47)
            .method_32098(0.5F, -5.0F, -2.0F, 7.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(25, 44)
            .method_32098(0.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(31, 45)
            .method_32098(2.5F, -6.0F, -2.0F, 5.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(31, 42)
            .method_32098(2.5F, -8.0F, -2.0F, 4.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(30, 40)
            .method_32098(1.5F, -9.0F, -2.0F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(30, 38)
            .method_32098(1.5F, -10.0F, -2.0F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F)),
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
