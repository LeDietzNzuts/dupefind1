package com.magistuarmory.client.render.model.block;

import com.magistuarmory.client.render.model.item.MedievalShieldModel;
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
public class PaviseBlockModel extends MedievalShieldModel {
   public PaviseBlockModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      partdefinition.method_32117(
         "plate",
         class_5606.method_32108()
            .method_32101(52, 35)
            .method_32098(-9.5F, 7.0F, -5.0F, 5.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(52, 33)
            .method_32098(-9.5F, -18.0F, -5.0F, 5.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 58)
            .method_32098(-14.5F, 7.0F, -4.0F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 56)
            .method_32098(-15.5F, 6.0F, -4.0F, 17.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 33)
            .method_32098(-16.5F, -16.0F, -4.0F, 19.0F, 22.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 60)
            .method_32098(-15.5F, -17.0F, -4.0F, 17.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 62)
            .method_32098(-14.5F, -18.0F, -4.0F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(13, 0)
            .method_32098(-9.5F, -21.0F, -5.0F, 5.0F, 32.0F, 1.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-22.5F, -21.0F, -4.0F, 31.0F, 32.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.0F, 2.0F, 0.0F, -0.3927F, 0.0F, 0.0F)
      );
      class_5610 handleAndSupport = partdefinition.method_32117("handle", class_5606.method_32108(), class_5603.field_27701);
      handleAndSupport.method_32117(
         "support",
         class_5606.method_32108()
            .method_32101(41, 6)
            .method_32096()
            .method_32098(-15.0F, -8.0F, 3.0F, 2.0F, 16.0F, 1.0F, new class_5605(0.0F))
            .method_32106(false)
            .method_32101(41, 6)
            .method_32098(-1.0F, -8.0F, 3.0F, 2.0F, 16.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.0F, 2.0F, 0.0F, 0.3927F, 0.0F, 0.0F)
      );
      handleAndSupport.method_32117(
         "handle2",
         class_5606.method_32108().method_32101(48, 52).method_32098(-8.0F, -9.0F, -3.0F, 2.0F, 6.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.0F, 2.0F, 0.0F, -0.3927F, 0.0F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 64, 64);
   }
}
